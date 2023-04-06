import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {
	private Buffer buffer;

	public Producer(Buffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		Date message;

		while (true) {
			// nap for awhile
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// produce an item & enter it into the buffer
			message = new Date();
			buffer.insert(message);
			System.out.println("Produttore inserisce il messaggio!" + message.toString());
		}
	}
}
