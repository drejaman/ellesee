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
}
