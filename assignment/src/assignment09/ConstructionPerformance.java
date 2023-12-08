package assignment09;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ConstructionPerformance {
    private static final int ITER_COUNT = 50;

    public static void main(String[] args) {
        try (FileWriter fw = new FileWriter(new File("construction_data.csv"))) {
            fw.write("Size,AvgTimeBulk,AvgTimeIndividual\n"); // 添加标题行

            for (int exp = 10; exp <= 20; exp++) {
                int size = (int) Math.pow(2, exp);

                long totalTimeBulk = 0;
                long totalTimeIndividual = 0;

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    // Generate vertical segments in a "worst-case" order
                    ArrayList<Segment> segments = new ArrayList<>();
                    for (int i = 0; i < size; i++) {
                        segments.add(new Segment(i, 0, i, 1)); // Vertical segment
                    }
                    Collections.shuffle(segments); // 打乱顺序以模拟最坏情况

                    // Time the bulk construction
                    long startBulk = System.nanoTime();
                    BSPTree bulkTree = new BSPTree(new ArrayList<>(segments));
                    long endBulk = System.nanoTime();
                    totalTimeBulk += endBulk - startBulk;

                    // Time individual insertions
                    BSPTree tree = new BSPTree();
                    long startIndividual = System.nanoTime();
                    for (Segment segment : segments) {
                        tree.insert(segment);
                    }
                    long endIndividual = System.nanoTime();
                    totalTimeIndividual += endIndividual - startIndividual;
                }

                double averageTimeBulk = totalTimeBulk / (double) ITER_COUNT;
                double averageTimeIndividual = totalTimeIndividual / (double) ITER_COUNT;

                System.out.println(size + "\t" + averageTimeBulk + "\t" + averageTimeIndividual);
                fw.write(size + "," + averageTimeBulk + "," + averageTimeIndividual + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
