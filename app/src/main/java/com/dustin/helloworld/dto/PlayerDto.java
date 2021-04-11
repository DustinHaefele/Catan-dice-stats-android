package com.dustin.helloworld.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerDto {
    private Integer id;
    private String user_name;
    private String full_name;
    private Integer wins;
    private Integer games_played;
    private Double win_pct;
}

