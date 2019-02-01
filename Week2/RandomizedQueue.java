import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int size;
	private Item[] array;
	public RandomizedQueue() {
		// construct an empty randomized queue
		resize(1);
		size = 0;
	}
	public boolean isEmpty() {
		// is the randomized queue empty?
		return size==0;
	}
	public int size() {
		// return the number of items on the randomized queue
		return this.size;
	}
	public void enqueue(Item item) {
		// add the item
		if(item == null) {
			throw new java.lang.IllegalArgumentException();
		}
		if(size == array.length)	resize(size * 2);
		array[size++] = item;
	}
	public Item dequeue() {
		// remove and return a random item
		if(isEmpty())	throw new java.util.NoSuchElementException();
		if(size == array.length /4)	resize(array.length /2);
		int ran = StdRandom.uniform(size);
		Item res = array[ran];
		array[ran] = array[--size];
		array[size] = null;
		return res;
	}
	public Item sample() {
		// return a random item (but do not remove it)
		if(isEmpty())	throw new java.util.NoSuchElementException();
		int ran = StdRandom.uniform(size);
		return array[ran];
	}
	private void resize(int capacity) {
		Item[] newArray = (Item[]) new Object[capacity];
		if(capacity == 1) {
			array = newArray;
			return;
		}
		for(int i=0;i<size;i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}
	public Iterator<Item> iterator(){
		// return an independent iterator over items in random order
		return new RandomQueueIterator();
	}
	private class RandomQueueIterator implements Iterator<Item>{
		private int p = 0;
		private int[] ran;
		public RandomQueueIterator() {
			ran = new int[size];
			for(int i=0;i<size;i++)	ran[i] = i;
			StdRandom.shuffle(ran);
		}
		@Override
		public boolean hasNext() {
			return p != size;
		}

		@Override
		public Item next() {
			if(!hasNext())	throw new java.util.NoSuchElementException();
			return array[ran[p++]];
		}
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
