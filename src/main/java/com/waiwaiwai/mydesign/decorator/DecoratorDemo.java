package com.waiwaiwai.mydesign.decorator;

import org.junit.Test;

import java.io.*;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/30 13:06
 * @Description: 装饰器模式
 */
public class DecoratorDemo {

    @Test
    public void test() {
        FileInput in = new FileInput("");
        DataInput din = new DataInput(in);
        BufferedInput bin = new BufferedInput(din);
        bin.read();


    }

    @Test
    public void test2() throws FileNotFoundException {
        File file;
        FileInputStream fin = new FileInputStream("file");

        DataInputStream dataInputStream = new DataInputStream(fin);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(dataInputStream);


    }


}
