package assignment04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import static assignment04.SortUtil.*;

public class SortsTimingExperiment {
  private static final int ITER_COUNT = 30;
  private static Comparator<Integer> cmp = Comparator.naturalOrder();

  public static void main(String[] args) {
    long startTime = System.nanoTime();
    while (System.nanoTime() - startTime < 1_000_000_000); // Warm-up

    try (FileWriter fw = new FileWriter(new File("sorts_timing_data.csv"))) {
      fw.write("Algorithm,ListType,Size,Strategy,Time\n");  // Adding a header line
      for (int exp = 10; exp <= 30; exp++) {
        int size = (int) Math.pow(2, exp);
        ArrayList<Integer> bestCase = generateBestCase(size);
        ArrayList<Integer> averageCase = generateAverageCase(size);
        ArrayList<Integer> worstCase = generateWorstCase(size);

        // Testing Mergesort with a threshold of 15
        testAndWrite(fw, "Mergesort", "Best", size, 15, new ArrayList<>(bestCase), cmp);
        testAndWrite(fw, "Mergesort", "Average", size, 15, new ArrayList<>(averageCase), cmp);
        testAndWrite(fw, "Mergesort", "Worst", size, 15, new ArrayList<>(worstCase), cmp);

        // Testing Quicksort with RANDOM_ELEMENT strategy
        testAndWrite(fw, "Quicksort", "Best", size, "RANDOM_ELEMENT", new ArrayList<>(bestCase), cmp);
        testAndWrite(fw, "Quicksort", "Average", size, "RANDOM_ELEMENT", new ArrayList<>(averageCase), cmp);
        testAndWrite(fw, "Quicksort", "Worst", size, "RANDOM_ELEMENT", new ArrayList<>(worstCase), cmp);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void testAndWrite(FileWriter fw, String algorithm, String listType, int size, Object thresholdOrStrategy, ArrayList<Integer> list, Comparator<Integer> cmp) throws IOException {
    long time = testSorting(list, cmp, thresholdOrStrategy, algorithm);
    System.out.println(String.format("%s,%s,%d,%s,%d\n", algorithm, listType, size, thresholdOrStrategy, time));
    fw.write(String.format("%s,%s,%d,%s,%d\n", algorithm, listType, size, thresholdOrStrategy, time));
  }
  private static long testSorting(ArrayList<Integer> list, Comparator<Integer> cmp, Object thresholdOrStrategy, String algorithm) {
    long totalTime = 0;
    for (int iter = 0; iter < ITER_COUNT; iter++) {
      long start = System.nanoTime();
      if ("Mergesort".equals(algorithm)) {
        mergesort(list, cmp, (int) thresholdOrStrategy);
      } else if ("Quicksort".equals(algorithm)) {
        PivotStrategy strategy;
        switch (thresholdOrStrategy.toString()) {
          case "RANDOM_ELEMENT":
            strategy = PivotStrategy.RANDOM_ELEMENT;
            break;
          // Add other cases for different pivot strategies if needed
          default:
            strategy = PivotStrategy.RANDOM_ELEMENT; // Default case
        }
        quicksort(list, cmp, strategy);
      }
      long stop = System.nanoTime();
      totalTime += stop - start;
    }
    return totalTime / ITER_COUNT;
  }

}
