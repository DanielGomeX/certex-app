package com.certex.certexapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.certex.certexapp.GemaCode.ConnectionAPI;
import com.certex.certexapp.GemaCode.SettingStrings;
import com.certex.certexapp.service.Alert;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class CompaniesActivity extends AppCompatActivity {

    private EditText etSocialName;
    private EditText etFantasyName;
    private EditText etCnpj;
    private EditText etStateRegistration;
    private EditText etCep;
    private EditText etState;
    private EditText etCity;
    private EditText etAddress;
    private EditText etNeighborhood;
    private EditText etComplement;
    private Button btSignature;
    private Button btSearchCep;
    private ImageView ivSignature;
    private String baseSignature;

    private boolean isSingup = false;
    private int idCompany = 0;
    private boolean error = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies);

        etSocialName = (EditText) findViewById(R.id.et_social_name_company);
        etFantasyName = (EditText) findViewById(R.id.et_fantasy_company);
        etCnpj = (EditText) findViewById(R.id.et_cnpj_company);
        etStateRegistration = (EditText) findViewById(R.id.et_state_registration_company);
        etCep = (EditText) findViewById(R.id.et_cep_company);
        etState = (EditText) findViewById(R.id.et_state_company);
        etCity = (EditText) findViewById(R.id.et_city_company);
        etAddress = (EditText) findViewById(R.id.et_address_company);
        etNeighborhood = (EditText) findViewById(R.id.et_neighborhood_company);
        etComplement = (EditText) findViewById(R.id.et_complement_company);
        btSignature = (Button) findViewById(R.id.bt_signature_company);
        btSearchCep = (Button) findViewById(R.id.bt_search_cep_company);
        ivSignature = (ImageView) findViewById(R.id.iv_signature_company);

        setTitle("Dados da Empresa");

        Intent it = getIntent();
        etCnpj.setText(it.getStringExtra("a"));
        etStateRegistration.setText(it.getStringExtra("b"));
        etSocialName.setText(it.getStringExtra("c"));
        etFantasyName.setText(it.getStringExtra("d"));
        etAddress.setText(it.getStringExtra("e"));
        etCep.setText(it.getStringExtra("f"));
        etComplement.setText(it.getStringExtra("g"));
        etNeighborhood.setText(it.getStringExtra("h"));
        etState.setText(it.getStringExtra("i"));
        etCity.setText(it.getStringExtra("j"));

        if (it.hasExtra("singup")){
            this.isSingup = true;
        }

        if (it.hasExtra("id_company")){
            this.idCompany = it.getExtras().getInt("id_company");
        }


        File imgFile = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "Signature.jpg");

        if (imgFile.exists()) {
            ivSignature.setVisibility(View.VISIBLE);
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ivSignature.setImageBitmap(myBitmap);
        } else {
            ivSignature.setVisibility(View.INVISIBLE);
        }

        btSearchCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etCep.getText().toString().isEmpty()) {
                    alert("Favor Preencha CEP Valido", true);
                } else {
                    searchCep();
                    etAddress.setEnabled(true);
                    etNeighborhood.setEnabled(true);
                    etComplement.setEnabled(true);
                }
            }
        });

//        etCnpj.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_NEXT) {
//                    if (!etCnpj.getText().toString().isEmpty()) {
//                        return false;
//                    } else {
//                        alert("CNPJ EMPTY", true);
//                    }
//                }
//                return true;
//            }
//        });
//
//        etStateRegistration.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (!etCnpj.getText().toString().isEmpty()) {
//                    return false
//                } else {
//                    alert("CNPJ EMPTY", true);
//                }
//                return true;
//            }
//        });

