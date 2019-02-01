import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
	private LineSegment[] lines;
	private int size;
	public BruteCollinearPoints(Point[] points) {
		// finds all line segments containing 4 points
		if (points == null) throw new java.lang.IllegalArgumentException();
	
		for(int i=0;i<points.length;i++) {
			if(points[i] == null)	throw new java.lang.IllegalArgumentException();
			for(int j = i+1;j<points.length;j++) {
				if(points[j] == null)	throw new java.lang.IllegalArgumentException();
				if(points[i].compareTo(points[j]) == 0)	throw new java.lang.IllegalArgumentException();
			}
		}
		Point[] ps = points.clone();
		ArrayList<LineSegment> res = new ArrayList<>();
		Arrays.sort(ps);
		int len = ps.length;
		for(int p = 0;p<len-3;p++) {
			for(int q = p+1;q<len-2;q++) {
				double pq = ps[p].slopeTo(ps[q]);
				for(int r = q+1;r <len-1;r++) {
					double qr = ps[q].slopeTo(ps[r]);
					if(pq != qr)	continue;
					for(int s =r +1;s < len;s++) {
						double rs = ps[r].slopeTo(ps[s]);
						if(qr == rs) {	
							Point[] temp = new Point[4];
							temp[0] = ps[p];
							temp[1] = ps[q];
							temp[2] = ps[r];
							temp[3] = ps[s];
							Arrays.sort(temp);
							if(ps[p] == temp[0])	res.add(new LineSegment(temp[0],temp[3]));
						}
					}
				}
			}
		}
		lines = new LineSegment[res.size()];
		size = res.size();
		for(int i=0;i<res.size();i++) {
			lines[i] = res.get(i);
		}
		
	}
	public int numberOfSegments() {
		// the number of line segments
		return size;
	}
	public LineSegment[] segments() {
		// the line segments
		return lines.clone();
	}
	public static void main(String[] args) {
		String filename = "d.txt";
		In in = new In(filename);
	    int N = in.readInt();
        Point[] points = new Point[N];
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(0,32767);
        StdDraw.setYscale(0,32767);
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }
        StdDraw.show();
        
        BruteCollinearPoints b = new BruteCollinearPoints(points);
        System.out.println(b.numberOfSegments()); 
       
	}
}
