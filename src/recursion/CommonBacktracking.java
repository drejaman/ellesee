package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonBacktracking {
	//https://leetcode.com/problems/permutations
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutedNumbers = new ArrayList<List<Integer>>();
        permuteArray(nums, permutedNumbers, new ArrayList<Integer>());
        return permutedNumbers;
    }
    
    //trick: for every call to this the for loop will start from i = 0 to array length
    //the check is when adding to currentList if that character/number already exists
    private void permuteArray(int[] nums, List<List<Integer>> lists, List<Integer> list)
    {
		if(nums.length == list.size() && !lists.contains(list))
		{
			lists.add(new ArrayList<Integer>(list));
			return;
		}
		
		for(int i = 0; i < nums.length; i++)
		{
			//making sure we are not adding duplicates
			if(list.contains(nums[i])) continue;
			
			list.add(nums[i]);
			permuteArray(nums, lists, list);
			list.remove(list.size() - 1);
		}
    }

    //lastnight
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> permutedNumbers = new ArrayList<List<Integer>>();
        
        if(nums == null || nums.length == 0) return permutedNumbers;
        
        // the visit portion is different than the regular permute where all numbers are unique
        // as there might be duplicate we won't know for sure which number is already used 
        // so we need to keep track using visit array
        // we also need to sort the array to keep track of position instead of number 
        // as there are duplicate numbers 
        Arrays.sort(nums);
        permuteVisit(nums, permutedNumbers, new ArrayList<Integer>(), new boolean[nums.length]);
        return permutedNumbers;
    }
    
    //trick: similar to previous to call again to this with a for loop starting at i = 0
    //difference is this one keeps track of index and a visit array to check 
    //if that is already added to current list
    private void permuteVisit(int[] nums, List<List<Integer>> lists, List<Integer> list, boolean[] visit)
    {
		if(nums.length == list.size() && !lists.contains(list))
		{
			lists.add(new ArrayList<Integer>(list));
			return;
		}
		
		for(int i = 0; i < nums.length; i++)
		{
			if(visit[i]) continue;
			//trick: when a number has the same value with its previous, 
			// we can use this number only if previous is used
			if(i > 0 && nums[i-1] == nums[i] && !visit[i-1]) continue;
			
			//trick: no list.contains() check
			list.add(nums[i]);
			visit[i] = true;
 			
			permuteVisit(nums, lists, list, visit);
			
			//backtrack
			visit[i] = false;
			list.remove(list.size() - 1);
		}
    }

    // https://leetcode.com/problems/subsets/
    // https://leetcode.com/problems/subsets-ii/ (input might contain duplicates but not the result)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> sets = new ArrayList<List<Integer>>();
        
        //required only for subset-II
        Arrays.sort(nums);
        miniSubset(sets, new ArrayList<Integer>(), nums, 0);
        return sets;
    }
    
    //trick: call with the increased index
    //add every set to the final result
    private void miniSubset(List<List<Integer>> sets, List<Integer> set, int []nums, int startIndex)
    {
    	// in the subset case we always add the new set that came in
    // if check is required only for subset-II
    	// (input might contain duplicates but not the result)
//    	if(!sets.contains(set))
//    	{
        	sets.add(new ArrayList<Integer>(set));    		
//    	}
    	
	    	for(int i = startIndex; i < nums.length; i++)
	    	{
	    		set.add(nums[i]);
	    		//add the next possible number
	    		miniSubset(sets, set, nums, i + 1);
	    		//backtrack
	    		set.remove(set.size() - 1);
	    	}
    }

    //https://leetcode.com/problems/subsets-ii/
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> sets = new ArrayList<List<Integer>>();                     Arrays.sort(nums);

        miniSubsetDup(sets, new ArrayList<Integer>(), nums, 0);
        return sets;
    }
    
    //trick: before adding another set check 
    //if it already exists in result set
    private void miniSubsetDup(List<List<Integer>> sets, List<Integer> set, int []nums, int startIndex)
    {
    	// in the subset case we always add the new set that came in
    	if(!sets.contains(set))
    	{
        	sets.add(new ArrayList<Integer>(set));    		
    	}
    	
    	for(int i = startIndex; i < nums.length; i++)
    	{
    		set.add(nums[i]);
    		//add the next possible number
    		miniSubsetDup(sets, set, nums, i + 1);
    		//backtrack
    		set.remove(set.size() - 1);
    	}
    }
    
    // https://leetcode.com/problems/combination-sum/
    //trick: for loop starts with currentIndex
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
	    	List<List<Integer>> combinations = new ArrayList<List<Integer>>();
	    	
	    	SingleCombination(combinations, new ArrayList<Integer>(), candidates, target, 0);
	    	return combinations;
    }
    
    private void SingleCombination(List<List<Integer>> combinations, List<Integer> combination, 
    		int[] candidates, int target, int start)
    {
	    	if(target < 0) return;
	    	
	    	if(target == 0)
	    	{
	    		combinations.add(new ArrayList<Integer>(combination));
	    		return;
	    	}
	    	
	    	// i is for the index in the array
	    	for(int i = start; i < candidates.length; i++)
	    	{
	    		combination.add(candidates[i]);
	    		//trick: since we can re use the same number when we call recursively 
	    		//we don't increase the start (index)
	    		SingleCombination(combinations, combination, candidates, target - candidates[i], i);
	    		// we backtrack when a combination doesn't yield a result set
	    		combination.remove(combination.size() - 1);
	    	}
    }
    
    // https://leetcode.com/problems/combination-sum-ii/
    // variation with i: each number can be used only once
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        //the line below is different than sum-I
        Arrays.sort(candidates);
        singleCombination(lists, new ArrayList<Integer>(), candidates, target, 0);
        return lists;
    }

    private void singleCombination(List<List<Integer>> combinations, List<Integer> combination, 
    		int[] candidates, int target, int startIndex)
    {
	    	if(target == 0)
	    	{
	    		combinations.add(new ArrayList<Integer>(combination));
	    		return;
	    	}
    	
	    	for(int i = startIndex; i < candidates.length; i++)
	    	{
	    		//trick: this line is crucial to make sure the same combination does not get added to the lists 
	    		// even though there are same duplicate number
	    		// Input: [10,1,2,7,6,1,5], 8
	    		// Output without the line below: [[1,1,6],[1,2,5],[1,7],[1,2,5],[1,7],[2,6]] 
	    		// so there are some repeats
	    		// Output with the line below: [[1,1,6],[1,2,5],[1,7],[2,6]]
		        if (i > startIndex && candidates[i] == candidates[i-1]) continue;
	
	    		combination.add(candidates[i]);
	    		singleCombination(combinations, combination, candidates, target - candidates[i], i);
	    		combination.remove(combination.size() - 1);
	    	}
    }
    
    // https://leetcode.com/problems/combination-sum-iii/
    //Find all possible combinations of k numbers that add up to a number n, 
    //given that only numbers from 1 to 9 can be used and 
    //each combination should be a unique set of numbers.
    //Input: k = 3, n = 9
	//Output: [[1,2,6], [1,3,5], [2,3,4]]
    // recursive backtracking solutions are like this where you need to come up a list of possible solutions
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> combinations = new ArrayList<List<Integer>>();
        
        SingleCombination(k, n, 1, combinations, new ArrayList<Integer>());
        return combinations;
    }
    
    private void SingleCombination(int k, int remainderSum, int start, List<List<Integer>> combinations, 
    		List<Integer> combination)
    {
    	
	    	if(remainderSum == 0 && combination.size() == k)
	    	{
	    		combinations.add(new ArrayList<Integer>(combination));
	    		return;// return as one path has been added completely fulfilling the conditions
	    	}
	    	
	    	//trick: using i + 1 in the recursive call makes sure we are using 
	    	for(int i = start; i <= 9; i++)
	    	{
				combination.add(i);
				SingleCombination(k, remainderSum - i, i + 1, combinations, combination);
	    		combination.remove(combination.size() - 1);
	    	}
    }
    
    //https://leetcode.com/problems/combinations/
