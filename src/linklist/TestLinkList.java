package linklist;

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

}
