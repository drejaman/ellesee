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
}