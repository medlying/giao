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

    //TreeMap 1.0
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

    //TreeMap 1.1
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

    //大神的答案
    public int[] kthSmallestPrimeFractions(int[] A, int K) {
        int n = A.length, min = A[0], max = A[n-1], p=0, q=0;
        double lo = (double)min/(double)max, hi = 1.0;
        while (lo < hi) {
            double mid = (lo + hi) / 2.0;
            int[] count = countPairs(A, mid);
            if (count[0] == K) {
                p = count[1];
                q = count[2];
                break;
            }
            if (count[0] < K) lo = mid;
            else hi = mid;
        }
        return new int[] {p, q};
    }

    private int[] countPairs(int[] A, double x) {
        int count = 0, n = A.length, p = 0, q = 0;
        double max = 0.0;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < i && A[j] < A[i] * x) j++;
            if (j > 0) {
                double fraction = (double)A[j-1] / (double)A[i];
                if (max < fraction) {
                    max = fraction;
                    p = A[j-1];
                    q = A[i];
                }
            }
            count += j;
        }
        return new int[] {count, p, q};
    }

    //大神的二分查找
    public int[] kthSmallestPrimeFractionByBinary(int[] A, int K) {
        int n = A.length, x = 0, y = 0, total = 0;
        double l = 0, r = 1;
        while(total != K) {
            total = 0;
            double m = (l + r) /2.0, max = 0.0;
            for(int i = 0, j = 1; i < n; i++) {
                while(j < n && A[i] > m * A[j])
                    j++;
                if (j == n || (total += n - j) > K)
                    break;
                if (A[i] > max * A[j]) {
                    max = A[i] / (double) A[j] ;
                    x = A[i];
                    y = A[j];
                }
            }
            if (total > K)
                r = m;
            else
                l = m;
        }
        return new int[] {x, y};
    }

    public static void main(String[] args) {
        int[] init = {1, 2, 3, 5};
        KPath kPath = new KPath();
        int[] result = kPath.kthSmallestPrimeFractionByBinary(init, 3);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
