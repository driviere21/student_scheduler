package com.zybooks.studentscheduler;

import static java.sql.Types.NULL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static int numAlert;

    private Button termButton, courseBtn, assessmentBtn;
    private DBHandler dbHandler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



/*
        BottomNavigationView bottomNavigationView = findViewById(R.menu.nav_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigation);

 */

        termButton = findViewById(R.id.termButton);
        courseBtn = findViewById(R.id.courseButton);
        assessmentBtn = findViewById(R.id.assessmentButton);


        dbHandler = new DBHandler(MainActivity.this);








        termButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the terms activity
                Intent intent = new Intent(MainActivity.this, TermActivity.class);
                startActivity(intent);
            }
        });

        courseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CourseActivity.class);
                startActivity(intent);
            }
        });

        assessmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AssessmentActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate menu for the app bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Determine which app bar item was chosen
        switch (item.getItemId()) {
            case R.id.menu_home:
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_terms:
                Intent intentT = new Intent(MainActivity.this, TermActivity.class);
                startActivity(intentT);
                break;

            case R.id.menu_courses:
                Intent intentC = new Intent(MainActivity.this, CourseActivity.class);
                startActivity(intentC);
                break;

            case R.id.menu_assessments:
                Intent intentA = new Intent(MainActivity.this, AssessmentActivity.class);
                startActivity(intentA);
                break;
        }

        return true;
    }
/*
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigation = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.menu_home:
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                    break;

                case R.id.menu_terms:
                    Intent intentT = new Intent(MainActivity.this, TermActivity.class);
                    startActivity(intentT);
                    break;

                case R.id.menu_courses:
                    Intent intentC = new Intent(MainActivity.this, CourseActivity.class);
                    startActivity(intentC);
                    break;

                case R.id.menu_assessments:
                    Intent intentA = new Intent(MainActivity.this, AssessmentActivity.class);
                    startActivity(intentA);
                    break;
            }

            return true;
        }
    };

 */


}