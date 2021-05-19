package com.dustin.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dustin.helloworld.dto.CountDto;
import com.dustin.helloworld.dto.GameStatsDto;

public class StatsFragment extends Fragment {

    GameStatsDto statsDto;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;

    private static String STATS_COUNT_DTO = "statsDto";

    public static StatsFragment newInstance(GameStatsDto count) {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();
        args.putSerializable(STATS_COUNT_DTO, count);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            statsDto = (GameStatsDto) getArguments().getSerializable(STATS_COUNT_DTO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        mRecyclerView = view.findViewById(R.id.stats_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayout = new LinearLayoutManager(this.getContext());

        mRecyclerView.setLayoutManager(mLayout);

        mAdapter = new StatAdapter(statsDto);
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }
}
