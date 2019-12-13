package com.example.assgn5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String stringURL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2019-10-01&minmagnitude=6.0&limit=20&orderby=time";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        QuakeAsyncTask task = new QuakeAsyncTask();
        task.execute(stringURL);

    }

    class QuakeAsyncTask extends AsyncTask<String, Void, List<String>> {
        @Override
        protected List<String> doInBackground(String... stringUrl) {
             return Utils.fetch(stringUrl[0]);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(final List<String> postExecuteResult) {
//            super.onPostExecute(postExecuteResult);
            CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, postExecuteResult);
            listView.setAdapter(customAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    List<String> quakeList = new ArrayList<>();
                    quakeList = postExecuteResult;
                    String earthInfo[] = quakeList.get(position).split("@@");

                    String _url = earthInfo[2];

                    Intent i = new Intent(MainActivity.this, webview.class);
                    i.putExtra("MY_KEY", _url);
                    startActivity(i);
                }
            });
        }

    }

    public void init() {
        listView = findViewById(R.id.list_item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
