package com.gorest.utils;

import com.github.javafaker.Faker;

import java.util.Random;

public class TestUtils {

    public static String getRandomValue() {
        Random random = new Random();
        int randomInt = random.nextInt(100000);
        return Integer.toString(randomInt);
    }

    public static String generateName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    public static String generateEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public static String generateGender() {
        Faker faker = new Faker();
        return faker.options().option("Male", "Female");
    }

    public static String generateStatus() {
        Faker faker = new Faker();
        return faker.options().option("active","inactive");
    }

}
