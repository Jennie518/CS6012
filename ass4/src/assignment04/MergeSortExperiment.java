package assignment04;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSortExperiment {

    private static final int ITER_COUNT = 20;
    private static Comparator<Integer> cmp = Comparator.naturalOrder();

    public static void main(String[] args) {
        // Warm-up loop to stabilize JIT performance
        int MAX_VALUE = 300000;
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000);

        // List of thresholds for insertion sort
        List<Integer> thresholds = List.of(5, 10, 15, 20, MAX_VALUE); // Including the special case for full mergesort

        // Iterating over each threshold
        for (int threshold : thresholds) {
            String thresholdAsString = threshold == MAX_VALUE ? "FullMergeSort" : String.valueOf(threshold); // Handling the special case

            try (FileWriter fw = new FileWriter(new File("mergesort_threshold_" + thresholdAsString + ".csv"))) {
                // Iterating over different sizes
                for (int exp = 10; exp <= 18; exp++) {
                    int size = (int) Math.pow(2, exp);
                    long totalTime = 0;

                    for (int iter = 0; iter < ITER_COUNT; iter++) {
                        ArrayList<Integer> largeList = SortUtil.generateAverageCase(size);
                        // Time the sorting process
                        long start = System.nanoTime();
                        SortUtil.mergesort(largeList, cmp, threshold); // Use the current threshold
                        long stop = System.nanoTime();
                        totalTime += stop - start;
                    }

                    double averageTime = totalTime / (double) ITER_COUNT;
                    System.out.println(size + "\t" + averageTime + "\n"); // Print to console
                    fw.write(size + "\t" + averageTime + "\n"); // Write to file.
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
