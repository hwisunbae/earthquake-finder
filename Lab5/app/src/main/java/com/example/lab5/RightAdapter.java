package com.example.lab5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RightAdapter extends BaseAdapter {
    private Country [] countries;
    private Context context;
    private LayoutInflater layoutinflater;

    RightAdapter() {
        this.countries = null;
    }

    RightAdapter(Country [] countries, Context context) {
        super();
        this.countries = countries;
        this.context = context;
        this.layoutinflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.countries.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutinflater.inflate(R.layout.r_list_item, null);

        TextView capitalText = (TextView) convertView.findViewById(R.id.capital);
        TextView countryText = (TextView) convertView.findViewById(R.id.country);
        TextView alphabetText = (TextView) convertView.findViewById(R.id.alphabet);
        ImageView flagImg = (ImageView) convertView.findViewById(R.id.flag);

        capitalText.setText(countries[position].getCapital());
        countryText.setText(countries[position].getCountry());
        alphabetText.setText(countries[position].getAlphabet());
        flagImg.setImageResource(countries[position].getFlag());

        return convertView;
    }




}
