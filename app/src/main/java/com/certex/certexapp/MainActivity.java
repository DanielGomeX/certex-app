package com.certex.certexapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
        etPassword.setText("123456");


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = etUsername.getText().toString().trim();
                String passwordText = etPassword.getText().toString().trim();

                if (usernameText.isEmpty() || passwordText.isEmpty()) {
                    alert("Favor preencher todos os campos", true);
                } else {
                    String[] keys = {"email", "password"};
                    String[] values = {usernameText, passwordText};
                    ConnectionAPI api = new ConnectionAPI();
                    api.Post(keys, values, "login", "access_token", MainActivity.this);
                    String token = Session.getInstance().getToken() + "";
                    alert(token, false);

                    if (Session.getInstance().getToken() != null) { //TESTE
                        Intent intent = new Intent(MainActivity.this, UserActivity.class);
                        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                        ActivityCompat.startActivity(MainActivity.this, intent, activityOptionsCompat.toBundle());
                        alert(token, false);
                    } else {
                        alert("Usuário e/ou Senha Incorretos(s)!", true);
                    }
                }
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


    private void alert(String msg, boolean error) {
        new Alert().show(msg, error, getLayoutInflater(), getApplicationContext(), this.findViewById(android.R.id.content));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.move_left, R.anim.fade_out);
    }
}

