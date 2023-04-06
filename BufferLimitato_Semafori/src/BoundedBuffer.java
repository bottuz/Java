import java.util.concurrent.Semaphore;

public class BoundedBuffer implements Buffer {
	private static final int BUFFER_SIZE = 5;
	private Object[] buffer;
	private int in, out;
	private Semaphore mutex;
	private Semaphore empty;
	private Semaphore full;

	public BoundedBuffer() {
		// buffer empty
		in = 0;
		out = 0;
		buffer = new Object[BUFFER_SIZE];

		mutex = new Semaphore(1);
		empty = new Semaphore(BUFFER_SIZE);
		full = new Semaphore(0);
	}

	public void insert(Object item) throws InterruptedException {
		try {
			empty.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mutex.acquire();

		// add an item to the buffer
		buffer[in] = item;
		in = (in + 1) % BUFFER_SIZE;

		mutex.release();
		full.release();
	}

	public Object remove() throws InterruptedException {
		try {
			full.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mutex.acquire();

		// remove an item from the buffer
		Object item = buffer[out];
		out = (out + 1) % BUFFER_SIZE;

		mutex.release();
		empty.release();

		return item;
	}
}
