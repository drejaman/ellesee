package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import bst.TreeNode;

public class TestGraphs {
	public TestGraphs()
	{
		
	}
	
	// https://leetcode.com/problems/all-paths-from-source-to-target/description/
	//trick: regular dfs. we start from node 0
	//graph[node] represents the others nodes that can be reachable from node
	//in this case we consider the target is the last node = graph.length - 1
	//variation could be we are also given the specific target
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
     List<List<Integer>> paths = new ArrayList<List<Integer>>();
     List<Integer> path = new ArrayList<Integer>();
     
     path.add(0);     
     dfsSearch(0, graph, paths, path);
     
     return paths;
    }
    
    //trick: since we are using the same subpath (same nodes) several times
    //we don't use visit matrix or state
    public void dfsSearch(int node, int[][] graph, List<List<Integer>> paths, List<Integer> path)
    {
    		//we have reached the last node of the graph
    		if(node == graph.length - 1) 
    		{
            // a new array is required to ensure that we capture the whole path instead of 
            // the list path that is going through the recursions stacks
    			paths.add(new ArrayList<Integer>(path));
    			return;
    		}
    		
    		for(int nextNode : graph[node])
    		{
    			//add the reachable node to path
    			path.add(nextNode);
    			//call dfsSearch recursively with this reachable node
    			dfsSearch(nextNode, graph, paths, path);
	            // this is important to ensure the path is rolled back for failure cases 
	            // where we haven't reached to the destination (n-1) the node
	            path.remove(path.size() - 1);
    		}
    }
    
    //https://leetcode.com/problems/friend-circles/
    // need to add some better explanation
    //  If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not.
    public int findCircleNum(int[][] M) {
        if(M == null || M.length == 0) return 0;
        
        int len = M.length;
        int count = 0;
        
        for(int i = 0; i < len; i++)
        {
    		//we will call dfsVisit for each row to checks friendship for each person
    		//but this check will reduce the call in case that row's relations are already checked out
			if(M[i][i] == 1)
			{
				this.dfsVisitFriend(M, i, len);
				count++;
			}
        }
        
        return count;
    }

    //dfs the grid for r-th person
    private void dfsVisitFriend(int[][] grid, int r, int len)
    {
		//check r's friendship with other persons
		for(int c = 0; c < len; c++)
		{
			//if there is a friendship exists between r and c then reset in both r and c's rows
			if(grid[r][c] == 1)
			{
				grid[r][c] = grid[c][r] = 0;
				//since r and c are friends then also check who are also friends with c
				//that way as long as we can do this recursively they are just in one circle
				this.dfsVisitFriend(grid, c, len);
			}
		}
    	}
    
    //https://leetcode.com/problems/number-of-islands/
    //logic: 
    //traverse the matrix and for each cell with 1 value call dfsVisit
    //that will set the cell to 0 and call dfsVisit for its adjacent cells
    //so at 1 call of dfsVisit it will reach to all 1s that can be connected to that 1
    //and will increase island count by 1
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        
        for(int i = 0; i < row; i++)
        {
    		for(int j = 0; j < col; j++)
    		{
    			//if we are not allowed to change the matrix then we can 
    			if(grid[i][j] == '1')
    			{
    				this.dfsVisitIsland(grid, i, j, row, col);
    				count++;
    			}
    		}
        }
        
        return count;
    }
    
    //the dfsVisit is called when the cell is 1. this sets the current cell to 0 
    //and call dfsVisit for all adjacent cell
    private void dfsVisitIsland(char[][] grid, int r, int c, int row, int col)
    {
		//if the cell is not 1 (already set to 0) then return as it is 
		//either was already 0 or set to 0
		//so its adjacent cell cannot be part of the island
		if(r < 0 || c < 0 || r >= row || c >= col || grid[r][c] != '1') return;

		grid[r][c] = '0';
		
		//island consists of only the up or down or left or right cell not the diagonal ones
		this.dfsVisitIsland(grid, r, c-1, row, col);
		this.dfsVisitIsland(grid, r, c+1, row, col);
		this.dfsVisitIsland(grid, r-1, c, row, col);
		this.dfsVisitIsland(grid, r+1, c, row, col);
    }
    
    //https://leetcode.com/problems/max-area-of-island/
    public int maxAreaOfIsland(int[][] grid) {
		int maxArea = 0;
 
		if(grid == null || grid.length == 0) return maxArea;
    		
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				//if it is 1 then it is part of the Area
				if(grid[i][j] == 1)
				{
					maxArea = Math.max(maxArea, 
							maxAreaVisit(grid, i, j, new boolean[grid.length][grid[0].length]));
				}
			}
		}
		
		return maxArea;
    }
    
    //this is a bit tricky as visit[r][c]
    //ensures that one cell is not counted twice when area calculation is ongoing
    private int maxAreaVisit(int[][] grid, int r, int c, boolean[][] visit)
    {
		//we need to check the indices at first otherwise it could be array index out of founds exception
		//also check if the cell is already visited
		if(r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0 || visit[r][c])
			return 0;
		
		//keeping the cell [r][c] in the area we expand the area 4 ways
		//and get area that can be covered 4 ways adding the cell [r][c]
		//this will give 
		visit[r][c] = true;
		return 1 + maxAreaVisit(grid, r - 1, c, visit) + maxAreaVisit(grid, r + 1, c, visit)
				+ maxAreaVisit(grid, r, c - 1, visit) + maxAreaVisit(grid, r, c + 1, visit);
    }

    // https://leetcode.com/problems/surrounded-regions/
    // A region is captured by flipping all 'O's into 'X's in that surrounded region.
    //logic: check all the border rows and columns cell
    //if any of them are O then call dfs and mark all the connecting O to broder
    //with B so that in the next phase those Os are not turned into X
    //in the second pass if the cell is still O then turn it into X
    //if the cell is B then turn it back into O
    public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0) return;
        
        int rows = board.length;
        int cols = board[0].length;
        
        // pass - 1: turn the bordering O and connecting ones to B
        for(int i = 0; i < rows ; i++)
        {
        	//check column 0 of each row
        	if(board[i][0] == 'O') expandBorderDfs(board, i, 0);

        	//check last column 0 of each row
        	if(board[i][cols - 1] == 'O') expandBorderDfs(board, i, cols - 1);
        }
        
        // pass - 2: turn remaining Os into X and turn back Bs to Os
        for(int j = 0; j < cols ; j++)
        {
        	//check every column of row 0
        	if(board[0][j] == 'O') expandBorderDfs(board, 0, j);

        	//check every column of last row
        	if(board[rows - 1][j] == 'O') expandBorderDfs(board, rows - 1, j);
        }
        
        //now expand X
        for(int i = 0; i < rows; i++ )
        {
    		for(int j = 0; j < cols ; j++)
    		{
    			if(board[i][j] == 'O')
    			{        				
    				board[i][j] = 'X';
    			}
    			else if(board[i][j] == 'B')
    			{
    				board[i][j] = 'O';        				
    			}
    		}
        }
    }    
    
    //expand the bordering 0s at first using DFS
    //expand the map and mark them as B if they are somehow connected to Bordering 0
    private void expandBorderDfs(char[][] board, int i, int j)
    {
    	if(i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1) return;
    	
    	if (board[i][j] == 'O')
    		board[i][j] = 'B';
    	
    	//check the adjacent rows up and down
    	if(i > 1 && board[i - 1][j] == 'O') expandBorderDfs(board, i - 1, j);
    	if(i < board.length - 2 && board[i + 1][j] == 'O') expandBorderDfs(board, i + 1, j);

    	//check the adjacent columns left and right 
    	if(j > 1 && board[i][j - 1] == 'O') expandBorderDfs(board, i, j - 1);
    	if(j < board[i].length - 2 && board[i][j + 1] == 'O') expandBorderDfs(board, i, j + 1);
    }    

    //https://leetcode.com/problems/word-search/
    public boolean exist(char[][] board, String word) {
        if(word == null || word.isEmpty() || word.length() == 0) return true;
        
        //the matching can start at any cell
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
    		
    		if(r < 0 || c < 0 || r >= board.length || c >= board[0].length 
    				|| board[r][c] != word.charAt(wi) 
    				|| visit[r][c])
    			return false;
    		
    		visit[r][c] = true;
    		
    		//unlike maxArea solution where dfsVisit aka maxAreaVisit are added (&&) 
    		//for four possible cases
    		//here wordExist go 4 ways but if if we find word in any of these 4 we are done
    		//it expands the search considering board[r][c] != word.charAt(wi) 
    		if(wordExist(board, r - 1, c, word, wi + 1, visit) ||
    			wordExist(board, r, c - 1, word, wi + 1, visit) ||
    			wordExist(board, r + 1, c, word, wi + 1, visit) ||
    			wordExist(board, r, c + 1, word, wi + 1, visit))
    			return true;
 
    		//trick
    		// this is the key as this is backtracking to setting the character's visit to false 
    		// that was likely can later be used for another pass
    		visit[r][c] = false;
        
    		return false;
    }

    //https://leetcode.com/problems/keys-and-rooms/
    //logic: 
    //start from room0 and push it into stack
    //keep popping from stack the current room
    //check each room key for current room
    //if the room is not already visited then set visit to true
    //and push it into stack
    //at the end check visit array
    //if all cell of the visit are set to true then return true
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
    
    //https://leetcode.com/problems/find-bottom-left-tree-value/
    //idea: BFS. when you reach a left leaf update minimum. 
    //at the end of graph traversal return graph left minimum
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
		//that will make sure we get the bottom most left child's value 
    	if(root.left != null) findBottomLeftHeight(root.left, 1 + currentHeight);
    	
    	if(root.right != null) findBottomLeftHeight(root.right, 1 + currentHeight);
    }	    
    
    //https://leetcode.com/problems/house-robber-iii/
    //idea: level order traversal won't work here
    //do dfs. for each node the max could be 
    //either adding that node [0] or not adding that node [1]
    //lastnight
    public int rob(TreeNode root) {
	    if(root == null) return 0;
	   
	    int[] robVal = dfsRob(root);
	    
	    return Math.max(robVal[0], robVal[1]);
    }
    
    //nodeRobVal[0] = so far robbed value adding this node's value
    //nodeRobVal[1] = so far robbed value without adding this node's value
    private int[] dfsRob(TreeNode node)
    {
    	if(node == null) return new int[2];
	
		int[] left = dfsRob(node.left);
		int[] right = dfsRob(node.right);
		
		int[] nodeRobVal = new int[2];
		
		//max value adding this node's value
		//as we are considering this node's value
		nodeRobVal[0] = node.val + left[1] + right[1];
		
		//as we haven't added this node's value we can add the max of each child's 
		//either including the child's value [0] or without adding child's value [1]
		nodeRobVal[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		
		return nodeRobVal;
    }
    
    //https://leetcode.com/problems/course-schedule/
    //TODO Don't understand the reasoning clearly
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer,List<Integer>> edges = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> completed = new HashSet<Integer>();
        for(int[] p : prerequisites) {
            edges.computeIfAbsent(p[0],k->new ArrayList<Integer>()).add(p[1]);
        }
        for(int course = 0; course < numCourses; course++) {
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
}