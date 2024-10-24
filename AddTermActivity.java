package com.zybooks.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTermActivity extends AppCompatActivity {




    private EditText termToEnroll, termStartDate, termEndDate;
    private Button enrollBtn, cancelBtn;
    public DBHandler dbHandler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        setTitle("Add Terms");

        termToEnroll = findViewById(R.id.editTermEntryEditText);
        termStartDate = findViewById(R.id.editTermEnterStartDateEditText);
        termEndDate = findViewById(R.id.editTermEnterEndDateEditText);
        enrollBtn = findViewById(R.id.editTermUpdateBtn);
        cancelBtn = findViewById(R.id.editTermCancelBtn);


      //  dbHandler = new DBHandler(AddTermActivity.this);

        enrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String termTitle = termToEnroll.getText().toString();
                String startDate = termStartDate.getText().toString();
                String endDate = termEndDate.getText().toString();

                if (termTitle.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
                    Toast.makeText(AddTermActivity.this, "Please Enter Missing Information", Toast.LENGTH_SHORT).show();
                    return;
                }



                dbHandler = new DBHandler(AddTermActivity.this);


                dbHandler.addNewTerm(termTitle, startDate, endDate);


                Toast.makeText(AddTermActivity.this,"Your Term Was Added", Toast.LENGTH_LONG).show();

                termToEnroll.setText("");
                termStartDate.setText("");
                termEndDate.setText("");



                Intent intent = new Intent(AddTermActivity.this, TermActivity.class);
                startActivity(intent);




            }


        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTermActivity.this, TermActivity.class);
                startActivity(intent);
            }
        });





    }
}