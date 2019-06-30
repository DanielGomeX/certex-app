package com.certex.certexapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.certex.certexapp.service.Alert;

import org.apache.commons.codec.binary.Base64;

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
    private ImageView ivSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies);

        etSocialName = (EditText) findViewById(R.id.et_extinguishers_capacity);
        etFantasyName = (EditText) findViewById(R.id.et_extinguishers_charge);
        etCnpj = (EditText) findViewById(R.id.et_extinguishers_code);
        etStateRegistration = (EditText) findViewById(R.id.et_extinguishers_number);
        etCep = (EditText) findViewById(R.id.et_extinguishers_charge_date);
        etState = (EditText) findViewById(R.id.et_extinguishers_validate);
        etCity = (EditText) findViewById(R.id.et_companies_city);
        etAddress = (EditText) findViewById(R.id.et_extinguishers_manufacturers);
        etNeighborhood = (EditText) findViewById(R.id.et_companies_neighborhood);
        etComplement = (EditText) findViewById(R.id.et_companies_complement);
        btSignature = (Button) findViewById(R.id.bt_companies_signature);
        ivSignature = (ImageView) findViewById(R.id.iv_companies_signature);

        setTitle("Dados da Empresa");

        Intent it = getIntent();
        etCnpj.setText(it.getStringExtra("cnpj"));
        etStateRegistration.setText(it.getStringExtra("ie"));

        File imgFile = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "Signature.jpg");

        if (imgFile.exists()) {
            ivSignature.setVisibility(View.VISIBLE);
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ivSignature.setImageBitmap(myBitmap);
        } else {
            ivSignature.setVisibility(View.INVISIBLE);
        }

        etCnpj.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!etCnpj.getText().toString().isEmpty()) {
                        return false;
                    } else {
                        alert("CNPJ EMPTY", true);
                    }
                }
                return true;
            }
        });

        etStateRegistration.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!etCnpj.getText().toString().isEmpty()) {
                    return false;
                } else {
                    alert("CNPJ EMPTY", true);
                }
                return true;
            }
        });

        etStateRegistration.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    alert("next IE", true);
                    return false;
                }
                return true;
            }
        });

        btSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompaniesActivity.this, SignatureActivity.class);
                intent.putExtra("cnpj", etCnpj.getText().toString());
                intent.putExtra("ie", etStateRegistration.getText().toString());
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(CompaniesActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bt_main_save:

                if (!etCnpj.getText().toString().isEmpty() //&& !etState.getText().toString().isEmpty() && !etNeighborhood.getText().toString().isEmpty() && !etStateRegistration.getText().toString().isEmpty() &&
                    // !etFantasyName.getText().toString().isEmpty() && !etSocialName.getText().toString().isEmpty() && !etAddress.getText().toString().isEmpty() &&
                    // !etCep.getText().toString().isEmpty() && !etCity.getText().toString().isEmpty()) {
                ) {
                    File imgFile = new File(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS), "Signature.jpg");
                    if (imgFile.exists()) {

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

                        alert("SALVO COM SUCESSO!", false);

                        Intent intent = new Intent(CompaniesActivity.this, MainActivity.class); //TESTE NECESSÁRIO CRIAR A ACTIVITY DASHBOARD
                        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                        ActivityCompat.startActivity(CompaniesActivity.this, intent, activityOptionsCompat.toBundle());
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

    private void isNew(){

    }

    private void isEdit(){

    }

    private void saveCRUD (){

    }
}

