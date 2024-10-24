package com.zybooks.studentscheduler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class TermRVAdapter extends RecyclerView.Adapter<TermRVAdapter.ViewHolder> {

    //Variable for the array list and context
    private ArrayList<Term> termArrayList;
    private Context context;

    //Constructor
    public TermRVAdapter(ArrayList<Term> termArrayList, Context context) {
        this.termArrayList = termArrayList;
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.term_rv_item, parent,false);
        return new  ViewHolder(view);
    }

    @Override
    public  void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //setting data to the views of recycler view item
        Term modal = termArrayList.get(position);
        holder.termTitle.setText(modal.getTermTitle());
        holder.startDate.setText(modal.getStartDate());
        holder.endDate.setText(modal.getEndDate());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Intent i = new Intent(context, TermDetailActivity.class);


               i.putExtra("termTitle", modal.getTermTitle());
               i.putExtra("startDate", modal.getStartDate());
               i.putExtra("endDate", modal.getEndDate());

               context.startActivity(i);
           }
        });
    }


    @Override
    public int getItemCount() {

        return termArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView termTitle, startDate, endDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            termTitle = itemView.findViewById(R.id.termTitleTextView);
            startDate = itemView.findViewById(R.id.termStartDateTextView);
            endDate = itemView.findViewById(R.id.termEndDateTextView);
        }

    }

}
