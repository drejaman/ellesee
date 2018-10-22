package numbers;

import java.util.ArrayList;
import java.util.List;

public class TestNumbers {

	public void test()
	{
		//Test https://leetcode.com/problems/self-dividing-numbers/description/		
		for(int i: selfDividingNumbers(151, 191))
		{
			System.out.println(i);
		}
	}

	//Solution for https://leetcode.com/problems/self-dividing-numbers/description/		
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
    			int currentNumber = numberToTest % 10;
    			
    			// A self dividing number is not allowed to have 0
    			if(currentNumber == 0) return false;
    			
    			if(!digits.contains(currentNumber))
    			{
    				digits.add(currentNumber);
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
    
    // notworking for special cases
    // https://leetcode.com/problems/divide-two-integers/description/
    // corner case -2147483648, -1 as -2147483648 is the INT_MIN but 2147483648 is not INT_MAX (2147483647)
    public int divide(int dividend, int divisor) {
    	
    		// handles division by zero
    		if(divisor == 0) return -1;
    		
    		// checks the sign that will be later used by quotient
        boolean sign = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        
        
        int quotient = 0;
        
        if(divisor < dividend)
        {
          dividend = dividend > 0 ? dividend : -dividend;
          divisor = divisor > 0 ? divisor : -divisor;
            
	        	while(divisor <= dividend)
	            {
	            		dividend -= divisor;
	            		quotient++;
	            }        	
        }
        else
        {
            while(divisor > dividend)
            {
            		dividend -= divisor;
            		quotient++;
            }        	        	
        }
	        
        return sign == true? quotient: -quotient;
    }
}
