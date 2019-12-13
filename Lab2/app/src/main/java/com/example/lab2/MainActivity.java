package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Set UserButton to UserName */
        final Button UsrButton = findViewById(R.id.usrButton);
        final EditText UsrName = findViewById(R.id.userName);

        UsrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsrButton.setText(UsrName.getText());
            }
        });

        /** Set PasswordButton to Password */
        final Button PwdButton = findViewById(R.id.pwButton);
        final EditText Pwd = findViewById(R.id.password);
        PwdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PwdButton.setText(Pwd.getText());
            }
        });

        /** Set UserButton And PasswordButton to UserName and Password */
        final Button UsrAndPwButton = findViewById(R.id.usrAndPwButton);
        UsrAndPwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsrAndPwButton.setText(UsrName.getText() + " and " + Pwd.getText());
            }
        });
    }
}
