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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConnectionAPI {

    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void Post(final String[] keys, final String[] value, String table, final String variable, Activity activity) {
        final RequestQueue MyRequestQueue = Volley.newRequestQueue(activity);

        String url = "http://177.44.248.19/api/" + table;
        final StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //JSONArray array;
                try {
                    JSONObject object = new JSONObject(response);
                    //JSONArray jarray = object.getJSONArray("access_token");
                    String output = object.getString(variable);
                    Token token = new Token();
                    token.setCode(output);
                    Session session = Session.getInstance();
                    session.setToken(token);

                    Log.i("Script", "SUCCESS: " + response);
                    //tv.setText(result);
                    //Log.i("Script", "SUCCESS: " + token);
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
//    private static String token;
//
//    private static String dataUrl = "177.44.248.19/api/";
//
//    public static final String LOGIN = "login";
//    public static final String COMPANY = "company";
//
//    public static Map<String, String> apiPOST(final String[] keys, final String[] values, String from) {
//        String dataUrlTemp = dataUrl;
//        dataUrlTemp += from;
//        final HashMap<String, String> map = new HashMap();
//        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, dataUrlTemp, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                JSONArray array;
//                try {
//                    JSONObject object = new JSONObject(response);
//                    if (object.length() > 0) {
//                        while (object.keys().hasNext()) {
//                            String s = object.keys().next();
//
//                            map.put(s + "", object.get(s) + "");
//                        }
//                    } else {
//                        map.put("erro", "No Info Find");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                alert("Error: " + error.getMessage(), true);
//            }
//        }) {
//            protected Map<String, String> getParams() {
//                Map<String, String> data = new HashMap();
//
//                for (int i = 0; i < keys.length; i++) {
//                    data.put(keys[i], values[i]);
//                }
//                return data;
//            }
//        };
//
//        return map;
//    }
//
//    public static Map<String, String> apiGET(String[] keys, String[] values, String from) {
//
//        String dataUrlTemp = dataUrl;
//        HashMap<String, String> map = new HashMap();
//
//        dataUrlTemp += from + "?";
//        dataUrlTemp += from + "?token=" + token;
//
//        if (keys.length == values.length) {
//            int length = keys.length;
//
//            if (length > 0) {
//                for (int i = 0; i < length; i++) {
//                    dataUrlTemp += "&" + keys[i] + "=" + values[i];
//                }
//            }
//
//            try {
//
//                HttpClient httpclient = (HttpClient) new DefaultHttpClient();
//                HttpGet httppost = new HttpGet(dataUrl);
//                HttpResponse response = (HttpResponse) httpclient.execute(httppost);
//                HttpEntity entity = response.getEntity();
//
//                JSONObject object = new JSONObject(EntityUtils.toString(entity));
//                if (object.length() > 0) {
//                    while (object.keys().hasNext()) {
//                        String s = object.keys().next();
//
//                        map.put(s + "", object.get(s) + "");
//
//                    }
//                } else {
//                    map.put("erro", "No Info Find");
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
////        return solution;
//
//        return map;
//    }

//    public static void setToken(String token) {
//        ConnectionAPI.token = token;
//    }


}