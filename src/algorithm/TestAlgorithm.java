package algorithm;

import java.util.Arrays;

//TODO needs refactoring and rearranging
public class TestAlgorithm {
public TestAlgorithm()
{
	
}

public void test()
{
	int[] array = {5,4,6,8,10,100,1,91,3};
//	findThreeSumInArray(array);
	
	//tests find two sum in an array given the index of the array of what number the 
	Arrays.sort(array);
	int[] result = this.findTwoSumInArray(array, array.length-1);
	
	for(int var:result)
	{
		System.out.print(var + ", ");
	}
}

//it will find three numbers in the array such that a+b=c
public void findThreeSumInArray(int[] array)
{
	int i=0,j=1,k=array.length-1;//pointers
	Arrays.sort(array);//sorts the array O(nlogn)
	
	int selection=1;//0 for i, 1 for j
	while(k>1)//i,j increases up to n for n times of k. so running time here is O(n^2)
	{
		if((array[i]+array[j])==array[k])
		{
			System.out.println(array[i]+"+"+array[j]+"="+array[k]);
			return;
		}
		else if((array[i]+array[j])>array[k])
		{
			k--;
		}
		else if((array[i]+array[j])<array[k])
		{
			if((selection%2)==1)
				j++;
			else i++;
			selection++;
		}
		if(j==k)
		{
			i=0;
			j=1;
			k--;
		}
	}
}

//find 2 elements in the array 
public int[] findTwoSumInArray(int[] Arr, int numbToFindIndex)
{
	int k = numbToFindIndex;
	
//	int[] result = new int[3];
	//when there will be no match then this will be the result
//	result[0] = result[1] = result[2] = -1;
	int[] result = {-1, 1, -1};
	
	while(k > 2)
	{
		int i = 0, j = k-1;
		
		while( i<j)
		{
			if((Arr[i] + Arr[j]) == Arr[k])
			{
				result[0] = Arr[i];
				result[1] = Arr[j];
				result[2] = Arr[k];
			}
			else if((Arr[i] + Arr[j]) >= Arr[k])
			{
				j--;
			}else
			{
				i++;
			}
		}
		k--;
	}
	
	return result;
}
}