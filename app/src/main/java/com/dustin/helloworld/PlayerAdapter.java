package com.dustin.helloworld;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dustin.helloworld.dto.PlayerDto;
import com.dustin.helloworld.dto.PlayerStringDto;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private ArrayList<PlayerStringDto> mPlayerList = new ArrayList<>();
    private DecimalFormat df = new DecimalFormat("##0.00");

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        public TextView playerName;
        public TextView playerWins;
        public TextView playerGames;
        public TextView playerPct;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.player_name);
            playerWins = itemView.findViewById(R.id.player_wins);
            playerGames = itemView.findViewById(R.id.player_games);
            playerPct = itemView.findViewById(R.id.player_win_pct);

        }
    }

    public PlayerAdapter(ArrayList<PlayerDto> playerList) {
        PlayerStringDto headers = new PlayerStringDto();
        headers.setUser_name("Name");
        headers.setGames_played("Games");
        headers.setWins("Wins");
        headers.setWin_pct("");
        mPlayerList.add(headers);
        playerList.sort((PlayerDto p1, PlayerDto p2)->{
            if (p1.getWin_pct() > p2.getWin_pct())
                return -1;
            if (p1.getWin_pct() < p2.getWin_pct())
                return 1;
            return 0;
        });
        for(PlayerDto player : playerList) {
            PlayerStringDto playerToAdd = new PlayerStringDto();
            String winPct = df.format(player.getWin_pct() * 100);
            String wins = Integer.toString(player.getWins());
            String games = Integer.toString(player.getGames_played());
            playerToAdd.setWin_pct(winPct);
            playerToAdd.setWins(wins);
            playerToAdd.setGames_played(games);
            playerToAdd.setUser_name(player.getUser_name());
            if(player.getGames_played() > 0) {
                mPlayerList.add(playerToAdd);
            }
        }
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_player, parent, false);
        PlayerViewHolder playerViewHolder = new PlayerViewHolder(v);
        return playerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        PlayerStringDto currentPlayer = mPlayerList.get(position);

        holder.playerName.setText(currentPlayer.getUser_name());
        holder.playerWins.setText(currentPlayer.getWins());
        holder.playerGames.setText(currentPlayer.getGames_played());
        holder.playerPct.setText(currentPlayer.getWin_pct()+"%");
    }

    @Override
    public int getItemCount() {
        return mPlayerList.size();
    }
}
