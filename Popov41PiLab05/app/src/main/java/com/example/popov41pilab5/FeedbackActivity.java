package com.example.popov41pilab5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class FeedbackActivity extends AppCompatActivity {
    private String name, surname;
    private RadioButton devOpsCheck,qaCheck;
    private Spinner qaSpinner,devOpsSpinner;
    private CheckBox[] checkBoxes = new CheckBox[3];

    private int outStrId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feedback);

        TextView textView = findViewById(R.id.textView2);
        devOpsCheck = findViewById(R.id.devOpsCheck);
        qaCheck = findViewById(R.id.qaCheck);
        qaSpinner = findViewById(R.id.QAspinner);
        devOpsSpinner = findViewById(R.id.DevOpsSpinner);
        checkBoxes[0] =  findViewById(R.id.checkBox);
        checkBoxes[1] =  findViewById(R.id.checkBox2);
        checkBoxes[2] =  findViewById(R.id.checkBox3);

        if(savedInstanceState != null){
            name = savedInstanceState.getString("name");
            surname = savedInstanceState.getString("surname");
            qaCheck.setChecked(savedInstanceState.getBoolean("qaChecked"));
            devOpsCheck.setChecked(!savedInstanceState.getBoolean("qaChecked"));
            for (int i = 0; i< checkBoxes.length;i++){
                checkBoxes[i].setChecked(savedInstanceState.getBoolean(i+"checkBox"));
            }
            devOpsSpinner.setSelection(savedInstanceState.getInt("devOpsSpinnerPos"));
            qaSpinner.setSelection(savedInstanceState.getInt("qaSpinnerPos"));
        }
        else {
            Intent intent = getIntent();
            name = intent.getStringExtra("name");
            surname = intent.getStringExtra("surname");
        }

        int specialisationStringId = getResources().getIdentifier("specialisation", "string", getPackageName());
        outStrId = getResources().getIdentifier("outStr", "string", getPackageName());


        if(name == null || name.isEmpty()) {
            name = getResources().getString(getResources().getIdentifier("student", "string", getPackageName()));
        }
        textView.setText(String.format(getResources().getString(specialisationStringId), name));


    }

    public void OnRadioButtonClick(android.view.View view)
    {
        if(devOpsCheck.isChecked())
        {
            devOpsSpinner.setVisibility(View.VISIBLE);
            qaSpinner.setVisibility(View.INVISIBLE);
        }
        else {

            devOpsSpinner.setVisibility(View.INVISIBLE);
            qaSpinner.setVisibility(View.VISIBLE);
        }
    }
    public void OnSendClick(android.view.View view) {
        String sp = "";
        if(devOpsCheck.isChecked())
        {
            sp = devOpsCheck.getText().toString();
        }
        else
        {
            sp = qaCheck.getText().toString();
        }
        String activities = "";
        for (CheckBox checkBox: checkBoxes){
            if(activities.length()>0) activities +=", ";
            if(checkBox.isChecked())
            {
                activities+=checkBox.getText().toString();
            }
        }
        String subj = "";
        if(devOpsCheck.isChecked())
        {
            subj = getResources().getStringArray(getResources().getIdentifier("subjectsDevOps",
                    "array", getPackageName()))[devOpsSpinner.getSelectedItemPosition()];
        }
        else {
            subj = getResources().getStringArray(getResources().getIdentifier("subjectsQA",
                    "array", getPackageName()))[qaSpinner.getSelectedItemPosition()];

        }
        String outStr = String.format(getResources().getString(outStrId),name,surname,sp,activities,subj);
//        Intent intent = new Intent(this, ReceiveActivity.class);
//        intent.putExtra("outStr", outStr);
//        startActivity(intent);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, outStr);
        intent.putExtra(Intent.EXTRA_EMAIL, "somebody@somewhere.www");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Learning navigator");
        startActivity(Intent.createChooser(intent, "Send via"));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", name);
        outState.putString("surname", surname);
        outState.putBoolean("qaChecked", qaCheck.isChecked());
        for (int i = 0; i< checkBoxes.length;i++){
            outState.putBoolean(i+"checkBox", checkBoxes[i].isChecked());
        }
        outState.putInt("devOpsSpinnerPos",devOpsSpinner.getSelectedItemPosition());
        outState.putInt("qaSpinnerPos",qaSpinner.getSelectedItemPosition());

    }
    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public void onResume() {
        super.onResume();
    }

}
