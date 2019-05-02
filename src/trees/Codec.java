package trees;

import bst.TreeNode;

//https://leetcode.com/problems/serialize-and-deserialize-bst/ TODO
///
public class Codec {
	
	public Codec()
	{
	}
	
    // Encodes a tree to a single string
    public String serialize(TreeNode root) {
    		
    		if(root == null) 
    		{
    			return "null";
    		}
    		else
    		{
    			return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    		}    		
    }

    // Decodes your encoded data to tree
    //TODO not implemented yet
    public TreeNode deserialize(String data) {        
    		if(data == null || data.isEmpty()) return null;
    		
    		String[] values = data.split(",");
    		
    		boolean[] marked = new boolean[values.length];
    		TreeNode head = new TreeNode(Integer.parseInt(values[0])); 
    		TreeNode root = head;
    		marked[0] = true;
    		
    		for( int i = 0; i < values.length; i++)
    		{
    			if(!marked[i] && !values[i].equals("null"))
    			{
    				root = new TreeNode(Integer.parseInt(values[i]));
    			}
    			
    			if(2 * i < values.length)
    			{
    				root.left = new TreeNode(Integer.parseInt(values[2 * i]));
    			}
    			
    		}
    		
    		return head;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));