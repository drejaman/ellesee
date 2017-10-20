package trees;

public class BinaryTree extends Tree{

	
	public BinaryTree(MyTreeNode r) {
		super(r);
		super.setChildrenSize(2);
	}
	
	public void addNode(BinaryTreeNode parentNode,BinaryTreeNode childNode)
	{
		if(parentNode.getLeft()==null)
			parentNode.setLeft(childNode);
		else if(parentNode.getRight()==null)
			parentNode.setRight(childNode);
		else addNode(parentNode.getLeft(),childNode);
	}

	public void addNodeBST(BinaryTreeNode parentNode,BinaryTreeNode childNode)
	{
		if(parentNode.getLeft()==null)
			parentNode.setLeft(childNode);
		else if(parentNode.getRight()==null)
			parentNode.setRight(childNode);
		else addNode(parentNode.getLeft(),childNode);
	}
	
	public void addNodeBalanced(BinaryTreeNode node,BinaryTreeNode childNode)
	{
		if(node!=null)
			System.out.println(node.getValue());
		if(node.getLeft()!=null)
			addNodeBalanced(node.getLeft(),childNode);
		if(node.getRight()!=null)
			addNodeBalanced(node.getRight(),childNode);
		if(node.getLeft()==null)
			node.setLeft(childNode);
		else if(node.getRight()==null)
			node.setRight(childNode);
	}
	
	public void preOrderPrint(BinaryTreeNode node)
	{
		if(node!=null)
			System.out.println(node.getValue());
		if(node.getLeft()!=null)
			preOrderPrint(node.getLeft());
		if(node.getRight()!=null)
			preOrderPrint(node.getRight());
	}
}
