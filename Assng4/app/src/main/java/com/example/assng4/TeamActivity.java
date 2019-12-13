package com.example.assng4;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 100;

    DatabaseHelper myDb;
    String picturePath;

    public static String EXTRA_CITY = "com.team.city";
    public static String EXTRA_NAME = "com.team.name";
    public static String EXTRA_SPORT = "com.team.sport";
    public static String EXTRA_MVP = "com.team.mvp";
    public static String EXTRA_STADIUM = "com.team.stadium";
    public static int REQUEST_CODE_ADDED = 2;

    private EditText cityEdit;
    private EditText nameEdit;
//    private EditText sportEdit;
    private EditText mvpEdit;
//    private EditText stadiumEdit;
    private Button uploadImgBtn;
    private Button submitBtn;
    private Button exitBtn;
    private Spinner sportSpnr;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCompat.requestPermissions(TeamActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);

        setContentView(R.layout.add_team_main);

        myDb = new DatabaseHelper(this);
        cityEdit = findViewById(R.id.city);
        nameEdit = findViewById(R.id.name);
//        sportEdit = findViewById(R.id.sport);
        sportSpnr = findViewById(R.id.sport);
        mvpEdit = findViewById(R.id.mvp);

        imageView = findViewById(R.id.imgUploaded);

        uploadImgBtn = findViewById(R.id.uploadImgBtn);
        submitBtn = findViewById(R.id.submitBtn);
        exitBtn = findViewById(R.id.exitBtn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportSpnr.setAdapter(adapter);

        uploadImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Gallery, RESULT_LOAD_IMAGE);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = cityEdit.getText().toString(),
                        name = nameEdit.getText().toString(),
                        sport = String.valueOf(sportSpnr.getSelectedItemPosition()),
                        mvp = mvpEdit.getText().toString(),
                        Base64 = bitmapToBase64(BitmapFactory.decodeFile(picturePath));

                if(city.equals("") || name.equals(""))
                    Toast.makeText(TeamActivity.this, "City and Name are required" , Toast.LENGTH_SHORT).show();
                else {
//                    teams.add(new Team(city, name, sport, mvp, stadium));
                    boolean isInserted = myDb.insertData(city, name, sport, mvp, Base64);

                    if(isInserted) {
                        getIntent().putExtra(EXTRA_CITY, city);
                        getIntent().putExtra(EXTRA_NAME, name);
                        getIntent().putExtra(EXTRA_SPORT, sport);
                        getIntent().putExtra(EXTRA_MVP, mvp);
                        getIntent().putExtra(EXTRA_STADIUM, Base64);

//                        Intent data = getIntent();
//                        Toast.makeText(TeamActivity.this, getCity(data) + getName(data), Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(TeamActivity.this, "not inserted" , Toast.LENGTH_SHORT).show();
                    clear();
                }
                setResult(RESULT_OK, getIntent());
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeamActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

//    Choose Image from Gallery and Display in Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE) {
            //&& resultCode == RESULT_OK && null != data
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.imgUploaded);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    /**
     * reduces the size of the image
     * @param image
     * @param maxSize
     * @return
     */
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static Intent newIntent (Context context) {
        Intent intent = new Intent(context, TeamActivity.class);
        return intent;
    }

    public void clear() {
        cityEdit.setText("");
        nameEdit.setText("");
        sportSpnr.setSelection(0);
        mvpEdit.setText("");
        imageView.setImageResource(R.drawable.image_not_found);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TeamActivity.this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(TeamActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
