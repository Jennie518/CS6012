package assignment07;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HashFunctionPerformanceTest {
    private static final int MAX_SIZE = 1000; // 最大数据集大小
    private static final int STEP = 100; // 数据集大小的步长

    public static void main(String[] args) {
        List<HashFunctor> functors = Arrays.asList(new BadHashFunctor(), new GoodHashFunctor(), new MediocreHashFunctor());
        List<List<Object>> data = new ArrayList<>();

        for (HashFunctor functor : functors) {
            for (int size = STEP; size <= MAX_SIZE; size += STEP) {
                ChainingHashTable table = new ChainingHashTable(size, functor);
//                int collisions = 0;
                //Time it
                long startTime = System.nanoTime();
                for (int i = 0; i < size; i++) {
                    table.add(Integer.toString(i));
//                    if (table.add(Integer.toString(i))) {
//                        collisions = table.getCollisions();
//                    }
                }
                long endTime = System.nanoTime();
                long duration = endTime - startTime;
                data.add(Arrays.asList(functor.getClass().getSimpleName(),size,duration));
            }
        }
        writeResultsToCSV(data);
    }

    private static void writeResultsToCSV(List<List<Object>> data) {
        String fileName = "hash_function_add_performance.csv";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Hash Function,Size,addTime (ns)\n");
            for (List<Object> row : data) {
                StringJoiner joiner = new StringJoiner(",");
                for (Object cell : row) {
                    joiner.add(cell.toString());  // This ensures each cell is converted to a string properly
                }
                writer.write(joiner.toString());
                writer.write("\n");  // Write a new line after each row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
