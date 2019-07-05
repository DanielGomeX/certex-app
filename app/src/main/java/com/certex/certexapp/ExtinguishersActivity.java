package com.certex.certexapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.certex.certexapp.GemaCode.ConnectionAPI;
import com.certex.certexapp.GemaCode.Session;
import com.certex.certexapp.GemaCode.SettingStrings;
import com.certex.certexapp.service.Alert;

import org.json.JSONObject;


public class ExtinguishersActivity extends AppCompatActivity {

    private int id = 0;
    private EditText etCode;
    private EditText etNumber;
    private EditText etCapacity;
    private EditText etCharge;
    private EditText etChargeDate;
    private EditText etValidateDate;
    private EditText etLocation;

    private CheckBox cbClass1;
    private CheckBox cbClass2;
    private CheckBox cbClass3;
    private CheckBox cbClass4;
    private CheckBox cbClass5;

    private boolean aux = false;
    private final Calendar myCalendar = Calendar.getInstance();

    private Spinner spManufacturers;
    List<String> manufacturers = new ArrayList<>();
    List<String> manufacturers_id = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extinguishers);

        etCode = (EditText) findViewById(R.id.et_tag_extinguishers);
        etNumber = (EditText) findViewById(R.id.et_number_extinguishers);
        etCapacity = (EditText) findViewById(R.id.et_capacity_extinguishers);
        etCharge = (EditText) findViewById(R.id.et_charge_extinguishers);
        etChargeDate = (EditText) findViewById(R.id.et_charge_date_extinguishers);
        etValidateDate = (EditText) findViewById(R.id.et_validate_extinguishers);
        etLocation = (EditText) findViewById(R.id.et_location_extinguishers);
        spManufacturers = (Spinner) findViewById(R.id.sp_manufacturers_extinguishers);

        cbClass1 = (CheckBox) findViewById(R.id.checkBox1);
        cbClass2 = (CheckBox) findViewById(R.id.checkBox2);
        cbClass3 = (CheckBox) findViewById(R.id.checkBox3);
        cbClass4 = (CheckBox) findViewById(R.id.checkBox4);
        cbClass5 = (CheckBox) findViewById(R.id.checkBox5);

        setTitle("Cadastro de Extintor");

        manufacturers.add("*Fornecedor");

        // Modo de edição
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if( bundle.containsKey("id_extinguishers") ){
            String[] fixed = {bundle.getString("id_extinguishers")};
            this.id = Integer.parseInt( bundle.getString("id_extinguishers") );
            JSONObject entityJson = ConnectionAPI.makeGet(fixed, null, ConnectionAPI.TABLE_EXTINGUISHER, ConnectionAPI.ACTION_SHOW);
            Log.i("ENTITY RETURN", entityJson.toString());
            try {
                etCode.setText( entityJson.getJSONObject("data").getJSONObject("extinguisher").getString("code") );
                etNumber.setText( entityJson.getJSONObject("data").getJSONObject("extinguisher").getString("numeration") );
                etCapacity.setText( entityJson.getJSONObject("data").getJSONObject("extinguisher").getString("capacity") );
                etCharge.setText( entityJson.getJSONObject("data").getJSONObject("extinguisher").getString("charge") );
                etChargeDate.setText( entityJson.getJSONObject("data").getJSONObject("extinguisher").getString("charge_date") );
                etValidateDate.setText( entityJson.getJSONObject("data").getJSONObject("extinguisher").getString("validate_date") );
                etLocation.setText( entityJson.getJSONObject("data").getJSONObject("extinguisher").getString("location") );
            } catch (Exception e){
                e.printStackTrace();
            }

        }


        int c = countManufactur() + 1;

        for (int i = 1; i < c; i++) {
            searchManufactur(i);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, manufacturers);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spManufacturers.setAdapter(dataAdapter);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        etChargeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aux = true;
                new DatePickerDialog(ExtinguishersActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        etValidateDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aux = false;
                new DatePickerDialog(ExtinguishersActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void searchManufactur(int id) {
        String param = id + "";
        String[] parametersFixed = {param, "show"};
        try {
            JSONObject json = ConnectionAPI.makeGet(parametersFixed, null, ConnectionAPI.TABLE_MANUFACTURER, null);
            Log.i("JOSN Manufacturer", json.toString());
            manufacturers.add((json.getJSONObject("data").getJSONObject("manufacturer").getString("name")).toUpperCase());
            manufacturers_id.add(json.getJSONObject("data").getJSONObject("manufacturer").getString("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int countManufactur() {
        String[] parametersFixed = {"count"};
        String x = "0";
        try {
            JSONObject json = ConnectionAPI.makeGet(parametersFixed, null, ConnectionAPI.TABLE_MANUFACTURER, null);
            Log.i("JOSN Manufacturer", json.toString());
            x = json.getJSONObject("data").getString("count");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.parseInt(x);
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt", "BR"));

        if (aux) {
            etChargeDate.setText(sdf.format(myCalendar.getTime()));
        } else {
            etValidateDate.setText(sdf.format(myCalendar.getTime()));
        }




    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bt_main_save:

                if (!etCode.getText().toString().isEmpty() && !etNumber.getText().toString().isEmpty() && !etCapacity.getText().toString().isEmpty() && !etCharge.getText().toString().isEmpty() &&
                        !etChargeDate.getText().toString().isEmpty() && !etValidateDate.getText().toString().isEmpty()) {
                    if (cbClass1.isChecked() || cbClass2.isChecked() || cbClass3.isChecked() || cbClass4.isChecked() || cbClass5.isChecked()) {
                        saveCRUD();

                        alert("SALVO COM SUCESSO!", false);

                        Intent intent = new Intent(ExtinguishersActivity.this, DashboardActivity.class);
                        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                        ActivityCompat.startActivity(ExtinguishersActivity.this, intent, activityOptionsCompat.toBundle());
                        return true;
                    } else {
                        alert("Favor Selecione a Classe!", true);
                    }
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

    private void saveCRUD() {
        int cb1, cb2, cb3, cb4, cb5 = 0;
        String value = "";

        if (cbClass1.isChecked()) {
            value = "1";
        }
        if (cbClass2.isChecked()) {
            if (value.equals("")) {
                value += "2";
            } else {
                value += ",2";
            }
        }
        if (cbClass3.isChecked()) {
            if (value.equals("")) {
                value += "3";
            } else {
                value += ",3";
            }
        }
        if (cbClass4.isChecked()) {
            if (value.equals("")) {
                value += "4";
            } else {
                value += ",4";
            }
        }
        if (cbClass5.isChecked()) {
            if (value.equals("")) {
                value += "5";
            } else {
                value += ",5";
            }
        }

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt", "BR"));

        String code = etCode.getText().toString();
        String number = etNumber.getText().toString();
        String capacity = etCapacity.getText().toString();
        String charge = etCharge.getText().toString();
        String chargeDate = etChargeDate.getText().toString();
        String validade = etValidateDate.getText().toString();
        String location = etLocation.getText().toString();
        String manufacturer = spManufacturers.getSelectedItemId() + "";
        String company = Session.getInstance().getToken().getCompanyID();

        try {
            JSONObject data = new JSONObject();
            data.put("code", code);
            data.put("numeration", number);
            data.put("capacity", capacity);
            data.put("charge", charge);
            data.put("charge_date", chargeDate);
            data.put("validade_date", validade);
            data.put("location", location);
            data.put("manufacturers_id", manufacturer);
            data.put("companies_id", company);
            data.put("extinguishers_types", "[" + value + "]");


            Log.i("JSON DATA", data.toString());

            JSONObject json = ConnectionAPI.makePost(ConnectionAPI.TABLE_EXTINGUISHERS, ConnectionAPI.ACTION_STORE, null, data);

            Log.i("JSON de SOTRE", json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

