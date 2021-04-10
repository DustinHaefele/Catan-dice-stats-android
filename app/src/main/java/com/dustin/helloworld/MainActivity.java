package com.dustin.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;


import com.dustin.helloworld.dto.CountDto;
import com.dustin.helloworld.services.CountService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements GameFragment.OnFragmentInteractionListener {



    private CountDto countDto = CountService.initializeCountDto();

    private FrameLayout fragmentContainer;
    private BottomNavigationView bottomNav;


    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav =  (BottomNavigationView) findViewById(R.id.bottom_nav_bar);
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GameFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                switch(item.getItemId()) {
                    case R.id.nav_game:
                        openCountFragment(countDto, "GAME");
                        break;
                    case R.id.nav_players:
                        selectedFragment = new PlayersFragment();
                        break;
                    case R.id.nav_stats:
                        openCountFragment(countDto, "STATS");
                        break;
                }
                if(selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
                return true;
            };
    public void openCountFragment(CountDto countDto, String type) {
        Fragment fragment;
        if(type.equals("GAME")) {
            fragment = GameFragment.newInstance(countDto);
        } else {
            fragment = StatsFragment.newInstance(countDto);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.fragment_container, fragment, "GAMES_FRAGMENT").commit();
    }

    public void openPlayerFragment(CountDto countDto) {
        GameFragment fragment = GameFragment.newInstance(countDto);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.fragment_container, fragment, "GAMES_FRAGMENT").commit();
    }


    @Override
    public void onFragmentInteraction(CountDto count) {
        countDto = count;
    }
}