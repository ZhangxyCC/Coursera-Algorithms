import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double[] trialRes;
	private int trials;
	private double mean;
	private double stddev;
	private double confidencelo;
	private double confidencehi;
	
	public PercolationStats(int n, int trials) { 
		// perform trials independent experiments on an n-by-n grid
		if(n<=0 || trials <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		this.mean = 0;
		this.stddev = 0;
		this.trials = trials;
		trialRes = new double[trials];
		for(int i=0;i<trials;i++) {
			Percolation p = new Percolation(n);
			while(!p.percolates()) {
				int row = StdRandom.uniform(n);
				int col = StdRandom.uniform(n);
				p.open(row+1, col+1);
			}
			trialRes[i] = (double)p.numberOfOpenSites() / (n * n);
		}
		this.mean = StdStats.mean(trialRes);
		this.stddev = StdStats.stddev(trialRes);
		this.confidencelo = this.mean -(1.96 * this.stddev) / Math.sqrt(trials);
		this.confidencehi = this.mean + (1.96 * this.stddev) / Math.sqrt(trials);
	}
	
    public double mean() {
    	// sample mean of percolation threshold	
    	return this.mean;
    }
    public double stddev() {
    	// sample standard deviation of percolation threshold
    	return this.stddev;
    }
    public double confidenceLo() {
    	// low  endpoint of 95% confidence interval
    	return confidencelo;
    }
    public double confidenceHi() {
    	// high endpoint of 95% confidence interval
    	return confidencehi;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PercolationStats test = new PercolationStats(64,150);
		System.out.println(test.mean());
		System.out.println(test.stddev());
		System.out.println(test.confidenceLo());
	}

}
