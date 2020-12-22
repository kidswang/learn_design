package com.waiwaiwai.mydesign.prototype;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/21 9:27
 * @Description: 10万个key,查询搜索次数并更新成最新的.
 */
public class PrototypeDemo1 {
    private final ConcurrentHashMap<String, SearchWord> currentKeyWords = new ConcurrentHashMap<>();
    private long lastUpdateTime = -1;

    public void refresh() {
        // 只查询最近更新的数据
        List<SearchWord> list =  getSearchWords(lastUpdateTime);
        long maxNewUpdatedTime = lastUpdateTime;
        for (SearchWord searchWord : list) {
            if (searchWord.getLastUpdateTime() > maxNewUpdatedTime) {
                maxNewUpdatedTime = searchWord.getLastUpdateTime();
            }
            if (currentKeyWords.containsKey(searchWord.getKeyword())) {
                currentKeyWords.replace(searchWord.getKeyword(), searchWord);
            } else {
                currentKeyWords.put(searchWord.getKeyword(), searchWord);
            }
        }
        lastUpdateTime = maxNewUpdatedTime;
    }

    private List<SearchWord> getSearchWords(long lastUpdateTime) {
        List<SearchWord> list = new ArrayList<>();
        SearchWord searchWord = new SearchWord();
        searchWord.setLastUpdateTime(lastUpdateTime);
        list.add(searchWord);
        return list;
    }

    @Data
    private static class SearchWord {
        private long lastUpdateTime;
        private String keyword;
    }
}


