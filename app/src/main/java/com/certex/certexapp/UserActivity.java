package com.certex.certexapp;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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

        setTitle("Dados do Usuário");

        //tvTeste.setText(Session.getInstance().getToken().getCode());

        etName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    return !validateNameFormat(etName.getText().toString());
                }
                return true;
            }
        });


        etEmail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return !validateNameFormat(etName.getText().toString());
            }
        });

        etEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    return !validateEmailFormat(etEmail.getText().toString());
                }
                return true;
            }
        });

        etPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return !validateEmailFormat(etEmail.getText().toString());
            }
        });

        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    return !goCompany();
                }
                return true;
            }
        });
    }

    private boolean validateNameFormat(String name) {
        if (name.isEmpty()) {
            alert("Nome Inválido!", true);
            return false; //Nome Invalido
        }
        if (name.length() < 4 || name.length() > 50) {
            alert("Nome Inválido!", true);
            return false; //Nome Invalido
        }

        if (name.indexOf(" ") == -1) {
            alert("Nome Inválido!", true);
            return false; //Deve conter nome e sobrenome
        }

        if (name.substring(0, name.indexOf(" ")).length() < 2 || name.substring(name.lastIndexOf(" ") + 1).length() < 2) {
            alert("Nome Inválido!", true);
            return false; //O nome e o sobrenome deve contrer pelo menos 2 caracteres
        }

        String[] apart = name.split(" ");
        String[] fullName = new String[apart.length];
        name = "";
        for (int i = 0; i < apart.length; i++) {
            fullName[i] = apart[i].substring(0, 1).toUpperCase().concat(apart[i].substring(1));
            name += fullName[i] + " ";
        }
        etName.setText(name.substring(0, name.length() - 1));
        etEmail.requestFocus();

        return true;
    }

    private boolean validateEmailFormat(final String email) {
        if (!email.isEmpty()) {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                return true;
            } else {
                alert("E-mail Inválido!", true);
            }
        } else {
            alert("E-mail Inválido!", true);
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bt_main_save:
                goCompany();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean goCompany() {
        if (!etName.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty() && !etEmail.getText().toString().isEmpty()) {
            if (validateNameFormat(etName.getText().toString())) {
                if (validateEmailFormat(etEmail.getText().toString())) {
                    if (etPassword.getText().length() > 5) {
                        Intent intent = new Intent(UserActivity.this, CompaniesActivity.class);
                        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                        ActivityCompat.startActivity(UserActivity.this, intent, activityOptionsCompat.toBundle());
                        return true;
                    } else {
                        alert("A Senha deve conter no mínimo 6 caracteres.!", true);
                        etPassword.requestFocus();
                    }
                } else {
                    alert("E-mail Inválido!", true);
                    etEmail.requestFocus();
                }
            } else {
                alert("Nome Inválido!", true);
                etName.requestFocus();
            }
        } else {
            alert("Favor Preencher os dados Corretamente!", true);
        }
        etPassword.setText("");
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void alert(String msg, boolean error) {
        new Alert().show(msg, error, getLayoutInflater(), getApplicationContext(), this.findViewById(android.R.id.content));
    }

//    @Override
//    public void onBackPressed() {
//        this.moveTaskToBack(true); //Não volta para Login
//    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.move_left, R.anim.fade_out);
    }
}
