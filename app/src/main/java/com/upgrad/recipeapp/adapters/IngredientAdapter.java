package com.upgrad.recipeapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upgrad.recipeapp.R;
import com.upgrad.recipeapp.model.Ingredient;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private Context context;
    private List<Ingredient> ingredientList;

    public IngredientAdapter(Context context, List<Ingredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.ingredient_list_item, viewGroup, false);
        return new IngredientViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder ingredientViewHolder, int i) {
        Ingredient ingredient = ingredientList.get(i);
        ingredientViewHolder.item.setText(ingredient.getName());
        ingredientViewHolder.quantity.setText(ingredient.getQuantity());
    }

    @Override
    public int getItemCount() {
        return ingredientList == null ? 0 : ingredientList.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        TextView item;
        TextView quantity;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.ingredientNameTV);
            quantity = itemView.findViewById(R.id.ingredientQuantityTV);
        }

    }

}
