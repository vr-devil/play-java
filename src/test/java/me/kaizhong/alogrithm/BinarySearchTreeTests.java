package me.kaizhong.alogrithm;


import me.kaizhong.alogrithm.search.BinarySearchTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class BinarySearchTreeTests {

    @Test
    public void insert() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.insertRecursively(10, 1);
        bst.insertIteratively(22, 22);
        bst.insertRecursively(-1, 3);
        bst.insertIteratively(3, 143);
        bst.insertIteratively(4, 144);
        bst.insertIteratively(-2, 145);

        Optional<Integer> result;

        result = bst.searchRecursively(-1);
        Assertions.assertTrue(result.isPresent());
        result.ifPresent(i -> {
            Assertions.assertEquals(3, i);
        });

        result = bst.searchIteratively(3);
        Assertions.assertTrue(result.isPresent());
        result.ifPresent(i -> {
            Assertions.assertEquals(143, i);
        });

        result = bst.searchIteratively(323);
        Assertions.assertFalse(result.isPresent());

        bst.deleteRecursively(-1);
        result = bst.searchRecursively(-1);
        Assertions.assertFalse(result.isPresent());

        result = bst.searchRecursively(3);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(143, result.get());

        result = bst.searchRecursively(4);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(144, result.get());
    }

    @Test
    public void delete() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.insertRecursively(10, 1);
        bst.insertIteratively(22, 22);
        bst.insertRecursively(-1, 3);
        bst.insertIteratively(3, 143);
        bst.insertIteratively(4, 144);
        bst.insertIteratively(-2, 145);

        Optional<Integer> result;

        bst.deleteIteratively(3);
        result = bst.searchRecursively(3);
        Assertions.assertFalse(result.isPresent());

        result = bst.searchRecursively(-2);
        Assertions.assertEquals(145, result.get());

        bst.deleteIteratively(-2);
        result = bst.searchRecursively(-2);
        Assertions.assertFalse(result.isPresent());

        result = bst.searchRecursively(-1);
        Assertions.assertEquals(3, result.get());
    }
}
