package linklist;

import java.util.Stack;

public class TestLinkList {

	public TestLinkList()
	{
	}

	public void test()
	{
		//printLinkedList(createLinkedListFromArray(new int[] {1,4,3,2,5,2}));
		//printLinkedList(partition(createLinkedListFromArray(new int[] {1,4,3,2,5,2}), 3));		
	}
	
	public ListNode createLinkedListFromArray(int[] array)
	{
		if(array == null || array.length == 0) return null;
		
		ListNode head = new ListNode(array[0]);
		
		ListNode current = head;
		
		for(int i = 1; i < array.length; i++)
		{
			current.next = new ListNode(array[i]);
			current = current.next;
		}
		
		return head;
	}
	
	public void printLinkedList(ListNode head)
	{
		if(head == null) return;
		
		ListNode current = head;
		
		while(current != null)
		{
			System.out.print(current.val + ", ");
			current = current.next;
		}

		System.out.println();
	}
	
	// https://leetcode.com/problems/partition-list/description/
	//Cracking2.4
    public ListNode partition(ListNode head, int x) {
        if(head == null) return null;
        
        // if equal to x then it will go to next list
        ListNode beforeXhead = null, afterXhead = null, 
        		 beforeXcurrent = null, afterXcurrent = null; 
        
	    	ListNode current = head;
	    	
	    	while(current != null)
	    	{
	    		if(current.val < x)
	    		{
	        		if(beforeXhead == null)
	        		{
	        			beforeXhead = current;
	        			beforeXcurrent = current;
	        		}
	        		else
	        		{
	        			beforeXcurrent.next = current;
	        			beforeXcurrent = beforeXcurrent.next;
	        		}
	    		}
	    		else
	    		{
	        		if(afterXhead == null)
	        		{
	        			afterXhead = current;
	        			afterXcurrent = current;
	        		}
	        		else
	        		{
	        			afterXcurrent.next = current;
	        			afterXcurrent = afterXcurrent.next;
	        		}    			
	    		}
	    		
	    		current = current.next;
	    	}
	    	
	    	// this step is crucial to ensure we don't fall into a loop
	    	if(afterXcurrent != null) afterXcurrent.next = null;
	    	
	    	if(beforeXhead == null) return afterXhead;
	    	
	    	beforeXcurrent.next = afterXhead;
	        return beforeXhead;
    }
    
