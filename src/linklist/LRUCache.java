package linklist;

import java.util.HashMap;

class DoubleNode{
	int key;
	int value;
	DoubleNode prev;
	DoubleNode next;
 
	public DoubleNode(int key, int value){
		this.key = key;
		this.value = value;
	}
}

public class LRUCache {
	
	DoubleNode head = null;
	DoubleNode tail = null;
	int capacity;

	//keeps track of a node based on it's key/value
	HashMap<Integer, DoubleNode> map = new HashMap<Integer, DoubleNode>();
 
	
	public LRUCache(int capacity) {
		this.capacity = capacity;
	}
 
	//every time a node is accessed if it exists it is deleted and 
	//moved to the front of the list to make sure it most recently used
	public int get(int key) {
		//if we already have the key
		if(map.containsKey(key)){
			//get the node by the key
			DoubleNode n = map.get(key);
			//delete the node
			delete(n);
			//now set the node at the head of the list
			setHead(n);
			return n.value;
		}
 
		return -1;
	}
 
	/*This method will delete node*/
	//there are 2 reasons for delete
	//a node is accessed so delete it from it's current position and move it to head
	//the list is over capacity and the last node needs to be deleted
	public void delete(DoubleNode node){
		if(node.prev!=null){
			node.prev.next = node.next;
		}else{
			//we are deleting head. so update head
			head = node.next;
		}
 
		if(node.next!=null){
			node.next.prev = node.prev;
		}else{
			//we are deleting tail
			tail = node.prev;
		}
	}
 
	/*This method will make passed node as head*/
	public void setHead(DoubleNode node){
		node.next = head;
		node.prev = null;
 
		if(head!=null)
			head.prev = node;
 
		head = node;
 
		if(tail ==null)
			tail = head;
	}
 
	public void put(int key, int value) {
		if(map.containsKey(key)){
			//update the old value
			DoubleNode old = map.get(key);
			old.value = value;
			delete(old);
			setHead(old);
		}else{
			DoubleNode newNode = new DoubleNode(key, value);
			
			if(map.size()>=capacity){
				
				map.remove(tail.key);
				// remove last node
				delete(tail);
				setHead(newNode);
 
			}else{
				setHead(newNode);
			}    
 
			map.put(key, newNode);
		}
	}
}