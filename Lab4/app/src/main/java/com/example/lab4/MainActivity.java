package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ListView alphaListView;
    private String alpha [] = new String [] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private GridView numGridView;
    private String num[] = new String[99];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Left ListView for A to Z
         */
//        alphaListView = (ListView) findViewById(R.id.alphaList);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alpha);
//        alphaListView.setAdapter(adapter);

        alphaListView = (ListView) findViewById(R.id.alphaList);
        CustomListAdapter alphaAdapter = new CustomListAdapter(MainActivity.this, alpha);
        alphaListView.setAdapter(alphaAdapter);


        /**
         * Right GridView for 5 to 195
         */
        for (int i = 0 ; i < num.length; i++ ) {
            int temp = (i+1) * 5;
            num[i] = Integer.toString(temp);
        }
//        numGridView = (GridView) findViewById(R.id.numList);
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, num);
//        numGridView.setAdapter(adapter2);

        numGridView = (GridView)findViewById(R.id.numList);
        CustomNumAdapter numAdapater = new CustomNumAdapter(MainActivity.this, num);
        numGridView.setAdapter(numAdapater);

    }
}
