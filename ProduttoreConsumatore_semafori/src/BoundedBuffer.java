import java.util.concurrent.Semaphore;

public class BoundedBuffer implements Buffer {
	private static int BUFFER_SIZE = 5;
	private Object[] buffer;
	private int in, out;
	private Semaphore mutex;
	private Semaphore empty;
	private Semaphore full;

	public BoundedBuffer() {
		buffer = new Object[BUFFER_SIZE];
		in = 0;
		out = 0;
		mutex = new Semaphore(1);
		empty = new Semaphore(BUFFER_SIZE);
		full = new Semaphore(0);
	}

	@Override
	public void Insert(Object o) {
		try {
			mutex.acquire();
			empty.acquire();
			buffer[in] = o;
			in = (in + 1) % BUFFER_SIZE;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.release();
			full.release();
		}
	}

	@Override
	public Object remove() {
		Object o = null;
		try {
			mutex.acquire();
			full.acquire();
			o = buffer[out];
			out = (out + 1) % BUFFER_SIZE;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.release();
			empty.release();
		}
		return o;
	}
}
