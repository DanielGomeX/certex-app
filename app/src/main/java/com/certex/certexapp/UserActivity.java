package com.certex.certexapp;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.certex.certexapp.GemaCode.Session;
import com.certex.certexapp.service.Alert;

public class UserActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etPassword;
    private EditText etEmail;
    private TextView tvTeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        etName = (EditText) findViewById(R.id.et_user_name);
        etPassword = (EditText) findViewById(R.id.et_user_password);
        etEmail = (EditText) findViewById(R.id.et_user_email);
        tvTeste = (TextView) findViewById(R.id.tv_teste);

        tvTeste.setText(Session.getInstance().getToken().getCode());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bt_main_save:
                if (!etName.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty() && !etEmail.getText().toString().isEmpty()) {
                    if (etPassword.getText().length() > 5) {
                        alert("SALVO COM SUCESSO!", false);
                        Intent intent = new Intent(UserActivity.this, CompaniesActivity.class);
                        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                        ActivityCompat.startActivity(UserActivity.this, intent, activityOptionsCompat.toBundle());
                        return true;
                    } else {
                        alert("A Senha deve conter no mínimo 6 caracteres.!", true);
                    }
                } else {
                    alert("Favor Preencher os dados Corretamente!", true);
                }
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
