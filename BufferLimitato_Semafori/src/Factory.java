
public class Factory {
	public static void main(String[] args) {
		Buffer buffer = new BoundedBuffer();

		// now create the producer and consumer Threads
		Thread producer = new Thread(new Producer(buffer));
		Thread consumer = new Thread(new Consumer(buffer));

		producer.start();
		System.out.println("Thread Produttore partito!");
		consumer.start();
		System.out.println("Thread Consumatore partito!");
	}
}
