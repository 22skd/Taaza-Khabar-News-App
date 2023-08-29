package com.example.newsapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WhatsappActivity extends AppCompatActivity {
    private EditText whatsappNumberEditText;
    private CheckBox scienceCheckbox, healthCheckbox, technologyCheckbox, entertainmentCheckbox, sportsCheckbox;
    private Button applyButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whatsapp_layout);

        whatsappNumberEditText = findViewById(R.id.whatsapp_number_edittext);
        scienceCheckbox = findViewById(R.id.science_checkbox);
        healthCheckbox = findViewById(R.id.health_checkbox);
        technologyCheckbox = findViewById(R.id.technology_checkbox);
        entertainmentCheckbox = findViewById(R.id.entertainment_checkbox);
        sportsCheckbox = findViewById(R.id.sports_checkbox);
        applyButton = findViewById(R.id.apply_button);
        backButton = findViewById(R.id.back_button);





        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the WhatsApp number entered by the user
                String whatsappNumber = whatsappNumberEditText.getText().toString();

                // Get the selected categories
                ArrayList<String> selectedCategories = new ArrayList<>();
                if (scienceCheckbox.isChecked()) {
                    selectedCategories.add("science");
                }
                if (healthCheckbox.isChecked()) {
                    selectedCategories.add("health");
                }
                if (technologyCheckbox.isChecked()) {
                    selectedCategories.add("technology");
                }
                if (entertainmentCheckbox.isChecked()) {
                    selectedCategories.add("entertainment");
                }
                if (sportsCheckbox.isChecked()) {
                    selectedCategories.add("sports");
                }

                // Process each selected category
                for (String category : selectedCategories) {
                    fetchNewsArticlesForCategory(whatsappNumber, category);
                }

            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click
                Intent intent = new Intent(WhatsappActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void sendNewsArticles(String phoneNumber, ArrayList<ModelClass> articles) {
        StringBuilder messageBuilder = new StringBuilder();

        for (ModelClass article : articles) {
            String headline = article.getTitle();
            String articleUrl = article.getUrl();

            messageBuilder.append(headline).append("\n").append(articleUrl).append("\n\n");
        }

        String message = messageBuilder.toString();
        TwilioHelper.sendMessage(phoneNumber, message);
    }

    private void fetchNewsArticlesForCategory(String whatsappNumber, String category) {
        // Make API call using Retrofit to fetch news articles for the specified category
        // Modify the code according to your specific API implementation

        String country = "in";
        int pageSize = 1;
        String apiKey = "enter the api key";

        ApiUtilities.getApiInterface().getCategoryNews(country, category, pageSize, apiKey).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful()) {
                    ArrayList<ModelClass> articles = response.body().getArticles();
                    sendNewsArticles(whatsappNumber, articles);
                } else {
                    Toast.makeText(WhatsappActivity.this, "Failed to fetch news articles for category: " + category, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {
                // Handle failure
                Toast.makeText(WhatsappActivity.this, "Failed to fetch news articles for category: " + category, Toast.LENGTH_SHORT).show();

            }
        });
    }



}

