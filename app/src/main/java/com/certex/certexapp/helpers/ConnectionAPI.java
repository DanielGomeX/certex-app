package com.certex.certexapp.helpers;

import android.support.v7.app.AppCompatActivity;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ConnectionAPI extends AppCompatActivity {

    private static String token;

    private static String dataUrl = "177.44.248.19/api/";

    public static final String LOGIN = "login";
    public static final String COMPANY = "company";

    public static String apiPOST(String[] keys, String[] values, String from) {

        String dataUrlTemp = dataUrl;
        String solution = null;
        dataUrlTemp += from + "?";
        //dataUrlTemp += from + "?token=" + token;
        if (keys.length == values.length) {
            int length = keys.length;

            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    if (i == 0) {
                        dataUrlTemp += keys[i] + "=" + values[i];
                    } else {
                        dataUrlTemp += "&" + keys[i] + "=" + values[i];
                    }
                }
            }

            StringBuilder reply = new StringBuilder();

            try {
                URL url = new URL(dataUrlTemp);
                HttpURLConnection client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("POST");
                client.setRequestProperty("Accept", "application/json");
                client.setConnectTimeout(5000);
                client.connect();

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    reply.append(scanner.next());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            solution = reply.toString().trim();

        }

        return solution;
    }

    public static String apiGET(String[] keys, String[] values, String from) {

        String dataUrlTemp = dataUrl;
        String solution = null;
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


                solution = EntityUtils.toString(entity).trim();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return solution;
    }

    public static void setToken(String token) {
        ConnectionAPI.token = token;
    }


}
