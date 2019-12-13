package com.example.assng4;

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

import static com.example.assng4.TeamMainActivity.EXTRA_CITY;

public class MainActivity extends AppCompatActivity {


    private Button addTeamBtn;
    private Button exitMainBtn;
    private ListView listView;
    private String city;

    private List<String> idList;
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

        addTeamBtn = findViewById(R.id.addTeamBtn);
        exitMainBtn = findViewById(R.id.exitBtn);

        exitMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
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
    protected void onResume() {
        super.onResume();

        listView = findViewById(R.id.list_item);

        myDb = new DatabaseHelper(getApplicationContext());
        idList = myDb.getAllData(0);
        cityList = myDb.getAllData(1);
        nameList = myDb.getAllData(2);
        sportList = myDb.getAllData(3);
        mvpList = myDb.getAllData(4);
        stadiumList = myDb.getAllData(5);

        CustomListAdapter customListAdapter = new CustomListAdapter(MainActivity.this, cityList,
                nameList, sportList, mvpList, stadiumList);
        listView.setAdapter(customListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = TeamMainActivity.newIntent(MainActivity.this,
                        idList.get(i), cityList.get(i), nameList.get(i), sportList.get(i), mvpList.get(i), stadiumList.get(i));

                startActivityForResult(intent, REQUEST_CODE_TEAM_LIST);

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
                    String tmp = getIntent().getStringExtra(EXTRA_CITY);
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
