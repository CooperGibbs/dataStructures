package final187;

public class Queue<T> {
	public LLink<T> queue;
	public Queue() {
		queue = new LLink<T>();
	}
	public Queue(T data) {
		( queue = new LLink<T>() ).addFirst(data);
	}
	
	public T enqueue(T data) {
		return queue.addFirst(data);
	}
	public T dequeue() {
		return queue.removeLast();
	}
	// inspired by peek but rhymes with enqueue && dequeue
	// spin-off of 'preview'
	public T prevueue() {
		return this.enqueue(dequeue());
	}
	@Override
	public String toString() {
		return queue.toString();
	}
}
