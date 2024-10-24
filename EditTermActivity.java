package com.zybooks.studentscheduler;

import static com.zybooks.studentscheduler.DBHandler.DB_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditTermActivity extends AppCompatActivity {

    private EditText updateTermTitle, updateTermStartDate, updateTermEndDate;
    private Button updateTermBtn, cancelBtn;
    private DBHandler dbHandler;
    SQLiteDatabase db;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);
        setTitle("Update Term");


        db = openOrCreateDatabase(DB_NAME,MODE_PRIVATE, null );

        updateTermTitle = findViewById(R.id.editTermEntryEditText);
        updateTermStartDate = findViewById(R.id.editTermEnterStartDateEditText);
        updateTermEndDate = findViewById(R.id.editTermEnterEndDateEditText);
        updateTermBtn = findViewById(R.id.editTermUpdateBtn);
        cancelBtn = findViewById(R.id.editTermCancelBtn);

        String dTermTitle = getIntent().getStringExtra("termTitle");
        String dTermStartDate = getIntent().getStringExtra("startDate");
        String dTermEndDate = getIntent().getStringExtra("endDate");
        String originalTermTitle = getIntent().getStringExtra("termTitle");

        updateTermTitle.setText(dTermTitle);
        updateTermStartDate.setText(dTermStartDate);
        updateTermEndDate.setText(dTermEndDate);


        updateTermBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedTermTitle = updateTermTitle.getText().toString();
                String updateStartDate = updateTermStartDate.getText().toString();
                String updateEndDate = updateTermEndDate.getText().toString();


                dbHandler = new DBHandler(EditTermActivity.this);

                dbHandler.updateTerm(originalTermTitle, updatedTermTitle, updateStartDate, updateEndDate);
                Toast.makeText(EditTermActivity.this, "Your Term has been updated", Toast.LENGTH_SHORT).show();

                updateTermTitle.setText("");
                updateTermStartDate.setText("");
                updateTermEndDate.setText("");


                Intent intent = new Intent(EditTermActivity.this, TermActivity.class);
                startActivity(intent);
            }
        });


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditTermActivity.this, TermActivity.class);
                startActivity(intent);
            }
        });








    }
}