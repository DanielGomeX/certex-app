package com.certex.certexapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
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
    private EditText etStatusExtinguishers;
    private Button btStatusExtinguishers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extinguishers);

         etCode = (EditText) findViewById(R.id.et_extinguishers_code);
         etNumber = (EditText) findViewById(R.id.et_extinguishers_number);
         etCapacity = (EditText) findViewById(R.id.et_extinguishers_capacity);
         etCharge = (EditText) findViewById(R.id.et_extinguishers_charge);
         etChargeDate = (EditText) findViewById(R.id.et_extinguishers_charge_date);
         etValidateDate = (EditText) findViewById(R.id.et_extinguishers_validate);
         etLocation = (EditText) findViewById(R.id.et_extinguishers_location);
         etManufacturers = (EditText) findViewById(R.id.et_extinguishers_manufacturers);
         btManufacturers = (Button) findViewById(R.id.bt_extinguishers_manufacturers);
         etStatusExtinguishers = (EditText) findViewById(R.id.et_extinguishers_status);
         btStatusExtinguishers = (Button) findViewById(R.id.bt_extinguishers_status);


        setTitle("Cadastro de Extintor");

        Intent it = getIntent();


        //Field code ======= START
        //Verify field empty
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
        //Field code ======= FINISH


        //Field number ======= START
        //Verify field empty
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

        //Next field
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
        //Field number ======= FINISH


        //Field capacity ======= START
        //Verify field empty
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

        //Next field
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
        //Field capacity ======= FINISH


        //Field charge ======= START
        //Verify field empty
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
        //Field charge ======= FINISH


        //Field date charge ======= START
        //Verify field empty
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

        //Next field
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
        //Field date charge ======= FINISH


        //Field validate ======= START
        //Verify field empty
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

        //Next field
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
        //Field validate ======= FINISH


        //Field manufacturers ======= START
        //Verify field empty
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
        //Field manufacturers ======= FINISH


        //Field status ======= START
        //Verify field empty
        etStatusExtinguishers.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!etStatusExtinguishers.getText().toString().isEmpty()) {
                        return false;
                    } else {
                        alert("Fornecedora do extintor vazia", true);
                    }
                }
                return true;
            }
        });

        //Next field
        etStatusExtinguishers.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    alert("Próximo: status", true);
                    return false;
                }
                return true;
            }
        });
        //Field status ======= FINISH

    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.bt_main_save:
//
//                if (!etCnpj.getText().toString().isEmpty() //&& !etState.getText().toString().isEmpty() && !etNeighborhood.getText().toString().isEmpty() && !etStateRegistration.getText().toString().isEmpty() &&
//                    // !etFantasyName.getText().toString().isEmpty() && !etSocialName.getText().toString().isEmpty() && !etAddress.getText().toString().isEmpty() &&
//                    // !etCep.getText().toString().isEmpty() && !etCity.getText().toString().isEmpty()) {
//                ) {
//                    File imgFile = new File(Environment.getExternalStoragePublicDirectory(
//                            Environment.DIRECTORY_DOWNLOADS), "Signature.jpg");
//                    if (imgFile.exists()) {
//
//                        File fileExt = new File(Environment.getExternalStoragePublicDirectory(
//                                Environment.DIRECTORY_DOWNLOADS), "base.txt");
//
//                        //Cria o arquivo txt para teste
//                        fileExt.getParentFile().mkdirs();
//                        FileOutputStream fosExt = null;
//                        try {
//                            fosExt = new FileOutputStream(fileExt);
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            fosExt.write(encodeFileToBase64Binary(imgFile).getBytes());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            fosExt.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        //Log.i("Base64", encodeFileToBase64Binary(imgFile));
//
//                        alert("SALVO COM SUCESSO!", false);
//
//                        Intent intent = new Intent(ExtinguishersActivity.this, MainActivity.class); //TESTE NECESSÁRIO CRIAR A ACTIVITY DASHBOARD
//                        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
//                        ActivityCompat.startActivity(ExtinguishersActivity.this, intent, activityOptionsCompat.toBundle());
//                        return true;
//                    } else {
//                        alert("Favor Crie uma Assinatura!", true);
//                    }
//                } else {
//                    alert("Favor Preencher os dados Corretamente!", true);
//                }
//                return false;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    private void alert(String msg, boolean error) {
        new Alert().show(msg, error, getLayoutInflater(), getApplicationContext(), this.findViewById(android.R.id.content));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.move_left, R.anim.fade_out);
    }
}

