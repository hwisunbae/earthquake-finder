package com.example.assgn6;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public final class Utils {
    public  static final String LOG_TAG = Utils.class.getSimpleName();

    public static List<String> fetchEarthquakeData(String requestUrl){
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG,"Error closing input stream", e);
        }
        return extractFeatureFromJson(jsonResponse);
    }

    private static URL createUrl(String stringUrl){
        URL url = null;
        try{
            url = new URL(stringUrl);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG,"Error with create URL", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";
        if(url==null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if(urlConnection.getResponseCode()==200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }else{
                Log.e(LOG_TAG,"Error response code: "+urlConnection.getResponseCode());
            }
        }catch (IOException e){
            Log.e(LOG_TAG,"Problem retrieving the earthquake Json result. ", e);
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if(inputStream!= null){
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line!=null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<String> extractFeatureFromJson(String earthquakeJSON){
        if(TextUtils.isEmpty(earthquakeJSON)){
            return null;
        }
        List<String> quakeList = new ArrayList<>();
        try{
            JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);
            JSONArray earthquakeArray = baseJsonResponse.getJSONArray("features");

            for (int i = 0; i < earthquakeArray.length(); i++) {
                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);
                JSONObject properties = currentEarthquake.getJSONObject("properties");
                //-------------------------------------------------------------
                String title = properties.getString("title");
                //-------------------------------------------------------------
                Long time = properties.getLong("time");
                Date date = new Date(time*1L);
                SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                jdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
                String java_date = jdf.format(date);
                //-------------------------------------------------------------
                String url = properties.getString("url");
                //-------------------------------------------------------------
                String mag = properties.getString("mag");
                //-------------------------------------------------------------
                JSONObject geometry = currentEarthquake.getJSONObject("geometry");
                JSONArray coordinates = geometry.getJSONArray("coordinates");
                String log = coordinates.getString(0);
                String lat = coordinates.getString(1);

                quakeList.add(mag+"@@"+title+"@@"+ java_date+"@@"+url+"@@"+log+"@@"+lat);
            }
            Log.i(LOG_TAG, quakeList.toString());
            return quakeList;
        }catch (JSONException e){
            Log.e(LOG_TAG,"Problem with earthquake Json result", e);
        }
        return  null;
    }

}
