package binsearch;

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
        
        int L = 0, R = nums.length - 1;
        
        while(L < R && nums[L] >= nums[R])
        {
        	int M = (L + R) / 2;
        	
        	if(nums[M] > nums[R])
        	{
        		L = M + 1; 
        	}
        	else
        	{
        		// R = M is important instead of R = M - 1 to make sure it doesn't skip the mid number
        		R = M;
        	}
        }
        
        return nums[L];
    }
}
