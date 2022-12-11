package tr.com.metea.hotelium.util;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author Mete Aydin
 * <p>
 * 11.12.2022
 */
public final class RandomGenerator {
    private RandomGenerator() {
    }

    public static String generateString() {
        return generateStringWithSize(36);
    }

    public static String generateStringWithSize(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static Long generateLong() {
        return new Random().nextLong();
    }

    public static BigDecimal generateBigDecimal() {
        return BigDecimal.valueOf(generateLong());
    }

    public static Boolean generateBoolean() {
        return new Random().nextBoolean();
    }
}
