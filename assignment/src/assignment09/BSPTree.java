package assignment09;
import java.util.ArrayList;
public class BSPTree {

    public class Node {
        Segment segment;
        Node left,right;
        Node(Segment segment){
            this.segment = segment;
        }
    }
    private Node root;
    // 默认构造函数
    public BSPTree() {
        root = null;
    }
    // 批量构造算法的构造函数
    public BSPTree(ArrayList<Segment> segments) {
        // 实现批量构造算法
         for(Segment segment:segments){
             insert(segment);
         }
    }
    public void insert(Segment segment){
        if (root == null) {
            root = new Node(segment);
            return;
        }

        Node current = root;
        while (true) {
            int side = current.segment.whichSide(segment);

            if (side <= 0) {
                if (current.left == null) {
                    current.left = new Node(segment);
                    break;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(segment);
                    break;
                }
                current = current.right;
            }
        }
    }

    private Node insert(Node node, Segment segment) {
        if (node == null) {
            return new Node(segment);
        }

        int side = node.segment.whichSide(segment);

        if (side <= 0) {
            // New segment on the left or overlapping, insert in the left subtree
            node.left = insert(node.left, segment);
        } else if (side > 0) {
            // New segment on the right, insert in the right subtree
            node.right = insert(node.right, segment);
        }

        return node;
    }
    public void traverseFarToNear(double x,double y, SegmentCallback callback){
            traverseFarToNear(root, x, y, callback);
        }


    public void traverseFarToNear(Node node, double x, double y, SegmentCallback callback) {
        if (node == null) {
            return;
        }

        int side = node.segment.whichSidePoint(x, y);
        // 根据观察点的位置动态决定遍历顺序
        if (side <= 0) {
            // 观察点在节点线段的左侧或在线段上，先遍历右子树（更远的）
            traverseFarToNear(node.right, x, y, callback);
            callback.callback(node.segment);
            traverseFarToNear(node.left, x, y, callback);
        } else {
            // 观察点在节点线段的右侧，先遍历左子树（更远的）
            traverseFarToNear(node.left, x, y, callback);
            callback.callback(node.segment);
            traverseFarToNear(node.right, x, y, callback);
        }
    }

    // 碰撞检测
    public Segment collision(Segment query) {
        return collision(root, query);
    }

    public Segment collision(Node node, Segment query) {
        if (node == null) {
            return null;
        }

        if (node.segment.intersects(query)) {
            return node.segment;
        }

        Segment leftResult = collision(node.left, query);
        if (leftResult != null) {
            return leftResult;
        }

        return collision(node.right, query);
    }
}