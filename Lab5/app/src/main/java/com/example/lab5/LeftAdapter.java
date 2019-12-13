package com.example.lab5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LeftAdapter extends BaseAdapter {
    private Country [] countries;
    private Context context;
    private LayoutInflater layoutInflater;

    LeftAdapter() {
        this.countries = null;
    }

    LeftAdapter(Country [] countries, Context context){
        super();
        this.context = context;
        this.countries = countries;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // Auto-generated method stu
        return this.countries.length;
    }

    @Override
    public Object getItem(int position) {
        // Auto-generated method stu
        return countries[position];
    }

    @Override
    public long getItemId(int position) {
        // Auto-generated method stu
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.l_list_item, null);

        TextView capitalText = convertView.findViewById(R.id.capital);
        TextView countryText = convertView.findViewById(R.id.country);
        ImageView flagImg = convertView.findViewById(R.id.flag);

        capitalText.setText(countries[position].getCapital());
        countryText.setText(countries[position].getCountry());
        flagImg.setImageResource(countries[position].getFlag());

        return convertView;
    }

}
