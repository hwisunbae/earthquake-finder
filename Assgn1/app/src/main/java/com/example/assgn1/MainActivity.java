package com.example.assgn1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String str[] = new String[] {"", "", ""};
    TextView world, seneca;
    RadioGroup rg1, rg2;
    RadioButton rb1, rb2;
    Button b1, b2, colours;
    CheckBox c1, c2, c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Initialize */
        world = (TextView) findViewById(R.id.world);
        seneca = (TextView) findViewById(R.id.seneca);

        rg1 = (RadioGroup) findViewById(R.id.BonjourGroup);
        rg2 = (RadioGroup) findViewById(R.id.HiGroup);

        b1 = (Button) findViewById(R.id.bonjourButton);
        b2 = (Button) findViewById(R.id.hiButton);
        colours = (Button) findViewById(R.id.colours);

        c1 = (CheckBox) findViewById(R.id.red);
        c2 = (CheckBox) findViewById(R.id.green);
        c3 = (CheckBox) findViewById(R.id.blue);

        /** Set text for Radio Group when button is clicked**/
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonId = rg1.getCheckedRadioButtonId();
                rb1 = (RadioButton) findViewById(radioButtonId);

                if (radioButtonId < 0) ;
                else  world.setText(rb1.getText().toString());
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int radioButtonId = rg2.getCheckedRadioButtonId();
                rb2 = (RadioButton) findViewById(radioButtonId);

                if (radioButtonId < 0) ;
                else seneca.setText(rb2.getText().toString());
            }
        });

        /** Toast only if check box is checked */
        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    Toast.makeText(MainActivity.this, c1.getText().toString(), Toast.LENGTH_SHORT).show();
                    str[0] = " " + c1.getText().toString();
                }
                else
                    str[0] = "";

            }
        });

        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    Toast.makeText(MainActivity.this, c2.getText().toString(), Toast.LENGTH_SHORT).show();
                    str[1] = " " + c2.getText().toString();
                }
                else
                    str[1] = "";
            }
        });

        c3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    Toast.makeText(MainActivity.this, c3.getText().toString(), Toast.LENGTH_SHORT).show();
                    str[2] = " " + c3.getText().toString();
                }
                else
                    str[2] = "";
            }
        });

        colours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, str[0]+str[1]+str[2], Toast.LENGTH_SHORT).show();
            }
        });

    }


    /** Make a toast for Radio Group **/
    public void bgclick (View v){
        int radioButtonId = rg1.getCheckedRadioButtonId();
        rb1 = (RadioButton) findViewById(radioButtonId);

        Toast.makeText(MainActivity.this, rb1.getText(), Toast.LENGTH_SHORT).show();
    }

    public void hgclick (View v) {
        int radioButtonId = rg2.getCheckedRadioButtonId();
        rb2 = (RadioButton) findViewById(radioButtonId);
        Toast.makeText(MainActivity.this, rb2.getText(), Toast.LENGTH_SHORT).show();
    }
}
