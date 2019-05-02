package stacQueue;

import java.util.PriorityQueue;

public class TestPQ {
    //https://leetcode.com/problems/kth-largest-element-in-an-array/
    //trick: PQ
    public int findKthLargest(int[] nums, int k) {
	    	//pq keeps the largest elements
	    	final PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	    	
	    	for(int val : nums)
	    	{
	    		// inserts the element
	    		pq.offer(val);
	    		
	    		// make sure pq contains at most k elements
	    		// if pq size is larger than k then removes the smallest element
	    		// The head of priority queue is the least element with respect to the specified ordering.
	    		// Retrieves and removes the head of this queue (least element), or returns null if this queue is empty.
	    		if(pq.size() > k)
	    		{
	    			pq.poll();
	    		}
	    	}
	    	
	    	//pq now only contains element kth largest and larger than that element
	    	return pq.peek();
    }
    
    //https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
    // runtime = O(n2)
    public int kthSmallest(int[][] matrix, int k) {
 	   if(matrix == null || matrix.length == 0 || matrix.length != matrix[0].length) return 0;
 	   
 	   // by default PQ contains only the largest elements
 	   PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
 	   
 	   // since we need to find k-th smallest element will be ks-th largest element
 	   // for a 3*3 matrix 4th smallest element is 3 * 3 - 4 + 1 = 6th largest element 
 	   int nMinusKthLargest = matrix.length * matrix[0].length  - k + 1;

 	   for(int i = 0; i < matrix.length; i++)
 		   for(int j = 0; j < matrix[0].length; j++)
 		   {
 			   pq.offer(matrix[i][j]);
 			   
 			   // if the pq size becomes more than ks then remove one element to keep only ks-th largest element
 			   if(pq.size() > nMinusKthLargest) pq.poll();
 		   }
 	   
 	   // return ks-th largest/ k-th smallest element
 	   return pq.poll();        
    }
	
	//https://leetcode.com/problems/top-k-frequent-elements/
	/*
	 * Input: nums = [1,1,1,2,2,3], k = 2
	   Output: [1,2]
	 * */
	//TODO
	//logic: can be solved using priority queue/heap. need to find better way
//    public List<Integer> topKFrequent(int[] nums, int k) {
//        
//    }

}
