package com.certex.certexapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.certex.certexapp.GemaCode.ConnectionAPI;
import com.certex.certexapp.service.Alert;

import org.json.JSONObject;


public class ExtinguishersListActivity extends AppCompatActivity {

    private ListView listRoute;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_extinguishers);

        setTitle("Extintores Cadastrados");

        Intent it = getIntent();

        listRoute = (ListView) findViewById(R.id.lw_list_extinguishers);

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

        JSONObject jsonCount = ConnectionAPI.makeGet(null, null, ConnectionAPI.TABLE_EXTINGUISHER, ConnectionAPI.ACTION_COUNT);

        Log.i("JSON teste", jsonCount.toString());

        String[] data;

        try {
            int count = jsonCount.getJSONObject("data").getInt("count");
            if (count > 0){
                data = new String[count];
                String[] fixed = { count+"", "1" };
                JSONObject json = ConnectionAPI.makeGet(fixed, null, ConnectionAPI.TABLE_EXTINGUISHER, ConnectionAPI.ACTION_INDEX);
                Log.i("JSON data", json.toString());
            } else {
                data = new String[1];
                data[0] = "Nenhum dado encontrado";
            }
        }catch (Exception e){
            e.printStackTrace();
            data = new String[1];
            data[0] = "Nenhum dado encontrado";
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listRoute.setAdapter(adapter);

    }
}

