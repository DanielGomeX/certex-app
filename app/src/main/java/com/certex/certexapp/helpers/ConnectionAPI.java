package com.certex.certexapp.helpers;

import android.support.v7.app.AppCompatActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ConnectionAPI extends AppCompatActivity {

    private static String token;

    private static String dataUrl = "http://177.44.248.19/api/";

    public static final String LOGIN = "login";
    public static final String COMPANY = "company";

    public static String api(String[] keys, String[] values, String from){

        String dataUrlTemp = dataUrl;
        String solution = null;

        dataUrlTemp += from + "?token=" + token;
        if (keys.length == values.length) {
            int length = keys.length;

            if(length > 0) {
                for (int i = 0; i < length; i++) {
                    dataUrlTemp += "&" + keys[i]+"="+values[i];
                }
            }

                try {

                    HttpClient httpclient = (HttpClient) new DefaultHttpClient();
                    HttpGet httpget = new HttpGet(dataUrl);
                    HttpResponse response = (HttpResponse) httpclient.execute(httpget);
                    HttpEntity entity = response.getEntity();

                    solution = EntityUtils.toString(entity).trim();

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        return solution;
    }

    public static void setToken(String token){
        ConnectionAPI.token = token;
    }


}
