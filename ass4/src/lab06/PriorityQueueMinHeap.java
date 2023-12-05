package lab06;

import java.util.ArrayList;

public class PriorityQueueMinHeap<T extends Comparable<T>> implements PriorityQueue<T> {
    private ArrayList<T> heap;

    // 构造一个空的优先级队列
    public PriorityQueueMinHeap() {
        heap = new ArrayList<>();
    }

    // 构造一个包含元素的优先级队列，并将其整理成堆
    public PriorityQueueMinHeap(ArrayList<T> elements) {
        heap = new ArrayList<>(elements);
        heapify();
    }

    // 添加一个元素到优先级队列，并保持堆的性质
    @Override
    public void add(T element) {
        heap.add(element);
        percolateUp(heap.size() - 1);
    }

    // 移除并返回优先级队列中最小的元素
    @Override
    public T removeMin() {
        if (isEmpty()) {
            return null;
        }
        T minElement = heap.get(0);

        // Move the last element to the root
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        // Percolate down to maintain the heap property
        if (!isEmpty()) {
            percolateDown(0);
        }

        return minElement;
    }

    // 检查优先级队列是否为空
    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // 将一个无序列表转换成最小堆
    private void heapify() {
        for (int i = parentIndex(heap.size() - 1); i >= 0; i--) {
            percolateDown(i);
        }
    }

    // 将元素向上移动，直到它找到合适的位置
    private void percolateUp(int index) {
        T element = heap.get(index);
        while (index > 0 && element.compareTo(heap.get(parentIndex(index))) < 0) {
            heap.set(index, heap.get(parentIndex(index)));
            index = parentIndex(index);
        }
        heap.set(index, element);
    }

    // 将元素向下移动t，直到它找到合适的位置
    private void percolateDown(int index) {
        T element = heap.get(index);
        int childIndex = leftChildIndex(index);

        while (childIndex < heap.size()) {
            int minChildIndex = childIndex; // 假设左孩子更小

            int rightChildIndex = rightChildIndex(index);
            if (rightChildIndex < heap.size() && heap.get(rightChildIndex).compareTo(heap.get(minChildIndex)) < 0) {
                minChildIndex = rightChildIndex;
            }

            if (heap.get(minChildIndex).compareTo(element) >= 0) {
                break;
            }

            heap.set(index, heap.get(minChildIndex));
            index = minChildIndex;
            childIndex = leftChildIndex(index);
        }
        heap.set(index, element);
    }

    // 获取给定索引的父节点索引
    private int parentIndex(int i) {
        return (i - 1) / 2;
    }

    // 获取给定索引的左孩子索引
    private int leftChildIndex(int i) {
        return 2 * i + 1;
    }

    // 获取给定索引的右孩子索引
    private int rightChildIndex(int i) {
        return 2 * i + 2;
    }
}
