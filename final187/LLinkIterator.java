package final187;
import java.util.Iterator;
public class LLinkIterator<T> implements Iterator<T>{
	Node<T> current;
	public LLinkIterator(Node<T> current) {
		this.current = current;
	}
	 
	@Override
	public T next() throws IllegalStateException{
		if(!hasNext())
			throw new IllegalStateException();
		T data = current.data;
		current = current.next;
		return data;
	}
	
	@Override
	public boolean hasNext() {
		return current != null;
	}
	
	@Override
	public void remove() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
	}
}
