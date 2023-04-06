
public class Main {
	public static void main(String[] args) {
		Machine buffer = new CokeMachine();

		Thread utente = new Thread(new Utente(buffer));
		Thread rifornitore = new Thread(new Rifornitore(buffer));
		utente.start();
		rifornitore.start();
	}
}
