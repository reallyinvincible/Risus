package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public interface JokeLoadInterface{
        void loadCompleted();
    }

    JokeLoadInterface jokeLoadInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jokeLoadInterface = new JokeLoadInterface() {
            @Override
            public void loadCompleted() {
                findViewById(R.id.loading_screen_container).setVisibility(View.INVISIBLE);
            }
        };

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        findViewById(R.id.loading_screen_container).setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute(new Pair<Context, JokeLoadInterface>(this, getJokeLoadInterface()));
    }

    public JokeLoadInterface getJokeLoadInterface() {
        return jokeLoadInterface;
    }
}
