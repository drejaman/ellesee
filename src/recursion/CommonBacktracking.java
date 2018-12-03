package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonBacktracking {
	//https://leetcode.com/problems/permutations
	//TODO
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutedNumbers = new ArrayList<List<Integer>>();
        return permutedNumbers;
    }
    
    // https://leetcode.com/problems/combination-sum-iii/
    // this solution is very important as this will solve the backtracking recursive solutions
    // recursive backtracking solutions are like this where you need to come up a list of possible solutions
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> combinations = new ArrayList<List<Integer>>();
        
        SingleCombination(k, n, 1, combinations, new ArrayList<Integer>());
        return combinations;
    }
    
    private void SingleCombination(int k, int remainderSum, int start, List<List<Integer>> combinations, List<Integer> combination)
    {
    	
    	if(remainderSum == 0 && combination.size() == k)
    	{
        	List<Integer> comb = new ArrayList<Integer>(combination);
    		combinations.add(comb);
    		return;// return as one path has been added completely fulfilling the conditions
    	}
    	
    	for(int i = start; i <= 9; i++)
    	{
			combination.add(i);
			SingleCombination(k, remainderSum - i, i + 1, combinations, combination);
    		combination.remove(combination.size() - 1);
    	}
    }
    
    // https://leetcode.com/problems/subsets/
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> sets = new ArrayList<List<Integer>>();
        
        miniSubset(sets, new ArrayList<Integer>(), nums, 0);
        return sets;
    }
    
    private void miniSubset(List<List<Integer>> sets, List<Integer> set, int []nums, int startIndex)
    {
    	// in the subset case we always add the new set that came in
    	sets.add(new ArrayList<Integer>(set));
    	
    	for(int i = startIndex; i < nums.length; i++)
    	{
    		set.add(nums[i]);
    		//add the next possible number
    		miniSubset(sets, set, nums, i + 1);
    		//backtrack
    		set.remove(set.size() - 1);
    	}
    }

    // https://leetcode.com/problems/combination-sum/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	List<List<Integer>> combinations = new ArrayList<List<Integer>>();
    	
    	SingleCombination(combinations, new ArrayList<Integer>(), candidates, target, 0);
    	return combinations;
    }
    
    private void SingleCombination(List<List<Integer>> combinations, List<Integer> combination, int[] candidates, int target, int start)
    {
    	if(target < 0) return;
    	
    	if(target == 0)
    	{
    		List<Integer> comb = new ArrayList<Integer>();
    		comb.addAll(combination);
    		combinations.add(comb);
    		return;
    	}
    	
    	// i is for the index in the array
    	for(int i = start; i < candidates.length; i++)
    	{
    		combination.add(candidates[i]);
    		// since we can re use the same number when we call recursively we don't increase the start (index)
    		SingleCombination(combinations, combination, candidates, target - candidates[i], i);
    		// we backtrack when a combination doesn't yield a result set
    		combination.remove(combination.size() - 1);
    	}
    }
    
    // https://leetcode.com/problems/combination-sum-ii/
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        singleCombination(lists, new ArrayList<Integer>(), candidates, target, 0);
        return lists;
    }

    public void singleCombination(List<List<Integer>> combinations, List<Integer> combination, int[] candidates, int target, int startIndex)
    {
    	if(target == 0)
    	{
    		combinations.add(new ArrayList<Integer>(combination));
    		return;
    	}
    	
    	for(int i = startIndex; i < candidates.length; i++)
    	{
    		// this line is crucial to make sure the same combination does not get added to the lists 
    		// even though there are same duplicate number
    		// Input: [10,1,2,7,6,1,5], 8
    		// Output without the line below: [[1,1,6],[1,2,5],[1,7],[1,2,5],[1,7],[2,6]] so there are some repeats
    		// Output with the line below: [[1,1,6],[1,2,5],[1,7],[2,6]]
            if (i > startIndex && candidates[i] == candidates[i-1]) continue;

    		combination.add(candidates[i]);
    		singleCombination(combinations, combination, candidates, target - candidates[i], i);
    		combination.remove(combination.size() - 1);
    	}
    }
    
    //notworking
    // https://leetcode.com/problems/3sum/
    public List<List<Integer>> anySum(int[] nums) {
    	List<List<Integer>> lists = new ArrayList<List<Integer>>();
    	
    	Arrays.sort(nums);
    	int noOfIntsToMakeSum = 4;
    	singleSum(lists, new ArrayList<Integer>(), noOfIntsToMakeSum, nums, 0, 0, 0);
    	
    	return lists;
    }

    public List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> lists = new ArrayList<List<Integer>>();
    	
    	Arrays.sort(nums);
    	singleSum(lists, new ArrayList<Integer>(), 3, nums, 0, 0, 0);
    	
    	return lists;
    }
    
    public void singleSum(List<List<Integer>> lists, List<Integer> list, int listLen, int[] nums, int target, int currentSum, int startIndex)
    {
    	if(startIndex != 0 && currentSum == target && list.size() == listLen )
    	{
    		lists.add(new ArrayList<Integer>(list));
    		return;
    	}
    	
    	for(int i = startIndex; i < nums.length; i++)
    	{
    		// this line is crucial to make sure the same combination does not get added to the lists 
    		// even though there are same duplicate number
    		// Input: [-1,0,1,2,-1,-4]
    		// Output without the line below: [[-1,-1,2],[-1,0,1],[-1,0,1]] so there are some repeats
    		// Output with the line below: [[-1,-1,2],[-1,0,1]]
            if(i > startIndex && nums[i] == nums[i-1]) continue;

    		list.add(nums[i]);
    		singleSum(lists, list, listLen, nums, target, currentSum + nums[i], i + 1);
    		list.remove(list.size() - 1);
    	}    	
    }
    
    //notworking
    //https://leetcode.com/problems/minimum-size-subarray-sum/
    public int minSubArrayLen(int s, int[] nums) {
        Arrays.sort(nums);        
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        int maxLen = 0;
        
        singleSubArray(lists, new ArrayList<Integer>(), nums, s, 0, maxLen, 0);
        
        return maxLen;
    }

    private void singleSubArray(List<List<Integer>> lists, List<Integer> list, int[] nums, int sum, int startIndex, int maxLen, int len)
    {
    	if(sum == 0 && len > 0)
    	{
    		maxLen = len;
    		lists.add(new ArrayList<Integer>(list));
    		return;
    	}
    	
    	if(maxLen == 0)
    	for(int i = startIndex; i >= 0; i--)
    	{
    		list.add(nums[i]);
    		singleSubArray(lists, list, nums, sum - nums[i], i - 1, maxLen, list.size());
    		list.remove(list.size() - 1);
    	}
    }
    
    //https://leetcode.com/problems/palindrome-partitioning/
    //TODO
//    public List<List<String>> partition(String s) {        
//    }    
}
