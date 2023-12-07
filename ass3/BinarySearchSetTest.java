package assignment03;
import static org.junit.Assert.*;
import org.junit.*;

import java.util.*;

public class BinarySearchSetTest {

    // 创建一个 BinarySearchSet 对象，用于测试
    BinarySearchSet<Integer> set;

    @Before
    public void setUp() {
        set = new BinarySearchSet<>();
    }

    @Test
    public void testAdd() {
        assertTrue(set.add(5));
        assertTrue(set.add(3));
        assertTrue(set.add(7));
        assertFalse(set.add(5)); // 重复元素不应该添加成功
    }

    @Test
    public void testRemove() {
        set.add(5);
        set.add(3);
        set.add(7);

        assertTrue(set.remove(3));
        assertFalse(set.remove(8)); // 不存在的元素不应该删除成功
    }

    @Test
    public void testContains() {
        set.add(5);
        set.add(3);
        set.add(7);

        assertTrue(set.contains(5));
        assertFalse(set.contains(8)); // 不存在的元素应该返回 false
    }

    @Test
    public void testSize() {
        assertEquals(0, set.size());
        set.add(5);
        set.add(3);
        set.add(7);
        assertEquals(3, set.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(set.isEmpty());
        set.add(5);
        assertFalse(set.isEmpty());
    }

    @Test
    public void testClear() {
        set.add(5);
        set.add(3);
        set.add(7);
        set.clear();
        assertTrue(set.isEmpty());
    }

    @Test
    public void testIterator() {
        set.add(5);
        set.add(3);
        set.add(7);

        Iterator<Integer> iterator = set.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(7), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testAddAll() {
        List<Integer> list = Arrays.asList(5, 3, 7);
        assertTrue(set.addAll(list));
        assertFalse(set.addAll(list)); // 重复添加应该返回 false
    }

    @Test
    public void testRemoveAll() {
        set.add(5);
        set.add(3);
        set.add(7);

        List<Integer> list = Arrays.asList(3, 7);
        assertTrue(set.removeAll(list));
        assertFalse(set.removeAll(list)); // 重复删除应该返回 false
    }


    @Test
    public void testContainsAll() {
        set.add(5);
        set.add(3);
        set.add(7);

        List<Integer> list = Arrays.asList(3, 7);
        assertTrue(set.containsAll(list));
        list = Arrays.asList(3, 8);
        assertFalse(set.containsAll(list)); // 包含不存在的元素应该返回 false
    }
    @Test
    public void testConstructorWithComparator() {
        Comparator<Integer> reversedComparator = Comparator.reverseOrder();
        BinarySearchSet<Integer> setWithComparator = new BinarySearchSet<>(reversedComparator);
        assertNotNull(setWithComparator);
        assertEquals(reversedComparator, setWithComparator.comparator());
    }

    @Test(expected = NoSuchElementException.class)
    public void testFirstWithEmptySet() {
        set.first(); // Should throw NoSuchElementException because the set is empty.
    }

    @Test(expected = NoSuchElementException.class)
    public void testLastWithEmptySet() {
        set.last(); // Should throw NoSuchElementException because the set is empty.
    }

    @Test
    public void testFirstAndLastWithNonEmptySet() {
        set.add(5);
        set.add(3);
        set.add(7);

        assertEquals(Integer.valueOf(3), set.first());
        assertEquals(Integer.valueOf(7), set.last());
    }
    @Test
    public void testToArray() {
        set.add(5);
        set.add(3);
        set.add(7);

        Object[] objectArray = set.toArray();

        // Assert that each element in the array is in the set.
        for (Object element : objectArray) {
            assertTrue(set.contains((Integer) element)); // Cast each element to Integer
        }
    }
}