package oopDesign;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//https://leetcode.com/problems/my-calendar-i/
//["MyCalendar","book","book","book","book","book","book","book","book","book","book"]
//[[],[47,50],[33,41],[39,45],[33,42],[25,32],[26,35],[19,25],[3,8],[8,13],[18,27]]
// not working for [19,25]
public class MyCalendar {

	// startDate, endDate
	private HashMap<Integer, Integer> bookingMap;
	
    public MyCalendar() {
    	bookingMap = new HashMap<Integer, Integer>();
    }
    
    //MyCalendar.book(10, 20); // returns true
    //MyCalendar.book(15, 25); // returns false
    //MyCalendar.book(20, 30); // returns true
    // logic:
    // - if the startDate is greater than existing startDate then startDate has to be greater than existing endDate
    // - if the startDate is less than existing startDate then endDate has to be less than existing startDate
    public boolean book(int start, int end) {
    	boolean validBookingDate = true;
    	
        Iterator it = bookingMap.entrySet().iterator();
        
        while(it.hasNext())
        {
        	Map.Entry map = (Map.Entry) it.next();

        	int existingStartDate = (int) map.getKey();
        	int existingEndDate = (int) map.getValue();

        	//the key is >= and <= to be in the right places
        	if((start > existingStartDate && start >= existingEndDate && end >= existingEndDate)
        			|| start < existingStartDate && end <= existingStartDate)
        	{
    			validBookingDate = true;
        	}
        	else
        	{
    			validBookingDate = false;
    			break;        		
        	}
        }
        
        if(validBookingDate)
        {
        	bookingMap.put(start, end);
        }
        
        return validBookingDate;
    }
}