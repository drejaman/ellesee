package hash;

import java.util.HashMap;
import java.util.HashSet;

public class TestHashMap {  
	
    //https://leetcode.com/problems/find-the-duplicate-number/
    public int findDuplicate(int[] nums) {
	    	HashSet<Integer> tracker = new HashSet<Integer>();
	    	
	    	for(int i: nums)
	    	{
	    		if(tracker.contains(i))
	    		{
	    			return i;
	    		}
	    		else
	    		{
	    			tracker.add(i);
	    		}
	    	}
	    	
	    	return -1;
    }
    
    //https://leetcode.com/problems/two-sum/solution/
    //Given an array of integers, return indices 
    //of the two numbers such that they add up to a specific target.
    //top100
    public int[] twoSum(int[] nums, int target) {
    		int[] result = new int[2];
    		
    		if(nums == null || nums.length == 0) return result;
    		
    		HashMap<Integer, Integer> tracker = new HashMap<Integer, Integer>();
    		
    		for(int i = 0; i < nums.length ; i++)
    		{
    			if(tracker.containsKey(target - nums[i]))
    			{
    				result[0] = tracker.get(target - nums[i]);
    				result[1] = i;
    				return result;
    			}
    			else
    			{
    				tracker.put(nums[i], i);
    			}
    		}
    		
    		return result;
    }
}
