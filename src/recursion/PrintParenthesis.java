package recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrintParenthesis {
int n;

public PrintParenthesis()
{
}

void printParentheses(int n)
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
	
	//sanity check - if '(' are more than ')' only then we can place one ')'
	if (left < right)
	{
		output[i] = ')';
		//reduce right as one ')' is placed
		doPrint(output, i+1, left, right-1);
	}
	
	//checks if still any '(' are yet to be placed
	if (left > 0)
	{
		output[i] = '(';
		//left is decreased as one '(' is placed
		doPrint(output, i+1, left-1, right);
	}
}

//https://leetcode.com/problems/generate-parentheses/
//Falls into common backtracking
public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<String>();

    if(n == 0) 
    {
    	result.add("");
    }
    else
    {
        generateParenthesis(result, n, n, "");
    }

    return result;
}

private void generateParenthesis(List<String> result, int left, int right, String currentList)
{
	if(right == 0) 
	{
		result.add(currentList);
		return;
	}
	
	if(right > left)
	{
		generateParenthesis(result, left, right - 1, currentList + ")");
	}
	
    if(left > 0)
	{
		generateParenthesis(result, left - 1, right, currentList + "(");		
	}
}

//https://leetcode.com/problems/longest-valid-parentheses/
// O(n) time and O(n) space
/*
 * we start by pushing a -1 onto the stack.

For every 
'(' encountered, we push its index onto the stack.

')' encountered, we pop the topmost element and subtract the current element's index from the top element 
of the stack, which gives the length of the currently encountered valid string of parentheses. 
If while popping the element, 
the stack becomes empty, we push the current element's index onto the stack. 
 * 
 * */
public int longestValidParentheses(String s) {
    int maxans = 0;
    Stack<Integer> stack = new Stack<Integer>();
    
    stack.push(-1);
    
    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == '(') {
            stack.push(i);
        } else {
        	// this line is KEY
            stack.pop();
            if (stack.empty()) {
                stack.push(i);
            } else {
            	// this line is KEY            	
                maxans = Math.max(maxans, i - stack.peek());
            }
        }
    }
    
    return maxans;
}
}