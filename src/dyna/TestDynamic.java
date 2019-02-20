package dyna;

import java.util.Arrays;

public class TestDynamic {
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
	//similar like unique path with obstacle only you don't need to consider any obstacle
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
    
	//https://leetcode.com/problems/unique-paths-ii/
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
     if(obstacleGrid == null || obstacleGrid.length == 0) return 0;
     
     //won't be able to start
     if(obstacleGrid[0][0] == 1) return 0;

     int row = obstacleGrid.length;
     int col = obstacleGrid[0].length;
    	
     obstacleGrid[0][0] = 1;
     
     // check if the current cell is 0 that means we can move
     // check if the previous cell is 1 that means we have a way to come up to previous spot
     // for the first row or first column we have either just one way to reach there or no way
     //initialize row0 and column0
     for(int i = 1; i < row; i++)
     {
	 	obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
     }

     for(int j = 1; j < col; j++)
     {
    	 	obstacleGrid[0][j] = (obstacleGrid[0][j] == 0 && obstacleGrid[0][j - 1] == 1) ? 1 : 0;
     }
     
     // now for other cells other than row0 or col0
     for(int i = 1; i < row; i++)
     {
    	 	for(int j = 1; j < col; j++)
    	 	{
    	 		//current cell 0 means we have no obstacle and a way to move
    	 		if(obstacleGrid[i][j] == 0)
    	 		{
    	 			//update the no of ways we can get to cell i,j
    	 			//which is the sum of left and upper cell
    	 			obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
    	 		}
    	 		else
    	 		{
    	 			//if the current cell i,j has already value 1 then 
    	 			//there is no way to move through this cell
    	 			obstacleGrid[i][j] = 0;
    	 		}
    	 	}
     }
     
     return obstacleGrid[row - 1][col - 1];
    }
    
    //https://leetcode.com/problems/palindromic-substrings/
    int count = 0;
    
    public int countSubstrings(String s) {
        if(s == null || s.isEmpty()) return 0;
        
        for(int i = 0; i < s.length(); i++)
        {
	    	//odd length. so considers the same index for left and right to start with
			countSubstring(s, i, i);
			//even length
			countSubstring(s, i, i+ 1);
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

    //https://leetcode.com/problems/longest-palindromic-substring/
    //a variation of the previous problem
    int palinLength = 0;
    String palinString = "";
    public String longestPalindrome(String s) {
        if(s == null || s.isEmpty()) return palinString;
        
        for(int i = 0; i < s.length(); i++)
        {
        	countSubstringLength(s, i, i);//odd length
        	countSubstringLength(s, i, i+ 1);//even length
        }
    	
        return palinString;
    }

    private void countSubstringLength(String s, int left, int right)
    {
		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right))
		{
			//this is variation from previous problem to calculate length
			if((right - left) >= palinLength)
			{
				palinString = s.substring(left, right + 1);
				palinLength = right - left;
			}
	
	        left--;
			right++;    			
		}
    }
    
    //https://leetcode.com/problems/arithmetic-slices/
    public int numberOfArithmeticSlices(int[] A) {
		int sum = 0;
		
		int[] dp = new int[A.length];

		//starting index is key, i = 2
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
    
    //https://leetcode.com/problems/perfect-squares/
    public int numSquares(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		// stopping condition. for 0 there's only a way 
		// to get to 0 with 0 squares
		dp[0] = 0;
		    		
		for(int i = 1; i <= n; i++)
		{
			int j = 1;
			
			int min = Integer.MAX_VALUE;
			
			// we use j as j square which is 1 way
			// and add dp[i - j*j] ways to it making
			// total no of ways to get i using j 
			// = dp[i - j * j] + 1
			// then we get min comparing this against previous min
			while(i - j* j >= 0)
			{
				min = Math.min(min, dp[i - j*j] + 1);
                j++;
			}
			
			dp[i] = min;
		}
		
		return dp[n];
    }    
}