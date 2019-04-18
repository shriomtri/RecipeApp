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
import com.upgrad.recipeapp.utils.CategoryListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private CategoryListener categoryListener;
    private List<Category> categoryList = new ArrayList<>();

    public CategoryAdapter(Context context) {
        this.context = context;
        this.categoryListener = (CategoryListener) context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.category_list_item, viewGroup, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {

        final Category category = categoryList.get(i);
        Glide.with(context).load(category.getStrCategoryThumb()).into(categoryViewHolder.categoryImage);
        categoryViewHolder.categoryName.setText(category.getStrCategory());

        categoryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryListener.categoryClicked(category);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public void updateList(List<Category> categories) {
        this.categoryList = categories;
        notifyDataSetChanged();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView categoryImage;
        private TextView categoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImage = itemView.findViewById(R.id.categoryImageView);
            categoryName = itemView.findViewById(R.id.categoryNameTV);

        }

    }
}
