package binsearch;

import java.util.PriorityQueue;

public class BinarySearch {
	//https://leetcode.com/problems/search-insert-position/submissions/
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        if(target > nums[nums.length - 1]) return nums.length - 1;
        if(target < nums[0]) return 0;
        return binarySearchIndex(nums, target, 0, nums.length - 1);
    }
    
    private int binarySearchIndex(int[] nums, int target, int start, int end)
    {
    	if(start > end) return -1;
    	
    	int mid = (start + end)/2;
    	
    	if(nums[mid] == target) return mid;
    	
    	else if(target < nums[mid])
    	{
    		if(mid - 1 >= 0 && target > nums[mid - 1]) return mid;
    		return binarySearchIndex(nums, target, start, mid - 1);
    	}
    	
    	if(target > nums[mid] && mid + 1 <= nums.length - 1 && target < nums[mid + 1]) {
    		return mid + 1;
    	}

    	return binarySearchIndex(nums, target,  mid + 1, end);
    }
    
    //https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        
        int left = 0, right = nums.length - 1;
        
        while(left < right && nums[left] >= nums[right])
        {
        	int mid = (left + right) / 2;
        	
        	if(nums[mid] > nums[right])
        	{
        		left = mid + 1; 
        	}
        	else
        	{
        		// right = mid is important instead of right = mid - 1 to make sure it doesn't skip the mid number
        		right = mid;
        	}
        }
        
        return nums[left];
    }

    
    //https://leetcode.com/problems/search-in-rotated-sorted-array/
    //no duplicates exist
    private int searchRotated(int[] nums, int left, int right, int x)
    {
    	if(nums == null || nums.length == 0) return -1;
    	
    	if(right < left) return -1;

    	/* Skipping the duplicates when duplicates exist to solve another variation of the same problem
    //https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
    //duplicates do exist
    //[3,1,1], 3
    	// skip duplicates from the left
    while (left < right && nums[left] == nums[left + 1]) left++; 
    
    // skip duplicates from the right
    while (right > left && nums[right] == nums[right - 1]) right--; 
    	*/
    	int mid = (left + right) / 2;
    	
    	if(nums[mid] == x) return mid;
    	
    	//case - 1: left is normally ordered
    	if(nums[left] < nums[mid])
    	{
    		if(x >= nums[left] && x < nums[mid])
    		{
    			return searchRotated(nums, left, mid - 1, x);
    		}
    		else
    		{
    			return searchRotated(nums, mid + 1, right, x);
    		}
    	}
    	//case - 2: right is normally ordered
    	else if(nums[mid] < nums[right])
    	{
    		if(x > nums[mid] && x <= nums[right])
    		{
    			return searchRotated(nums, mid + 1, right, x);
    		}
    		else
    		{
    			return searchRotated(nums, left, mid - 1, x);
    		}
    	}
    	//case - 3: left/right half is same number
    	else if(nums[left] == nums[mid])
    	{
    		// right half is not same so search in that half
    		if(nums[mid] != nums[right])
    		{
    			return searchRotated(nums, mid + 1, right, x);
    		}
    		// worst case: need to search in both half
    		else
    		{
    			int result = searchRotated(nums, left, mid - 1, x);
    			
    			if(result == -1)
    			{
    				return searchRotated(nums, mid + 1, right, x);
    			}
    			else
    			{
    				return result;
    			}    			
    		}
    	}
    	
    	return -1;
    }

    //Cracking 10.5 searchSparse(String[] strings, int left, int right, String needle)    
   public int searchSparse(String[] strings, int left, int right, String needle)
   {
	   if(left > right) return -1;
	   
	   int mid = (left + right)/2;
	   
	   if(strings[mid].equals(needle)) return mid;
	   
	   int midLeft = mid - 1;
	   int midRight = mid + 1;
	   
	   // this stopping logic is important
	   while(midLeft > left && midRight < right)
	   {
		   if(!strings[midLeft].equals(""))
		   {
			   mid = midLeft;
			   break;
		   }

		   if(!strings[midRight].equals(""))
		   {
			   mid = midRight;
			   break;
		   }
		   
		   midLeft--;
		   midRight++;
	   }
	   
	   // compareTo returns negative if this string is less than the argument string
	   if(strings[mid].compareTo(needle) < 0)
	   {
		   // so for compareTo negative result it searches the lower half
		   return searchSparse(strings, left, mid, needle);
	   }
	   
	   return searchSparse(strings, mid, right, needle);
   }
   
    //Cracking 10.8 findDuplicates TODO
   // You have an array with all the numbers from 1toN, where N is at most 32,000.
   // The array may have duplicate entries and you do not know what N is. With only 4 kilobytes of memory available, how would you print all duplicate elements in the array?
   
   
    //Cracking 10.9 sortedMatrixSearch
   public int findElement(int[][] matrix, int element)
   {
	   int row = findRow(matrix, 0, matrix.length, element);
	   
	   if(row == -1) return row;
	   
	   return binarySearchIndex(matrix[row], 0, matrix[row].length, element);	   
   }
   
   private int findRow(int[][] matrix, int start, int end, int element)
   {
	   if(start > end) return -1;
	   int mid = (start + end)/2;
	   
	   if(mid < end && element >= matrix[mid][0] && element <= matrix[mid + 1][0])
	   {
		   return mid;
	   }
	   else if(element < matrix[mid][0] && element >= matrix[start][0])
	   {
		   return findRow(matrix, start, mid, element);
	   }
	   else if(element > matrix[mid + 1][0] && element <= matrix[end][0])
	   {
		   return findRow(matrix, mid + 1, end, element);	   		   
	   }
	   
	   return -1;
   }
   
   //similar to the above one but iterative solution
   public boolean binSearchMatrix(int[][] matrix, int target) {
	     if(matrix == null || matrix.length == 0) return false;
	     
	     int colLeft = 0, colRight = matrix[0].length;
	     int targetCol = -1;
	     
	     // find the right column
	     while(colLeft < colRight)
	     {
	    	 	int colMid = (colLeft + colRight)/2;
	    	 	
	    	 	// found the number
	    	 	if(target == matrix[0][colMid]) return true;
	    	 	
	    	 	// found the right column
	    	 	if(target > matrix[0][colMid] && target < matrix[0][colRight])
	    	 	{
	    	 		targetCol = colMid;
	    	 		break;
	    	 	}
	    	 	
	    	 	if(target < matrix[0][colMid])
	    	 	{
	    	 		colRight = colMid;
	    	 	}
	    	 	else
	    	 	{
	    	 		colLeft = colMid;
	    	 	}
	     }
	     
	     int rowStart = 0, rowEnd = matrix.length;
	     
	     while(rowStart < rowEnd)
	     {
	    	 	int rowMid = (rowStart + rowEnd)/2;
	    	 	
	    	 	if(matrix[rowMid][targetCol] == target) return true;
	    	 	
	    	 	if(target < matrix[rowMid][targetCol])
	    	 	{
	    	 		rowEnd = rowMid - 1;
	    	 	}
	    	 	else
	    	 	{
	    	 		rowStart = rowMid + 1;
	    	 	}
	     }
	     
	     return true;
	    }
   
   //https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
   // runtime = O(n2)
   public int kthSmallest(int[][] matrix, int k) {
	   if(matrix == null || matrix.length == 0 || matrix.length != matrix[0].length) return 0;
	   
	   // by default PQ contains only the largest elements
	   PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	   
	   // since we need to find k-th smallest element will be ks-th largest element
	   // for a 3*3 matrix 4th smallest element is 3 * 3 - 4 + 1 = 6th largest element 
	   int ks = matrix.length * matrix[0].length  - k + 1;

	   for(int i = 0; i < matrix.length; i++)
		   for(int j = 0; j < matrix[0].length; j++)
		   {
			   pq.offer(matrix[i][j]);
			   
			   // if the pq size becomes more than ks then remove one element to keep only ks-th largest element
			   if(pq.size() > ks) pq.poll();
		   }
	   
	   // return ks-th largest/ k-th smallest element
	   return pq.poll();        
   }

   //https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
   //https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/
   //Approach 2: Binary Search
   // logic: 
   // use a variant of binary search 
   // to find the leftMost index we keep on searching in the left half as long as A[mid] == target 
   // we return lo (mid + 1) when the binary search no longer matches and we need the leftMost index   
   // finding the rightmost index is like regular binary search except that we do nothing for A[mid] == target
   //TODO
//   public int[] searchRange(int[] nums, int target) {
//       
//   }

   //Cracking 10.10 rankStreams TODO
   //Cracking 10.11 peaksValleys TODO
}
