package com.miro.framework.utils;

import com.miro.framework.configuration.UserConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.String.format;

public class RandomValuesGenerator {

    private static UserConfig userConfig = ConfigFactory.create(UserConfig.class, System.getProperties());

    private static int counter = 0;
    private static ReentrantLock locker = new ReentrantLock(true);

    private static void setCounter() {
        locker.lock();
        try {
            counter++;
        } finally {
            locker.unlock();
        }
    }

    /**
     * Generate a random string of specific length
     *
     * @param length specifies the number of symbols in string
     * @return random string of a specific length
     */
    public static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length).toLowerCase();

    }

    /**
     * Generate a random numeric string of specific length
     *
     * @param length specifies the number of digits in a string
     * @return numeric string of a specific length
     */
    public static String generateRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length).toLowerCase();
    }

    private static String generateSpecialCharactersString(int length) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        String specials = "~=+%^*/()[]{}/!@#$?|";
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(specials.length());
            char ch = specials.charAt(number);
            builder.append(ch);
        }
        return builder.toString();

    }

    /**
     * Generating
     *
     * @return
     */
    public static String generateGoodPassword(){
        String textPart = StringUtils.capitalize(generateRandomString(6) + generateSpecialCharactersString(1));
        String digitPart = generateRandomNumber(2);
        return textPart + digitPart;
    }

    /**
     * Generate random email from a specific template
     *
     * @return email
     */
    public static String generateWorkEmail() {
        setCounter();
        String template = userConfig.emailTemplate();
        long unixTime = new GregorianCalendar().getTimeInMillis();

        return format(template, generateRandomString(7)
                + String.valueOf(unixTime).substring(4)
                + counter
        );

    }
}
