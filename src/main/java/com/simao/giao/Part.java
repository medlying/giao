package com.simao.giao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Part {
    static List<Factory<? extends Part>> partFactory = new ArrayList<>();

    static {
        partFactory.add(new FuelFilter.Factory());
        partFactory.add(new AirFilter.Factory());
    }

    private static Random random = new Random(47);

    public static Part createRandom() {
        int n = random.nextInt(partFactory.size());
        return partFactory.get(n).create();
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
