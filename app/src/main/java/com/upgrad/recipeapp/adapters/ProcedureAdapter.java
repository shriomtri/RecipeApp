package com.upgrad.recipeapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class ProcedureAdapter extends RecyclerView.Adapter<ProcedureAdapter.PrcedureViewHolder> {

    @NonNull
    @Override
    public PrcedureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PrcedureViewHolder prcedureViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PrcedureViewHolder extends RecyclerView.ViewHolder {
        public PrcedureViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
