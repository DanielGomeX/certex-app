package com.certex.certexapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.certex.certexapp.GemaCode.ConnectionAPI;
import com.certex.certexapp.service.Alert;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.TreeMap;


public class ExtinguishersListActivity extends AppCompatActivity {

    private ListView listExtinguishers;
    private ArrayAdapter<String> adapter;
    private boolean certifications = false;

    TreeMap<String, Integer> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_extinguishers);

        setTitle("Extintores Cadastrados");

        Intent it = getIntent();
        Bundle bundle = it.getExtras();

        if (it.hasExtra("isCertification")) {
            certifications = bundle.getBoolean("isCertification");
            Log.i("BOOLEAN ########", "" + bundle.getBoolean("isCertification"));
        }

        info = new TreeMap();

        listExtinguishers = (ListView) findViewById(R.id.lw_list_extinguishers);

        listExtinguishers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent;

                if (certifications) {
                    intent = new Intent(ExtinguishersListActivity.this, CheckListActivity.class);
                } else {
                    intent = new Intent(ExtinguishersListActivity.this, ExtinguishersActivity.class);
                }

                //Get value String of variable listRoute
                Object obj = parent.getItemAtPosition(position);
                String idObj = "" + info.get(obj);
                Log.i("Item selecionado", "" + obj);
                Log.i("Item selecionado ID", "" + info.get(obj));

                //Message or feedback to user
                //Toast.makeText(ExtinguishersListActivity.this, "Carregando dados para edição", Toast.LENGTH_SHORT).show();

                //Preparations of value, to send tha next screen
                Bundle bundle = new Bundle();
                bundle.putString("id_extinguishers", idObj);

                //Send parameters and start the screen
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        listExtinguishers();


    }

    private void alert(String msg, boolean error) {
        new Alert().show(msg, error, getLayoutInflater(), getApplicationContext(), this.findViewById(android.R.id.content));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.move_left, R.anim.fade_out);
    }

    private void listExtinguishers() {

        JSONObject jsonCount = ConnectionAPI.makeGet(null, null, ConnectionAPI.TABLE_EXTINGUISHER, ConnectionAPI.ACTION_COUNT);

        Log.i("JSON teste", jsonCount.toString());

        String[] data;

        try {
            int count = jsonCount.getJSONObject("data").getInt("count");
            if (count > 0) {
                data = new String[count];
                String[] fixed = {"0", count + ""};
                JSONObject json = ConnectionAPI.makeGet(fixed, null, ConnectionAPI.TABLE_EXTINGUISHER, ConnectionAPI.ACTION_INDEX);
                Log.i("JSON data", json.getString("data"));
                JSONArray arrayJson = json.getJSONObject("data").getJSONArray("extinguishers");
                for (int i = 0; i < arrayJson.length(); i++) {
                    String temp = "Extintor # Código: " + arrayJson.getJSONObject(i).getString("code") + " / Número: " + arrayJson.getJSONObject(i).getString("numeration");
                    info.put(temp, arrayJson.getJSONObject(i).getInt("id"));
                    data[i] = temp;
                    Log.i("JSON array", temp);
                }
            } else {
                data = new String[1];
                data[0] = "Nenhum dado encontrado";
                listExtinguishers.setEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            data = new String[1];
            data[0] = "Nenhum dado encontrado";
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listExtinguishers.setAdapter(adapter);

    }
}

