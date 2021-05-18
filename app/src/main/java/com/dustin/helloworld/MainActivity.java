package com.dustin.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import com.dustin.helloworld.dto.PlayerDto;
import com.dustin.helloworld.services.CountService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GameFragment.OnFragmentInteractionListener, GamePlayersFragment.OnFragmentInteractionListener {


    private CountDto countDto = CountService.initializeCountDto();

    private FrameLayout fragmentContainer;
    private BottomNavigationView bottomNav;
    private ArrayList<PlayerDto> players = new ArrayList<>();
    private RequestQueue mQueue;



    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav =  (BottomNavigationView) findViewById(R.id.bottom_nav_bar);
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        mQueue = Volley.newRequestQueue(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GameFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {

                switch(item.getItemId()) {
                    case R.id.nav_game:
                        openFragment(countDto, "GAME");
                        break;
                    case R.id.nav_players:
                        openFragment(countDto, "GAME_PLAYERS");
                        break;
                    case R.id.nav_stats:
                        openFragment(countDto, "STATS");
                        break;
                }
                return true;
            };
    public void openFragment(CountDto countDto, String type) {
        Fragment fragment;
        String tag = null;
        if(type.equals("GAME")) {
            fragment = GameFragment.newInstance(countDto);
            tag = "GAMES_FRAGMENT";
        } else if (type.equals("STATS")) {
            fragment = StatsFragment.newInstance(countDto);
            tag = "STATS_FRAGMENT";
        } else {
            fragment = GamePlayersFragment.newInstance(countDto);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag).commit();
    }

    private void getPlayerStats() {
        String url = "https://catan-dice-stats.herokuapp.com/api/player";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try{
                        JSONArray playersJson = response.getJSONArray("players");

                        for(int i =0 ; i < playersJson.length(); i++) {
                            JSONObject player = playersJson.getJSONObject(i);
                            Integer id = player.getInt("id");
                            String playerName = player.getString("user_name");
                            String fullName = player.getString("full_name");
                            Integer playerWins = player.getInt("wins");
                            Integer playerGames = player.getInt("games_played");
                            Double playerPct = player.getDouble("win_pct");

                            PlayerDto playerDto = new PlayerDto(id, playerName, fullName, playerWins, playerGames, playerPct);

                            players.add(playerDto);
                        }

                    } catch(Exception e) {
                        e.printStackTrace();
                    }

                }, Throwable::printStackTrace);
        mQueue.add(request);
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

}