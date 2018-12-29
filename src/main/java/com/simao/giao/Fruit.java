package com.simao.giao;

import java.util.ArrayList;
import java.util.List;

public class Fruit<T> {

    static class Reader<T> {
        T readValue(List<? extends T> list) {
            return list.get(0);
        }
    }

    static void function() {
        Reader<Fruit> reader = new Reader<Fruit>();
        List<Fruit> list = new ArrayList<>();
        Fruit f1 = reader.readValue(list);
    }

    public static <T> void add(List<T> list, Class<T> e) throws IllegalAccessException, InstantiationException {
        T t = e.newInstance();
        list.add(t);
    }

    public static void main(String[] args) {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2);
    }
}
