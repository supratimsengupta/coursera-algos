public class PercolationStats {
    private double[] results;
    private int size;
    private int numberOfExperiments;

    public PercolationStats(int N, int T) {
        numberOfExperiments = T;
        size = N;
        results = new double[numberOfExperiments];
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(numberOfExperiments));
    }

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(numberOfExperiments));
    }

    private void runExperiments() {
        for (int count = 0; count < numberOfExperiments; count++) {
            StdOut.println("Experiment " + Integer.toString(count + 1) + "...");
            results[count] = performExperiment(count);
        }
    }

    private double performExperiment(int count) {
        Percolation percolation = new Percolation(size);
        int blocksOpened = 0;
        while (!percolation.percolates()) {
            int i = getRandomNumber(size);
            int j = getRandomNumber(size);
            if (!percolation.isOpen(i, j)) {
                percolation.open(i, j);
                blocksOpened++;
            }
        }

        return (double) blocksOpened / (size * size);
    }

    private int getRandomNumber(int upperBound) {
        return StdRandom.uniform(0, upperBound);
    }

    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(N, T);
        percolationStats.runExperiments();
        StringBuilder output = new StringBuilder();
        output.append("mean                    = " + percolationStats.mean());
        output.append("\n");
        output.append("stddev                  = " + percolationStats.stddev());
        output.append("\n");
        output.append("95% confidence interval = "
                + Double.toString(percolationStats.confidenceLo()) + ", "
                + Double.toString(percolationStats.confidenceHi()));
        StdOut.print(output.toString());
    }

}
