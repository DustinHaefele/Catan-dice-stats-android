package com.dustin.helloworld.dto;

import org.json.JSONObject;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerDto implements Serializable {
    private Integer id;
    private String user_name;
    private String full_name;
    private Integer wins;
    private Integer games_played;
    private Double win_pct;

    public static PlayerDto createFromJSONObject(JSONObject playerJson) {
        try {
            Integer id = playerJson.getInt("id");
            String user_name = playerJson.getString("user_name");
            String full_name = playerJson.getString("full_name");
            Integer wins = playerJson.getInt("wins");
            Integer games_played = playerJson.getInt("games_played");
            Double win_pct = playerJson.getDouble("win_pct");
            return new PlayerDto(id, user_name, full_name,wins,games_played,win_pct);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return new PlayerDto();
    }

    public static JSONObject createJSON(PlayerDto playerDto) {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("user_name", playerDto.getUser_name());
            jsonObject.put("full_name", playerDto.getFull_name());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonObject;

    }

}

