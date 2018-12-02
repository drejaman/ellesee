package trees;

// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 class TreeLinkNode {
     int val;
     TreeLinkNode left, right, next;
     TreeLinkNode(int x) { val = x; }
 }

public class TreeLinkTest {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        
        // if left and right are not null then connect left and right by the next pointer of left
        if(root.left != null)
        {
        	if(root.right != null) root.left.next = root.right;
        }
        
        // now we need to set right's next pointer
        // the trick is to set the right's next to it's parent's sibling's left
        if(root.right != null)
        {
        	if(root.next != null)
        	{
        		root.right.next = root.next.left;
        	}
        }
        
        // now recurse it through left and right
        connect(root.left);
        connect(root.right);
    }
}
