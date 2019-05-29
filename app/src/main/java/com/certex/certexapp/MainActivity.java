package com.certex.certexapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.certex.certexapp.GemaCode.ConnectionAPI;
import com.certex.certexapp.service.Alert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btLogin;
    private EditText etUsername;
    private EditText etPassword;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        btLogin = (Button) findViewById(R.id.btn_login);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        tv = (TextView) findViewById(R.id.textView);


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, UserActivity.class);
//                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
//                ActivityCompat.startActivity(MainActivity.this, intent, activityOptionsCompat.toBundle());
//                //startActivity(intent); //TESTE TROCA DE TELA

                String usernameText = etUsername.getText().toString().trim();
                String passwordText = etPassword.getText().toString().trim();

                if (usernameText.isEmpty() || passwordText.isEmpty()){
                    alert("Favor preencher todos os campos", true);
                } else {
                    String[] keys = {"email", "password"};
                    String[] values = {usernameText, passwordText};
                    String reply = ConnectionAPI.apiPOST(keys, values, ConnectionAPI.LOGIN).get("access_token");
                    tv.setText(reply);
                }
            }
        });
    }

//    public void Conn() {
//        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
//
//        String url = "http://177.44.248.19/api/login";
//        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                JSONArray array;
//                try {
//                    JSONObject object = new JSONObject(response);
//                    //JSONArray jarray = object.getJSONArray("access_token");
//                    String token = object.getString("access_token");
//
//                    Log.i("Script", "SUCCESS: " + response);
//                    tv.setText(token);
//                    Log.i("Script", "SUCCESS: " + token);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                alert("Error: " + error.getMessage(), true);
//            }
//        }) {
//            protected Map<String, String> getParams() {
//                Map<String, String> MyData = new HashMap<String, String>();
//                MyData.put("email", "vitor@certex.com");
//                MyData.put("password", "123456");
//                return MyData;
//            }
//        };
//
//        MyRequestQueue.add(MyStringRequest);
//    }

    private void alert(String msg, boolean error) {
        new Alert().show(msg, error, getLayoutInflater(), getApplicationContext(), this.findViewById(android.R.id.content));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.move_left, R.anim.fade_out);
    }
}

