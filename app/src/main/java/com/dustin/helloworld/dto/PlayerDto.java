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
    private String playerName;
    private Integer playerWins;
    private Integer playerGames;
    private Double playerPct;
}
