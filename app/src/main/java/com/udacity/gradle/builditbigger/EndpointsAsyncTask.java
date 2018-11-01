package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.reallyinvincible.jokedisplay.JokeDisplayActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import androidx.core.util.Pair;

class EndpointsAsyncTask extends AsyncTask<Pair<Context, MainActivity.JokeLoadInterface>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private MainActivity.JokeLoadInterface jokeLoadInterface;

    @Override
    protected String doInBackground(Pair<Context, MainActivity.JokeLoadInterface>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://android-risus.appspot.com/_ah/api/");
            myApiService = builder.build();
        }

        context = params[0].first;
        jokeLoadInterface = params[0].second;

        try {
            return myApiService.sayHi().execute().getData();
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(context, JokeDisplayActivity.class);
        intent.putExtra("Joke", result);
        jokeLoadInterface.loadCompleted();
        context.startActivity(intent);
    }
}
