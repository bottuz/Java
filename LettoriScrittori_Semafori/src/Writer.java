import java.util.concurrent.TimeUnit;

public class Writer implements Runnable {
	private RWLock db;

	public Writer(RWLock db) {
		this.db = db;
	}

	public void run() {
		while (true) {
			// nap for awhile
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			try {
				db.acquireWriteLock();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			// you have access to write to the database
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				db.releaseWriteLock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
