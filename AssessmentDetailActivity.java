package com.zybooks.studentscheduler;

import static com.zybooks.studentscheduler.DBHandler.DB_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AssessmentDetailActivity extends AppCompatActivity {

    private EditText assessmentTitle, endDate, type, courseAssigned;
    private Button updateAssessmentBtn, cancelBtn;
    private DBHandler dbHandler;
    int assessCourseId = -1;
    SQLiteDatabase db;

    public String aAssessmentTitle;
    public String aAssessmentEndDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);
        setTitle("Update Assessment");

        db = openOrCreateDatabase(DB_NAME,MODE_PRIVATE, null );

        assessmentTitle = findViewById(R.id.assessmentDetailsAssessmentTitleEditText);
        endDate = findViewById(R.id.assessmentDetailEndDateEditText);
        type = findViewById(R.id.assessmentDetailTypeEditText);
        courseAssigned = findViewById(R.id.assessmentDetailCourseAssignedEditText);
        updateAssessmentBtn = findViewById(R.id.assessmentDetailUpdateBtn);
        cancelBtn = findViewById(R.id.assessmentDetailCancelBtn);


        String editAssessmentTitle = getIntent().getStringExtra("assessmentTitle");
        String editAssessmentEndDate = getIntent().getStringExtra("endDate");
        String editAssessmentType = getIntent().getStringExtra("assessmentType");
        String originalAssessmentName = getIntent().getStringExtra("assessmentTitle");

        assessmentTitle.setText(editAssessmentTitle);
        endDate.setText(editAssessmentEndDate);
        type.setText(editAssessmentType);

        aAssessmentTitle = editAssessmentTitle;
        aAssessmentEndDate = editAssessmentEndDate;


        updateAssessmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedAssessmentTitle = assessmentTitle.getText().toString();
                String updatedEndDate = endDate.getText().toString();
                String updatedType = type.getText().toString();
                String updatedCourseAssigned = courseAssigned.getText().toString();
               // String originalAssessmentName = assessmentTitle.getText().toString();
                String startDate = "NA";

                if (updatedAssessmentTitle.isEmpty() && updatedEndDate.isEmpty()){
                    Toast.makeText(AssessmentDetailActivity.this, "Please enter a Assessment Title and End Date", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler = new DBHandler(AssessmentDetailActivity.this);

                getSelectedCourseIdFromDataBase(updatedCourseAssigned);

                int  assessmentCourseId = assessCourseId;

                dbHandler.updateAssessment(originalAssessmentName, updatedAssessmentTitle, startDate, updatedEndDate,updatedType, assessmentCourseId);
                Toast.makeText(AssessmentDetailActivity.this, "Your Assessment has been updated", Toast.LENGTH_SHORT).show();

                assessmentTitle.setText("");
                endDate.setText("");
                type.setText("");
                courseAssigned.setText("");

                Intent intent = new Intent(AssessmentDetailActivity.this, AssessmentActivity.class);
                startActivity(intent);



            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssessmentDetailActivity.this, AssessmentActivity.class);
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
                    assessCourseId = cursor.getInt(0);
                } while (cursor.moveToNext());
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate menu for the app bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.update_assessment_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // Determine which app bar item was chosen
        switch (item.getItemId()) {
            case R.id.menu_reminder:
                Intent intentT = new Intent(AssessmentDetailActivity.this, AssessmentAlertActivity.class);
                intentT.putExtra("assessmentTitle", aAssessmentTitle);
                intentT.putExtra("assessmentEndDate", aAssessmentEndDate);
                startActivity(intentT);
                return true;

        }

        return true;
    }
}