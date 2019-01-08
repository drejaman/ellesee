package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import trees.TreeNode;

public class TestGraphs {

	public TestGraphs()
	{
		
	}
	
	// https://leetcode.com/problems/all-paths-from-source-to-target/description/
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
    
    //https://leetcode.com/problems/friend-circles/
    // need to add some better explanation
    public int findCircleNum(int[][] M) {
        if(M == null || M.length == 0) return 0;
        
        int len = M.length;
        int count = 0;
        
        for(int i = 0; i < len; i++)
        {
    			if(M[i][i] == 1)
    			{
    				this.dfsVisitFriend(M, i, len);
    				count++;
    			}
        }
        
        return count;
    }

    private void dfsVisitFriend(int[][] grid, int r, int len)
    {
    		for(int c = 0; c < len; c++)
    		{
    			if(grid[r][c] == 1)
    			{
    				grid[r][c] = grid[c][r] = 0;
    				this.dfsVisitFriend(grid, c, len);
    			}
    		}
    	}
    
    //https://leetcode.com/problems/number-of-islands/
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        
        for(int i = 0; i < row; i++)
        {
        		for(int j = 0; j < col; j++)
        		{
        			if(grid[i][j] == '1')
        			{
        				this.dfsVisitIsland(grid, i, j, row, col);
        				count++;
        			}
        		}
        }
        
        return count;
    }
    
    private void dfsVisitIsland(char[][] grid, int r, int c, int row, int col)
    {
    		if(r < 0 || c < 0 || r >= row || c >= col || grid[r][c] != '1') return;

    		grid[r][c] = '0';
    		
    		//island consists of only the up or down or left or right cell not the diagonal ones
    		this.dfsVisitIsland(grid, r, c-1, row, col);
    		this.dfsVisitIsland(grid, r, c+1, row, col);
    		this.dfsVisitIsland(grid, r-1, c, row, col);
    		this.dfsVisitIsland(grid, r+1, c, row, col);
    }
    
    //https://leetcode.com/problems/word-search/
    public boolean exist(char[][] board, String word) {
        if(word == null || word.isEmpty() || word.length() == 0) return true;
        
        for(int i = 0; i < board.length; i++)
        {
        		for(int j = 0; j < board[0].length; j++)
        		{
        			if(board[i][j] == word.charAt(0))
        			{
        				if(wordExist(board, i, j, word, 0, new boolean[board.length][board[0].length]))
        					return true;
        			}
        		}
        }
        
        return false;
    }
    
    private boolean wordExist(char[][] board, int r, int c, String word, int wi, boolean[][] visit)
    {
    		if(wi == word.length()) return true;
    		
    		if(r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] != word.charAt(wi) || visit[r][c])
    			return false;
    		
    		visit[r][c] = true;

    		if(wordExist(board, r - 1, c, word, wi + 1, visit) ||
    			wordExist(board, r, c - 1, word, wi + 1, visit) ||
    			wordExist(board, r + 1, c, word, wi + 1, visit) ||
    			wordExist(board, r, c + 1, word, wi + 1, visit))
    			return true;
 
    		// this is the key as this is backtracking to setting the character's visit to false 
    		// that was likely can later be used for another pass
    		visit[r][c] = false;
        
    		return false;
    }

    //https://leetcode.com/problems/keys-and-rooms/
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    		if(rooms == null) return true;
    		
    		boolean[] visit = new boolean[rooms.size()];
    		
    		Stack<Integer> traverse = new Stack<Integer>();
    		visit[0] = true;
    		traverse.push(0);
    		
    		while(!traverse.isEmpty())
    		{
    			int current = traverse.pop();
    			
    			for(int key : rooms.get(current))
    			{
    				if(!visit[key])
    				{
    					visit[key] = true;
    					traverse.push(key);
    				}
    			}
    		}
    		
    		for(boolean visited : visit)
    			if(!visited) return false;
    		
    		return true;
    }

    //https://leetcode.com/problems/max-area-of-island/
    public int maxAreaOfIsland(int[][] grid) {
    		int maxArea = 0;
 
    		if(grid == null || grid.length == 0) return maxArea;
    		
    		for(int i = 0; i < grid.length; i++)
    		{
    			for(int j = 0; j < grid[0].length; j++)
    			{
    				if(grid[i][j] == 1)
    				{
    					maxArea = Math.max(maxArea, maxAreaVisit(grid, i, j, new boolean[grid.length][grid[0].length]));
    				}
    			}
    		}
    		
    		return maxArea;
    }
    
    private int maxAreaVisit(int[][] grid, int r, int c, boolean[][] visit)
    {
    		//we need to check the indices at first otherwise it could be array index out of founds exception
    		if(r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0 || visit[r][c])
    			return 0;
    		
    			visit[r][c] = true;
    			return 1 + maxAreaVisit(grid, r - 1, c, visit) + maxAreaVisit(grid, r + 1, c, visit)
    					+ maxAreaVisit(grid, r, c - 1, visit) + maxAreaVisit(grid, r, c + 1, visit);
    }

    
    //https://leetcode.com/problems/house-robber-iii/
    //idea: level order traversal. 
    // add the odd rows = sumOddRows
    // add the even rows = sumEvenRows
    // return Math.max(sumOddRows, sumEvenRows)
    //TODO
//    public int rob(TreeNode root) {
//        
//    }
    
    //https://leetcode.com/problems/course-schedule/
    //TODO
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer,List<Integer>> edges = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> completed = new HashSet<Integer>();
        for(int[] p : prerequisites) {
            edges.computeIfAbsent(p[0],k->new ArrayList<Integer>()).add(p[1]);
        }
        for(int course = 0; course<numCourses; course++) {
            dfs(course,edges,visited,completed);
            if(completed.size() >= numCourses) return true;
        }
        
        return false;
    }
    
    private boolean dfs(int course, Map<Integer,List<Integer>> edges,Set<Integer> visited, Set<Integer> completed) {
        if(completed.contains(course)) return true;
        if(visited.contains(course)) return false;
        if(edges.containsKey(course)) { // Has prerequisites
            visited.add(course);
            for(int prereq : edges.get(course)) {
                if(!dfs(prereq,edges,visited,completed)) return false; // Cycle, can't complete this course
            }
        }
        completed.add(course);
        return true;
    }
	    
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

	    	//for each node check if the currentHeight is greater than max at that time
	    	//and if that is the case update the value and max height
    		if(currentHeight > maxHeight)
    		{
    			minLeftValue = root.val;
    			maxHeight = currentHeight;
    		}
    		
    		//it will at first call the left child recursively 
    		//that will make sure we get the bottomost left child's value 
	    	if(root.left != null) findBottomLeftHeight(root.left, 1 + currentHeight);
	    	
	    	if(root.right != null) findBottomLeftHeight(root.right, 1 + currentHeight);
    }	    
}
