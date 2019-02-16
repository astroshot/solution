package com.solution.tree;

import static com.solution.tree.BTree.createASortedTree;

public class TestBTree {

    public static void testBTreeOrder() {
        BTree root = createASortedTree();
        BTree.preOrder(root);
        BTree.inOrder(root);
        BTree.postOrder(root);
        BTree.layerOrder(root);
    }

}
