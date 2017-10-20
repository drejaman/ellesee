package sorting;

public class StringSorting {

public StringSorting()
{
}

public int searchString(String required,String[] str,int lo,int hi)
{

	int mid=(lo+hi)/2;
	//System.out.println(mid);
	System.out.println("Printing mid string: "+str[mid]);
	if(str[mid].equals(""))
		while(str[mid].equals(""))
			mid++;
	if(required.equals(str[mid]))
		return mid;	
	else if(required.compareTo(str[mid])<0)
		return searchString(required,str,lo,mid-1);
	else if(required.compareTo(str[mid])>0)
		return searchString(required,str,mid+1,hi);
	else return -1;
}


}
