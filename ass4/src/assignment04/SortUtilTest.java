package assignment04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static assignment04.SortUtil.PivotStrategy.*;
import static org.junit.jupiter.api.Assertions.*;

class SortUtilTest {
    private Comparator<Integer> cmp = Comparator.naturalOrder();
    private Comparator<PhoneNumber> phoneCmp = Comparator.comparing(PhoneNumber::toString);
    private ArrayList<PhoneNumber> phoneNumbers;
    private ArrayList<Integer> randomList;
    private ArrayList<Integer> largerRandomList;
    private ArrayList<Integer> emptyList;
    private ArrayList<Integer> duplicateList;
    private ArrayList<Integer> sortedList;
    private ArrayList<Integer> reversedList;

    @BeforeEach
    public void setup(){
        randomList = SortUtil.generateAverageCase(5);
        largerRandomList = SortUtil.generateAverageCase(100);
        emptyList = SortUtil.generateAverageCase(0);
        duplicateList = new ArrayList<>(Arrays.asList(2, 2, 1, 1, 3, 3));
        sortedList = SortUtil.generateBestCase(5);
        reversedList = SortUtil.generateWorstCase(5);
        // Initialize PhoneNumber list
        phoneNumbers = new ArrayList<>(Arrays.asList(
                new PhoneNumber("123-456-7890"),
                new PhoneNumber("987-654-3210"),
                new PhoneNumber("456-789-0123"),
                new PhoneNumber("invalid-number")
        ));
    }


    @Test
    public void testGenerateBestCase() {
        ArrayList<Integer> list = SortUtil.generateBestCase(10);
        assertEquals(10, list.size());
        assertTrue(isSorted(list, cmp));
    }

    @Test
    public void testGenerateAverageCase() {
        assertEquals(5, randomList.size());
    }

    @Test
    public void testGenerateWorstCase() {
        assertEquals(5, reversedList.size());
        for (int i = 0; i < reversedList.size() - 1; i++) {
            assertTrue(reversedList.get(i) > reversedList.get(i + 1));
        }
    }

    @Test
    public void testMergeSort() {
        SortUtil.mergesort(randomList, cmp,10);
        assertTrue(isSorted(randomList, cmp));
    }

    @Test
    public void testMergeSortWithEmptyList() {
        SortUtil.mergesort(emptyList, cmp,10);
        assertTrue(emptyList.isEmpty());
    }

    @Test
    public void testQuickSortWithRandomElementPivot() {
        SortUtil.quicksort(randomList, cmp, RANDOM_ELEMENT);
        assertTrue(isSorted(randomList, cmp));
    }

    @Test
    public void testQuickSortWithDuplicates() {
        SortUtil.quicksort(duplicateList, cmp,RANDOM_ELEMENT);
        assertTrue(isSorted(duplicateList, cmp));
    }

    @Test
    public void testQuickSortWithSortedData() {
        SortUtil.quicksort(sortedList, cmp,RANDOM_ELEMENT);
        assertTrue(isSorted(sortedList, cmp));
    }

    @Test
    public void testQuickSortWithReversedData() {
        SortUtil.quicksort(reversedList, cmp,RANDOM_ELEMENT);
        assertTrue(isSorted(reversedList, cmp));
    }
    @Test
    public void testPhoneNumberSortingWithQuickSort() {
        SortUtil.quicksort(phoneNumbers, phoneCmp, RANDOM_ELEMENT);
        assertTrue(isSorted(phoneNumbers, phoneCmp));
    }

    @Test
    public void testPhoneNumberSortingWithMergeSort() {
        SortUtil.mergesort(phoneNumbers, phoneCmp, 10);
        assertTrue(isSorted(phoneNumbers, phoneCmp));
    }

    private static <T> boolean isSorted(ArrayList<T> list, Comparator<? super T> cmp){
        for (int i = 1; i < list.size(); i++) {
            if (cmp.compare(list.get(i - 1), list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }
}
