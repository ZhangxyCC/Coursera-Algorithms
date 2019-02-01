import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private byte[] map; 
	private WeightedQuickUnionUF union;
	private int N;
	private int numOpen;
	public Percolation(int n) {
		// create n-by-n grid, with all sites blocked
		if(n <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		map = new byte[n * n + 1];
		union = new WeightedQuickUnionUF(n * n +1);
		N = n;
		numOpen = 0;
		map[0] = 1;
		
	}
    public void open(int row, int col) {
    	// open site (row, col) if it is not open already
    	if(row <= 0 || row > N || col <= 0 || col > N) {
    		throw new java.lang.IllegalArgumentException();
    	}
    	int index = (row -1) * N + col;
    	if(map[index] != 0)	return;
    	numOpen++;
    	map[index] = 1;
    	if(row == 1) {
    		union.union(0, index);	
    	}
    	if(row == N) {
    		map[index] = 2;
    		map[union.find(index)] = 2;
    	}
    	
    	if(row != 1 && map[index - N] != 0) {
    		int rootUp = union.find(index - N);
    		int rootCur = union.find(index);
    		union.union(rootUp, rootCur);
    		if(map[rootUp] == 2 || map[rootCur] == 2) {
    			map[union.find(index)] = 2;
    		}
    	}
    	if(row != N && map[index + N] != 0) {
    		int rootUp = union.find(index + N);
    		int rootCur = union.find(index);
    		union.union(rootUp, rootCur);
    		if(map[rootUp] == 2 || map[rootCur] == 2) {
    			map[union.find(index)] = 2;
    		}
    	}
    	if(col != 1 && map[index - 1] != 0) {
    		int rootUp = union.find(index - 1);
    		int rootCur = union.find(index);
    		union.union(rootUp, rootCur);
    		if(map[rootUp] == 2 || map[rootCur] == 2) {
    			map[union.find(index)] = 2;
    		}
    	}
    	if(col != N && map[index + 1] != 0) {
    		int rootUp = union.find(index + 1);
    		int rootCur = union.find(index);
    		union.union(rootUp, rootCur);
    		if(map[rootUp] == 2 || map[rootCur] == 2) {
    			map[union.find(index)] = 2;
    		}
    	}
    	
    }
    public boolean isOpen(int row, int col) {
    	// is site (row, col) open?
    	if(row <= 0 || row > N || col <= 0 || col > N) {
    		throw new java.lang.IllegalArgumentException();
    	}
    	return map[(row -1) * N + col] != 0;
    }
    public boolean isFull(int row, int col) {
    	// is site (row, col) full?
    	if(row <= 0 || row > N || col <= 0 || col > N) {
    		throw new java.lang.IllegalArgumentException();
    	}
    	return union.connected((row -1) * N + col, 0);
    }
    public int numberOfOpenSites() {
    	// number of open sites	
    	return numOpen;
    }
    public boolean percolates() {
    	// does the system percolate?
    	return map[union.find(0)] == 2;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
