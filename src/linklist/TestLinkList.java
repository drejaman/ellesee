package linklist;

import java.util.Stack;

public class TestLinkList {

	public TestLinkList()
	{
	}

	public void test()
	{
//		printLinkedList(createLinkedListFromArray(new int[] {1,4,3,2,5,2}));
		printLinkedList(partition(createLinkedListFromArray(new int[] {1,4,3,2,5,2}), 3));		
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
        ListNode beforeX = null, afterX = null, 
        		beforeXcurrent = null, afterXcurrent = null; 
        
    	ListNode current = head;
    	
    	while(current != null)
    	{
    		if(current.val < x)
    		{
        		if(beforeX == null)
        		{
        			beforeX = current;
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
        		if(afterX == null)
        		{
        			afterX = current;
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
    	
    	// this step is crucial to ensure we don'e fall into a loop
    	if(afterXcurrent != null) afterXcurrent.next = null;
    	
    	if(beforeX == null) return afterX;
    	
    	beforeXcurrent.next = afterX;
        return beforeX;
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
    
    //Cracking2.1 - No buffer (HashMap) allowed. So solved using 2 pointers and requires O(N2) time
    public void deleteDups(ListNode head)
    {
    	if(head == null) return;
    	
    	ListNode current = head;
    	
    	//O(N) outer loop contributing to total of O(N2)
    	while(current != null)
    	{
    		ListNode runner = current;
    		
    		// O(N) time
    		// we have to have the runner.next check as we will actully 
    		// access the value of runner.next
    		while(runner.next != null)
    		{
    			//when the value match and we update then it could be the case that
    			//runner.next.next was already null (last node). so we don't do
    			// runner = runner.next when we find the match and update runner.next
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
    public ListNode nthToLast(ListNode head, int k)
    {
    	if(head == null) return null;
    	
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
    	
    	// that means list has odd elements and skip that one
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
    
    //Cracking2.7 TODO
    //Cracking2.8 TODO

    //TODO
    //https://leetcode.com/problems/reverse-nodes-in-k-group/
    /*
     * Example:
		Given this linked list: 1->2->3->4->5
		For k = 2, you should return: 2->1->4->3->5
		For k = 3, you should return: 3->2->1->4->5
     * 
     * */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null) return head; 
    }

    //https://leetcode.com/problems/rotate-list/
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;         
    }
}
