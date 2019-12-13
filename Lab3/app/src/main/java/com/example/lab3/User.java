package com.example.lab3;


import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;


public class User extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        final Button homeButton = findViewById(R.id.homeInUsr);
        final TextView HiddenUsr = findViewById(R.id.hiddenUsr);

        /** Set UserName to MainActivity class*/
        HiddenUsr.setText(" "+SecureClass.ID);

        /** Set HomeButton to MainActivity class*/
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
