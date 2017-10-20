package moderate;

public class AmountInWord {
int amount;
	public AmountInWord(int am)
	{
		amount=am;
	}
	
	public void printInWord()
	{
		int thousand=amount/1000;
		int rest=amount%1000;
		if(thousand>0)
		{
			print3Digits(thousand);
			System.out.print("thousand ");
		}
		if(rest>0)
		{
			print3Digits(rest);
			//System.out.print("thousand ");
		}
		
	}
	
	public void print3Digits(int thousand)
	{
			int hundreds=thousand/100;
			int rest2=thousand%100;
			if(hundreds>0)
			{
					printSingle(hundreds);
						System.out.print("hundred ");
			}
			if(rest2<20)
			{
				printSingle(rest2);
			}
			else
			{
				printDouble(rest2/10);
				printSingle(rest2%10);
			}

	}

	public void printSingle(int digit)
	{
		switch(digit)
		{
		case 1:
			System.out.print("One ");
			break;
		case 2:
			System.out.print("Two ");
			break;
		case 3:
			System.out.print("Three ");
			break;
		case 4:
			System.out.print("Four ");
			break;
		case 5:
			System.out.print("Five ");
			break;
		case 6:
			System.out.print("Six ");
			break;
		case 7:
			System.out.print("Seven ");
			break;
		case 8: 
			System.out.print("Eight ");
			break;
		case 9:
			System.out.print("Nine ");
			break;
		case 10:
			System.out.print("Ten ");
			break;
		case 11:
			System.out.print("Eleven ");
			break;
		case 12:
			System.out.print("Tweleve ");
			break;
		case 13:
			System.out.print("Thirteen ");
			break;
		case 14:
			System.out.print("Fourteen ");
			break;
		case 15:
			System.out.print("Fifteen ");
			break;
		case 16:
			System.out.print("Sixteen ");
			break;
		case 17:
			System.out.print("Seventeen ");
			break;
		case 18:
			System.out.print("Eighteen ");
			break;
		case 19:
			System.out.print("Nineteen ");
			break;
		default:
			break;
		}	
}
	
	public void printDouble(int digit)
	{
		switch(digit)
		{
		case 2:
			System.out.print("Twenty ");
			break;
		case 3:
			System.out.print("Thirty ");
			break;
		case 4:
			System.out.print("Fourty ");
			break;
		case 5:
			System.out.print("Fifty ");
			break;
		case 6:
			System.out.print("Sixty ");
			break;
		case 7:
			System.out.print("Seventy ");
			break;
		case 8:
			System.out.print("Eighty ");
			break;
		case 9:
			System.out.print("Ninety ");
			break;
		default:
			break;
		}	
}
	
}
