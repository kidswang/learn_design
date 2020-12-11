package com.waiwaiwai.mydesign.inaction;

import com.waiwaiwai.mydesign.inaction.improve.RandomIdGenerator;
import org.junit.Test;

public class ExtendsTest extends RandomIdGenerator {

    @Test
    public void test() {
        RandomIdGenerator randomIdGenerator = new RandomIdGenerator();

        RandomIdGenerator randomIdGenerator2 = new ExtendsTest();

        ExtendsTest2 test2 = new ExtendsTest2();
        String randomAlphameric = test2.generateRandomAlphameric(1);
        System.out.println(randomAlphameric);

    }

}
