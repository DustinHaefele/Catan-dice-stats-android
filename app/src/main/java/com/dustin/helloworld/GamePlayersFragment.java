package com.dustin.helloworld;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dustin.helloworld.dto.CountDto;
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

    private CountDto countDto;
    private OnFragmentInteractionListener mListener;

    TextInputLayout winnerInput;
    TextInputLayout loser1Input;
    TextInputLayout loser2Input;
    TextInputLayout loser3Input;
    TextInputLayout loser4Input;
    TextInputLayout loser5Input;

    Button submit;

    private void submitGame(View view) {
        winnerInput = view.findViewById(R.id.add_player_1);
        loser1Input = view.findViewById(R.id.add_player_2);
        loser2Input = view.findViewById(R.id.add_player_3);
        loser3Input = view.findViewById(R.id.add_player_4);
        loser4Input = view.findViewById(R.id.add_player_5);
        loser5Input = view.findViewById(R.id.add_player_6);

        ArrayList<String> losers = new ArrayList<>();

        String winner = winnerInput.getEditText().getText().toString();
        String loser1 = loser1Input.getEditText().getText().toString();
        String loser2 = loser2Input.getEditText().getText().toString();
        String loser3 = loser3Input.getEditText().getText().toString();
        String loser4 = loser4Input.getEditText().getText().toString();
        String loser5 = loser5Input.getEditText().getText().toString();

        if(!loser1.matches("")) {
            losers.add(loser1);
        }
        if(!loser2.matches("")) {
            losers.add(loser2);
        }
        if(!loser3.matches("")) {
            losers.add(loser3);
        }
        if(!loser4.matches("")) {
            losers.add(loser4);
        }
        if(!loser5.matches("")) {
            losers.add(loser5);
        }
        if(!winner.matches("")) {
            countDto.setWinner(winner);
            countDto.setLosers(losers);
        }

        sendBack(countDto);

    }

    public GamePlayersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment GamePlayersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GamePlayersFragment newInstance(CountDto count) {
        GamePlayersFragment fragment = new GamePlayersFragment();
        Bundle args = new Bundle();
        args.putSerializable(COUNT_DTO, count);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mLosers = getArguments().getStringArrayList(LOSERS);
//            mWinner = getArguments().getString(WINNER);
            countDto = (CountDto) getArguments().getSerializable(COUNT_DTO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_players, container, false);



        submit = view.findViewById(R.id.submit_button);

        submit.setOnClickListener(v-> {
            submitGame(view);
        });


        return view;
    }

    public void sendBack(CountDto countDto) {
        if(mListener !=null) {
            mListener.onFragmentInteraction(countDto, true);
        }

    }

    @Override
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
        void onFragmentInteraction(CountDto countDto, Boolean submit);
    }
}