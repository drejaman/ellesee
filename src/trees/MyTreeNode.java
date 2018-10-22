package trees;
import java.util.ArrayList;

public class MyTreeNode {
int id;
int parentId;
String value;
int intValue;
ArrayList <MyTreeNode> children=new ArrayList<MyTreeNode>();;

public MyTreeNode()
{	
}

public MyTreeNode(int id, String val)
{
	parentId=-1;//parentId = -1 means the parent is not set yet
	this.id=id;
	value=val;
	intValue=0;
	
	//System.out.println("Initializing node"+this.id);
}

public MyTreeNode(int id,int val)
{
	parentId=-1;//parentId = -1 means the parent is not set yet
	this.id=id;
	value="A"+val;
	intValue=val;
	
	//System.out.println("Initializing node"+this.id);
}

public void addChild(MyTreeNode node)
{
	node.setParentId(this.getId());//here we set the parent
	System.out.println("Adding Node "+node.getId()+" to "+node.getParentId());
	children.add(node);
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getParentId() {
	return parentId;
}

public void setParentId(int parentId) {
	this.parentId = parentId;
}

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}


public ArrayList<MyTreeNode> getChildren() {
	return children;
}

public void setChildren(ArrayList<MyTreeNode> children) {
	this.children = children;
}
}
