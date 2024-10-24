package com.zybooks.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteCourseActivity extends AppCompatActivity {

    private EditText courseTitleToDelete, courseStartDate, courseEndDate, instructorName;
    private Button deleteBtn, cancelBtn;
    private DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_course);
        setTitle("Delete Course");

        courseTitleToDelete = findViewById(R.id.deleteCourseCourseTitleEditText);
        courseStartDate = findViewById(R.id.deleteCourseCourseStartDateEditText);
        courseEndDate = findViewById(R.id.deleteCourseCourseEndDateEditText);
        instructorName = findViewById(R.id.deleteCourseInstructorNameEditText);
        deleteBtn = findViewById(R.id.deleteCourseDeleteBtn);
        cancelBtn = findViewById(R.id.deleteCourseCancelBtn);

        dbHandler = new DBHandler(DeleteCourseActivity.this);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseTitle = courseTitleToDelete.getText().toString();
                if (courseTitle.isEmpty()){
                    Toast.makeText(DeleteCourseActivity.this, "Please enter the course details ", Toast.LENGTH_SHORT).show();
                }

                dbHandler.deleteCourse(courseTitle);

                Toast.makeText(DeleteCourseActivity.this, "Your course was deleted", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DeleteCourseActivity.this, CourseActivity.class);
                startActivity(intent);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeleteCourseActivity.this, CourseActivity.class);
                startActivity(intent);
            }
        });



    }
}