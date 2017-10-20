package basic;

import java.lang.reflect.Method;

public class Reflect {
String className;
public Reflect(String cn)
{
	className = cn;
}

public void printMethods()
{
	try 
	{
		Class<?> c = Class.forName(className);
		Method[] methods=c.getMethods();
		
		for(Method method:methods)
		{
			System.out.println(method.getName());
			System.out.println("-------------------");
			System.out.println(method.toGenericString());
			System.out.println();
		}
	} 
	catch (ClassNotFoundException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
