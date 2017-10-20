package epic;

public class TestEpic {
	public TestEpic()
	{
		
	}

	public void test() throws Exception
	{
		//test coin change program
		/*
		ExchangeCoins exc=new ExchangeCoins(1889);
		exc.exchangeCoins();
		exc.printResult();
		*/
		//ExchangeCoins2 exc=new ExchangeCoins2(1889);
		ExchangeCoins2 exc=new ExchangeCoins2(18.89);
		exc.exchangeCoins();
		exc.printResult();
		//replace4thString("I'll give you red light special all through the night");

		//test keyPad
		/*
		KeyPad keyPad=new KeyPad();
		keyPad.keyPadSequence();
		*/
		
		//Tests the reverse of a sentence 
		//StringWork stw=new StringWork();
		//System.out.println(stw.reverseLine("A tribute to Dennis M Ritchie"));
		//System.out.println(stw.reverseLine("This is Great"));
		
		//tests the reverse of sentenc keeping the word right
		/*
		System.out.println(stw.replaceVowels("an apple"));
		System.out.println(stw.replaceVowels("an apple a day saves me"));
		System.out.println(stw.replaceVowels("in this life i"));
		*/
		//test Palindrome
		/*
		System.out.println(checkPalinDrome("humble"));
		System.out.println(checkPalinDrome("konok"));
		System.out.println(checkPalinDrome("112211"));
		*/
		//Security Key
		/*
		SecurityKey secKey=new SecurityKey("1735".toCharArray());
		System.out.println(secKey.checkValid("1765"));
		System.out.println(secKey.checkValid("1865"));
		System.out.println(secKey.checkValid("1535"));
		*/
	}
	

	


	
	
	
}

