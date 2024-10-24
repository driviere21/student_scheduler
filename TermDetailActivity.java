package com.zybooks.studentscheduler;

import static com.zybooks.studentscheduler.DBHandler.DB_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class TermDetailActivity extends AppCompatActivity {

    private EditText termDetail;
    private Button editTermBtn;
    private DBHandler dbHandler;
    private ArrayList<Courses> termDetailArrayList;
    private RecyclerView termDetailRV;
    private TermDetailRVAdapter termDetailRVAdapter;

    int termIdD = -1;
    List<Courses> coursesList;





    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);





        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);


        termDetailArrayList = new ArrayList<>();
        dbHandler = new DBHandler(TermDetailActivity.this);


        termDetail = findViewById(R.id.termDetailTermEditTextText);
        editTermBtn = findViewById(R.id.viewDetailsButton);


        String termDetailsTermTitle = getIntent().getStringExtra("termTitle");
        String upTermTitle = getIntent().getStringExtra("termTitle");
        String upTermStartDate = getIntent().getStringExtra("startDate");
        String upTermEndDate = getIntent().getStringExtra("endDate");

        String eTermTitle = upTermTitle;
        String eTermStartDate = upTermStartDate;
        String eTermEndDate = upTermEndDate;
       /*
        Intent intent = new Intent(TermDetailActivity.this, EditTermActivity.class);
        intent.putExtra("termTitle", eTermTitle);
        intent.putExtra("startDate", eTermStartDate);
        intent.putExtra("endDate", eTermEndDate);
        startActivity(intent);

        */

        termDetail.setText(termDetailsTermTitle);

        String details = termDetail.getText().toString();



        termDetailArrayList.clear();


        getSelectedTermIdFromDataBase(details);


        int courseTermIdD = termIdD;

        String termDetailSql = "SELECT * FROM courses WHERE termId = ?";
        Cursor cursorC = db.rawQuery(termDetailSql, new String[]{String.valueOf(courseTermIdD)});
        if (cursorC != null && cursorC.getCount() > 0) {
            if (cursorC.moveToFirst()) {
                do {
                    termDetailArrayList.add(new Courses(cursorC.getString(1),
                            cursorC.getString(2),
                            cursorC.getString(3),
                            cursorC.getString(4),
                            cursorC.getString(5),
                            cursorC.getString(6),
                            cursorC.getString(7),
                            cursorC.getString(8)));

                } while (cursorC.moveToNext());
                termDetailRVAdapter = new TermDetailRVAdapter(termDetailArrayList, TermDetailActivity.this);
                termDetailRV = findViewById(R.id.termDetailRV);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TermDetailActivity.this, RecyclerView.VERTICAL, false);
                termDetailRV.setLayoutManager(linearLayoutManager);
                termDetailRV.setAdapter(termDetailRVAdapter);

            }

            cursorC.close();
        }


        editTermBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermDetailActivity.this, EditTermActivity.class);
                intent.putExtra("termTitle", eTermTitle);
                intent.putExtra("startDate", eTermStartDate);
                intent.putExtra("endDate", eTermEndDate);
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
                    termIdD = cursor.getInt(0);

                } while (cursor.moveToNext());
            }

        }


    }


    private ArrayList<Courses> readTermDetails() {

        String termDetailSql = "SELECT * FROM courses WHERE termId = ?";
        Cursor cursorC = db.rawQuery(termDetailSql, new String[]{String.valueOf(termIdD)});

        ArrayList<Courses> termDetailsArrayList = new ArrayList<>();

        if (cursorC != null && cursorC.getCount() > 0) {
            if (cursorC.moveToFirst()) {
                do {
                    termDetailsArrayList.add(new Courses(cursorC.getString(1),
                            cursorC.getString(2),
                            cursorC.getString(3),
                            cursorC.getString(4),
                            cursorC.getString(5),
                            cursorC.getString(6),
                            cursorC.getString(7),
                            cursorC.getString(8)));

                } while (cursorC.moveToNext());
            }


            cursorC.close();


        }
        return termDetailsArrayList;


    }


}