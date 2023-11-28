package assignment06;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T>{
    private Node root;
    private class Node {
        private T e;
        private Node left;
        private Node right;

        public Node(T e) {
            this.e = e;
//            this.left = null;
//            this.right = null;
        }
    }
    // 无参数构造函数
    public BinarySearchTree() {
        root = null; // 初始化一个空树
    }
    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean add(T item) {
        if(item == null){
            throw new NullPointerException();
        }
        if(contains(item)){
            return false;
        }

        Node newNode = new Node(item);
        if (root == null) {
            root = newNode;
            return true;
        }

        Node current = root;
        while (true) {
            int cmp = item.compareTo(current.e);
            if (cmp < 0) {
                if (current.left == null) {
                    current.left = newNode;
                    return true;
                }
                current = current.left;
            } else if (cmp > 0) {
                if (current.right == null) {
                    current.right = newNode;
                    return true;
                }
                current = current.right;
            }
        }
    }

//     public boolean add(T item) {
//        // Check if the item is null, and throw NullPointerException if it is
//        if(item == null){
//            throw new NullPointerException();
//        }
//        if(contains(item)){
//            return false;
//        }
//        // Call the recursive helper method to add the item
//        root = addRecursive(root, item);
//        return contains(item);
//    }
//    private Node addRecursive(Node current, T item){
//        // If the current node is null, a new node is created with the item
//        if(current == null){
//            return new Node(item);
//        }
//        // Compare the item with the data at the current node
//        int cmp = item.compareTo(current.e);
//
//        // If item is less than the current node's data, recur for the left subtree
//        if(cmp < 0){
//            current.left = addRecursive(current.left, item);
//        }
//        // If item is greater than the current node's data, recur for the right subtree
//        else if(cmp > 0){
//            current.right = addRecursive(current.right, item);
//        }
//        // If item is equal to the current node's data, do nothing (no duplicates in BST)
//        return current;
//    }


    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean addAll(Collection<? extends T> items) {
        boolean isChanged = false; // Flag to track if any item is added
        if(containsAll(items)){
            return false;
        }//avoid repeat element
        for(T item:items){
            if(item == null){// Check for null items and throw NullPointerException if found
                throw new NullPointerException();
            }
            if(add(item)){ // Attempt to add each item, update isChanged if the item is added
                isChanged =true;
            }
        }
        return isChanged;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        root = null;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean contains(T item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node current = root;
        while (current != null) {
            int cmp = item.compareTo(current.e);
            if (cmp == 0) {
                return true;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

//    public boolean contains(T item) {
//        if(item == null){
//            throw new NullPointerException();
//        }
//        return containsRecursive(root,item);
//    }
//    private boolean containsRecursive(Node current,T item){
//        if(current == null){
//            return false;
//        }
//        // Compare the item with the data at the current node
//        int cmp = item.compareTo(current.e);
//        if (cmp == 0) {// If item is equal to the current node's data, return true
//            return true;
//        }
//        // If item is greater than the current node's data, recur for the right subtree
//        else if(cmp > 0){
//            return containsRecursive(current.right, item);
//        }
//        else{
//            // If item is less than the current node's data, recur for the left subtree
//            return containsRecursive(current.left, item);
//        }
//    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean containsAll(Collection<? extends T> items) {
        for(T item : items){
            // Check for null items and throw NullPointerException if found
            if(item == null){
                throw new NullPointerException("Items in the collection cannot be null.");
            }
            // If any item is not contained in the set, return false
            if(!contains(item)){
                return false;
            }
        }
        // If all items are contained in the set, return true
        return true;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public T first() throws NoSuchElementException {
        if(root == null){
            throw new NoSuchElementException("The set is empty.");
        }
        // Start from the root and move to the leftmost node
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        // The leftmost node contains the smallest item
        return current.e;
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        // Check if the root node is null. If it is, the tree is empty.
        if(root == null){
            return true;
        }
        return false;
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public T last() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        // Start from the root and move to the rightmost node
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        // The rightmost node contains the greatest item
        return current.e;
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually removed); otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean remove(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (!contains(item)) {
            return false; // 如果元素不存在，直接返回false
        }
        root = removeRecursive(root, item);
        return true; // 元素存在并已被移除，返回true
    }
    private Node removeRecursive(Node current,T item){
        if(current==null){
            return null;
        }
        int cmp = item.compareTo(current.e);
        if (cmp < 0) {
            current.left = removeRecursive(current.left, item);
        } else if (cmp > 0) {
            current.right = removeRecursive(current.right, item);
        }else {
            // Node to be deleted is found
            if (current.left == null && current.right == null) { // Case 1: No children
                return null;
            } else if (current.left == null) { // Case 2: One child (right)
                return current.right;
            } else if (current.right == null) { // Case 3: One child (left)
                return current.left;
            } else { // Case 4: Two children
                //Can choose predecessor or successor of item in Inorder Traversal
                //In this case I use successor: find the smallest value in the right subtree
                Node smallestNode = findSmallestNode(current.right);
                //copy the smallest node to current position
                current.e = smallestNode.e;
                //remove this smallest node from original position
                current.right = removeRecursive(current.right,smallestNode.e);
            }
        }
        return current;//return the changed root
    }
    private Node findSmallestNode(Node root) {
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually removed); otherwise,
     * returns false
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean removeAll(Collection<? extends T> items) {
        boolean isRemoved = false;
        for(T item : items){
            if(item == null){
                throw new NullPointerException();
            }
            if (remove(item)){
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return countNodes(root);
    }
    private int countNodes(Node node){
        if(node == null){
            return 0;
        }
        return 1 + countNodes(node.left)+countNodes(node.right);
        //current node add up nums of both left nodes and right nodes
    }

    /**
     * Returns an ArrayList containing all of the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        inorderTraversal(root,list);
        return list;
    }
    private void inorderTraversal(Node node, ArrayList<T> list){
        if(node!=null){
            inorderTraversal(node.left,list);//1. traverse left subtree
            list.add(node.e); //2.drop by current node
            inorderTraversal(node.right,list);//3.traverse right subtree
        }
    }

}
