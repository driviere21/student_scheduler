package com.zybooks.studentscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class TermActivity extends AppCompatActivity {

    private ArrayList<Term> termArrayList;
    private DBHandler dbHandler;
    private TermRVAdapter termRVAdapter;
    private RecyclerView termRV;

    private Button addTermBtn, deleteTermBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);

        termArrayList = new ArrayList<>();
        dbHandler = new DBHandler(TermActivity.this);

        termArrayList = dbHandler.readTerm();

        termRVAdapter = new TermRVAdapter(termArrayList, TermActivity.this);
        termRV = findViewById(R.id.RVTerms);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TermActivity.this, RecyclerView.VERTICAL, false);
        termRV.setLayoutManager(linearLayoutManager);

        termRV.setAdapter(termRVAdapter);



       addTermBtn = findViewById(R.id.termAddTermBtn);
       deleteTermBtn = findViewById(R.id.termDeleteTermBtn);

       addTermBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(TermActivity.this, AddTermActivity.class);
               startActivity(intent);
           }
       });

       deleteTermBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(TermActivity.this, DeleteTermActivity.class);
               startActivity(intent);
           }
       });


    }
}