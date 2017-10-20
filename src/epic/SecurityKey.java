package epic;

public class SecurityKey {
char[][] numbers= {{'1','2','3'},{'4','5','6'},{'7','8','9'}};
char[] key;
public SecurityKey(char[] k)
{
	key=new char[k.length];
	key=k;	
}

public boolean checkValid(String ent)
{
	char[] entry=ent.toCharArray();
	int len=entry.length;
	char allowed=' ';
	int i=0;
	int row=0,col=0,rowK=0,colK=0;
	while(i<len)
	{
		if(entry[i]!=key[i])
		{
			if(allowed==' ')
			{
				row=(entry[i]-'0'-1)/3;
				col=(entry[i]-'0'-1)%3;
				rowK=(key[i]-'0'-1)/3;
				colK=(key[i]-'0'-1)%3;
				if(row==rowK||col==colK)
				{
					allowed=entry[i];
				}
				else return false;
			}
			else if(entry[i]!=allowed)
				return false;			
		}
		i++;
	}
	return true;
}
}
