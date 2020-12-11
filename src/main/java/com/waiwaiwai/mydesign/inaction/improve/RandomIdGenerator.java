package com.waiwaiwai.mydesign.inaction.improve;

import com.google.common.annotations.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

public class RandomIdGenerator implements LogTraceIdGenerator {
    private static final Logger logger = LoggerFactory.getLogger(RandomIdGenerator.class);

    @Override
    public String generate() {
        String hostName = getLastFiledOfHostName();
        long currentTimeMillis = System.currentTimeMillis();
        String randomAlphameric = generateRandomAlphameric(8);
        String id = String.format("%s-%d-%s", hostName, currentTimeMillis, randomAlphameric);
        return id;
    }

    private String getLastFiledOfHostName() {
        String subStrOfHostName = "";
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            subStrOfHostName = getLastSubStrSplitByDot(hostName);
        } catch (UnknownHostException e) {
            logger.warn("Failed to get the host name", e);
            e.printStackTrace();
        }
        return subStrOfHostName;
    }

    @VisibleForTesting
    protected String getLastSubStrSplitByDot(String hostName) {
        String[] tokens = hostName.split("\\.");
        String subStrOfHostName = tokens[tokens.length - 1];
        return subStrOfHostName;
    }

    @VisibleForTesting
    protected String generateRandomAlphameric(int length) {
        char[] randomChars = new char[length];
        Random random = new Random();
        int count = 0;
        int maxAscii = 'z';
        while (count < length) {
            int randomAscii = random.nextInt(maxAscii);
            boolean isDigit = randomAscii >= '0' && randomAscii <= '9';
            boolean isUpperCase = randomAscii >= 'A' && randomAscii <= 'Z';
            boolean isLowerCase = randomAscii >= 'a' && randomAscii <= 'z';
            if (isDigit || isUpperCase || isLowerCase) {
                randomChars[count] = (char) randomAscii;
                count++;
            }
        }
        return new String(randomChars);
    }

}
