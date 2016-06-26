package com.actonica.fitstore.Activities;

import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.actonica.fitstore.Fragments.ActiveFragment;
import com.actonica.fitstore.Fragments.FitStoreFragment;
import com.actonica.fitstore.Fragments.SettingsFragment;
import com.actonica.fitstore.R;
import com.actonica.fitstore.SharedPrefsHelper;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarFragment;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnMenuTabClickListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
