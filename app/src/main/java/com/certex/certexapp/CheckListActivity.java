package com.certex.certexapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.certex.certexapp.GemaCode.ConnectionAPI;
import com.certex.certexapp.GemaCode.Session;
import com.certex.certexapp.service.Alert;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckListActivity extends AppCompatActivity {

    private RadioGroup rgIdentifica;
    private RadioGroup rgCarga;
    private RadioGroup rgReteste;
    private RadioGroup rgPintura;
    private RadioGroup rgLacre;
    private RadioGroup rgMano;
    private RadioGroup rgBico;
    private RadioGroup rgMangueira;
    private RadioGroup rgPunho;
    private RadioGroup rgRecarga;
    private RadioGroup rgEtiqueta;
    private RadioGroup rgAlavanca;
    private RadioGroup rgAnel;
    private RadioGroup rgPiso;
    private RadioGroup rgSinaliza;
    private RadioGroup rgDesobistru;
    private RadioGroup rgProtecao;
    private RadioGroup rgFixa;
    private RadioGroup rgFogo;

    private RadioButton cb_conforme_identif;
    private RadioButton cb_nconforme_identif;
    private RadioButton cb_na_identif;
    private RadioButton cb_conforme_carga;
    private RadioButton cb_nconforme_carga;
    private RadioButton cb_na_carga;
    private RadioButton cb_conforme_reteste;
    private RadioButton cb_nconforme_reteste;
    private RadioButton cb_na_reteste;
    private RadioButton cb_conforme_pintura;
    private RadioButton cb_nconforme_pintura;
    private RadioButton cb_na_pintura;
    private RadioButton cb_conforme_lacre;
    private RadioButton cb_nconforme_lacre;
    private RadioButton cb_na_lacre;
    private RadioButton cb_conforme_mano;
    private RadioButton cb_nconforme_mano;
    private RadioButton cb_na_mano;
    private RadioButton cb_conforme_bico;
    private RadioButton cb_nconforme_bico;
    private RadioButton cb_na_bico;
    private RadioButton cb_conforme_mangueira;
    private RadioButton cb_nconforme_mangueira;
    private RadioButton cb_na_mangueira;
    private RadioButton cb_conforme_punho;
    private RadioButton cb_nconforme_punho;
    private RadioButton cb_na_punho;
    private RadioButton cb_conforme_recarga;
    private RadioButton cb_nconforme_recarga;
    private RadioButton cb_na_recarga;
    private RadioButton cb_conforme_etiqueta;
    private RadioButton cb_nconforme_etiqueta;
    private RadioButton cb_na_etiqueta;
    private RadioButton cb_conforme_alavanca;
    private RadioButton cb_nconforme_alavanca;
    private RadioButton cb_na_alavanca;
    private RadioButton cb_conforme_anel;
    private RadioButton cb_nconforme_anel;
    private RadioButton cb_na_anel;
    private RadioButton cb_conforme_piso;
    private RadioButton cb_nconforme_piso;
    private RadioButton cb_na_piso;
    private RadioButton cb_conforme_sinaliza;
    private RadioButton cb_nconforme_sinaliza;
    private RadioButton cb_na_sinaliza;
    private RadioButton cb_conforme_desobstruido;
    private RadioButton cb_nconforme_desobstruido;
    private RadioButton cb_na_desobstruido;
    private RadioButton cb_conforme_protecao;
    private RadioButton cb_nconforme_protecao;
    private RadioButton cb_na_protecao;
    private RadioButton cb_conforme_fix;
    private RadioButton cb_nconforme_fix;
    private RadioButton cb_na_fix;
    private RadioButton cb_conforme_fogo;
    private RadioButton cb_nconforme_fogo;
    private RadioButton cb_na_fogo;

    private EditText etDescription;

    private Button btPhoto;
    private ImageView imageViewFoto;

    private int certification_id;
    private int idExtinguisher;
    private String baseImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        setTitle("Check List");

        rgIdentifica = (RadioGroup) findViewById(R.id.rg_identif);
        rgCarga = (RadioGroup) findViewById(R.id.rg_carga);
        rgReteste = (RadioGroup) findViewById(R.id.rg_reteste);
        rgPintura = (RadioGroup) findViewById(R.id.rg_pintura);
        rgLacre = (RadioGroup) findViewById(R.id.rg_lacre);
        rgMano = (RadioGroup) findViewById(R.id.rg_mano);
        rgBico = (RadioGroup) findViewById(R.id.rg_bico);
        rgMangueira = (RadioGroup) findViewById(R.id.rg_mangueira);
        rgPunho = (RadioGroup) findViewById(R.id.rg_punho);
        rgRecarga = (RadioGroup) findViewById(R.id.rg_recarga);
        rgEtiqueta = (RadioGroup) findViewById(R.id.rg_etiqueta);
        rgAlavanca = (RadioGroup) findViewById(R.id.rg_alavanca);
        rgAnel = (RadioGroup) findViewById(R.id.rg_anel);
        rgPiso = (RadioGroup) findViewById(R.id.rg_piso);
        rgSinaliza = (RadioGroup) findViewById(R.id.rg_sinaliza);
        rgDesobistru = (RadioGroup) findViewById(R.id.rg_desobstruido);
        rgProtecao = (RadioGroup) findViewById(R.id.rg_protecao);
        rgFixa = (RadioGroup) findViewById(R.id.rg_fix);
        rgFogo = (RadioGroup) findViewById(R.id.rg_fogo);

        cb_conforme_identif = (RadioButton) findViewById(R.id.cb_conforme_identif);
        cb_nconforme_identif = (RadioButton) findViewById(R.id.cb_nconforme_identif);
        cb_na_identif = (RadioButton) findViewById(R.id.cb_na_identif);
        cb_conforme_carga = (RadioButton) findViewById(R.id.cb_conforme_carga);
        cb_nconforme_carga = (RadioButton) findViewById(R.id.cb_nconforme_carga);
        cb_na_carga = (RadioButton) findViewById(R.id.cb_na_carga);
        cb_conforme_reteste = (RadioButton) findViewById(R.id.cb_conforme_reteste);
        cb_nconforme_reteste = (RadioButton) findViewById(R.id.cb_nconforme_reteste);
        cb_na_reteste = (RadioButton) findViewById(R.id.cb_na_reteste);
        cb_conforme_pintura = (RadioButton) findViewById(R.id.cb_conforme_pintura);
        cb_nconforme_pintura = (RadioButton) findViewById(R.id.cb_nconforme_pintura);
        cb_na_pintura = (RadioButton) findViewById(R.id.cb_na_pintura);
        cb_conforme_lacre = (RadioButton) findViewById(R.id.cb_conforme_lacre);
        cb_nconforme_lacre = (RadioButton) findViewById(R.id.cb_nconforme_lacre);
        cb_na_lacre = (RadioButton) findViewById(R.id.cb_na_lacre);
        cb_conforme_mano = (RadioButton) findViewById(R.id.cb_conforme_mano);
        cb_nconforme_mano = (RadioButton) findViewById(R.id.cb_nconforme_mano);
        cb_na_mano = (RadioButton) findViewById(R.id.cb_na_mano);
        cb_conforme_bico = (RadioButton) findViewById(R.id.cb_conforme_bico);
        cb_nconforme_bico = (RadioButton) findViewById(R.id.cb_nconforme_bico);
        cb_na_bico = (RadioButton) findViewById(R.id.cb_na_bico);
        cb_conforme_mangueira = (RadioButton) findViewById(R.id.cb_conforme_mangueira);
        cb_nconforme_mangueira = (RadioButton) findViewById(R.id.cb_nconforme_mangueira);
        cb_na_mangueira = (RadioButton) findViewById(R.id.cb_na_mangueira);
        cb_conforme_punho = (RadioButton) findViewById(R.id.cb_conforme_punho);
        cb_nconforme_punho = (RadioButton) findViewById(R.id.cb_nconforme_punho);
        cb_na_punho = (RadioButton) findViewById(R.id.cb_na_punho);
        cb_conforme_recarga = (RadioButton) findViewById(R.id.cb_conforme_recarga);
        cb_nconforme_recarga = (RadioButton) findViewById(R.id.cb_nconforme_recarga);
        cb_na_recarga = (RadioButton) findViewById(R.id.cb_na_recarga);
        cb_conforme_etiqueta = (RadioButton) findViewById(R.id.cb_conforme_etiqueta);
        cb_nconforme_etiqueta = (RadioButton) findViewById(R.id.cb_nconforme_etiqueta);
        cb_na_etiqueta = (RadioButton) findViewById(R.id.cb_na_etiqueta);
        cb_conforme_alavanca = (RadioButton) findViewById(R.id.cb_conforme_alavanca);
        cb_nconforme_alavanca = (RadioButton) findViewById(R.id.cb_nconforme_alavanca);
        cb_na_alavanca = (RadioButton) findViewById(R.id.cb_na_alavanca);
        cb_conforme_anel = (RadioButton) findViewById(R.id.cb_conforme_anel);
        cb_nconforme_anel = (RadioButton) findViewById(R.id.cb_nconforme_anel);
        cb_na_anel = (RadioButton) findViewById(R.id.cb_na_anel);
        cb_conforme_piso = (RadioButton) findViewById(R.id.cb_conforme_piso);
        cb_nconforme_piso = (RadioButton) findViewById(R.id.cb_nconforme_piso);
        cb_na_piso = (RadioButton) findViewById(R.id.cb_na_piso);
        cb_conforme_sinaliza = (RadioButton) findViewById(R.id.cb_conforme_sinaliza);
        cb_nconforme_sinaliza = (RadioButton) findViewById(R.id.cb_nconforme_sinaliza);
        cb_na_sinaliza = (RadioButton) findViewById(R.id.cb_na_sinaliza);
        cb_conforme_desobstruido = (RadioButton) findViewById(R.id.cb_conforme_desobstruido);
        cb_nconforme_desobstruido = (RadioButton) findViewById(R.id.cb_nconforme_desobstruido);
        cb_na_desobstruido = (RadioButton) findViewById(R.id.cb_na_desobstruido);
        cb_conforme_protecao = (RadioButton) findViewById(R.id.cb_conforme_protecao);
        cb_nconforme_protecao = (RadioButton) findViewById(R.id.cb_nconforme_protecao);
        cb_na_protecao = (RadioButton) findViewById(R.id.cb_na_protecao);
        cb_conforme_fix = (RadioButton) findViewById(R.id.cb_conforme_fix);
        cb_nconforme_fix = (RadioButton) findViewById(R.id.cb_nconforme_fix);
        cb_na_fix = (RadioButton) findViewById(R.id.cb_na_fix);
        cb_conforme_fogo = (RadioButton) findViewById(R.id.cb_conforme_fogo);
        cb_nconforme_fogo = (RadioButton) findViewById(R.id.cb_nconforme_fogo);
        cb_na_fogo = (RadioButton) findViewById(R.id.cb_na_fogo);

        etDescription = (EditText) findViewById(R.id.et_desc_checklist);

        btPhoto = (Button) findViewById(R.id.bt_photo_checklist);
        imageViewFoto = (ImageView) findViewById(R.id.iv_photo_checklist);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle.containsKey("id_extinguishers")) {
            String x = bundle.getString("id_extinguishers");
            idExtinguisher = Integer.parseInt(x);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }

        btPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPhoto();
            }
        });
    }

    public void checkPhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    public static String convert(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap images = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(images);

            baseImage = convert(images);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void generateReport(int certificationId, int extinguisherId) {
        String param1 = certificationId + "";
        String param2 = extinguisherId + "";
        String[] parametersFixed = {param1, param2, "generate"};
        try {
            String json = ConnectionAPI.makeReport(parametersFixed, null, ConnectionAPI.TABLE_CERTIFICATIONS, null) + "";
            Log.i("JOSN Link: ", json);
            shareButton(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void shareButton(String link) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "certex-app");
        sendIntent.putExtra(Intent.EXTRA_TEXT, link);
        startActivity(Intent.createChooser(sendIntent, "Share via"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bt_main_save:

                int checkedIdAlavanca = rgAlavanca.getCheckedRadioButtonId();
                int checkedIdAnel = rgAnel.getCheckedRadioButtonId();
                int checkedIdBico = rgBico.getCheckedRadioButtonId();
                int checkedIdCarga = rgCarga.getCheckedRadioButtonId();
                int checkedIdDeso = rgDesobistru.getCheckedRadioButtonId();
                int checkedIdEtiqueta = rgEtiqueta.getCheckedRadioButtonId();
                int checkedIdFixa = rgFixa.getCheckedRadioButtonId();
                int checkedIdFogo = rgFogo.getCheckedRadioButtonId();
                int checkedIdIdenti = rgIdentifica.getCheckedRadioButtonId();
                int checkedIdLacre = rgLacre.getCheckedRadioButtonId();
                int checkedIdMangueira = rgMangueira.getCheckedRadioButtonId();
                int checkedIdMano = rgMano.getCheckedRadioButtonId();
                int checkedIdPintura = rgPintura.getCheckedRadioButtonId();
                int checkedIdPiso = rgPiso.getCheckedRadioButtonId();
                int checkedIdProtecao = rgProtecao.getCheckedRadioButtonId();
                int checkedIdPunho = rgPunho.getCheckedRadioButtonId();
                int checkedIdRecarga = rgRecarga.getCheckedRadioButtonId();
                int checkedIdReteste = rgReteste.getCheckedRadioButtonId();
                int checkedIdSinaliza = rgSinaliza.getCheckedRadioButtonId();

                if (checkedIdAlavanca != -1 && checkedIdAnel != -1 && checkedIdBico != -1 && checkedIdCarga != -1 && checkedIdDeso != -1 && checkedIdEtiqueta != -1 && checkedIdFixa != -1 && checkedIdFogo != -1 && checkedIdIdenti != -1 &&
                        checkedIdLacre != -1 && checkedIdMangueira != -1 && checkedIdMano != -1 && checkedIdPintura != -1 && checkedIdPiso != -1 && checkedIdProtecao != -1 && checkedIdPunho != -1 && checkedIdRecarga != -1 && checkedIdReteste != -1 &&
                        checkedIdSinaliza != -1) {
                    createReport();
                    for (int i = 0; i < 19; i++) {
                        saveCRUD(i);
                    }

                    alert("SALVO COM SUCESSO!", false);

                    Intent intent = new Intent(CheckListActivity.this, DashboardActivity.class); //TESTE NECESSÁRIO CRIAR A ACTIVITY DASHBOARD
                    ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                    ActivityCompat.startActivity(CheckListActivity.this, intent, activityOptionsCompat.toBundle());

                    generateReport(certification_id, idExtinguisher);
                } else {
                    alert("Favor Selecione adequadamente!", true);
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

    private void createReport() {

        SimpleDateFormat formataData = new SimpleDateFormat("HHmmss/yyyy");
        SimpleDateFormat formataData2 = new SimpleDateFormat("dd/MM/yyyy");
        Date fdata = new Date();
        String dataFormatada = formataData2.format(fdata);
        String report_code = formataData.format(fdata);
        String users_id = Session.getInstance().getToken().getUserID();

        try {
            JSONObject data = new JSONObject();
            data.put("report_code", report_code);
            data.put("users_id", users_id);
            data.put("signature", "Minha Assinatura");
            data.put("date", dataFormatada);
            data.put("extinguishers_id", idExtinguisher);


            Log.i("JSON DATA", data.toString());

            JSONObject json = ConnectionAPI.makePost(ConnectionAPI.TABLE_CERTIFICATIONS, ConnectionAPI.ACTION_STORE, null, data);
            String id = json.getJSONObject("data").getJSONObject("certification").getString("id");
            this.certification_id = Integer.parseInt(id);

            Log.i("JSON de SOTRE", json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void saveCRUD(int id) {
        int op = 0;

        if (id == 0) {
            if (cb_conforme_identif.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_identif.isChecked()) {
                op = 2;
            }
            if (cb_na_identif.isChecked()) {
                op = 3;
            }
        } else if (id == 1) {
            if (cb_conforme_carga.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_carga.isChecked()) {
                op = 2;
            }
            if (cb_na_carga.isChecked()) {
                op = 3;
            }
        } else if (id == 2) {
            if (cb_conforme_reteste.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_reteste.isChecked()) {
                op = 2;
            }
            if (cb_na_reteste.isChecked()) {
                op = 3;
            }
        } else if (id == 3) {
            if (cb_conforme_pintura.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_pintura.isChecked()) {
                op = 2;
            }
            if (cb_na_pintura.isChecked()) {
                op = 3;
            }
        } else if (id == 4) {
            if (cb_conforme_lacre.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_lacre.isChecked()) {
                op = 2;
            }
            if (cb_na_lacre.isChecked()) {
                op = 3;
            }
        } else if (id == 5) {
            if (cb_conforme_mano.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_mano.isChecked()) {
                op = 2;
            }
            if (cb_na_mano.isChecked()) {
                op = 3;
            }
        } else if (id == 6) {
            if (cb_conforme_bico.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_bico.isChecked()) {
                op = 2;
            }
            if (cb_na_bico.isChecked()) {
                op = 3;
            }
        } else if (id == 7) {
            if (cb_conforme_mangueira.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_mangueira.isChecked()) {
                op = 2;
            }
            if (cb_na_mangueira.isChecked()) {
                op = 3;
            }
        } else if (id == 8) {
            if (cb_conforme_punho.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_punho.isChecked()) {
                op = 2;
            }
            if (cb_na_punho.isChecked()) {
                op = 3;
            }
        } else if (id == 9) {
            if (cb_conforme_recarga.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_recarga.isChecked()) {
                op = 2;
            }
            if (cb_na_recarga.isChecked()) {
                op = 3;
            }
        } else if (id == 10) {
            if (cb_conforme_etiqueta.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_etiqueta.isChecked()) {
                op = 2;
            }
            if (cb_na_etiqueta.isChecked()) {
                op = 3;
            }
        } else if (id == 11) {
            if (cb_conforme_alavanca.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_alavanca.isChecked()) {
                op = 2;
            }
            if (cb_na_alavanca.isChecked()) {
                op = 3;
            }
        } else if (id == 12) {
            if (cb_conforme_anel.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_anel.isChecked()) {
                op = 2;
            }
            if (cb_na_anel.isChecked()) {
                op = 3;
            }
        } else if (id == 13) {
            if (cb_conforme_piso.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_piso.isChecked()) {
                op = 2;
            }
            if (cb_na_piso.isChecked()) {
                op = 3;
            }
        } else if (id == 14) {
            if (cb_conforme_fogo.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_fogo.isChecked()) {
                op = 2;
            }
            if (cb_na_fogo.isChecked()) {
                op = 3;
            }
        } else if (id == 15) {
            if (cb_conforme_sinaliza.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_sinaliza.isChecked()) {
                op = 2;
            }
            if (cb_na_sinaliza.isChecked()) {
                op = 3;
            }
        } else if (id == 16) {
            if (cb_conforme_desobstruido.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_desobstruido.isChecked()) {
                op = 2;
            }
            if (cb_na_desobstruido.isChecked()) {
                op = 3;
            }
        } else if (id == 17) {
            if (cb_conforme_protecao.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_protecao.isChecked()) {
                op = 2;
            }
            if (cb_na_protecao.isChecked()) {
                op = 3;
            }
        } else if (id == 18) {
            if (cb_conforme_fix.isChecked()) {
                op = 1;
            }
            if (cb_nconforme_fix.isChecked()) {
                op = 2;
            }
            if (cb_na_fix.isChecked()) {
                op = 3;
            }
        }

        int questionsId = id + 1;
        String description = etDescription.getText().toString();
        String photo = baseImage;
        String active = "1";
        String alternatives_id = op + "";
        String extinguishers_id = idExtinguisher + "";
        String certifications_id = certification_id + "";
        String questions_id = questionsId + "";

        try {
            JSONObject data = new JSONObject();
            data.put("description", description);
            if (id == 0) {
                data.put("photo", photo);
            } else {
                data.put("photo", "SEM FOTO");
            }
            data.put("active", active);
            data.put("alternatives_id", alternatives_id);
            data.put("extinguishers_id", extinguishers_id);
            data.put("certifications_id", certifications_id);
            data.put("questions_id", questions_id);

            Log.i("JSON DATA", data.toString());

            JSONObject json = ConnectionAPI.makePost(ConnectionAPI.TABLE_ANSWERS, ConnectionAPI.ACTION_STORE, null, data);

            Log.i("JSON de SOTRE", json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
