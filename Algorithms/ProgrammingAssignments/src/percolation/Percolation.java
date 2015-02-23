public class Percolation {
    private boolean[][] percolationGrid;
    private int matrixSize;
    private WeightedQuickUnionUF unionFindAlgo;
    private int top, bottom;

    public Percolation(int N) { // create N-by-N grid, with all sites blocked
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        matrixSize = N;
        top = 0;
        bottom = N * N + 1;
        unionFindAlgo = new WeightedQuickUnionUF(N * N + 2);
        initializeGrid();
    }

    public void open(int p, int q) {
        validateInput(p, q);
        int i = p - 1;
        int j = q - 1;
        percolationGrid[i][j] = true;
        int flattenedIndex = getFlattenedIndex(p, q);

        if (p == 1) {
            unionFindAlgo.union(top, flattenedIndex);
        }

        if (p == matrixSize) {
            unionFindAlgo.union(bottom, flattenedIndex);
        }

        if (p - 1 > 0 && percolationGrid[i - 1][j]) { // TOP
            int y = getFlattenedIndex(p - 1, q);
            unionFindAlgo.union(flattenedIndex, y);
        }

        if (p + 1 <= matrixSize && percolationGrid[i + 1][j]) { // BOTTOM
            int y = getFlattenedIndex(p + 1, q);
            unionFindAlgo.union(flattenedIndex, y);
        }

        if (q - 1 > 0 && percolationGrid[i][j - 1]) { // LEFT
            int y = getFlattenedIndex(p, q - 1);
            unionFindAlgo.union(flattenedIndex, y);
        }

        if (q + 1 <= matrixSize && percolationGrid[i][j + 1]) { // RIGHT
            int y = getFlattenedIndex(p, q + 1);
            unionFindAlgo.union(flattenedIndex, y);
        }
    }

    public boolean isOpen(int p, int q) {
        validateInput(p, q);
        int i = p - 1;
        int j = q - 1;
        return percolationGrid[i][j];
    }

    public boolean isFull(int p, int q) {
        validateInput(p, q);
        int flattenedIndex = getFlattenedIndex(p, q);
        return (isOpen(p, q) && unionFindAlgo.connected(top, flattenedIndex));
    }

    public boolean percolates() {
        return unionFindAlgo.connected(top, bottom);
    }

    private void validateInput(int i, int j) {
        if (i < 1 || i > matrixSize || j < 1 || j > matrixSize) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void initializeGrid() {
        percolationGrid = new boolean[matrixSize][matrixSize];
    }

    private int getFlattenedIndex(int i, int j) {
        return ((i - 1) * matrixSize + (j - 1)) + 1;
    }
}