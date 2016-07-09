package com.actonica.fitstore.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actonica.fitstore.API.JuiceFitAPIHandler;
import com.actonica.fitstore.ApiResponsesGson.RegisterUserResponse;
import com.actonica.fitstore.ApiResponsesGson.VerifyUserResponse;
import com.actonica.fitstore.Helpers.UserInfoSyncer;
import com.actonica.fitstore.R;
import com.actonica.fitstore.Helpers.SharedPrefsHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity {

    TextView request_code;
    TextView get_new_code;
    TextView refresh_code;

    LinearLayout refresh_code_block;

    EditText code_input;
    EditText phone_edit;
    LinearLayout error_block;

    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        phone_edit = (EditText)findViewById(R.id.phone_edit);
        code_input = (EditText)findViewById(R.id.code_input);

        request_code = (TextView)findViewById(R.id.request_code);
        get_new_code = (TextView)findViewById(R.id.get_new_code);
        refresh_code = (TextView)findViewById(R.id.refresh_code);


        request_code.setOnClickListener(request_phone_code);
        get_new_code.setOnClickListener(request_phone_code);
        refresh_code.setOnClickListener(request_phone_code);

        refresh_code_block = (LinearLayout)findViewById(R.id.refresh_code_block);
        error_block = (LinearLayout)findViewById(R.id.error_block);

        phone_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 10)
                    request_code.setEnabled(false);
                else
                    request_code.setEnabled(true);
            }
        });

        code_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() == 4) {
                    showProgressDialog("Проверка кода...");
                    JuiceFitAPIHandler.verifyUser(phone_edit.getText().toString(), code_input.getText().toString(), new Callback<VerifyUserResponse>() {
                        @Override
                        public void onResponse(Call<VerifyUserResponse> call, Response<VerifyUserResponse> response) {
                            if (response.body() != null && response.isSuccessful()) {
                                String token = response.body().token;
                                boolean is_new = response.body().is_new;
                                SharedPrefsHelper sphelper = new SharedPrefsHelper(SigninActivity.this);
                                sphelper.saveToken(token);
                                Toast.makeText(SigninActivity.this, "Saved token: "+token, Toast.LENGTH_LONG).show();

                                Intent i = new Intent(getBaseContext(), MainActivity.class);
                                i.putExtra("loadPrograms", true);
                                startActivity(i);
                                finish();

                            } else { //need to handle error (or not?)
                                //response.errorBody();
                                code_input.setText("");
                                refresh_code_block.setVisibility(View.GONE);
                                error_block.setVisibility(View.VISIBLE);
                                code_input.requestFocus();
                            }
                            hideProgressDialog();
                        }

                        @Override
                        public void onFailure(Call<VerifyUserResponse> call, Throwable t) {
                            Toast.makeText(SigninActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                            hideProgressDialog();
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    View.OnClickListener request_phone_code = new View.OnClickListener() {
        public void onClick(View v) {
            showProgressDialog("Запрашиваем код...");
            JuiceFitAPIHandler.resgisterUser(phone_edit.getText().toString(), new Callback<RegisterUserResponse>() {
                @Override
                public void onResponse(Call<RegisterUserResponse> call, Response<RegisterUserResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        boolean status = response.body().status;
                        if (status && error_block.getVisibility() != View.VISIBLE) {
                            code_input.setVisibility(View.VISIBLE);
                            request_code.setVisibility(View.GONE);
                            refresh_code_block.setVisibility(View.VISIBLE);
                            code_input.requestFocus();
                        }
                    }
                    hideProgressDialog();
                }

                @Override
                public void onFailure(Call<RegisterUserResponse> call, Throwable t) {
                    Toast.makeText(SigninActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    hideProgressDialog();
                }
            });
        }
    };

    private void showProgressDialog(String message){
        progress=new ProgressDialog(this);
        progress.setMessage(message);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.setProgress(0);
        progress.show();
    }

    private void hideProgressDialog(){
        progress.hide();
    }
}

