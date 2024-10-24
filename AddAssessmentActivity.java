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

public class AddAssessmentActivity extends AppCompatActivity {

    private EditText assessmentTitle, endDate, type, courseAssigned;
    private Button addAssessmentBtn, cancelBtn;
    private DBHandler dbHandler;
    private int assessmentCourseId = -1;
    private String startDate = "NA";
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);
        setTitle("Add Assessments");

        db = openOrCreateDatabase(DB_NAME,MODE_PRIVATE, null );


        assessmentTitle = findViewById(R.id.addAssessmentAssessmentTitleEditText);
        endDate = findViewById(R.id.addAssessmentEnterDateEditText);
        type = findViewById(R.id.addAssessmentTypeEditText);
        courseAssigned = findViewById(R.id.addAssessmentCourseAssignedEditText);

        addAssessmentBtn = findViewById(R.id.addAssessmentAddBtn);
        cancelBtn = findViewById(R.id.addAssessmentCancelBtn);


        addAssessmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addAssessmentTitle = assessmentTitle.getText().toString();
                String addAssessmentEndDate = endDate.getText().toString();
                String course = courseAssigned.getText().toString();
                String assessmentType = type.getText().toString();





                if (addAssessmentTitle.isEmpty() && addAssessmentEndDate.isEmpty()) {
                    Toast.makeText(AddAssessmentActivity.this, "Please enter assessment information", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler = new DBHandler(AddAssessmentActivity.this);

                getSelectedCourseIdFromDataBase(course);

                int assessCourseId = assessmentCourseId;

                dbHandler.addNewAssessment(addAssessmentTitle, startDate, addAssessmentEndDate,assessmentType, assessCourseId );
                Toast.makeText(AddAssessmentActivity.this, "Your assessment has been added", Toast.LENGTH_SHORT).show();

                assessmentTitle.setText("");
                endDate.setText("");
                type.setText("");
                courseAssigned.setText("");

                Intent intent = new Intent(AddAssessmentActivity.this, AssessmentActivity.class);
                startActivity(intent);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddAssessmentActivity.this, AssessmentActivity.class);
                startActivity(intent);
            }
        });


    }

    private void getSelectedCourseIdFromDataBase(String courseTitle) {

        String sql = "SELECT courseId FROM courses WHERE courseTitle = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{courseTitle});
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    assessmentCourseId = cursor.getInt(0);
                } while (cursor.moveToNext());
            }

        }
    }
}