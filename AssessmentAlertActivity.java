package com.zybooks.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AssessmentAlertActivity extends AppCompatActivity {

    private EditText alertAssessmentTitle, alertAssessmentEndDate;
    private Button assessmentSetAlertBtn, assessmentCancelAlertBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_alert);


        alertAssessmentTitle = findViewById(R.id.assessmentAlertTitleEditText);
        alertAssessmentEndDate = findViewById(R.id.assessmentAlertEndDateEditText);
        assessmentSetAlertBtn = findViewById(R.id.assessmentSetAlertBtn);
        assessmentCancelAlertBtn = findViewById(R.id.assessmentAlertCancelBtn);

        String sAssessmentTitle = getIntent().getStringExtra("assessmentTitle");
        String sAssessmentEndDate = getIntent().getStringExtra("assessmentEndDate");


        alertAssessmentTitle.setText(sAssessmentTitle);
        alertAssessmentEndDate.setText(sAssessmentEndDate);

        assessmentSetAlertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String alertEndDate = alertAssessmentEndDate.getText().toString();
                String dateFormat = "MM/DD/YY";
                SimpleDateFormat sdf = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    sdf = new SimpleDateFormat(dateFormat, Locale.US);
                }

                Date endDate = null;
                try {

                    endDate = sdf.parse(alertEndDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                Long triggerEnd = endDate.getTime();

                Intent intent = new Intent(AssessmentAlertActivity.this, AssessmentReceiver.class);
                intent.putExtra("Assessment", "Review your Assessment");
                PendingIntent sender = PendingIntent.getBroadcast(AssessmentAlertActivity.this, ++MainActivity.numAlert, intent, 0);
                AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, triggerEnd, sender);

                Intent i = new Intent(AssessmentAlertActivity.this, AssessmentActivity.class);
                startActivity(i);

            }
        });

        assessmentCancelAlertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssessmentAlertActivity.this, AssessmentActivity.class);
                startActivity(intent);
            }
        });


    }
}