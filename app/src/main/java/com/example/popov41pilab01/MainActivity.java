package com.example.popov41pilab01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextView textView;
    private int profDescId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        textView = findViewById(R.id.textView);
        profDescId = getResources().getIdentifier("professions_descriptions", "array", getPackageName());
    }
    public void getDescription(android.view.View view)
    {
        int i = spinner.getSelectedItemPosition();
        String description = getResources().getStringArray(profDescId)[i];
        textView.setText(description);
    }
}
