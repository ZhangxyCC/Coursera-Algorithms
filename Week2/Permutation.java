/*
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

	public static void main(String[] args) {
		*/
//testcode
	/*
		String filename = "distinct.txt";
		FileReader reader;
		BufferedReader br;
		ArrayList<String> res = new ArrayList<>();
		String line;
		try{
			reader = new FileReader(filename);
            br = new BufferedReader(reader);
            while((line = br.readLine()) != null) {
            	String[] temp = line.split(" ");
            	for(int i=0;i<temp.length;i++)	res.add(temp[i]);
            }
            
            int count = 0;
    		RandomizedQueue<String> queue = new RandomizedQueue<>();
            int k = 9;
            for(int j=0;j<k;j++) {
            	queue.enqueue(res.get(j));
            	count++;
            }
            for(String s : queue) {
            	System.out.println(s);
            }
            for(int j=k;j<res.size();j++) {
            	count++;
            	int ran = StdRandom.uniform(count);
    			if(ran <k) {
    				queue.dequeue();
    				queue.enqueue(res.get(j));
    			}
            }
            for(String s : queue) {
            	System.out.println(s);
            }
		}catch(IOException e) {
			e.printStackTrace();
		}
		*/
		/*
		int k = Integer.parseInt(args[0]);
		int count = 0;
		RandomizedQueue<String> queue = new RandomizedQueue<>();
		while(k!=0 && !StdIn.isEmpty()) {
			count++;
			if(count >k) {
				int ran = StdRandom.uniform(count);
				if(ran <k) {
					queue.dequeue();
					queue.enqueue(StdIn.readString());
				}
			}else {
				queue.enqueue(StdIn.readString());
			}	
		}	
		while(!queue.isEmpty()) {
			StdOut.println(queue.dequeue());
		}
		
//		System.out.println(StdRandom.uniform(4));
 * */
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
    	
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++) {
            System.out.println(queue.dequeue());
        }
        
   /*
    	int k = Integer.parseInt(args[0]);
		int count = 0;
		RandomizedQueue<String> queue = new RandomizedQueue<>();
		if(k==0)	return;
		int ran;
		while(!StdIn.isEmpty()) {
			count++;
			if(count >k) {
				ran = StdRandom.uniform(count);
				if(ran <k) {
					queue.dequeue();
					queue.enqueue(StdIn.readString());
				}
			}else {
				queue.enqueue(StdIn.readString());
			}	
		}	
		while(!queue.isEmpty()) {
			StdOut.println(queue.dequeue());
		}
	*/	
    }
    
 	
    
}
