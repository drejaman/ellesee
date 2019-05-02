package javathread;

public class TestThreads extends Thread{
	int count = 5;
    public void run() 
    { 
        try
        { 
            // Displaying the thread that is running 
//            System.out.println ("Thread " + 
//                  Thread.currentThread().getId() + 
//                  " is running"); 

            System.out.println ("Count value is: " + count++); 
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Exception is caught"); 
        } 
    } 
}