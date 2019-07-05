package com.certex.certexapp;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.certex.certexapp.service.CustomListView;

public class DashboardActivity extends AppCompatActivity {


//    String[] maintitle = {
//            "Certificado 1", "Certificado 2",
//            "Certificado 3", "Certificado 4",
//            "Certificado 5",
//    };
//
//    String[] subtitle = {
//            "Extintor 1", "Extintor 2",
//            "Extintor 3", "Extintor 4",
//            "Extintor 5",
//    };
//
//    Integer[] imgid = {
//            R.drawable.icon, R.drawable.icon,
//            R.drawable.icon, R.drawable.icon,
//            R.drawable.icon,
//    };

    //    private Button btRegisterManufacturers;
    private Button btCreateReport;
    private Button btCreateExtinguishers;
    private Button btShowReport;

    private Button btManufacturersRegister;
    private Button btManufacturersList;
    private Button btExtinguishersList;

//    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().hide();

//        CustomListView adapter = new CustomListView(this, maintitle, subtitle, imgid);
//        list = (ListView) findViewById(R.id.lv_dashboard);
//        list.setAdapter(adapter);

        btCreateReport = (Button) findViewById(R.id.bt_dashboard_create_report);
        btCreateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ExtinguishersListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isCertification", true);
                intent.putExtras(bundle);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(DashboardActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });

        btCreateExtinguishers = (Button) findViewById(R.id.bt_dashboard_create_extinguishers);
        btCreateExtinguishers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ExtinguishersActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(DashboardActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });

        btShowReport = (Button) findViewById(R.id.bt_dashboard_show_report);
        btShowReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, CertificationsListActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(DashboardActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });

        btExtinguishersList = (Button) findViewById(R.id.bt_extinguishers_list);
        btExtinguishersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ExtinguishersListActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(DashboardActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });

        btManufacturersList = (Button) findViewById(R.id.bt_manufacturers_lister);
        btManufacturersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ManufacturersListActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(DashboardActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });

        btManufacturersRegister = (Button) findViewById(R.id.bt_manufacturers_register);
        btManufacturersRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ManufacturersActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(DashboardActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });
//
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // TODO Auto-generated method stub
//                if (position == 0) {
//                    //code specific to first list item
//                    Toast.makeText(getApplicationContext(), "Place 1", Toast.LENGTH_SHORT).show();
//                } else if (position == 1) {
//                    //code specific to 2nd list item
//                    Toast.makeText(getApplicationContext(), "Place 2", Toast.LENGTH_SHORT).show();
//                } else if (position == 2) {
//
//                    Toast.makeText(getApplicationContext(), "Place 3", Toast.LENGTH_SHORT).show();
//                } else if (position == 3) {
//
//                    Toast.makeText(getApplicationContext(), "Place 4", Toast.LENGTH_SHORT).show();
//                } else if (position == 4) {
//
//                    Toast.makeText(getApplicationContext(), "Place 5", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        ActivityOptionsCompat activityOptionsCompat;
        int id = item.getItemId();

        if (id == R.id.nav_extinguishers_lister) {
            Log.i("Clicou Aqui", "Aqui óoooo if");
            intent = new Intent(DashboardActivity.this, ExtinguishersListActivity.class);
            activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
            ActivityCompat.startActivity(DashboardActivity.this, intent, activityOptionsCompat.toBundle());

        } else if (id == R.id.nav_manufacturers_register) {
            Log.i("Clicou Aqui", "Aqui óoooo if");
            intent = new Intent(DashboardActivity.this, ManufacturersActivity.class);
            activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
            ActivityCompat.startActivity(DashboardActivity.this, intent, activityOptionsCompat.toBundle());

        } else if (id == R.id.nav_extinguishers_register) {
            Log.i("Clicou Aqui", "Aqui óoooo if");
            intent = new Intent(DashboardActivity.this, ExtinguishersActivity.class);
            activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
            ActivityCompat.startActivity(DashboardActivity.this, intent, activityOptionsCompat.toBundle());
        }

        return super.onOptionsItemSelected(item);
    }

}