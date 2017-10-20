package oopDesign;

public class BlackJack extends Card{

	public BlackJack(int r, Suit s) {
		super(r, s);
	}
	
	public int value()
	{
		int blackVal = super.value();
		
		if(blackVal == 1)
			blackVal = 11;
		else if(blackVal <= 10)
			blackVal = 10;
		return blackVal;		
	}
}
