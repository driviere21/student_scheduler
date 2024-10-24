package com.zybooks.studentscheduler;

import static com.zybooks.studentscheduler.DBHandler.DB_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddCourseActivity extends AppCompatActivity {

    SQLiteDatabase db;

    private EditText courseTitle, startDate, endDate, instructorName, instructorPhone, instructorEmail, termAssigned, notes, status;
    private Button addCourseBtn, cancelBtn;
    private DBHandler dbHandler;


    List<String> termsList;
    int courseTermId=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        setTitle("Add New Course");

        db = openOrCreateDatabase(DB_NAME,MODE_PRIVATE, null );
        termsList = new ArrayList<String>();


        courseTitle = findViewById(R.id.addCourseCourseTitleEditText);
        startDate = findViewById(R.id.addCourseCourseStartDateEditText);
        endDate = findViewById(R.id.addCourseCourseEndDateEditText);
        instructorName = findViewById(R.id.addCourseInstructorNameEditText);
        instructorPhone = findViewById(R.id.addCourseInstructorPhoneEditText);
        instructorEmail = findViewById(R.id.addCourseInstructorEmailEditText);
        termAssigned = findViewById(R.id.termAssignedEditText);
        status = findViewById(R.id.statusEditText);
        notes = findViewById(R.id.addCourseNoteEditText);
        addCourseBtn = findViewById(R.id.updateCourseBtn);
        cancelBtn = findViewById(R.id.addCourseCancelBtn);


        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseToEnroll = courseTitle.getText().toString();
                String courseStartDate = startDate.getText().toString();
                String courseEndDate = endDate.getText().toString();
                String courseStatus = status.getText().toString();
                String instructorNm = instructorName.getText().toString();
                String instructorEm = instructorEmail.getText().toString();
                String instructorTel = instructorPhone.getText().toString();
                String assignedTo = termAssigned.getText().toString();
                String addTermNotes = notes.getText().toString();

                getSelectedTermIdFromDataBase(courseToEnroll);


                if (courseToEnroll.isEmpty() && courseStartDate.isEmpty() && courseEndDate.isEmpty() && courseStatus.isEmpty() && instructorNm.isEmpty() && instructorEm.isEmpty() && instructorTel.isEmpty() && assignedTo.isEmpty()) {
                    Toast.makeText(AddCourseActivity.this, "Please enter all the information in this form", Toast.LENGTH_SHORT).show();
                    return;
                }


                dbHandler = new DBHandler(AddCourseActivity.this);

                getSelectedTermIdFromDataBase(assignedTo);

                int termId = courseTermId;


                dbHandler.addNewCourse(courseToEnroll, courseStartDate, courseEndDate, courseStatus, instructorNm, instructorTel, instructorEm, addTermNotes, termId);

                Toast.makeText(AddCourseActivity.this, "Your Course was added", Toast.LENGTH_SHORT).show();

                courseTitle.setText("");
                startDate.setText("");
                endDate.setText("");
                status.setText("");
                instructorName.setText("");
                instructorEmail.setText("");
                instructorPhone.setText("");
                termAssigned.setText("");
                notes.setText("");

                Intent intent = new Intent(AddCourseActivity.this, CourseActivity.class);
                startActivity(intent);

            }
        });


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCourseActivity.this, CourseActivity.class);
                startActivity(intent);
            }
        });


    }

    private void getSelectedTermIdFromDataBase(String termTitle) {

        String sql = "SELECT termId FROM terms WHERE termTitle = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{termTitle});
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    courseTermId = cursor.getInt(0);
                } while (cursor.moveToNext());
            }

        }
    }






    }



