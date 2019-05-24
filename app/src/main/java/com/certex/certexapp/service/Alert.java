package com.certex.certexapp.service;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.certex.certexapp.R;


public class Alert {

    public void show(String msg, boolean error, LayoutInflater layoutInflater, Context context, View view) {
        if (error) {
            LayoutInflater inflater = layoutInflater;
            View layout = inflater.inflate(R.layout.custom_toast_erro,
                    (ViewGroup) view.findViewById(R.id.custom_toast_container));

            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText(msg);

            Toast toast = new Toast(context);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        } else {
            LayoutInflater inflater = layoutInflater;
            View layout = inflater.inflate(R.layout.custom_toast_ok,
                    (ViewGroup) view.findViewById(R.id.custom_toast_container));

            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText(msg);

            Toast toast = new Toast(context);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        }
    }
}
