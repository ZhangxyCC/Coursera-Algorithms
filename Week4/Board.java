import java.util.ArrayList;

public class Board {
	private int[][] tiles;
	private int n;

    public Board(int[][] tiles) {
    	this.n = tiles.length;
    	this.tiles = new int[n][n];
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			this.tiles[i][j] = tiles[i][j];
    		}
    	}
    	
    }
                                           
    // string representation of this board
    public String toString() {
    	StringBuilder s = new StringBuilder();
    	s.append(n + "\n");
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			s.append(tiles[i][j] + " ");
    		}
    		s.setLength(s.length() - 1);
    		s.append("\n");
    	}
    	s.setLength(s.length() - 1);
    	return s.toString();
    }

    public int dimension() {
    	return n;
    }

    public int hamming() {
    	int count = 0;
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			if(tiles[i][j]  != i * n + j + 1 && (i != n - 1 || j != n - 1))	count++;
    		}
    	}
    	return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
    	int count = 0;
    	int row = 0;
    	int col = 0;
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			if(tiles[i][j] == 0 || tiles[i][j] == i * n + j + 1)	continue;
    			row = (tiles[i][j] - 1) / n;
    			col = (tiles[i][j] - 1) % n;
    			count += Math.abs(row - i);
    			count += Math.abs(col - j);
    		}
    	}
    	return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			int temp = tiles[i][j] == 0 ? n * n : tiles[i][j];
    			if(temp != i * n + j + 1)	return false;
    		}
    	}
    	return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
    	if(y == this)	return true;
    	if(y == null)	return false;
    	if(y.getClass() != this.getClass())	return false;
    	Board b = (Board)y;
    	return b.toString().equals(this.toString());
    }
    private int[][] copyTiles(){
    	int[][] res = new int[n][n];
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			res[i][j] = tiles[i][j];
    		}
    	}
    	return res;
    }
    // all neighboring boards
    public Iterable<Board> neighbors(){
    	ArrayList<Board> res = new ArrayList<>();
    	int row = 0;
    	int col = 0;
    	Loop: for(row = 0; row < n; row++) {
    		for(col = 0; col < n; col++) {
    			if(tiles[row][col] == 0) {
    				break Loop;
    			}
    		}
    	}
    	if(row != 0) {//move down
    		int[][] temp = copyTiles();
    		temp[row][col] = temp[row - 1][col];
    		temp[row - 1][col] = 0;
    		res.add(new Board(temp));
    	}
    	if(row != n - 1) {//move up
    		int[][] temp = copyTiles();
    		temp[row][col] = temp[row + 1][col];
    		temp[row + 1][col] = 0;
    		res.add(new Board(temp));
    	}
    	if(col != 0) {//move right
    		int[][] temp = copyTiles();
    		temp[row][col] = temp[row][col - 1];
    		temp[row][col - 1] = 0;
    		res.add(new Board(temp));
    	}
    	if(col != n - 1) {//move left
    		int[][] temp = copyTiles();
    		temp[row][col] = temp[row][col + 1];
    		temp[row][col + 1] = 0;
    		res.add(new Board(temp));
    	}
    	return res;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
    	int[][] newBoard = copyTiles();
    	if(newBoard[0][0] == 0) {
    		int temp = newBoard[0][1];
    		newBoard[0][1] = newBoard[1][1];
    		newBoard[1][1] = temp;
    		return new Board(newBoard);
    	}else {
    		if(newBoard[0][1] != 0) {
    			int temp = newBoard[0][0];
        		newBoard[0][0] = newBoard[0][1];
        		newBoard[0][1] = temp;
        		return new Board(newBoard);
    		}else {
    			int temp = newBoard[0][0];
        		newBoard[0][0] = newBoard[1][0];
        		newBoard[1][0] = temp;
        		return new Board(newBoard);
    		}
    	}
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] a =new int[][]{{1,3},{2,0}};
		Board b = new Board(a);
		System.out.println(b.twin().toString());
//		for(Board bb : b.neighbors()) {
//			System.out.println(bb.toString());
//		}
	}

}
