package com.certex.certexapp.service;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.certex.certexapp.R;

public class CustomListView extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] maintitle;
    private final String[] subtitle;
    private final Integer[] imgid;

    public CustomListView(Activity context, String[] maintitle, String[] subtitle, Integer[] imgid) {
        super(context, R.layout.custom_list_view, maintitle);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.maintitle = maintitle;
        this.subtitle = subtitle;
        this.imgid = imgid;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custom_list_view, null, true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title_list_default);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon_list_default);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.title_list_default);

        titleText.setText(maintitle[position]);
        imageView.setImageResource(imgid[position]);
        subtitleText.setText(subtitle[position]);

        return rowView;
    }
}