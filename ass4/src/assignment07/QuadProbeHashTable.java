package assignment07;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

public class QuadProbeHashTable implements Set<String> {
    private String[] table;
    private int size;
    private HashFunctor functor;

    public QuadProbeHashTable(int capacity, HashFunctor functor) {
        this.functor = functor;
        this.size = 0;
        int primeCapacity = getPrimeCapacity(capacity);
        this.table = new String[primeCapacity];
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(String item) {
        int hash = Math.abs(functor.hash(item));
        int index = hash % table.length;
        int i = 1;

        while (table[index] != null) {
            if (table[index].equals(item)) {
                // Item already exists in the table
                return false;
            }
            // Quadratic probing
            index = (hash + i * i) % table.length;
            i++;
        }

        table[index] = item;
        size++;

        if (getLoadFactor() > 0.5) {
            resize();
        }

        return true;
    }


    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     */
    @Override
    public boolean addAll(Collection<? extends String> items) {
        boolean isAdded = false;
        for(String item:items){
            if(add(item)){
                isAdded = true;
            }
        }
        return isAdded;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
//        for(int i = 0;i < table.length;i++){
//            table[i] = null;
//        }
//        size = 0;
        Arrays.fill(table, null); // 使用Arrays.fill方法将整个数组的所有元素设置为null
        size = 0;                 // 将存储的元素数量重置为0
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     */
    @Override
    public boolean contains(String item) {
        int hash = Math.abs(functor.hash(item));
        int index = hash % table.length;
        int i = 1;
        while (table[index] != null) {
            if (table[index].equals(item)) {
                // Item  exists in the table
                return true;
            }
            // Quadratic probing
            index = (hash + i * i) % table.length;
            i++;
        }
        return false;
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends String> items) {
        for(String item:items){
            if(!contains(item)){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually removed); otherwise, returns false
     */
    @Override
    public boolean remove(String item) {
        int hash = Math.abs(functor.hash(item));
        int index = hash % table.length;
        int i = 1;
        while (table[index] != null) {
            if (table[index].equals(item)) {
                // Item  exists in the table
                table[index] = null;
                return true;
            }
            // Quadratic probing
            index = (hash + i * i) % table.length;
            i++;
        }
        return false;
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually removed); otherwise,
     * returns false
     */
    @Override
    public boolean removeAll(Collection<? extends String> items) {
        boolean isRemoved = false;
        for(String item:items){
            remove(item);
            isRemoved = true;
        }
        return isRemoved;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return size;
    }
    private int getPrimeCapacity(int capacity) {
        // Use BigInteger to find the next prime number larger than capacity
        return BigInteger.valueOf(capacity).nextProbablePrime().intValue();
    }

    private void resize() {
        // Resize the hash table and rehash all elements
        QuadProbeHashTable newTable = new QuadProbeHashTable(table.length * 2, functor);
        for (String item : table) {
            if (item != null) {
                newTable.add(item);
            }
        }
        this.table = newTable.table;
    }

    private double getLoadFactor() {
        return (double) size / table.length;
    }
}
