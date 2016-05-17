public class HashEntry<K,V> 
{
  private K key;
  private V value;
  
  public HashEntry(K k,V v) 
  {
    key = k;
	value = v;
  }
  
  public K getKey() 
  { 
	return key; 
  }
  
  public V getValue() 
  { 
	return value; 
  }
  
  public void setKey(K newKey) 
  { 
	key = newKey; 
  }
  
  public void setValue(V newValue) 
  { 
	value = newValue; 
  }
  
  public boolean equals(Object searchEntry){	
	return key.equals(((HashEntry<K,V>)searchEntry).getKey());
  }
  
  public String toString(){
	return key + " | " + value;
  }
  
}