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
    
    // trick: the key is 1 <= a[i] <= n (n = size of array) 
    // The concept here is to negate the number at array[number - 1] 
    // Once a value is negated, if it requires to be negated again then it is a duplicate
    // because the number is duplicate and we are visiting the same index twice
    // so add that number as duplicate
    // O(N) time and O(1) space
	//    Input
	//    [4,3,2,7,8,2,1,3]
	//    [0,1,2,3,4,5,6,7]
	//
	//    Output:
	//    [2,3]
	//
	//    Simulation
	//    4, nums[3] = -7
	//    3, nums[2] = -2
	//    2, nums[1] = -3
	//    7, nums[6] = -1
	//    8, nums[7] = -3
	//    2, nums[1] = -3, 3
	//    1, nums[0] = -4, 
	//    3, nums[2] = -2, 2
    public List<Integer> findDuplicates(int[] nums) {
    		List<Integer> result = new ArrayList<Integer>();
    		
    		if(nums == null || nums.length == 0) return result;
    		
    		for(int num : nums)
    		{
    			int index = Math.abs(num) - 1;
    			
    			if(nums[index] > 0) nums[index] = -nums[index];
    			
    			// otherwise it was already negated once
    			// so num is duplicate and so index = num - 1 is visited twice
    			else
    			{
    				result.add(Math.abs(num));
    			}
    		}
    		
    		return result;
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
        
        //check the first if this is the peak
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

        //check the last if the last is peak
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

    // https://leetcode.com/problems/maximum-swap/
    // find the max digit and min digit before max and swap them
    // need to get this working for 98368
    // input: 98368
    // L[digit] last[9] = 0, last[8] = 4, last[6] = 3, last[3] = 2
    // Last[digit] > i
    // O(N) runtime actually O(kn) as k = 10 constant making it O(N), O(1) space
    public int maximumSwap(int num) {
    		if(num == 0) return num;
    		
    		char[] numChars = Integer.toString(num).toCharArray();
    		
    		if(numChars.length == 1) return num;
    		
    		int[] last = new int[10];
    		
    		//find the last (lowest value for that digit) index of each digit (1 - 9)
    		for(int i = 0; i < numChars.length; i++)
    		{
    			last[numChars[i] - '0'] = i;
    		}

    		//start with left for the number
    		//first lower value than higher value to be replaced
    		for(int i = 0; i < numChars.length; i++)
    		{
        		//start with the highest digit 9
    			//and go up to the current value at index i
    			for(int digit = 9; digit > numChars[i] - '0'; digit--)
    			{
    				//found a digit higher than current digit but at lower value index
    				//so make the swap bumping up the current number
    				if(last[digit] > i)
    				{
    					char temp = numChars[last[digit]];
    					numChars[last[digit]] = numChars[i];
    					numChars[i] = temp;
    					
    					//we only need one swap so return
    					return Integer.valueOf(new String(numChars));
    				}
    			}
    		}
    		
    		return num;
    }
    
    //https://leetcode.com/problems/powx-n
    public double myPow(double x, int n) {
        if(n == 0) return 1.0;
        
        //special case to handle overflow problem like 2.00000, -2147483648
        if(n == Integer.MIN_VALUE){
            return myPow(x*x, n/2);
        }

        //if the power (n) is negative then we need to change x into fraction
        if(n < 0)
        {
	        	n = -n;
	        	x = 1/x;
        }
        
        return n % 2 == 0 ? myPow(x * x, n/2) : x * myPow(x * x, n/2);        
    }
}