package com.upgrad.recipeapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.upgrad.recipeapp.R;
import com.upgrad.recipeapp.adapters.CategoryAdapter;
import com.upgrad.recipeapp.adapters.LatestAdapter;
import com.upgrad.recipeapp.model.Category;
import com.upgrad.recipeapp.model.Recipe;
import com.upgrad.recipeapp.utils.NetworkFetcher;
import com.upgrad.recipeapp.utils.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView categoryList, latestList;
    private CategoryAdapter categoryAdapter;
    private LatestAdapter latestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        categoryAdapter = new CategoryAdapter(this);
        latestAdapter = new LatestAdapter(this);
        setUpList();
        fetchData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.action_refresh:
                fetchData();
                break;
        }

        return true;
    }

    private void setUpList() {

        categoryList = findViewById(R.id.categoriesList);
        categoryList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoryList.setAdapter(categoryAdapter);

        latestList = findViewById(R.id.latestList);
        latestList.setLayoutManager(new LinearLayoutManager(this));
        latestList.setNestedScrollingEnabled(false);
        latestList.setAdapter(latestAdapter);

    }

    private void fetchData() {
        new FetchTask().execute("category", URL.CATEGORIES, "");
        new FetchTask().execute("latest", URL.LATEST, "");
    }

    private class FetchTask extends AsyncTask<String, Void, String[]> {


        @Override
        protected String[] doInBackground(String... strings) {
            String type = strings[0];
            String url = strings[1];
            String query = strings[2];
            String response = NetworkFetcher.makeServiceCall(url + query);
            return new String[]{type, response};
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);

            String response = strings[1];
            if (strings[0].equals("category")) {

                if (response != null && response.length() > 0) {
                    try {
                        List<Category> categories = new ArrayList<>();
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray categoryArray = jsonObject.getJSONArray("categories");

                        for (int i = 0, size = categoryArray.length(); i < size; i++) {
                            Gson gson = new Gson();
                            categories.add(gson.fromJson(categoryArray.get(i).toString(), Category.class));
                        }

                        categoryAdapter.updateList(categories);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    showToast("Please refresh, error occurred");
                }

            } else if (strings[0].equals("latest")) {

                if (response != null && response.length() > 0) {
                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray meals = jsonObject.getJSONArray("meals");

                        if (meals.length() > 0) {
                            Gson gson = new Gson();
                            List<Recipe> recipeList = new ArrayList<>();
                            for (int i = 0; i < meals.length(); i++) {
                                recipeList.add(gson.fromJson(meals.get(i).toString(), Recipe.class));
                            }
                            latestAdapter.updateList(recipeList);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    showToast("Please refresh, error occurred");
                }

            }

        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
