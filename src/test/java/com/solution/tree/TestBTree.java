package com.solution.tree;

import com.solution.AbstractTestCase;
import org.junit.Test;

import static com.solution.tree.BTree.createASortedTree;

public class TestBTree extends AbstractTestCase {

    @Test
    public void testBTreeOrder() {
        BTree root = createASortedTree();
        BTree.preOrder(root);
        BTree.inOrder(root);
        BTree.postOrder(root);
        BTree.layerOrder(root);
    }

    @Test
    public void testMorrisInOrder() {
        BTree root = createASortedTree();
        BTree.inOrder(root);
        BTree.morrisInOrder(root);
    }
}
