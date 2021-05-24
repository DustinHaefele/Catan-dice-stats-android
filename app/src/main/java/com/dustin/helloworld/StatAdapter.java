package com.dustin.helloworld;

import android.annotation.SuppressLint;
import android.graphics.Color;
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
import java.util.HashMap;
import java.util.Map;

public class StatAdapter extends RecyclerView.Adapter<StatAdapter.StatsViewHolder> {

    private ArrayList<String> mStatsList = new ArrayList<>();
    private GameStatsDto gameStatsDto;
    private DecimalFormat df = new DecimalFormat("##0.00");
    private Map<String, Double> standards = new HashMap<>();

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
        standards.put("two", 2.78);
        standards.put("three", 5.56);
        standards.put("four", 8.33);
        standards.put("five", 11.11);
        standards.put("six", 13.89);
        standards.put("seven", 16.67);
        standards.put("twelve", 2.78);
        standards.put("eleven", 5.56);
        standards.put("ten", 8.33);
        standards.put("nine", 11.11);
        standards.put("eight", 13.89);
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
        return new StatsViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int position) {
        String number = mStatsList.get(position);
        double pct = gameStatsDto.getGameStats().get(number)*100;
        Double expected = standards.get(number);
        String color = "#000000";

        if(pct > expected) {
            color = "#008000";
        } else if (pct < expected) {
            color = "#b30000";
        }



        holder.number.setText(number);
        holder.rolls.setText(gameStatsDto.getRollTotals().get(number).toString());
        holder.pct.setText(df.format(gameStatsDto.getGameStats().get(number)*100));
        holder.pct.setTextColor(Color.parseColor(color));
    }

    @Override
    public int getItemCount() {
        return mStatsList.size();
    }
}
