package com.example.lab8;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter1 extends ArrayAdapter<String> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> strings;

    public CustomAdapter1(Activity activity, List<String> item) {
        super(activity, R.layout.frag1, item);
        this.context = activity;
        this.strings = item;
    }

    @Override
    public int getCount() {
        return this.strings.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (layoutInflater == null)
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.list_view, null);

        TextView textView = convertView.findViewById(R.id.textView);
        textView.setText(strings.get(position));

        View list = convertView.findViewById(R.id.list_id);
        list.setBackgroundColor(Color.parseColor("#FF4444"));
        return convertView;
    }
}
