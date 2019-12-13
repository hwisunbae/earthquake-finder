package com.example.assgn3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button addTeamBtn;
    private Button exitMainBtn;
    private ListView listView;
    private String city;

    private List<String> cityList;
    private List<String> nameList;
    private List<String> sportList;
    private List<String> mvpList;
    private List<String> stadiumList;

    DatabaseHelper myDb;

    private static final int REQUEST_CODE_TEAM_LIST = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_item);
        addTeamBtn = findViewById(R.id.addTeamBtn);
        exitMainBtn = findViewById(R.id.exitBtn);

        myDb = new DatabaseHelper(getApplicationContext());
        cityList = myDb.getAllData(0);
        nameList = myDb.getAllData(1);
        sportList = myDb.getAllData(2);
        mvpList = myDb.getAllData(3);
        stadiumList = myDb.getAllData(4);

        CustomListAdapter customListAdapter = new CustomListAdapter(MainActivity.this, cityList,
                nameList, sportList, mvpList, stadiumList);
        listView.setAdapter(customListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = TeamMainActivity.newIntent(MainActivity.this,
                        cityList.get(i), nameList.get(i), sportList.get(i), mvpList.get(i), stadiumList.get(i));

                startActivityForResult(intent, REQUEST_CODE_TEAM_LIST);
            }
        });

        exitMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
//                Toast.makeText(MainActivity.this, cityList.get(0), Toast.LENGTH_SHORT).show();
            }
        });

        addTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = TeamActivity.newIntent(MainActivity.this);
                startActivityForResult(intent, TeamActivity.REQUEST_CODE_ADDED);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode != Activity.RESULT_OK)
                return;

            if (requestCode == TeamActivity.REQUEST_CODE_ADDED) {
                if (resultCode == Activity.RESULT_OK) {
//                    teams.add(TeamActivity.getTeams());
                    city = TeamActivity.getCity(data) + TeamActivity.getName(data) +
                            TeamActivity.getSport(data) +TeamActivity.getMvp(data) +
                            TeamActivity.getStadium(data);
                }
            }

            if (requestCode == REQUEST_CODE_TEAM_LIST) {
                if (data == null)
                    return;
            }

        }
        catch (Exception e) {
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
