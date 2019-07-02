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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ConnectionAPI {

    private final static String url = "http://177.44.248.19/api/";
    public final static String TABLE_LOGIN = "login";
    public final static String TABLE_COMPANY = "company";
    public final static String TABLE_CEP = "cep";
    public final static String TABLE_MANUFACTURER = "manufacturer";
    public final static String TABLE_USER = "user";

    public final static String ACTION_STORE = "store";
    public final static String ACTION_UPDATE = "update";
    public final static String ACTION_SHOW = "show";
    public final static String ACTION_INDEX = "index";
    public final static String ACTION_DESTROY = "destroy";
    public final static String ACTION_COUNT = "count";
    public final static String ACTION_NULL = null;

    /**
     *
     * @param from
     * @param action
     * @param indice
     * @param jsonData
     * @return
     */
    public static JSONObject makePost(String from, String action, String indice, JSONObject jsonData) {
        URL myUrl;
        HttpURLConnection connection = null;
        JSONObject json = null;
        //Formando a URL
        String urlTemp = url + from;
        if (indice != null){ urlTemp += "/" + indice; }
        if (action != null){ urlTemp += "/" + action; }
        if (Session.isFromInstance()) { urlTemp += "?token=" + Session.getInstance().getToken().getCode(); }
        Log.i("URL DO POST => ", urlTemp);
        try {
            //Estabelecer a conexão
            myUrl = new URL(urlTemp);
            connection = (HttpURLConnection) myUrl.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST"); // hear you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
            connection.connect();
            //Enviar as requisições
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(jsonData.toString());
            wr.flush();
            wr.close();
            //Receber os resultados
            InputStream is;
            String response = connection.getResponseMessage();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseOutput = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) { responseOutput.append(line); }
            br.close();
            Log.i("RETORNO BRUTO POST => ", responseOutput.toString());
            //Montar o JSON
            json = new JSONObject(responseOutput.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) { connection.disconnect(); }
            return json;
        }
    }

    /**
     *
     * @param parametersFixed /cep/95890000
     * @param parameters ?name=modelo&idade=99
     * @param from
     * @param action
     * @return
     */
    public static JSONObject makeGet ( String[] parametersFixed, String[] parameters, String from, String action ){
        StringBuilder result = new StringBuilder();
        JSONObject json = null;
        //Montar a URL
        String dataUrlTemp = url + from;
        if (parametersFixed != null){
            for (int i = 0; i < parametersFixed.length; i++){ dataUrlTemp += "/" + parametersFixed[i]; }
        }
        if (action != null) { dataUrlTemp += "/" + action; }
        if (Session.isFromInstance()) { dataUrlTemp += "?token=" + Session.getInstance().getToken().getCode(); }
        if (parameters != null){
            for (int i = 0; i < parameters.length; i++){
                if ( Session.isFromInstance() && i ==0){ dataUrlTemp += "?" + parameters[i]; }
                else { dataUrlTemp += "&" + parameters[i]; }
            }
        }
        Log.i("URL DO GET => ", dataUrlTemp);
        try {
            //Estabelecer a conexão
            URL url = new URL(dataUrlTemp);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //Receber o resultado
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) { result.append(line); }
            //Montar o JSON
            String st_json = result.toString();
            json = new JSONObject(st_json);
//            Log.i("JSON", json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return json;
        }

    }

    @Deprecated
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