//    Input: n = 4, k = 2
//    		Output:
//    		[
//    		  [2,4],
//    		  [3,4],
//    		  [2,3],
//    		  [1,2],
//    		  [1,3],
//    		  [1,4],
//    		]
    //trick:similar to combination sum
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations= new ArrayList<List<Integer>>();
        combine(combinations, new ArrayList<Integer>(), 1, n, k);
        return combinations;
    }
    
    private void combine(List<List<Integer>> lists, List<Integer> list, int start, int n, int k) {
		if(k == 0) {
			lists.add(new ArrayList<Integer>(list));
			return;
		}
		
		// here it spans like trees. it goes on a look and calls combine i times
		// so for i = 1, it spans n combine calls. for i = 2 it calls n-1 combine
		// so the total call is n!
		for(int i = start; i <= n; i++) {
			list.add(i);
			combine(lists, list, i+1, n, k-1);
			list.remove(list.size()-1);
		}
	}
    
    //https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    //not exactly backtracking more like recursive
    //lastnight
    private String[] map = 
    		new String[] {" ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    
    public List<String> letterCombinations(String digits) {    		
		List<String> words = new ArrayList<String>();
		
        if(digits == null || digits.isEmpty() || digits.length() == 0)
        {
        		return words;
        }
        
        letterCombinations(digits, 0, words, "");
        
        return words;
    }
    
    private void letterCombinations(String digits, int currentIndex, List<String> words, String word)
    {
		if(currentIndex >= digits.length())
		{
			words.add(new String(word));
			return;
		}
		
		int currentDigit = digits.charAt(currentIndex) - '0';
		
		for(char ch : map[currentDigit].toCharArray())
		{
			letterCombinations(digits, currentIndex + 1, words, word + ch);    				
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
	    	
	    	if(maxLen == 0)     	for(int i = startIndex; i >= 0; i--)
	    	{
	    		list.add(nums[i]);
	    		singleSubArray(lists, list, nums, sum - nums[i], i - 1, maxLen, list.size());
	    		list.remove(list.size() - 1);
	    	}
    }
    
    //https://leetcode.com/problems/palindrome-partitioning/
    //TODO
//    public list<list<string>> partition(string s) {        
//    }    
}