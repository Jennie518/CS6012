import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortTest1 {
        public static void testSort(){
            int[] array = {5, 3, 10, 4};
            int[] expected = {3, 4, 5, 10};
            assertArrayEquals(expected, Sort.Sort(array), "The array should be sorted in ascending order.");
            //assertArrayEquals(expected arr,your arr,messageSupplier)// messageSupplier is failed statement
        }
    }
