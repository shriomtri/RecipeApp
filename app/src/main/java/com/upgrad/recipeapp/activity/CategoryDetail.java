package com.upgrad.recipeapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.upgrad.recipeapp.R;
import com.upgrad.recipeapp.adapters.CategoryDetailAdapter;
import com.upgrad.recipeapp.model.Recipe;
import com.upgrad.recipeapp.utils.CategoryDetailListener;
import com.upgrad.recipeapp.utils.NetworkFetcher;
import com.upgrad.recipeapp.utils.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetail extends AppCompatActivity implements CategoryDetailListener {

    private String searchQuery;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private CategoryDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        Intent intent = getIntent();
        searchQuery = intent.getStringExtra("category_name");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(searchQuery);

        progressBar = findViewById(R.id.porgressBar);

        adapter = new CategoryDetailAdapter(this);

        recyclerView = findViewById(R.id.categoryTypeList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        new FetchTask().execute("search", URL.SEARCH_BY_CATEGORY, searchQuery);

    }

    @Override
    public void categoryItemClic(com.upgrad.recipeapp.model.CategoryDetail categoryDetail) {
        new FetchTask().execute("search_id", URL.SEARCH_BY_ID, categoryDetail.getIdMeal());
    }

    private class FetchTask extends AsyncTask<String,Void,String[]>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... strings) {
            String type = strings[0];
            String url = strings[1];
            String query = strings[2];
            String response = NetworkFetcher.makeServiceCall(url+query);
            return new String[]{type, response};
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            String type = strings[0];
            String response = strings[1];

            if(type.equals("search")){

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("meals");
                    List<com.upgrad.recipeapp.model.CategoryDetail> categoryDetailList = new ArrayList<>();
                    Gson gson = new Gson();

                    for(int i=0,size= jsonArray.length(); i<size; i++){
                        categoryDetailList.add(gson.fromJson(jsonArray.get(i).toString(), com.upgrad.recipeapp.model.CategoryDetail.class));
                    }

                    adapter.updateList(categoryDetailList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if(type.equals("search_id")){

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("meals");
                    Gson gson = new Gson();
                    Recipe recipe = gson.fromJson(jsonArray.get(0).toString(), Recipe.class);

                    Intent intent = new Intent(CategoryDetail.this, DetailActivity.class);
                    intent.putExtra("recipe", recipe);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }


    }

}
