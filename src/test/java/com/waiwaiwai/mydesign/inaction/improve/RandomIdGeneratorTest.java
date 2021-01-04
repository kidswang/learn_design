package com.waiwaiwai.mydesign.inaction.improve;

import org.junit.Assert;
import org.junit.Test;

import java.net.UnknownHostException;

public class RandomIdGeneratorTest {

    @Test
    public void test() {
        RandomIdGenerator randomIdGenerator = new RandomIdGenerator();
        String generate = randomIdGenerator.generate();
        System.out.println(generate);
    }

    @Test
    public void testGetLastSubstrSplitByDot() throws UnknownHostException {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualSubstr = idGenerator.getLastSubStrSplitByDot("field1.field2.field3");
        Assert.assertEquals("field3", actualSubstr);
        actualSubstr = idGenerator.getLastSubStrSplitByDot("field1");
        Assert.assertEquals("field1", actualSubstr);
        actualSubstr = idGenerator.getLastSubStrSplitByDot("field1#field2$field3");
        Assert.assertEquals("field1#field2$field3", actualSubstr);
    }

    @Test
    public void testGenerateRandomAlphameric() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualRandomString = idGenerator.generateRandomAlphameric(6);
        Assert.assertNotNull(actualRandomString);
        Assert.assertEquals(6, actualRandomString.length());
        for (char c : actualRandomString.toCharArray()) {
            Assert.assertTrue(('0' < c && c < '9') || ('a' < c && c < 'z') || ('A' < c && c < 'Z'));
        }
    }

    @Test
    public void testGenerateRandomAlphameric_lengthEqualsOrLessThanZero() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualRandomString = idGenerator.generateRandomAlphameric(0);
        Assert.assertEquals("", actualRandomString);
        actualRandomString = idGenerator.generateRandomAlphameric(-1);
        Assert.assertNull(actualRandomString);
    }

}
