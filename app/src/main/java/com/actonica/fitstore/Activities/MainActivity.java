package com.actonica.fitstore.Activities;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.actonica.fitstore.Fragments.ActiveFragment;
import com.actonica.fitstore.Fragments.FitStoreFragment;
import com.actonica.fitstore.Fragments.SettingsFragment;
import com.actonica.fitstore.Helpers.SharedPrefsHelper;
import com.actonica.fitstore.Helpers.UserInfoSyncer;
import com.actonica.fitstore.Models.Program;
import com.actonica.fitstore.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarFragment;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if(intent.hasExtra("loadPrograms"))
            UserInfoSyncer.fillActivePrograms(MainActivity.this);


        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.noTabletGoodness();

        mBottomBar.setFragmentItems(getSupportFragmentManager(), R.id.fragments_container,
                new BottomBarFragment(FitStoreFragment.newInstance(), R.drawable.ic_fitstore, "FitStore"),
                new BottomBarFragment(ActiveFragment.newInstance(), R.drawable.ic_active, "Active"),
                new BottomBarFragment(SettingsFragment.newInstance(), R.drawable.ic_settings, "Settings")
        );


        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItemFitstore) {
                    // The user selected item number one.
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItemFitstore) {
                    // The user reselected item number one, scroll your content to top.
                }
            }
        });
        mBottomBar.setActiveTabColor("#009688");
        mBottomBar.selectTabAtPosition(1, false);
    }
}