//        etStateRegistration.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_NEXT) {
//                    alert("next IE", true);
//                    return false;
//                }
//                return true;
//            }
//        });

        btSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompaniesActivity.this, SignatureActivity.class);
                intent.putExtra("a", etCnpj.getText().toString());
                intent.putExtra("b", etStateRegistration.getText().toString());
                intent.putExtra("c", etSocialName.getText().toString());
                intent.putExtra("d", etFantasyName.getText().toString());
                intent.putExtra("e", etAddress.getText().toString());
                intent.putExtra("f", etCep.getText().toString());
                intent.putExtra("g", etComplement.getText().toString());
                intent.putExtra("h", etNeighborhood.getText().toString());
                intent.putExtra("i", etState.getText().toString());
                intent.putExtra("j", etCity.getText().toString());
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(CompaniesActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bt_main_save:

                if (!etCnpj.getText().toString().isEmpty() && /*!etState.getText().toString().isEmpty() &&*/ !etNeighborhood.getText().toString().isEmpty() && !etStateRegistration.getText().toString().isEmpty() &&
                        !etFantasyName.getText().toString().isEmpty() && !etSocialName.getText().toString().isEmpty() && !etAddress.getText().toString().isEmpty() &&
                        !etCep.getText().toString().isEmpty() /*&& !etCity.getText().toString().isEmpty()*/) {
                    File imgFile = new File(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS), "Signature.jpg");
                    if (imgFile.exists()) {
                        baseSignature = encodeFileToBase64Binary(imgFile);

                        saveCRUD();

                        File fileExt = new File(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_DOWNLOADS), "base.txt");

                        //Cria o arquivo txt para teste
                        fileExt.getParentFile().mkdirs();
                        FileOutputStream fosExt = null;
                        try {
                            fosExt = new FileOutputStream(fileExt);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            fosExt.write(encodeFileToBase64Binary(imgFile).getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            fosExt.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //Log.i("Base64", encodeFileToBase64Binary(imgFile));
                        if (error) {
                            alert("ERRO AO SALVAR!", false);
                        } else {
                            alert("SALVO COM SUCESSO!", false);
                            if (isSingup) {
                                Intent intent = new Intent(CompaniesActivity.this, UserActivity.class); //TESTE NECESSÁRIO CRIAR A ACTIVITY DASHBOARD
                                Bundle bundle = new Bundle();
                                bundle.putInt("id_company", idCompany);
                                intent.putExtras(bundle);
                                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                                ActivityCompat.startActivity(CompaniesActivity.this, intent, activityOptionsCompat.toBundle());
                                finish();
                            } else {
                                Intent intent = new Intent(CompaniesActivity.this, DashboardActivity.class); //TESTE NECESSÁRIO CRIAR A ACTIVITY DASHBOARD
                                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                                ActivityCompat.startActivity(CompaniesActivity.this, intent, activityOptionsCompat.toBundle());
                            }
                        }
                        return true;
                    } else {
                        alert("Favor Crie uma Assinatura!", true);
                    }
                } else {
                    alert("Favor Preencher os dados Corretamente!", true);
                }
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private static String encodeFileToBase64Binary(File file) {
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedfile;
    }

    private void searchCep() {
        String cep = etCep.getText().toString();
        String[] parametersFixed = {cep};
        try {
            JSONObject json = ConnectionAPI.makeGet(parametersFixed, null, ConnectionAPI.TABLE_CEP, null);
            Log.i("JOSN CEP", json.toString());
            etCity.setText(SettingStrings.removeAccentuation(json.getJSONObject("data").getString("localidade")).toUpperCase());
            etState.setText(SettingStrings.removeAccentuation(json.getJSONObject("data").getString("uf")).toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
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

    private void isNew() {

    }

    private void isEdit() {

    }

    private void saveCRUD() {
        String cnpJ = etCnpj.getText().toString();
        String stateRegistration = etStateRegistration.getText().toString();
        String socialName = etSocialName.getText().toString();
        String fantasyName = etFantasyName.getText().toString();
        String address = etAddress.getText().toString();
        String cep = etCep.getText().toString();
        String complement = etComplement.getText().toString();
        String neighborhood = etNeighborhood.getText().toString();
        String signature = baseSignature;
        String state = etState.getText().toString();
        String city = etCity.getText().toString();

        try {
            JSONObject data = new JSONObject();
            data.put("cnpj", cnpJ);
            data.put("state_registration", stateRegistration);
            data.put("social_name", socialName);
            data.put("fantasy_name", fantasyName);
            data.put("address", address);
            data.put("cep", cep);
            data.put("complement", complement);
            data.put("neighborhood", neighborhood);
            data.put("signature", signature);
            data.put("state", state);
            data.put("city", city);

            Log.i("JSON DATA", data.toString());

            JSONObject json;
            if (idCompany == 0) {
                json = ConnectionAPI.makePost(ConnectionAPI.TABLE_COMPANY, ConnectionAPI.ACTION_STORE, null, data);
                this.idCompany = json.getJSONObject("data").getJSONObject("company").getInt("id");
            } else {
                json = ConnectionAPI.makePost(ConnectionAPI.TABLE_COMPANY, ConnectionAPI.ACTION_UPDATE, ""+idCompany, data);
            }
            this.error = json.has("error");
            Log.i("JSON de SOTRE QUERO ID", json.getJSONObject("data").getJSONObject("company").getInt("id") + "");
        } catch (Exception e) {
            this.error = true;
            e.printStackTrace();
        }

    }
}