    //https://leetcode.com/problems/add-two-numbers/
    //Cracking2.5
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    		return addTwoNumbers(l1, l2, 0); 
	}

    public ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {

    		if(l1 == null && l2 == null)
	    	{
	    		if(carry == 0) return null;
	    		else return new ListNode(carry);
	    	}
    	
		int value = carry;
		
		if(l1 != null) value += l1.val;
		if(l2 != null) value += l2.val;
		
		ListNode node = new ListNode(value % 10);
		node.next = addTwoNumbers(l1 != null ? l1.next: null, l2 != null ? l2.next: null, value/10);
		
		return node;
    }
    
    //https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode MergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null)
        {
            return null;
        }
        
        if(l1 == null)
        {
            return l2;
        }
        
        if(l2 == null)
        {
            return l1;
        }
        
        if(l1 != null && l2 != null)
        {
	        	//trick: the smaller one's next will point to
	        	//method call of smaller's next and the other list
	        	//then we return the smaller one        	
            if(l1.val <= l2.val)
            {
                l1.next = MergeTwoLists(l1.next, l2);
                return l1;
            }
            
            //don't need this if check
            if(l1.val > l2.val)
            {
                l2.next = MergeTwoLists(l1, l2.next);
                return l2;
            }
        }
        
        return null;
    }
    
    //https://leetcode.com/problems/remove-duplicates-from-sorted-list/
    //Cracking2.1 - No buffer (HashMap) allowed. So solved using 2 pointers and requires O(N2) time
    //two loops and two pointers
    //lastnight
    public void deleteDups(ListNode head)
    {
	    	if(head == null) return;
	    	
	    	ListNode current = head;
	    	
	    	//O(N) outer loop contributing to total of O(N2)
	    	while(current != null)
	    	{
	    		ListNode runner = current;
	    		
	    		// O(N) time
	    		// we have to have the runner.next check as we will actually 
	    		// access the value of runner.next
	    		while(runner.next != null)
	    		{
	    			//trick
	    			//when the value match and we update then it could be the case that
	    			//runner.next.next was already null (last node). so we don't do
	    			// runner = runner.next when we find the match and update only runner.next
	    			if(runner.next.val == current.val)
	    			{
	    				runner.next = runner.next.next;
	    			}
	    			else
	    			{
	    				runner = runner.next;
	    			}
	    		}
	    		
	    		current = current.next;
	    	}
    }
    
    //Cracking 2.2
    public ListNode kthToLast(ListNode head, int k)
    {
	    	if(head == null) return null;
	    	
	    	//p2 will eventually point to k-th from last Node
	    	//when the advanced node, p1 
	    	ListNode p1 = head, p2 = head;
	    	
	    	for(int i = 0; i < k; i++)
	    	{
	    		p1 = p1.next;
	    		
	        	// we even don't have k nodes
	    		if(p1 == null) return null;
	    	}
	
	    	// as p1 is already advanced k places p2 will have k more items left 
	    	// when p1 will reach at the end of the list
	    	while(p1 != null)
	    	{
	    		p1 = p1.next;
	    		p2 = p2.next;
	    	}
	    	
	    	return p2;
    }
    
    //https://leetcode.com/problems/palindrome-linked-list/
    //Cracking2.6
    public boolean isPalindrome(ListNode head)
    {
	    	ListNode slow = head, fast = head;
	    	
	    	Stack<Integer> listStack = new Stack<Integer>();
	    	
	    	// this condition is important as we break either when we reach at the end (even case)
	    	// or we are one node away from the end (odd case)
	    	// also for fast we are moving 2 nodes at a time. so we don't get null pointer exception
	    	// for fast.next.next
	    	while(fast != null && fast.next != null)
	    	{
	    		listStack.push(slow.val);
	    		slow = slow.next;
	    		fast = fast.next.next;
	    	}
	    	
	    	//trick:
	    	//that means list has odd elements and skip that one middle element
	    	if(fast != null) slow = slow.next;
	    	
	    	// now the validation part    	
	    	while(slow != null)
	    	{
	    		if(slow.val != listStack.pop().intValue())
	    		{
	    			return false;
	    		}
	    		
	    		slow = slow.next;
	    	}
	    	
	    	return true;
    }
    
    //https://leetcode.com/problems/swap-nodes-in-pairs/
    //lastnight
    public ListNode swapPairs(ListNode head) {
        if(head.next == null) return head;

        //we need to keep track of head of the list
        //which will be original head.next pointed by start
        ListNode start = head.next;
        ListNode first = head, second = head.next;
        
        //first->second->third->fourth
        while(first != null && second != null)
        {
        	//trick
            ListNode temp = second.next;//third
            first.next = temp;//first->temp (third)
            second.next = first;//second->first->temp(third)
            
            first = temp;//first = third
            if(temp != null) second = temp.next;//second = fourth
        }
        
        return start;
    }
    
    //https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    //lastnight
    public ListNode RemoveNthFromEnd(ListNode head, int n) {
        if(head.next == null && n == 1) return null;
        
        int i = 0;
        
        ListNode marker = head;
        
        while(marker.next != null && i < n)
        {
            marker = marker.next;
            i++;
        }
        
        if(i == (n-1)) return head.next;
        
        ListNode prev = head;
        ListNode current = head.next;
        
        //as marker already moved n places
        //when marker reaches at the end of list
        //at that time current will point to n-th from end
        while(marker.next != null)
        {
            marker = marker.next;
            prev = current;
            current = current.next;
        }
        
        //we remove the n-th from end element
        prev.next = current.next;
        
        return head;
    }
    
    //https://leetcode.com/problems/remove-linked-list-elements/
    //lastnight
    public ListNode RemoveElements(ListNode head, int val) {
        if(head == null) return null;
        
        //handles the case where the head contains the val to be removed
        // head !=n null covers the case when the whole list contains the same element that is being deleted like
        // [4,4,4,4,4,4], 4
        while(head != null && head.val == val) head = head.next;

        if(head == null || head.next == null) return head;
        
        //at this point we know head doesn't contain an element
        //whose value is same as val
        ListNode prev = head;
        ListNode current = head.next;
        
        //trick:
        while(current!= null)
        {
            //only the deletion case
        		//keep on skipping the elements whose value equals val
            while(current != null && current.val == val)
            {
                current = current.next;
            }
            
            //covers deletion and non deletion
            //sets prev's next to a node whose va
            prev.next = current;
            prev = current;
            
            // special case when the last element is deleted
            if(current != null)
                current = current.next;
        }
        
        return head;
    }
    
    //https://leetcode.com/problems/delete-node-in-a-linked-list/
    public void deleteNode(ListNode node) {
        if(node == null) return;

        //2 cases
        //case - 1: this is the last node
        if(node.next == null)
        {
            node = null;
        }
        // case - 2: this is in the middle of the list
        else
        {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    //https://leetcode.com/problems/linked-list-cycle/
    public boolean hasCycle(ListNode head) {
        ListNode current = head;
        ListNode currentDouble = head;
        
        while(currentDouble!=null && currentDouble.next!=null)
        {
            current = current.next;
            currentDouble = currentDouble.next.next;
            
            if(currentDouble != null && current.val == currentDouble.val) 
                return true;
        }
        
        return false;
    }

    //https://leetcode.com/problems/reverse-linked-list/
    //lastnight
    public ListNode ReverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode prev = head;
        ListNode current = prev.next;
        //trick: make sure prev.next is set to null
        //otherwise it will create a cycle
        prev.next = null;
                
        while(current != null)
        {
            ListNode temp = current.next;
            current.next = prev;
            
            prev = current;
            current = temp;
        }
        
        //trick: it can get confusing what to return current/prev
        //so keep in mind that you need to return prev
        return prev;
    }

    //Cracking2.7 TODO
    //Cracking2.8 TODO

    //https://leetcode.com/problems/reverse-nodes-in-k-group/
    /*
     * Example:
		Given this linked list: 1->2->3->4->5
		For k = 2, you should return: 2->1->4->3->5
		For k = 3, you should return: 3->2->1->4->5
     * 
     * */
    
    //TODO
//    public ListNode reverseKGroup(ListNode head, int k) {
//        if(head == null || head.next == null) return head; 
//    }

    //https://leetcode.com/problems/rotate-list/
    //TODO
//    public ListNode rotateRight(ListNode head, int k) {
//        if(head == null || head.next == null) return head;         
//    }
}
