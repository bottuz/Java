import java.util.concurrent.Semaphore;

public class CokeMachine implements Machine {
	private static int LATTINE_MAX = 5;
	private Object[] buffer;
	private int in, out;
	private Semaphore mutex;
	private Semaphore empty;
	private Semaphore full;

	public CokeMachine() {
		buffer = new Object[LATTINE_MAX];
		in = 0;
		out = 0;
		mutex = new Semaphore(1);
		empty = new Semaphore(LATTINE_MAX);
		full = new Semaphore(0);
	}

	@Override
	public void rifornisci(Object o) {
		try {
			empty.acquire();
			mutex.acquire();

			buffer[in] = o;
			in = (in + 1) % LATTINE_MAX;

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.release();
			full.release();
		}

	}

	@Override
	public Object preleva() {
		Object obj = null;
		try {
			full.acquire();
			mutex.acquire();

			obj = buffer[out];
			out = (out + 1) % LATTINE_MAX;

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.release();
			empty.release();
		}
		return obj;
	}

}
