package tr.com.metea.hotelium.util;

import java.math.BigDecimal;
import java.security.SecureRandom;

/**
 * @author Mete Aydin
 * <p>
 * 11.12.2022
 */
public final class RandomGenerator {
    private static final SecureRandom random = new SecureRandom();

    private RandomGenerator() {
    }

    public static String generateString() {
        return generateUpperOrLowerString(36);
    }

    public static String generateLowerStringWithSize(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        return generate(leftLimit, rightLimit, targetStringLength);
    }

    public static String generateUpperStringWithSize(int targetStringLength) {
        int leftLimit = 65; // letter 'A'
        int rightLimit = 90; // letter 'Z'
        return generate(leftLimit, rightLimit, targetStringLength);
    }

    public static Long generateLong() {
        return random.nextLong();
    }

    public static BigDecimal generateBigDecimal() {
        return BigDecimal.valueOf(generateLong());
    }

    public static Boolean generateBoolean() {
        return random.nextBoolean();
    }

    public static String generateNumericString(int targetStringLength) {
        int leftLimit = 48; // number '0'
        int rightLimit = 57; // number '9'
        return generate(leftLimit, rightLimit, targetStringLength);
    }

    private static String generate(int leftLimit, int rightLimit, int targetStringLength) {
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + random.nextInt(rightLimit - leftLimit + 1);
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static String generateUpperOrLowerString(int targetStringLength) {
        StringBuilder buffer = new StringBuilder(targetStringLength);
        int upperLeftLimit = 65;
        int lowerLeftLimit = 97;
        int upperRightLimit = 90;
        int lowerRightLimit = 122;

        for (int i = 0; i < targetStringLength; i++) {
            boolean useUpperCase = random.nextBoolean();
            int randomNumber;
            if (useUpperCase) {
                randomNumber = upperLeftLimit + random.nextInt(upperRightLimit - upperLeftLimit + 1);
            } else {
                randomNumber = lowerLeftLimit + random.nextInt(lowerRightLimit - lowerLeftLimit + 1);
            }
            buffer.append((char) randomNumber);
        }
        return buffer.toString();
    }
}
