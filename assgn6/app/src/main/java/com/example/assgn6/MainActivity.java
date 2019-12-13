package com.example.assgn6;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    final String stringURL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2019-10-01&minmagnitude=6.0&limit=20&orderby=time";

    TextView DateText;
    Button SubmitBtn;
    Button StartDateBtn;
    Spinner Spinner;
    EditText NumOfQuakeEdit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        //----- Set up Spinner
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.types, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner.setAdapter(arrayAdapter);

        //----- Set up Calender
        StartDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To show current date in the datePicker
                Calendar mCurrentDate = Calendar.getInstance();
                int mYear = mCurrentDate.get(Calendar.YEAR);
                int mMonth = mCurrentDate.get(Calendar.MONTH);
                int mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Log.e("Date Selected",  " Year: " + year +"Month: " + month + " Day: " + dayOfMonth);
                        DateText.setText(year + "-" + (month+1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.setTitle("Select date");
                datePickerDialog.show();
            }
        });

        //----- Set up Submit Button
        //https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2019-10-01&minmagnitude=7.0&limit=20&orderby=time
        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mStartTime = DateText.getText().toString();
                String mLimit = NumOfQuakeEdit.getText().toString();
                String mOrderBy = (Spinner.getSelectedItem().toString().equals("date"))?"time":"magnitude";
//                String mOrderBy = Spinner.getSelectedItem().toString();

                Log.i("URL Transferred", "mStartTime: "+mStartTime
                        +" mLimit: "+mLimit+ " mOrderBy: "+mOrderBy);

                String URLStr = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime="+mStartTime
                        +"&minmagnitude=7.0&limit="+mLimit+"&orderby="+mOrderBy;

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("_url", URLStr);
                startActivity(intent);
            }
        });
    }


    public void init() {
        DateText = findViewById(R.id.dateText);
        SubmitBtn = findViewById(R.id.submitBtn);
        StartDateBtn = findViewById(R.id.startDateBtn);

        Spinner = findViewById(R.id.orderBySpinner);
        NumOfQuakeEdit = findViewById(R.id.numOfQuakeEdit);

        // Set up DateText to the current date
        Date current = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateText.setText(df.format(current));
    }
}


