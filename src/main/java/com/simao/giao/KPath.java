package com.simao.giao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * 786. 第 K 个最小的素数分数
 */
public class KPath {

    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        long sum = 1l;
        for (int i = 1; i < A.length; i++) {
            sum *= A[i];
        }
        sum = Math.abs(sum);
        TreeMap<Long, Map<Integer, Integer>> treeMap = new TreeMap<>();
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                HashMap<Integer, Integer> temp = new HashMap<>();
                temp.put(A[i], A[j]);
                treeMap.put(sum / A[j] * A[i], temp);
            }
        }
        Map<Integer, Integer> map = null;
        while (K > 0) {
            map = treeMap.pollFirstEntry().getValue();
            K--;
        }
        return new int[]{(int) map.keySet().toArray()[0], (int) map.values().toArray()[0]};
    }

    public int[] kthSmallestTreeMap(int[] A, int K) {
        TreeMap treeMap = new TreeMap<Prime, Prime>(((o1, o2) -> {
            Integer numerator = o1.numerator * o2.denominator;
            Integer denominator = o2.numerator * o1.denominator;
            return numerator - denominator;
        }));
        List<Prime> combine = getCombine(A);
        for (Prime prime:combine) {
            treeMap.put(prime, 1);
        }
        while (K > 1) {
            treeMap.pollFirstEntry();
            K--;
        }
        Map.Entry entry = treeMap.pollFirstEntry();
        Prime prime = (Prime) entry.getKey();
        return new int[]{prime.numerator, prime.denominator};
    }

    //大顶堆
    public int[] kThSmallestFraction(int[] A, int K) {
        Queue<Prime> maxHeap = new PriorityQueue<>(K, (o1, o2) -> {
            Integer numerator = o1.numerator * o2.denominator;
            Integer denominator = o2.numerator * o1.denominator;
            return denominator.compareTo(numerator);
        });

        List<Prime> list = getCombine(A);

        for (Prime prime:list) {
            if (maxHeap.size() < K) {
                maxHeap.add(prime);
            } else {
                if (isMin(prime, maxHeap.peek())) {
                    maxHeap.poll();
                    maxHeap.add(prime);
                }
            }
        }
        Prime peek = maxHeap.peek();
        return new int[]{peek.numerator, peek.denominator};
    }

    private boolean isMin(Prime o1, Prime o2) {
        Integer numerator = o1.numerator * o2.denominator;
        Integer denominator = o2.numerator * o1.denominator;
        return numerator < denominator;
    }

    private List<Prime> getCombine(int[] A) {
        List<Prime> list = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                list.add(new Prime(A[i], A[j]));
            }
        }
        return list;
    }

    class Prime {
        private int numerator;
        private int denominator;

        public Prime(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    public static void main(String[] args) {
        int[] init = {1, 2, 3, 5};
        KPath kPath = new KPath();
        int[] result = kPath.kthSmallestTreeMap(init, 3);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
