import java.io.*;

public class PercolationTestRunner {

    public static void main(String[] args) {
        String directory = "Z:/Supratim/Dev Work/Personal/CourseraAlgos/Algorithms/ProgrammingAssignments/tests/percolation/";
        File[] files = getFilesFromTestDirectory(directory);
        for (File file : files) {
            StdOut.print("Test case in file " + file.getName() + "...");
            In reader = new In(file);
            int N = Integer.parseInt(reader.readLine());
            Percolation percolation = new Percolation(N);
            String[] lines = reader.readAllLines();
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].trim();
                String[] inputs = line.split(" ");
                String x, y;
                if (inputs.length > 1) {
                    x = inputs[0].trim();
                    y = inputs[inputs.length - 1].trim();

                    if (x.length() == 0 || y.length() == 0) {
                        continue;
                    }

                    int p = Integer.parseInt(x);
                    int q = Integer.parseInt(y);
                    percolation.open(p, q);
                }
            }
            String percolationResult = percolation.isFull(18, 1) ? "PERCOLATES"
                    : "DOES NOT PERCOLATE";
            StdOut.println(" " + percolationResult);

        }

    }

    private static File[] getFilesFromTestDirectory(String directoryPath) {
        File folder = new File(directoryPath);
        FilenameFilter filter = new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                if (name.lastIndexOf('.') > 0) {
                    // get last index for '.' char
                    int lastIndex = name.lastIndexOf('.');

                    // get extension
                    String str = name.substring(lastIndex);

                    // match path name extension
                    if (str.equals(".txt")) {
                        return true;
                    }
                }
                return false;
            }
        };

        return folder.listFiles(filter);
    }

}
