package com.simao.giao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HashMap<K, V> implements Map<K, V>, Cloneable, Serializable{

    private static float DEFAULT_LOAD_FACTORIES = 0.75f;

    private static int MAX_CAPACITY = 1 << 30;

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    final float loadFactor;

    int threshold;

    transient int size;

    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTORIES;
    }

    public HashMap(int size) {
        this(size, DEFAULT_LOAD_FACTORIES);
    }

    public HashMap(int init, float loadFactor) {
        if (init < 0) {
            throw new IllegalArgumentException("init capacity is illegal");
        }
        if (loadFactor < 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("load factory is illegal");
        }
        if (init > MAX_CAPACITY)
            init = MAX_CAPACITY;
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(init);
    }

    private int tableSizeFor(int init) {
        int n = init - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAX_CAPACITY) ? MAX_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        System.out.println(-4>>>2);
        System.out.println(hashMap.tableSizeFor(1));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
