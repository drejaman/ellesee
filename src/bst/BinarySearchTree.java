package bst;

import trees.TreeNode;

public class BinarySearchTree
{
	TreeNode root;
	
	public BinarySearchTree(TreeNode r)
	{
		root=r;
	}
	
	public void addNode(TreeNode parent, TreeNode child)
	{
		if(child.getValue()<=parent.getValue())	//less than or equal
		//if(child.getValue()>parent.getValue())	//just uncomment this line to test function isBST 
			{
				if(parent.getLeft()==null)
					parent.setLeft(child);
				else
					addNode(parent.getLeft(),child);
			}
		else 
			{
				if(parent.getRight()==null)
					parent.setRight(child);
				else
					addNode(parent.getRight(),child);
			}			
	}
	
	public void printTree(TreeNode printer)
	{
		System.out.println(printer.getValue());
		if(printer.getLeft()!=null)
			printTree(printer.getLeft());
		if(printer.getRight()!=null)
			printTree(printer.getRight());
		//System.out.println("Printing finished");
	}

	//largest to smallest
	public void printRevTree(TreeNode printer)
	{
		if(printer.getRight()!=null)
			printRevTree(printer.getRight());
		System.out.println(printer.getValue());
		if(printer.getLeft()!=null)
			printRevTree(printer.getLeft());
		//System.out.println("Printing finished");
	}
	
	public int treeHeight(TreeNode node)
	{		
		int lH = 0,rH = 0;
		if(node.getLeft() != null)
			lH = treeHeight(node.getLeft());
		if(node.getRight() != null)
			rH = treeHeight(node.getRight());
		return 1 + Math.max(lH, rH);
	}
	
	//incomplete or does not work
	public int findMinSum(TreeNode node)
	{
		if(node.getLeft()==null && node.getRight()==null)
			return node.getValue();
		else if(node.getLeft()==null)
			return node.getValue()+ findMinSum(node.getRight());
		else if(node.getRight()==null)
			return node.getValue()+ findMinSum(node.getLeft());
		else
			return node.getValue()+ Math.min(findMinSum(node.getRight()),findMinSum(node.getRight()));		
	}
	
	public boolean lookup(TreeNode node,int value)
	{
		if(node==null) return false;

		if(node.getValue() == value)
			return true;
		else if(value < node.getValue())
			return lookup(node.getLeft(), value);
		else if(value > node.getValue())
			return lookup(node.getRight(), value);
		else
			return false;
	}
	
	public int minValue(TreeNode node)
	{
		if(node.getLeft()==null)
			return node.getValue();
		else return minValue(node.getLeft());
	}

	public int maxValue(TreeNode node)
	{
		if(node.getRight() == null)
			return node.getValue();
		else return maxValue(node.getRight());
	}
	
	public boolean hasPathSum(TreeNode node, int sum)
	{
		//if(node!=null)
		System.out.println("Current Sum is:"+sum);
		//checks here if it reached the leaf of the tree and the sum reaches zero
		if((sum-node.getValue())==0 && node.getLeft()==null && node.getRight()==null) 
			return true;
		
		boolean left=false,right = false;
		if(node.getLeft()!=null)
			left = hasPathSum(node.getLeft(),sum-node.getValue());
		if(node.getRight()!=null)
			right = hasPathSum(node.getRight(),sum-node.getValue());
		return (left||right);
	}
	
	//this one changes the tree to its mirror
	public void mirror(TreeNode node)
	{
		if(node!=null)
		{
			TreeNode temp=node.getLeft();
			node.setLeft(node.getRight());
			node.setRight(temp);
		}
		if(node.getLeft()!=null)
			mirror(node.getLeft());
		if(node.getRight()!=null)
			mirror(node.getRight());
	}
	
	//this one creates another tree which is the mirror of the given tree
	public TreeNode mirrorTree(TreeNode oldRoot)
	{
		TreeNode newRoot = new TreeNode(oldRoot);
		if(oldRoot.getLeft()!=null)
		{
			newRoot.setRight(mirrorTree(oldRoot.getLeft()));			
		}
		if(oldRoot.getRight()!=null)
		{
			newRoot.setLeft(mirrorTree(oldRoot.getRight()));			
		}
		return newRoot;
	}	
	
	//lesson: initially the solution had part-2 before part-1. which leads to an infinite recursion and  
	//gives error as the stacks are all used up
	public void doubleTree(TreeNode node)
	{
		//part-1
		if(node.getLeft()!=null)
			doubleTree(node.getLeft());
		if(node.getRight()!=null)
			doubleTree(node.getRight());	

		//part-2
		if(node!=null)
		{
			TreeNode temp=node.getLeft();
			TreeNode duplicateNode=new TreeNode(node.getValue());
			node.setLeft(duplicateNode);
			duplicateNode.setLeft(temp);
		}		
	}
	
	//https://leetcode.com/problems/same-tree/submissions/
	public boolean sameTree(TreeNode p,TreeNode q)
	{
        if(p == null && q ==null) return true;
        
        if((p == null && q != null) || (p != null && q == null))
            return false;
        
        if(p.val == q.val)
        {
            return sameTree(p.left, q.left)
                && sameTree(p.right, q.right);
        }
        
        return false;
	}
	
	//https://leetcode.com/problems/validate-binary-search-tree/
	public boolean valid(TreeNode p, Integer low, Integer high) 
	{   
		if (p == null) return true;    
	 
		return (low == null || p.val > low) 
	     && (high == null || p.val < high)          
	     && valid(p.left, low, p.val)          
	     && valid(p.right, p.val, high); 
	} 
}