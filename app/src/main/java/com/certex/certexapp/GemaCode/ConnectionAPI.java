package com.certex.certexapp.GemaCode;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class ConnectionAPI {

    public void Post(final String[] keys, final String[] value, final String table, final String variable, Activity activity) {
        final RequestQueue MyRequestQueue = Volley.newRequestQueue(activity);

        String url = "http://177.44.248.19/api/" + table;
        final StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    //JSONArray jarray = object.getJSONArray("access_token");
                    String output = object.getString(variable);
                    if (table.equals("login")) {
                        Token token = new Token();
                        token.setCode(output);
                        Session session = Session.getInstance();
                        session.setToken(token);
                    }

                    Log.i("Script", "SUCCESS: " + response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //alert("Error: " + error.getMessage());
                Log.i("Script", "ERROR: " + error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                for (int i = 0; i < keys.length; i++) {
                    MyData.put(keys[i], value[i]);
                }
//                MyData.put("email", "vitor@certex.com");
//                MyData.put("password", "123456");
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }
}