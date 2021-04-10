package com.dustin.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dustin.helloworld.dto.CountDto;

public class StatsFragment extends Fragment {

    private static String STATS_COUNT_DTO = "statsDto";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }

    public static StatsFragment newInstance(CountDto count) {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();
        args.putSerializable(STATS_COUNT_DTO, count);
        fragment.setArguments(args);
        return fragment;
    }
}
