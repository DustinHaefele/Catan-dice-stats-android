package com.dustin.helloworld;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dustin.helloworld.dto.GameStatsDto;
import com.dustin.helloworld.dto.PlayerDto;
import com.dustin.helloworld.dto.PlayerStringDto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

public class StatAdapter extends RecyclerView.Adapter<StatAdapter.StatsViewHolder> {

    private ArrayList<String> mStatsList = new ArrayList<>();
    private GameStatsDto gameStatsDto;
    private DecimalFormat df = new DecimalFormat("##0.00");

    public static class StatsViewHolder extends RecyclerView.ViewHolder {
        public TextView number;
        public TextView rolls;
        public TextView pct;

        public StatsViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number_stat);
            rolls = itemView.findViewById(R.id.rolls_stat);
            pct = itemView.findViewById(R.id.pct_stat);
        }
    }

    public StatAdapter(GameStatsDto gameStats) {
        mStatsList.add("two");
        mStatsList.add("three");
        mStatsList.add("four");
        mStatsList.add("five");
        mStatsList.add("six");
        mStatsList.add("seven");
        mStatsList.add("eight");
        mStatsList.add("nine");
        mStatsList.add("ten");
        mStatsList.add("eleven");
        mStatsList.add("twelve");
        gameStatsDto = gameStats;
    }

    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_stat, parent, false);
        StatsViewHolder statsViewHolder = new StatsViewHolder(v);
        return statsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int position) {
        String number = mStatsList.get(position);


        holder.number.setText(number);
        holder.rolls.setText(gameStatsDto.getRollTotals().get(number).toString());
        holder.pct.setText(df.format(gameStatsDto.getGameStats().get(number)*100));
    }

    @Override
    public int getItemCount() {
        return mStatsList.size();
    }
}
