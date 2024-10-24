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

public class AssessmentRVAdapter  extends RecyclerView.Adapter<AssessmentRVAdapter.ViewHolder> {

    private ArrayList<Assessments> assessmentsArrayList;
    private Context context;

    public AssessmentRVAdapter(ArrayList<Assessments> assessmentsArrayList, Context context) {
        this.assessmentsArrayList = assessmentsArrayList;
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assessment_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Assessments modal = assessmentsArrayList.get(position);
        holder.assessmentTitle.setText(modal.getAssessmentTitle());
        holder.startDate.setText(modal.getStartDate());
        holder.endDate.setText(modal.getEndDate());
        holder.assessmentType.setText(modal.getAssessmentType());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AssessmentDetailActivity.class);

                i.putExtra("assessmentTitle", modal.getAssessmentTitle());
                i.putExtra("startDate", modal.getStartDate());
                i.putExtra("endDate", modal.getEndDate());
                i.putExtra("assessmentType", modal.getAssessmentType());

                context.startActivity(i);
            }
        });

    }




    @Override
    public int getItemCount() { return assessmentsArrayList.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView assessmentTitle, startDate, endDate, assessmentType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);



            assessmentTitle = itemView.findViewById(R.id.assessment2TextView);
            startDate = itemView.findViewById(R.id.assType2TextView);
            endDate = itemView.findViewById(R.id.assEndDate2TextView);
            assessmentType = itemView.findViewById(R.id.assType2TextView);
        }
    }

}
