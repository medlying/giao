package com.simao.giao;

import javafx.util.Pair;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Util {
    public static <K, V> boolean compare(Pair<K, V> pair1, Pair<K, V> pair2) {
        return pair1.getKey() == pair2.getKey() && pair1.getValue() == pair2.getValue();
    }

    public static String encode(String s) throws UnsupportedEncodingException {
        return URLEncoder.encode(s, "UTF-8");
    }

    public static String decode(String s) throws UnsupportedEncodingException {
        return URLDecoder.decode(s, "UTF-8");
    }
}
