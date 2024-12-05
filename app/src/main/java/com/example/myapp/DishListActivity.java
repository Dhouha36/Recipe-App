package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class DishListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_list);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        // Configurer la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        // Ajouter la flèche de retour
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Récupérer la sous-catégorie passée depuis DishCategoryActivity
        String subCategory = getIntent().getStringExtra("subcategory");
        setTitle(subCategory); // Afficher le nom de la sous-catégorie dans l'action bar

        // Simuler des plats en fonction de la sous-catégorie
        ListView listView = findViewById(R.id.dish_list);
        String[] dishes;

        if ("Entrées".equals(subCategory)) {
            dishes = new String[]{"Salade", "Bruschetta", "Soupe"};
        } else if ("Plats principaux".equals(subCategory)) {
            dishes = new String[]{"Spaghetti", "Couscous", "Pizza"};
        } else if ("Desserts".equals(subCategory)) {
            dishes = new String[]{"Tiramisu", "Baklava", "Crêpes"};
        } else {
            dishes = new String[]{"Plat non disponible"};
        }

        // Adapter pour la liste des plats
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dishes);
        listView.setAdapter(adapter);

        // OnClickListener pour chaque plat
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(DishListActivity.this, DishDetailsActivity.class);
            intent.putExtra("dish", dishes[position]); // Passer le plat sélectionné
            startActivity(intent);
        });
    }

    // Implémenter l'action de la flèche de retour
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
