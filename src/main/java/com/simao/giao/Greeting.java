package com.simao.giao;

public class Greeting {

    private long id;

    private String name;

    public Greeting(long l, String name) {
        this.id = l;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
