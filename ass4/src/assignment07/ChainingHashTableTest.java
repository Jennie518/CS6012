package assignment07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChainingHashTableTest {

    private ChainingHashTable hashTable;
    private HashFunctor hashFunctor;
    private ArrayList<String> items = new ArrayList<>();
    @BeforeEach
    void setUp(){
        hashFunctor = new BadHashFunctor(); // 或者您自己的散列函数实现
        hashTable = new ChainingHashTable(10, hashFunctor);
        items.add("a");
        items.add("b");
        items.add("c");
    }
    @Test
    void TestAddAndContains(){
        assertTrue(hashTable.add("test"));
        assertTrue(hashTable.contains("test"));
        assertFalse(hashTable.add("test")); // 添加重复元素应返回 false
    }
    @Test
    void TestAddAllAndContainsAll(){
        assertTrue(hashTable.addAll(items));
        assertTrue(hashTable.containsAll(items));
    }
    @Test
    public void testClear() {
        hashTable.add("test");
        hashTable.clear();
        assertTrue(hashTable.isEmpty());
    }

    @Test
    public void testContains() {
        hashTable.add("test");
        assertTrue(hashTable.contains("test"));
        assertFalse(hashTable.contains("notInTable"));
    }

    @Test
    public void testRemove() {
        hashTable.add("test");
        assertTrue(hashTable.remove("test"));
        assertFalse(hashTable.remove("test")); //move item which not exist return false
    }
    @Test
    void testRemoveAll(){
        hashTable.addAll(items);
        assertTrue(hashTable.removeAll(items));
        assertFalse(hashTable.removeAll(items)); //move item which not exist return false

    }


    @Test
    public void testSize() {
        assertEquals(0, hashTable.size());
        hashTable.add("test1");
        hashTable.add("test2");
        assertEquals(2, hashTable.size());
    }

}