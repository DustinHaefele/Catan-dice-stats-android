package com.dustin.helloworld;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dustin.helloworld.dto.CountDto;
import com.dustin.helloworld.dto.PlayerDto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayersFragment extends Fragment {

    private ArrayList<PlayerDto> players = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;




    private static final String PLAYERS_DTO = "players";

    private PlayersFragment.OnFragmentInteractionListener mListener;

    public static PlayersFragment newInstance(List<PlayerDto> playerDtoList) {
        PlayersFragment fragment = new PlayersFragment();
        Bundle args = new Bundle();
        args.putSerializable(PLAYERS_DTO, (Serializable) playerDtoList);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player, container, false);

        mRecyclerView = v.findViewById(R.id.player_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayout = new LinearLayoutManager(this.getContext());

        for(PlayerDto player : players) {
            Log.d("player_name", player.getFull_name());
        }

        mRecyclerView.setLayoutManager(mLayout);

        PlayerDto player = new PlayerDto(1,"theu", "theu", 1,1,1.0);

        players.add(player);
        for(PlayerDto playera : players) {
            Log.d("HERE1", "HERE");
            Log.d("HERE", playera.getGames_played().toString());
        }

        mAdapter = new PlayerAdapter(players);
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }


    @Override
    @NonNull
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(ArrayList<PlayerDto> playerDtoList);
    }
}
