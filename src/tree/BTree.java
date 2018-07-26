package tree;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dell on 2017/4/20.
 */
public class BTree {
    private int value;
    private BTree lchild, rchild;

    public BTree() {
        this.value = 0;
        this.lchild = null;
        this.rchild = null;
    }

    public BTree(int value) {
        this.value = value;
        this.lchild = null;
        this.rchild = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BTree getLchild() {
        return lchild;
    }

    public void setLchild(BTree lchild) {
        this.lchild = lchild;
    }

    public BTree getRchild() {
        return rchild;
    }

    public void setRchild(BTree rchild) {
        this.rchild = rchild;
    }

    /**
     * whether if the current node is leaf
     * @return
     */
    public boolean isLeaf() {
        if (this.lchild == null && this.rchild == null)
            return true;
        else
            return false;
    }


    /**
     * 生成一棵二叉排序树
     *           50
     *        /      \
     *      38       64
     *     / \     /   \
     *   30  40  58    73
     *   /\      /\   / \
     * 10 35   51 60 70 100
     *                    \
     *                    110
     */
    public static BTree createASortedTree() {
        final int NODES_NUM = 14;
        BTree[] treeNodes = new BTree[NODES_NUM];
        for (int i = 0; i < treeNodes.length; i++) {
            treeNodes[i] = new BTree();
        }

        treeNodes[0].setValue(50);
        treeNodes[1].setValue(38);
        treeNodes[2].setValue(64);
        treeNodes[3].setValue(30);
        treeNodes[4].setValue(40);
        treeNodes[5].setValue(58);
        treeNodes[6].setValue(73);
        treeNodes[7].setValue(10);
        treeNodes[8].setValue(35);
        treeNodes[9].setValue(51);
        treeNodes[10].setValue(60);
        treeNodes[11].setValue(70);
        treeNodes[12].setValue(100);
        treeNodes[13].setValue(110);

        for (int i = 0; i < 4; i++) {
            treeNodes[i].setLchild(treeNodes[2 * i + 1]);
            treeNodes[i].setRchild(treeNodes[2 * i + 2]);
        }

        for (int i = 5; i < 7; i++) {
            treeNodes[i].setLchild(treeNodes[2 * i - 1]);
            treeNodes[i].setRchild(treeNodes[2 * i]);
        }

        treeNodes[12].setRchild(treeNodes[13]);
        return treeNodes[0];
    }
    /**
     * 访问某个节点
     * @param node
     */
    public static void visit(BTree node) {
        System.out.printf("%6d", node.getValue());
    }
    /**
     * 先序遍历某二叉树，非递归方法
     * @param root 二叉树的根节点
     */
    public static void preOrder(BTree root) {
        Stack<BTree> stack;
        BTree p = root;
        if (p == null) {
            System.out.println("Empty Tree!");
        } else {
            stack = new Stack<BTree>();
            while (p != null || !stack.empty()) {
                while (p != null) {
                    visit(p);
                    stack.push(p);
                    p = p.getLchild();
                }
                p = stack.pop();
                p = p.getRchild();
            }
            System.out.println("\nPreOrder Complete!");
        }
    }

    /**
     * 中序遍历某二叉树
     * @param root 二叉树的根节点
     */
    public static void inOrder(BTree root) {
        Stack<BTree> stack;
        BTree p = root;
        if (p == null) {
            System.out.println("Empty Tree!");
        } else {
            stack = new Stack<BTree>();
            while (p != null || !stack.empty()) {
                while (p != null) {
                    stack.push(p);
                    p = p.getLchild();
                }
                p = stack.pop();
                visit(p);
                p = p.getRchild();
            }
            System.out.println("\nInOrder Complete!");
        }
    }

    /**
     * 后序遍历某二叉树
     * @param root
     */
    public static void postOrder(BTree root) {
        Stack<BTree> stack;
        Stack<Boolean> flagstack;
        BTree p = root;
        boolean flag;

        if (p == null) {
            System.out.println("Empty Tree!");
        } else {
            stack = new Stack<BTree>();
            flagstack = new Stack<Boolean>();

            while (p != null || !stack.empty()) {
                while (p != null) {
                    stack.push(p);
                    flagstack.push(false);
                    p = p.getLchild();
                }
                p = stack.pop();
                flag = flagstack.pop();

                if (flag) {
                    visit(p);
                    p = null;
                } else { // 当前节点的右子树尚未进栈，需要将当前节点进栈
                    stack.push(p);
                    flagstack.push(true);
                    p = p.getRchild();
                }
            }
            System.out.println("\nPostOrder Complete!");
        }
    }

    /**
     * 按层次遍历某二叉树
     * @param root
     */
    public static void layerOrder(BTree root) {
        Queue<BTree> queue;
        BTree p = root;
        if (p == null) {
            System.out.println("Empty Tree!");
        } else {
            queue = new LinkedList<BTree>();
            queue.offer(p);
            while (!queue.isEmpty()) {
                p = queue.poll();
                visit(p);
                if (p.getLchild() != null)
                    queue.offer(p.getLchild());
                if (p.getRchild() != null)
                    queue.offer(p.getRchild());
            }
            System.out.println("\nLayerOrder Complete!");
        }
    }

    /**
     * 返回左子树的最大节点
     * @return
     */
    public static BTree maxLeft(BTree root) {
        BTree p = root.lchild;
        while (!(p.rchild == null || p.rchild == root)) {
            p = p.rchild;
        }
        return p;
    }

    /**
     * 返回右子树的最小节点
     * @return
     */
    public static BTree minRight(BTree root) {
        BTree p = root.rchild;
        while (!(p.lchild == null || p.lchild == root)) {
            p = p.lchild;
        }
        return p;
    }

    public static BTree convertToDLink(BTree root) {
        BTree p = root, left = null, right = null;
        if (p != null) {
            left = root.getLchild();
            right = root.getRchild();
            p = maxLeft(root);
            p.setRchild(root);
            p = minRight(root);
            p.setLchild(root);

        }
        // boolean flag = 10%2 == 1 && 10 / 3 == 0 && 1 / 0 == 0 ;
        return p;
    }

    public static void testBTreeOrder() {
        BTree root = createASortedTree();
        preOrder(root);
        inOrder(root);
        postOrder(root);
        layerOrder(root);
//        ExecutorService exec= Executors.newCachedThreadPool();
    }

    private static BTree createPathTree() {
        int num = 5;
        BTree[] trees = new BTree[num];
        for (int i = 0; i < num; i++) {
            trees[i] = new BTree();
        }

        trees[0].setValue(10);
        trees[1].setValue(5);
        trees[2].setValue(12);
        trees[3].setValue(4);
        trees[4].setValue(7);

        trees[0].setLchild(trees[1]);
        trees[0].setRchild(trees[2]);
        trees[1].setLchild(trees[3]);
        trees[1].setRchild(trees[4]);

        return trees[0];
    }
    /**
     * find all paths that its summary is sum
     * @param target
     */
    public static void findPath(BTree root, int target) {
        BTree p = root;
        Stack<BTree> bTreeStack;
        Stack<Integer> sumStack;
        ListIterator<BTree> iter;
        int sum = 0;
        if (p == null) {
            System.out.println("Empty tree.");
        } else {
            bTreeStack = new Stack<BTree>();
            sumStack = new Stack<Integer>();

            while (p != null || !bTreeStack.isEmpty()) {
                while (p != null) {
                    sum += p.getValue();
                    sumStack.push(sum);
                    bTreeStack.push(p);
                    p = p.getLchild();
                }
                p = bTreeStack.pop();
                sum = sumStack.pop();

                if (p.isLeaf() && sum == target) {
                    iter = bTreeStack.listIterator();
                    while (iter.hasNext()) {
                        visit(iter.next());
                        //iter.next();
                    }
//                    for (BTree node : bTreeStack) {
//                        visit(node);
//                    }
                    visit(p);
                    System.out.println();
                }

                p = p.getRchild();
            }
            System.out.println("Path not found!");
        }
    }

    public static void testPrintPath() {
        BTree root = createPathTree();
        findPath(root, 22);
    }

}
