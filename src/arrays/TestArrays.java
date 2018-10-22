package arrays;

public class TestArrays {

	public TestArrays()
	{
		
	}
	
	public void test()
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
    
    // https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
    public int removeDuplicates(int[] nums) {

        if(nums == null || nums.length == 0) return 0;
        
    	int currentPos = 0;
    	int i = 0;
    	
    	for(; i < nums.length - 1; i++, currentPos++)
    	{
    		// for array like this [1,1,1,2,2,2,2,3,3,3,3,4] this line 
    		// at first copies a[0] = 1 and after the while loop it also copies the last same number a[2] = 1
    		// which will give the resul [1,1,2,2,3,3,4]
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