package com.certex.certexapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.certex.certexapp.GemaCode.ConnectionAPI;
import com.certex.certexapp.service.Alert;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.TreeMap;


public class CertificationsListActivity extends AppCompatActivity {

    private ListView listCertifications;
    private ArrayAdapter<String> adapter;

    TreeMap<String, Integer> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_certifications);

        setTitle("Certificações Cadastradas");

        Intent it = getIntent();

        info = new TreeMap();

        listCertifications = (ListView) findViewById(R.id.lw_list_extinguishers);

        listCertifications.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(CertificationsListActivity.this, ExtinguishersActivity.class);

                //Get value String of variable listRoute
                Object obj = parent.getItemAtPosition(position);
                String idObj = "" + info.get(obj);
                Log.i("Item selecionado", "" + obj);
                Log.i("Item selecionado ID", "" + info.get(obj));

                //Message or feedback to user
                Toast.makeText(CertificationsListActivity.this, "Carregando dados para edição", Toast.LENGTH_SHORT).show();

                //Preparations of value, to send tha next screen
                Bundle bundle = new Bundle();
                bundle.putString("id_certifications", idObj);

                //Send parameters and start the screen
//                intent.putExtras(bundle);
//                startActivity(intent);
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

        JSONObject jsonCount = ConnectionAPI.makeGet(null, null, ConnectionAPI.TABLE_CERTIFICATION, ConnectionAPI.ACTION_COUNT);

        Log.i("JSON teste", jsonCount.toString());

        String[] data;

        try {
            int count = jsonCount.getJSONObject("data").getInt("count");
            if (count > 0) {
                data = new String[count];
                String[] fixed = {"0", count + ""};
                JSONObject json = ConnectionAPI.makeGet(null, null, ConnectionAPI.TABLE_CERTIFICATION, ConnectionAPI.ACTION_ALL);
                Log.i("JSON data", json.getString("data"));
                JSONArray arrayJson = json.getJSONObject("data").getJSONArray("certifications");
                for (int i = 0; i < arrayJson.length(); i++) {
                    String temp = "Certificado # Número: " + arrayJson.getJSONObject(i).getString("report_code");
                    info.put(temp, arrayJson.getJSONObject(i).getInt("id"));
                    data[i] = temp;
                    Log.i("JSON array", temp);
                }
            } else {
                data = new String[1];
                data[0] = "Nenhum dado encontrado";
                listCertifications.setEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            data = new String[1];
            data[0] = "Nenhum dado encontrado";
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listCertifications.setAdapter(adapter);

    }
}

