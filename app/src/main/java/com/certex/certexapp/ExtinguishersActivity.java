package com.certex.certexapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.certex.certexapp.service.Alert;


public class ExtinguishersActivity extends AppCompatActivity {

    private EditText etCode;
    private EditText etNumber;
    private EditText etCapacity;
    private EditText etCharge;
    private EditText etChargeDate;
    private EditText etValidateDate;
    private EditText etLocation;
    private EditText etManufacturers;
    private Button btManufacturers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extinguishers);

        etCode = (EditText) findViewById(R.id.et_extinguishers_code4);
        etNumber = (EditText) findViewById(R.id.et_number_extinguishers);
        etCapacity = (EditText) findViewById(R.id.et_capacity_extinguishers);
        etCharge = (EditText) findViewById(R.id.et_charge_extinguishers);
        etChargeDate = (EditText) findViewById(R.id.et_charge_date_extinguishers);
        etValidateDate = (EditText) findViewById(R.id.et_validate_extinguishers);
        etLocation = (EditText) findViewById(R.id.et_location_extinguishers);
        etManufacturers = (EditText) findViewById(R.id.et_manufacturers_extinguishers);
        btManufacturers = (Button) findViewById(R.id.bt_manufacturers_extinguishers);

        setTitle("Cadastro de Extintor");

        etCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!etCode.getText().toString().isEmpty()) {
                        return false;
                    } else {
                        alert("Código de barras vazio", true);
                    }
                }
                return true;
            }
        });

        //Next field
        etCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    alert("Próximo: número do extintor", true);
                    return false;
                }
                return true;
            }
        });

        etNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!etNumber.getText().toString().isEmpty()) {
                        return false;
                    } else {
                        alert("Número do extintor vazio", true);
                    }
                }
                return true;
            }
        });

        etNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    alert("Próximo: capacidade do extintor", true);
                    return false;
                }
                return true;
            }
        });

        etCapacity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!etCapacity.getText().toString().isEmpty()) {
                        return false;
                    } else {
                        alert("Capacidade do extintor vazia", true);
                    }
                }
                return true;
            }
        });

        etCapacity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    alert("Próximo: recarga do extintor", true);
                    return false;
                }
                return true;
            }
        });

        etCharge.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!etCharge.getText().toString().isEmpty()) {
                        return false;
                    } else {
                        alert("Recarga do extintor vazia", true);
                    }
                }
                return true;
            }
        });

        //Next field
        etCharge.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    alert("Próximo: data da recarga", true);
                    return false;
                }
                return true;
            }
        });

        etChargeDate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!etChargeDate.getText().toString().isEmpty()) {
                        return false;
                    } else {
                        alert("Data da recarga do extintor vazia", true);
                    }
                }
                return true;
            }
        });

        etChargeDate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    alert("Próximo: validade do extintor", true);
                    return false;
                }
                return true;
            }
        });

        etValidateDate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!etValidateDate.getText().toString().isEmpty()) {
                        return false;
                    } else {
                        alert("Validade do extintor vazia", true);
                    }
                }
                return true;
            }
        });

        etValidateDate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    alert("Próximo: localização", true);
                    return false;
                }
                return true;
            }
        });

        etManufacturers.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!etManufacturers.getText().toString().isEmpty()) {
                        return false;
                    } else {
                        alert("Fornecedora do extintor vazia", true);
                    }
                }
                return true;
            }
        });

        //Next field
        etManufacturers.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    alert("Próximo: status", true);
                    return false;
                }
                return true;
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bt_main_save:

                if (!etCode.getText().toString().isEmpty() && !etNumber.getText().toString().isEmpty() && !etCapacity.getText().toString().isEmpty() && !etCharge.getText().toString().isEmpty() &&
                        !etChargeDate.getText().toString().isEmpty() && !etValidateDate.getText().toString().isEmpty() && !etManufacturers.getText().toString().isEmpty()) {

                    alert("SALVO COM SUCESSO!", false);

                    Intent intent = new Intent(ExtinguishersActivity.this, DashboardActivity.class);
                    ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                    ActivityCompat.startActivity(ExtinguishersActivity.this, intent, activityOptionsCompat.toBundle());
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

