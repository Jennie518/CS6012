package assignment05;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StackPerformanceTest {

  private static final int ITER_COUNT = 100; // Number of iterations for averaging the results
  private static final int OPERATION_COUNT = 1000; // Number of push/pop operations in each iteration

  public static void main(String[] args) {
    long startTime = System.nanoTime();
    while (System.nanoTime() - startTime < 1_000_000_000); // Warm-up loop

    // Testing push and pop for ArrayStack and LinkedListStack
    testStackPerformance(new ArrayStack<>(), "ArrayStack");
    testStackPerformance(new LinkedListStack<>(), "LinkedListStack");
  }

  private static void testStackPerformance(Stack<Integer> stack, String stackType) {
    try (FileWriter fw = new FileWriter(new File(stackType + "_performance.csv"))) {
      fw.write("Operation,Time(ns)\n"); // Writing header for CSV file

      // Testing push operation
      long pushTime = testPushOperation(stack);
      fw.write("Push," + pushTime + "\n");

//      // Testing pop operation
//      long popTime = testPopOperation(stack);
//      fw.write("Pop," + popTime + "\n");

      System.out.println(stackType + " Push Time: " + pushTime + " ns");
//      System.out.println(stackType + " Pop Time: " + popTime + " ns");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static long testPushOperation(Stack<Integer> stack) {
    long totalTime = 0;
    for (int iter = 0; iter < ITER_COUNT; iter++) {
      long start = System.nanoTime();
      for (int i = 0; i < OPERATION_COUNT; i++) {
        stack.push(i);
      }
      long end = System.nanoTime();
      totalTime += (end - start);
      // Clear the stack for the next iteration
      stack.clear();
    }
    return totalTime / ITER_COUNT; // Average time per iteration
  }

//  private static long testPopOperation(Stack<Integer> stack) {
//    // Pre-populate the stack with elements for popping
//    for (int i = 0; i < OPERATION_COUNT; i++) {
//      stack.push(i);
//    }
//
//    long totalTime = 0;
//    for (int iter = 0; iter < ITER_COUNT; iter++) {
//      long start = System.nanoTime();
//      for (int i = 0; i < OPERATION_COUNT; i++) {
//        stack.pop();
//      }
//      long end = System.nanoTime();
//      totalTime += (end - start);
//      // Repopulate the stack for the next iteration
//      for (int i = 0; i < OPERATION_COUNT; i++) {
//        stack.push(i);
//      }
//    }
//    return totalTime / ITER_COUNT; // Average time per iteration
//  }
}
