package linklist;

import java.util.HashMap;

//lastnight
class DoubleNodeV<T>{
	T key;
	
	DoubleNodeV<T> prev, next;
 
	public DoubleNodeV(T key){
		this.key = key;
	}
}

public class LRUCacheV<T> {
	DoubleNodeV<T> head = null, tail = null;

	int capacity;

	//keeps track of a node based on it's key/value
	HashMap<T, DoubleNodeV<T>> map = new HashMap<T, DoubleNodeV<T>>();
	
	public LRUCacheV(int capacity) {
		this.capacity = capacity;
	}
 
	//every time a node is accessed if it exists it is deleted and 
	//moved to the front of the list to make sure it most recently used
	public DoubleNodeV<T> get(T key) {
		//if we already have the key
		if(map.containsKey(key)){
			//get the node from map by the key
			DoubleNodeV<T> node = map.get(key);
			//delete the node
			delete(node);
			//now set the node at the head of the list
			setHead(node);
			//the map doesn't need to be updated
			return node;
		}
 
		return null;
	}
 
	/*This method will delete node*/
	//it will not be called directly but will be used by put and get
	//there are 2 reasons for delete
	//(1) a node is accessed (get) so delete it from it's current position and move it to head
	//(2) a new node will be added (put) the list is over capacity and the last node needs to be deleted
	public void delete(DoubleNodeV<T> node){
		
		//two things to take care here:
		//1. node's prev
		//2. node's next
		
		if(node.prev != null){
			node.prev.next = node.next;
		} else {
			//we are deleting head. so update head (very unlikely)
			head = node.next;
		}
 
		//either updating the node's next's prev to node's prev
		if(node.next != null){
			node.next.prev = node.prev;
		} else {
			//or we are deleting tail
			//so updating the tail to node's prev
			tail = node.prev;
		}
	}
 
	/*This method will make the passed node as head*/
	public void setHead(DoubleNodeV<T> node){
		node.next = head;
		node.prev = null;
 
		if(head != null)
			head.prev = node;
 
		head = node;
 
		//the case when we just added the first element
		if(tail == null)
			tail = head;
	}
 
	public void put(T key) {
		//the key already exists but his is like accessing it
		//so put the item at the head of the list
		if(map.containsKey(key)){			
			DoubleNodeV<T> old = map.get(key);
			
			//delete the node from end and move it to head
			delete(old);
			setHead(old);
		} else {
			
			DoubleNodeV<T> newNode = new DoubleNodeV<T>(key);
			
			// we only delete a node when the list size exceeds capacity
			// and this only happens when we try to add a new node
			// and find the size already exceeds capacity
			if(map.size() >= capacity){
				
				// always remove the last node
				// delete from map
				map.remove(tail.key);
				// delete from linked list
				delete(tail);
				setHead(newNode);
 
			} else {
				setHead(newNode);
			}    
 
			map.put(key, newNode);
		}
	}
}