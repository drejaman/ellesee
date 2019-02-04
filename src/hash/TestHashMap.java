package hash;

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
}
