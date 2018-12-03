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
	int capacity;
	HashMap<Integer, DoubleNode> map = new HashMap<Integer, DoubleNode>();
	DoubleNode head = null;
	DoubleNode end = null;
 
	public LRUCache(int capacity) {
		this.capacity = capacity;
	}
 
	public int get(int key) {
		if(map.containsKey(key)){
			DoubleNode n = map.get(key);
			delete(n);
			setHead(n);
			return n.value;
		}
 
		return -1;
	}
 
	/*This method will delete node*/
	public void delete(DoubleNode node){
		if(node.prev!=null){
			node.prev.next = node.next;
		}else{
			head = node.next;
		}
 
		if(node.next!=null){
			node.next.prev = node.prev;
		}else{
			end = node.prev;
		}
 
	}
 
	/*This method will make passed node as head*/
	public void setHead(DoubleNode node){
		node.next = head;
		node.prev = null;
 
		if(head!=null)
			head.prev = node;
 
		head = node;
 
		if(end ==null)
			end = head;
	}
 
	public void put(int key, int value) {
		if(map.containsKey(key)){
			// update the old value
			DoubleNode old = map.get(key);
			old.value = value;
			delete(old);
			setHead(old);
		}else{
			DoubleNode newNode = new DoubleNode(key, value);
			if(map.size()>=capacity){
				
				map.remove(end.key);
				// remove last node
				delete(end);
				setHead(newNode);
 
			}else{
				setHead(newNode);
			}    
 
			map.put(key, newNode);
		}
	}
}