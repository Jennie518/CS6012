//my partner is XiaoHan Chen
import java.util.Arrays;
import java.util.Comparator;
public class Median {

    // Method to compute the median of an array using a Comparator
    public static <T> T medianWithComparator(T[] array, Comparator<? super T> comparator) {
        if (array == null || array.length == 0) {
            return null;
        }

        Arrays.sort(array, comparator);

        int middle = array.length / 2;

        if (array.length % 2 == 0) {
            return array[middle - 1]; // could also be array[middle] depending on definition
        } else {
            return array[middle];
        }
    }

    public static <T extends Comparable<T>> T medianWithComparable(T[]array){
        if (array == null || array.length == 0) {
            return null;
        }

        Arrays.sort(array);

        int middle = array.length / 2;

        if (array.length % 2 == 0) {
            return array[middle - 1]; // could also be array[middle] depending on definition
        } else {
            return array[middle];
        }

    }
}

