public interface LinkedList<E> {

	public void addFirst(E newElement);
	
	public void addLast(E newElement);
	
	public E removeFirst();
	
	public E removeLast();
	
	public int searchList(E searchKey);
	
	public void printList();

	public int size();

	public boolean isEmpty();
 
}