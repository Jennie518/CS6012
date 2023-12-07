package assignment03;

import java.util.*;

public class BinarySearchSet<E> implements SortedSet<E>,Iterable<E> {
    private E[] data;
    private int capacity;
    private int size;
    private Comparator<? super E> comparator;

    public BinarySearchSet() {
        capacity = 10;
        this.data = (E[]) new Object[capacity]; // 初始大小可以根据需要调整
        this.size = 0;
        this.comparator = null; // 自然排序
    }

    public BinarySearchSet(Comparator<? super E> comparator) {
        capacity = 10;
        this.data = (E[]) new Object[capacity];
        this.size = 0;
        this.comparator = comparator;
    }

    @Override
    public Comparator comparator() {
        return comparator;
    }

    @Override
    public E first() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Set is empty");
        return data[0];
    }

    @Override
    public E last() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Set is empty");
        return data[size - 1];
    }

    @Override
    public boolean add(E element) {
        if (contains(element)) {
            return false;
        }
        if (data.length == size) {
            capacity = capacity * 2;
            E[] newArray = (E[]) new Object[capacity];
            for (int i = 0; i < data.length; i++) {
                newArray[i] = data[i];
            }
            data = newArray;
        }
        int insertIndex = findInsertIndex(element);
        //begin from insert position, element shift
        if (size - insertIndex > 0) {
            System.arraycopy(data, insertIndex, data, insertIndex + 1, size - insertIndex);
        }
        data[insertIndex] = element;//insert new element
        size++;
        return true;
    }

    // 辅助方法：找到插入新元素的索引
    private int findInsertIndex(E element) {
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int cmp = (comparator == null) ? ((Comparable<? super E>)data[mid]).compareTo(element)
                    : comparator.compare(data[mid], element);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                // 在找到相同元素的情况下，返回 mid
                return mid;
            }
        }
        return low; // 如果元素不存在，则 low 是插入位置
    }

    /**
     * Adds all of the elements in the specified collection to this set if they
     * are not already present and not set to null.
     *
     * @param elements collection containing elements to be added to this set
     * @return true if this set changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends E> elements) {
        boolean isChanged = false;
        for (E element : elements) {
            if (element != null) {
                if (add(element)) {
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }


    @Override
    public void clear() {
        capacity = 10;
        data = (E[]) new Object[capacity];
        size = 0;
        comparator = null;
    }

    @Override
    public boolean contains(E element) {
        if (element == null) {
            return false; // 或者抛出异常，取决于您的设计决策
        }
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = (comparator == null) ? ((Comparable<E>)data[mid]).compareTo(element)
                    : comparator.compare(data[mid], element);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return true; // 找到元素
            }
        }
        return false; // 没有找到元素
    }

    @Override
    public boolean containsAll(Collection<? extends E> elements) {//use binary search
        for (E element : elements) {
            if (!this.contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int currentIndex = 0;
            private boolean canRemove = false;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                canRemove = true;
                return data[currentIndex++];
            }
            @Override
            public void remove() {
                if (!canRemove) {
                    throw new IllegalStateException();
                }
                BinarySearchSet.this.remove(data[--currentIndex]);
                canRemove = false;
            }

        };
    }

    @Override
    public boolean remove(E element) {
        if (element == null) {
            return false;
        }
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp =(comparator == null) ? ((Comparable<E>)data[mid]).compareTo(element)
                    : comparator.compare(data[mid], element);
            if (cmp == 0) {
                int numMoved = size - mid - 1;
                if (numMoved > 0) {
                    System.arraycopy(data, mid + 1, data, mid, numMoved);
                }
                data[--size] = null; // 清除最后一个元素的引用
                return true;
            }
            if (cmp < 0) {
                low = mid + 1;
            }
            if (cmp > 0) {
                high = mid - 1;
            }
        }
        return false;

    }

    @Override
    public boolean removeAll(Collection<? extends E> elements) {
        boolean isChanged = false;
        for (E element : elements) {
            if (remove(element)) {
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] toArray() {
        Object[] objectArray = new Object[size];
        System.arraycopy(data, 0, objectArray, 0, size);
        return (E[]) objectArray;
    }

}