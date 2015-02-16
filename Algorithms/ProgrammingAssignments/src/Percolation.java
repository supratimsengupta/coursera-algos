public class Percolation {
    private boolean[][] percolationGrid;
    private int matrixSize;
    private WeightedQuickUnionUF unionFindAlgo;

    public Percolation(int N) { // create N-by-N grid, with all sites blocked
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        matrixSize = N;
        unionFindAlgo = new WeightedQuickUnionUF(N * N);
        initializeGrid();
    }

    public void open(int p, int q) {
        int i = p - 1;
        int j = q - 1;
        validateInput(i, j);
        percolationGrid[i][j] = true;
        int flattenedIndex = getFlattenedIndex(i, j);

        if (i - 1 > -1 && percolationGrid[i - 1][j]) {
            int y = getFlattenedIndex(i - 1, j);
            unionFindAlgo.union(flattenedIndex, y);
        }

        if (i + 1 < matrixSize && percolationGrid[i + 1][j]) {
            int y = getFlattenedIndex(i + 1, j);
            unionFindAlgo.union(flattenedIndex, y);
        }

        if (j - 1 > -1 && percolationGrid[i][j - 1]) {
            int y = getFlattenedIndex(i, j - 1);
            unionFindAlgo.union(flattenedIndex, y);
        }

        if (j + 1 < matrixSize && percolationGrid[i][j + 1]) {
            int y = getFlattenedIndex(i, j + 1);
            unionFindAlgo.union(flattenedIndex, y);
        }

    }

    public boolean isOpen(int p, int q) {
        int i = p - 1;
        int j = q - 1;
        validateInput(i, j);
        return percolationGrid[i][j];
    }

    public boolean isFull(int p, int q) {
        int i = p - 1;
        int j = q - 1;
        validateInput(i, j);
        int max = matrixSize * matrixSize;
        for (int index = 0; index < max; index++) {
            int flattenedIndex = getFlattenedIndex(i, j);
            if (isOpen(p, q) && unionFindAlgo.connected(index, flattenedIndex)) {
                return true;
            }
        }
        return false;
    }

    public boolean percolates() {
        int lastRow = matrixSize - 1;
        int firstRow = 0;
        for (int topRowColumn = 0; topRowColumn < matrixSize; topRowColumn++) {
            for (int bottomRowColumn = 0; bottomRowColumn < matrixSize; bottomRowColumn++) {
                int x = getFlattenedIndex(firstRow, topRowColumn);
                int y = getFlattenedIndex(lastRow, bottomRowColumn);
                if (percolationGrid[firstRow][topRowColumn]
                        && percolationGrid[lastRow][bottomRowColumn]
                        && unionFindAlgo.connected(x, y)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void validateInput(int i, int j) {
        if (i < 0 || i > matrixSize - 1 || j < 0 || j > matrixSize - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void initializeGrid() {
        percolationGrid = new boolean[matrixSize][matrixSize];
    }

    private int getFlattenedIndex(int i, int j) {
        return i * matrixSize + j;
    }
}