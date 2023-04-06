import java.util.Date;

public class Producer implements Runnable {
	private Buffer buffer;

	public Producer(Buffer buffer) {
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
			message = new Date();
			buffer.Insert(message);
		}
	}

}
