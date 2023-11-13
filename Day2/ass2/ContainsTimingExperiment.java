package assignment02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ContainsTimingExperiment {

  private static final int ITER_COUNT = 100;

  public static void main(String[] args) {
    // you spin me round baby, right round
    long startTime = System.nanoTime();
    while (System.nanoTime() - startTime < 1_000_000_000);//warm up
    // 这行代码的作用是创建一个空的循环，这个循环会一直执行，直到从循环开始到当前时间的差值达到1,000,000,000纳秒（1 second）。

    try (FileWriter fw = new FileWriter(new File("data.csv"))) { // open up a file writer so we can write
                                                                                // to file.
      Random random = new Random();
      for (int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
        int size = (int) Math.pow(2, exp); // or ..

        // Do the experiment multiple times, and average out the results
        long totalTime = 0;

        for (int iter = 0; iter < ITER_COUNT; iter++) {
          // SET UP!
//          SortedSet<Integer> set = new TreeSet<>();
          List<Integer> set = new ArrayList<>();
          for (int i = 0; i < size; i++) {
            set.add(random.nextInt());
          }
          int findElement = random.nextInt(size); // This gets me a random int between 0 and size;

          // TIME IT!
          long start = System.nanoTime();
          Collections.sort(set);//test contains()
          Collections.sort(set);//test contains()
          long stop = System.nanoTime();
          totalTime += stop - start;
        }
        double averageTime = totalTime / (double) ITER_COUNT;
        System.out.println(size + "\t" + averageTime); // print to console
        fw.write(size + "\t" + averageTime + "\n"); // write to file.
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
