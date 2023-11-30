package lab06;

import java.util.TreeSet;

// 实现一个优先级队列，使用 Java 的 TreeSet 作为底层数据结构
public class PriorityQueueTreeSet<T extends Comparable<T>> implements PriorityQueue<T> {
    private TreeSet<T> treeSet;  // 使用 TreeSet 存储队列元素

    // 构造函数，初始化一个空的 TreeSet
    public PriorityQueueTreeSet() {
        treeSet = new TreeSet<>();
    }

    // 添加元素到优先级队列
    // TreeSet 自动根据元素的自然顺序（或指定的 Comparator）排序元素
    @Override
    public void add(T element) {
        treeSet.add(element);
    }

    // 移除并返回优先级队列中的最小元素
    // pollFirst 方法从 TreeSet 中移除并返回第一个（即最小的）元素
    @Override
    public T removeMin() {
        return treeSet.pollFirst();
    }

    // 检查优先级队列是否为空
    @Override
    public boolean isEmpty() {
        return treeSet.isEmpty();
    }
}
