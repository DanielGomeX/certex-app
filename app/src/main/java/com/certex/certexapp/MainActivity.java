package com.certex.certexapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.certex.certexapp.GemaCode.ConnectionAPI;
import com.certex.certexapp.GemaCode.Session;
import com.certex.certexapp.service.Alert;

public class MainActivity extends AppCompatActivity {

    private Button btLogin;
    private Button btSign;
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        btLogin = (Button) findViewById(R.id.btn_login);
        btSign = (Button) findViewById(R.id.btn_sign);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        etUsername.setText("jackson@certex.com");
        etPassword.setText("");

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                getToken();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getToken();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Action on click
            }
        });

        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    goLogin();
                    return true;
                }
                return false;
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLogin();
            }
        });

        btSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(MainActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });
    }

    private void goLogin() {
        String usernameText = etUsername.getText().toString().trim();
        String passwordText = etPassword.getText().toString().trim();

        if (usernameText.isEmpty() || passwordText.isEmpty()) {
            alert("Favor preencher todos os campos", true);
        } else {
            if (Session.getInstance().getToken() != null) {
                Intent intent = new Intent(MainActivity.this, CompaniesActivity.class); //TESTE NECESSÁRIO CRIAR A ACTIVITY DASHBOARD
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(MainActivity.this, intent, activityOptionsCompat.toBundle());
            } else {
                alert("Usuário e/ou Senha Incorretos(s)!", true);
            }
        }
    }

    private void getToken() {
        String usernameText = etUsername.getText().toString().trim();
        String passwordText = etPassword.getText().toString().trim();

        if (etPassword.getText().length() > 5) {
            String[] keys = {"email", "password"};
            String[] values = {usernameText, passwordText};
            ConnectionAPI api = new ConnectionAPI();
            Log.i("Script", "beforeTextChanged");
            api.Post(keys, values, "login", "access_token", MainActivity.this);
        }
    }

    private void alert(String msg, boolean error) {
        new Alert().show(msg, error, getLayoutInflater(), getApplicationContext(), this.findViewById(android.R.id.content));
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true); //Não volta
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.move_left, R.anim.fade_out);
    }
}

