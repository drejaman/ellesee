package trees;

public class BinaryTreeNode extends MyTreeNode{

BinaryTreeNode left,right;
String value;

public BinaryTreeNode(String val)
{
	left=right=null;
	value=val;
}

public BinaryTreeNode getLeft() {
	return left;
}

public void setLeft(BinaryTreeNode left) {
	this.left = left;
}


public BinaryTreeNode getRight() {
	return right;
}

public void setRight(BinaryTreeNode right) {
	this.right = right;
}

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}

}
