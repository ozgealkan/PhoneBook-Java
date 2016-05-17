import java.lang.reflect.Array;

public class MyHashMap<K,V>
{
	private MyLinkedList<HashEntry<K, V>>[] bucket;
	private long size;
	
	private final static int BUCKET_SIZE = 5;

	public MyHashMap()
	{
		size = 0;
		bucket = (MyLinkedList<HashEntry<K, V>>[])Array.newInstance(MyLinkedList.class, BUCKET_SIZE);
		
		for(int i=0; i<BUCKET_SIZE; i++)
			bucket[i] = new MyLinkedList<HashEntry<K,V>>();
	}
	
	public long size() 
	{
		return size;
	}
	
	public boolean isEmpty() 
	{
		return(size() == 0);
	}

	public V get(K k)
	{
		int hash = (k.hashCode() % BUCKET_SIZE);
		LinkedListIterator<HashEntry<K,V>> itr = (LinkedListIterator<HashEntry<K,V>>)bucket[hash].iterator();
		while(itr.hasNext()){
			HashEntry<K,V> he = itr.next();
			if(he.getKey().equals(k))
				return he.getValue();
		}
		return null;
	}

	public V put(K k, V v)
	{
		int hash = (k.hashCode() % BUCKET_SIZE);
		
		LinkedListIterator<HashEntry<K,V>> itr = (LinkedListIterator<HashEntry<K,V>>)bucket[hash].iterator();
		while(itr.hasNext()){
			HashEntry<K,V> he = itr.next();
			if(he.getKey().equals(k)){
				V oldVal = he.getValue();
				he.setValue(v);
				return oldVal;
			}				
		}
		
		bucket[hash].addLast(new HashEntry(k,v));
		size++;
		return null;		
	}   
	
	public V remove(K k)
	{
		int hash = (k.hashCode() % BUCKET_SIZE);
		
		LinkedListIterator<HashEntry<K,V>> itr = (LinkedListIterator<HashEntry<K,V>>)bucket[hash].iterator();
		while(itr.hasNext()){
			HashEntry<K,V> he = itr.next();
			if(he.getKey().equals(k)){
				V oldVal = he.getValue();
				bucket[hash].remove(he);
				size--;
				return oldVal;
			}				
		}		
		return null;		
	}
	
	public void printHashMap()
	{
		for(int i=0; i<BUCKET_SIZE; i++)
		{
			System.out.print("Bucket " + i + "--> ");
			bucket[i].printList();
			System.out.println();
		}
	}

}