package com.dustin.helloworld;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dustin.helloworld.dto.CountDto;
import com.dustin.helloworld.dto.PlayerDto;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPlayersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPlayersFragment extends Fragment {

    private static final String PLAYER_DTO = "playerDto";

    private static PlayerDto playerDto = new PlayerDto();
    private OnFragmentInteractionListener mListener;


    TextInputLayout userNameInput;
    TextInputLayout fullNameInput;

    Button submit;

    private void submitPlayer(View view) {
        userNameInput = view.findViewById(R.id.add_player__username);
        fullNameInput = view.findViewById(R.id.add_player_full_name);

        String userName = userNameInput.getEditText().getText().toString();
        String fullName = fullNameInput.getEditText().getText().toString();

        if(!userName.matches("") && !fullName.matches("")) {
            playerDto.setUser_name(userName);
            playerDto.setFull_name(fullName);
        }

        sendBack(playerDto);

    }

    public AddPlayersFragment() {
        // Required empty public constructor
    }

    public static AddPlayersFragment newInstance() {
        AddPlayersFragment fragment = new AddPlayersFragment();
        Bundle args = new Bundle();
        args.putSerializable(PLAYER_DTO, playerDto);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_player, container, false);


        submit = view.findViewById(R.id.submit_player);

        submit.setOnClickListener(v-> submitPlayer(view));

        return view;
    }

    public void sendBack(PlayerDto playerDto) {
        if(mListener !=null) {
            mListener.onFragmentInteraction(playerDto);
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
        void onFragmentInteraction(PlayerDto playerDto);
    }
}