package com.waiwaiwai.mydesign.inaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * 通用代码方面：
 * 1、如果有两种id生成策略,最好是使用接口,连个具体的实现类实现接口
 * 2、中间有随机数 本机名、random随机数、时间戳函数
 * 3、太多的魔法值
 *
 * 业务代码方面：
 * 1、如果机器名为空，并且捕获了异常，只打印一条报警日志，使用起来不太友好
 * 2、没有涉及到共享变量，不用有线程安全方面的问题。
 * 3、获取随机数，只需要48-122的值，出现前面47个用不到数就会空循环，可能会有多次
 * 4、
 */
public class IdGenerator {
    private static final Logger logger = LoggerFactory.getLogger(IdGenerator.class);

    public static String generate() {
        String id = "";
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            String[] tokens = hostName.split("\\.");
            if (tokens.length > 0) {
                hostName = tokens[tokens.length - 1];
            }
            char[] randomChars = new char[8];
            int count = 0;
            Random random = new Random();
            while (count < 8) {
                int randomAscii = random.nextInt(122);
                if (randomAscii >= 48 && randomAscii <= 57) {
                    randomChars[count] = (char)('0' + (randomAscii - 48));
                    count++;
                } else if (randomAscii >= 65 && randomAscii <= 90) {
                    randomChars[count] = (char)('A' + (randomAscii - 65));
                    count++;
                } else if (randomAscii >= 97 && randomAscii <= 122) {
                    randomChars[count] = (char)('a' + (randomAscii - 97));
                    count++;
                }
            }
            id = String.format("%s-%d-%s", hostName,
                    System.currentTimeMillis(), new String(randomChars));
        } catch (UnknownHostException e) {
            logger.warn("Failed to get the host name.", e);
        }
        return id;
    }
}