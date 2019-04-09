package com.upgrad.recipeapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upgrad.recipeapp.R;

public class ProcedureAdapter extends RecyclerView.Adapter<ProcedureAdapter.PrcedureViewHolder> {

    private Context context;
    private String[] stepsList;

    public ProcedureAdapter(Context context, String[] stepsList) {
        this.context = context;
        this.stepsList = stepsList;
    }

    @NonNull
    @Override
    public PrcedureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.procedure_list_item, viewGroup, false);
        return new PrcedureViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PrcedureViewHolder prcedureViewHolder, int i) {

        prcedureViewHolder.stepCount.setText(String.valueOf(i+1)+".");
        prcedureViewHolder.stepDetail.setText(stepsList[i].trim()+".");

    }

    @Override
    public int getItemCount() {
        return stepsList.length;
    }

    public class PrcedureViewHolder extends RecyclerView.ViewHolder {

        TextView stepCount, stepDetail;

        public PrcedureViewHolder(@NonNull View itemView) {
            super(itemView);
            stepCount = itemView.findViewById(R.id.stepCountTV);
            stepDetail = itemView.findViewById(R.id.stepDetailTV);
        }
    }
}
