package com.dustin.helloworld.dto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameStatsDto implements Serializable {

    private Map<String,Integer> rollTotals;
    private Map<String,Double> gameStats;
    private Integer overallTotal;
    private Integer gameCount;

    public Integer getRollsPerGame() {
        return overallTotal / gameCount;
    }

    public static GameStatsDto createFromJSONObject(JSONObject statsJson) {
        try {
            Integer gameCount = statsJson.getInt("gameCount");
            Integer overallTotal = statsJson.getInt("overallTotal");
            Map<String,Integer> rollTotals = GameStatsDto.createRollTotalsFromJson(statsJson.getJSONObject("rollTotals"));
            Map<String,Double> gameStats = GameStatsDto.createGameStatsFromJson(statsJson.getJSONObject("gameStats"));

            return new GameStatsDto(rollTotals, gameStats, overallTotal, gameCount);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return new GameStatsDto();
    }

    public static Map<String,Integer> createRollTotalsFromJson(JSONObject rolls) {
        Map<String,Integer> rollTotals = new HashMap<>();
        try{
            rollTotals.put("two", rolls.getInt("two"));
            rollTotals.put("three", rolls.getInt("three"));
            rollTotals.put("four", rolls.getInt("four"));
            rollTotals.put("five", rolls.getInt("five"));
            rollTotals.put("six", rolls.getInt("six"));
            rollTotals.put("seven", rolls.getInt("seven"));
            rollTotals.put("eight", rolls.getInt("eight"));
            rollTotals.put("nine", rolls.getInt("nine"));
            rollTotals.put("ten", rolls.getInt("ten"));
            rollTotals.put("eleven", rolls.getInt("eleven"));
            rollTotals.put("twelve", rolls.getInt("twelve"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rollTotals;
    }

    public static Map<String,Double> createGameStatsFromJson(JSONObject statsJson) {
        Map<String,Double> stats = new HashMap<>();
        try{
            stats.put("two", statsJson.getDouble("two"));
            stats.put("three", statsJson.getDouble("three"));
            stats.put("four", statsJson.getDouble("four"));
            stats.put("five", statsJson.getDouble("five"));
            stats.put("six", statsJson.getDouble("six"));
            stats.put("seven", statsJson.getDouble("seven"));
            stats.put("eight", statsJson.getDouble("eight"));
            stats.put("nine", statsJson.getDouble("nine"));
            stats.put("ten", statsJson.getDouble("ten"));
            stats.put("eleven", statsJson.getDouble("eleven"));
            stats.put("twelve", statsJson.getDouble("twelve"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return stats;
    }

}
