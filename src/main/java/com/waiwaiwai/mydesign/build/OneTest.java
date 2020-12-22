package com.waiwaiwai.mydesign.build;

import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.MD5;
import com.google.common.util.concurrent.AtomicDouble;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class OneTest {

    @Test
    public void test() {
//        ConstructorArg dsf = new ConstructorArg.Builder().setIsRef(true).setArg(1).build();
//        System.out.println(dsf);

//        byte[] bytes = new byte[10];
//        byte[] byte1 = {'c','v','m','b','i'};
//
//        MD5 md5 = MD5.create();
//        Digester digester = md5.setSalt(new byte[]{'c'});
//        String s = digester.digestHex(byte1);
//        System.out.println(s);

        // 原子类在更新的时候使用compareAndSet方法更新
        AtomicInteger i1 = new AtomicInteger(1);
        AtomicInteger i2 = new AtomicInteger(1);

        System.out.println(i1.get() == i2.get());

//        int andUpdate = i.getAndUpdate(operand -> 4);
//        boolean b = i.compareAndSet(4, 10);
//        System.out.println(b);
//        System.out.println(i);
//        System.out.println(andUpdate);
    }

}
