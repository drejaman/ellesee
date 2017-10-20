package basic;

public class TestBasic {
	
public TestBasic()
{
}

public void test()
{
	Reflect reflect=new Reflect("java.awt.Button");
	reflect.printMethods();
}
}
