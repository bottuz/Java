
public interface Buffer {
	public abstract void insert(Object item) throws InterruptedException;

	public abstract Object remove() throws InterruptedException;
}
