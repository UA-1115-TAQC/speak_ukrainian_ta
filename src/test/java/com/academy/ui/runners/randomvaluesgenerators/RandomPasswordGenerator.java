package com.academy.ui.runners.randomvaluesgenerators;

    import java.security.SecureRandom;

    public class RandomPasswordGenerator {

        private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
        private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static final String DIGITS = "0123456789";
        private static final String SPECIAL_SYMBOLS = "!@#$%^&*()-_+=<>?";

        public String generateRandomPassword() {
            SecureRandom random = new SecureRandom();
            int passwordLength = random.nextInt(13) + 8;

            StringBuilder password = new StringBuilder();
            password.append(getRandomChar(LOWERCASE_LETTERS, random));
            password.append(getRandomChar(UPPERCASE_LETTERS, random));
            password.append(getRandomChar(DIGITS, random));
            password.append(getRandomChar(SPECIAL_SYMBOLS, random));

            for (int i = 4; i < passwordLength; i++) {
                String allCharacters = LOWERCASE_LETTERS + UPPERCASE_LETTERS + DIGITS + SPECIAL_SYMBOLS;
                password.append(getRandomChar(allCharacters, random));
            }

            char[] passwordArray = password.toString().toCharArray();
            for (int i = passwordArray.length - 1; i > 0; i--) {
                int index = random.nextInt(i + 1);
                char temp = passwordArray[index];
                passwordArray[index] = passwordArray[i];
                passwordArray[i] = temp;
            }

            return new String(passwordArray);
        }

        private char getRandomChar(String characters, SecureRandom random) {
            int randomIndex = random.nextInt(characters.length());
            return characters.charAt(randomIndex);
        }
    }


