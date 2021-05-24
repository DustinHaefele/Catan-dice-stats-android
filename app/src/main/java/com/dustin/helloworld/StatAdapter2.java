package com.dustin.helloworld;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dustin.helloworld.dto.GameStatsDto;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StatAdapter2 extends RecyclerView.Adapter<StatAdapter2.StatsViewHolder> {

    private ArrayList<String> mStatsList = new ArrayList<>();
    private GameStatsDto gameStatsDto;
    private DecimalFormat df = new DecimalFormat("##0.00");

    public static class StatsViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView stat;

        public StatsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.total_stat_name);
            stat = itemView.findViewById(R.id.total_stat);
        }
    }

    public StatAdapter2(GameStatsDto gameStats) {
        mStatsList.add("Total Rolls");
        mStatsList.add("Total Games");
        mStatsList.add("Rolls/Game");
        gameStatsDto = gameStats;
    }

    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_stat2, parent, false);
        return new StatsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int position) {
        String key = mStatsList.get(position);
        String value = "";

        switch(key) {
            case "Total Rolls":
                value = gameStatsDto.getOverallTotal().toString();
                break;
            case "Total Games":
                value = gameStatsDto.getGameCount().toString();
                break;
            case "Rolls/Game":
                value = gameStatsDto.getRollsPerGame().toString();
                break;
        }



        holder.name.setText(key);
        holder.stat.setText(value);
    }

    @Override
    public int getItemCount() {
        return mStatsList.size();
    }
}
