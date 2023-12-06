package assignment06;

import assignment06.BinarySearchTree;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;



public class BSTExperiment {
    private static final int REPEATS = 5; // 重复执行的次数来计算平均时间
    private static final int START_N = 10000;
    private static final int END_N = 50000;
    private static final int STEP = 10000;

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("bst_experiment_results.csv")) {
            writer.write("N,TimeSorted,TimeRandom\n");

            for (int N = START_N; N <= END_N; N += STEP) {
                ArrayList<Integer> items = generateSortedItems(N);
                long timeSorted = timeBSTContains(items, true);
                long timeRandom = timeBSTContains(items, false);

                writer.write(N + "," + timeSorted + "," + timeRandom + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Integer> generateSortedItems(int N) {
        ArrayList<Integer> items = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            items.add(i);
        }
        return items;
    }

    private static long timeBSTContains(ArrayList<Integer> items, boolean isSorted) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (Integer item : items) {
            bst.add(item);
        }

        if (!isSorted) {
            Collections.shuffle(items);
            bst.clear();
            for (Integer item : items) {
                bst.add(item);
            }
        }

        long totalTime = 0;
        for (int i = 0; i < REPEATS; i++) {
            Collections.shuffle(items); // 随机顺序测试前再次打乱
            long startTime = System.nanoTime();
            for (Integer item : items) {
//                bst.contains(item);
                bst.remove(item); // 进行remove操作
            }
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return totalTime / REPEATS;
    }
}