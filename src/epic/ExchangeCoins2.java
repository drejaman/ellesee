package epic;

public class ExchangeCoins2 {
int[] values= {1000,500,100,25,10,5,1};
int[] coins= {0,0,0,0,0,0,0};
String[] names= {"10$","5$","1$","Qurter","Dime","Nickel","Penny"};
int amount;
public ExchangeCoins2(int am)
{
	amount=am;
}

public ExchangeCoins2(double am)
{
	double db=am*10000;
	amount=(int)db;
	amount=amount/100;
}

public int getCoins(int amount,int denominition)
{
	int noCoins=amount/denominition;
	return noCoins;
}

public int getRemainder(int amount,int denominition)
{
	int leftOver=amount%denominition;
	return leftOver;
}

public void exchangeCoins()
{
	int remainder=amount;
	int i;
for(i=0;i<6;i++)
{
	coins[i]=getCoins(remainder,values[i]);
	remainder=getRemainder(remainder, values[i]);
}
coins[i]=remainder;
}

public void printResult()
{
	for(int i=0;i<7;i++)
		System.out.println(names[i]+" : "+coins[i]);
}
}
