package hash;

import java.util.HashSet;
import java.util.List;

public class TestHashMap {
	
	//https://leetcode.com/problems/top-k-frequent-elements/
	/*
	 * Input: nums = [1,1,1,2,2,3], k = 2
	   Output: [1,2]
	 * */
	//TODO
//    public List<Integer> topKFrequent(int[] nums, int k) {
//        
//    }
    
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
