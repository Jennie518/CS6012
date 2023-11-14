import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MedianTest {

    //test Comparator
    @Test
    public void testMedianWithComparator() {
        String[] oddArray = {"apple", "banana", "cherry"};
        String[] evenArray = {"dog", "cat", "bird", "ant"};

        String expectedOddMedian = "banana";
        String expectedEvenMedian = "bird";

        assertEquals(expectedOddMedian, Median.medianWithComparator(oddArray, Comparator.naturalOrder()));
        assertEquals(expectedEvenMedian, Median.medianWithComparator(evenArray, Comparator.naturalOrder()));
    }
    @Test
    public void testEmptyComparator(){
        String[] emptyArray = {};
        Assertions.assertNull(Median.medianWithComparator(emptyArray, Comparator.naturalOrder()));
    }
    //test comparable
    @Test
    public void testMedianWithComparable() {
        String[] oddArray = {"apple", "banana", "cherry"};
        String[] evenArray = {"dog", "cat", "bird", "ant"};

        String expectedOddMedian = "banana";
        String expectedEvenMedian = "bird";

        assertEquals(expectedOddMedian, Median.medianWithComparable(oddArray));
        assertEquals(expectedEvenMedian, Median.medianWithComparable(evenArray));
    }
    @Test
    public void testEmptyComparable(){
        String[] emptyArray = {};
        Assertions.assertNull(Median.medianWithComparable(emptyArray));
    }



}