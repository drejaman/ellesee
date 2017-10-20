package oopDesign;

public class TestOOP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
	public void test()
	{
		//Card testCard = new BlackJack(1,Suit.DIAMOND);
		Card testCard = new Card(1,Suit.DIAMOND);
		System.out.println(testCard.value());
		
	}

}
