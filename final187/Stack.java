package final187;

public class Stack<T> {
	public LLink<T> stack;
	public Stack() {
		stack = new LLink<T>();
	}
	public Stack(T data) {
		( stack = new LLink<T>() ).addLast(data);
	}
	
	public T push(T data) {
		return stack.addLast(data);
	}
	
	public T peek() {
		// not good in terms of memory but efficient on programmers end (time)
	 return this.push(pop());
	}
	
	public T pop() {
		return stack.removeLast();
	}
	@Override
	public String toString() {
		return stack.toString();
	}
}
