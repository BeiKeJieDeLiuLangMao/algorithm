package bbm.leetcode;

import java.util.Arrays;

/**
 * @author bbm
 * @date 2020/5/22
 */
public class Question155 {
    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(-10);
        obj.push(14);
        System.out.println(obj.getMin());
        System.out.println(obj.getMin());
        obj.push(-20);
        System.out.println(obj.getMin());

        System.out.println(obj.getMin());
        System.out.println(obj.top());
        System.out.println(obj.getMin());
        obj.pop();
        obj.push(10);
        obj.push(-7);
        System.out.println(obj.getMin());
        obj.push(-7);
        obj.pop();
        System.out.println(obj.top());
        System.out.println(obj.getMin());
        obj.pop();
    }
    //["MinStack","push","push","getMin","getMin","push","getMin","getMin","top","getMin","pop","push","push","getMin","push","pop","top","getMin","pop"]
    //[[],[-10],[14],[],[],[-20],[],[],[],[],[],[10],[-7],[],[-7],[],[],[],[]]

    static class MinStack {
        int cap = 1024;
        int[] minData = new int[cap];
        int[] data = new int[cap];
        int size = 0;
        int min;
        boolean minInited = false;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            data[0] = Integer.MIN_VALUE;
        }

        public void push(int x) {
            size++;
            if (size >= cap) {
                cap *= 2;
                minData = Arrays.copyOf(minData, cap);
                data = Arrays.copyOf(data, cap);
            }
            data[size - 1] = x;
            insert2MinData(x);
        }

        private void insert2MinData(int x) {
            if (x < min || !minInited) {
                min = x;
                minInited = true;
            }
            minData[size - 1] = min;
        }

        public void pop() {
            size--;
            if (size == 0) {
                minInited = false;
            } else if (min == minData[size]) {
                min = minData[size - 1];
            }
        }

        public int top() {
            return data[size - 1];
        }

        public int getMin() {
            return minData[size - 1];
        }
    }
}
