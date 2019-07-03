package com.certex.certexapp;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.certex.certexapp.service.CustomListView;

public class DashboardActivity extends AppCompatActivity {

    ListView list;

    String[] maintitle = {
            "Certificado 1", "Certificado 2",
            "Certificado 3", "Certificado 4",
            "Certificado 5",
    };

    String[] subtitle = {
            "Extintor 1", "Extintor 2",
            "Extintor 3", "Extintor 4",
            "Extintor 5",
    };

    Integer[] imgid = {
            R.drawable.icon, R.drawable.icon,
            R.drawable.icon, R.drawable.icon,
            R.drawable.icon,
    };

    //    private Button btRegisterManufacturers;
    private Button btCreateReport;
    private Button btCreateExtinguishers;
    private Button btShowReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().hide();

        CustomListView adapter = new CustomListView(this, maintitle, subtitle, imgid);
        list = (ListView) findViewById(R.id.lv_dashboard);
        list.setAdapter(adapter);

        btCreateReport = (Button) findViewById(R.id.bt_dashboard_create_report);
        btCreateExtinguishers = (Button) findViewById(R.id.bt_dashboard_create_extinguishers);
        btShowReport = (Button) findViewById(R.id.bt_dashboard_show_report);

        btCreateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, CompaniesActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(DashboardActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });

        btCreateExtinguishers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ExtinguishersActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(DashboardActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });

        btShowReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if (position == 0) {
                    //code specific to first list item
                    Toast.makeText(getApplicationContext(), "Place 1", Toast.LENGTH_SHORT).show();
                } else if (position == 1) {
                    //code specific to 2nd list item
                    Toast.makeText(getApplicationContext(), "Place 2", Toast.LENGTH_SHORT).show();
                } else if (position == 2) {

                    Toast.makeText(getApplicationContext(), "Place 3", Toast.LENGTH_SHORT).show();
                } else if (position == 3) {

                    Toast.makeText(getApplicationContext(), "Place 4", Toast.LENGTH_SHORT).show();
                } else if (position == 4) {

                    Toast.makeText(getApplicationContext(), "Place 5", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}