package com.waiwaiwai.mydesign.prototype;

import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/21 9:27
 * @Description: 10万个key, 查询搜索次数并更新成最新的.
 * 现在有一个新的要求，需要每次提供的版本是一致的，不能是有V2版本的也有V3版本的
 * demo2的方法并不好，每次更新的时候都需要去查询10万条数据，而有些数据可能并没有变
 * 有点浪费资源和时间
 * 改进 使用原型模式
 * demo3中的拷贝是浅拷贝，只是复制了内存地址，
 * 改进 使用深拷贝拷贝对象
 * 使用遍历深拷贝对象的方式也太浪费时间了
 * 改进使用浅拷贝，有更新的数据时，从浅拷贝中移除，并插入新的数据。
 */
public class PrototypeDemo5 {
    private HashMap<String, SearchWord> currentKeyWords = new HashMap<>();
    private long lastUpdateTime = -1;

    public void refresh() {
        // 递归遍历拷贝对象。
        HashMap<String, SearchWord> newKeyWords = (HashMap<String, SearchWord>) currentKeyWords.clone();

        // 查询更新的数据
        List<SearchWord> list = getSearchWords(lastUpdateTime);
        long maxUpdateTime = lastUpdateTime;
        for (SearchWord searchWord : list) {
            if (searchWord.getLastUpdateTime() > maxUpdateTime) {
                maxUpdateTime = searchWord.getLastUpdateTime();
            }
            if (newKeyWords.containsKey(searchWord.getKeyword())) {
               newKeyWords.remove(searchWord.getKeyword());
            }
            currentKeyWords.put(searchWord.getKeyword(), searchWord);
        }
        currentKeyWords = newKeyWords;
        lastUpdateTime = maxUpdateTime;
    }

    private List<SearchWord> getSearchWords(long lastUpdateTime) {
        List<SearchWord> list = new ArrayList<>();
        SearchWord searchWord = new SearchWord();
        list.add(searchWord);
        return list;
    }

    @Data
    private static class SearchWord {
        private long lastUpdateTime;
        private String keyword;
        private long count;
    }

    public Object deepCopy(Object object) throws IOException, ClassNotFoundException {
        // 先将对象序列化
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(object);
        // 反序列化为对象
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();
    }
}


