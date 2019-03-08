package com.simao.giao;

/**
 * 快排
 */
public class Quick {
    public void sort(int arr[], int low, int high) {
        int l = low;
        int h = high;
        int povit = arr[low];

        while (l < h) {
            while (l < h && arr[h] >= povit)
                h--;
            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                l++;
            }

            while (l < h && arr[l] <= povit)
                l++;

            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                h--;
            }
        }
        if (l - 1 > low) sort(arr, low, l - 1);
        if (h + 1 < high) sort(arr, h + 1, high);
    }

    public void sortOf(int array[], int start, int end) {
        int low = start;
        int high = end;
        int key = array[start];
        while (low < high) {
            //step 1
            while (low < high && array[high] > key)
                high--;
            if (low < high) {
                int temp = array[high];
                array[high] = array[low];
                array[low] = temp;
                low++;
            }
            //step 2
            while (low < high && array[low] < key)
                low++;
            if (low < high) {
                int temp = array[high];
                array[high] = array[low];
                array[low] = temp;
                high--;
            }
        }
        //step 3
        if (low - 1 > start)
            sortOf(array, start, low - 1);
        if (high + 1 < end)
            sortOf(array, high + 1, end);
    }
}
