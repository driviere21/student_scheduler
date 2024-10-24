package com.zybooks.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteTermActivity extends AppCompatActivity {

    private EditText termTitleToDelete, startDate, endDate;
    private Button deleteBtn, cancelBtn;
    private DBHandler dbHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_term);
        setTitle("Delete Term");

        termTitleToDelete = findViewById(R.id.deleteTermTitleEditText);
        startDate = findViewById(R.id.deleteTermStartDateEditText);
        endDate = findViewById(R.id.deleteTermEndDateEditText);
        deleteBtn = findViewById(R.id.deleteTermDeleteBtn);
        cancelBtn = findViewById(R.id.deleteTermCancelBtn);

        dbHandler = new DBHandler(DeleteTermActivity.this);


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String termTitle = termTitleToDelete.getText().toString();

                if(termTitle.isEmpty()){
                    Toast.makeText(DeleteTermActivity.this, "Please enter the Term Title", Toast.LENGTH_SHORT).show();
                    return;

                }


                dbHandler.deleteTerm(termTitle);

                Toast.makeText(DeleteTermActivity.this, "If the term was NOT deleted (Still Visible), there are course(s) assigned. Else Term Deleted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DeleteTermActivity.this, TermActivity.class);
                startActivity(intent);


            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeleteTermActivity.this, TermActivity.class);
                startActivity(intent);
            }
        });
    }
}