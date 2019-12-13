package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Send UserName to User.xml */
        final Button UsrButton = findViewById(R.id.usrButton);
        final EditText UsrName = findViewById(R.id.userName);

        UsrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecureClass.ID = UsrName.getText().toString();
                Intent intent = new Intent(MainActivity.this, User.class);
                startActivity(intent);
            }
        });

        /** Send Password to Password.xml */
        final Button PwdButton = findViewById(R.id.pwButton);
        final EditText Pwd = findViewById(R.id.password);
        PwdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecureClass.PW = Pwd.getText().toString();
                Intent intent = new Intent(MainActivity.this, Password.class);
                startActivity(intent);
            }
        });

        /** Send UserButton And PasswordButton to UAndP.xml */
        final Button UsrAndPwButton = findViewById(R.id.usrAndPwButton);
        UsrAndPwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UAndP.class);
                intent.putExtra("User", " " + UsrName.getText().toString());
                intent.putExtra("Password", " " +  Pwd.getText().toString());
                startActivity(intent);
            }
        });
    }
}
