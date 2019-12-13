package com.example.assgn3;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Context context;

    private List<String> city;
    private List<String> name;
    private List<String> sport;
    private List<String> mvp;
    private List<String> stadium;

    private LayoutInflater layoutInflater;

    CustomListAdapter(Context context, List<String> city, List<String> name,
                      List<String> sport, List<String> mvp, List<String> stadium) {
        this.city = city;
        this.name = name;
        this.sport = sport;
        this.mvp = mvp;
        this.stadium = stadium;
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        // Auto-generated method stu
        return this.city.size();
    }

    @Override
    public Object getItem(int pos) {
        // Auto-generated method stu
        return null;
    }

    @Override
    public long getItemId(int pos) {
        // Auto-generated method stu
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        convertView = layoutInflater.inflate(R.layout.list_view, null);

        TextView cityText = (TextView) convertView.findViewById(R.id.city);
        TextView nameText = (TextView) convertView.findViewById(R.id.name);

        cityText.setText(city.get(pos));
        nameText.setText(name.get(pos));

        return convertView;
    }
}
