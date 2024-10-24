package com.zybooks.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class AssessmentActivity extends AppCompatActivity {

    private ArrayList<Assessments> assessmentsArrayList;
    private DBHandler dbHandler;
    private AssessmentRVAdapter assessmentRVAdapter;
    private RecyclerView assessmentRV;

    private Button addAssessmentBtn, deleteAssessmentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        setTitle("Current Assessments");

        addAssessmentBtn = findViewById(R.id.assessmentAddAssessmentBtn);
        deleteAssessmentBtn = findViewById(R.id.assessmentDeleteAssessmentBtn);


        assessmentsArrayList = new ArrayList<>();
        dbHandler = new DBHandler(AssessmentActivity.this);

        assessmentsArrayList = dbHandler.readAssessment();

        assessmentRVAdapter = new AssessmentRVAdapter(assessmentsArrayList, AssessmentActivity.this);
        assessmentRV = findViewById(R.id.RVAssessments);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AssessmentActivity.this, RecyclerView.VERTICAL, false);
        assessmentRV.setLayoutManager(linearLayoutManager);

        assessmentRV.setAdapter(assessmentRVAdapter);



        addAssessmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssessmentActivity.this, AddAssessmentActivity.class);
                startActivity(intent);
            }
        });

        deleteAssessmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssessmentActivity.this, DeleteAssessmentActivity.class);
                startActivity(intent);
            }
        });
    }
}