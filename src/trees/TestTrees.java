package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
		BinaryTreeNode root=new BinaryTreeNode("A");
		root.setParentId(0);
		BinaryTree tree=new BinaryTree(root);
		BinaryTreeNode child1 = new BinaryTreeNode("B");
		BinaryTreeNode child2 = new BinaryTreeNode("C");
		BinaryTreeNode child3 = new BinaryTreeNode("D");
		BinaryTreeNode child4 = new BinaryTreeNode("E");
		BinaryTreeNode child5 = new BinaryTreeNode("F");
		
		tree.addNode(root, child1);
		tree.addNode(root, child2);
		tree.addNode(root, child3);
		tree.addNode(root, child4);
		tree.addNode(root, child5);
		
		/*
		tree.addNodeBalanced(root, child1);
		tree.addNodeBalanced(root, child2);
		tree.addNodeBalanced(root, child3);
		tree.addNodeBalanced(root, child4);
		tree.addNodeBalanced(root, child5);
		*/
		tree.preOrderPrint(root);
		//tree.postOrderPrint(root);
	}

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
    
    // Inorder traversal and Store the nodes in a list
    private List<TreeNode> InOrderTraversal(TreeNode node)
    {
    	if(node == null) return null;
    	
    	ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
    	
    	if(node.left != null) nodes.addAll(InOrderTraversal(node.left));
    	nodes.add(node);
    	if(node.right != null) nodes.addAll(InOrderTraversal(node.right));

    	return nodes;    	
    }
    
    public int findTilt(TreeNode root) {
        if(root == null) return 0;
        
        return Math.abs(this.TreeSum(root.left) - this.TreeSum(root.right));
    }
    
    public int TreeSum(TreeNode root)
    {
    	if(root == null) return 0;
    	
    	return root.val + this.TreeSum(root.left) + this.TreeSum(root.right);
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
     * */
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
    
    // https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
    // this solution works perfectly fine except for huge input the memory limit exceeds
    // runtime O(N) - number of nodes in the tree
    // logic: 
    // if right child is empty then node.right = node.left
    // if right child is not empty then push the right child in the stack, then node.right = node.left and then recursively call the method with node.left
    // once it get backs to the same node then the returningNode.right = flatten(pop())
    static Stack<TreeNode> treeStack = new Stack<TreeNode>();
    
    public static TreeNode flattenTree(TreeNode root) {
    	if(root == null) return null;
    	
    	if(root.left == null && root.right != null) flattenTree(root.right);
    	else if(root.right != null) treeStack.push(root.right);
    	
    	if(root.left != null) 
    	{
        	root.right = root.left;
        	root.left = null;
    	}

    	if(root.right != null) flattenTree(root.right);
    	
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
    
    public static void InOrderPrint(TreeNode node)
    {
    	if(node == null) return;
    	
    	if(node.left != null) InOrderPrint(node.left);
    	System.out.print(node.val + ", ");
    	if(node.right != null) InOrderPrint(node.right);
    }

    //https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/description/
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
    
    //https://leetcode.com/problems/balanced-binary-tree/
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        
        return (Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1)
        		&& isBalanced(root.left)
        		&& isBalanced(root.right);
    }
    
    public int maxDepth(TreeNode node)
    {
    	if(node == null) return 0;
    	
    	return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
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

    private TreeNode sortedListToBST(int start, int end)
    {
        if(start > end) return null;
        
        int mid = (start + end) /2 ;
        
        TreeNode leftChild = sortedListToBST(start, mid - 1);
        TreeNode parent = new TreeNode(list.val);
        list = list.next;
        parent.left = leftChild;
        parent.right = sortedListToBST(mid + 1, end);
        
        return parent;
    }

    //Cracking4.10
    public boolean containsTree(TreeNode t1, TreeNode t2)
    {
    	// null tree is always a subTree
    	if(t2 == null) return true;

    	return subTree(t1, t2);
    }

    private boolean subTree(TreeNode t1, TreeNode t2)
    {
    	// the big tree is null and we still haven't found the match
    	if(t1 == null) return false;
    	
    	else if(t1.val == t2.val && matchTree(t1, t2))
		{
			return true;
		}

    	// otherwise continue the search on big tree's left and right
    	return subTree(t1.left, t2) || subTree(t1.right, t2);
    }
    
    private boolean matchTree(TreeNode t1, TreeNode t2)
    {
    	if(t1 == null && t2 == null) return true;
    	else if(t1 == null || t2 == null) return false;
    	else if(t1.val != t2.val) return false;
    	else return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right);
    }
    
    //Cracking4.12 pathswithsum
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
    	
    	if(currentSum == targetSum) totalWays++;
    	
    	totalWays += countPathsWithSumFromNode(node.left, targetSum, currentSum);
    	totalWays += countPathsWithSumFromNode(node.right, targetSum, currentSum);
    	
    	return totalWays;
    }
    
    
    //https://leetcode.com/problems/find-duplicate-subtrees/submissions/
    private HashMap<String, Integer> subtreeCount;
    private List<TreeNode> answer;
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
    	subtreeCount = new HashMap<String, Integer>();
    	answer = new ArrayList<TreeNode>();
    	this.serializeToFindDuplicate(root);
    	return answer;
    }
    
    private String serializeToFindDuplicate(TreeNode node)
    {
    	if(node == null) return "#";
    	
    	String currentSerial = node.val + "," + serializeToFindDuplicate(node.left) + "," + serializeToFindDuplicate(node.right);

    	subtreeCount.put(currentSerial, subtreeCount.getOrDefault(currentSerial, 0) + 1);
    	
    	if(subtreeCount.get(currentSerial) == 2)
    	{
    		answer.add(node);
    	}
    	
    	return currentSerial;
    }
    
    //https://leetcode.com/problems/binary-tree-level-order-traversal/
    //Given binary tree [3,9,20,null,null,15,7],
    //[
    //[3],
    //[9,20],
    //[15,7]
    //]
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
    //idea: this will be a variation of levelOrder traversal
    //implementation clue: 
    // get the size of list of list 
    // if the (size + 1) % 2 == 0 then the current list insertion is left to right
    // otherwise insertion is right to left
    // we might need to insert into treeNode list in regular order but alternate times we read from it reverse way
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
        	largeValues.add(Collections.max(current));        	
        }
        
        return largeValues;
    }    
    
    //https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    //TODO
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        
//    }
    
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
    
    // notworking according to the specifications
    //https://leetcode.com/problems/binary-tree-pruning/description/
    public TreeNode pruneTree(TreeNode root) {
    	
    	if(root == null) return null;
    	
    	if(root.left != null)
    	{
    		if(HasZeroInTree(root.left)) root.left = null;
    		else pruneTree(root.left);
    	}
    	
    	if(root.right != null)
    	{
    		if(HasZeroInTree(root.right)) root.right = null;
    		else pruneTree(root.right);
    	}

    	return root;  
    }
    
    private boolean HasZeroInTree(TreeNode root)
    {
    	if(root == null) return true;
    	
    	if(root.val == 0) return true;
    	
    	if((root.left != null && root.left.val == 0) || (root.right != null && root.right.val == 0)) return true;
    	
    	return HasZeroInTree(root.left) && HasZeroInTree(root.right);
    }
        
    //notworking (stack overflow error)
    // https://leetcode.com/problems/insert-into-a-binary-search-tree/description/
    public TreeNode insertIntoBST(TreeNode root, int val) {
    	if(root == null) return new TreeNode(val);
    	
    	if(val >= root.val && root.right == null)
    	{
    		root.right = new TreeNode(val);
    	}
    	
    	if(val < root.val && root.left == null)
    	{
    		root.left = new TreeNode(val);
    	}
    	
    	if(val >= root.val && root.right != null)
    	{
    		insertIntoBST(root.right, val);
    	}

    	if(val < root.val && root.left != null)
    	{
    		insertIntoBST(root.left, val);
    	}

    	return root;
    }
    
}
