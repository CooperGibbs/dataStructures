package final187;
import java.util.Iterator;
public class LLink<T> implements Iterable<T>{
		public Node<T> head;
		public Node<T> tail;
		public int size;
		public LLink() {
			head = null;
			tail = null;
			size = 0;
		}
		
		/** 
		 * @return true 
		 * if empty (size == 0) ; false otherwise
		 */
		public boolean isEmpty() {
			return size == 0;
		}
		
		/** add to beginning of link
		 * @throws NullPointerException 
		 * if (@param) data == null
		 * 
		 * @param data
		 */
		public T addFirst(T data) throws NullPointerException{
			if(data == null)
				throw new NullPointerException();
			size++;
			 head = new Node<T>(null, head, data);
			if(size == 1)
				tail = head;
			else
				head.next.pre = head;
			return data;
		}

		/** add to end of link
		 * @throws NullPointerException 
		 * if (@param) data == null
		 * 
		 * @param data
		 */
		public T addLast(T data) throws NullPointerException{
			if(data == null)
				throw new NullPointerException();
			size++;
			tail = new Node<T>(tail, null, data);
			if(size == 1)
				head = tail;
			else
				tail.pre.next = tail;
			return data;
		}
		
		/**returns and removes tail
		 *  @throws IllegalStateException if empty (size == 0)
		 * @return tails data
		 */
		public T removeLast() throws IllegalStateException{
			if(this.isEmpty())
				throw new IllegalStateException();
			T returnData = tail.data;
			if(size-- == 1) {
				head = null;
				tail = null;
			} else {
			  tail.pre.next = null;
			  tail = tail.pre;
			}
			  return returnData;
			  
		}
		
		public T removeFirst() throws IllegalStateException{
			if(isEmpty())
				throw new IllegalStateException();
			T returnData = head.data;
			if(size-- == 1) {
				head = null;
				tail = null;
			} else {
				head.next.pre = null;
				head = head.next;
			}
			return returnData;
				
		}
		
		public T removeAt(int i) throws IndexOutOfBoundsException{
			if(i < 0 || i >= size)
				throw new IndexOutOfBoundsException();
			if(i == 0)
				return removeFirst();
			if(i == size-1)
				return removeLast();
			size--;
			Node<T> pointer = head;
			while(i-- > 1) 
				pointer = pointer.next;
			
			T returnData = pointer.next.data;
			//  **Style**
			(pointer.next = pointer.next.next).pre = pointer;
			/**
			 * 	**Readability**
			 * pointer.next = pointer.next.next;
			 * pointer.next.pre = pointer;
			 */
			return returnData;
			
			
		}
		
		/* adds at given index
		 * * @throws IndexOutOfBoundsException 
		 * 	if index < 0 or index >= size
		 * @throws NullPointerException
		 * if (@param) data == null
		 */
		public T addAt(int index, T data) throws IndexOutOfBoundsException, IndexOutOfBoundsException{
			if(index < 0 || index >= size)
				throw new IndexOutOfBoundsException();
			if(data == null)
				throw new IndexOutOfBoundsException();
			if(index == 0) 
				return addFirst(data);
			if(index == size - 1) 
				return addLast(data);
			size++;
			Node<T> pointer = head;
			int i = 1;
			while(i++ < index) {
				pointer = pointer.next;
			}
			//  *style*
			( pointer.next = new Node<T>(pointer, pointer.next, data) ).next.pre = pointer.next;
		/**  *readability*
		 * pointer.next = new Node<T>(pointer, pointer.next, data);
		 *	pointer.next.next.pre = pointer.next;
		 */
			return data;
		}
		@Override
		public Iterator<T> iterator() {
			return new LLinkIterator<T>(head);
		}
		public String toString(Node<T> node) {
			return (node == null)? "": node.toString() + "\n" + this.toString(node.next);
		}
		
		@Override
		public String toString() {
			return this.toString(head);
		}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LLink<String> list = new LLink<String>();
		list.addFirst("First One in");
		list.addLast("Second one in");
		list.addFirst("Should be first now");
		list.addLast("Back of the back");
		list.addLast("further");
		list.addAt(4, "between back of the back and further");
		for(String x: list)
			System.out.println(x);
		
	}

}