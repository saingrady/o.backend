package util;

import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;

public class Generator {
	
    public static String randomAlphaNumeric(int count) {
        return RandomStringUtils.random(count, true, true);
    }

    public static String accessToken() {
        return new StringBuilder()
                .append(System.currentTimeMillis())
                .append("-")
                .append(randomAlphaNumeric(128))
                .append("-")
                .append(UUID.randomUUID().toString()).toString();
    }
}
