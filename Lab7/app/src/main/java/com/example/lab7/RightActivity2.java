package com.example.lab7;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RightActivity2 extends AppCompatActivity {
    public static final String EXTRA_PERSON_NAME = "com.lab7.person.name";
    public static final String EXTRA_PERSON_DEPT = "com.lab7.person.dept";
    public static final String EXTRA_PERSON_YEAR = "com.lab7.person.year";

    private TextView name;
    private TextView dept;
    private TextView year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_list_main);

        name = findViewById(R.id.name_RMain);
        dept = findViewById(R.id.dept_RMain);
        year = findViewById(R.id.year_RMain);

        name.setText(getIntent().getStringExtra(EXTRA_PERSON_NAME));
        dept.setText(getIntent().getStringExtra(EXTRA_PERSON_DEPT));
        year.setText(getIntent().getStringExtra(EXTRA_PERSON_YEAR));

    }

    public static Intent newIntent(Context context, String name, String dept, String year){
        Intent intent = new Intent(context, RightActivity2.class);
        intent.putExtra(EXTRA_PERSON_NAME, name);
        intent.putExtra(EXTRA_PERSON_DEPT, dept);
        intent.putExtra(EXTRA_PERSON_YEAR, year);
        return intent;
    }

}
