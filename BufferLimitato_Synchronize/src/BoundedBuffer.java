
public class BoundedBuffer implements Buffer {
	private static int BUFFER_SIZE = 5;
	private int count, in, out;
	private Object[] buffer;

	public BoundedBuffer() {
		count = 0;
		in = 0;
		out = 0;
		buffer = new Object[BUFFER_SIZE];
	}

	@Override
	public synchronized void insert(Object item) {
		while (count == BUFFER_SIZE) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		++count;
		buffer[in] = item;
		in = (in + 1) % BUFFER_SIZE;
		notify();
	}

	@Override
	public synchronized Object remove() {
		Object item;
		while (count == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		--count;
		item = buffer[out];
		out = (out + 1) % BUFFER_SIZE;
		return item;
	}

}
