package assignment06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    private BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    private BinarySearchTree<Integer> emptyBst = new BinarySearchTree<>();
    private ArrayList<Integer> items = new ArrayList<>();
    private ArrayList<Integer> emptyItems = new ArrayList<>();
    @BeforeEach
    void setUp() {
        bst.add(5);
        bst.add(3);
        bst.add(7);
        items.add(5);
        items.add(3);
        items.add(7);
        emptyItems.add(null);
    }
    @Test
    void testEmptyBst(){
        assertTrue(emptyBst.isEmpty());
    }

    @Test
    void testFirst(){
        assertEquals(bst.first(),3);
    }
    @Test
    void testLast(){
        assertEquals(bst.last(),7);
    }
    @Test
    void testRemoveall(){
        bst.removeAll(items);
        assertFalse(bst.containsAll(items));
    }

    @Test
    void testAddAndContains() {
        assertTrue(bst.contains(5));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(7));
        assertFalse(bst.contains(10)); // 测试一个不存在的元素
    }
    @Test
    void testAddallWithEmptyCollections() {
        assertThrows(NullPointerException.class, () -> {
            bst.addAll(emptyItems);
        });
    }
    @Test
    void testAddAll(){
        ArrayList<Integer> newItems = new ArrayList<>();
        newItems.add(2);
        newItems.add(1);
        bst.addAll(newItems);
        assertTrue(bst.containsAll(newItems));
    }
    @Test
    void testAddOnEmptyBSTAndLast(){
        emptyBst.add(3);
        emptyBst.add(7);
        assertEquals(emptyBst.last(),7);
        assertEquals(emptyBst.size(),2);
    }
    @Test
    void testContainsAll(){
        assertTrue(bst.containsAll(items));
    }
    @Test
    void testAddWithexistItem() {
        bst.add(5);//add exist item
        assertEquals(3, bst.size());
    }
    @Test
    void testRemove() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(5);
        bst.add(3);
        bst.add(7);

        assertTrue(bst.remove(3));
        assertFalse(bst.contains(3));
    }
    @Test
    void testRemoveWithNotExistItem() {
        assertFalse(bst.remove(10));
    }
    @Test
    void testRemoveWithExistItem() {
        assertTrue(bst.remove(3));
        assertFalse(bst.contains(3));
    }
    @Test
    void testSize() {
        assertEquals(3, bst.size());
    }
    @Test
    void testToArrayList() {
        ArrayList<Integer> list = bst.toArrayList();
        assertEquals(Arrays.asList(3, 5, 7), list);
    }
}