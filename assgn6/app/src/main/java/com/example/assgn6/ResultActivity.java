package com.example.assgn6;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    ListView ListView;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        QuakeAsyncTask task = new QuakeAsyncTask();
        task.execute(getIntent().getStringExtra("_url"));
    }

    class QuakeAsyncTask extends AsyncTask<String, Void, List<String>> {
        @Override
        protected List<String> doInBackground(String... stringURL) {
            return Utils.fetchEarthquakeData(stringURL[0]);
        }

        @Override
        protected void onPostExecute(final List<String> postExecuteResult) {
//            super.onPostExecute(strings);
            CustomAdapter customAdapter = new CustomAdapter(ResultActivity.this, postExecuteResult);
            ListView = findViewById(R.id.listView);
            ListView.setAdapter(customAdapter);

            if(postExecuteResult.size() == 0)
                Toast.makeText(ResultActivity.this, "NO EARTHQUAKES", Toast.LENGTH_LONG).show();

            ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    List<String> quakeList = new ArrayList<>();
                    quakeList = postExecuteResult;
                    String earthInfo[] = quakeList.get(position).split("@@");
                    // String url = earthInfo[3];
                    String lng = earthInfo[4];
                    String lat = earthInfo[5];
                    String _mapURL = "https://www.openstreetmap.org/?mlat="+lat+"&mlon="+lng+"#map=5/"+lat+"/"+lng;

                    Intent intent = new Intent(ResultActivity.this, WebViewActivity.class);
                    intent.putExtra("MY_KEY", _mapURL);
                    startActivity(intent);

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
