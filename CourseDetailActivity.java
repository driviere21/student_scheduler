package com.zybooks.studentscheduler;

import static com.zybooks.studentscheduler.DBHandler.DB_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class CourseDetailActivity extends AppCompatActivity {




    private EditText courseTitle, startDate, endDate, instructorName, instructorPhone, instructorEmail, termAssigned, notes, status;
    private Button updateCourseBtn, cancelBtn;
    private DBHandler dbHandler;
    int courseTermId = -1;
    private Context context;

    public String aCourseTitle;
    public String aCourseStartDate;
    public String aCourseEndDate;
   // private int nTermId = -1;
  //  private String nTermNm = "";


     int nTermId;
     String nTermNm;



    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        setTitle("Update Course");





        db = openOrCreateDatabase(DB_NAME,MODE_PRIVATE, null );


        courseTitle = findViewById(R.id.addCourseCourseTitleEditText);
        startDate = findViewById(R.id.addCourseCourseStartDateEditText);
        endDate = findViewById(R.id.addCourseCourseEndDateEditText);
        instructorName = findViewById(R.id.addCourseInstructorNameEditText);
        instructorPhone = findViewById(R.id.addCourseInstructorPhoneEditText);
        instructorEmail = findViewById(R.id.addCourseInstructorEmailEditText);
        termAssigned = findViewById(R.id.termAssignedEditText);
        status = findViewById(R.id.statusEditText);
        notes = findViewById(R.id.addCourseNoteEditText);
        updateCourseBtn = findViewById(R.id.updateCourseBtn);
        cancelBtn = findViewById(R.id.addCourseCancelBtn);



            String editCourseTitle = getIntent().getStringExtra("courseTitle");
            String editCourseStartDate = getIntent().getStringExtra("startDate");
            String editCourseEndDate = getIntent().getStringExtra("endDate");
            String editCourseStatus = getIntent().getStringExtra("status");
            String editInstructorName = getIntent().getStringExtra("instructorName");
            String editInstructorTel = getIntent().getStringExtra("instructorTel");
            String editInstructorEmail = getIntent().getStringExtra("instructorEmail");
            String editCourseNotes = getIntent().getStringExtra("notes");

            String editCourseTermId = getIntent().getStringExtra("courseTermId");
            String originalCourseName = getIntent().getStringExtra("courseTitle");




            courseTitle.setText(editCourseTitle);
            startDate.setText(editCourseStartDate);
            endDate.setText(editCourseEndDate);
            status.setText(editCourseStatus);
            instructorName.setText(editInstructorName);
            instructorEmail.setText(editInstructorEmail);
            instructorPhone.setText(editInstructorTel);
            notes.setText(editCourseNotes);

            termAssigned.setText(editCourseTermId);

            aCourseTitle = editCourseTitle;
            aCourseStartDate = editCourseStartDate;
            aCourseEndDate = editCourseEndDate;


            getTermIdFromDatabase(aCourseTitle);

            int newTermId = nTermId;

            getTermTitleFromDatabase(newTermId);

            String editTermAssigned = nTermNm;

            termAssigned.setText(editTermAssigned);




            updateCourseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String updatedCourseTitle = courseTitle.getText().toString();
                    String updatedStartDate = startDate.getText().toString();
                    String updatedEndDate = endDate.getText().toString();
                    String updatedStatus = status.getText().toString();
                    String updatedInstructorName = instructorName.getText().toString();
                    String updatedInstructorEmail = instructorEmail.getText().toString();
                    String updatedInstructorPhone = instructorPhone.getText().toString();
                    String updatedNote = notes.getText().toString();
                    String updatedAssignedTo = termAssigned.getText().toString();
                    String originalCourseTitle = courseTitle.getText().toString();




                    if (updatedCourseTitle.isEmpty() && updatedStartDate.isEmpty() && updatedEndDate.isEmpty() && updatedStatus.isEmpty() && updatedInstructorName.isEmpty() && updatedInstructorEmail.isEmpty() && updatedInstructorPhone.isEmpty() && updatedAssignedTo.isEmpty()) {
                        Toast.makeText(CourseDetailActivity.this, "Please enter all the information in this form", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    dbHandler = new DBHandler(CourseDetailActivity.this);

                    getSelectedTermIdFromDataBase(updatedAssignedTo);

                    int updatedTermCourseId = courseTermId;

                    dbHandler.updateCourse(originalCourseName, updatedCourseTitle, updatedStartDate, updatedEndDate, updatedStatus, updatedInstructorName, updatedInstructorEmail, updatedInstructorPhone, updatedNote, updatedTermCourseId);

                    Toast.makeText(CourseDetailActivity.this, "Your Course was updated", Toast.LENGTH_SHORT).show();

                    courseTitle.setText("");
                    startDate.setText("");
                    endDate.setText("");
                    status.setText("");
                    instructorName.setText("");
                    instructorEmail.setText("");
                    instructorPhone.setText("");
                    termAssigned.setText("");
                    notes.setText("");

                    Intent intent = new Intent(CourseDetailActivity.this, CourseActivity.class);
                    startActivity(intent);



                }
            });

            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(CourseDetailActivity.this, CourseActivity.class);
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




    private void getTermIdFromDatabase(String courseTitle) {
        String courseTitleSql = "SELECT termId FROM courses WHERE courseTitle =?";
        Cursor cursor = db.rawQuery(courseTitleSql, new String[]{courseTitle});
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToNext()) {
                do {
                    nTermId = cursor.getInt(0);
                } while (cursor.moveToNext());
            }
        }
    }


    private void getTermTitleFromDatabase(int nTermId){
        String termTitleSql = "SELECT termTitle FROM terms WHERE termId =?";
        Cursor cursor = db.rawQuery(termTitleSql, new String[]{String.valueOf(nTermId)});
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToNext()) {
                do {
                    nTermNm = cursor.getString(0);
                }while (cursor.moveToNext());
            }
        }
    }












    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate menu for the app bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.update_course_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String courseNotes = getIntent().getStringExtra("notes");

        // Determine which app bar item was chosen
        switch (item.getItemId()) {
            case R.id.menu_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, courseNotes);
                sendIntent.putExtra(Intent.EXTRA_TEXT, courseNotes);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;

            case R.id.menu_reminder:
                Intent intentT = new Intent(CourseDetailActivity.this, CourseAlertActivity.class);


                intentT.putExtra("courseTitle", aCourseTitle);
                intentT.putExtra("startDate", aCourseStartDate);
                intentT.putExtra("endDate", aCourseEndDate);







                startActivity(intentT);
                break;


        }

        return true;
    }





}