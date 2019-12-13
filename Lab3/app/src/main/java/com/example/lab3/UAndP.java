package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UAndP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uand_p);

        final Button homeButton = findViewById(R.id.homeInUsrAndPw);
        final TextView HiddenUsr = findViewById(R.id.hiddenUsr);
        final TextView HiddenPwd = findViewById(R.id.hiddenPwd);

        /** Set UserName to MainActivity class*/
//        HiddenUsr.setText(" " + SecureClass.ID);
//        HiddenPwd.setText(" " + SecureClass.PW);
        Intent intent = getIntent();
        HiddenUsr.setText(intent.getStringExtra("User"));
        HiddenPwd.setText(intent.getStringExtra("Password"));

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
