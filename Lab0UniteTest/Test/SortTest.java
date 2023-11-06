import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {
    @BeforeEach
    void setUp() {
        int[] arr1 = new int[0];
        int[] arr2 = new int[]{3, 3, 3};
        int[] arr3 = new int[]{52, 4, -8, 0, -17};
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void sortArrayTest1() {
        int[] array = {5, 3, 10, 4};
        int[] expected = {3, 4, 5, 10};
        assertArrayEquals(expected, Sort.Sort(array), "The array should be sorted in ascending order.");
    }

}