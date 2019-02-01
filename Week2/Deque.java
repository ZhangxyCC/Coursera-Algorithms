import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	private class Node{
		Item item;
		Node next;
		Node before;
	}
	private int size;
	private Node first;
	private Node last;
	public Deque() {
		// construct an empty deque
		size = 0;
		first = null;
		last = null;
	}
	public boolean isEmpty() {
		// is the deque empty?
		return size==0;
	}
	public int size() {
		// return the number of items on the deque
		return size;
	}
	public void addFirst(Item item) {
		// add the item to the front
		if(item == null) {
			throw new java.lang.IllegalArgumentException();
		}
		size++;
		Node newfirst = new Node();
		newfirst.item = item;
		newfirst.next = first;	
		newfirst.before = null;
		if(size == 1)	last = newfirst;
		else	first.before = newfirst;
		first = newfirst;
	}
	public void addLast(Item item) {
		// add the item to the end
		if(item == null) {
			throw new java.lang.IllegalArgumentException();
		}
		size++;
		Node newlast = new Node();
		newlast.item = item;
		newlast.next= null;
		newlast.before = last;
		if(size==1) first = newlast;			
		else	last.next = newlast;
		last = newlast;
	}
	public Item removeFirst() {
		// remove and return the item from the front
		if(isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		size--;
		Item item = first.item;
		first = first.next;
		if(size == 0)	last = null;
		else first.before = null;
		return item;
	}
	public Item removeLast() {
		// remove and return the item from the end
		if(isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		size--;
		Node toRemove = last;
		last = toRemove.before;
		if(size == 0)	first = null;	
		else	last.next = null;
		return toRemove.item;
	}
	public Iterator<Item> iterator(){
		// return an iterator over items in order from front to end
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<Item>{
		private Node p = first;
		@Override
		public boolean hasNext() {
			return p !=null;
		}
		@Override
		public Item next() {
			if(!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			Item item = p.item;
			p = p.next;
			return item;
		}
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deque<Deque<Integer>> q = new Deque<>();
		Deque<Integer> q1 = new Deque<>();
		Deque<Integer> q2 = new Deque<>();
		q1.addFirst(3);
		q1.addFirst(2);
		q1.addFirst(1);
		q2.addFirst(6);
		q2.addFirst(5);
		q2.addFirst(4);
		q.addFirst(q1);
		q.addFirst(q2);
		
		for(Deque<Integer> temp: q) {
			for(int i:temp) {
				System.out.println(i);
			}
		}
		
	}

}
