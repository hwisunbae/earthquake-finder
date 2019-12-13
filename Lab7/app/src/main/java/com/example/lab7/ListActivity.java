package com.example.lab7;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class ListActivity extends AppCompatActivity {
    public static final String EXTRA_PERSON_STRING = "com.lab7.person.string";
    public static final String EXTRA_PERSON_STRING2 = "com.lab7.person.string2";

    private ListView listViewL;
    private ListView listViewR;
    private List<Person> personL = new ArrayList<>();
    private List<Person> personR = new ArrayList<>();
    private ArrayList<String> strL = new ArrayList<>();
    private ArrayList<String> strR = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        /** Get intent and put data in List<Person> */
        strL = getIntent().getStringArrayListExtra(EXTRA_PERSON_STRING);
        for (int i = 0; i < strL.size()/3; i++)
            personL.add(i, new Person(strL.get(3*i), strL.get(3*i+1), strL.get(3*i+2)));

        strR = getIntent().getStringArrayListExtra(EXTRA_PERSON_STRING2);
        for (int j = 0; j < strR.size()/3; j++)
            personR.add(j, new Person(strR.get(3*j), strR.get(3*j+1), strR.get(3*j+2)));

        /** LEFT List View */
        listViewL = findViewById(R.id.leftView);
        listViewL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = LeftActivity2.newIntent(ListActivity.this,
                        personL.get(i).getName(), personL.get(i).getDept(), personL.get(i).getYear());
                startActivity(intent);
            }
        });

        LeftAdapter leftAdapter = new LeftAdapter(personL, ListActivity.this);
        listViewL.setAdapter(leftAdapter);

        /** RIGHT List View */
        listViewR = findViewById(R.id.rightView);
        listViewR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentR = RightActivity2.newIntent(ListActivity.this,
                        personR.get(i).getName(), personR.get(i).getDept(), personR.get(i).getYear());
                startActivity(intentR);
            }
        });

        RightAdapter rightAdapter = new RightAdapter(personR, ListActivity.this);
        listViewR.setAdapter(rightAdapter);
    }

    public static Intent newIntent(Context context, ArrayList<String> strL, ArrayList<String> strR) {
        Intent intent = new Intent(context, ListActivity.class);
        intent.putStringArrayListExtra(EXTRA_PERSON_STRING, strL);
        intent.putStringArrayListExtra(EXTRA_PERSON_STRING2, strR);

        return intent;
    }
}
