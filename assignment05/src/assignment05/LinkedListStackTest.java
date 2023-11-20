package assignment05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

/**
 * Test class for LinkedListStack.
 */
public class LinkedListStackTest {

    private LinkedListStack<Integer> stack;

    // Set up a new stack before each test method is executed
    @BeforeEach
    void setUp() {
        stack = new LinkedListStack<>();
    }

    // Test to check if a newly created stack is empty
    @Test
    void testIsEmptyOnNewStack() {
        assertTrue(stack.isEmpty(), "New stack should be empty.");
    }

    // Test to check the size after pushing an element
    @Test
    void testPushAndSize() {
        stack.push(10);
        assertEquals(1, stack.size(), "Stack size should be 1 after one push.");
    }

    // Test to verify the correct element is returned by peek without removing it
    @Test
    void testPeekSingleElement() {
        stack.push(20);
        assertEquals(20, stack.peek(), "Peek should return the last element pushed.");
    }

    // Test to verify the correct element is returned and removed by pop
    @Test
    void testPopSingleElement() {
        stack.push(30);
        assertEquals(30, stack.pop(), "Pop should return the last element pushed.");
        assertTrue(stack.isEmpty(), "Stack should be empty after popping the only element.");
    }

    // Test to verify the order of elements after multiple push and pop operations
    @Test
    void testPushPopMultipleElements() {
        stack.push(40);
        stack.push(50);
        assertEquals(50, stack.pop(), "Pop should return the last element pushed.");
        assertEquals(40, stack.peek(), "Peek should return the new top element after a pop.");
    }

    // Test to ensure the stack is empty after calling clear
    @Test
    void testClear() {
        stack.push(60);
        stack.clear();
        assertTrue(stack.isEmpty(), "Stack should be empty after clear.");
    }

    // Test to ensure popping from an empty stack throws the appropriate exception
    @Test
    void testPopEmptyStack() {
        assertThrows(NoSuchElementException.class, stack::pop, "Pop should throw NoSuchElementException on empty stack.");
    }

    // Test to ensure peeking into an empty stack throws the appropriate exception
    @Test
    void testPeekEmptyStack() {
        assertThrows(NoSuchElementException.class, stack::peek, "Peek should throw NoSuchElementException on empty stack.");
    }

    // Test to ensure the size is correct after a series of push and pop operations
    @Test
    void testSizeAfterMultipleOperations() {
        stack.push(70);
        stack.push(80);
        stack.pop();
        stack.push(90);
        assertEquals(2, stack.size(), "Stack size should reflect the number of pushes and pops.");
    }
}
