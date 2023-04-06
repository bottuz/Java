
public interface RWLock {
	public abstract void acquireReadLock() throws InterruptedException;

	public abstract void acquireWriteLock() throws InterruptedException;

	public abstract void releaseReadLock() throws InterruptedException;

	public abstract void releaseWriteLock() throws InterruptedException;

}
