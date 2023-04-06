import java.util.concurrent.TimeUnit;

public class Reader implements Runnable {
	private RWLock db;

	public Reader(RWLock db) {
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
				db.acquireReadLock();
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
				db.releaseReadLock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}