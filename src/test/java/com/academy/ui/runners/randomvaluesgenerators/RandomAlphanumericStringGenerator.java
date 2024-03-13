package com.academy.ui.runners.randomvaluesgenerators;

import io.qameta.allure.Step;

import java.security.SecureRandom;

public class RandomAlphanumericStringGenerator {
    private static final String NUMERIC = "0123456789";
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String ALPHANUMERIC = NUMERIC + LETTERS;
    @Step("Generate a random string")
    public static String generateRandomString(int minLength, int maxLength, int type) {
        if (minLength < 0 || maxLength < 0 || minLength > maxLength) {
            throw new IllegalArgumentException("Invalid length range");
        }

        String characters;
        switch (type) {
            case 1:
                characters = NUMERIC;
                break;
            case 2:
                characters = LETTERS;
                break;
            case 3:
                characters = ALPHANUMERIC;
                break;
            default:
                throw new IllegalArgumentException("Invalid type");
        }

        StringBuilder result = new StringBuilder();
        SecureRandom random = new SecureRandom();

        int length = minLength + random.nextInt(maxLength - minLength + 1);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }

        return result.toString();
    }
}
