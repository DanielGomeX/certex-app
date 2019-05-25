package com.certex.certexapp.helpers;

import android.support.v7.app.AppCompatActivity;

public class ConnectionAPI extends AppCompatActivity {

    private static String token;

    private static String dataUrl = "http://177.44.248.19/api/" + "?token=";



    public static String api(String[] keys, String[] values){

        String dataUrlTemp = dataUrl;
        String solution = null;

        dataUrlTemp += token;
        if (keys.length == values.length) {
            int length = keys.length;

            if(length > 0) {
                for (int i = 0; i < length; i++) {
                    dataUrlTemp += "&" + keys[i]+"="+values[i];
                }
            }

                try {

//                    HttpClient httpclient = (HttpClient) new DefaultHttpClient();
//                    HttpGet httpget = new HttpGet(dataUrl);
//                    HttpResponse response = (HttpResponse) httpclient.execute(httpget);
//                    HttpEntity entity = response.getEntity();


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
