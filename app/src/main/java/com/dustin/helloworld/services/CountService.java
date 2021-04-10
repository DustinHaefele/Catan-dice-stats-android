package com.dustin.helloworld.services;

import com.dustin.helloworld.dto.CountDto;

import java.util.HashMap;
import java.util.Map;

public class CountService {

    public static CountDto initializeCountDto() {
        CountDto countDto = new CountDto();
        Map<String,Double> countStatsMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("two", 0);
        countMap.put("three", 0);
        countMap.put("four", 0);
        countMap.put("five", 0);
        countMap.put("six", 0);
        countMap.put("seven", 0);
        countMap.put("eight", 0);
        countMap.put("nine", 0);
        countMap.put("ten", 0);
        countMap.put("eleven", 0);
        countMap.put("twelve", 0);
        countDto.setCountMap(countMap);

        for (Map.Entry<String,Integer> entry : countMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            Double dubValue = (double)value;

            countStatsMap.put(key,dubValue);
    }

        countDto.setCountStatsMap(countStatsMap);

        return countDto;
    }

    private static Integer getTotalRolls(CountDto countDto) {
        int total = 0;
        for (Map.Entry<String,Integer> entry : countDto.getCountMap().entrySet()) {
            total = total + entry.getValue();
        }
        return total;
    }

    public static CountDto updateStats(CountDto countDto) {
        Integer total = getTotalRolls(countDto);

        for (Map.Entry<String,Integer> entry : countDto.getCountMap().entrySet()) {
            Double pct = ((double)entry.getValue()) / ((double)total);
            countDto.getCountStatsMap().put(entry.getKey(), pct);
        }

        return countDto;
    }


}
