import java.util.Date;

public class Rifornitore implements Runnable {
	private Machine buffer;
	private int i = 1;

	public Rifornitore(Machine buffer) {
		this.buffer = buffer;
		Thread.currentThread().setName("RIFORNITORE" + i);
		i++;
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
			buffer.rifornisci(message);
		}
	}

}
