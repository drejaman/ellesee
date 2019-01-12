package dyna;

public class TestDynamic {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
     if(obstacleGrid == null || obstacleGrid.length == 0) return 0;
     
     //won't be able to start
     if(obstacleGrid[0][0] == 1) return 0;

     int row = obstacleGrid.length;
     int col = obstacleGrid[0].length;
    	
     obstacleGrid[0][0] = 1;
     
     //initialize row0 and column0
     for(int i = 1; i < row; i++)
     {
    	 	obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
     }

     for(int j = 1; j < col; j++)
     {
    	 	obstacleGrid[0][j] = (obstacleGrid[0][j] == 0 && obstacleGrid[0][j - 1] == 1) ? 1 : 0;
     }
     
     for(int i = 1; i < row; i++)
     {
    	 	for(int j = 1; j < col; j++)
    	 	{
    	 		if(obstacleGrid[i][j] == 0)
    	 		{
    	 			obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
    	 		}
    	 		else
    	 		{
    	 			obstacleGrid[i][j] = 0;
    	 		}
    	 	}
     }
     
     return obstacleGrid[row - 1][col - 1];
    }

    //https://leetcode.com/problems/unique-paths/
    /*
     A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

	The robot can only move either down or right at any point in time. 
	The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
	
	logic: 
	for 0,0 position ways are 0
	for the cells in top row (0th row) or 0th column ways are 1
	for other cell (i, j) the ways(i, j) = sum(ways(i-1, j) + ways(i, j-1)) 
     * */
    //tips: memoization is a must to avoid time limit exceed problem
    //interestingly here n represents rows and m columns
    public int uniquePaths(int m, int n) {
        if(m == 1 && n == 1) return 1;
        
        int row = n, col = m;
        // we could do it recursively exhausting all the stacks and exceeding time limit 
        int[][] memo = new int[row][col];
        
        //initialization of memo book
        for(int i = 0; i < row; i++) memo[i][0] = 1;
        for(int j = 0; j < col; j++) memo[0][j] = 1;
        
        for(int i = 1; i < row; i++)
        {
        		for(int j = 1; j < col; j++)
        		{
        			memo[i][j] = memo[i-1][j] + memo[i][j-1];
        		}
        }
        
        return memo[row-1][col-1];
    }
    
    //https://leetcode.com/problems/palindromic-substrings/
    int count = 0;
    
    public int countSubstrings(String s) {
        if(s == null || s.isEmpty()) return 0;
        
        for(int i = 0; i < s.length(); i++)
        {
        		countSubstring(s, i, i);//odd length
        		countSubstring(s, i, i+ 1);//even length
        }
        
        return count;
    }
    
    private void countSubstring(String s, int left, int right)
    {
    		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right))
    		{
    			left--;
    			right++;
    			count++;
    		}
    }
    
    //https://leetcode.com/problems/arithmetic-slices/
    public int numberOfArithmeticSlices(int[] A) {
    		int sum = 0;
    		
    		int[] dp = new int[A.length];
    		
    		for(int i = 2; i < A.length; i++)
    		{
    			// this check true means we have got an arithmetic slice
    			if(A[i] - A[i - 1] == A[i - 1] - A[i - 2])
    			{
    				dp[i] = dp[i - 1] + 1;
    			}
    			
    			sum += dp[i];
    		}
    		
    		return sum;
    }


}