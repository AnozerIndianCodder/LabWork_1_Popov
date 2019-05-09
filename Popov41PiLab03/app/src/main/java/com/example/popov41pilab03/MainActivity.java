package com.example.popov41pilab03;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    int seconds = 0;
    private boolean isRunning= false;

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        h2.postDelayed(run, 0);
    }

    public void OnStartClick(android.view.View view)
    {
        isRunning = true;
    }
    public void OnPauseClick(android.view.View view)
    {
        isRunning= false;
    }

    public void OnResetClick(android.view.View view)
    {
        isRunning= false;
        seconds = 0;
    }
    Handler h2 = new Handler();
    Runnable run = new Runnable() {
        @Override
        public void run() {
            int secs =seconds%60;
            int minutes = (seconds / 60)%60;
            int hours = (seconds / 360)%60;
            String s = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
            textView.setText(s);
            if(isRunning) {
                seconds++;
            }
            h2.postDelayed(this, 1000);
        }

    };
    @Override
    public void onPause() {
        super.onPause();
        h2.removeCallbacks(run);
    }
    @Override
    public void onResume() {
        super.onResume();
        h2.postDelayed(run, 0);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("isRunning", isRunning);
    }
}
