package com.example.assng4;

import android.content.Context;
import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TeamMainActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 100;
    String picturePath;

    public static String EXTRA_CITY = "com.team.city";
    public static String EXTRA_NAME = "com.team.name";
    public static String EXTRA_SPORT = "com.team.sport";
    public static String EXTRA_MVP = "com.team.mvp";
    public static String EXTRA_STADIUM = "com.team.stadium";
    public static String EXTRA_ID = "com.team.id";

    private EditText cityEdit;
    private EditText nameEdit;
//    private EditText sportEdit;
    private EditText mvpEdit;
//    private EditText stadiumEdit;

    private Spinner sportSpnr;
    private Button updateBtn;
    private Button deleteBtn;
    private Button exitBtn;
    private Button uploadImgBtn;
    private ImageView imageView;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);

        myDb = new DatabaseHelper(this);

        cityEdit = findViewById(R.id.city);
        nameEdit = findViewById(R.id.name);
        sportSpnr = findViewById(R.id.sport);
        mvpEdit = findViewById(R.id.mvp);
        imageView = findViewById(R.id.image);

        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        exitBtn = findViewById(R.id.exitBtn);
        uploadImgBtn = findViewById(R.id.imageBtn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportSpnr.setAdapter(adapter);

        cityEdit.setText(getIntent().getStringExtra(EXTRA_CITY));
        nameEdit.setText(getIntent().getStringExtra(EXTRA_NAME));
        sportSpnr.setSelection(Integer.parseInt(getIntent().getStringExtra(EXTRA_SPORT)));
        mvpEdit.setText(getIntent().getStringExtra(EXTRA_MVP));
        imageView.setImageBitmap(base64ToBitmap(getIntent().getStringExtra(EXTRA_STADIUM)));



        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb = new DatabaseHelper(getApplicationContext());

                if(myDb.updateData(getIntent().getStringExtra(EXTRA_ID),
                                cityEdit.getText().toString(),
                                nameEdit.getText().toString(),
                                String.valueOf(sportSpnr.getSelectedItemPosition()),
                                mvpEdit.getText().toString(),
                                picturePath))
                    onBackPressed();

            }
        });

        uploadImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Gallery, RESULT_LOAD_IMAGE);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb = new DatabaseHelper(getApplicationContext());

                if(myDb.deleteData(getIntent().getStringExtra(EXTRA_ID)))
                    onBackPressed();
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            picturePath = bitmapToBase64(BitmapFactory.decodeFile(picturePath));
        }
    }

    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    private Bitmap base64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }


    public static Intent newIntent (Context context, String id, String city, String name,
                                    String sport, String mvp, String stadium) {
        Intent intent = new Intent(context, TeamMainActivity.class);
        intent.putExtra(EXTRA_ID, id);
        intent.putExtra(EXTRA_CITY, city);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_SPORT, sport);
        intent.putExtra(EXTRA_MVP, mvp);
        intent.putExtra(EXTRA_STADIUM, stadium);

        return intent;
    }
}

