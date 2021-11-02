package me.kaizhong.test.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TreeSetTests {

    static class Item {
        Integer id;
        String name;

        Item(Integer id, String name) {
            this.id = id;
            this.name = name;
        }


        public String getName() {
            return name;
        }
    }

    @Test
    public void testSetsOperation() {

        Set<Item> A = Set.of(new Item(1, "item1"), new Item(1, "item2"));
        Set<Item> B = Set.of(new Item(1, "item1"), new Item(null, "item3"));


        TreeSet<Item> set = new TreeSet<>(Comparator.comparing(Item::getName));
        set.addAll(A);
        set.retainAll(B); // remove
        set.addAll(B); // add

        Assertions.assertEquals(
                Set.of("item1", "item3"),
                set.stream().map(Item::getName).collect(Collectors.toSet())
        );
    }

}
