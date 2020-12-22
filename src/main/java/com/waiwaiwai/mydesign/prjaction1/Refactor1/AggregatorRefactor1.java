package com.waiwaiwai.mydesign.prjaction1.Refactor1;

import com.waiwaiwai.mydesign.prjaction1.improve.RequestInfo;
import com.waiwaiwai.mydesign.prjaction1.improve.RequestStat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/15 11:10
 * @Description: 计算类
 */
public class AggregatorRefactor1 {

    public Map<String, RequestStat> aggregate(Map<String, List<RequestInfo>> requestInfos, long durationInMillis) {
        Map<String, RequestStat> requestStats = new HashMap<>();
        for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
            String apiName = entry.getKey();
            List<RequestInfo> requestInfosPerApi = entry.getValue();
            RequestStat requestStat = doAggregate(requestInfosPerApi, durationInMillis);
            requestStats.put(apiName, requestStat);
            requestStats.put(apiName, requestStat);
        }
        return requestStats;
    }

    private RequestStat doAggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        List<Double> respTimes = new ArrayList<>();
        for (RequestInfo requestInfo : requestInfos) {
            double respTime = requestInfo.getResponseTime();
            respTimes.add(respTime);
        }
        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(max(respTimes));
        requestStat.setMinResponseTime(min(respTimes));
        requestStat.setAvgResponseTime(avg(respTimes));
        requestStat.setP999ResponseTime(percentile999(respTimes));
        requestStat.setP99ResponseTime(percentile99(respTimes));
        requestStat.setCount(respTimes.size());
        requestStat.setTps(tps(respTimes.size(), durationInMillis / 1000));
        return requestStat;
    }

    private long tps(int size, long l) {
        return (size / l * 1000);
    }

    private double percentile99(List<Double> respTimes) {
        long count = respTimes.size();
        return (int) (count * 0.99);
    }

    private double percentile999(List<Double> respTimes) {
        long count = respTimes.size();
        return (int) (count * 0.999);
    }

    private double avg(List<Double> respTimes) {
        if (respTimes.isEmpty()) {
            return 0;
        }
        Double aDouble = respTimes.stream().reduce(Double::sum).get();
        return aDouble / respTimes.size();
    }

    private double min(List<Double> respTimes) {
        return respTimes.stream().min(Double::compareTo).get();
    }

    private double max(List<Double> respTimes) {
        return respTimes.stream().max(Double::compareTo).get();
    }
}
