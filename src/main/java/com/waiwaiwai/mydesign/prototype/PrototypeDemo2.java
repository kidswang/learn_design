package com.waiwaiwai.mydesign.prototype;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/21 9:27
 * @Description: 10万个key,查询搜索次数并更新成最新的.
 * 现在有一个新的要求，需要每次提供的版本是一致的，不能是有V2版本的也有V3版本的
 */
public class PrototypeDemo2 {
    private HashMap<String, SearchWord> currentKeyWords = new HashMap<>();

    public void refresh() {
        // 查询所有的数据
        List<SearchWord> list =  getSearchWords();
        HashMap<String, SearchWord> newKeyWords = new HashMap<>();
        for (SearchWord searchWord : list) {
            newKeyWords.put(searchWord.getKeyword(), searchWord);
        }
        // 这样只能替换map
        currentKeyWords = newKeyWords;
    }

    private List<SearchWord> getSearchWords() {
        List<SearchWord> list = new ArrayList<>();
        SearchWord searchWord = new SearchWord();
        list.add(searchWord);
        return list;
    }

    @Data
    private static class SearchWord {
        private long lastUpdateTime;
        private String keyword;
    }
}


