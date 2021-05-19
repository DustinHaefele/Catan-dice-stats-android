package com.dustin.helloworld.dto;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerStringDto {
    private String user_name;
    private String wins;
    private String games_played;
    private String win_pct;


}

