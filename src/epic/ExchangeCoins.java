package epic;

public class ExchangeCoins {
	int totalAmount;
	int tenDollar;
	int fiveDollar;
	int oneDollar;
	int quarter;
	int dime;
	int nickel;
	int penny;
	
public ExchangeCoins(int a)
{
	totalAmount=a;
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
	tenDollar=getCoins(totalAmount,1000);
	int remainder=getRemainder(totalAmount, 1000);
	fiveDollar=getCoins(remainder,500);
	remainder=getRemainder(remainder, 500);
	oneDollar=getCoins(remainder,100);
	remainder=getRemainder(remainder, 100);
	quarter=getCoins(remainder,25);
	remainder=getRemainder(remainder, 25);
	dime=getCoins(remainder,10);
	remainder=getRemainder(remainder, 10);	
	nickel=getCoins(remainder,5);
	penny=getRemainder(remainder, 5);

}

public void printResult()
{
	System.out.println(totalAmount+" :");
	System.out.println(tenDollar+" 10$ bills ");
	System.out.println(fiveDollar+" 5$ bills ");
	System.out.println(oneDollar+" 1$ bills ");
	System.out.println(quarter+" quarter ");
	System.out.println(dime+" dime ");
	System.out.println(nickel+" nickel");
	System.out.println(penny+" penny");
}

}
