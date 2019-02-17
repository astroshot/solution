package com.solution.tree;

import static com.solution.tree.BTree.createASortedTree;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBTree {

    @Test
    public void testBTreeOrder() {
        BTree root = createASortedTree();
        BTree.preOrder(root);
        BTree.inOrder(root);
        BTree.postOrder(root);
        BTree.layerOrder(root);
    }

}
