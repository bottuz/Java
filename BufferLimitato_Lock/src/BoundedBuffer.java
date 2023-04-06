import java.util.concurrent.locks.*;

public class BoundedBuffer implements Buffer {
	private static int BUFFERSIZE = 5;
	private int in, out, count;
	Object[] buffer;

//condition
	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();

	public BoundedBuffer() {
		in = 0;
		out = 0;
		count = 0;
		buffer = new Object[BUFFERSIZE];
	}

	@Override
	public void insert(Object item) throws InterruptedException {
		lock.lock();
		try {
			while (count == BUFFERSIZE)
				notFull.await();
			++count;
			buffer[in] = item;
			in = (in + 1) % BUFFERSIZE;
			if (count == BUFFERSIZE)
				System.out.println("Producer Entered " + item + " Buffer FULL");
			else
				System.out.println("Producer Entered " + item + " Buffer Size = " + count);

			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public Object remove() throws InterruptedException {
		Object remove;
		lock.lock();
		try {
			while (count == 0)
				notEmpty.await();
			--count;
			remove = buffer[out];
			out = (out + 1) % BUFFERSIZE;
			if (count == 0)
				System.out.println("Consumer Consumed " + remove + " Buffer EMPTY");
			else
				System.out.println("Consumer Consumed " + remove + " Buffer Size = " + count);
			notFull.signal();
			return remove;
		} finally {
			lock.unlock();
		}
	}

}
