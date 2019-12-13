package com.example.assgn3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    public static List<Team> teams = new ArrayList<>();

    public static String EXTRA_CITY = "com.team.city";
    public static String EXTRA_NAME = "com.team.name";
    public static String EXTRA_SPORT = "com.team.sport";
    public static String EXTRA_MVP = "com.team.mvp";
    public static String EXTRA_STADIUM = "com.team.stadium";
    public static int REQUEST_CODE_ADDED = 2;

    private EditText cityEdit;
    private EditText nameEdit;
    private EditText sportEdit;
    private EditText mvpEdit;
    private EditText stadiumEdit;
    private Button submitBtn;
    private Button exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_team_main);

        myDb = new DatabaseHelper(this);
        cityEdit = findViewById(R.id.city);
        nameEdit = findViewById(R.id.name);
        sportEdit = findViewById(R.id.sport);
        mvpEdit = findViewById(R.id.mvp);
        stadiumEdit = findViewById(R.id.stadium);
        submitBtn = findViewById(R.id.submitBtn);
        exitBtn = findViewById(R.id.exitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = cityEdit.getText().toString(),
                        name = nameEdit.getText().toString(),
                        sport = sportEdit.getText().toString(),
                        mvp = mvpEdit.getText().toString(),
                        stadium = stadiumEdit.getText().toString();

                if(city.equals("") || name.equals(""))
                    Toast.makeText(TeamActivity.this, "City and Name are required" , Toast.LENGTH_SHORT).show();
                else {
                    teams.add(new Team(city, name, sport, mvp, stadium));
                    boolean isInserted = myDb.insertData(city, name, sport, mvp, stadium);

                    if(isInserted) {
                        getIntent().putExtra(EXTRA_CITY, city);
                        getIntent().putExtra(EXTRA_NAME, name);
                        getIntent().putExtra(EXTRA_SPORT, sport);
                        getIntent().putExtra(EXTRA_MVP, mvp);
                        getIntent().putExtra(EXTRA_STADIUM, stadium);

//                        Intent data = getIntent();
//                        Toast.makeText(TeamActivity.this, getCity(data) + getName(data), Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(TeamActivity.this, "not inserted" , Toast.LENGTH_SHORT).show();
                    clear();
                }
                setResult(RESULT_OK, getIntent());
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeamActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public static String getCity(Intent result) {
        return result.getStringExtra(EXTRA_CITY);
    }

    public static String getName(Intent result) {
        return result.getStringExtra(EXTRA_NAME);
    }

    public static String getSport(Intent result) {
        return result.getStringExtra(EXTRA_SPORT);
    }

    public static String getMvp(Intent result) {
        return result.getStringExtra(EXTRA_MVP);
    }

    public static String getStadium(Intent result) {
        return result.getStringExtra(EXTRA_STADIUM);
    }

    public static Intent newIntent (Context context) {
        Intent intent = new Intent(context, TeamActivity.class);
        return intent;
    }

    public void clear() {
        cityEdit.setText("");
        nameEdit.setText("");
        sportEdit.setText("");
        mvpEdit.setText("");
        stadiumEdit.setText("");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TeamActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
