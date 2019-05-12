package com.example.popov41pilab04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity { //loginActivity
    private EditText nameField;
    private EditText surnameField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nameField = findViewById(R.id.nameField);
        surnameField = findViewById(R.id.surnameField);
    }

    public void OnLogInClick(android.view.View view)
    {
        String name = nameField.getText().toString();
        String surname = surnameField.getText().toString();
        if(name.length() <= 1) {
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            return;
        }
        if(surname.length() <= 1) {
            Toast.makeText(this, surname, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, FeedbackActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("surname", surname);
        startActivity(intent);
    }
}
