package bst;

import trees.TreeNode;

public class TestBST
{
	public TestBST()
	{
		
	}
	
	public void test(){
		TreeNode root=new TreeNode(10);
		BinarySearchTree bst=new BinarySearchTree(root);
		TreeNode child1=new TreeNode(5);
		TreeNode child2=new TreeNode(15);
		//TreeNode child3=new TreeNode(15);
		TreeNode child4=new TreeNode(22);
		TreeNode child5=new TreeNode(1);
		TreeNode child6=new TreeNode(12);
		bst.addNode(root,child1);
		bst.addNode(root,child2);
		//bst.addNode(root,child3);
		bst.addNode(root,child4);
		bst.addNode(root,child5);
		bst.addNode(root,child6);
		
		//bst.printTree(root);//preOrderTree
		//System.out.println(bst.findSum(root));//this doesn't work
		
		//int[] elements= {5,3,2,4,8,6,7,9,10};
		//BinarySearchTree bbst=createTreeFromPreOrderTraversal(elements);
		//bbst.printTree(bbst.root);
		bst.printRevTree(root);
		//bst.printTree(bst.mirrorTree(root));
		
		//System.out.println(bst.treeHeight(root));
		//System.out.println(bst.lookup(root, 32));
		//System.out.println(bst.minValue(root));
		//System.out.println(bst.maxValue(root));
		//System.out.println(bst.hasPathSum(root,160));
		//bst.mirror(root);
		//bst.printTree(root);
		//bst.doubleTree(root);
		//bst.printTree(root);

		/*
		 //checks if 2 tress are equal
		TreeNode roots=new TreeNode(10);
		BinarySearchTree sbst=new BinarySearchTree(roots);
		TreeNode child1s=new TreeNode(5);
		TreeNode child2s=new TreeNode(15);
		TreeNode child3s=new TreeNode(15);
		TreeNode child4s=new TreeNode(22);
		TreeNode child5s=new TreeNode(1);
		TreeNode child6s=new TreeNode(12);
		sbst.addNode(roots,child1s);
		sbst.addNode(roots,child2s);
		sbst.addNode(roots,child3s);
		sbst.addNode(roots,child4s);
		sbst.addNode(roots,child5s);
		sbst.addNode(roots,child6s);
		System.out.println("Same Tree: "+bst.sameTree(root, roots));
		//sbst.printTree(roots);
		*/
		//System.out.println(bst.isBST(root));
	}
	
	public BinarySearchTree createTreeFromPreOrderTraversal(int[] elements)
	{
		TreeNode root=new TreeNode(elements[0]);
		BinarySearchTree bst=new BinarySearchTree(root);
		

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
}