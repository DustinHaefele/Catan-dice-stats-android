package com.dustin.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dustin.helloworld.dto.CountDto;
import com.dustin.helloworld.dto.GameStatsDto;
import com.dustin.helloworld.dto.PlayerDto;
import com.dustin.helloworld.services.CountService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GameFragment.OnFragmentInteractionListener, GamePlayersFragment.OnFragmentInteractionListener, AddPlayersFragment.OnFragmentInteractionListener {


    private CountDto countDto = CountService.initializeCountDto();

    private FrameLayout fragmentContainer;
    private BottomNavigationView bottomNav;
    private ArrayList<PlayerDto> players = new ArrayList<>();
    private GameStatsDto gameStatsDto;
    private RequestQueue mQueue;
    private PlayerDto playerToAdd;



    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav =  (BottomNavigationView) findViewById(R.id.bottom_nav_bar);
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        mQueue = Volley.newRequestQueue(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GameFragment()).commit();
        this.getPlayerStats();
        this.getGameStats();
    }

    @SuppressLint("NonConstantResourceId")
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {

                switch(item.getItemId()) {
                    case R.id.nav_game:
                        openFragment(countDto, "GAME");
                        break;
                    case R.id.nav_players:
                        openFragment(countDto, "GAME_PLAYERS");
                        break;
                    case R.id.nav_player_stats:
                        openFragment(countDto, "PLAYER_STATS");
                        break;
                    case R.id.nav_stats:
                        openFragment(countDto, "STATS");
                        break;
                    case R.id.nav_add_player:
                        openFragment(countDto, "ADD_PLAYER");
                        break;
                }
                return true;
            };
    public void openFragment(CountDto countDto, String type) {
        Fragment fragment;
        String tag = null;
        switch (type) {
            case "GAME":
                fragment = GameFragment.newInstance(countDto);
                tag = "GAMES_FRAGMENT";
                break;
            case "STATS":
                fragment = StatsFragment.newInstance(gameStatsDto);
                tag = "STATS_FRAGMENT";
                break;
            case "PLAYER_STATS":
                fragment = PlayersFragment.newInstance(players);
                tag = "PLAYER_STATS_FRAGMENT";
                break;
            case "ADD_PLAYER":
                fragment = AddPlayersFragment.newInstance();
                tag = "PLAYER_STATS_FRAGMENT";
                break;
            default:
                fragment = GamePlayersFragment.newInstance(countDto, players);
                break;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag).commit();
    }

    private void getPlayerStats() {
        String url = "https://catan-dice-stats.herokuapp.com/api/player";


        JsonObjectRequest playerRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try{
                        JSONArray playersArray = response.getJSONArray("players");
                        for(int i =0; i<playersArray.length();i++) {
                            JSONObject playerJson = playersArray.getJSONObject(i);
                            PlayerDto playerDto = PlayerDto.createFromJSONObject(playerJson);
                            players.add(playerDto);
                        }
                    } catch (Exception ex) {
                        Log.e("ERROR", "ERROR");
                        ex.printStackTrace();
                    }

                }, Throwable::printStackTrace);
        mQueue.add(playerRequest);

    }

    private void getGameStats() {
        String url = "https://catan-dice-stats.herokuapp.com/api/game/stats";


        JsonObjectRequest gameRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try{

                        gameStatsDto = GameStatsDto.createFromJSONObject(response);

                    } catch (Exception ex) {
                        Log.e("ERROR", "ERROR");
                        ex.printStackTrace();
                    }

                }, Throwable::printStackTrace);
        mQueue.add(gameRequest);
    }


    @Override
    public void onFragmentInteraction(CountDto count, Boolean submit) {
        countDto = count;
        if(submit && !countDto.getWinner().isEmpty() && !countDto.getLosers().isEmpty()) {
            String url = "https://catan-dice-stats.herokuapp.com/api/game";
            JSONObject body = countDto.getCountDtoJson();

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, url, body, response -> {
                        Log.i("RESPONSE", response.toString());
                        openFragment(CountService.initializeCountDto(), "GAME");
                    }, error -> {
                        openFragment(countDto, "GAME");
                        error.printStackTrace();
                    });
            mQueue.add(jsonObjectRequest);
        }
    }

    @Override
    public void onFragmentInteraction(PlayerDto player) {
        playerToAdd = player;
        if(!playerToAdd.getUser_name().isEmpty() && !playerToAdd.getFull_name().isEmpty()) {
            String url = "https://catan-dice-stats.herokuapp.com/api/player";
            JSONObject body = PlayerDto.createJSON(playerToAdd);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, url, body, response -> {
                        Log.i("RESPONSE", response.toString());
                        openFragment(CountService.initializeCountDto(), "GAME");
                    }, error -> {
                        openFragment(countDto, "GAME");
                        error.printStackTrace();
                    });
            mQueue.add(jsonObjectRequest);
        }
    }

}