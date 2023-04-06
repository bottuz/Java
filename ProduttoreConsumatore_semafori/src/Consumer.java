import java.util.Date;

public class Consumer implements Runnable {
	private Buffer buffer;

	public Consumer(Buffer buffer) {
		this.buffer = buffer;
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		Date message;
		while (true) {
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			message = (Date) buffer.remove();
		}
	}
}
