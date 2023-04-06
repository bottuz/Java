import java.util.Date;

public class Utente implements Runnable {
	private Machine buffer;
	private int i = 1;

	public Utente(Machine buffer) {
		this.buffer = buffer;
		Thread.currentThread().setName("UTENTE" + i);
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
			message = (Date) buffer.preleva();
		}
	}
}
