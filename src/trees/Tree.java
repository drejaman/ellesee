package trees;

public class Tree {

MyTreeNode root;
int childrenSize;

public MyTreeNode getRoot() {
	return root;
}

public void setRoot(MyTreeNode root) {
	this.root = root;
}

public int getChildrenSize() {
	return childrenSize;
}

public void setChildrenSize(int childrenSize) {
	this.childrenSize = childrenSize;
}

public Tree(MyTreeNode r)
{
	System.out.println("Creating new Tree");
	root=r;
	childrenSize=3;
}

public void addNode(MyTreeNode parentNode,MyTreeNode childNode)
{		
		if(parentNode.children.size()<childrenSize)//we are adding maximum 3 children at nodes
			parentNode.addChild(childNode);
		
		else 
		{
			for(MyTreeNode treeNode:parentNode.children)
			{
				if(treeNode.children.size()<childrenSize)
				treeNode.addChild(childNode);
				break;
			}			
		}
}

public void preOrderPrint(MyTreeNode node)
{
	//System.out.println("Printing Tree's Preorder print");
	MyTreeNode printer=node;
	System.out.println(printer.getValue());
		for(MyTreeNode treeNode:printer.getChildren())
		{	
			if(treeNode.getChildren().size()>0)
			{
				preOrderPrint(treeNode);
			}
			else
			{
				System.out.println(treeNode.getValue());
			}
		}
}

public void postOrderPrint(MyTreeNode node)
{
	MyTreeNode printer=node;
		for(MyTreeNode treeNode:printer.getChildren())
		{	
			if(treeNode.getChildren().size()>0)			
			{
				System.out.println(treeNode.getValue());
			}
		}
	System.out.println(printer.getValue());
}

//public void preOrderPrint(BinaryTreeNode node)
//{
//	if(node!=null)
//		System.out.println(node.getValue());
//	if(node.getLeft()!=null)
//		preOrderPrint(node.getLeft());
//	if(node.getRight()!=null)
//		preOrderPrint(node.getRight());
//}
}
