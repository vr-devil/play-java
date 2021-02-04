package me.kaizhong.alogrithm.search;

import java.util.Objects;
import java.util.Optional;

public class BinarySearchTree<K extends Comparable<K>, V> {

    private static class Node<K extends Comparable<K>, V> {
        private Node<K, V> left;
        private Node<K, V> right;
        private K key;
        private V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V> root;

    public void insertRecursively(K key, V value) {

        Objects.requireNonNull(key);
        Objects.requireNonNull(value);

        root = insertRecursivelyInternal(root, key, value);
    }

    public void insertIteratively(K key, V value) {

        Objects.requireNonNull(key);
        Objects.requireNonNull(value);

        if(root == null) {
            root = new Node<>(key, value);
        } else {
            insertIterativelyInternal(root, key, value);
        }
    }

    public void deleteRecursively(K key) {
        root = deleteRecursivelyInternal(root, key);
    }

    public void deleteIteratively(K key) {
        root = deleteIterativelyInternal(root, key);
    }

    public Optional<V> searchRecursively(K key) {

        Objects.requireNonNull(key);

        return searchRecursivelyInternal(root, key);
    }

    public Optional<V> searchIteratively(K key) {
        Objects.requireNonNull(key);

        return searchIterativelyInternal(root, key);
    }

    private Node<K, V> insertRecursivelyInternal(Node<K, V> root, K key, V value) {

        if(root == null) {
            return new Node<>(key, value);
        }

        if(key.compareTo(root.key) < 0) {
            root.left = insertRecursivelyInternal(root.left, key, value);
        } else if(key.compareTo(root.key) > 0) {
            root.right = insertRecursivelyInternal(root.left, key, value);
        } else {
            root.value = value;
        }

        return root;
    }

    private void insertIterativelyInternal(Node<K, V> root, K key, V value) {

        Node<K, V> walk = root;
        while (walk != null) {
            if(key.compareTo(walk.key) < 0) {
                if(walk.left == null) {
                    walk.left = new Node<>(key, value);
                    return;
                }

                walk = walk.left;
            } else if(key.compareTo(walk.key) > 0) {

                if(walk.right == null) {
                    walk.right = new Node<>(key, value);
                    return;
                }

                walk = walk.right;
            } else {
                walk.value = value;
                return;
            }
        }
    }

    /*
     * 1. 查找中序后继节点；
     * 2. 拷贝后继节点;
     * 3. 删除后继节点；
     */
    private Node<K, V> deleteRecursivelyInternal(Node<K, V> root, K key) {

        if(root == null) {
            return null;
        }

        if(key.compareTo(root.key) < 0) {
            root.left = deleteRecursivelyInternal(root.left, key);
        } else if(key.compareTo(root.key) > 0) {
            root.right = deleteRecursivelyInternal(root.right, key);
        } else {

            if(root.left == null && root.right == null) {
                root = null;
            } else if(root.left == null) {
                root =  root.right;
            } else if(root.right == null) {
                root = root.left;
            } else {
                Node<K, V> successor = findMin(root.right);
                root.right = deleteRecursivelyInternal(root.right, key);

                root.key = successor.key;
                root.value = successor.value;
            }
        }

        return root;
    }

    private Node<K, V> findMin(Node<K, V> root) {
        Node<K, V> min = root;
        while (root.left != null) {
            min = root.left;
            root = root.left;
        }

        return min;
    }

    private Optional<V> searchRecursivelyInternal(Node<K, V> root, K key) {

        if(root == null) {
            return Optional.empty();
        }

        if(key.compareTo(root.key) < 0) {
            return searchRecursivelyInternal(root.left, key);
        } else if(key.compareTo(root.key) > 0) {
            return searchRecursivelyInternal(root.right, key);
        } else {
            return Optional.ofNullable(root.value);
        }
    }

    private Optional<V> searchIterativelyInternal(Node<K, V> root, K key) {

        while (root != null) {
            if(key.compareTo(root.key) < 0) {
                root = root.left;
            } else if(key.compareTo(root.key) > 0) {
                root = root.right;
            } else {
                return Optional.ofNullable(root.value);
            }
        }

        return Optional.empty();
    }

    private Node<K, V> deleteIterativelyInternal(Node<K, V> root, K key) {

        if(root == null) {
            return null;
        }

        Node<K, V> prev = null;
        Node<K, V> curr = root;

        while (curr != null && curr.key != key) {
            prev = curr;
            curr = key.compareTo(curr.key) < 0 ? curr.left : curr.right;
        }

        if(curr == null) {
            return root;
        }



        if(curr.left != null && curr.right != null) {
            Node<K, V> successor = findMin(curr.right);

            curr.key = successor.key;
            curr.value = successor.value;

            // delete successor.
            curr.right = deleteIterativelyInternal(curr.right, key);
        } else {
            Node<K, V> replaced = null;

            if(curr.left != null || curr.right != null) {
                // curr has one child.
                if(curr.left == null) {
                    replaced = curr.right;
                } else {
                    replaced = curr.left;
                }
            }

            if(prev == null)
                // curr is root.
                return replaced;

            if(curr == prev.left) {
                prev.left = replaced;
            } else {
                prev.right = replaced;
            }
        }

        return root;
    }

    private void replaceNode(Node<K, V> parent, Node<K, V> child, Node<K, V> target) {

        if(parent == null || parent == child) {
            // case: child is root.
            return;
        }

        if(parent.left == child) {
            parent.left = target;
        } else if(parent.right == child) {
            parent.right = target;
        }
    }

}
