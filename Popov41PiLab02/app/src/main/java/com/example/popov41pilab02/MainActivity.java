package com.example.popov41pilab02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
    }

    public void OnButtonClick(android.view.View view)
    {
        String s = editText.getText().toString();
//        Intent intent = new Intent(this, SecondActivity.class);
//        intent.putExtra("inputText", s);
//        startActivity(intent);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, s);
        startActivity(Intent.createChooser(intent, "Open via"));
    }
}
