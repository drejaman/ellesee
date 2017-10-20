package trees;

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

}
