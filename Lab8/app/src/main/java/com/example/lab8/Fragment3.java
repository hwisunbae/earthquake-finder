package com.example.lab8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {
    public Fragment3() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.frag1, container, false);

        List<String> stringList = new ArrayList<>();
        stringList.add("Milk");
        stringList.add("Bread");
        stringList.add("Sugar");
        stringList.add("Powder");
        stringList.add("Apples");
        stringList.add("Bananas");
        stringList.add("Grapes");
        stringList.add("cookies");
        stringList.add("Juices");
        stringList.add("Cheese");
        stringList.add("Eleven");
        stringList.add("Fish");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_list_item_1, stringList);
        ListView listView = v.findViewById(R.id.list_item);
        listView.setAdapter(adapter);


        return v;
    }
}
