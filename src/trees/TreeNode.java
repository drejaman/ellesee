package trees;
public class TreeNode
{
	public TreeNode left,right;
	public int val;
	
	public TreeNode()
	{
		left = right = null;		
		val = -1;
	}
	
	public TreeNode(int x)
	{
		left = right = null;	
		val = x;		
	}

	public TreeNode(TreeNode node)
	{
		this.val = node.val;
	}
	
	
	public void setLeft(TreeNode left)
	{
		this.left = left;
	}

	public void setRight(TreeNode right)
	{
		this.right = right;
	}
	
	public TreeNode getLeft()
	{
		return left;
	}

	public TreeNode getRight()
	{
		return right;
	}
	
	public void setValue(int x)
	{
		val = x;
	}
	
	public int getValue()
	{
		return val;
	}
}
