package trees;

import java.util.ArrayList;
import java.util.List;

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
		MyTreeNode root=new MyTreeNode(1,"A");
		root.setParentId(0);
		Tree tree=new Tree(root);
		MyTreeNode child1 = new MyTreeNode(2,"B");
		MyTreeNode child2 = new MyTreeNode(3,"C");
		MyTreeNode child3 = new MyTreeNode(4,"D");
		MyTreeNode child4 = new MyTreeNode(5,"E");
		tree.addNode(root, child1);
		tree.addNode(root, child2);
		tree.addNode(root, child3);
		tree.addNode(root, child4);
		tree.preOrderPrint(root);
		//tree.postOrderPrint(root);
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
}
