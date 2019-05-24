package com.certex.certexapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.certex.certexapp.model.CEP;
import com.certex.certexapp.model.SimpleCallback;
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
                        etCep.requestFocus();
                    }
                }
                return false;
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
        if (error) {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast_erro,
                    (ViewGroup) findViewById(R.id.custom_toast_container));

            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText(msg);

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        } else {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast_ok,
                    (ViewGroup) findViewById(R.id.custom_toast_container));

            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText(msg);

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.move_left, R.anim.fade_out);
    }
}

