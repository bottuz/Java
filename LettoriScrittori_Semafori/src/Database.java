import java.util.concurrent.Semaphore;

///	Priorità ai lettori: Il primo lettore esegue un’operazione di acquire 
///	sul db, impedendo a qualunque scrittore di entrare nella base di 
///	dati

public class Database implements RWLock {
	private int readerCount;
	private Semaphore mutex;
	private Semaphore db;

	public Database() {
		readerCount = 0;
		mutex = new Semaphore(1);
		db = new Semaphore(1);
	}

	@Override
	public void acquireReadLock() throws InterruptedException {
		mutex.acquire();
		++readerCount;
		// se sono il primo dite agli altri che il database viene letto
		if (readerCount == 1)
			db.acquire();
		mutex.release();
	}

	@Override
	public void acquireWriteLock() throws InterruptedException {
		db.acquire();
	}

	@Override
	public void releaseReadLock() throws InterruptedException {
		mutex.acquire();
		--readerCount;
		// se sono l'ultimo lettore dite agli altri che il database non viene più letto
		if (readerCount == 0)
			db.release();
		mutex.release();
	}

	@Override
	public void releaseWriteLock() throws InterruptedException {
		db.release();
	}

}
