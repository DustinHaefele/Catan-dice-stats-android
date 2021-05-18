package com.dustin.helloworld.dto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
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
    private ArrayList<String> losers;
    private String winner;

    public Map<String, Integer> getCountMap() {
        return countMap;
    }

    public Map<String, Double> getCountStatsMap() {
        return countStatsMap;
    }

    public JSONObject getCountDtoJson() {
        JSONObject countMapJson = createJsonMap(this.countMap);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonLosers = new JSONArray(losers);

        try {
            jsonObject.put("countMap", countMapJson);
            jsonObject.put("losers", jsonLosers);
            jsonObject.put("winner", winner);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return jsonObject;
    }

    public JSONObject createJsonMap(Map<String, Integer> map) {
        JSONObject countMapJson = new JSONObject();

        try {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                countMapJson.put(key, value);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countMapJson;
    }

}
