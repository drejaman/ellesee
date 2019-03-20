import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import bst.TreeNode;
import linklist.ListNode;

public class DayBefore {
	//====================== ARRAYS =====================================
    //https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
    // 6, 7, 9, 11, 1, 3, 4, 5
    // 0, 1, 2, 3,  4, 5, 6, 7
    //lastnight
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        
        int left = 0, right = nums.length - 1;
        
        while(left < right && nums[left] >= nums[right])//nums[4] < nums[5] so break
        {
        	int mid = (left + right) / 2;//index = 3, 5
        	
        	// if nums[mid] is greater than nums[right] that 
        	// means the whole left side can't contain min
        	if(nums[mid] > nums[right])
        	{
        		left = mid + 1; //4
        	}
        	else
        	{
        		// right = mid is important instead of right = mid - 1 to make sure 
        		// it doesn't skip the mid number
        		right = mid;//5
        	}
        }
        
        return nums[left];//return nums[4]
    }
    
    //https://leetcode.com/problems/search-in-rotated-sorted-array/
    //no duplicates exist
    //lastnight
    public int searchRotated(int[] nums, int left, int right, int x)
    {
    	if(nums == null || nums.length == 0) return -1;
    	
    	if(right < left) return -1;

    	/* Skipping the duplicates when duplicates exist to solve another variation of the same problem
	    //https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
	    //when duplicates do exist
	    //[3,1,1], 3
	    // skip duplicates from the left
	    while (left < right && nums[left] == nums[left + 1]) left++; 
	    
	    // skip duplicates from the right
	    while (left < right && nums[right] == nums[right - 1]) right--; 
    	*/
    	int mid = (left + right) / 2;
    	
    	if(nums[mid] == x) return mid;
    	
    	//case - 1: left is normally ordered
    	if(nums[left] < nums[mid])
    	{
    		//and x exists in the left portion
    		if(x >= nums[left] && x < nums[mid])
    		{
    			return searchRotated(nums, left, mid - 1, x);
    		}
    		else
    		{
    			return searchRotated(nums, mid + 1, right, x);
    		}
    	}
    	//case - 2: right is normally ordered
    	else if(nums[mid] < nums[right])
    	{
    		//and x exists in the right portion
    		if(x > nums[mid] && x <= nums[right])
    		{
    			return searchRotated(nums, mid + 1, right, x);
    		}
    		else
    		{
    			return searchRotated(nums, left, mid - 1, x);
    		}
    	}
    	//case - 3: left/right half is same number
    	else if(nums[left] == nums[mid])
    	{
    		// right half is not same so search in the right half
    		if(nums[mid] != nums[right])
    		{
    			return searchRotated(nums, mid + 1, right, x);
    		}
    		// worst case: need to search in both half
    		else
    		{
    			int result = searchRotated(nums, left, mid - 1, x);
    			
    			if(result == -1)
    			{
    				return searchRotated(nums, mid + 1, right, x);
    			}
    			else
    			{
    				return result;
    			}    			
    		}
    	}
    	
    	return -1;
    }
    
	//====================== TREES =====================================    
    // here is the alternate solution
    //lastnight
    private TreeNode prev = null;
    
    public void flatten(TreeNode root)
    {
    	if(root == null) return;
    	
    	// at first recursively flatten right and left subtrees
    	flatten(root.right);
    	flatten(root.left);
    	
    	// when the right subtree is flattened then the head of right subtree becomes prev 
    	// and tail of left subtree is root
    	// tailLeftST(root).right <- prev(headRightST)
    	root.right = prev;
    	// in the flatten process left is always set to null
    	root.left = null;
    	// required to keep track of linking
    	prev = root;
    }

    //https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
    //Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
    //Output: [1,2,3,4,5,6,7]
    //lastnight
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return constructFromPrePost(pre, 0, pre.length - 1, post, 0, pre.length - 1);
    }
    
    private TreeNode constructFromPrePost(int[] pre, int preStart, int preEnd,
    		int[] post, int postStart, int postEnd) {
        // Base cases.
        if (preStart > preEnd) {
            return null;
        }
        if (preStart == preEnd) {
            return new TreeNode(pre[preStart]);
        }
        
        // Build root.
        TreeNode root = new TreeNode(pre[preStart]);
        
        // Locate left subtree.
        int leftSubRootInPre = preStart + 1; 
        int leftSubRootInPost = findLeftSubRootInPost(pre[leftSubRootInPre], post, postStart, postEnd);
        //trick: important to remember this single line
        //easy way to remember - sum up the calculated ones and then deducts given one (postStart)
        int leftSubEndInPre = leftSubRootInPre + (leftSubRootInPost - postStart);
        
        // Divide.
        root.left = constructFromPrePost(pre, leftSubRootInPre, leftSubEndInPre, 
                                                    post, postStart, leftSubRootInPost);  
        root.right = constructFromPrePost(pre, leftSubEndInPre + 1, preEnd, 
                                                     post, leftSubRootInPost + 1, postEnd - 1);        
        return root;
    }
    
    private int findLeftSubRootInPost(int leftSubRootVal, int[] post, int postStart, int postEnd) {
        for (int i = postStart; i <= postEnd; i++) {
            if (post[i] == leftSubRootVal) {
                return i;
            }
        }
        
        throw new IllegalArgumentException();
    }

    //https://leetcode.com/problems/binary-tree-pruning/description/
    //lastnight
    public TreeNode pruneTree(TreeNode root) {
    	return HasOneInTree(root) ? root : null;
    }

    // HasOneInTree(root) does two things: 
    // it tells us whether the subtree at this node contains a 1 
    // it also prunes all subtrees not containing 1.
    // If for example, node.left does not contain a one, then we should prune it via node.left = null.
    private boolean HasOneInTree(TreeNode root)
    {
    	if(root == null) return false;
    	
    	//the ordering is important
    	boolean leftOne = HasOneInTree(root.left);
    	boolean rightOne = HasOneInTree(root.right);
    	
    	//setting the subtree to null if the subtree doesn't contain a 1
    	if(!leftOne) root.left = null;
    	if(!rightOne) root.right = null;
    	
    	// there is at least one 1 in this subtree rooted at root
    	return root.val == 1 || leftOne || rightOne;
    }
    
    //https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
    //https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
    //lastnight
    public TreeNode LowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
        {
            return null;
        }
        
        //case - 1
        if(root.val == p.val && (this.containsTree(root.left, q) || this.containsTree(root.right, q)))
        {
            return p;
        }
        
        //case - 2
        if(root.val == q.val && (this.containsTree(root.left, p) || this.containsTree(root.right, p)))
        {
            return q;
        }
        
        //case - 3 
        //either left contains p and right contains q 
        //either left contains q and right contains p
        //then root is the LCA
        if(root.left != null && root.right != null)
        {
            if((this.containsTree(root.left, p) && this.containsTree(root.right, q))
            ||(this.containsTree(root.left, q) && this.containsTree(root.right, p)))
            {
                return root;
            }
        }
        
        //case - 4: otherwise check if left contains LCA
        if(root.left != null && this.containsTree(root.left, p) && this.containsTree(root.left, q))
        {
            return LowestCommonAncestor(root.left, p, q);
        }
        
        //case - 5: otherwise check if right contains LCA
        if(root.right != null && this.containsTree(root.right, p) && this.containsTree(root.right, q))
        {
            return LowestCommonAncestor(root.right, p, q);
        }
 
        //case - 6: No LCA
        return null;
    }
    
    private boolean containsTree(TreeNode root, TreeNode seeker)
    {
        if(root == null && seeker == null)
        {
            return true;
        }
        
        if((root == null && seeker != null) || (root != null && seeker == null))
        {
            return false;
        }
        
        if(root.val == seeker.val)
        {
            return true;
        }
        
        return (containsTree(root.left, seeker)||containsTree(root.right, seeker));
    }
    
    //https://leetcode.com/problems/house-robber-iii/
    //idea: level order traversal won't work here
    //do dfs. for each node the max could be 
    //either adding that node [0] or not adding that node [1]
    //lastnight
    public int rob(TreeNode root) {
	    if(root == null) return 0;
	   
	    int[] robVal = dfsRob(root);
	    
	    return Math.max(robVal[0], robVal[1]);
    }
    
    //nodeRobVal[0] = so far robbed value adding this node's value
    //nodeRobVal[1] = so far robbed value without adding this node's value
    private int[] dfsRob(TreeNode node)
    {
    	if(node == null) return new int[2];
	
		int[] left = dfsRob(node.left);
		int[] right = dfsRob(node.right);
		
		int[] nodeRobVal = new int[2];
		
		//max value adding this node's value
		//as we are considering this node's value
		nodeRobVal[0] = node.val + left[1] + right[1];
		
		//as we haven't added this node's value we can add the max of each child's 
		//either including the child's value [0] or without adding child's value [1]
		nodeRobVal[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		
		return nodeRobVal;
    }
    
	//=============================== LINKED LIST =====================================
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
        
        while(marker.next != null)
        {
            marker = marker.next;
            prev = current;
            current = current.next;
        }
        
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

        if(head == null) return null;
        
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

    //https://leetcode.com/problems/reverse-linked-list/
    //lastnight
    public ListNode ReverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode prev = head;
        ListNode current = prev.next;
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

	//=========================== NUMBERS =====================================
    // https://leetcode.com/problems/maximum-swap/
    // find the max digit and min digit before max and swap them
    // input: 98368
    // L[digit] last[9] = 0, last[8] = 4, last[6] = 3, last[3] = 2
    // Last[digit] > i
    // O(N) runtime actually O(kn) as k = 10 constant making it O(N), O(1) space
    //lastnight
    public int maximumSwap(int num) {
    		if(num == 0) return num;
    		
    		char[] numChars = Integer.toString(num).toCharArray();
    		
    		if(numChars.length == 1) return num;
    		
    		int[] last = new int[10];
    		
    		//find the last (lowest value for that digit) index of each digit (1 - 9)
    		for(int i = 0; i < numChars.length; i++)
    		{
    			last[numChars[i] - '0'] = i;
    		}

    		//start with left for the number
    		//first lower value than higher value to be replaced
    		for(int i = 0; i < numChars.length; i++)
    		{
        		//start with the highest digit 9
    			//and go up to the current value at index i
    			for(int digit = 9; digit > numChars[i] - '0'; digit--)
    			{
    				//found a digit higher than current digit but at lower value index
    				//so make the swap bumping up the current number
    				if(last[digit] > i)
    				{
    					char temp = numChars[last[digit]];
    					numChars[last[digit]] = numChars[i];
    					numChars[i] = temp;
    					
    					//we only need one swap so return
    					return Integer.valueOf(new String(numChars));
    				}
    			}
    		}
    		
    		return num;
    }

    //https://leetcode.com/problems/product-of-array-except-self/
    //lastnight
    public int[] productExceptSelf(int[] nums)
    {
        if (nums == null || nums.length <= 1)
        {
            return nums;
        }

        int numLen = nums.length;

        // compute the left product values of i
        int []leftProduct = new int[numLen];

        // compute the right product values of i
        int[] rightProduct = new int[numLen];

        int[] finalProduct = new int[numLen];

        //initialize left array
        leftProduct[0] = 1;

        //calculate left product
        for (int i = 1; i < numLen; i++)
        {
            leftProduct[i] = leftProduct[i - 1] * nums[i-1]; 
        }

        //initialize right array
        rightProduct[numLen - 1] = 1;

        //calculate right product
        for (int i = numLen - 2; i >= 0; i--)
        {
            rightProduct[i] = rightProduct[i + 1] * nums[i + 1];
        }

        //calculate final product
        for (int i = 0; i < numLen; i++)
        { 
            finalProduct[i] = leftProduct[i] * rightProduct[i];
        }

        return finalProduct;
    }

	//=========================== RECURSION =====================================
    //lastnight
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> permutedNumbers = new ArrayList<List<Integer>>();
        
        if(nums == null || nums.length == 0) return permutedNumbers;
        
        // the visit portion is different than the regular permute where all numbers are unique
        // as there might be duplicate we won't know for sure which number is already used 
        // so we need to keep track using visit array
        // we also need to sort the array to keep track of position instead of number 
        // as there are duplicate numbers 
        Arrays.sort(nums);
        permuteVisit(nums, permutedNumbers, new ArrayList<Integer>(), new boolean[nums.length]);
        return permutedNumbers;
    }
    
    //trick: similar to previous to call again to this with a for loop starting at i = 0
    //difference is this one keeps track of index and a visit array to check 
    //if that is already added to current list
    private void permuteVisit(int[] nums, List<List<Integer>> lists, List<Integer> list, boolean[] visit)
    {
		if(nums.length == list.size() && !lists.contains(list))
		{
			lists.add(new ArrayList<Integer>(list));
			return;
		}
		
		for(int i = 0; i < nums.length; i++)
		{
			if(visit[i]) continue;
			//trick: when a number has the same value with its previous, 
			// we can use this number only if previous is used
			if(i > 0 && nums[i-1] == nums[i] && !visit[i-1]) continue;
			
			//trick: no list.contains() check
			list.add(nums[i]);
			visit[i] = true;
 			
			permuteVisit(nums, lists, list, visit);
			
			//backtrack
			visit[i] = false;
			list.remove(list.size() - 1);
		}
    }
    
    //https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    //not exactly backtracking more like recursive
    //lastnight
    private String[] map = 
    		new String[] {" ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    
    public List<String> letterCombinations(String digits) {    		
		List<String> words = new ArrayList<String>();
		
        if(digits == null || digits.isEmpty() || digits.length() == 0)
        {
        		return words;
        }
        
        letterCombinations(digits, 0, words, "");
        
        return words;
    }
    
    private void letterCombinations(String digits, int currentIndex, List<String> words, String word)
    {
		if(currentIndex >= digits.length())
		{
			words.add(new String(word));
			return;
		}
		
		int currentDigit = digits.charAt(currentIndex) - '0';
		
		for(char ch : map[currentDigit].toCharArray())
		{
			letterCombinations(digits, currentIndex + 1, words, word + ch);    				
		}
    }

  //https://leetcode.com/problems/longest-valid-parentheses/
  // O(n) time and O(n) space
  /*
   * we start by pushing a -1 onto the stack.

  For every 
  '(' encountered, we push its index onto the stack.

  ')' encountered, we pop the topmost element and subtract the current element's index from the top element 
  of the stack, which gives the length of the currently encountered valid string of parentheses. 
  If while popping the element, 
  the stack becomes empty, we push the current element's index onto the stack. 
   * 
   * */
  //lastnight
  public int longestValidParentheses(String s) {
      int maxans = 0;
      Stack<Integer> stack = new Stack<Integer>();
      
      stack.push(-1);
      
      for (int i = 0; i < s.length(); i++) {
          if (s.charAt(i) == '(') {
              stack.push(i);
          } else {
          	// this line is KEY
              stack.pop();
              if (stack.empty()) {
                  stack.push(i);
              } else {
              	// this line is KEY            	
                  maxans = Math.max(maxans, i - stack.peek());
              }
          }
      }
      
      return maxans;
  }

  //=========================== QUEUE/STACK/PQ =====================================
  //https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
  // runtime = O(n2)
  public int kthSmallest(int[][] matrix, int k) {
	   if(matrix == null || matrix.length == 0 || matrix.length != matrix[0].length) return 0;
	   
	   // by default PQ contains only the largest elements
	   PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	   
	   // since we need to find k-th smallest element will be ks-th largest element
	   // for a 3*3 matrix 4th smallest element is 3 * 3 - 4 + 1 = 6th largest element 
	   int nMinusKLargest = matrix.length * matrix[0].length  - k + 1;

	   for(int i = 0; i < matrix.length; i++)
		   for(int j = 0; j < matrix[0].length; j++)
		   {
			   pq.offer(matrix[i][j]);
			   
			   // if the pq size becomes more than ks then remove one element to keep only ks-th largest element
			   if(pq.size() > nMinusKLargest) pq.poll();
		   }
	   
	   // return ks-th largest/ k-th smallest element
	   return pq.poll();        
  }
  
  //=========================== STRING =====================================
//https://leetcode.com/problems/reverse-words-in-a-string/
//Input: "the sky is blue",
//Output: "blue is sky the"
//lastnight
public String reverseWords1(String s) {
    if(s == null || s.isEmpty()) return s;
    
    //we start from the end as we are reversing the line
    int j = s.length();
    
    StringBuilder builder = new StringBuilder();
    
    for(int i = s.length() - 1; i >= 0; i--)
    {
		//that means we have finished a word/
		//reached end of the word
		//the word is in between index 
        if(s.charAt(i) == ' '){
            j = i;
        }
        
        //we have reached end of a word
        //or beginning of the sentence
        //say for 'is blue' when i at b then s.charAt(i - 1) == ' '
        else if(i == 0 || s.charAt(i - 1) == ' ')
        {
        	//don't add the space at the beginning of the line
        	//builder is still empty so we don't anything
            if(builder.length() != 0)
            {
                builder.append(' ');
            }
            
            //add the word as is 
            //j is pointing at the end of the line or the space after the word
            //i is pointing at the beginning of the word
            //we add 'blue' to builder
            builder.append(s.substring(i, j));
        }
    }
    
    return builder.toString();
}

//https://leetcode.com/problems/roman-to-integer/
//Input: "MCMXCIV"
//Output: 1994
//Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
//lastnight
public int romanToInt(String s) {
	//trick: look how the hashMap is initialized
	HashMap<Character, Integer> romanMap= new HashMap<Character, Integer>()
	{{
		put('M', 1000); 
		put('D', 500);
		put('C', 100);
		put('L', 50);
		put('X', 10);
		put('V', 5);
		put('I', 1);
	}};
	
	int value = 0;
	int prev = 0, current = 0;
	
	for(char ch : s.toCharArray())
	{
		//MCMXCIV
		//M(1000), C (100), M(1000), X (10), C(100), I(1), V(5)
		current = romanMap.get(ch);
		//trick
		//logic: this is the main logic
		//when the current is greater than prev that means either 9 (IX), 90 (XC), etc
		//corrective measure is current(say X is 10) - 2 * prev (I is 1)
		//as we added the prev already to value in previous step
		//so the right addition is current - 2 * prev to also remove addition of prev
		//1000, 100, 1000 - 2 * 100 = 800, 10,   100 - 2 * 10 = 80, 1, 5 - 2 * 1 			
		value += current > prev ? current - 2 * prev : current;
		//1000, 100, 1000, 10, 100, 1, 5
		prev = current;
	}
	
	return value;	
	}

	//https://leetcode.com/problems/integer-to-roman/
// trick: initialize array, start with the highest value also considering 9's like 900 90 etc.
// start dividing up the number from the highest value values[0]
//lastnight
	public String intToRoman(int num) {
		String[] romanChars = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		
		StringBuilder builder = new StringBuilder();
		
		int i = 0;
		
		//992 -> CMXCII
		while(num > 0)
		{
			int k = num / values[i];//992/1000 = 0, 992/900 = 1, ...., 92/90 = 1, ..., 2/1 = 2  
			num /= values[i];//992%1000 = 992, 992 % 900 = 92, ...., 92%90 = 2, 2%1 = 0
			
			for(int j = 0; j < k ; j++)
			{
				builder.append(romanChars[i]);// , CM, XC, I I
			}
			
			i++;
		}
		
		return builder.toString();
	}   
    
    //cracking1.5
    //lastnight
    public boolean oneEditDistanceAway(String s1, String s2)
    {
    	if(Math.abs(s1.length() - s2.length()) > 1) return false;
    	
    	String shorter = s1.length() < s2.length() ? s1 : s2;
    	String longer = s1.length() < s2.length() ? s2 : s1;
    	
    	boolean foundMismatch = false;
    	
    	int index1 = 0, index2 = 0;
    	
    	while(index1 < shorter.length() && index2 < longer.length())
    	{
    		// the characters are not matched. so it has to be insert or replace
    		if(shorter.charAt(index1) != longer.charAt(index2))
    		{
    			// already one mismatch found before. so another mismatch means not one edit distance away
    			if(foundMismatch) return false;
    			//otherwise mark the first mismatch
    			foundMismatch = true;
    			
    			//if the lengths are same that means it is a replace. so move shorter pointer
    	   		if(shorter.length() == longer.length())
        		{
        			index1++;
        		}
    	   		//otherwise is an insert. so no short pointer is changed
        	}
    		//they are matching so move both pointers are increased
    		else
    		{
    			index1++;
    		}
    		//longer pointer is always increased
    		index2++;
    	}
    	
    	return true;
    }
    
    //https://leetcode.com/problems/longest-substring-without-repeating-characters/
    //Input: "abcdcefg"
    //Output: 5 
    //lastnight
    public int lengthOfLongestSubstring(String s) {
    	boolean exists[] = new boolean[256];
    	
    	int maxLen = 0, i = 0;
    	
    	for(int j = 0; j < s.length(); j++)
    	{
    		while(i < s.length() && exists[s.charAt(j)])//for j = 4 this will become true
    		{
				// keep on incrementing i until we past the first occurrence of the repeated character
				// in the current substring
				// for example in abcdcefg, j = 4 when we get the current max substring abcd
				// the next possible non repeated substring will start from index, i = 3 (d) as then
				// so after i is increased to 2 then exists[c] is reset resulting into i = 3 and this while 
				// loop breaks
    			//then it will reset exists[s.charAt(i)] from i = 0 to 2
    			//like exists[a], exists[b], exists[c] all will reset to false
				exists[s.charAt(i)] = false;
				i++;
    		}
    		
    		// case when the character doesn't already exist in 
    		// current ongoing longest substring consideration
    		exists[s.charAt(j)] = true;//exists[c] will be set to true second time here
    		maxLen = Math.max(j - i + 1, maxLen);
    	}
    	
    	return maxLen;
    }      
}
