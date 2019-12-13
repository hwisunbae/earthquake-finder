package com.example.lab5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LeftActivity extends AppCompatActivity {

    private static final String EXTRA_CAPITAL = "capital_text";
    private static final String EXTRA_COUNTRY = "country_text";
    private static final String EXTRA_FLAG = "flag_img";

    private ImageView flagImg;
    private TextView capitalText;
    private TextView countryText;

    private int flag;
    private String country;
    private String capital;

    public static Intent newIntent(Context context, String country, String capital, int flag) {
        Intent intent = new Intent(context, LeftActivity.class);
        intent.putExtra(EXTRA_COUNTRY, country);
        intent.putExtra(EXTRA_CAPITAL, capital);
        intent.putExtra(EXTRA_FLAG, flag);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_list_main);

        flagImg = findViewById(R.id.flag);
        capitalText = findViewById(R.id.capital);
        countryText = findViewById(R.id.country);

        flag = getIntent().getIntExtra(EXTRA_FLAG, 0);
        country = getIntent().getStringExtra(EXTRA_COUNTRY);
        capital = getIntent().getStringExtra(EXTRA_CAPITAL);

        flagImg.setImageResource(flag);
        countryText.setText(country);
        capitalText.setText(capital);


    }
}
