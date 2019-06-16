package com.certex.certexapp.GemaCode;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ConnectionAPI {

    private static String url = "http://177.44.248.19/api/";


    public static void Post(final String[] keys, final String[] value, final String table, final String variable, Activity activity) {
        final RequestQueue MyRequestQueue = Volley.newRequestQueue(activity);

        String urlTemp = url + table;

        final StringRequest MyStringRequest = new StringRequest(Request.Method.POST, urlTemp, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    Log.i("Script", object.toString());
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
                Map<String, String> myData = new HashMap<String, String>();
                for (int i = 0; i < keys.length; i++) {
                    myData.put(keys[i], value[i]);
                }
//                MyData.put("email", "vitor@certex.com");
//                MyData.put("password", "123456");
                return myData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }


    public static HashMap<String, String> apiPOST(final String[] keys, final String[] value, final String[] keysInput, final String table) {
        final HashMap<String, String> map = new HashMap();


        String urlTemp = url + table;

        final StringRequest MyStringRequest = new StringRequest(Request.Method.POST, urlTemp, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);

                    for(int k = 0; k < keysInput.length; k++){
                        String temp = object.getString(keysInput[k]);
                        String key = keysInput[k];
                        String val = temp;
                        map.put(key, val);
                        Log.i("Script", "################################## JSON: " + key + "->" + val);
                    }
/*
                    Iterator<String> in = object.keys();

                    while (in.hasNext()){
                        String key = in.next();
                        String val = object.getString(key);
                        map.put(key, val);

                        Log.i("Script", "################################## JSON: " + key + "->" + val);
                    }
*/
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
                Map<String, String> myData = new HashMap<String, String>();
                for (int i = 0; i < keys.length; i++) {
                    myData.put(keys[i], value[i]);
                }
//                MyData.put("email", "vitor@certex.com");
//                MyData.put("password", "123456");
                return myData;
            }
        };

        return map;
    }

    public static void makePost(String[] keys, String[] value, String table) {
        URL tempUrl;
        HttpURLConnection connection = null;
        try {
            tempUrl = new URL(url + table);
            connection = (HttpURLConnection) tempUrl.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST"); // hear you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
            connection.connect();

            JSONObject json = new JSONObject();

            for(int i = 0; i < keys.length; i++){
                json.put(keys[i], value[i]);
            }

            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(json.toString());
            wr.flush();
            wr.close();

            InputStream is;
            int response = connection.getResponseCode();
            Log.d("RESPONSE::", String.valueOf(response));
            Log.d("RESPONSE::", String.valueOf(connection.getContent()));
//                if (response >= 200 && response <= 399) {
//                    //return is = connection.getInputStream();
//                    return true;
//                } else {
//                    //return is = connection.getErrorStream();
//                    return false;
//                }


        } catch (Exception e) {

            e.printStackTrace();
//                return false;

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }



    public static Map<String, String> apiGET(String[] keys, String[] values, String from) {

        String dataUrlTemp = url;
        HashMap<String, String> map = new HashMap();

        dataUrlTemp += from + "?token=" + Session.getInstance().getToken().getCode();

        if (keys.length == values.length) {
            int length = keys.length;

            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    dataUrlTemp += "&" + keys[i] + "=" + values[i];
                }
            }

            try {

                HttpClient httpclient = (HttpClient) new DefaultHttpClient();
                HttpGet httppost = new HttpGet(dataUrlTemp);
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
}