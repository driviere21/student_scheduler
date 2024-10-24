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

public class CourseAlertActivity extends AppCompatActivity {

    private EditText alertCourseTitle, alertCourseStartDate, alertCourseEndDate;
    private Button courseSetAlertBtn, courseAlertCancelBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_alert);
        setTitle("Set Your Alert");

        alertCourseTitle = findViewById(R.id.courseAlertTitleEditText);
        alertCourseStartDate = findViewById(R.id.alertCourseStartDateEditText);
        alertCourseEndDate = findViewById(R.id.alertCourseEndDateEditText);
        courseSetAlertBtn = findViewById(R.id.courseSetAlertBtn);
        courseAlertCancelBtn = findViewById(R.id.courseAlertCancelBtn);


        String sCourseTitle = getIntent().getStringExtra("courseTitle");
        String sCourseStartDate = getIntent().getStringExtra("startDate");
        String sCourseEndDate = getIntent().getStringExtra("endDate");


        alertCourseTitle.setText(sCourseTitle);
        alertCourseStartDate.setText(sCourseStartDate);
        alertCourseEndDate.setText(sCourseEndDate);





        courseSetAlertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String alertStartDate = alertCourseStartDate.getText().toString();
                String alertEndDate = alertCourseEndDate.getText().toString();
                String dateFormat = "MM/DD/YY";
                SimpleDateFormat sdf = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    sdf = new SimpleDateFormat(dateFormat, Locale.US);
                }
                Date startDate = null;
                Date endDate = null;
                try {
                    startDate = sdf.parse(alertStartDate);
                    endDate = sdf.parse(alertEndDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long triggerStart = startDate.getTime();
                Long triggerEnd = endDate.getTime();

                Intent intent = new Intent(CourseAlertActivity.this, CourseReceiver.class);
                intent.putExtra("Course", "Review your Course");
                PendingIntent sender = PendingIntent.getBroadcast(CourseAlertActivity.this, ++MainActivity.numAlert, intent, 0);
                AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, triggerStart, sender);

                Intent i = new Intent(CourseAlertActivity.this, CourseActivity.class);
                startActivity(i);

            }



        });

        courseAlertCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseAlertActivity.this, CourseActivity.class);
                startActivity(intent);
            }
        });

    }
}