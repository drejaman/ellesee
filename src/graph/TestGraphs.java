package graph;

import java.util.ArrayList;
import java.util.List;

import trees.TreeNode;

public class TestGraphs {

	public TestGraphs()
	{
		
	}
	
	// https://leetcode.com/problems/all-paths-from-source-to-target/description/
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
     List<List<Integer>> paths = new ArrayList<>();
     List<Integer> path = new ArrayList<Integer>();
     
     path.add(0);
     
     dfsSearch(0, graph, paths, path);
     
     return paths;
    }
    
    public void dfsSearch(int node, int[][] graph, List<List<Integer>> paths, List<Integer> path)
    {
    		if(node == graph.length - 1) 
    		{
                // a new array is required to ensure that we capture the whole path instead of 
                // the list path that is going through the recursions stacks
    			paths.add(new ArrayList<Integer>(path));
    			return;
    		}
    		
    		for(int nextNode : graph[node])
    		{
    			path.add(nextNode);
    			dfsSearch(nextNode, graph, paths, path);
                // this is important to ensure the path is rolled back for failure cases 
                // where we haven't reached to the destination (n-1) the node
                path.remove(path.size() - 1);
    		}
    }
    //notworking    
    //https://leetcode.com/problems/find-bottom-left-tree-value/
    //idea: BFS. when you reach a left leaf update minimum. at the end of graph traversal return graph left minimum
    int minLeftValue = Integer.MAX_VALUE;
    int maxHeight = Integer.MIN_VALUE;

    public int findBottomLeftValue(TreeNode root) {
    	if(root == null) return -1;
    	
    	findBottomLeftHeight(root, 1);
    	
    	return minLeftValue;
    }
    
    private void findBottomLeftHeight(TreeNode root, int currentHeight)
    {
    	if( root == null) return;
    	
    	if(root.left != null)
    	{
    		int leftHeight = currentHeight + 1;
    		
    		if(leftHeight > maxHeight)
    		{
    			minLeftValue = root.left.val;
    			maxHeight = leftHeight;
    		}
    		
    		findBottomLeftHeight(root.left, leftHeight);
    	}
    	
    	if(root.right != null)
    	{
    		findBottomLeftHeight(root.right, 1 + currentHeight);
    	}
    }
    
    //https://leetcode.com/problems/binary-tree-level-order-traversal/
    //Given binary tree [3,9,20,null,null,15,7],
    //[
    //[3],
    //[9,20],
    //[15,7]
    //]
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> treeValues = new ArrayList<List<Integer>>();
        if(root == null) return treeValues;
        
        List<TreeNode> previous = new ArrayList<TreeNode>();
        previous.add(root);
        
        while(!previous.isEmpty())
        {
        	List<TreeNode> current = new ArrayList<TreeNode>();
        	List<Integer> valueList = new ArrayList<Integer>();
        	
        	for(TreeNode node : previous)
        	{
        		if(node.left != null)
        		{
        			current.add(node.left);
        		}
        		
        		if(node.right != null)
        		{
        			current.add(node.right);
        		}

        		valueList.add(node.val);
        	}
        	
        	treeValues.add(valueList);
        	previous = current;
        }
        
        return treeValues;
    }

    
    //https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
    //Given binary tree [3,9,20,null,null,15,7],
    //[
    // [3],
    // [20,9],
    // [15,7]
    //]
    //idea: this will be a variation of levelOrder traversal
    //implementation clue: 
    // get the size of list of list 
    // if the (size + 1) % 2 == 0 then the current list insertion is left to right
    // otherwise insertion is right to left
    // we might need to insert into treeNode list in regular order but alternate times we read from it reverse way
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) return null;
        
        List<List<Integer>> treeValues = new ArrayList<List<Integer>>();
        
        List<TreeNode> previous = new ArrayList<TreeNode>();
        previous.add(root);
        
        while(!previous.isEmpty())
        {
        	List<TreeNode> current = new ArrayList<TreeNode>();
        	List<Integer> valueList = new ArrayList<Integer>();
        	
        	for(TreeNode node : previous)
        	{
        		if((treeValues.size() + 1) % 2 == 0)
        		{
            		if(node.left != null)
            		{
            			current.add(node.left);
            		}

            		if(node.right != null)
            		{
            			current.add(node.right);
            		}
        		}
        		else
        		{
            		if(node.right != null)
            		{
            			current.add(node.right);
            		}

            		if(node.left != null)
            		{
            			current.add(node.left);
            		}
        		}
        		
        		valueList.add(node.val);
        	}
        	
        	treeValues.add(valueList);
        	previous = current;
        }
        
        return treeValues;
    }

    //https://leetcode.com/problems/binary-tree-right-side-view/
    //Input: [1,2,3,null,5,null,4]
    //Output: [1, 3, 4]
    //idea: do level order traversal and get the last node of east level
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightValues = new ArrayList<Integer>();

        if(root == null) return rightValues;

        List<List<Integer>> nodeValues = levelOrder(root);
        
        for(List<Integer> current : nodeValues)
        {
        	rightValues.add(current.get(current.size() - 1));
        }
        
        return rightValues;
    }
}
