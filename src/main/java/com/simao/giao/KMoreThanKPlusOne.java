package com.simao.giao;

/**
 * 一个序列，其中A[0]>A[n], 找出其中一个k，使得A[k] > A[k+1]
 */
public class KMoreThanKPlusOne {

    public int binary(int[] nums, int min, int max) {
        if (min + 1 == max) {
            return min;
        }
        int mid = (min + max) / 2;
        if (nums[min] > nums[mid]) {
            return binary(nums, min, mid);
        } else {
            return binary(nums, mid, max);
        }
    }

    public int three(int[] nums, int min, int max) {
        if (min + 1 == max || max - min < 3) {
            return min;
        }
        int left = (max - min) / 3 + min;
        int right = max - (max - min) / 3; //int right = (max - min) / 3 * 2 + min
        if (nums[left] > nums[right]) {
            return three(nums, left, right);
        } else if (nums[min] <= nums[left]) {
            return three(nums, right, max);
        } else {
            return three(nums, min, left);
        }
    }

    public int four(int[] nums, int min, int max) {
        if (min + 1 == max) {
            return min;
        }
        if (max - min < 4) {
            return binary(nums, min, max);
        }
        int one = (max - min) / 4 + min;
        int two = (max - min) / 4 * 2 + min;
        int three = (max - min) / 4 * 3 + min;
        if (nums[one] > nums[two]) {
            return four(nums, one, two);
        } else if (nums[two] > nums[three]) {
            return four(nums, two, three);
        } else if (nums[min] > nums[one]) {
            return four(nums, min, one);
        } else {
            return four(nums, three, max);
        }
    }

    public int finds(int[] inits) {
        return three(inits, 0, inits.length);
    }

    public static void main(String[] args) {
        KMoreThanKPlusOne kPath = new KMoreThanKPlusOne();
        int[] inits = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0};
        System.out.println(kPath.finds(inits));
    }
}
