package final187;

public class Node<T> {
	public Node<T> pre;
	public Node<T> next;
	public T data;
	public Node() {
		this(null);
	}
	public Node(T data) {
		this(null, null, data);
	}
	public Node(Node<T> pre, Node<T> next, T data) {
		this.pre = pre;
		this.next = next;
		this.data = data;
	}
	@Override
	public String toString() {
			return (this.data == null)? "":"" + this.data;
		}
	}
