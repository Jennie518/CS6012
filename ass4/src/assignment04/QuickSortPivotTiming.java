package assignment04;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class QuickSortPivotTiming {

    private static final int ITER_COUNT = 100;
    private static Comparator<Integer> cmp = Comparator.naturalOrder();

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000); // Warm-up loop

        for (SortUtil.PivotStrategy strategy : SortUtil.PivotStrategy.values()) {
            try (FileWriter fw = new FileWriter(new File("quicksort_pivot_" + strategy + ".csv"))) {
                for (int exp = 10; exp <= 20; exp++) {
                    int size = (int) Math.pow(2, exp);
                    long totalTime = 0;
                    for (int iter = 0; iter < ITER_COUNT; iter++) {
                        ArrayList<Integer> largeList = SortUtil.generateAverageCase(size);
                        long start = System.nanoTime();
                        SortUtil.quicksort(largeList, cmp, strategy); // Using quicksort with strategy
                        long stop = System.nanoTime();
                        totalTime += stop - start;
                    }
                    double averageTime = totalTime / (double) ITER_COUNT;
                    System.out.println("Size: " + size + " Strategy: " + strategy + " Time: " + averageTime);
                    fw.write(size + "\t" + averageTime + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
