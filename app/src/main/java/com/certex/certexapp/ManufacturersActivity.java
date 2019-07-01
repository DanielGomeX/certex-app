package com.certex.certexapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.certex.certexapp.GemaCode.ConnectionAPI;
import com.certex.certexapp.service.Alert;

import org.json.JSONObject;


public class ManufacturersActivity extends AppCompatActivity {

    private int id;
    private EditText etName;
    private EditText etFone;
    private EditText etEmail;
    private EditText etDescription;
    private EditText etCep;
    private EditText etCity;
    private EditText etState;
    private Button btSave;
    private Button btSearchCep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturers);

        etName = (EditText) findViewById(R.id.et_name_manufacturers);
        etFone = (EditText) findViewById(R.id.et_fone_manufacturers);
        etEmail = (EditText) findViewById(R.id.et_email_manufacturers);
        etDescription = (EditText) findViewById(R.id.et_description_manufacturers);
        etCep = (EditText) findViewById(R.id.et_cep_manufacturers);
        etCity = (EditText) findViewById(R.id.et_city_manufacturers);
        etState = (EditText) findViewById(R.id.et_state_manufacturers);
        btSave = (Button) findViewById(R.id.bt_manufacturers_save);
        btSearchCep = (Button) findViewById(R.id.bt_search_cep);

        setTitle("Cadastro de Fornecedor");

        Intent it = getIntent();

        btSearchCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etCep.getText().toString().isEmpty()){
                    alert("Por favor preencha um cep valido", true);
                } else {
                    searchCep();
                }
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCRUD();
            }
        });

        //Field name ======= START
        //Verify field empty
        etName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!etName.getText().toString().isEmpty()) {
                        return false;
                    } else {
                        alert("Nome vazio", true);
                    }
                }
                return true;
            }
        });

        //Next field
        etName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    alert("Próximo: Telefone", true);
                    return false;
                }
                return true;
            }
        });
        //Field name ======= FINISH


        //Field fone ======= START
        //Verify field empty
        etFone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!etFone.getText().toString().isEmpty()) {
                        return false;
                    } else {
                        alert("Telefone vazio", true);
                    }
                }
                return true;
            }
        });

        //Next field
        etFone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    alert("Próximo: Email", true);
                    return false;
                }
                return true;
            }
        });
        //Field fone ======= FINISH


        //Field email ======= START
        //Verify field empty
        etEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!etEmail.getText().toString().isEmpty()) {
                        return false;
                    } else {
                        alert("Email vazio", true);
                    }
                }
                return true;
            }
        });

        //Next field
        etEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    alert("Próximo: Descrição", true);
                    return false;
                }
                return true;
            }
        });
        //Field email ======= FINISH


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

    private void isNew(){

    }

    private void isEdit(){

    }

    private void searchCep(){
        String cep = etCep.getText().toString();
        String[] parametersFixed = {cep};
        try {
            JSONObject json = ConnectionAPI.makeGet(parametersFixed, null, ConnectionAPI.TABLE_CEP, null);
            Log.i("JOSN CEP", json.toString());
            etCity.setText( json.getJSONObject("data").getString("localidade") );
            etState.setText( json.getJSONObject("data").getString("uf") );
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void saveCRUD (){
        String name = etName.getText().toString();
        String fone = etFone.getText().toString();
        String email = etEmail.getText().toString();
        String description = etDescription.getText().toString();
        String cep = etCep.getText().toString();
        String city = etCity.getText().toString();
        String state = etState.getText().toString();
        try {
            JSONObject data = new JSONObject();
            data.put("name", name);
            data.put("fone", fone);
            data.put("email", email);
            data.put("description", description);
            data.put("cep", cep);
            data.put("city", city);
            data.put("state", state);

            Log.i("JSON DATA", data.toString());

            JSONObject json = ConnectionAPI.makePost(ConnectionAPI.TABLE_MANUFACTURER, ConnectionAPI.ACTION_STORE, null, data);

            Log.i("JSON de SOTRE", json.toString());
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}

