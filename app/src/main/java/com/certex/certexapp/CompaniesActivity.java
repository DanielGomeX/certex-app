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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.certex.certexapp.model.CEP;
import com.certex.certexapp.model.SimpleCallback;
import com.certex.certexapp.service.Alert;
import com.certex.certexapp.service.CEPService;

public class CompaniesActivity extends AppCompatActivity {

    private EditText etSocialName;
    private EditText etFantasyName;
    private EditText etCpnj;
    private EditText etStateRegistration;
    private EditText etCep;
    private EditText etState;
    private EditText etCity;
    private EditText etAddress;
    private EditText etNeighborhood;
    private EditText etComplement;
    private Button btSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies);

        etSocialName = (EditText) findViewById(R.id.et_social_name);
        etFantasyName = (EditText) findViewById(R.id.et_fantasy_name);
        etCpnj = (EditText) findViewById(R.id.et_cpnj);
        etStateRegistration = (EditText) findViewById(R.id.et_state_registration);
        etCep = (EditText) findViewById(R.id.et_cep);
        etState = (EditText) findViewById(R.id.et_states);
        etCity = (EditText) findViewById(R.id.et_city);
        etAddress = (EditText) findViewById(R.id.et_address);
        etNeighborhood = (EditText) findViewById(R.id.et_neighborhood);
        etComplement = (EditText) findViewById(R.id.et_complement);
        btSignature = (Button) findViewById(R.id.bt_signature);

        etState.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (!etCep.getText().toString().isEmpty()) {
                        CEPService service = new CEPService(CompaniesActivity.this);

                        service.getCEP(etCep.getText().toString(), new SimpleCallback<CEP>() {

                            @Override
                            public void onResponse(CEP response) {
                                CEP cep = response;
                                String[] txt = cep.toString().split(";");
                                etState.setText(txt[0]);
                                etCity.setText(txt[1]);
                                etState.setEnabled(false);
                                etAddress.setEnabled(true);
                                etNeighborhood.setEnabled(true);
                                etComplement.setEnabled(true);
                                etAddress.requestFocus();
                            }

                            @Override
                            public void onError(String error) {
                                alert("CEP Não Encontrado!", true);
                                etState.setEnabled(true);
                                etAddress.setEnabled(false);
                                etNeighborhood.setEnabled(false);
                                etComplement.setEnabled(false);
                                etCep.setText("");
                                etCep.requestFocus();
                            }
                        });

                    } else {
                        alert("Favor Preencher o CEP", true);
                        etState.setEnabled(true);
                        etAddress.setEnabled(false);
                        etNeighborhood.setEnabled(false);
                        etComplement.setEnabled(false);
                        etCep.setText("");
                        etCep.requestFocus();
                    }
                }
                return false;
            }
        });

        etCep.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!etCep.getText().toString().isEmpty()) {
                        CEPService service = new CEPService(CompaniesActivity.this);

                        service.getCEP(etCep.getText().toString(), new SimpleCallback<CEP>() {

                            @Override
                            public void onResponse(CEP response) {
                                CEP cep = response;
                                String[] txt = cep.toString().split(";");
                                etState.setText(txt[0]);
                                etCity.setText(txt[1]);
                                etState.setEnabled(false);
                                etAddress.setEnabled(true);
                                etNeighborhood.setEnabled(true);
                                etComplement.setEnabled(true);
                                etAddress.requestFocus();
                            }

                            @Override
                            public void onError(String error) {
                                alert("CEP Não Encontrado!", true);
                                etState.setEnabled(true);
                                etAddress.setEnabled(false);
                                etNeighborhood.setEnabled(false);
                                etComplement.setEnabled(false);
                                etCep.setText("");
                                etCep.requestFocus();
                            }
                        });

                    } else {
                        alert("Favor Preencher o CEP", true);
                        etState.setEnabled(true);
                        etAddress.setEnabled(false);
                        etNeighborhood.setEnabled(false);
                        etComplement.setEnabled(false);
                        etCep.setText("");
                        etCep.requestFocus();
                    }
                    return true;
                }
                return false;
            }
        });

        btSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompaniesActivity.this, SignatureActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(CompaniesActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bt_main_save:
                if (!etState.getText().toString().isEmpty() && !etNeighborhood.getText().toString().isEmpty() && !etStateRegistration.getText().toString().isEmpty() &&
                        !etCpnj.getText().toString().isEmpty() && !etFantasyName.getText().toString().isEmpty() && !etSocialName.getText().toString().isEmpty() && !etAddress.getText().toString().isEmpty() &&
                        !etCep.getText().toString().isEmpty() && !etCity.getText().toString().isEmpty()) {
                    alert("SALVO COM SUCESSO!", false);
                    onBackPressed();
                    return true;
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

