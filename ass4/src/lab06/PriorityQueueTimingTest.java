package lab06;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PriorityQueueTimingTest {

    public static void main(String[] args) {
        int[] testSizes = {1000, 10000, 100000}; // 测试不同的数据规模
        String csvFile = "timing_results.csv";

        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.append("Data Structure,Operation,Data Order,Size,Time (ns)\n");

            for (int size : testSizes) {
                ArrayList<Integer> orderedData = new ArrayList<>();
                ArrayList<Integer> shuffledData = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    orderedData.add(i);
                    shuffledData.add(i);
                }
                Collections.shuffle(shuffledData, new Random(0));

                // 测试 TreeSet 实现的创建时间 (有序数据)
                long startTime = System.nanoTime();
                PriorityQueueTreeSet<Integer> treeQueue = new PriorityQueueTreeSet<>();
                for (Integer data : orderedData) {
                    treeQueue.add(data);
                }
                long endTime = System.nanoTime();
                writer.append(String.format("TreeSet,Creation,Ordered,%d,%d\n", size, (endTime - startTime)));

                // 测试 TreeSet 实现的创建时间 (乱序数据)
                startTime = System.nanoTime();
                treeQueue = new PriorityQueueTreeSet<>();
                for (Integer data : shuffledData) {
                    treeQueue.add(data);
                }
                endTime = System.nanoTime();
                writer.append(String.format("TreeSet,Creation,Shuffled,%d,%d\n", size, (endTime - startTime)));

                // 测试 ArrayList 实现的创建时间 (有序数据)
                startTime = System.nanoTime();
                PriorityQueueMinHeap<Integer> heapQueue = new PriorityQueueMinHeap<>(new ArrayList<>(orderedData));
                endTime = System.nanoTime();
                writer.append(String.format("ArrayList,Creation,Ordered,%d,%d\n", size, (endTime - startTime)));

                // 测试 ArrayList 实现的创建时间 (乱序数据)
                startTime = System.nanoTime();
                heapQueue = new PriorityQueueMinHeap<>(shuffledData);
                endTime = System.nanoTime();
                writer.append(String.format("ArrayList,Creation,Shuffled,%d,%d\n", size, (endTime - startTime)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
