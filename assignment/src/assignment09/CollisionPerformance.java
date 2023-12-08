package assignment09;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CollisionPerformance {
    private static final int ITER_COUNT = 50 ;

    public static void main(String[] args) {
        try (FileWriter fw = new FileWriter(new File("collision_data.csv"))) {
            Random random = new Random(42); // fixed seed for reproducibility
            for (int exp = 10; exp <= 20; exp++) {
                int size = (int) Math.pow(2, exp);

                long totalOptimizedTime = 0;
                long totalNaiveTime = 0;

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    BSPTree tree = setupBSPTree(size, random);

                    // Pick a random segment for collision testing
                    Segment query = new Segment(random.nextDouble(), random.nextDouble(),
                            random.nextDouble(), random.nextDouble());

                    // Time the optimized collision method
                    long startOptimized = System.nanoTime();
                    tree.collision(query);
                    long endOptimized = System.nanoTime();
                    totalOptimizedTime += endOptimized - startOptimized;

                    // Time the naive collision detection method
                    long startNaive = System.nanoTime();
                    traverseAndCheckCollision(tree, query);
                    long endNaive = System.nanoTime();
                    totalNaiveTime += endNaive - startNaive;
                }

                double averageOptimizedTime = totalOptimizedTime / (double) ITER_COUNT;
                double averageNaiveTime = totalNaiveTime / (double) ITER_COUNT;
                System.out.println(size + "\t" + averageOptimizedTime + "\t" + averageNaiveTime);
                fw.write(size + "\t" + averageOptimizedTime + "\t" + averageNaiveTime + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BSPTree setupBSPTree(int size, Random random) {
        ArrayList<Segment> segments = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            segments.add(new Segment(random.nextDouble(), random.nextDouble(),
                    random.nextDouble(), random.nextDouble()));
        }
        return new BSPTree(segments);
    }

    private static void traverseAndCheckCollision(BSPTree tree, Segment query) {
        final boolean[] collisionFound = {false};
        tree.traverseFarToNear(0, 0, segment -> {
            if (segment.intersects(query)) {
                collisionFound[0] = true;
            }
        });
    }
}
