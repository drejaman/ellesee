package hard;

public class TestHard {
public TestHard()
{
}

public void test()
{
	System.out.println(addWithoutArithmetic(10,25));
}


public int addWithoutArithmetic(int a,int b)
{
	int carry = 0, bitA, bitB, inPlace;
	int sum=0;
	int i=0;
	
	while(a>0 || b>0)
	{
		if(a>0)
		{
			bitA=a&0x01;
			a=a>>1;
		}
		else
			bitA=0;
		if(b>0)
		{
			bitB=b&0x01;
			b=b>>1;
		}
		else
			bitB=0;
			
		if(bitA==1)
			bitB=bitB|carry;
		else
			bitA=bitA|carry;
		
		inPlace=bitA^bitB;
		
		carry=bitA&bitB;
		sum=(inPlace<<i)|sum;
		i++;
	}
	sum=(carry<<i)|sum;
	return sum;
}
}
