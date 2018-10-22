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
     
     int oi = 0, oj =0;
     
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


}
