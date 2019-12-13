package com.example.assgn3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TeamMainActivity extends AppCompatActivity {
    public static String EXTRA_CITY = "com.team.city";
    public static String EXTRA_NAME = "com.team.name";
    public static String EXTRA_SPORT = "com.team.sport";
    public static String EXTRA_MVP = "com.team.mvp";
    public static String EXTRA_STADIUM = "com.team.stadium";

    private EditText cityEdit;
    private EditText nameEdit;
    private EditText sportEdit;
    private EditText mvpEdit;
    private EditText stadiumEdit;
    private Button exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);

        cityEdit = findViewById(R.id.city);
        nameEdit = findViewById(R.id.name);
        sportEdit = findViewById(R.id.sport);
        mvpEdit = findViewById(R.id.mvp);
        stadiumEdit = findViewById(R.id.stadium);
        exitBtn = findViewById(R.id.exitBtn);

        cityEdit.setText(getIntent().getStringExtra(EXTRA_CITY));
        nameEdit.setText(getIntent().getStringExtra(EXTRA_NAME));
        sportEdit.setText(getIntent().getStringExtra(EXTRA_SPORT));
        mvpEdit.setText(getIntent().getStringExtra(EXTRA_MVP));
        stadiumEdit.setText(getIntent().getStringExtra(EXTRA_STADIUM));

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                setResult(RESULT_OK, getIntent());
            }
        });
    }

    public static Intent newIntent (Context context, String city, String name,
                                    String sport, String mvp, String stadium) {
        Intent intent = new Intent(context, TeamMainActivity.class);
        intent.putExtra(EXTRA_CITY, city);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_SPORT, sport);
        intent.putExtra(EXTRA_MVP, mvp);
        intent.putExtra(EXTRA_STADIUM, stadium);
        return intent;
    }
}
