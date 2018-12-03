package arrays;

import java.util.PriorityQueue;

public class TestArrays {

	public TestArrays()
	{
		
	}

	// https://leetcode.com/problems/reshape-the-matrix/description/
    public int[][] matrixReshape(int[][] nums, int r, int c) {
     if(nums == null || nums.length * nums[0].length != r * c) {
    	 	return nums;
     }
     
     // handling the row and columns of the original Matrix is the tricky part
     int originalColumns = nums[0].length;
     
     int [][] reshapedMatrix = new int[r][c];
     
     int oi = 0, oj = 0;
     
     for(int i = 0; i < r; i++) {
    	 	for(int j = 0; j < c; j++) {
    	 		reshapedMatrix[i][j] = nums[oi][oj%originalColumns];
    	 		
    	 		// column of the original array increases after each number is visited. so should be mod of columnNo
    	 		oj++;
    	 		// row of the original array increases after each row is done for all columns
    	 		if(oj % originalColumns == 0) oi++;
    	 	}
     }
     
     return reshapedMatrix;
    }
    
    //https://leetcode.com/problems/island-perimeter/
    public int islandPerimeter(int[][] grid) {
    		if(grid == null) return 0;
    		
    		int row = grid.length;
    		int col = grid[0].length;
    		int perimeter = 0;
    		
    		for(int i = 0; i < row; i++)
    			for(int j = 0; j < col; j++)
    			{
    				if(grid[i][j] == 1)
    				{
    					//scan the surrounding and based on that add perimeter value

    					// cell is the top or in the last row
    					if(i == 0) perimeter++;
    					if(i == (row - 1)) perimeter++;

    					// cell is the first or in the last column
    					if(j == 0) perimeter++;
    					if(j == (col - 1)) perimeter++;
    					
    					// not the top row. check the immediate top cell
    					if(i != 0 && grid[i-1][j] == 0) perimeter++;
    					
    					// not the bottom row. check immediate cell below
    					if(i != (row - 1) && grid[i+1][j] == 0) perimeter++;
    					
    					// not the leftmost column. check the immediate 
    					if(j != 0 && grid[i][j-1] == 0) perimeter++;
    					
    					// not the rightmost column
    					if(j != (col - 1) && grid[i][j+1] == 0) perimeter++;
    				}
    			}
    		
    		return perimeter;
    }
    
    // https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
    	int currentPos = 0;
    	
    	for(int i = 0; i < nums.length - 1; i++, currentPos++)
    	{
    		// for array like this [1,1,1,2,2,2,2,3,3,3,3,4] this line 
    		// at first copies a[0] = 1 and after the while loop it also copies the last same number a[2] = 1
    		// which will give the result [1,1,2,2,3,3,4]
    		// removing the line i-- after while will make sure the result is [1,2,3,4]
    		nums[currentPos] = nums[i];

    		if(nums[i] == nums[i + 1])
    		{
    			while(i < nums.length - 1 && nums[i] == nums[i+1])
    			{
    				i++;
    			}
    			// remove only the line below if you want to make sure the result array will only contain
    			i--;
    		}
    	}
    	
    	nums[currentPos] = nums[nums.length - 1];
    	
