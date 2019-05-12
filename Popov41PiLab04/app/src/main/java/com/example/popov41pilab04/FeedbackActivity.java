package com.example.popov41pilab04;

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
        Intent intent = getIntent();

        int specialisationStringId = getResources().getIdentifier("specialisation", "string", getPackageName());
        outStrId = getResources().getIdentifier("outStr", "string", getPackageName());
        name = intent.getStringExtra("name");
        surname = intent.getStringExtra("surname");
        TextView textView = findViewById(R.id.textView2);
        devOpsCheck = findViewById(R.id.devOpsCheck);
        qaCheck = findViewById(R.id.qaCheck);
        qaSpinner = findViewById(R.id.QAspinner);
        devOpsSpinner = findViewById(R.id.DevOpsSpinner);
        if(name == null || name.isEmpty()) {
            name = getResources().getString(getResources().getIdentifier("student", "string", getPackageName()));
        }
        checkBoxes[0] =  findViewById(R.id.checkBox);
        checkBoxes[1] =  findViewById(R.id.checkBox2);
        checkBoxes[2] =  findViewById(R.id.checkBox3);
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
        Intent intent = new Intent(this, ReceiveActivity.class);
        intent.putExtra("outStr", outStr);
        startActivity(intent);
    }
}
