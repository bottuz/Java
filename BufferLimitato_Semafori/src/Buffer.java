
public interface Buffer {
	// producer call this method
	public abstract void insert(Object item) throws InterruptedException;

	// consumer call this method
	public abstract Object remove() throws InterruptedException;
}
