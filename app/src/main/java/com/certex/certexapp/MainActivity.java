package com.certex.certexapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.certex.certexapp.helpers.ConnectionAPI;
import com.certex.certexapp.service.Alert;

public class MainActivity extends AppCompatActivity {

    private Button btLogin ;
    private EditText etUsername ;
    private EditText etPassword ;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        getSupportActionBar().hide();

        btLogin = (Button) findViewById(R.id.btn_login);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        tv = (TextView) findViewById(R.id.textView);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(MainActivity.this, intent, activityOptionsCompat.toBundle());
                //startActivity(intent); //TESTE TROCA DE TELA

                String usernameText = etUsername.getText().toString().trim();
                String passwordText = etPassword.getText().toString().trim();

                if (usernameText.isEmpty() || passwordText.isEmpty()){
                    alert("Favor preencher todos os campos", true);
                } else {
                    String[] keys = {"email", "password"};
                    String[] values = {"anderson@certex.com", "123456"};

                    tv.setText( ConnectionAPI.api(keys, values, ConnectionAPI.LOGIN) );

                    ConnectionAPI.setToken("");

                }

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

