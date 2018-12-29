package com.simao.giao;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private static final int MAX_ENTRIES = 3;

    public LRUCache() {
        super(MAX_ENTRIES, 0.75f, true);
    }

    protected boolean removeEldest(Map.Entry entry) {
        return size() > MAX_ENTRIES;
    }
}
