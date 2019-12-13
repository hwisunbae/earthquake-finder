package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    static int LIST_ITEM_COUNT = 12;
    Country [] l_countries = new Country[LIST_ITEM_COUNT];
    Country [] r_countries = new Country[LIST_ITEM_COUNT];
//    char [] alphabet = new char [LIST_ITEM_COUNT];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         *  Left capital and countries
         */
        String [] LCapital = getResources().getStringArray(R.array.LCapitals);
        String [] LCountries = getResources().getStringArray(R.array.LCountries);

        for (int i = 0; i < LIST_ITEM_COUNT; i++) {
            int flag = getResources().getIdentifier("l_img" + (i+1), "drawable",
                    getPackageName());
            l_countries[i] = new Country(LCountries[i], LCapital[i], flag);
        }

        ListView l_countryList = (ListView) findViewById(R.id.leftListView);
        l_countryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String l_capital = l_countries[i].getCapital();
                String l_country = l_countries[i].getCountry();
                int l_flag = l_countries[i].getFlag();
//                Toast.makeText(MainActivity.this, item, Toast.LENGTH_LONG).show();
                Intent intent = LeftActivity.newIntent(MainActivity.this,
                        l_country, l_capital, l_flag);
                startActivity(intent);
            }
        });
        /** With use of class, default ArrayAdapter does not work  */
        //  ArrayAdapter<Country> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);
        //  countryList.setAdapter(adapter);
        LeftAdapter l_adapter = new LeftAdapter(l_countries, MainActivity.this);
        l_countryList.setAdapter(l_adapter);



        /**
         *  Right capital and countries
         */
        String [] RCapital = getResources().getStringArray(R.array.RCapitals);
        String [] RCountries = getResources().getStringArray(R.array.RCountries);
        String [] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L"};

        for (int i = 0; i < LIST_ITEM_COUNT; i++) {
            int flag = getResources().getIdentifier("r_img" + (i+1), "drawable", getPackageName());
            r_countries[i] = new Country(RCountries[i], RCapital[i], flag, alphabet[i]);
        }

        ListView r_countryList = (ListView) findViewById(R.id.rightListView);
        r_countryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String r_capital = r_countries[i].getCapital();
                String r_country = r_countries[i].getCountry();
                String r_alphabet = r_countries[i].getAlphabet();
                int r_flag = r_countries[i].getFlag();
                Intent intent = RightActivity.newIntent(MainActivity.this,
                        r_country, r_capital, r_alphabet, r_flag);
                startActivity(intent);
            }
        });
        RightAdapter r_adapter = new RightAdapter(r_countries, MainActivity.this);
        r_countryList.setAdapter(r_adapter);
    }
}
