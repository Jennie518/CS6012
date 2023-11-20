package assignment05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Iterator;


/**
 * This class contains tests for the SinglyLinkedList class.
 */
public class SinglyLinkedListTest {

    private SinglyLinkedList<Integer> list;

    /**
     * Setup method that initializes a new SinglyLinkedList instance before each test.
     */
    @BeforeEach
    public void setup() {
        list = new SinglyLinkedList<>();
    }

    /**
     * Test to ensure the initial state of the list is empty.
     */
    @Test
    public void testInitialListState() {
        Assertions.assertTrue(list.isEmpty()); // Checks if the list is empty
        Assertions.assertEquals(0, list.size()); // Checks if the size of the list is 0
    }

    /**
     * Test adding elements to the list using the insertFirst method.
     */
    @Test
    public void testInsertFirst() {
        list.insertFirst(1);
        Assertions.assertEquals(1, list.size()); // Checks if the size is 1 after insertion
        Assertions.assertEquals(1, list.getFirst()); // Checks if the first element is the one just added
    }

    /**
     * Test the functionality of inserting elements at specified positions.
     */
    @Test
    public void testInsertAtPosition() {
        list.insertFirst(1);
        list.insert(1, 2); // Inserts 2 at index 1
        Assertions.assertEquals(2, list.size()); // Checks if the size is 2 after insertion
        Assertions.assertEquals(2, list.get(1)); // Checks if the element at index 1 is 2
    }

    /**
     * Test retrieving the first element from the list.
     */
    @Test
    public void testGetFirst() {
        list.insertFirst(1);
        Assertions.assertEquals(1, list.getFirst()); // Verifies the first element is 1
    }

    /**
     * Test deleting the first element from the list.
     */
    @Test
    public void testDeleteFirst() {
        list.insertFirst(1);
        list.insertFirst(2);
        Integer deleted = list.deleteFirst();
        Assertions.assertEquals(2, deleted); // Verifies the deleted element is the one that was first
        Assertions.assertEquals(1, list.size()); // Checks if the size is 1 after deletion
    }

    /**
     * Test deleting elements at specified positions.
     */
    @Test
    public void testDeleteAtPosition() {
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        Integer deleted = list.delete(1); // Deletes the element at index 1
        Assertions.assertEquals(2, deleted); // Verifies the deleted element is the one at index 1
        Assertions.assertEquals(2, list.size()); // Checks if the size is 2 after deletion
    }

    /**
     * Test the indexOf method to find the position of a given element.
     */
    @Test
    public void testIndexOf() {
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        int index = list.indexOf(2); // Finds the index of element 2
        Assertions.assertEquals(1, index); // Verifies that the index of element 2 is 1
    }

    /**
     * Test the toArray method to ensure it correctly converts the list to an array.
     */
    @Test
    public void testToArray() {
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        Object[] array = list.toArray();
        Assertions.assertArrayEquals(new Object[]{3, 2, 1}, array); // Verifies the array representation of the list
    }

    /**
     * Test the iterator functionality to ensure it can iterate over the list and remove elements.
     */
    @Test
    public void testIterator() {
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        Iterator<Integer> it = list.iterator();
        Assertions.assertTrue(it.hasNext()); // Checks if the iterator has more elements
        Integer next = it.next();
        Assertions.assertEquals(3, next); // Verifies the first element returned by the iterator
        it.remove();
        Assertions.assertEquals(2, list.size()); // Checks if the size is 2 after removal
        Assertions.assertEquals(2, list.getFirst()); // Verifies the first element in the list is now 2
    }
}
