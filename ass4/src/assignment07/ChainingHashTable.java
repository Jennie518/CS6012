package assignment07;

import java.util.Collection;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String>{
    private LinkedList<String>[] storage;
    private HashFunctor functor;
    private int size;
    private int collisions;
    @SuppressWarnings("unchecked")
    public ChainingHashTable(int capacity, HashFunctor functor){
        // Initialize the storage with an array of buckets (LinkedLists).
        // Each bucket stores items with the same hash code.
        storage = (LinkedList<String>[]) new LinkedList[capacity];
        this.functor = functor;
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
        int index = Math.abs(functor.hash(item)) % storage.length;

        // If the bucket is non-empty, and we're adding a new item, it's a collision.
        if (storage[index] != null && !storage[index].isEmpty()) {
            collisions++;  // This is where we count a collision.
        } else if (storage[index] == null) {
            // If the bucket is empty, initialize it with a new LinkedList.
            storage[index] = new LinkedList<>();
        }

        // Check if the item already exists in the bucket.
        LinkedList<String> list = storage[index];
        for (String existingItem : list) {
            if (existingItem.equals(item)) {
                return false; // Item already exists, no need to add.
            }
        }

        // Item doesn't exist, so we add it to the bucket.
        list.add(item);
        size++;
        return true; // Item was successfully added.
    }
    // A getter for the collisions might be useful for your testing.
    public int getCollisions() {
        return collisions;
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
        boolean changed = false;
        for(String item: items) {
            if (item != null) {
                add(item);
                changed = true; // 如果成功添加了项，将 changed 设置为 true
            }
        }
        return changed;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[i].clear(); // 清空链表
            }
        }
        size = 0; // 更新 size
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
        for (LinkedList<String> bucket : storage) {
            if (bucket != null && bucket.contains(item)) {
                return true;
            }
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
        boolean removed = false; // 用于标记是否删除了项
        for(LinkedList<String> bucket:storage){
            if(bucket != null && bucket.contains(item)){
                while (bucket.remove(item)) { // 循环删除所有匹配的项
                    size--;
                    removed = true; // 设置标记为 true，表示删除了项
                }
            }
        }
        return removed;
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
        boolean removed = false; // 用于标记是否删除了任何项
        for (String item : items) {
            if (remove(item)) { // 尝试删除项，并检查是否删除成功
                removed = true; // 如果成功删除了任何项，将标记设置为 true
            }
        }
        return removed; // 返回是否删除了任何项
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return size;
    }
}
