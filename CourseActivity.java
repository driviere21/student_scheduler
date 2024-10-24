package com.zybooks.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {

    private ArrayList<Courses> coursesArrayList;
    private DBHandler dbHandler;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    private Button addCourse, deleteCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);


        coursesArrayList = new ArrayList<>();
        dbHandler = new DBHandler(CourseActivity.this);

        coursesArrayList = dbHandler.readCourses();

        courseRVAdapter = new CourseRVAdapter(coursesArrayList, CourseActivity.this);
        coursesRV = findViewById(R.id.RVCourse);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CourseActivity.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        coursesRV.setAdapter(courseRVAdapter);

        addCourse = findViewById(R.id.courseAddCourseBtn);
        deleteCourse = findViewById(R.id.courseDeleteCourseBtn);




        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseActivity.this, AddCourseActivity.class);
                startActivity(intent);
            }
        });

        deleteCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseActivity.this, DeleteCourseActivity.class);
                startActivity(intent);
            }
        });

    }
}