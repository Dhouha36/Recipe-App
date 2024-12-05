package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Modifier la couleur de la Status Bar
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        // Setup de la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); // Afficher la flèche de retour
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Définir l'action de la flèche pour retourner vers WelcomeActivity
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();  // Terminer MainActivity pour éviter de revenir en arrière
            }
        });

        // Initialisation du ListView
        listView = findViewById(R.id.category_list);

        listView.setDivider(getResources().getDrawable(R.drawable.divider));
        listView.setDividerHeight(18);

        // Les catégories à afficher
        String[] categories = {"Entrées", "Plats principaux", "Desserts"};
        CategoryAdapter adapter = new CategoryAdapter(categories);
        listView.setAdapter(adapter);
    }

    private class CategoryAdapter extends BaseAdapter {
        private String[] categories;

        public CategoryAdapter(String[] categories) {
            this.categories = categories;
        }

        @Override
        public int getCount() {
            return categories.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, android.view.ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_category, null);
            }

            // Références du TextView et ImageView pour chaque élément
            TextView categoryName = convertView.findViewById(R.id.category_name);
            categoryName.setText(categories[position]);

            // Image associée à la catégorie
            ImageView categoryImage = convertView.findViewById(R.id.category_image);
            categoryImage.setImageResource(getImageForCategory(categories[position]));

            // Lien vers la page suivante lorsqu'un item est cliqué
            convertView.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, DishCategoryActivity.class);
                intent.putExtra("category", categories[position]);
                startActivity(intent);
            });

            return convertView;
        }

        private int getImageForCategory(String category) {
            switch (category) {
                case "Entrées":
                    return R.drawable.entrees;
                case "Plats principaux":
                    return R.drawable.plat_principal;
                case "Desserts":
                    return R.drawable.dessert;
                default:
                    return R.drawable.entrees;
            }
        }
    }
}
