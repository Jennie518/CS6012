package assignment03;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchSetTest {
    private BinarySearchSet<Integer> set;

    @BeforeEach
    public void setUp() {
        set = new BinarySearchSet<>();
    }

    @Test
    public void testAddAndContains() {
        assertTrue(set.add(5));
        assertTrue(set.contains(5));
        assertFalse(set.add(5)); // 重复添加应该返回 false
    }

    @Test
    public void testAddOrder() {
        set.add(3);
        set.add(1);
        set.add(4);
        set.add(2);
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(3));
        assertTrue(set.contains(4));
        assertEquals(4, set.size());
    }
    @Test
    public void testRemove() {
        set.add(5);
        assertTrue(set.remove(5));
        assertFalse(set.remove(5)); // 再次删除应该返回 false
    }

    @Test
    public void testSize() {
        assertEquals(0, set.size());
        set.add(1);
        set.add(2);
        assertEquals(2, set.size());
    }

    @Test
    public void testClear() {
        set.add(1);
        set.add(2);
        set.clear();
        assertEquals(0, set.size());
    }

    @Test
    public void testIterator() {
        set.add(1);
        set.add(3);
        set.add(2);

        Iterator<Integer> it = set.iterator();
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(1), it.next());
        assertEquals(Integer.valueOf(2), it.next());
        assertEquals(Integer.valueOf(3), it.next());
        assertFalse(it.hasNext());
    }

}