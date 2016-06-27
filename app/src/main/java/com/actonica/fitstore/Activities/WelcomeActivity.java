package com.actonica.fitstore.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.actonica.fitstore.R;
import com.actonica.fitstore.Helpers.SharedPrefsHelper;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        SharedPrefsHelper sphelper = new SharedPrefsHelper(WelcomeActivity.this);
        String saved_token = sphelper.getSavedToken();
        if (!saved_token.equals("")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void go_next(View v) {
        Intent intent = new Intent(this, SigninActivity.class);
        startActivity(intent);
        finish();
    }
}
