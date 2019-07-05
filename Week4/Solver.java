import java.util.ArrayList;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
public class Solver {
	private class Node{
		private Board board;
		private int move;
		private Node father;
		private int val;
		
		public Node(Board board, int move, Node father) {
			this.board = board;
			this.move = move;
			this.father = father;
			this.val = move + board.manhattan();
		}
	}

	private Node solution;
    public Solver(Board initial) {
    	Comparator<Node> comparator = new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if(o1.val < o2.val)	return -1;
				else if(o1.val > o2.val)	return 1;
				return 0;
			}	
    	};
    	if(initial == null)	throw new java.lang.IllegalArgumentException();

    	MinPQ<Node> q = new MinPQ<>(1, comparator);
    	MinPQ<Node> qTwin = new MinPQ<>(1, comparator);
    	Node root = new Node(initial, 0, null);
    	q.insert(root);
    	qTwin.insert(new Node(root.board.twin(), 0, null));
    	Node temp = null;
    	Node tempTwin = null;
    	while(!q.isEmpty() && !qTwin.isEmpty()) {
    		temp = q.delMin();
    		tempTwin = qTwin.delMin();
//    		System.out.println(temp.board.toString());
//    		solution.add(temp.board);
    		if(temp.board.isGoal() || tempTwin.board.isGoal())	break;
    		for(Board b : temp.board.neighbors()) {
    			if(temp.father != null && b.equals(temp.father.board))	continue;
    			q.insert(new Node(b, temp.move + 1, temp));
    		}
    		for(Board b : tempTwin.board.neighbors()) {
    			if(tempTwin.father != null && b.equals(tempTwin.father.board))	continue;
    			qTwin.insert(new Node(b, tempTwin.move + 1, tempTwin));
    		}
    	}
    	if(temp.board.isGoal())	solution = temp;
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
    	return solution != null;
    }

    // min number of moves to solve initial board
    public int moves() {
    	if(solution == null)	return -1;
    	return solution.move;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution(){
    	if(solution == null)	return null;
    	ArrayList<Board> res = new ArrayList<>();
    	Node p = solution;
    	while(p != null) {
    		res.add(0, p.board);
    		p = p.father;
    	}
    	return res;
    }

	public static void main(String[] args) {
		  // create initial board from file
	    In in = new In("puzzle4x4-unsolvable.txt");
	    int n = in.readInt();
	    int[][] blocks = new int[n][n];
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < n; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);

	    // solve the puzzle
	    Solver solver = new Solver(initial);

	    // print solution to standard output
	    if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }

	}

}
