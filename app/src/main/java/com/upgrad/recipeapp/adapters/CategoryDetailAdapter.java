package com.upgrad.recipeapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.upgrad.recipeapp.R;
import com.upgrad.recipeapp.model.Category;
import com.upgrad.recipeapp.model.CategoryDetail;
import com.upgrad.recipeapp.utils.CategoryDetailListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailAdapter extends RecyclerView.Adapter<CategoryDetailAdapter.ViewHolder> {

    private Context context;
    private List<CategoryDetail> categoryList = new ArrayList<>();
    private CategoryDetailListener categoryDetailListener;

    public CategoryDetailAdapter(Context context) {
        this.context = context;
        categoryDetailListener = (CategoryDetailListener) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.category_detail_list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final CategoryDetail category = categoryList.get(i);
        Glide.with(context).load(category.getStrMealThumb()).into(viewHolder.categoryImage);
        viewHolder.categoryName.setText(category.getStrMeal());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDetailListener.categoryItemClic(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public void updateList(List<CategoryDetail> categoryDetailList) {
        this.categoryList = categoryDetailList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView categoryImage;
        private TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.categoryImageView);
            categoryName = itemView.findViewById(R.id.categoryNameTV);
        }
    }
}
