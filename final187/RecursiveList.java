package final187;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class RecursiveList<T> implements ListInterface<T> {
	private int size;
	private LLNode<T> head;
	private LLNode<T> tail;
	public RecursiveList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public Iterator<T> iterator() {
		return new RecursiveListIterator<T>(this.head);
	}
	
	public int size() {
		return size;
	}
	
	public ListInterface<T> insertFirst(T elem) throws NullPointerException{
		if(elem == null)
			throw new NullPointerException();
		else if (isEmpty()) {
			head = new LLNode<T>(elem,null,null); 
			tail = head;
		}
		else {
			head.before = new LLNode<T>(elem,null,this.head);		
			head = head.before;
		}
		size++;
		return this;
	}
	
	public ListInterface<T> insertLast(T elem) throws NullPointerException{
		if(elem == null)
			throw new NullPointerException();
		else if(isEmpty()) {
			tail = new LLNode<T>(elem,null,null);
			head = tail;
		} else {
			tail.after = new LLNode<T>(elem,tail,null);
			tail = tail.after;
		}
		size++;
		return this;
	}
	
	public ListInterface<T> insertAt(int index, T elem) throws IndexOutOfBoundsException, NullPointerException{
		if(elem == null)
			throw new NullPointerException();
		else if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		else if(index == 0)
			return insertFirst(elem);
		else if(index == size)
			return insertLast(elem);
		else {
		size++;
		return insertAt(index,elem,0,head);
		}
		
		
	}
	
	private RecursiveList<T> insertAt(int index, T elem, int curr,LLNode<T> temp) {
		if(index == curr) {
			temp.before.after = new LLNode<T>(elem,temp.before,temp);
			temp.before = temp.before.after; 
			return this;
		}
		return insertAt(index, elem, curr + 1, temp.after);
	}
	
	public T removeFirst() throws IllegalStateException{
		if(isEmpty())
			throw new IllegalStateException();
		
		T elem = head.data;
		head = head.after;
		size--;
		if(size == 0) 
			tail = null;
		else 
			head.before = null;
		
		
		return elem;
	}
	
	public T removeLast() throws IllegalStateException{
		if(isEmpty())
			throw new IllegalStateException();
		T elem = tail.data;
		
		tail = tail.before;
		size--;
		if(size == 0)
			head = null;
		else
			tail.after = null;
		return elem;
	}
	
	public T removeAt(int i) throws IndexOutOfBoundsException {
		if(i<0 || i >= size) 
			throw new IndexOutOfBoundsException();
		if(i == 0)
			return removeFirst();
		if( i == size-1)
			return removeLast();
		size--;
		return removeAt(i,0,head);
	}
	private T removeAt(int i,int curr, LLNode<T> temp) {
		if(i == curr) {
			T elem = temp.data;
			temp.before.after = temp.after;
			temp.after.before = temp.before;
			return elem;
		}
		return removeAt(i,curr+1,temp.after) ;
	
		} 
	
	public T getFirst() throws IllegalStateException{
		if(isEmpty())
			throw new IllegalStateException();
		return head.data;
	}
	
	public T getLast() throws IllegalStateException{
		if(isEmpty())
			throw new IllegalStateException();
		return tail.data;
	}
	
	public T get(int i) throws IndexOutOfBoundsException{
		if(i<0 || i>= size)
			throw new IndexOutOfBoundsException();
		return get(i,0,head);
	}
	private T get(int i,int counter,LLNode<T> temp) {
		if(i == counter)
			return temp.data;
		return get(i,counter + 1,temp.after);
	}
	
	public boolean remove(T elem) throws NullPointerException{
		if(elem == null)
			throw new NullPointerException();
		if(indexOf(elem) != -1) {
			this.removeAt(indexOf(elem));
			return true;
		}
		return false;
	}
	
	public int indexOf(T elem) throws NullPointerException{
		if(elem == null)
			throw new NullPointerException();
		
		return indexOf(elem,head,0);
	}
	
	public int indexOf(T elem, LLNode<T> temp,int counter) {
		return (temp == null)? -1 :
			(elem.equals(temp.data))? counter :
		indexOf(elem, temp.after, counter + 1);
	}
	public boolean isEmpty() {
		return size == 0;
	}
	
}
class LLNode<T> {
	public LLNode<T> before;
	public LLNode<T> after;
	public T data;
	public LLNode(T data,LLNode<T> before, LLNode<T> after) {
		this.data = data;
		this.before = before;
		this.after = after;
	}
	public LLNode() {
		before = null;
		after = null;
		data = null;
	}
}

class RecursiveListIterator<T> implements Iterator<T>{
		LLNode<T> head;
		
	public RecursiveListIterator(LLNode<T> head) {	
		this.head = head;
	}
	@Override
	public boolean hasNext() {
		return head != null;
	}
	@Override
	public T next() {
		if(! hasNext())
			throw new NoSuchElementException();
		T data = head.data;
		head = head.after;
		return data;
	}
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
}
