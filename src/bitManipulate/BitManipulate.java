package bitManipulate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BitManipulate {

	public BitManipulate()
	{
		
	}
	
	//set all bits between i and j in N equal to M
	public int updateMintoN(int M,int N,int i,int j)
	{
		int result=0;
		int max=~0;//all 1's
		int left=max-((1<<(j+1))-1);
		System.out.println(left);
		int right=(1<<i)-1;
		System.out.println(right);
		int mask=left|right;
		System.out.println(mask);
		result=(N&mask);
		return result;
	}
	
	public String decimalFractionToBinary(String decimal)
	{
		int intPart=Integer.parseInt(decimal.substring(0, decimal.indexOf(".")));
		//System.out.println(intPart);
		double fracPart=Double.parseDouble(decimal.substring(decimal.indexOf("."),decimal.length()));
		//System.out.println(fracPart);
		String intString="";
		
		while(intPart > 0)
		{
			intString = intPart % 2 + intString;
			intPart = intPart/2;
		}
		//System.out.println(intString);

		StringBuffer decimalString = new StringBuffer();
	
		while(fracPart != 0.0 || decimalString.length() < 3)
		{
			fracPart = 2 * fracPart;
			
			if(fracPart > 0)
			{
				decimalString.append(1);
				//decimalString=decimalString+"1";
				fracPart = fracPart - 1;
			}
			else 
			{
				decimalString.append(0);
				//decimalString=decimalString+"0";
				//fracPart=fracPart;
			}
		}
		return intString + "." + decimalString;
	}

	public void simpleTest()
	{
		int max=~0;
		int left=max-((1<<6)-1);
		int result = left & 182;
		System.out.println(result);		
	}
	
	/*
	 You are given a function that generates 0 and 1 with equal probability. 
	 Write a function that uses the above function to generate any n(1<=n<=1000) so that 
	 probability of producing any number between 1 to 1000 is equal and is 1/1000.
	 * */
	public int random1000()
	{
		int num = 0;
		Random rand = new Random();
		num = rand.nextInt(2); //generate 0 or 1
		num = num << 1; //bitwise shift num by 1 to the left
		for(int i=0; i<8; i++)
		{
			num = num | rand.nextInt(2);
			num = num << 1;
			
		}		
		
		return num%1001;
	}
	
	//https://leetcode.com/problems/gray-code/
	public List<Integer> grayCode(int n) {
	    List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < Math.pow(2,n); i++) 
            result.add(i ^ i/2);
        return result;
	}
	
	//https://leetcode.com/problems/divide-two-integers/
	//10 / 3 , dividend = 10, divisor = 3
	//notworking for Int.Min -2147483648
    public int divide(int dividend, int divisor) {
        int result = 0;
        
        if(divisor == 0) return -1;
        
        if (dividend == 1 << 31 && divisor == -1) return (1 << 31) - 1;
        
        int sign = ((divisor > 0 && dividend > 0) || (divisor < 0 && dividend < 0)) ? 1 : -1;
        
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        
        while(dividend >= divisor)
        {
        	dividend -= divisor;
        	result++;
        }
        
        return sign * result;
    }
    
    public int addWithoutArithmetic(int a, int b)
    {
    	int carry = 0, bitA, bitB, inPlace;
    	int sum = 0;
    	int i = 0;
    	
    	while(a > 0 || b > 0)
    	{
    		if(a>0)
    		{
    			bitA = a & 0x01;
    			a = a >> 1;
    		}
    		else
    			bitA = 0;
    		
    		if(b>0)
    		{
    			bitB = b & 0x01;
    			b = b >> 1;
    		}
    		else
    			bitB = 0;
    			
    		if(bitA == 1)
    			bitB = bitB | carry;
    		else
    			bitA = bitA | carry;
    		
    		inPlace = bitA ^ bitB;
    		
    		carry = bitA & bitB;
    		
    		sum = (inPlace << i) | sum;
    		i++;
    	}
    	
    	sum = (carry << i) | sum;
    	return sum;
    }
}