//    public static HashMap apiPOST(final String[] keys, final String[] value, final String[] keysInput, String table, Activity activity) {
//        final RequestQueue MyRequestQueue = Volley.newRequestQueue(activity);
//
//        Log.i("Script", "********************** Antes da URL");
//
//        RetryHashMap m = new RetryHashMap();
//
//        String urlTemp = url + table;
//        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, urlTemp,
//
//                new Response.Listener<String>()
//                {
//
//                    @Override
//                    public void onResponse(String response) {
//                        Log.i("Script", "********************** Entrou no Response");
//
//                        try {
//
//                            JSONObject object = new JSONObject(response);
//                            RetryHashMap m = new RetryHashMap();
//
//                            for (int i = 0; i < keysInput.length; i++){
//                                String temp = object.getString(keysInput[i]);
//                                m.put(keysInput[i], temp);
//
//                                Log.i("Script", "################################## JSON: "+ keysInput[i] +" ->" + temp);
//                            }
//
//                            HashMap<String, String> ma = m.temp;
//
//                            for ( String k : ma.keySet()){
//                                Log.i("Script", "================IN RESPONSE================= > " + ma.get(k));
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //alert("Error: " + error.getMessage());
//                        Log.i("Script", "ERROR: " + error.getMessage());
//                    }
//                }) {
//                    protected Map<String, String> getParams() {
//                        Map<String, String> myData = new HashMap<String, String>();
//                        for (int i = 0; i < keys.length; i++) {
//                            myData.put(keys[i], value[i]);
//                        }
//        //                MyData.put("email", "vitor@certex.com");
//        //                MyData.put("password", "123456");
//                        return myData;
//                    }
//                };
//
//        HashMap<String, String> map = RetryHashMap.temp;
//
//        //RetryHashMap.temp = RetryHashMap.newMap();
//
//        for ( String k : map.keySet()){
//            Log.i("Script", "================================= > " + map.get(k));
//        }
//
//        Log.i("StringRequest ::::::::::::::::; ", MyStringRequest.toString());
//
//
//        MyRequestQueue.add(MyStringRequest);
//
//        return map;
//    }

//    public static HashMap apiPOSTtest(String[] keys, String[] value, String[] keysInput, String table) {
//        HashMap<String, String> map = new HashMap();
//
//        String urlTemp = url + table;
//
//        try {
//            URL myUrl = new URL(urlTemp);
//
//            HttpURLConnection connection =  (HttpURLConnection) myUrl.openConnection();
//
//            String urlParameters = "?";
//
//            for (int i = 0; i < keys.length; i++){
//                urlParameters = keys[i]+"="+value[i]+"&";
//            }
//
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
//            connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
//            connection.setDoOutput(true);
//
//            DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
//            dStream.writeBytes(urlParameters);
//            dStream.flush();
//            dStream.close();
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String line = "";
//            StringBuilder responseOutput = new StringBuilder();
//
//            while ((line = br.readLine()) != null) {
//                responseOutput.append(line);
//            }
//            br.close();
//
//            Log.i("NOVA POST :::::::::::::::::::::::::: ",responseOutput.toString());
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return map;
//    }

//    public static Map<String, String> apiGET(String[] parametersFixed, String[] parameters, String from, String action) {
//
//        String dataUrlTemp = url;
//        HashMap<String, String> map = new HashMap();
//
//        dataUrlTemp += from;
//
//        if (parametersFixed != null){
//            for (int i = 0; i < parametersFixed.length; i++){
//                dataUrlTemp += "/" + parametersFixed[i];
//            }
//        }
//
//        if (action != null) {
//            dataUrlTemp += "/" + action;
//        }
//
//        if (Session.isFromInstance()) {
//            dataUrlTemp += "?token=" + Session.getInstance().getToken().getCode();
//        }
//
//        if (parameters != null){
//            for (int i = 0; i < parameters.length; i++){
//                if ( Session.isFromInstance() && i ==0){
//                    dataUrlTemp += "?" + parameters[i];
//                } else {
//                    dataUrlTemp += "&" + parameters[i];
//                }
//            }
//        }
//
//        try {
//            dataUrlTemp = "http://viacep.com.br/ws/95890000/json";
//            HttpClient httpclient = (HttpClient) new DefaultHttpClient();
//            HttpGet httppost = new HttpGet(dataUrlTemp);
//            HttpResponse response = (HttpResponse) httpclient.execute(httppost);
//            HttpEntity entity = response.getEntity();
//            Log.i("SCRIPT API GET URL", dataUrlTemp);
//            Log.i("SCRIPT API GET RESULT", EntityUtils.toString(entity));
//
//            JSONObject object = new JSONObject(EntityUtils.toString(entity));
////            if (object.length() > 0) {
////                while (object.keys().hasNext()) {
////                    String s = object.keys().next();
////                    map.put(s + "", object.get(s) + "");
////                }
////            } else {
////                map.put("erro", "No Info Find");
////            }
//            Log.i("JSON", "Aqui");
//
//            Log.i("JSON", object.toString());
//        } catch (Exception e) {
//
//            Log.i("JSON###", e.getMessage());
//            e.printStackTrace();
//        }
//
////        return solution;
//
//        return map;
//    }
}