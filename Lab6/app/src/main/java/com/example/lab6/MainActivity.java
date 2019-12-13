package com.example.lab6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    private EditText idText;
    private EditText nameText;
    private EditText markText;

    private Button addButton;
    private Button viewButton;
    private Button findButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        /** initialize */
        idText = findViewById(R.id.id);
        nameText = findViewById(R.id.name);
        markText = findViewById(R.id.marks);

        addButton = findViewById(R.id.add_student);
        viewButton = findViewById(R.id.view_students);
        findButton = findViewById(R.id.find_student);
        deleteButton = findViewById(R.id.delete_student);

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String _id = idText.getText().toString(),
                        _name = nameText.getText().toString(),
                        _mark = markText.getText().toString();

                if (_id.matches("") || _name.matches("") || _mark.matches(""))
                    Toast.makeText(MainActivity.this, "Incomplete" , Toast.LENGTH_SHORT).show();

                else {
                    boolean isInserted = myDb.insertData(_id, _name, _mark);
                    if (isInserted == true)
                        showBuilder("The following student was added",
                                "ID:" + _id + " Name:" + _name + " Marks:" + _mark);
                    else
                        Toast.makeText(MainActivity.this, "Should not appear" , Toast.LENGTH_LONG).show();
                    clear();
                }
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0)
                    showBuilder("No students were found", null);

                else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("ID:" + res.getString(0) + " ");
                        buffer.append("Name:" + res.getString(1) + " ");
                        buffer.append("Marks:" + res.getString(2) + "\n");
                    }
                    if (res.getCount() == 1)
                        showBuilder("The following student has been added" , buffer.toString());
                    else
                        showBuilder("The following students have been added" , buffer.toString());
                }
                res.close();
            }
        });

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getData(idText.getText().toString());
                if (res.getCount() == 0)
                    showBuilder("This student does not exist", null);
                else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("ID:" + res.getString(0) + " ");
                        buffer.append("Name:" + res.getString(1) + " ");
                        buffer.append("Marks:" + res.getString(2) + "\n");
                    }
                    if (res.getCount() == 1)
                        showBuilder("Student detail is as follows" , buffer.toString());
                    else
                        showBuilder("Student details are as follows" , buffer.toString());
                }
                res.close();
                clear();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = myDb.getData(idText.getText().toString());
                if (res.getCount() == 0)
                    showBuilder("This student does not exist", null);

                else {
                    boolean isDeleted = myDb.deleteData(idText.getText().toString());
                    if (isDeleted == true) {
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID:" + res.getString(0) + " ");
                            buffer.append("Name:" + res.getString(1) + " ");
                            buffer.append("Marks:" + res.getString(2) + "\n");
                        }
                        showBuilder("The following student has been deleted" , buffer.toString());
                    }
                    else
                        Toast.makeText(MainActivity.this, "Should not appear" , Toast.LENGTH_LONG).show();
                }
                res.close();
                clear();
            }
        });
    }

    public void clear () {
        idText.setText("");
        nameText.setText("");
        markText.setText("");
    }

    public void showBuilder (String title, String Msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true)
                .setTitle(title)
                .setMessage(Msg)
                .setNeutralButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        builder.show();


    }
}
