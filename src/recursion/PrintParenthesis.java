package recursion;

public class PrintParenthesis {
int n;

public PrintParenthesis()
{
}

void printParenthese(int n)
{
	char[] output = new char[2*n];
	doPrint(output, 0, n, n);
}

void doPrint(char[] output, int i, int left, int right)
{
	if (right == 0)//when all the parentheses are placed
	{
		System.out.println(output);
		return;
	}
	
	if (left < right)//sanity check - if '(' are more than ')' only then we can place one ')'
	{
		output[i] = ')';
		doPrint(output, i+1, left, right-1);//reduce right as one ')' is placed
	}
	if (left > 0)//checks if still any '(' are yet to be placed
	{
		output[i] = '(';
		doPrint(output, i+1, left-1, right);//left is decreased as one '(' is placed
	}
}

}
