package com.example.lab7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class RightAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<Person> person;

    RightAdapter() {
    }

    RightAdapter(List<Person> person, Context context) {
        super();
        this.context = context;
        this.person = person;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.person.size();
    }

    @Override
    public Object getItem(int pos) {
        return person.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        // Auto-generated method stu
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.r_list_item, null);
        TextView nameText = convertView.findViewById(R.id.nameDisplayR);

        nameText.setText(person.get(pos).getName());
        return convertView;
    }







}
