package com.example.lab7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LeftDatabaseHelper myDbL;
    RightDatabaseHelper myDbR;
    List<Person> personL = new ArrayList<>();
    List<Person> personR = new ArrayList<>();


    private EditText nameL;
    private EditText deptL;
    private EditText yearL;
    private EditText nameR;
    private EditText deptR;
    private EditText yearR;

    private Button addBtnL;
    private Button addBtnR;
    private Button viewBtn;

    private ListView listViewL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDbL = new LeftDatabaseHelper(this);
        myDbR = new RightDatabaseHelper(this);

        /** initialize */
        addBtnL = findViewById(R.id.l_add);
        addBtnR = findViewById(R.id.r_add);
        viewBtn = findViewById(R.id.view);

        nameL = findViewById(R.id.l_name);
        deptL = findViewById(R.id.l_dept);
        yearL = findViewById(R.id.l_year);
        nameR = findViewById(R.id.r_name);
        deptR = findViewById(R.id.r_dept);
        yearR = findViewById(R.id.r_year);

        listViewL = findViewById(R.id.leftView);

        addBtnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameL.getText().toString(),
                        dept = deptL.getText().toString(),
                        year = yearL.getText().toString();

                if (name.matches("") || dept.matches("") || year.matches(""))
                    Toast.makeText(MainActivity.this, "Incomplete" , Toast.LENGTH_SHORT).show();
                else {
                    /** Make instance of Person */
                    personL.add(new Person(name, dept, year));

                    boolean isInserted = myDbL.insertData(name, dept, year);
                    if (!isInserted)
                        Toast.makeText(MainActivity.this, "Should not appear" , Toast.LENGTH_LONG).show();
                    clearL();
                }
            }
        });

        addBtnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameR.getText().toString(),
                        dept = deptR.getText().toString(),
                        year = yearR.getText().toString();

                if (name.matches("") || dept.matches("") || year.matches(""))
                    Toast.makeText(MainActivity.this, "Incomplete" , Toast.LENGTH_SHORT).show();
                else {
                    /** Make instance of Person */
                    personR.add(new Person(name, dept, year));

                    boolean isInserted = myDbR.insertData(name, dept, year);
                    if (!isInserted)
                        Toast.makeText(MainActivity.this, "Should not appear" , Toast.LENGTH_LONG).show();
                    clearR();
                }
            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> personStringL = new ArrayList<>();
                ArrayList<String> personStringR = new ArrayList<>();
                for (int i = 0; i < personL.size() ; i++) {
                    personStringL.add(3*i, personL.get(i).getName());
                    personStringL.add(3*i + 1, personL.get(i).getDept());
                    personStringL.add(3*i + 2, personL.get(i).getYear());
                }

                for (int i = 0; i < personR.size() ; i++) {
                    personStringR.add(3*i, personR.get(i).getName());
                    personStringR.add(3*i + 1, personR.get(i).getDept());
                    personStringR.add(3*i + 2, personR.get(i).getYear());
                }
                Intent intent = ListActivity.newIntent(MainActivity.this, personStringL, personStringR);
                startActivityForResult(intent, 0);
            }
        });

    }

    public void clearL () {
        nameL.setText("");
        deptL.setText("");
        yearL.setText("");
    }

    public void clearR () {
        nameR.setText("");
        deptR.setText("");
        yearR.setText("");
    }
}
