package com.example.finalproject_joshromney;

import com.example.finalproject_joshromney.db.Activity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetActivities extends AsyncTask<String, Integer, String> {

    private String rawJSON;

    private OnActivityListImport listener;

    public interface OnActivityListImport
    {
        void completedActivityList(Activity activities);
    }

    public void setOnActivityListImportListener(OnActivityListImport listenerFromMain)
    {
        listener = listenerFromMain;
    }


    @Override
    protected String doInBackground(String... strings) {
        try
        {
            URL url = new URL("https://www.boredapi.com/api/activity/");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.connect();

            int status = connection.getResponseCode();

            switch (status)
            {
                case 200:
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    rawJSON = bufferedReader.readLine();

                    Log.d("GetActivities", "doInBackground: " + rawJSON.toString());

                    break;
            }

        }
        catch  (Exception e)
        {
            Log.d("GetActivities", "doInBackground" + e.toString());
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Activity activities;
        try
        {
            activities = parseJson();
            listener.completedActivityList(activities);
        }
        catch (Exception e)
        {
            Log.d("GetActivities", "doPostExecute: " + e.toString());

        }

        super.onPostExecute(s);

    }


    private Activity parseJson()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Activity activities = null;

        try
        {
            activities = gson.fromJson(rawJSON, Activity.class);

            Log.d("GetAct", "parseJson: " + activities.toString());

        }catch (Exception e)
        {
            Log.d("GetAct", "parseJson: " + e.toString());

        }

        return activities;
    }
}
