package com.dustin.helloworld.dto;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CountDto implements Serializable {

    private Map<String,Integer> countMap;
    private Map<String,Double> countStatsMap;
    private List<String> losers;
    private String winner;

    public Map<String, Integer> getCountMap() {
        return countMap;
    }

    public Map<String, Double> getCountStatsMap() {
        return countStatsMap;
    }
}
