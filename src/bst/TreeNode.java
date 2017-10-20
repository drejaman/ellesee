package bst;
public class TreeNode
{
	TreeNode left,right;
	int value;
	
	public TreeNode()
	{
		left=right=null;		
		value=-1;
	}
	
	public TreeNode(int val)
	{
		left=right=null;	
		value=val;		
	}

	public TreeNode(TreeNode node)
	{
		this.value=node.value;
	}
	
	
	public void setLeft(TreeNode left)
	{
		this.left=left;
	}

	public void setRight(TreeNode right)
	{
		this.right=right;
	}
	
	public TreeNode getLeft()
	{
		return left;
	}

	public TreeNode getRight()
	{
		return right;
	}
	
	public void setValue(int val)
	{
		value=val;
	}
	
	public int getValue()
	{
		return value;
	}
}
