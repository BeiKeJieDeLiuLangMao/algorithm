package bbm.leetcode.bytedance.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * @author bbm
 * @date 2020/7/13
 */
public class Structure1032 {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* 缓存容量 */);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }

    static class LRUCache {

        Map<Integer, LinkedNode> map;
        LinkedNode head;
        LinkedNode tail;
        int capacity;

        public LRUCache(int capacity) {
            map = new HashMap<>(capacity * 2);
            this.capacity = capacity;
        }

        public int get(int key) {
            LinkedNode node = map.get(key);
            if (node != null) {
                //handle linked list
                handleLinkedList2Head(node);
                return node.value;
            } else {
                return -1;
            }
        }

        private void handleLinkedList2Head(LinkedNode node) {
            if (head != node) {
                removeNodeFromLink(node);
                insertNode2Head(node);
            }
        }

        public void put(int key, int value) {
            LinkedNode node = map.get(key);
            if (node != null) {
                node.value = value;
                //handle linked list to head
                handleLinkedList2Head(node);
            } else {
                node = new LinkedNode(key, value);
                //handle linked list to head
                map.put(key, node);
                insertNode2Head(node);
            }
            if (map.size() > capacity) {
                //delete tail linked list
                LinkedNode toDelete = removeTailNode();
                map.remove(toDelete.key);
            }
        }

        private void removeNodeFromLink(LinkedNode node) {
            node.prev.next = node.next;
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            if (tail == node) {
                tail = node.prev;
            }
            node.prev = null;
            node.next = null;
        }

        private void insertNode2Head(LinkedNode node) {
            node.next = head;
            if (head != null) {
                head.prev = node;
            }
            head = node;
            if (tail == null) {
                tail = node;
            }
        }

        private LinkedNode removeTailNode() {
            LinkedNode toDelete = tail;
            tail = toDelete.prev;
            tail.next = null;
            toDelete.prev = null;
            return toDelete;
        }

        private static class LinkedNode {
            LinkedNode prev;
            LinkedNode next;
            int key;
            int value;

            public LinkedNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

    }
}