    	return currentPos + 1;
    }    

    //https://leetcode.com/problems/maximum-subarray/
    public int maxSubArray(int[] A) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        
        if(A==null || A.length==0) 
            return maxSum;
        
        for(int i = 0; i < A.length; i++){
            sum += A[i];
            
            if(sum > maxSum){
                maxSum = sum;
            }
            
            if(sum < 0) sum = 0;
        }

         return maxSum;
   }
    
    //https://leetcode.com/problems/minimum-path-sum/
    public int minPathSum(int[][] grid) {
        
        if(grid==null||grid.length==0) return 0;
        
        int[][] minPath = new int[grid.length][grid[0].length];

        //for the leftmost corner it is the same
        minPath[0][0] = grid[0][0];
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                //for row0
                if(i==0 && j!=0){
                    minPath[i][j] = minPath[i][j-1] + grid[i][j];
                }
                //for column0
                if(i!=0 && j==0){
                    minPath[i][j] = minPath[i-1][j] + grid[i][j];
                }
                if(i!=0 && j!=0){
                    minPath[i][j] = Math.min(minPath[i-1][j], minPath[i][j-1]) + grid[i][j];
                }
            }
        }
        
        return minPath[grid.length-1][grid[0].length-1];
    }
    
    //https://leetcode.com/problems/kth-largest-element-in-an-array/
    public int findKthLargest(int[] nums, int k) {
    	//pq keeps the largest elements
    	final PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
    	
    	for(int val : nums)
    	{
    		// inserts the element
    		pq.offer(val);
    		
    		//if pq size is larger than k then removes the smallest element
    		if(pq.size() > k)
    		{
    			pq.poll();
    		}
    	}
    	
    	//pq now only contains element kth largest and larger than that element
    	return pq.peek();
    }
    
    //https://leetcode.com/problems/subarray-sum-equals-k/solution/
    // O(n2) runtime, O(1) space
    public int subarraySum(int[] nums, int k) {
     if(nums == null || nums.length == 0) return 0;
     
     int sumCount = 0;
     
     for(int i = 0; i < nums.length; i++)
     {
    	 int sum = nums[i];
    	 
         if(sum == k)
         {
             sumCount++;
         }

    	 for(int j = i + 1; j < nums.length; j++)
    	 {
    		 sum += nums[j];
             
    		 if(sum == k)
    		 {
    			 sumCount++;
    		 }
    	 }
     }

     return sumCount;
    }
    
    //https://leetcode.com/problems/search-a-2d-matrix-ii/
    //runtime O(m+n) 
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;
        
        int row = 0;
        int col = matrix[row].length - 1;
        
        while(col >= 0 && row <= (matrix.length - 1))
        {
       	 	if(matrix[row][col] == target) return true;
       	 	
       	 	if(target > matrix[row][col]) row++;
       	 	else col--;
        }
        
        return false;
    }
    
    //https://leetcode.com/problems/max-area-of-island/
    //TODO
//    public int maxAreaOfIsland(int[][] grid) {
//        
//    }
    
    //https://leetcode.com/problems/unique-paths/
    /*
     A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

	The robot can only move either down or right at any point in time. 
	The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * */
    //TODO
//    public int uniquePaths(int m, int n) {
//        
//    }
    
    //https://leetcode.com/problems/rotate-image/
    //TODO
    public void rotate(int[][] matrix) {
        
    }
    
    // notworking
    // Works for most cases. For some cases like where there is a full row/column of zero
    // https://leetcode.com/problems/surrounded-regions/description/
    public void solve(char[][] board) {
        if(board == null) return;
        
        int row = board.length;
        int col = board[0].length;
        
        for(int i = 1; i< row - 1; i++ )
        {
        		for(int j = 1; j < col - 1 ; j++)
        		{
        			if(board[i][j] == 'O' && checkBorderZero(i, j, board))
        			{        				
        				board[i][j] = 'X';
        			}
        		}
        }
    }    
    
    //checks if the specific 0 is actually a border zero
    private boolean checkBorderZero(int rowPos, int colPos, char[][] board)
    {
    	boolean isBorderZero = false;
        int row = board.length;
        int col = board[0].length;

        if(rowPos < row - 1 && colPos < col - 1 && board[rowPos][colPos] == 0)
        {
	    		if((rowPos == 1 && board[rowPos-1][colPos] == 'O') 
	    				||(rowPos == (row - 2) && board[row- 2 + 1][colPos] == 'O')
	    				||(colPos == 1 && board[rowPos][colPos - 1] == 'O')
	    				||(colPos == (col - 2) && board[rowPos][col- 2 + 1] == 'O'))
	    			isBorderZero = true;        	
	    		else
	    		{
	    			return checkBorderZero(rowPos - 1, colPos, board) 
	    					|| checkBorderZero(rowPos + 1, colPos, board) 
	    					|| checkBorderZero(rowPos, colPos - 1, board) 
	    					|| checkBorderZero(rowPos, colPos + 1, board);
	    		}
        }
		
		return isBorderZero;
    }
    

    //notworking
    //https://leetcode.com/problems/partition-array-into-disjoint-intervals/
    //           5, 0, 3, 8, 6
    // this should work. works for the sample
    public int partitionDisjoint(int[] A)
    {
    	if(A == null || A.length == 0) return -1;
    	
    	int i = 0, j = A.length - 1;
    	
    	int leftMax = A[i], rightMin = A[j];
    	
    	int partition = i;
    	
    	while(i <= j && A[i] < rightMin)
    	{
    		if(A[i] > leftMax)
    		{
    			leftMax = A[i];
    		}
    		
    		if(A[j] < rightMin)
    		{
    			rightMin = A[j];
    		}
    		
    		if(leftMax >= rightMin)
    		{
    			partition = i;
    			break;    			
    		}
    		
    		i++;
    		j--;
    	}
    	
    	return partition;
    }    
}