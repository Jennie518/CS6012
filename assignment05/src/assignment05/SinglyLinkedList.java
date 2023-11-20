package assignment05;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {
        E element;  // The data stored in the node.
        Node<E> next;  // Reference to the next node in the list.

        Node(E element) {
            this.element = element;
            this.next = null;  // Initially, the next node is null.
        }
    }

    private Node<E> head;  // The first node of the list.
    private int size;  // The number of elements in the list.

    public SinglyLinkedList() {
        head = null;  // Initially, the list is empty, so head is null.
        size = 0;  // And the size is 0.
    }

    // Adds an element at the beginning of the list.
    @Override
    public void insertFirst(E element) {
        Node<E> newNode = new Node<>(element);  // Create a new node.
        newNode.next = head;  // New node points to the current head.
        head = newNode;  // New node becomes the new head of the list.
        size++;  // Increase the size of the list.
    }

    // Inserts an element at a specified index.
    @Override
    public void insert(int index, E element) {
        // Check for valid index. Throw exception if the index is out of bounds.
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        // Special case: inserting at the head of the list.
        if (index == 0) {
            insertFirst(element);
            return;
        }
        // Go through the list to find the position to insert.
        Node<E> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        // Insert the new node at the specified position.
        Node<E> newNode = new Node<>(element);
        newNode.next = current.next;
        current.next = newNode;
        size++;  // Increase the size of the list.
    }

    // Returns the first element of the list.
    @Override
    public E getFirst() {
        // If the list is empty, throw an exception.
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return head.element;  // Return the element of the head node.
    }

    // Retrieves the element at a specified index.
    @Override
    public E get(int index) {
        // Check for valid index. Throw exception if index is out of bounds.
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // Traverse the list to find the element.
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;  // Return the found element.
    }

    // Deletes and returns the first element of the list.
    @Override
    public E deleteFirst() {
        // If the list is empty, throw an exception.
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        E element = head.element;  // Store the element to return.
        head = head.next;  // Move the head to the next node.
        size--;  // Decrease the size of the list.
        return element;  // Return the stored element.
    }

    // Deletes and returns the element at a specified index.
    @Override
    public E delete(int index) {
        // Check for valid index. Throw exception if index is out of bounds.
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // Special case: deleting the head of the list.
        if (index == 0) {
            return deleteFirst();
        }
        // Traverse the list to find the node before the one to delete.
        Node<E> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        // Delete the node and adjust the links.
        E element = current.next.element;
        current.next = current.next.next;
        size--;  // Decrease the size of the list.
        return element;  // Return the deleted element.
    }

    // Finds the index of a specific element. Returns -1 if not found.
    @Override
    public int indexOf(E element) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            // Check if the current element matches.
            if ((element == null && current.element == null) ||
                    (element != null && element.equals(current.element))) {
                return i;
            }
            current = current.next;  // Move to the next node.
        }
        return -1;  // Element not found.
    }

    // Returns the number of elements in the list.
    @Override
    public int size() {
        return size;
    }

    // Checks if the list is empty.
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Clears the list.
    @Override
    public void clear() {
        head = null;  // Set head to null to clear the list.
        size = 0;  // Reset the size.
    }

    // Converts the list to an array.
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.element;  // Add each element to the array.
            current = current.next;  // Move to the next node.
        }
        return array;
    }

    // Provides an iterator over the elements of the list.
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;  // Current node in the iteration.
            private Node<E> previous = null;  // Previous node in the iteration.
            private Node<E> previousOfPrevious = null;  // Node before the previous node.

            @Override
            public boolean hasNext() {
                return current != null;  // Check if there is another element.
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E element = current.element;  // Store the current element.
                // Update the previous and current nodes.
                previousOfPrevious = previous;
                previous = current;
                current = current.next;
                return element;  // Return the current element.
            }

            @Override
            public void remove() {
                // Check if the remove operation is valid.
                if (previous == null) {
                    throw new IllegalStateException();
                }
                // Adjust the links to remove the previous node.
                if (previousOfPrevious == null) {
                    head = current;
                } else {
                    previousOfPrevious.next = current;
                }
                previous = null;  // Reset previous node.
                size--;  // Decrease the size of the list.
            }
        };
    }
}
