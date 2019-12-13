package com.example.lab3;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class Password extends User {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        Button homeButton = findViewById(R.id.homeInPw);
        TextView HiddenPwd = findViewById(R.id.hiddenPwd);

        HiddenPwd.setText(" " + SecureClass.PW);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                openMainActivity();
            }
        });
    }
}
