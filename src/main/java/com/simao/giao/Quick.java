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

    //快排
    public void quickSort(int array[], int start, int end) {
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
            quickSort(array, start, low - 1);
        if (high + 1 < end)
            quickSort(array, high + 1, end);
    }

    //冒泡排序1
    public void bubbleSort(int array[]) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
    }

    //冒泡排序2
    public void bubbleSort2(int arr[]) {
        for(int i =0;i<arr.length-1;i++) {
            for(int j=0;j<arr.length-i-1;j++) {//-1为了防止溢出
                if(arr[j]>arr[j+1]) {
                    int temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }

    //直接选择排序
    public void selectSort(int array[]) {
        if (array == null || array.length ==0) {
            return;
        }
        int temp = 0, minIndex = 0;
        for (int i = 0; i < array.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        Quick quick = new Quick();
        int[] array = {1, 5, 8, 2, 7, 5, 9, 2};
        quick.bubbleSort2(array);
        quick.quickSort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
