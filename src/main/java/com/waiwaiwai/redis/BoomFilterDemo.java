package com.waiwaiwai.redis;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.lang.reflect.Member;
/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/8 15:06
 * @Description: 布隆过滤器
 */
public class BoomFilterDemo {

    public static void main(String[] args) {
        int total = 1000000; // 总数量
        BloomFilter<CharSequence> bf =
                BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total,0.001);

        // 初始化 1000000 条数据到过滤器中
        for (int i = 0; i < total; i++) {
            bf.put("" + i);
        }
//        System.out.println();
        // 判断值是否存在过滤器中
        int count = 0;
        for (int i = 0; i < total + 10000; i++) {
            if (bf.mightContain("" + i)) {
                count++;
            }
        }
        System.out.println("已匹配数量 " + count);
    }

}
