package com.dustin.helloworld;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.dustin.helloworld.dto.CountDto;
import com.dustin.helloworld.dto.PlayerDto;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GamePlayersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GamePlayersFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String COUNT_DTO = "countDto";
    private static final String WINNER_DTO = "winners";
    private static final String LOSER_DTO = "losers";

    private CountDto countDto;
    private ArrayList<String> winnerNames;
    private ArrayList<String> loserNames;
    private OnFragmentInteractionListener mListener;

    Spinner winnerInput;
    Spinner loser1Input;
    Spinner loser2Input;
    Spinner loser3Input;
    Spinner loser4Input;
    Spinner loser5Input;

    Button submit;

    private void submitGame(View view) {
//        winnerInput = view.findViewById(R.id.add_player_1);
        winnerInput = view.findViewById(R.id.spinner1);
        loser1Input = view.findViewById(R.id.spinner2);
        loser2Input = view.findViewById(R.id.spinner3);
        loser3Input = view.findViewById(R.id.spinner4);
        loser4Input = view.findViewById(R.id.spinner5);
        loser5Input = view.findViewById(R.id.spinner6);


        ArrayList<String> losers = new ArrayList<>();

//        String winner = winnerInput.getEditText().getText().toString();
        String winner = winnerInput.getSelectedItem().toString();
        String loser1 = loser1Input.getSelectedItem().toString();
        String loser2 = loser2Input.getSelectedItem().toString();
        String loser3 = loser3Input.getSelectedItem().toString();
        String loser4 = loser4Input.getSelectedItem().toString();
        String loser5 = loser5Input.getSelectedItem().toString();


        if(!loser1.matches("loser")) {
            losers.add(loser1);
        }
        if(!loser2.matches("loser")) {
            losers.add(loser2);
        }
        if(!loser3.matches("loser")) {
            losers.add(loser3);
        }
        if(!loser4.matches("loser")) {
            losers.add(loser4);
        }
        if(!loser5.matches("loser")) {
            losers.add(loser5);
        }
        if(!winner.matches("winner") && !losers.isEmpty()) {
            countDto.setWinner(winner);
            countDto.setLosers(losers);
            sendBack(countDto);
        }
        if(winner.matches("winner")){
            winnerInput.setBackgroundColor(Color.parseColor("#b30000"));
        }
        if(losers.isEmpty()) {
            loser1Input.setBackgroundColor(Color.parseColor("#b30000"));
        }
    }

    public GamePlayersFragment() {
        // Required empty public constructor
    }

    public static GamePlayersFragment newInstance(CountDto count, ArrayList<PlayerDto> players) {
        GamePlayersFragment fragment = new GamePlayersFragment();
        ArrayList<String> winnerNames = new ArrayList<>();
        ArrayList<String> loserNames = new ArrayList<>();
        winnerNames.add("winner");
        loserNames.add("loser");
        for(PlayerDto player: players) {
            winnerNames.add(player.getUser_name());
            loserNames.add(player.getUser_name());
        }
        Bundle args = new Bundle();
        args.putSerializable(COUNT_DTO, count);
        args.putStringArrayList(WINNER_DTO, winnerNames);
        args.putStringArrayList(LOSER_DTO, loserNames);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            countDto = (CountDto) getArguments().getSerializable(COUNT_DTO);
            winnerNames = getArguments().getStringArrayList(WINNER_DTO);
            loserNames = getArguments().getStringArrayList(LOSER_DTO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_players, container, false);

        ArrayAdapter<String> adapterW = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, winnerNames);
        ArrayAdapter<String> adapterL = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, loserNames);


        winnerInput = view.findViewById(R.id.spinner1);
        winnerInput.setAdapter(adapterW);

        loser1Input = view.findViewById(R.id.spinner2);
        loser1Input.setAdapter(adapterL);

        loser2Input = view.findViewById(R.id.spinner3);
        loser2Input.setAdapter(adapterL);

        loser3Input = view.findViewById(R.id.spinner4);
        loser3Input.setAdapter(adapterL);

        loser4Input = view.findViewById(R.id.spinner5);
        loser4Input.setAdapter(adapterL);

        loser5Input = view.findViewById(R.id.spinner6);
        loser5Input.setAdapter(adapterL);

        submit = view.findViewById(R.id.submit_button);

        submit.setOnClickListener(v-> submitGame(view));


        return view;
    }

    public void sendBack(CountDto countDto) {
        if(mListener !=null) {
            mListener.onFragmentInteraction(countDto, true);
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
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
        void onFragmentInteraction(CountDto countDto, Boolean submit);
    }
}