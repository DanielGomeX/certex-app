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


public class ManufacturersActivity extends AppCompatActivity {

    private EditText etDescription;
    private EditText etCep;
    private EditText etCity;
    private EditText etState;
    private Button btSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturers);

         etDescription = (EditText) findViewById(R.id.et_extinguishers_code);
         etCep = (EditText) findViewById(R.id.et_extinguishers_number);
         etCity = (EditText) findViewById(R.id.et_extinguishers_capacity);
         etState = (EditText) findViewById(R.id.et_extinguishers_charge);
         btSave = (Button) findViewById(R.id.bt_manufacturers_save);


        setTitle("Cadastro de Extintor");

        Intent it = getIntent();


        //Field description ======= START
        //Verify field empty
        etDescription.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!etDescription.getText().toString().isEmpty()) {
                        return false;
                    } else {
                        alert("Descrição vazio", true);
                    }
                }
                return true;
            }
        });

        //Next field
        etDescription.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    alert("Próximo: CEP", true);
                    return false;
                }
                return true;
            }
        });
        //Field description ======= FINISH


        //Field CEP ======= START
        //Verify field empty
        etCep.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!etCep.getText().toString().isEmpty()) {
                        return false;
                    } else {
                        alert("CEP vazio", true);
                    }
                }
                return true;
            }
        });

        //Next field
//        etCep.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_NEXT) {
//                    alert("Próximo: capacidade do extintor", true);
//                    return false;
//                }
//                return true;
//            }
//        });
        //Field CEP ======= FINISH


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

