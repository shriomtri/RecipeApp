package com.upgrad.recipeapp.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.upgrad.recipeapp.R;
import com.upgrad.recipeapp.adapters.IngredientAdapter;
import com.upgrad.recipeapp.adapters.ProcedureAdapter;
import com.upgrad.recipeapp.model.Recipe;
import com.upgrad.recipeapp.utils.RecipeHelper;

public class DetailActivity extends AppCompatActivity {

    private Recipe recipe;
    private RecyclerView ingredientList, procedureList;

    private IngredientAdapter ingredientAdapter;
    private ProcedureAdapter procedureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(recipe.getStrMeal());
        setUpList();
    }

    private void setUpList() {

        ingredientList = findViewById(R.id.ingredientList);
        ingredientList.setLayoutManager(new GridLayoutManager(this,3));
        ingredientAdapter = new IngredientAdapter(this, RecipeHelper.getIngredient(recipe));
        ingredientList.setAdapter(ingredientAdapter);

        procedureList = findViewById(R.id.stepsList);
        procedureList.setLayoutManager(new LinearLayoutManager(this));
        procedureAdapter = new ProcedureAdapter(this, RecipeHelper.getSteps(recipe));
        procedureList.setAdapter(procedureAdapter);
        procedureList.setNestedScrollingEnabled(false);

    }
}
