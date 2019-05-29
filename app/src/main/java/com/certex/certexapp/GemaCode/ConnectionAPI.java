package com.certex.certexapp.GemaCode;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConnectionAPI extends AppCompatActivity {

    private static String token;

    private static String dataUrl = "177.44.248.19/api/";

    public static final String LOGIN = "login";
    public static final String COMPANY = "company";

    public static Map<String, String> apiPOST(final String[] keys, final String[] values, String from) {
        String dataUrlTemp = dataUrl;
        dataUrlTemp += from;
        final HashMap<String, String> map = new HashMap();
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, dataUrlTemp, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray array;
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.length() > 0) {
                        while (object.keys().hasNext()) {
                            String s = object.keys().next();

                            map.put(s + "", object.get(s) + "");

                        }
                    } else {
                        map.put("erro", "No Info Find");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                alert("Error: " + error.getMessage(), true);
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap();

                for (int i = 0; i < keys.length; i++) {
                    data.put(keys[i], values[i]);
                }

                return data;
            }
        };

        return map;
    }

    public static Map<String, String> apiGET(String[] keys, String[] values, String from) {

        String dataUrlTemp = dataUrl;
        HashMap<String, String> map = new HashMap();

        dataUrlTemp += from + "?";
        dataUrlTemp += from + "?token=" + token;

        if (keys.length == values.length) {
            int length = keys.length;

            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    dataUrlTemp += "&" + keys[i] + "=" + values[i];
                }
            }

            try {

                HttpClient httpclient = (HttpClient) new DefaultHttpClient();
                HttpGet httppost = new HttpGet(dataUrl);
                HttpResponse response = (HttpResponse) httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();

                JSONObject object = new JSONObject(EntityUtils.toString(entity));
                if (object.length() > 0) {
                    while (object.keys().hasNext()) {
                        String s = object.keys().next();

                        map.put(s + "", object.get(s) + "");

                    }
                } else {
                    map.put("erro", "No Info Find");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        return solution;

        return map;
    }

    public static void setToken(String token) {
        ConnectionAPI.token = token;
    }


}