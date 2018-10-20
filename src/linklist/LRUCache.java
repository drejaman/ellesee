package linklist;

import java.util.HashMap;

class LRUCache {

	int lruCapacity;
	HashMap<Integer, DoubleListNode> keyMapper = new HashMap<Integer, DoubleListNode>();
	// most recently used ones are at the front of the list
	// deletion occurs from tail
	DoubleListNode front, tail;
	
    public LRUCache(int capacity) {
    	lruCapacity = capacity;
    }
    
    public int get(int key) {
    	
    	int value = -1;
    	
    	if(keyMapper.containsKey(key))
    	{
    		DoubleListNode currentNode = keyMapper.get(key); 
            value = currentNode.val;
            
            // put the node to front to make sure it gets prioritized
            if(currentNode != front)
            {
                 moveNodeToFront(currentNode);            	
            }
    	}
    	
    	return value;
    }
    
    public void put(int key, int value) {
        if(keyMapper.containsKey(key)) return;
        
        DoubleListNode node = new DoubleListNode(value);
        keyMapper.put(key, node);
        
        if(front == null || tail == null)
        {
        	front = node;
        	tail = node;
        	return;
        }
        
        if(keyMapper.size() >= lruCapacity)
        {
        	keyMapper.remove(key);
        	// now we need to delete tail as it is the least recently used among all existing nodes
        	deleteTailEnd();
        }                
        
        node.next = front;
        front = node;
    }
    
    private void moveNodeToFront(DoubleListNode node)
    {
    	// holds true even for tail
		node.prev.next = node.next;

		// we already checked that node is not front
    	if(node != tail)
    	{
    		node.next.prev = node.prev;
    	}
    	else
    	{
    		// updating tail
    		tail = tail.prev;
    	}

    	// now updating node's pointers and taking that at front
		node.prev = null;
		node.next = front;
		front = node;
    }
    
    private void deleteTailEnd()
    {
    	if(tail == null) return;
    	
    	// update prev nodes back pointer not to point to this tail anymore
    	tail.prev.next = null;
    	tail = tail.prev;
    }
}