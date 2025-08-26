package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class TestDataGenerator {

    public static String getRandomEmail() {
        return "auto" + RandomStringUtils.randomAlphanumeric(5) + "@example.com";
    }

    public static String getRandomName() {
        return "User" + RandomStringUtils.randomAlphabetic(6);
    }

    public static String getRandomemmobiliser() {
        return "emb-" + RandomStringUtils.randomNumeric(8);
    }

    public static String getRandomLed(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
    public static String getRandometicketing() {
        return "eticket-" + System.currentTimeMillis();
    }
    public static String getRandomegps() {
        return "eticket-" + System.currentTimeMillis();
    }

    public static String getRandomDayBefore(int referenceDay) {
        return String.valueOf(referenceDay - 1);
    }

    public static String getRandomDayAfter(int referenceDay) {
        return String.valueOf(referenceDay );
    }
}
