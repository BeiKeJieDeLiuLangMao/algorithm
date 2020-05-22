package bbm.leetcode;

import java.util.Arrays;

/**
 * @author bbm
 * @date 2020/5/22
 */
public class Question155 {
    static class MinStack {
        int cap = 1024;
        int[] sortedData = new int[cap];
        int[] data = new int[cap];
        int size = 0;

        /** initialize your data structure here. */
        public MinStack() {

        }

        public void push(int x) {
            size++;
            if (size >= cap) {
                cap *= 2;
                sortedData = Arrays.copyOf(sortedData, cap);
                data = Arrays.copyOf(data, cap);
            }
            data[size - 1] = x;
            insert2SortedData(x);
        }

        private void insert2SortedData(int x) {
            int rightIndex = findRightIndex(x);
            if (rightIndex == size) {
                sortedData[rightIndex] = x;
            } else {
                for (int i = size; i > rightIndex; i--) {
                    sortedData[i] = sortedData[i - 1];
                }
                sortedData[rightIndex] = x;
            }

        }

        private int findRightIndex(int x) {
            int current = size / 2;
            while (true) {
                if (sortedData[current] == x) {
                    return current;
                } else if (sortedData[current] > x) {
                    current = current / 2;
                } else {
                    current = (current + size) / 2;
                }
                if (current == 0 || (current == size - 1)) {
                    break;
                }
            }
            return current;
        }

        private void deleteFromSortedData(int x) {
            int rightIndex = findRightIndex(x);
            for (int i = rightIndex; i < size; i++) {
                sortedData[i] = sortedData[i + 1];
            }
        }

        public void pop() {
            size--;
            deleteFromSortedData(data[size]);
        }

        public int top() {
            return data[size - 1];
        }

        public int getMin() {
            return sortedData[0];
        }
    }

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-1);
        System.out.println(obj.getMin());
        System.out.println(obj.top());
        obj.pop();
        System.out.println(obj.getMin());
    }
}
