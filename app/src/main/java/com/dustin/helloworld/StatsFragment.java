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
    private RecyclerView mRecyclerView2;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView.LayoutManager mLayout;
    private RecyclerView.LayoutManager mLayout2;

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
        mRecyclerView2 = view.findViewById(R.id.stats2_recycler);
        mRecyclerView2.setHasFixedSize(true);
        mRecyclerView.setHasFixedSize(true);
        mLayout = new LinearLayoutManager(this.getContext());
        mLayout2 = new LinearLayoutManager(this.getContext());

        mRecyclerView.setLayoutManager(mLayout);
        mRecyclerView2.setLayoutManager(mLayout2);

        mAdapter = new StatAdapter(statsDto);
        mAdapter2 = new StatAdapter2(statsDto);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView2.setAdapter(mAdapter2);

        return view;
    }
}
