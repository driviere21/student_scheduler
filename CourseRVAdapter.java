package com.zybooks.studentscheduler;

import static com.zybooks.studentscheduler.DBHandler.DB_NAME;
import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {

    private ArrayList<Courses> coursesArrayList;
    private Context context;
    private DBHandler dbHandler;
    SQLiteDatabase db;

    private int courseTermId = -1;






    //Constructor
    public CourseRVAdapter(ArrayList<Courses> coursesArrayList, Context context) {
        this.coursesArrayList = coursesArrayList;
        this.context = context;

    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_rv_item, parent, false);
        return new ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //setting data to the views of recycler view item
        Courses modal = coursesArrayList.get(position);
        holder.courseTitle.setText(modal.getCourseTitle());
        holder.startDate.setText(modal.getStartDate());
        holder.endDate.setText(modal.getEndDate());
        holder.status.setText(modal.getStatus());
        holder.instructorName.setText(modal.getInstructorName());
        holder.instructorTel.setText(modal.getInstructorTel());
        holder.instructorEmail.setText(modal.getInstructorEmail());
        holder.notes.setText(modal.getNotes());





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CourseDetailActivity.class);

                i.putExtra("courseTitle", modal.getCourseTitle());
                i.putExtra("startDate", modal.getStartDate());
                i.putExtra("endDate", modal.getEndDate());
                i.putExtra("status", modal.getStatus());
                i.putExtra("instructorName", modal.getInstructorName());
                i.putExtra("instructorTel", modal.getInstructorTel());
                i.putExtra("instructorEmail", modal.getInstructorEmail());
                i.putExtra("notes", modal.getNotes());

                context.startActivity(i);
            }


        });






    }





    @Override
    public int getItemCount() {
        return coursesArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView courseTitle, startDate, endDate, status, instructorName, instructorTel, instructorEmail, notes, termId;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            courseTitle = itemView.findViewById(R.id.courseTextView);
            startDate = itemView.findViewById(R.id.startDateTextView);
            endDate = itemView.findViewById(R.id.endDateTextView);
            status = itemView.findViewById(R.id.statusTextView);
            instructorName = itemView.findViewById(R.id.instructorNmTextView);
            instructorTel = itemView.findViewById(R.id.telTextView);
            instructorEmail = itemView.findViewById(R.id.emailTextView);
            termId = itemView.findViewById(R.id.termAssignedTextView);
            notes = itemView.findViewById(R.id.notesTextView);




        }



    }

}

