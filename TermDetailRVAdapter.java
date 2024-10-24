package com.zybooks.studentscheduler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TermDetailRVAdapter  extends RecyclerView.Adapter<TermDetailRVAdapter.ViewHolder> {

    private ArrayList<Courses> termDetailArrayList;
    private Context context;

    public TermDetailRVAdapter(ArrayList<Courses> termDetailArrayList, Context context) {
        this.termDetailArrayList = termDetailArrayList;
        this.context = context;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.term_detail_rv_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Courses modal = termDetailArrayList.get(position);
        holder.courseTitle.setText(modal.getCourseTitle());


        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v) {
                Intent i = new Intent(context, TermDetailActivity.class);

                i.putExtra("courseTitle", modal.getCourseTitle());

                context.startActivity(i);
            }



        });
    }

    @Override
    public int getItemCount() {
        return termDetailArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView courseTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.termDetailCourseTitleTextView2);
        }
    }
}
