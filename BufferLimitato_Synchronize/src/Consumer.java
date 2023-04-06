import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
	private Buffer buffer;

	public Consumer(Buffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		Date message;

		while (true) {
			// nap for awhile
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // produce an item & enter it into the buffer
			message = (Date) buffer.remove();
			System.out.println("Consumatore rimuove il messaggio!" + message.toString());
		}
	}
}
