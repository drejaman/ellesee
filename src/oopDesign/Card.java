package oopDesign;

public class Card {
	int card;
	Suit suit;

public Card(int r,Suit s)
{
	card = r;
	suit = s;
}

public int value()
{
	return card;
}

public Suit suit()
{
	return suit;
}
}

