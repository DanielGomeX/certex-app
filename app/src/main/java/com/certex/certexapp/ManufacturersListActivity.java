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


public class ManufacturersListActivity extends AppCompatActivity {

    private ListView listManufacturers;
    private ArrayAdapter<String> adapter;

    TreeMap<String, Integer> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_manufacturers);

        setTitle("Fornecedores Cadastrados");

        Intent it = getIntent();

        info = new TreeMap();

        listManufacturers = (ListView) findViewById(R.id.lw_list_extinguishers);

        listManufacturers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ManufacturersListActivity.this, ManufacturersActivity.class);

                //Get value String of variable listRoute
                Object obj = parent.getItemAtPosition(position);
                String idObj = "" + info.get(obj);
                Log.i("Item selecionado", ""+obj);
                Log.i("Item selecionado ID", "" + info.get(obj));

                //Message or feedback to user
                Toast.makeText(ManufacturersListActivity.this, "Carregando dados para edição", Toast.LENGTH_SHORT).show();

                //Preparations of value, to send tha next screen
                Bundle bundle = new Bundle();
                bundle.putString("id_manufacturers", idObj);

                //Send parameters and start the screen
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        listExtinguishers();



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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

    private void listExtinguishers(){

        JSONObject jsonCount = ConnectionAPI.makeGet(null, null, ConnectionAPI.TABLE_MANUFACTURER, ConnectionAPI.ACTION_COUNT);

        Log.i("JSON teste", jsonCount.toString());

        String[] data;

        try {
            int count = jsonCount.getJSONObject("data").getInt("count");
            if (count > 0){
                data = new String[count];
                String[] fixed = { "0", count+"" };
                JSONObject json = ConnectionAPI.makeGet(null, null, ConnectionAPI.TABLE_MANUFACTURER, ConnectionAPI.ACTION_ALL);
                Log.i("JSON data", json.getString("data"));
                JSONArray arrayJson = json.getJSONObject("data").getJSONArray("manufacturers");
                for (int i = 0; i < arrayJson.length(); i++){
                    String temp = "Fornecedores # Nome: " + arrayJson.getJSONObject(i).getString("name");
                    info.put(temp, arrayJson.getJSONObject(i).getInt("id"));
                    data[i] = temp;
                    Log.i("JSON array", temp);
                }
            } else {
                data = new String[1];
                data[0] = "Nenhum dado encontrado";
                listManufacturers.setEnabled(false);
            }
        }catch (Exception e){
            e.printStackTrace();
            data = new String[1];
            data[0] = "Nenhum dado encontrado";
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listManufacturers.setAdapter(adapter);

    }
}

