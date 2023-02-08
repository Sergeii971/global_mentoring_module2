package com.os.module2.task2;

import java.util.concurrent.RecursiveAction;

public class QuickSortAction extends RecursiveAction {
    private int[] data;
    private int left;
    private int right;

    public QuickSortAction(int[] data) {
        this.data = data;
        left = 0;
        right = data.length - 1;
    }

    public QuickSortAction(int[] data, int left, int right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        if(left < right){
            int pivot = partition(data, left, right);
            invokeAll(new QuickSortAction(data,left, pivot),
                    new QuickSortAction(data, pivot + 1, right));
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[low];
        int i = low - 1;
        int j  = high + 1;
        while (true) {
            do {
                i++;
            }
            while (array[i] < pivot);

            do {
                j--;
            }
            while (array[j] > pivot);
            if (i >= j) {
                return j;
            }
            swap(array, i, j);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
