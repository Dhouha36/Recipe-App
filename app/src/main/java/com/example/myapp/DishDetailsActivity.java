package com.example.myapp;

import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class DishDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_details);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        // Setup de la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Initialisation des TextViews et ImageView
        TextView dishNameTextView = findViewById(R.id.dish_name);
        TextView recipeTextView = findViewById(R.id.dish_recipe);
        ImageView dishImageView = findViewById(R.id.dish_image);

        // Récupérer les données passées depuis DishCategoryActivity
        String dishName = getIntent().getStringExtra("dish_name");
        String dishRecipe = getIntent().getStringExtra("dish_recipe");
        int dishImage = getIntent().getIntExtra("dish_image", -1); // Récupérer l'image

        // Afficher le nom du plat, la recette et l'image
        dishNameTextView.setText(dishName);
        recipeTextView.setText(dishRecipe);
        dishImageView.setImageResource(dishImage);  // Afficher l'image
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Retourne à la page précédente
        return true;
    }
}
