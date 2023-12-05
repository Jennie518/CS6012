package assignment06;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.Random;

public class BSTPerformanceTest {

    private static final int START_N = 10000;
    private static final int END_N = 200000;
    private static final int STEP = 10000;

    public static void main(String[] args) {
        List<Long> timesTreeSetAdd = new ArrayList<>();
        List<Long> timesTreeSetContains = new ArrayList<>();
        List<Long> timesBSTAdd = new ArrayList<>();
        List<Long> timesBSTContains = new ArrayList<>();

        for (int N = START_N; N <= END_N; N += STEP) {
            // Generate random items
            List<Integer> items = generateRandomItems(N);

            // Test TreeSet
            TreeSet<Integer> treeSet = new TreeSet<>();
            long startTime = System.currentTimeMillis();
            items.forEach(treeSet::add);
            long endTime = System.currentTimeMillis();
            timesTreeSetAdd.add(endTime - startTime);

            startTime = System.currentTimeMillis();
            items.forEach(treeSet::contains);
            endTime = System.currentTimeMillis();
            timesTreeSetContains.add(endTime - startTime);

            // Test BinarySearchTree
            BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            startTime = System.currentTimeMillis();
            items.forEach(bst::add);
            endTime = System.currentTimeMillis();
            timesBSTAdd.add(endTime - startTime);

            startTime = System.currentTimeMillis();
            items.forEach(bst::contains);
            endTime = System.currentTimeMillis();
            timesBSTContains.add(endTime - startTime);
        }

        // Now you have all the times recorded, you can print them or save to a file
        // to create a plot later. Here's an example of printing:
        System.out.println("TreeSet Add times: " + timesTreeSetAdd);
        System.out.println("TreeSet Contains times: " + timesTreeSetContains);
        System.out.println("BST Add times: " + timesBSTAdd);
        System.out.println("BST Contains times: " + timesBSTContains);

        writeResultsToCSV(timesTreeSetAdd, timesTreeSetContains, timesBSTAdd, timesBSTContains);
    }

    private static List<Integer> generateRandomItems(int N) {
        List<Integer> items = new ArrayList<>(N);
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            items.add(rand.nextInt());
        }
        return items;
    }

    // Implement this method if you want to write the results to a CSV file
    private static void writeResultsToCSV(List<Long> timesTreeSetAdd, List<Long> timesTreeSetContains,
                                          List<Long> timesBSTAdd, List<Long> timesBSTContains) {
        String fileName = "bst_performance.csv";
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write("N,TimeTreeSetAdd,TimeTreeSetContains,TimeBSTAdd,TimeBSTContains\n");
            for (int i = 0; i < timesTreeSetAdd.size(); i++) {
                int N = START_N + i * STEP;
                fileWriter.write(N + "," + timesTreeSetAdd.get(i) + ","
                        + timesTreeSetContains.get(i) + ","
                        + timesBSTAdd.get(i) + ","
                        + timesBSTContains.get(i) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
