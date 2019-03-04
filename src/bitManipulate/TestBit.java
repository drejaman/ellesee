package bitManipulate;

public class TestBit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public void test()
	{
		BitManipulate bmp=new BitManipulate();
		//bmp.simpleTest();
		//System.out.println(bmp.updateMintoN(182, 21, 2, 6));
		System.out.println(bmp.decimalFractionToBinary("3.75"));
	}
	
	// https://leetcode.com/problems/counting-bits/description/
	// need to do more work to fulfil additional requirements like
	// - can you do it in linear time O(n) /possibly in a single pass?
	// - Space complexity should be O(n).
    public int[] countBits(int num) {
        
    	int[] noOfBits = new int[num + 1];
    	
    	for(int i = 0; i <= num; i++)
    	{
    		noOfBits[i] = bitCount(i);
     	}
    	
    	return noOfBits;
    }
    
    // Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
    private int bitCount(int n)
    {
    	int count = 0;
    	
    	while(n > 0)
    	{
    		count++;
    		n /= 2;
    	}
    	
    	return count;
    }
    
    //https://leetcode.com/problems/number-of-1-bits/
    public int HammingWeight(int n) {
        if (n <= 0)
        {
            return 0;
        }

        // this is important as n is provided explicitly as uint
        // so if n is casted to int somewhere then large number like   
        ///4294967295 (11111111111111111111111111111111) will give error
        int result = 0;

        while (n > 0)
        {
            // result += (int) n % 2 will yield to wrong result in some cases
            result += n % 2;
            n /= 2;
        }

        // now you can convert the result to int
        return (int) result;
    }
    
    //https://leetcode.com/problems/hamming-distance/
    public int HammingDistance(int x, int y) {
        int distance = x ^ y;

        return this.NumberOf1Bits(distance);
    }

	public int NumberOf1Bits(int n)
	{
	        int count = 0;
	
	        while(n != 0)
	        {
	            count++;
	
	            n &= (n - 1);    
	        }
	
	        return count;
	}
}
