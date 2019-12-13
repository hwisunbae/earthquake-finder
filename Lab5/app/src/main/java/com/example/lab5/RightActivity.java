package com.example.lab5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RightActivity extends AppCompatActivity {

    private static final String EXTRA_CAPITAL = "capital_text";
    private static final String EXTRA_COUNTRY = "country_text";
    private static final String EXTRA_FLAG = "flag_img";
    private static final String EXTRA_ALPHA = "alphabet_text";

    private ImageView flagImg;
    private TextView capitalText;
    private TextView countryText;
    private TextView alphabetText;

    private int flag;
    private String country;
    private String capital;
    private String alphabet;

    public static Intent newIntent(Context context, String country, String capital, String alphabet, int flag) {
        Intent intent = new Intent(context, RightActivity.class);
        intent.putExtra(EXTRA_COUNTRY, country);
        intent.putExtra(EXTRA_CAPITAL, capital);
        intent.putExtra(EXTRA_FLAG, flag);
        intent.putExtra(EXTRA_ALPHA, alphabet);

        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_list_main);

        flagImg = findViewById(R.id.flag);
        capitalText = findViewById(R.id.capital);
        countryText = findViewById(R.id.country);
        alphabetText = findViewById(R.id.alphabet);

        flag = getIntent().getIntExtra(EXTRA_FLAG, 0);
        country = getIntent().getStringExtra(EXTRA_COUNTRY);
        capital = getIntent().getStringExtra(EXTRA_CAPITAL);
        alphabet = getIntent().getStringExtra(EXTRA_ALPHA);

        flagImg.setImageResource(flag);
        countryText.setText(country);
        capitalText.setText(capital);
        alphabetText.setText(alphabet);
    }
}
