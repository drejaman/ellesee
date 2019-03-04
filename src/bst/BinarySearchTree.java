package bst;

//No LC solutions here. Basic trivial Tree operations like print, mirror etc.
public class BinarySearchTree
{
	TreeNode root;
	
	public BinarySearchTree(TreeNode r)
	{
		root = r;
	}
	
	public void addNode(TreeNode parent, TreeNode child)
	{
		if(child.getValue() <= parent.getValue())	//less than or equal
		//if(child.getValue()>parent.getValue())	//just uncomment this line to test function isBST 
		{
			if(parent.getLeft() == null)
				parent.setLeft(child);
			else
				addNode(parent.getLeft(),child);
		}
		else 
		{
			if(parent.getRight() == null)
				parent.setRight(child);
			else
				addNode(parent.getRight(),child);
		}			
	}
	
	public void printTree(TreeNode printer)
	{
		System.out.println(printer.getValue());
		if(printer.getLeft() != null)
			printTree(printer.getLeft());
		if(printer.getRight() != null)
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
	
	public boolean lookup(TreeNode node,int value)
	{
		if(node == null) return false;

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
		if(node.getLeft() == null)
			return node.getValue();
		else return minValue(node.getLeft());
	}

	public int maxValue(TreeNode node)
	{
		if(node.getRight() == null)
			return node.getValue();
		else return maxValue(node.getRight());
	}
	
	//this one changes the tree to its mirror
	public void mirror(TreeNode node)
	{
		if(node != null)
		{
			TreeNode temp = node.getLeft();
			node.setLeft(node.getRight());
			node.setRight(temp);
		}
		
		if(node.getLeft() != null)
			mirror(node.getLeft());
		
		if(node.getRight() != null)
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
	
	public BinarySearchTree createTreeFromPreOrderTraversal(int[] elements)
	{
		TreeNode root=new TreeNode(elements[0]);
		BinarySearchTree bst = new BinarySearchTree(root);

		findPlace(root,root.getValue(),elements,1,elements.length);
		
		return bst;
	}
	
	public void findPlace(TreeNode node,int referenceValue,int[] elements,int index,int len)
	{
		if(index == len)
			return;
		//if(elements[index]>referenceValue && node.getValue()!=referenceValue)
		//	return;
		if(elements[index]>node.getValue())
		{
			if(elements[index]<referenceValue)
			{
				node.setRight(new TreeNode(elements[index]));
				findPlace(node.getRight(),referenceValue,elements,index+1,len);
			}
			else //if(elements[index]>referenceValue && node.getValue()==referenceValue)
			{
					referenceValue=elements[index];
					return;
			}
		}
		else
		{
			node.setLeft(new TreeNode(elements[index]));
			findPlace(node.getLeft(),referenceValue,elements,index+1,len);			
		}
	}	
	
	//notworking
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
}