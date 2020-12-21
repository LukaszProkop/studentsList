package com.epam.homework;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;

public class Randomizer {

    public static int randomPoints() {
        return new Random().ints(0, 101)
                .findFirst()
                .orElse(0);
    }

    public static String randomName() {
        int length = new Random().ints(4, 11)
                .findFirst()
                .orElse(0);
        String name = RandomStringUtils.randomAlphabetic(length);

        return name.substring(0, 1)
                .toUpperCase() + name.substring(1)
                .toLowerCase();
    }
}
