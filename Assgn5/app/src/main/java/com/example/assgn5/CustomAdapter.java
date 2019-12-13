package com.example.assgn5;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> quakeList = new ArrayList<>();

    public CustomAdapter(Activity activity, List<String> item) {
        super(activity, R.layout.list_view, item);
        this.context = activity;
        this.quakeList = item;
    }

    @Override
    public int getCount() {
        return this.quakeList.size();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        if(layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_view, null);
        }
        String earthInfo[] = quakeList.get(pos).split("@@");

        TextView areaText = convertView.findViewById(R.id.area);
        TextView timeText = convertView.findViewById(R.id.time);

        areaText.setText(earthInfo[0]);
        timeText.setText(earthInfo[1]);

        View list = convertView.findViewById(R.id.list_id);

        if (pos % 2 == 0)
            list.setBackgroundColor(Color.parseColor("#99CC00"));
        else
            list.setBackgroundColor(Color.parseColor("#FFBB33"));

        return convertView;
    }

}