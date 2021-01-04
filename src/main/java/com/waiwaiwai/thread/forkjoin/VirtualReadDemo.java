package com.waiwaiwai.thread.forkjoin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: wangzhenglei
 * @DateTime: 2021/1/4 10:51
 * @Description: 模拟读取文件, 使用 fork/join 统计单词的个数
 */
public class VirtualReadDemo {

    public static void main(String[] args) {
        String[] fc = {"hello world", "hello me", "hello fork", "hello join", "fork join in world"};
        //创建ForkJoin线程池
        ForkJoinPool fjp = new ForkJoinPool(3);
        MR mr = new MR(fc, 0, fc.length);
        Map<String, Long> invoke = fjp.invoke(mr);
        invoke.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    public static class MR extends RecursiveTask<Map<String, Long>> {
        private final String[] fc;
        private final int start;
        private final int end;

        public MR(String[] fc, int start, int end) {
            this.fc = fc;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Map<String, Long> compute() {
            if (end - start == 1) {
                return cal(fc[start]);
            } else {     // 4     1
                int mid = (end - start) / 2 + start; // 2
                MR mr1 = new MR(fc, start, mid);
                mr1.fork();
                MR mr2 = new MR(fc, mid, end);
                return merge(mr2.compute(), mr1.join());
            }
        }

        private Map<String, Long> merge(Map<String, Long> r1, Map<String, Long> r2) {
            Map<String, Long> result = new HashMap<>(r1);
//            r2.forEach((k, v) -> {
//                Long aLong = result.get(k);
//                if (aLong != null) {
//                    result.put(k, v + aLong);
//                } else {
//                    result.put(k, v);
//                }
//            });
            r2.forEach((k, v) -> result.merge(k, v, Long::sum));
            return result;
        }

        private Map<String, Long> cal(String line) {
            String[] words = line.split("\\s+");
            Map<String, Long> result = new HashMap<>();
            for (String word : words) {
                result.merge(word, 1L, Long::sum);
//                Long aLong = result.get(word);
//                if (aLong != null) {
//                    result.put(word, aLong + 1);
//                } else {
//                    result.put(word, 1L);
//                }
            }
            return result;
        }
    }

}
