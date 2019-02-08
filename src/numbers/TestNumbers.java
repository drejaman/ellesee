package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestNumbers {

	public void test()
	{
		for(int i: selfDividingNumbers(151, 191))
		{
			System.out.println(i);
		}
	}

	//https://leetcode.com/problems/self-dividing-numbers/description/		
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> selfDividingNumbers = new ArrayList<Integer>();
        
        for(int i = left ; i <= right; i++)
        {
    		if(isSelfDividingNumber(i))
    		{
    			selfDividingNumbers.add(i);
    		}
        }
        
        return selfDividingNumbers;
    }
    
    private boolean isSelfDividingNumber(int number)
    {
		boolean IsSelfDivided = true;
		int numberToTest = number;
		
		ArrayList<Integer> digits = new ArrayList<Integer>();
		
		while(numberToTest > 0)
		{
			int currentDigit = numberToTest % 10;
			
			// A self dividing number is not allowed to have 0
			// strip the digits of the number
			// like for number 54232 add 2, 3, 4, 5
			if(currentDigit == 0) return false;
			
			if(!digits.contains(currentDigit))
			{
				digits.add(currentDigit);
			}
			
			numberToTest /= 10;
		}
		
		if(digits.size() > 0)
		{
			for(int i: digits)
			{
				if((number % i) != 0)
				{
					IsSelfDivided = false;
					break;
				}
			}
		}
		
		return IsSelfDivided;
    }
    
    // https://leetcode.com/problems/judge-route-circle/description/
    // removed
    public boolean judgeCircle(String moves) {
	 //initial position 0,0
	 int verticalSum = 0;
     int horizontalSum = 0;
     
     char[] moveSequence = moves.toUpperCase().toCharArray();
     
     for(char ch: moveSequence)
     {
    	 	switch(ch) {
    	 		case 'U':
    	 			verticalSum++;
    	 			break;
    	 		case 'D':
    	 			verticalSum--;
    	 			break;
    	 		case 'R':
    	 			horizontalSum++;
    	 			break;
    	 		case 'L':
    	 			horizontalSum--;
    	 			break;
    	 		default:
    	 			break;
    	 	}
     }
     
     // we have to make sure after all the moves it comes back to initial position 0,0
     return verticalSum == 0 && horizontalSum == 0;
    }
    
    // https://leetcode.com/problems/find-all-duplicates-in-an-array/description/
    // O(n) time but O(n) extra space.
    // Fine tuning need to be done to solve using no extra space
    // Time Limit Exceeded
    // 25/28 test cases passed
    //TODO
    public List<Integer> findDuplicates(int[] nums) {
    	// If the ArrayList comparison doesn't work then use hashmap or hashtable
    	ArrayList<Integer> nonDuplicates = new ArrayList<Integer>();    	
    	ArrayList<Integer> duplicates = new ArrayList<Integer>();
    	
    	for(int current: nums)
    	{
    		if(nonDuplicates.contains(current) && !duplicates.contains(current))
    		{
    			duplicates.add(current);
    		}
    		else
    		{
    			nonDuplicates.add(current);
    		}
    	}
    	
    	return duplicates;
    }
    
    // https://leetcode.com/problems/array-partition-i/
    public int arrayPairSum(int[] nums) {
    	int len = nums.length;
    	int sum = 0;
    	
    	// sort the array with built in algorithm using O(nlogn) time
    	Arrays.sort(nums);
    	
    	for(int i = 0; i < len; i++)
    	{
    		// the first number of the set will be added only as we would pick min (a1, b1)
    		if(i % 2 == 0) sum += nums[i];
    	}
    	
    	return sum;
    }
    
    // https://leetcode.com/problems/monotonic-array/
    public boolean isMonotonic(int[] A) {
    	// the tricky part is to determine whether it is increasing or decreasing
    	// specially for the cases like [2,2,2,3] or [2,2,2,1]
        int i = 1;
        while(i < A.length && A[i] - A[i-1] == 0) i++;            
        boolean increasing = (i < A.length && (A[i] - A[i-1]) >= 0) ? true : false;
        
        if(increasing)
        {
        	for( ; i < A.length; i++)
        	{
        		if(A[i] - A[i-1] >= 0) continue;
        		return false;
        	}        	
        }
        else
        {
        	for(; i < A.length; i++)
        	{
        		if(A[i] - A[i-1] <= 0) continue;
        		return false;
        	}        	        	
        }
        
        return true;
    }

	//https://leetcode.com/problems/find-peak-element/
    public int findPeakElement(int[] nums) {
        if(nums == null || nums.length == 0) return -1;

    	int len = nums.length;
        int maxIndex = 0;
        int maxPeak = 0;
        
        if(len == 1) return 0;
        
        if(nums[0] > nums[1]) 
        {
        	maxIndex = 0;
        	maxPeak = nums[0];
        }
        
        for(int i = 1; i < len - 1; i++)
        {
        	if(nums[i] > nums[i-1] && nums[i] > nums[i+1] && nums[i] > maxPeak)
        	{
            	maxIndex = i;
            	maxPeak = nums[i];        		
        	}
        }

        if(nums[len - 1] > nums[len - 2]) 
        {
        	maxIndex = len - 1;
        	maxPeak = nums[len - 1];
        }

        return maxIndex;
    }

    //https://leetcode.com/problems/peak-index-in-a-mountain-array/
    public int peakIndexInMountainArray(int[] A) {
        if(A == null || A.length == 0) return -1;
        
        if(A.length == 1) return 0;
        
        // for mountain pattern A[i] > A[i-1] has to hold first
        boolean increasing = true;
        int peakIndex = - 1;
        
        for(int i = 1; i < A.length; i++)
        {
        	if(increasing)
        	{
        		if(A[i] > A[i-1]) continue;
        		else//we already arrived at peak and now should decreasing
        		{
        			increasing = false;
        			//so the previous index was peak
        			peakIndex = i - 1;
        		}   
        	}
        	//or it is going downhill and A[i] has to be less thans A[i-1]
        	else if(!increasing && A[i] < A[i-1]) continue;
        	//there could multiple peaks so find the next one
        	//going uphill again
        	else increasing = true;
        }
        
        // the following logic handles the case like 0, 1, 2, 3, 4, 5
        if(increasing && peakIndex == -1) peakIndex = A.length - 1;
        
        return peakIndex;
    }

    //notworking
    // https://leetcode.com/problems/maximum-swap/
    // find the max digit and min digit before max and swap them
    // need to get this working for 98368
    public int maximumSwap(int num) {
       char[] numChars = Integer.toString(num).toCharArray();
       
       int max = Integer.MIN_VALUE;
       int maxIndex = -1;
       int min = Integer.MAX_VALUE;
       int minIndex = -1;
       
       for(int i = 0; i < numChars.length; i++)
       {
    	
    	int numChar = numChars[i] - '0';
    	   
    	if(numChar >= max)
    	{
    		max = numChar;
    		maxIndex = i;    		   
    	}
    	
    	if(numChar < min && min < max)
    	{
    		min = numChar;
    		minIndex = i;
    	}    	
       }
       
       if(minIndex < maxIndex)
       {
    	   char temp = numChars[minIndex];
    	   numChars[minIndex] = numChars[maxIndex];
    	   numChars[maxIndex] = temp;
       }
       
       return Integer.parseInt(new String(numChars));
    }
    
    //notworking
    //https://leetcode.com/problems/perfect-squares/
    public int numSquares(int n) {
        int[] squares = {1, 4, 9, 16, 25, 36, 49, 64, 100};
        
        List<Integer> finalResult = new ArrayList<Integer>();
        findWays(n, squares, 0, finalResult, new ArrayList<Integer>());
        
        return (finalResult.size() > 0) ? finalResult.size() : -1;
    }
    
    private void findWays(int n, int[] squares, int index, List<Integer> finalResult, List<Integer> currentCombination)
    {
    	if(n == 0)
    	{
    		finalResult.addAll(currentCombination);
    		return;
    	}
    	
    	if(n > 0)
    	{
    		int currentNumber = squares[index];
    		currentCombination.add(currentNumber);
    		findWays(n - currentNumber, squares, index, finalResult, currentCombination);
    		findWays(n - currentNumber, squares, index + 1, finalResult, currentCombination);
    	}
    }

    //notworking for n = IntMax or n = IntMin
    //https://leetcode.com/problems/powx-n
    public double myPow(double x, int n) {
    	long nl = n;
    	
    	return myPowD(x, nl);
    }
    
    private double myPowD(double x, double n)
    {
        if(n == 0) return 1.0;
        
        if(n < 0)
        {
        	n = -n;
        	x = 1/x;
        }
        
        return n % 2 == 0 ? myPowD(x * x, n/2) : x * myPowD(x * x, n/2);    	
    }
}