package com.simao.giao;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThread {

    static volatile int num = 0;

    public static void main(String[] args) {
        String s1 = "123", s2 = "456", s3 = "789";
        final Lock lock = new ReentrantLock();
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        StringBuilder stringBuilder3 = new StringBuilder();
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                lock.lock();
                if (num % 3 == 0) {
                    stringBuilder1.append(s1.charAt(num/3));
                    num++;
                }
                lock.unlock();
            }
        });
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                lock.lock();
                if (num % 3 == 1) {
                    stringBuilder2.append(s2.charAt(num/3));
                    num++;
                }
                lock.unlock();
            }
        });
        Thread threadC = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                lock.lock();
                if (num % 3 == 2) {
                    stringBuilder3.append(s3.charAt(num/3));
                    num++;
                }
                lock.unlock();
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
        System.out.println(stringBuilder1.toString());
        System.out.println(stringBuilder2.toString());
        System.out.println(stringBuilder3.toString());
    }
}
