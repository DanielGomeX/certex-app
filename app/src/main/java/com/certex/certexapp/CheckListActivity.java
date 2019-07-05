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

        if(bundle.containsKey("id_extinguishers")){
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

                //if (checkedIdAlavanca != -1 || checkedIdAnel != -1 || checkedIdBico != -1 || checkedIdCarga != -1 || checkedIdDeso != -1 || checkedIdEtiqueta != -1 || checkedIdFixa != -1 || checkedIdFogo != -1 || checkedIdIdenti != -1 ||
                //      checkedIdLacre != -1 || checkedIdMangueira != -1 || checkedIdMano != -1 || checkedIdPintura != -1 || checkedIdPiso != -1 || checkedIdProtecao != -1 || checkedIdPunho != -1 || checkedIdRecarga != -1 || checkedIdReteste != -1 ||
                //       checkedIdSinaliza != -1) {
                saveCRUD();
                //} else {
                //     alert("Favor Selecione adequadamente!", true);
                // }


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
            certification_id = Integer.parseInt(id);

            Log.i("JSON de SOTRE", json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void saveCRUD() {
        int op = 0;

        RadioButton cb_conforme_bico = (RadioButton) findViewById(R.id.cb_conforme_bico);
        RadioButton cb_nconforme_bico = (RadioButton) findViewById(R.id.cb_nconforme_bico);
        RadioButton cb_na_bico = (RadioButton) findViewById(R.id.cb_na_bico);

        if (cb_conforme_bico.isSelected()) {
            op = 1;
        }
        if (cb_nconforme_bico.isSelected()) {
            op = 2;
        }
        if (cb_na_bico.isSelected()) {
            op = 3;
        }

        alert("OP = " + op, false);

//        String cnpJ = etCnpj.getText().toString();
//        String stateRegistration = etStateRegistration.getText().toString();
//        String socialName = etSocialName.getText().toString();
//        String fantasyName = etFantasyName.getText().toString();
//        String address = etAddress.getText().toString();
//        String cep = etCep.getText().toString();
//        String complement = etComplement.getText().toString();
//        String neighborhood = etNeighborhood.getText().toString();
//        String signature = baseSignature;
//        String state = etState.getText().toString();
//        String city = etCity.getText().toString();
//
//        try {
//            JSONObject data = new JSONObject();
//            data.put("cnpj", cnpJ);
//            data.put("state_registration", stateRegistration);
//            data.put("social_name", socialName);
//            data.put("fantasy_name", fantasyName);
//            data.put("address", address);
//            data.put("cep", cep);
//            data.put("complement", complement);
//            data.put("neighborhood", neighborhood);
//            data.put("signature", signature);
//            data.put("state", state);
//            data.put("city", city);
//
//            Log.i("JSON DATA", data.toString());
//
//            JSONObject json = ConnectionAPI.makePost(ConnectionAPI.TABLE_COMPANY, ConnectionAPI.ACTION_STORE, null, data);
//
//            Log.i("JSON de SOTRE", json.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

}
