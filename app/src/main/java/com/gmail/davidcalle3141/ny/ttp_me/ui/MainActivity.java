package com.gmail.davidcalle3141.ny.ttp_me.ui;

import android.os.Bundle;

import com.gmail.davidcalle3141.ny.ttp_me.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bottom_navigation_view) BottomNavigationView navigationView;

    private NavController navController;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = menuItem -> {
        switch (menuItem.getItemId()){
            case R.id.navigation_tweets:
                navController.navigate(R.id.action_global_tweetsFragment);
                return true;
            case R.id.navigation_search:
                navController.navigate(R.id.action_global_searchFragment);
                return true;
            case R.id.navigation_groups:
                navController.navigate(R.id.action_global_groupsFragment);
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavBehaviour());
    }



}
