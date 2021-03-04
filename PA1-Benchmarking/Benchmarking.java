/*
 *  Assignment: Benchmarking a linked list
 *
 *  [this file]_main: Main class for the assignment
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;

class Benchmarking {

	private LinkedList<Integer> list;
	
	public Benchmarking(String fileName){
		
		this.list = new LinkedList<Integer>();
		
		File file = new File(fileName);
		
		try {
			Scanner scr = new Scanner (file);
			

	    	long insertStartTime = System.nanoTime();
			while (scr.hasNextLine()) {
				
				String i = scr.nextLine();
				insert(Integer.parseInt(i));
			
			}
			
			scr.close();
			long insertEndTime = System.nanoTime();
			long insertResult = insertEndTime-insertStartTime;
			System.out.println("Run time for insert:" + insertResult);
		}
		
		catch (FileNotFoundException e){
			e.printStackTrace();
	}
		

	}

	public void insert(int value) {
		
		if(list.size() == 0 || value < list.getFirst() ) {
			list.addFirst(value);
		}
		else if(value > list.getLast() ) {
			list.addLast(value);
		}
		else {
			Iterator<Integer> it = list.iterator();
			int index = 0;
		       while(it.hasNext() && value >= it.next()){
		    	   
		    	   index ++;
		    	   
		        }
		       list.add(index,value);
		
//			for(int i = 1; i<list.size(); i++) {
//				if(value < list.get(i)) {
//					list.add(i,value);
//					break;
//				}
			}
			
		
		}

	
	public int findMin() {
		return list.getFirst();
		
	}
	
	public int findMax() {
		return list.getLast();
	}
	
	public int findMedian() {
		if (list.size() % 2 != 0) {
			return list.get(list.size()/2) / 2 ;
		}else {
			return (list.get(list.size()/2) + list.get(list.size()/2 - 1))/2;
		}
			
	}

	
    public static void main(String args[]) throws FileNotFoundException {
		
    	Benchmarking bench = new Benchmarking(args[0]);
    	

    	
    	long startTime = System.nanoTime();
    	bench.findMin();
    	long endTime = System.nanoTime();
    	long timeMin = endTime - startTime;
    	System.out.println("Minimum:" + bench.findMin());
    	System.out.println("Run time for find Minimum:" + timeMin);
    	
    	startTime = System.nanoTime();
    	bench.findMax();
    	endTime = System.nanoTime();
    	long timeMax = endTime - startTime;
    	System.out.println("Maximum:" + bench.findMax());
    	System.out.println("Run time for find Maximum:" + timeMax);
    	
    	startTime = System.nanoTime();
    	bench.findMedian();
    	endTime = System.nanoTime();
    	long timeMed = endTime - startTime;
    	System.out.println("Median:" + bench.findMedian());
    	System.out.println("Run time for find Median:" + timeMed);
    	
    	
    }
}
