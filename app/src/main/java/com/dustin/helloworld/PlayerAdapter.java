package com.dustin.helloworld;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dustin.helloworld.dto.PlayerDto;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private ArrayList<PlayerDto> mPlayerList;

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
        mPlayerList = playerList;
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
        PlayerDto currentPlayer = mPlayerList.get(position);

        holder.playerName.setText(currentPlayer.getUser_name());
        holder.playerWins.setText(currentPlayer.getWins());
        holder.playerGames.setText(currentPlayer.getGames_played());
        holder.playerPct.setText(Double.toString(currentPlayer.getWin_pct()));
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
