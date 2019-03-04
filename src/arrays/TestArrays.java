package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class TestArrays {

	public TestArrays()
	{
		
	}

	//https://leetcode.com/problems/reshape-the-matrix/description/
	//easy
	//logic: 
	// counters for originalRow and originalColumn
	// keep incrementing oj after each assignment
	// if( oj % originalColumn == 0) oi++
	// in general reshapedMatrix[i][j] = nums[oi][oj % originalColumn]
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
    	 		reshapedMatrix[i][j] = nums[oi][oj % originalColumns];
    	 		
    	 		// column of the original array increases after each number is visited
    	 		// so should be mod of columnNo
    	 		oj++;
    	 		// row of the original array increases after each row is done for all columns
    	 		if(oj % originalColumns == 0) oi++;
    	 	}
     }
     
     return reshapedMatrix;
    }
    
    //https://leetcode.com/problems/island-perimeter/
    //trick:
    //inspect each cell
    //there are two cases a cell with 1 will be added to perimeter
    //case - 1: the cell is already in perimeter (edge)
    //case - 2: the cell is not on edge but is surrounded by water
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
					//scan the surrounding and based on that adds to perimeter value
	
					//case - 1: the cell is already in perimeter (edge)
					// cell is the top or in the last row or cell is the first or in the last column
					if(i == 0 || i == (row - 1) || j == 0 || j == (col - 1)) perimeter++;
					
					//case - 2: the cell is not on edge but is surrounded by water
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
    
    //https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
    	int uniquePos = 0;
    	
    	//key trick here is:
    	//uniquePos gets increased only for the unique number
    	//i always gets increased even for duplicate numbers
    	for(int i = 0; i < nums.length - 1; i++, uniquePos++)
    	{
    		// for array like this [1,1,1,2,2,2,2,3,3,3,3,4] this line 
    		// at first copies a[0] = 1 and after the while loop it also copies the last same number a[2] = 1
    		// which will give the result [1,1,2,2,3,3,4]
    		// removing the line i-- after while will make sure the result is [1,2,3,4]
    		nums[uniquePos] = nums[i];

    		if(nums[i] == nums[i + 1])
    		{
    			//make sure to only increase i for duplicates
    			while(i < nums.length - 1 && nums[i] == nums[i+1])
    			{
    				i++;
    			}
    			// remove only the line below if you want to make sure 
    			// the result array will only contain non-dups
    			// removing this line makes sure we skip all duplicates
    			// and only keep one copy of every unique number
    			i--;
    		}
    	}
    	
    	//the final update
    	nums[uniquePos] = nums[nums.length - 1];
    	
    	return uniquePos + 1;
    }    
    
    //https://leetcode.com/problems/remove-duplicates-from-sorted-array/
    //runtime O(n)
    //trick: we need two pointers
    //i points to the running index in the array
    //uniqueNumbers points to the unique number in the array
    public int removeDuplicatesI(int[] A) {
        if(A==null || A.length==0) return 0;
        
        int currentNumber = A[0];

        int uniqueNumbers = 1;
        
        // At this step we mark all duplicate numbers as -1
        for( int i = 1; i < A.length;)
        {
        	// as long as it is the same number we keep increasing i
            while(i < A.length && A[i] == currentNumber)
            {
                i++;
            }
            
            //we are done with duplicate numbers and
            //now A[i] contains a new unique number
            //so add that unique number to the array A
            if(i < A.length){
                currentNumber = A[i++];    
                A[uniqueNumbers] = currentNumber;
                uniqueNumbers++;
            }
        }
        
        return uniqueNumbers;
    }
    
    //https://leetcode.com/problems/remove-element/
    public int removeElement(int[] A, int elem) {
        int len = A.length - 1;
        
        for(int i = 0; i <= len; i++)
        {
        	//when we find the element we move the last element
        	//from the list to that location
            if(A[i] == elem)
            {
                while(len >= 0 && A[len] == elem) len--;

                if(len > i)
                {
                    A[i] = A[len];
                    len--;
                }
            }
        }
        
        return len+1;
    }

    //https://leetcode.com/problems/missing-number/
    //Mathematically sum = n * (n + 1) / 2
    public int MissingNumber(int[] nums)
    {
        if (nums == null) return -1;

        int numLen = nums.length;

        int sum = (numLen * (numLen + 1))/ 2;

        for (int num : nums)
        {
            sum -=  num;
        }

        //return (numLen > sum) ? numLen : sum;
        return sum;
    }
    
    //https://leetcode.com/problems/contains-duplicate/
    public boolean ContainsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2)
        {
            return false;
        }

        HashSet<Integer> tracker = new HashSet<Integer>();

        for (int i : nums)
        { 
            if(tracker.contains(i))
            {
                return true;
            }

            tracker.add(i);
        }

        return false;
    }

    //https://leetcode.com/problems/move-zeroes/
    public void moveZeroes(int[] nums) {
        
        //edge/error cases
        if(nums == null || nums.length <= 1) return;
        
        int zeroIndex = 0;
        
        //this handles the case when we have no zero in the array
        while(zeroIndex < nums.length && nums[zeroIndex] != 0)
        {
            zeroIndex++;
        }
        
        for(int i = zeroIndex; zeroIndex > -1 && i < nums.length && zeroIndex < nums.length; i++)
        {
        	// find a zeroIndex
            if(nums[i] == 0 && i < zeroIndex) zeroIndex = i;
            
            //we have found an index that doesn't contain 0 and we haven't just set the zeroIndex
            if(nums[i] != 0 && i != zeroIndex)
            {
                nums[zeroIndex] = nums[i];
                nums[i] = 0;
                
                // this is the key as after the swap we have to scan for zero 
                // right after from previous zeroIndex
                int tempIndex = i;
                i = zeroIndex;
                zeroIndex = tempIndex;
            }
        }
    }
    
    //https://leetcode.com/problems/maximum-subarray/
    //a variation is the size of MaxSubarray: keep a counter and reset it if(sum < 0)
    public int maxSubArray(int[] A) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        
        if(A == null || A.length == 0) 
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
    
    //https://leetcode.com/problems/subarray-sum-equals-k/
    // O(n2) runtime, O(1) space
    // trick: 2 loops
    // for each i we start summing it up with another loop. if the sum == k we break
    // else for another i run another loop
    public int subarraySum(int[] nums, int k) {
     if(nums == null || nums.length == 0) return 0;
     
     int sumCount = 0;
     
     for(int i = 0; i < nums.length; i++)
     {
	 	int sum = nums[i];
    	 
	 	// there could be case the initialized number is already equal to sum
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
    			 break;
    		 }
    	 }
     }

     return sumCount;
    }
    
    //https://leetcode.com/problems/minimum-path-sum/
    public int minPathSum(int[][] grid) {
        
        if(grid == null || grid.length == 0) return 0;
        
        int[][] minPath = new int[grid.length][grid[0].length];

        //for the leftmost corner it is the same
        minPath[0][0] = grid[0][0];
        
        //logic: 3 cases
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                //for row0
                if(i == 0 && j != 0){
                    minPath[i][j] = minPath[i][j-1] + grid[i][j];
                }
                
                //for column0
                if(i != 0 && j == 0){
                    minPath[i][j] = minPath[i-1][j] + grid[i][j];
                }
                
                // when not row or column 0
                if(i != 0 && j != 0){
                    minPath[i][j] = Math.min(minPath[i-1][j], minPath[i][j-1]) + grid[i][j];
                }
            }
        }
        
        return minPath[grid.length-1][grid[0].length-1];
    }
    
    //https://leetcode.com/problems/search-a-2d-matrix-ii/
    // trick: start at row 0 and col n-1 
    // if the target is greater than current no increase row
    // or decrease column
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
    
    //https://leetcode.com/problems/search-a-2d-matrix/
    public boolean searchMatrixI(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0) return false;
        
        //at first find the right row
        int i = 0;
        for(; i < matrix.length-1; i++){
                if(target==matrix[i][0]) return true;
                if(target>matrix[i][0] && target<matrix[i+1][0]){
                    break;
                }
        }
        
        for(int j=0; j<matrix[i].length; j++){
            if(target==matrix[i][j]) return true;
        }

        return false;
    }

    //https://leetcode.com/problems/container-with-most-water/
    //trick:
    //use two index for left (i) and right (j)
    //at any point, width = j - i
    //to calculate area use min (leftH, rightH) * (j - i)
    //now if leftH is less then increase i or if rightH is less decrease j
    public int maxArea(int[] height) {
        if(height == null || height.length == 0) return 0;
        
        int maxArea = 0;
        
        for(int i = 0, j = height.length - 1; i < j; )
        {
        	int left = height[i];
        	int right = height[j];
        	int width = j - i;
        	int minHeight = left > right ? right : left;
        	int area = minHeight * width;
        	
        	if(area > maxArea)
        	{
        		maxArea = area;
        	}
        	
        	if(left <= right) i++;
        	else j--;
        }
        
        return maxArea;
    }    

    //https://leetcode.com/problems/partition-array-into-disjoint-intervals/
    //Input: [5,0,3,8,6]
    //Output: 3
    //Explanation: left = [5,0,3], right = [8,6]
    //O(N) runtime and O(N) space. Need to check if it can be improved to run with constant space
    public int partitionDisjoint(int[] A)
    {
    	if(A == null || A.length == 0) return -1;
    	int len = A.length;

    	//hold max up to that index starting at 0
    	int[] maxLeft = new int[len];
    	
    	//hold min up to that index starting at len - 1
    	int[] minRight = new int[len];
    	
    	int max = A[0];
    	
    	for(int i = 0; i < len ; i++)
    	{
    		//maxLeft[i] = Math.max(max, A[i]);	//this line doesn't work
    		max = Math.max(max, A[i]);	
    		maxLeft[i] = max;
    	}

    	int min = A[len - 1];
    	
    	for(int i = len - 1; i >= 0 ; i--)
    	{
    		//minRight[i] = Math.min(min, A[i]);// this doesn't work!
    		min = Math.min(min, A[i]);	
            minRight[i] = min;
    	}
    	
    	//now find the index where it can be partitioned
    	for(int i = 1; i < len; i++)
    	{
    		if(maxLeft[i - 1] <= minRight[i]) return i;
    	}
    	
    	return -1;
    }
    
    //https://leetcode.com/problems/sort-colors/
    public void sortColors(int[] A) {
        
        if(A==null||A.length==0) return;
        
        int red = 0, white = 0, blue = 0;
        
        for(int i = 0; i < A.length; i++){
            if(A[i]==0) red++;
            if(A[i]==1) white++;
            if(A[i]==2) blue++;
        }
        
        for(int i=0; i < red; i++){
            A[i] = 0;
        }

        for(int i=0; i < white; i++){
            A[i+red] = 1;
        }

        for(int i=0; i < blue; i++){
            A[i+red+white] = 2;
        }
    }
    
    //https://leetcode.com/problems/rotate-array/
    //Input: [1,2,3,4,5,6,7] and k = 3
    //Output: [5,6,7,1,2,3,4]
    //O(n) time and space
    public void Rotate(int[] nums, int k)
    {
        int arrayLen = nums.length;

        if (k == arrayLen) return;

        if (k >= arrayLen)
        {
            k = k % arrayLen;
        }

        int[] temp = new int[k];

        // copy the last k elements to temp
        for (int i = 0; i < k; i++)
        {
            temp[i] = nums[arrayLen - k + i];
        }

        // move the front part at the end of the array
        for (int i = arrayLen - 1, j = arrayLen - k - 1; i >= k  && j >= 0 ; i--, j--)
        {
            nums[i] = nums[j]; 
        }

        // move the last elements (from temp array) to the front part of the array
        for (int i = 0; i < temp.length; i++)
        {
            nums[i] = temp[i];
        }
    }
    
    //https://leetcode.com/problems/merge-sorted-array/
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        if(nums1 == null || nums2 == null) return;
        
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        
        while(i >= 0 && j >= 0)
        {
            if(nums1[i] > nums2[j])
            {
                nums1[k] = nums1[i];
                i--;
            }
            else
            {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        //still have some elements left in nums2
        while(j >= 0)
        {
                nums1[k] = nums2[j];
                j--;
                k--;
        }
    }
    
    //https://leetcode.com/problems/pascals-triangle/
    public List<List<Integer>> generate(int numRows) {
    	List<List<Integer>> triangle = new ArrayList<List<Integer>>();

		ArrayList<Integer> previousRow = new ArrayList<Integer>();
		
		for(int i = 0; i < numRows; i++){//this is for rows
			ArrayList<Integer> currentRow = new ArrayList<Integer>();
			
			for(int j = 0; j < (i+1); j++){//the number of columns in each row is equal to row number				
				if( j == 0 || j == i ) currentRow.add(j, 1);
				else{
					int cellValue = previousRow.get(j-1) + previousRow.get(j);
					currentRow.add(j, cellValue);
				}
			}
			
			triangle.add(i, currentRow);
			previousRow = currentRow;
		}
	
		return triangle;
    }

    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
  	public static int[] stockProfit(int[] stockPrices)
  	{
  	  int maxProfit = 0;
  	  int minIndex = 0, maxIndex = 0;
  	  int min = stockPrices[0], max = stockPrices[0];

  	  int[] dates = new int[2];

  	  //O(N) runtime
  	  for(int i = 1; i < stockPrices.length; i++)
  	  {
  	    if(stockPrices[i] >= max)
  	    {
  	      max = stockPrices[i];
  	      maxIndex = i;
  	    }

  	    if(stockPrices[i] < min)
  	    {
  	      min = stockPrices[i];
  	      minIndex = i;
  	    }

  	    if(maxIndex > minIndex && ((max - min) > maxProfit))
  	    {
  	      maxProfit = max - min;
  	      dates[0] = minIndex;
  	      dates[1] = maxIndex;
  	    }
  	  }

  	  return dates;
  	}
    
    // runtime O(N)
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        
        boolean bought = false;
        
        int profit = 0;
        
        int purchasePrice = Integer.MAX_VALUE;
        
        for(int i = 0; i < prices.length; i++)
        {
            if(prices[i] < purchasePrice){
              purchasePrice = prices[i];
              bought = true;
            } 
            
            if(bought && 
            	((i < prices.length - 1 && prices[i] > prices[i + 1]) ||  
            	(i == prices.length - 1) && (prices[i] > purchasePrice)))
            {
                profit += prices[i] - purchasePrice;
                bought = false;
                purchasePrice = Integer.MAX_VALUE;
            }
        }
        
        return profit;
    }
  	
  	//https://leetcode.com/problems/plus-one/submissions/
    public int[] plusOne(int[] digits) {
        if(digits==null || digits.length==0) return null;
        
        int len = digits.length;
        
        //if LSB is less than 9 
        if(digits[len-1] < 9){
            digits[len-1]++;
            return digits;
        }
        
        int carry = 1;
        int sum = 0;
        
        for(int j = len-1; j >= 0; j--){
            sum = digits[j] + carry;
            
            digits[j] = sum%10;
            carry = sum/10;
        }
        
        if(carry==1){
            int[] newNumber = new int[len+1];
            newNumber[0] = 1;
            
            for(int i = 0; i < len; i++){
                newNumber[i+1] = digits[i];
            }
            return newNumber;
        }else{
            return digits;
        }
    }
    
    //https://leetcode.com/problems/majority-element/
    public int MajorityElement(int[] nums) {
        if (nums == null || nums.length == 0)
        {
            return -1;
        }

        int targetLen = nums.length / 2;

        HashMap<Integer, Integer>  tracker = new HashMap<Integer, Integer>();

        for(int n : nums)
        {
            //gets the value for n and checks if that is the majority
            if (tracker.get(n) >= targetLen)
            {
                return n;
            }

            tracker.put(n, tracker.getOrDefault(n, 0) + 1);
        }

        return -1;
    }
    
    //https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
    //Input:[4,3,2,7,8,2,3,1]
    //Output:[5,6]
    public List<Integer> FindDisappearedNumbers(int[] nums)
    {
        int n = nums.length;

        boolean[] presence = new boolean[n];

        for(int num : nums)
        {
            if(!presence[num - 1])
            {
                presence[num - 1] = true;
            }
        }

        List<Integer> missing = new ArrayList<Integer>();

        for(int i = 0; i < presence.length; i++)
        {
            if(!presence[i])
            {
                missing.add(i + 1);
            }
        }

        return missing;
    }

    //https://leetcode.com/problems/max-consecutive-ones/
    public int FindMaxConsecutiveOnes(int[] nums)
    {
        int max1s = 0;
        int running1s = 0;

        for(int i : nums)
        {
            if(i == 1)
            {
                running1s++;
                
                if(running1s > max1s)
                {
                    max1s = running1s;
                }
            }

            if(i != 1)
            {
                running1s = 0;
            }
        }

        return max1s;
    }
}