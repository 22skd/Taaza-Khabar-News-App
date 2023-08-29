package com.example.newsapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private SearchView searchView;
    private Button searchButton;
    private RecyclerView searchResultsRecyclerView;
    private Adapter searchResultsAdapter;
    private ArrayList<ModelClass> searchResultsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.searchView);
        searchButton = findViewById(R.id.searchButton);
        searchResultsRecyclerView = findViewById(R.id.searchResultsRecyclerView);

        searchResultsList = new ArrayList<>();
        searchResultsAdapter = new Adapter(this, searchResultsList);
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchResultsRecyclerView.setAdapter(searchResultsAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchView.getQuery().toString();
                performSearch(query);
            }
        });
    }

    private void performSearch(String query) {
        ApiUtilities.getApiInterface().searchNews(query, 100, "b5453742409f44f0a26995bb4ccc18ac")
                .enqueue(new Callback<mainNews>() {
                    @Override
                    public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                        if (response.isSuccessful()) {
                            searchResultsList.clear();
                            searchResultsList.addAll(response.body().getArticles());
                            searchResultsAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<mainNews> call, Throwable t) {
                        Toast.makeText(SearchActivity.this, "Failed to perform search", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
