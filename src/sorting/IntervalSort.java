package sorting;

import java.util.ArrayList;
import java.util.List;

 class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
 }

public class IntervalSort {
    //https://leetcode.com/problems/summary-ranges/
	//	Input:  [0,1,2,4,5,7]
	//	Output: ["0->2","4->5","7"]
    public List<String> SummaryRanges(int[] nums)
    {
        List<String> ranges = new ArrayList<String>();

        if (nums == null || nums.length == 0) return ranges;

        StringBuilder builder = new StringBuilder();

        builder.append(nums[0]);//0 is added
        
        boolean continuous = false;// if "->" is already added to string builder

        for(int i = 0; i < nums.length - 1; i++)
        {
            if (Math.abs(nums[i + 1] - nums[i]) > 1)//when 4 - 2 > 1, again when 7 - 5 > 1
            {
                // special case when there were no range
                if (continuous)
                {
                    builder.append(nums[i]);//end, so add 2 making builder string to 0->2. same way 4->5         
                    continuous = false;
                }

                ranges.add(builder.toString());//"0->2" added. same way 4->5 added
                builder.setLength(0);
                builder.append(nums[i + 1]);//start, new builder started with 4
            }

            if (Math.abs(nums[i + 1] - nums[i]) == 1 && !continuous)
            {
                builder.append("->");//end, -> added when number is 1, again is 4
                continuous = true;//set to true when number is 1, again is 4
            }
        }

        // the last element is getting added no matter what it is
        if (continuous)
        {
            builder.append(nums[nums.length - 1]);//end        
        }

        ranges.add(builder.toString());

        return ranges;
    }
    
	//TODO https://leetcode.com/problems/merge-intervals/
//  public List<Interval> merge(List<Interval> intervals) {
//  	// sort the array based on their start value
//  	Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));        
//  }

  //TODO https://leetcode.com/problems/insert-interval/
  /*
   * insert the new interval in the intervals
   * then call the merge method
   * */
	//TODO
//  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
//      
//  }    
}