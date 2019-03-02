package bst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;

import linklist.ListNode;

public class TestTrees {

	public TestTrees()
	{
		
	}
	
	public void test()
	{
		testTree();
		//testBinaryTree();		
	}
	
	public static void testTree()
	{
//		MyTreeNode root=new MyTreeNode(1,"A");
//		root.setParentId(0);
//		Tree tree=new Tree(root);
//		MyTreeNode child1 = new MyTreeNode(2,"B");
//		MyTreeNode child2 = new MyTreeNode(3,"C");
//		MyTreeNode child3 = new MyTreeNode(4,"D");
//		MyTreeNode child4 = new MyTreeNode(5,"E");
//		tree.addNode(root, child1);
//		tree.addNode(root, child2);
//		tree.addNode(root, child3);
//		tree.addNode(root, child4);
//		tree.preOrderPrint(root);
		//tree.postOrderPrint(root);
//		TreeNode root = constructMaximumBinaryTree(new int[] {3,2,1,6,0,5});
		isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
	}
	
	public static void testBinaryTree()
	{
	}
 
    //CAT: CONSTRUCT, NODE POSITIONS
    //------------------------------
	//https://leetcode.com/problems/validate-binary-search-tree/
    public boolean isValidBST(TreeNode root) {
        return valid(root, null, null);
    }
    
