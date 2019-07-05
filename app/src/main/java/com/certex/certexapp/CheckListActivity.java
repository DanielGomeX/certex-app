package com.certex.certexapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.certex.certexapp.GemaCode.ConnectionAPI;
import com.certex.certexapp.GemaCode.Session;
import com.certex.certexapp.service.Alert;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckListActivity extends AppCompatActivity {

    private int idExtinguisher;

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

    private int certification_id = 0;

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

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle.containsKey("id_extinguishers")) {
            idExtinguisher = bundle.getInt("id_extinguishers");
        }

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

                if (checkedIdAlavanca != -1 || checkedIdAnel != -1 || checkedIdBico != -1 || checkedIdCarga != -1 || checkedIdDeso != -1 || checkedIdEtiqueta != -1 || checkedIdFixa != -1 || checkedIdFogo != -1 || checkedIdIdenti != -1 ||
                        checkedIdLacre != -1 || checkedIdMangueira != -1 || checkedIdMano != -1 || checkedIdPintura != -1 || checkedIdPiso != -1 || checkedIdProtecao != -1 || checkedIdPunho != -1 || checkedIdRecarga != -1 || checkedIdReteste != -1 ||
                        checkedIdSinaliza != -1) {
                    createReport();
                    for (int i = 0; i < 19; i++) {
                        saveCRUD(i);
                    }
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

        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
        Date fdata = new Date();
        String dataFormatada = formataData.format(fdata);
        String report_code = dataFormatada;
        String users_id = Session.getInstance().getToken().getUserID();

        try {
            JSONObject data = new JSONObject();
            data.put("report_code", report_code);
            data.put("users_id", users_id);
            data.put("signature", "Minha Assinatura");


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

        RadioButton cb_conforme_identif = (RadioButton) findViewById(R.id.cb_conforme_identif);
        RadioButton cb_nconforme_identif = (RadioButton) findViewById(R.id.cb_nconforme_identif);
        RadioButton cb_na_identif = (RadioButton) findViewById(R.id.cb_na_identif);
        RadioButton cb_conforme_carga = (RadioButton) findViewById(R.id.cb_conforme_carga);
        RadioButton cb_nconforme_carga = (RadioButton) findViewById(R.id.cb_nconforme_carga);
        RadioButton cb_na_carga = (RadioButton) findViewById(R.id.cb_na_carga);
        RadioButton cb_conforme_reteste = (RadioButton) findViewById(R.id.cb_conforme_reteste);
        RadioButton cb_nconforme_reteste = (RadioButton) findViewById(R.id.cb_nconforme_reteste);
        RadioButton cb_na_reteste = (RadioButton) findViewById(R.id.cb_na_reteste);
        RadioButton cb_conforme_pintura = (RadioButton) findViewById(R.id.cb_conforme_pintura);
        RadioButton cb_nconforme_pintura = (RadioButton) findViewById(R.id.cb_nconforme_pintura);
        RadioButton cb_na_pintura = (RadioButton) findViewById(R.id.cb_na_pintura);
        RadioButton cb_conforme_lacre = (RadioButton) findViewById(R.id.cb_conforme_lacre);
        RadioButton cb_nconforme_lacre = (RadioButton) findViewById(R.id.cb_nconforme_lacre);
        RadioButton cb_na_lacre = (RadioButton) findViewById(R.id.cb_na_lacre);
        RadioButton cb_conforme_mano = (RadioButton) findViewById(R.id.cb_conforme_mano);
        RadioButton cb_nconforme_mano = (RadioButton) findViewById(R.id.cb_nconforme_mano);
        RadioButton cb_na_mano = (RadioButton) findViewById(R.id.cb_na_mano);
        RadioButton cb_conforme_bico = (RadioButton) findViewById(R.id.cb_conforme_bico);
        RadioButton cb_nconforme_bico = (RadioButton) findViewById(R.id.cb_nconforme_bico);
        RadioButton cb_na_bico = (RadioButton) findViewById(R.id.cb_na_bico);
        RadioButton cb_conforme_mangueira = (RadioButton) findViewById(R.id.cb_conforme_mangueira);
        RadioButton cb_nconforme_mangueira = (RadioButton) findViewById(R.id.cb_nconforme_mangueira);
        RadioButton cb_na_mangueira = (RadioButton) findViewById(R.id.cb_na_mangueira);
        RadioButton cb_conforme_punho = (RadioButton) findViewById(R.id.cb_conforme_punho);
        RadioButton cb_nconforme_punho = (RadioButton) findViewById(R.id.cb_nconforme_punho);
        RadioButton cb_na_punho = (RadioButton) findViewById(R.id.cb_na_punho);
        RadioButton cb_conforme_recarga = (RadioButton) findViewById(R.id.cb_conforme_recarga);
        RadioButton cb_nconforme_recarga = (RadioButton) findViewById(R.id.cb_nconforme_recarga);
        RadioButton cb_na_recarga = (RadioButton) findViewById(R.id.cb_na_recarga);
        RadioButton cb_conforme_etiqueta = (RadioButton) findViewById(R.id.cb_conforme_etiqueta);
        RadioButton cb_nconforme_etiqueta = (RadioButton) findViewById(R.id.cb_nconforme_etiqueta);
        RadioButton cb_na_etiqueta = (RadioButton) findViewById(R.id.cb_na_etiqueta);
        RadioButton cb_conforme_alavanca = (RadioButton) findViewById(R.id.cb_conforme_alavanca);
        RadioButton cb_nconforme_alavanca = (RadioButton) findViewById(R.id.cb_nconforme_alavanca);
        RadioButton cb_na_alavanca = (RadioButton) findViewById(R.id.cb_na_alavanca);
        RadioButton cb_conforme_anel = (RadioButton) findViewById(R.id.cb_conforme_anel);
        RadioButton cb_nconforme_anel = (RadioButton) findViewById(R.id.cb_nconforme_anel);
        RadioButton cb_na_anel = (RadioButton) findViewById(R.id.cb_na_anel);
        RadioButton cb_conforme_piso = (RadioButton) findViewById(R.id.cb_conforme_piso);
        RadioButton cb_nconforme_piso = (RadioButton) findViewById(R.id.cb_nconforme_piso);
        RadioButton cb_na_piso = (RadioButton) findViewById(R.id.cb_na_piso);
        RadioButton cb_conforme_sinaliza = (RadioButton) findViewById(R.id.cb_conforme_sinaliza);
        RadioButton cb_nconforme_sinaliza = (RadioButton) findViewById(R.id.cb_nconforme_sinaliza);
        RadioButton cb_na_sinaliza = (RadioButton) findViewById(R.id.cb_na_sinaliza);
        RadioButton cb_conforme_desobstruido = (RadioButton) findViewById(R.id.cb_conforme_desobstruido);
        RadioButton cb_nconforme_desobstruido = (RadioButton) findViewById(R.id.cb_nconforme_desobstruido);
        RadioButton cb_na_desobstruido = (RadioButton) findViewById(R.id.cb_na_desobstruido);
        RadioButton cb_conforme_protecao = (RadioButton) findViewById(R.id.cb_conforme_protecao);
        RadioButton cb_nconforme_protecao = (RadioButton) findViewById(R.id.cb_nconforme_protecao);
        RadioButton cb_na_protecao = (RadioButton) findViewById(R.id.cb_na_protecao);
        RadioButton cb_conforme_fix = (RadioButton) findViewById(R.id.cb_conforme_fix);
        RadioButton cb_nconforme_fix = (RadioButton) findViewById(R.id.cb_nconforme_fix);
        RadioButton cb_na_fix = (RadioButton) findViewById(R.id.cb_na_fix);
        RadioButton cb_conforme_fogo = (RadioButton) findViewById(R.id.cb_conforme_fogo);
        RadioButton cb_nconforme_fogo = (RadioButton) findViewById(R.id.cb_nconforme_fogo);
        RadioButton cb_na_fogo = (RadioButton) findViewById(R.id.cb_na_fogo);

        if (id == 0) {
            if (cb_conforme_identif.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_identif.isSelected()) {
                op = 2;
            }
            if (cb_na_identif.isSelected()) {
                op = 3;
            }
        } else if (id == 1) {
            if (cb_conforme_carga.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_carga.isSelected()) {
                op = 2;
            }
            if (cb_na_carga.isSelected()) {
                op = 3;
            }
        } else if (id == 2) {
            if (cb_conforme_reteste.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_reteste.isSelected()) {
                op = 2;
            }
            if (cb_na_reteste.isSelected()) {
                op = 3;
            }
        } else if (id == 3) {
            if (cb_conforme_pintura.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_pintura.isSelected()) {
                op = 2;
            }
            if (cb_na_pintura.isSelected()) {
                op = 3;
            }
        } else if (id == 4) {
            if (cb_conforme_lacre.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_lacre.isSelected()) {
                op = 2;
            }
            if (cb_na_lacre.isSelected()) {
                op = 3;
            }
        } else if (id == 5) {
            if (cb_conforme_mano.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_mano.isSelected()) {
                op = 2;
            }
            if (cb_na_mano.isSelected()) {
                op = 3;
            }
        } else if (id == 6) {
            if (cb_conforme_bico.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_bico.isSelected()) {
                op = 2;
            }
            if (cb_na_bico.isSelected()) {
                op = 3;
            }
        } else if (id == 7) {
            if (cb_conforme_mangueira.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_mangueira.isSelected()) {
                op = 2;
            }
            if (cb_na_mangueira.isSelected()) {
                op = 3;
            }
        } else if (id == 8) {
            if (cb_conforme_punho.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_punho.isSelected()) {
                op = 2;
            }
            if (cb_na_punho.isSelected()) {
                op = 3;
            }
        } else if (id == 9) {
            if (cb_conforme_recarga.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_recarga.isSelected()) {
                op = 2;
            }
            if (cb_na_recarga.isSelected()) {
                op = 3;
            }
        } else if (id == 10) {
            if (cb_conforme_etiqueta.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_etiqueta.isSelected()) {
                op = 2;
            }
            if (cb_na_etiqueta.isSelected()) {
                op = 3;
            }
        } else if (id == 11) {
            if (cb_conforme_alavanca.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_alavanca.isSelected()) {
                op = 2;
            }
            if (cb_na_alavanca.isSelected()) {
                op = 3;
            }
        } else if (id == 12) {
            if (cb_conforme_anel.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_anel.isSelected()) {
                op = 2;
            }
            if (cb_na_anel.isSelected()) {
                op = 3;
            }
        } else if (id == 13) {
            if (cb_conforme_piso.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_piso.isSelected()) {
                op = 2;
            }
            if (cb_na_piso.isSelected()) {
                op = 3;
            }
        } else if (id == 14) {
            if (cb_conforme_fogo.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_fogo.isSelected()) {
                op = 2;
            }
            if (cb_na_fogo.isSelected()) {
                op = 3;
            }
        } else if (id == 15) {
            if (cb_conforme_sinaliza.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_sinaliza.isSelected()) {
                op = 2;
            }
            if (cb_na_sinaliza.isSelected()) {
                op = 3;
            }
        } else if (id == 16) {
            if (cb_conforme_desobstruido.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_desobstruido.isSelected()) {
                op = 2;
            }
            if (cb_na_desobstruido.isSelected()) {
                op = 3;
            }
        } else if (id == 17) {
            if (cb_conforme_protecao.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_protecao.isSelected()) {
                op = 2;
            }
            if (cb_na_protecao.isSelected()) {
                op = 3;
            }
        } else if (id == 18) {
            if (cb_conforme_fix.isSelected()) {
                op = 1;
            }
            if (cb_nconforme_fix.isSelected()) {
                op = 2;
            }
            if (cb_na_fix.isSelected()) {
                op = 3;
            }
        }

        int questionsId = id + 1;
        String description = "Teste";
        String photo = "Sem Foto";
        String active = "1";
        String alternatives_id = op + "";
        String extinguishers_id = idExtinguisher + "";
        String certifications_id = certification_id + "";
        String questions_id = questionsId + "";

        try {
            JSONObject data = new JSONObject();
            data.put("description", description);
            data.put("photo", photo);
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
