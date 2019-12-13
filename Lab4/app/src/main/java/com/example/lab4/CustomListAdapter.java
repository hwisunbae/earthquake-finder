package com.example.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter {

    private Context context;
    private String[] str;

    public CustomListAdapter(Context context, String[] str) {
        this.context = context;
        this.str = str;
    }

    @Override
    public int getCount() {
        return this.str.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // inflate the layout for each list row
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.alpha_item, null);

        // get the TextView for item name and item description
        TextView textView = (TextView) convertView.findViewById(R.id.text_view);
        textView.setText(this.str[position]);

        // returns the view for the current row
        return convertView;
    }

}