import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private LineSegment[] lines;
	private int size;
	public FastCollinearPoints(Point[] points) {
		// finds all line segments containing 4 or more points
		if (points == null) throw new java.lang.IllegalArgumentException();
		int len = points.length;
		for(int i=0;i<len;i++) {
			if(points[i] == null)	throw new java.lang.IllegalArgumentException();
			for(int j = i+1;j<len;j++) {
				if(points[j] == null)	throw new java.lang.IllegalArgumentException();
				if(points[i].compareTo(points[j]) == 0)	throw new java.lang.IllegalArgumentException();
			}
		}
		Arrays.sort(clonePoints);
		Point[] clonePoints = points.clone();
		ArrayList<LineSegment> res = new ArrayList<>();
		if(len < 4)	{
			lines = new LineSegment[0];
			size = 0;
			return;
		}
	//	Arrays.sort(clonePoints);
		Point[] temp = clonePoints.clone();
		for(Point p : clonePoints) {		
			Arrays.sort(temp,p.slopeOrder());
			double slope = p.slopeTo(temp[1]);
			ArrayList<Point> beLine = new ArrayList<>();
			beLine.add(p);
			beLine.add(temp[1]);
			for(int i=2;i<len;i++) {
				double slopeTemp = p.slopeTo(temp[i]);
				if(slopeTemp != slope) {
					if(beLine.size() >= 4) {
						Collections.sort(beLine);
						if(beLine.get(0) == p) {
							res.add(new LineSegment(p,beLine.get(beLine.size()-1)));
						}
					}
					if(i == len -1)	break;
					beLine = new ArrayList<>();
					beLine.add(p);
					beLine.add(temp[i]);
					slope = slopeTemp;

				}else {
					beLine.add(temp[i]);
					if(i == len-1 && beLine.size() >= 4) {
						Collections.sort(beLine);
						if(beLine.get(0) == p) {
							res.add(new LineSegment(p,beLine.get(beLine.size()-1)));
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
		String filename = "input9.txt";
		In in = new In(filename);
	    int N = in.readInt();
        Point[] points = new Point[N];
	    ArrayList<Point> a = new ArrayList<>();
        StdDraw.clear();
     //   StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(0,32768);
        StdDraw.setYscale(0,32768);
    //    Point p=null;
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        	StdDraw.setPenColor(StdDraw.BLUE);
            points[i].draw(); 
            a.add(new Point(x,y));
        }
        StdDraw.show();
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
      
	}

}
