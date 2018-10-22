package graph;

import java.util.ArrayList;
import java.util.List;

public class TestGraphs {

	public TestGraphs()
	{
		
	}
	
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
     List<List<Integer>> paths = new ArrayList<List<Integer>>();
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
                // the list path that is going through the recursion stacks
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
}
