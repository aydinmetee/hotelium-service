package tr.com.metea.hotelium.util;

import java.util.UUID;

public final class IdGenerator {
    private IdGenerator() {
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
