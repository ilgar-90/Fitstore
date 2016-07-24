package com.actonica.fitstore.Activities;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.actonica.fitstore.Fragments.ActiveFragment;
import com.actonica.fitstore.Fragments.FitStoreFragment;
import com.actonica.fitstore.Fragments.SettingsFragment;
import com.actonica.fitstore.Helpers.UserInfoSyncer;
import com.actonica.fitstore.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;


public class MainActivity extends AppCompatActivity {

    private BottomBar mBottomBar;
    private FitStoreFragment fitStoreFragment;
    private ActiveFragment activeFragment;
    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if(intent.hasExtra("loadPrograms")) {
            UserInfoSyncer.fillActivePrograms(getApplicationContext());
            UserInfoSyncer.fillUserHistory(getApplicationContext());
        }


        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.noTabletGoodness();
        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.setActiveTabColor("#3BB9FF");
        mBottomBar.selectTabAtPosition(1, false);

        fitStoreFragment = new FitStoreFragment();
        activeFragment = new ActiveFragment();
        settingsFragment = new SettingsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragments_container, activeFragment).commit();


        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch (menuItemId){
                    case R.id.bottomBarItemFitstore:
                        getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragments_container, fitStoreFragment).commit();
                        break;
                    case R.id.bottomBarItemActive:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragments_container, activeFragment).commit();
                        break;
                    case R.id.bottomBarItemSettings:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragments_container, settingsFragment).commit();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                //if current tab was re-selected
            }
        });

    }

    public void removeProgram(int programId){
        activeFragment.removeProgram(programId);
    }
}