	private boolean valid(TreeNode p, Integer low, Integer high) 
	{    if (p == null) return true;    
	 return (low == null || p.val > low) 
	     && (high == null || p.val < high)          
	     && valid(p.left, low, p.val)          
	     && valid(p.right, p.val, high); 
	} 
    //https://leetcode.com/problems/maximum-binary-tree/description/
    /*
     * Approach: 
     * Step - 1: find the max element of the array and make it the root
     * Step - 2: root.Left <- left array of the max number following the same process
     * Step - 3: root.Right <- right array of the max number following the same process
     */
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums == null || nums.length == 0) return null;
        
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }
    
    // Subroutine to construct a binary tree from an array given a range
    private static TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        if(nums == null || nums.length == 0) return null;
        
        TreeNode root = null;
        
        int maxIndex = FindMaxIndex(nums, start, end);
        
        if( maxIndex >= 0)
        {
        	root = new TreeNode(nums[maxIndex]);
            if(maxIndex >= start) root.left = constructMaximumBinaryTree(nums, start, maxIndex - 1);
        	if(maxIndex <= end) root.right = constructMaximumBinaryTree(nums, maxIndex + 1, end); 
        }
        
        return root;
    }
    
    /*
     * Finds the index of the Maximum number given an array and a range in the array
     * 
     */
    private static int FindMaxIndex(int[] nums, int start, int end)
    {
    	if(nums == null || nums.length == 0) return -1;
    	
    	int max = Integer.MIN_VALUE;
    	int maxPosition = -1;
    	
    	for(int i = start; i <= end; i++)
    	{
    		if(nums[i] > max) 
    			{
    				maxPosition = i;
    				max = nums[i];
    			}
    	}
    	
    	return maxPosition;
    }
    
    // https://leetcode.com/problems/insert-into-a-binary-search-tree/
    public TreeNode insertIntoBST(TreeNode root, int val) {
    	// this line is the key
        if(root == null)
            root = new TreeNode(val);
        else if(val < root.val)
            root.left = insertIntoBST(root.left, val);
        else
            root.right = insertIntoBST(root.right, val);
        return root;
    }
     
    // https://leetcode.com/problems/search-in-a-binary-search-tree/
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) return null;
        
        if(val == root.val) return root;
        else if(val > root.val) return searchBST(root.right, val);
        
        return searchBST(root.left, val);        
    }
    
    //https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
    // this is Bottom up approach
    private ListNode list;
    
    public TreeNode sortedListToBST(ListNode head) {
        
    	ListNode p = head;
        int n = 0;
        
        // determine the length of the list
        while( p != null)
        {
        	p = p.next;
        	n++;
        }
        
        // set the head to begin with. this is KEY
        list = head;
        
        return sortedListToBST(0, n-1);
    }

    //1, 3, 5, 6, 7, 9
    //list initially points to head which is 1
    //sortedlist(1, 3, 5, 6, 7, 9) -> left = sortedlist(1, 3), root = 5, right = sortedList(6, 7, 9)
    //sortedlist(1, 3) -> mid = 0, left = null, root = 1 (list), right = sortedlist (3) now list is pointing to 3
    //sortedList(6, 7, 9) -> mid = index 4, left = sortedlist (6) list is pointing to 6, root = 7, right = sortedlist(9) 
    //		 5
    //	   /   \		
    //    1	    7	
    //	   \	/\
    //	    3  6  8
    private TreeNode sortedListToBST(int start, int end)
    {
        if(start > end) return null;
        
        int mid = (start + end) /2 ;
        
        TreeNode leftChild = sortedListToBST(start, mid - 1);
        TreeNode root = new TreeNode(list.val);
        list = list.next;
        root.left = leftChild;
        root.right = sortedListToBST(mid + 1, end);
        
        return root;
    }

    // https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
    // this solution works perfectly fine except for huge input the memory limit exceeds
    // runtime O(N) - number of nodes in the tree
    // goal: all the nodes are in the right side. that means only the right children has value  
    // logic: 
    // if right child is empty then node.right = node.left
    // if right child is not empty then push the right child in the stack, 
    // then node.right = node.left and then recursively call the method with node.left
    // once it get backs to the same node then the returningNode.right = flatten(pop())
    static Stack<TreeNode> treeStack = new Stack<TreeNode>();
    
    public static TreeNode flattenTree(TreeNode root) {
    	if(root == null) return null;
    	
    	if(root.left == null && root.right != null) flattenTree(root.right);
    	else if(root.right != null) treeStack.push(root.right);
    	
    	if(root.left != null) 
    	{
    		//root.right is already pushed to stack. so assign left to right
        	root.right = root.left;
        	root.left = null;
    	}
    	
    	//after reassignment root.right shouldn't be null if root is not leaf
    	if(root.right != null) flattenTree(root.right);
    	
    	//after flattenTree is done on right then we pop the element from stack
    	//and flatten the popped element
    	if(!treeStack.isEmpty())
    	{
    		root.right = flattenTree(treeStack.pop());        		
    	}
    	
		return root;
    }
    
    // here is the alternate solution
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
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return constructFromPrePost(pre, 0, pre.length - 1, post, 0, pre.length - 1);
    }
    
    private TreeNode constructFromPrePost(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd) {
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

    
    //CAT: TWO TREES
    //--------------
	// https://leetcode.com/problems/merge-two-binary-trees/description/
	// Runtime O(n + m) = O(n) considering n > m. Best possible time as we have to traverse 
	// all the nodes of each tree
	// Variation: If we have a list of trees that needs merging? Take 2 trees at a time and merge them 
	// and take the next tree with the merged tree 
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		// Terminating condition
    	if(t1 == null && t2 == null) return null;
    	
    	TreeNode mergedRoot = new TreeNode((t1 != null ? t1.val : 0) + (t2 != null ? t2.val : 0));
    	mergedRoot.left = mergeTrees(t1 != null ? t1.left : null, t2 != null ? t2.left : null);
    	mergedRoot.right = mergeTrees(t1 != null ? t1.right : null, t2 != null ? t2.right : null);
	     
	    return mergedRoot;
    }

    //Cracking4.10
    public boolean containsTree(TreeNode t1, TreeNode t2)
    {
    	// null tree is always a subTree
    	if(t2 == null) return true;

    	return isSubtree(t1, t2);
    }

    //https://leetcode.com/problems/subtree-of-another-tree/
    public boolean isSubtree(TreeNode s, TreeNode t) {
    	// the big tree is null and we still haven't found the match
    	if(s == null) return false;
    	
    	//the values matched now try matching the whole tree
    	if(s != null && t != null && s.val == t.val && matchTree(s, t))
		{
			return true;
		}

    	// ended up the subtree not matching in full
    	// otherwise continue the search on big tree's left and right
        boolean inLeft = false, inRight = false;
        
        if(s.left != null) inLeft = isSubtree(s.left, t);
        if(s.right != null) inRight = isSubtree(s.right, t);
    	return (inLeft || inRight);
    }
        
    private boolean matchTree(TreeNode t1, TreeNode t2)
    {
    	//both are null so they match
    	if(t1 == null && t2 == null) return true;
    	//one of then null so no match
    	else if(t1 == null || t2 == null) return false;
    	//value doesn't match. don't need this check
    	else if(t1 != null && t2 != null && t1.val != t2.val) return false;
    	//t1 and t2's values match so check their left and right subtree
    	else return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right);
    }
    
    //https://leetcode.com/problems/most-frequent-subtree-sum/
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    
    public int[] findFrequentTreeSum(TreeNode root) {
    	int[] result = new int[map.size()];
    	
    	findSubtreeSum(root);
    	
    	for(Entry<Integer, Integer> entry : map.entrySet())
    	{
    		entry.getKey();
    	}
    	
    	return result;
    }
    
    public int findSubtreeSum(TreeNode node)
    {
    	if(node == null) return 0;
    	
    	int sum = node.val;
    	
    	sum += findSubtreeSum(node.left);
    	sum += findSubtreeSum(node.right);
    	
    	//increase the frequency of the sum by 1
    	map.put(sum, map.getOrDefault(sum, 0) + 1);
    	
    	return sum;
    }

    //CAT: Serialization
    //------------------
    // https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/description/
    // diff = outer edges - inner edges
    // for root = 2 - 0 = 2, for internal node = 2 - 1
    // so for every non-leaf node we visit we add 2 to count and reduce 1 for the incoming one
    // at the end if the count is zero then the serialization is right
    public static boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        int edgeCount = 1;
        for (String node: nodes) {
            if (--edgeCount < 0) return false;
            if (!node.equals("#")) edgeCount += 2;
        }
        return edgeCount == 0;
    }
    
    //https://leetcode.com/problems/find-duplicate-subtrees/
    private HashMap<String, Integer> subtreeCount;
    private List<TreeNode> answer;
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
    	subtreeCount = new HashMap<String, Integer>();
    	answer = new ArrayList<TreeNode>();
    	this.serializeToFindDuplicate(root);
    	return answer;
    }
    
    //		5
    //	  /	  \
    //	 4	   4
    //  /  \  /  \
    //	1  2 1	  2 
    private String serializeToFindDuplicate(TreeNode node)
    {
    	if(node == null) return "#";
    	
    	//this contains the serialization of the subtree rooted at node 
    	String currentSerial = node.val + "," + serializeToFindDuplicate(node.left) + "," + serializeToFindDuplicate(node.right);
    	
    	//for the above example when it traverse left 4 it will create serialization like 4,1,#,#,2,#,#
    	//and put it into subtreeCount hashmap
    	subtreeCount.put(currentSerial, subtreeCount.getOrDefault(currentSerial, 0) + 1);
    	
    	//then later when it traverses right 4 it will create the same serialization 
    	//then this will become true and the duplicate node will be added to answer
    	if(subtreeCount.get(currentSerial) == 2)
    	{
    		answer.add(node);
    	}
    	
    	return currentSerial;
    }
    
    
    //CAT: LEVEL ORDER
    //------------------

    //https://leetcode.com/problems/binary-tree-level-order-traversal/
    //most important
    //Given binary tree [3,9,20,null,null,15,7],
    //[
    //[3],
    //[9,20],
    //[15,7]
    //]
    //trick: 
    //to traverse levels need to keep two lists. one for the previous and the other
    //is current to put the children of each node of previous
    //after all the children of previous is added to current list
    //the current list is added to list of list that will be returned as result
    //and then current becomes previous
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> treeValues = new ArrayList<List<Integer>>();
        if(root == null) return treeValues;
        
        List<TreeNode> previous = new ArrayList<TreeNode>();
        previous.add(root);
        
        while(!previous.isEmpty())
        {
        	List<TreeNode> current = new ArrayList<TreeNode>();
        	List<Integer> valueList = new ArrayList<Integer>();
        	
        	for(TreeNode node : previous)
        	{
        		if(node.left != null)
        		{
        			current.add(node.left);
        		}
        		
        		if(node.right != null)
        		{
        			current.add(node.right);
        		}

        		valueList.add(node.val);
        	}
        	
        	treeValues.add(valueList);
        	previous = current;
        }
        
        return treeValues;
    }
    
    //https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
    //Given binary tree [3,9,20,null,null,15,7],
    //[
    // [3],
    // [20,9],
    // [15,7]
    //]
    // idea: this will be a variation of levelOrder traversal
    // trick: 
    // get the size of list of list 
    // if the (size + 1) % 2 == 0 then the current list insertion is left to right
    // otherwise insertion is right to left
    // we might need to insert into treeNode list in regular order but 
    // alternate times we read from it reverse way
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> treeValues = new ArrayList<List<Integer>>();

        if(root == null) return treeValues;
        
        List<TreeNode> previous = new ArrayList<TreeNode>();
        previous.add(root);
        
        while(!previous.isEmpty())
        {
        	List<TreeNode> current = new ArrayList<TreeNode>();
        	List<Integer> valueList = new ArrayList<Integer>();
        	
        	for(TreeNode node : previous)
        	{
        		if((treeValues.size() + 1) % 2 == 0)
        		{
            		if(node.left != null)
            		{
            			current.add(node.left);
            		}

            		if(node.right != null)
            		{
            			current.add(node.right);
            		}
        		}
        		else
        		{
            		if(node.right != null)
            		{
            			current.add(node.right);
            		}

            		if(node.left != null)
            		{
            			current.add(node.left);
            		}
        		}
        		
        		valueList.add(node.val);
        	}
        	
        	treeValues.add(valueList);
        	previous = current;
        }
        
        return treeValues;
    }

    //https://leetcode.com/problems/binary-tree-right-side-view/
    //Input: [1,2,3,null,5,null,4]
    //Output: [1, 3, 4]
    //idea: do level order traversal and get the last node of east level
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightValues = new ArrayList<Integer>();

        if(root == null) return rightValues;

        List<List<Integer>> nodeValues = levelOrder(root);
        
        for(List<Integer> current : nodeValues)
        {
        	rightValues.add(current.get(current.size() - 1));
        }
        
        return rightValues;
    }
    
    //https://leetcode.com/problems/find-largest-value-in-each-tree-row/
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> largeValues = new ArrayList<Integer>();

        if(root == null) return largeValues;

        List<List<Integer>> nodeValues = levelOrder(root);
        
        for(List<Integer> current : nodeValues)
        {
	        	//careful about this Collections.max
	        	largeValues.add(Collections.max(current));        	
        }
        
        return largeValues;
    }
    

    //CAT: NODE/TREE VALUES
    //---------------------
    //https://leetcode.com/problems/binary-tree-tilt/
    private int tilt = 0;
    
    public int findTilt(TreeNode root) {
        if(root == null) return 0;
        
        navigate(root);
        
        return tilt;
    }
    
    // navigate returns the total value in this node
    // node.val + nav(node.left) + nav(node.right)
    private int navigate(TreeNode root)
    {
    	if(root == null) return 0;
    	
    	int left = navigate(root.left);
    	int right = navigate(root.right);
    	
    	//on a node, tilt is the difference 
    	//between left and right subtree
    	tilt += Math.abs(left-right);
    	
    	//in a node the sum is node's value and left, right subtree
    	return root.val + left + right;
    }
    
    //Cracking4.12 pathswithsum
    //we need 2 methods:
    //method - 1:continue tracking currentSum and from topDown and return totalWays when the targetSum found
    //countPathsWithSumFromNode
    //method - 2: at each node call method-1 for root, call method-2 for both left and right
    public int countPathsWithSum(TreeNode node, int targetSum)
    {
    	if(node == null) return 0;
    	
    	int pathsFromRoot = countPathsWithSumFromNode(node, targetSum, 0);
    	
    	int pathsFromLeft = countPathsWithSum(node.left, targetSum);
    	int pathsFromRight = countPathsWithSum(node.left, targetSum);
    	
    	return pathsFromRoot + pathsFromLeft + pathsFromRight;
    }
    
    private int countPathsWithSumFromNode(TreeNode node, int targetSum, int currentSum)
    {
    	if(node == null) return 0;
    	
    	currentSum += node.val;
    	
    	int totalWays = 0;
    	
    	//can't we return totalWays here?
    	if(currentSum == targetSum) totalWays++;
    	
    	//check in both left and right subtree after adding current node's value to currentsum
    	totalWays += countPathsWithSumFromNode(node.left, targetSum, currentSum);
    	totalWays += countPathsWithSumFromNode(node.right, targetSum, currentSum);
    	
    	return totalWays;
    }
    
    //https://leetcode.com/problems/kth-smallest-element-in-a-bst/
    int currentCount = 0;
    int smallest = Integer.MAX_VALUE;
    
    public int kthSmallest(TreeNode root, int k) {
        kthSmallestTraversal(root, k);
        return smallest;
    }

    // we are doing an inorder traversal as that prints tree 
    // in the smallest to largest order
    // so we keep a global counter and stop when reach the value k
    private void kthSmallestTraversal(TreeNode root, int k)
    {
    	if(root == null) return;
    	
    	kthSmallestTraversal(root.left, k);  

        currentCount++;
    	if(currentCount == k) 
    	{
    		smallest = root.val;
    		return;
    	}
    	
    	kthSmallestTraversal(root.right, k);
    }
    
    //CAT: OTHERS
    //-----------

    // https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
    // The trick here is that the min difference between any nodes in any positions in the tree
    // that means do a sorted traversal (inorder) and then get the difference between pairs and 
    // return the minimum difference
    // Runtime is O(N) + O(N) = O(N)
    public int getMinimumDifference(TreeNode root) {
        if(root == null) return Integer.MAX_VALUE;
        
        int min = Integer.MAX_VALUE;
        
        ArrayList<TreeNode> nodes = (ArrayList<TreeNode>) this.InOrderTraversal(root); 
        
        for(int i = 0; i < nodes.size() - 1; i++)
        {
        	TreeNode current = nodes.get(i);
        	TreeNode next = nodes.get(i + 1);
        	
        	if(Math.abs(next.val - current.val) < min)
        	{
        		min = Math.abs(next.val - current.val);
        	}
        }
        
        return min;
    }
    
    // In Order traversal and Store the nodes in a list
    private List<TreeNode> InOrderTraversal(TreeNode node)
    {
    	if(node == null) return null;
    	
    	ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
    	
    	if(node.left != null) nodes.addAll(InOrderTraversal(node.left));
    	nodes.add(node);
    	if(node.right != null) nodes.addAll(InOrderTraversal(node.right));

    	return nodes;    	
    }
    
    //https://leetcode.com/problems/binary-tree-inorder-traversal/
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> newList = new ArrayList<Integer>();
        if(root == null) return newList;
        if(root.left != null) newList.addAll(inorderTraversal(root.left));
        newList.add(root.val);
        if(root.right != null) newList.addAll(inorderTraversal(root.right));
        
        return newList;
    }

	//https://leetcode.com/problems/binary-tree-preorder-traversal/
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> newList = new ArrayList<Integer>();
        
        if(root==null) return newList;
        
        newList.add(root.val);
        if(root.left!=null) newList.addAll(preorderTraversal(root.left));
        if(root.right!=null) newList.addAll(preorderTraversal(root.right));
        
        return newList;
    }	

    //https://leetcode.com/problems/binary-tree-pruning/description/
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
    	
    	boolean leftOne = HasOneInTree(root.left);
    	boolean rightOne = HasOneInTree(root.right);
    	
    	if(!leftOne) root.left = null;
    	if(!rightOne) root.right = null;
    	
    	return root.val == 1 || leftOne || rightOne;
    }
    
    public static void InOrderPrint(TreeNode node)
    {
    	if(node == null) return;
    	
    	if(node.left != null) InOrderPrint(node.left);
    	System.out.print(node.val + ", ");
    	if(node.right != null) InOrderPrint(node.right);
    }
    
    //https://leetcode.com/problems/balanced-binary-tree/
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        
        // we need to check if it is balanced in the current level
        // and other subsequent levels on left and right subtree
        return (Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1)
        		&& isBalanced(root.left)
        		&& isBalanced(root.right);
    }
    
    //https://leetcode.com/problems/minimum-depth-of-binary-tree/
    public int MinDepth(TreeNode root) {
        if(root == null) return 0;
        
        if(root.left == null && root.right != null) return 1 + MinDepth(root.right);
        if(root.right == null && root.left != null) return 1 + MinDepth(root.left);
        
        return 1 + Math.min(MinDepth(root.left), MinDepth(root.right));
    }
    
    public int maxDepth(TreeNode node)
    {
    	if(node == null) return 0;
    	
    	return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }
    
    //https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/
    //TODO
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        
//    }
    
    //https://leetcode.com/problems/kth-smallest-element-in-a-bst/
    //TODO
//    public int kthSmallest(TreeNode root, int k) {
//        
//    }
}