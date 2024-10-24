package com.zybooks.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteAssessmentActivity extends AppCompatActivity {

    //private EditText assTitleToDelete, assEndDate, assType;

    private EditText title, endDate, type;
    private DBHandler dbHandler;

    private Button deleteBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_assessment);
        setTitle("Delete Assessments");

        title = findViewById(R.id.deleteAssessmentAssessmentTitleEditText);
        endDate = findViewById(R.id.deleteAssessmentEndDateEditText);
        type = findViewById(R.id.deleteAssessmentTypeEditText);

        deleteBtn = findViewById(R.id.deleteAssessmentDeleteBtn);
        cancelBtn = findViewById(R.id.deleteAssessmentCancelBtn);

        dbHandler = new DBHandler(DeleteAssessmentActivity.this);


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String assessmentTitle = title.getText().toString();
                String assessmentEndDate = endDate.getText().toString();
                String assessmentType = type.getText().toString();

                if (assessmentTitle.isEmpty() && assessmentEndDate.isEmpty() && assessmentType.isEmpty()) {
                    Toast.makeText(DeleteAssessmentActivity.this, "Please enter the assessment details", Toast.LENGTH_SHORT).show();
                }

                dbHandler.deleteAssessment(assessmentTitle);
                Toast.makeText(DeleteAssessmentActivity.this, "Your assessment has been deleted", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DeleteAssessmentActivity.this, AssessmentActivity.class);
                startActivity(intent);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeleteAssessmentActivity.this, AssessmentActivity.class);
                startActivity(intent);
            }
        });
    }
}