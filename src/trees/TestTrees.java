package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
	// Runtime O(n + m) = O(n) considering n > m. Best possible time as we have to traverse all the nodes of each tree
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
    // that means do a sorted traversal (inorder) and then get the difference between pairs and return the minimum difference
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
    
    // https://leetcode.com/problems/n-ary-tree-level-order-traversal/description/
    // Not Working - Says memory limit reached
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Node>> levelNodes = new ArrayList<List<Node>>();
        List<List<Integer>> levelNumbers = new ArrayList<List<Integer>>();
        
        ArrayList<Node> firstNodeList = new ArrayList<Node>(); 
        firstNodeList.add(root);
        levelNodes.add(firstNodeList);
        ArrayList<Integer> firstNumberList = new ArrayList<Integer>(); 
        firstNumberList.add(root.val);
        levelNumbers.add(firstNumberList);
        
        for(int i = 0; i < levelNodes.size(); i++)
        {
        	List<Node> currentLevel = levelNodes.get(i);
        	List<Node> nextLevel = new ArrayList<Node>();
        	List<Integer> nextNumbers = new ArrayList<Integer>();
        	
        	for(int j = 0; j < currentLevel.size(); j++)
        	{
        		Node currentNode = currentLevel.get(j);
        		
        		for(Node nextNode : currentNode.children)
        		{
        			nextLevel.add(nextNode);
        			nextNumbers.add(nextNode.val);
        		}
        	}
        	
        	levelNodes.add(nextLevel);
        	levelNumbers.add(nextNumbers);
        }
        
        return levelNumbers;
    }
    
    // https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
    // this solution works perfectly fine except for huge input the memory limit exceeds
    // runtime O(N) - number of nodes in the tree
    // logic: 
    // if right child is empty then node.right = node.left
    // if right child is not empty then push the right child in the stack, then node.right = node.left and then recursively call the method with node.left
    // once it get backs to the same node then the returningNode.right = flatten(pop())
    static Stack<TreeNode> treeStack = new Stack<TreeNode>();
//
//    public static void flatten(TreeNode root) {
//    	flattenTree(root);
//    }
    
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
    	
    	// when the right subtree is flattened then the head of right subtree becomes prev and tail of left subtree is root
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
