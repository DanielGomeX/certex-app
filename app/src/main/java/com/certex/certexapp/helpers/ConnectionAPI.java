package com.certex.certexapp.helpers;

import android.support.v7.app.AppCompatActivity;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionAPI extends AppCompatActivity {

    private static String token;

    private static String dataUrl = "177.44.248.19/api/";

    public static final String LOGIN = "login";
    public static final String COMPANY = "company";

    public static String api(String[] keys, String[] values, String from){

        String dataUrlTemp = dataUrl;
        String solution = null;
        dataUrlTemp += from + "?";
//        dataUrlTemp += from + "?token=" + token;
        if (keys.length == values.length) {
            int length = keys.length;

            if(length > 0) {
                for (int i = 0; i < length; i++) {
                    dataUrlTemp += "&" + keys[i]+"="+values[i];
                }
            }

                try {

//                    HttpClient httpclient = (HttpClient) new DefaultHttpClient();
//                    HttpPost httppost = new HttpPost(dataUrl);
//                    HttpResponse response = (HttpResponse) httpclient.execute(httppost);
//                    HttpEntity entity = response.getEntity();


                    URL url = new URL(dataUrlTemp);
                    HttpURLConnection client = null;
                    try {
                        client = (HttpURLConnection) url.openConnection();
                        client.setRequestMethod("POST");
                        client.setRequestProperty("key", "value");
                        client.setDoOutput(true);

                        OutputStream outputPost = new BufferedOutputStream(client.getOutputStream());
                        solution = outputPost.toString();
                        outputPost.flush();
                        outputPost.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    //solution =EntityUtils.toString(entity).trim();

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
