package com.solution.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BTreeTest {

    protected static void log(String format, Object... args) {
        System.out.printf(format, args);
    }

    /**
     * 将二叉树转换成双向链表
     * 遍历过程中维护两个指针，分别指向当前节点和当前节点的前一个节点
     */
    public static BTree convertToLink(BTree root) {
        // 将二叉树转换为双向链表
        BTree p = root;
        if (p == null) {
            return null;
        }

        List<BTree> stack = new ArrayList<>();
        BTree head = null;
        BTree preNode = null;
        // 中序遍历
        while (p != null || stack.size() > 0) {
            while (p != null) {
                stack.add(p);
                p = p.getLeft();
            }

            p = stack.remove(stack.size() - 1);
            // 处理指针转换
            if (p.getLeft() == null) {
                if (head == null) {
                    head = p; // 确认链表的头结点
                }
                p.setLeft(preNode);
            }

            if (preNode != null) {
                preNode.setRight(p);
            }
            preNode = p;
            p = p.getRight();
        }

        return head;
    }

    public static void testConvertLink() {
        BTree root = BTree.createASortedTree();
        BTree.inOrder(root);
        BTree h = convertToLink(root);
        while (h != null) {
            System.out.printf("%d ", h.getValue());
            h = h.getRight();
        }
    }

    /**
     * 根据中序遍历和前序遍历结果恢复二叉树，
     * C++ 版在递归的时候传数组元素的地址，然后用指针+相对偏移得到元素
     * 由于 java 和 C++  的差异性，java 不可能传数组中某个元素的地址，因此调试会更麻烦，需要每次递归传递起始地址和元素数量
     *
     * @param inOrder  中序遍历数组
     * @param inBegin  中序遍历起始位置
     * @param inNum    中序遍历数量
     * @param preOrder 前序遍历数组
     * @param preBegin 前序遍历起始位置
     * @param preNum   前序遍历数量
     * @return 二叉树根节点
     */
    public static BTree restore(int[] inOrder, int inBegin, int inNum, int[] preOrder, int preBegin, int preNum) {
        if (inNum != preNum) {
            throw new RuntimeException("Invalid inOrder array and preOrder array");
        }

        if (inNum == 0) {
            return null;
        }

        int rootVal = preOrder[preBegin];
        if (inNum == 1) {
            return new BTree(rootVal);
        }

        int rootIndex = -1;
        for (int i = 0; i < inNum; i++) {
            if (inOrder[inBegin + i] == rootVal) {
                rootIndex = i;
                break;
            }
        }

        if (rootIndex == -1) {
            int[] tmpIn = Arrays.copyOfRange(inOrder, inBegin, inBegin + inNum);
            int[] tmpPre = Arrays.copyOfRange(preOrder, preBegin, preBegin + preNum);
            log("No root found, inOrder: %s, preOrder: %s\n",
                    Arrays.toString(tmpIn), Arrays.toString(tmpPre));
            throw new RuntimeException("No root found");
        }

        BTree root = new BTree(rootVal);
        int leftNum = rootIndex;
        int rightNum = inNum - leftNum - 1;
        // 无法 debug 时只能用日志输出
        log("totalNum: %d, rootIndex: %d, inBegin: %d, leftNum: %d, preBegin: %d, rightNum: %d\n",
                inNum, rootIndex, inBegin, leftNum, preBegin, rightNum);
        BTree left = restore(inOrder, inBegin, leftNum, preOrder, preBegin + 1, leftNum);
        BTree right = restore(inOrder, inBegin + leftNum + 1, rightNum,
                preOrder, preBegin + leftNum + 1, rightNum);
        root.setLeft(left);
        root.setRight(right);
        return root;
    }

    public static void testRestore() {
        int[] pre = new int[]{1, 2, 4, 5, 8, 3, 6, 7};
        int[] in = new int[]{4, 2, 8, 5, 1, 6, 3, 7};
        BTree root = restore(in, 0, in.length, pre, 0, pre.length);
        BTree.postOrder(root);
    }

    public static void main(String[] args) {
        // testConvertLink();
        // testRestore();
        BTree root = BTree.createASortedTree();
        BTree.layerOrder(root);
    }
}
