import java.util.*;

public class MyLinkedList<E> implements LinkedList<E>, Iterable<E> {
  protected Node<E> head;
  protected Node<E> tail;
  protected int size;
  
  public Iterator<E> iterator()
	{
		return new LinkedListIterator(head);
	}
  
  public MyLinkedList() {
    head = null;
	tail = null;
    size = 0;
  }
  
  public int size() { 
    return size;
  }
  
  public boolean isEmpty() {
    return size == 0;
  }
  
  public void addFirst(E newElement) {
	Node<E> newNode = new Node(newElement,null);
    if(size == 0)
		tail = newNode;
	
	newNode.setNext(head);
	head = newNode;
	size++;
  }
  
  public void addLast(E newElement) {
	Node<E> newNode = new Node(newElement,null);
	
	if(size == 0)
		head = newNode;
	
	if (size != 0)
		tail.setNext(newNode);
	
	tail = newNode;
	size++;
  }
  
  public E removeFirst() {
	Node<E> tempNode = null;
	if (size != 0) {
		if(size == 1)
			tail = null;
			
		tempNode = head;
		head = head.getNext();
		tempNode.setNext(null);
		size--;
	}

	return tempNode.getElement();
	
  }
  
  public E removeLast() {
	Node<E> tempNode = head;
	
	if(size == 0)
		return null;
	
	if(size == 1) {
		head = null;
		tail = null;
		size--;
		return tempNode.getElement();
	}
	
	for(int i=1; i<=size-2; i++) {
		tempNode = tempNode.getNext();
	}
	
	Node<E> tempNode2 = tail;
	tail = tempNode;
	tail.setNext(null);
	size--;
	return tempNode2.getElement();
	
  }
  
  public void remove(E element){
	int index = 0;
	boolean found = false;
	Node<E> temp = head;
	for(int i=1; i<=size; i++) {
		index++;
		if(temp.getElement().equals(element)){
			found = true;
			break;
		}
		temp = temp.getNext(); 
	}
		
	if(found){
		if(index == 1) 
			removeFirst();
		
		else if(index == size)
			removeLast();
		
		else{	
			Node<E> prev = head;
			for(int i=1; i<index-1; i++) {
				prev = prev.getNext(); 
			}
			
			prev.setNext(temp.getNext());
			temp.setNext(null);
			size--;
		}
	}
  }
  
  public int searchList(E searchKey) {
	if(size == 0)
		return -1;
		
	Node tempNode = head;
	for(int i=1; i<=size; i++) {
		if(tempNode.getElement().equals(searchKey))
			return i;
		tempNode = tempNode.getNext();
	}
	
	return -1;
  }
  
  public void printList() {
	Node tempNode = head;
	for(int i=1; i<=size; i++) {
		System.out.print(tempNode.getElement());
		if(i!=size)
			System.out.print(" - ");
		tempNode = tempNode.getNext();
	}
	System.out.println();
	
  }
  
}