package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
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

public class DishCategoryActivity extends AppCompatActivity {

    private ListView listView;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_category);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        // Setup de la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); // Pour le bouton retour
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Initialisation du ListView
        listView = findViewById(R.id.dish_list);

        listView.setDivider(getResources().getDrawable(R.drawable.divider));
        listView.setDividerHeight(18);
        // Récupérer la catégorie passée par MainActivity
        String category = getIntent().getStringExtra("category");

        // Remplir la liste en fonction de la catégorie
        String[] dishes = getDishesForCategory(category);
        DishAdapter adapter = new DishAdapter(dishes);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Retourne à la page précédente (MainActivity)
        return true;
    }

    private String[] getDishesForCategory(String category) {
        switch (category) {
            case "Entrées":
                return new String[] {"Salade", "Bruschetta", "Soupe"};
            case "Plats principaux":
                return new String[] {"Spaghetti", "Couscous", "Pizza"};
            case "Desserts":
                return new String[] {"Tiramisu", "Baklava", "Crêpes"};
            default:
                return new String[] {};
        }
    }

    private class DishAdapter extends BaseAdapter {
        private String[] dishes;

        public DishAdapter(String[] dishes) {
            this.dishes = dishes;
        }

        @Override
        public int getCount() {
            return dishes.length;
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
                convertView = inflater.inflate(R.layout.item_dish, null);
            }

            // Références du TextView et ImageView
            TextView dishName = convertView.findViewById(R.id.dish_name);
            dishName.setText(dishes[position]);
            dishName.setTypeface(null, Typeface.BOLD);
            dishName.setTextSize(20);

            // ImageView pour afficher l'image
            ImageView dishImage = convertView.findViewById(R.id.dish_image);
            dishImage.setImageResource(getImageForDish(dishes[position]));  // Associe l'image à chaque plat

            // Ajout d'un click listener sur chaque élément de la liste
            convertView.setOnClickListener(v -> {
                // Créez un Intent pour ouvrir DishDetailsActivity et envoyer le nom du plat
                Intent intent = new Intent(DishCategoryActivity.this, DishDetailsActivity.class);
                intent.putExtra("dish_name", dishes[position]);
                intent.putExtra("dish_recipe", getRecipeForDish(dishes[position]));
                intent.putExtra("dish_image", getImageForDish(dishes[position]));  // Passer l'image
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            });

            return convertView;
        }

        private int getImageForDish(String dish) {
            switch (dish) {
                case "Salade":
                    return R.drawable.salad_image;
                case "Bruschetta":
                    return R.drawable.bruschetta_image;
                case "Soupe":
                    return R.drawable.soup_image;
                case "Spaghetti":
                    return R.drawable.spaghetti_image;
                case "Couscous":
                    return R.drawable.couscous_image;
                case "Pizza":
                    return R.drawable.pizza_image;
                case "Tiramisu":
                    return R.drawable.tiramisu_image;
                case "Baklava":
                    return R.drawable.baklava_image;
                case "Crêpes":
                    return R.drawable.crepes_image;
                default:
                    return R.drawable.entrees;
            }
        }

        private String getRecipeForDish(String dish) {
            switch (dish) {
                case "Salade":
                    return "Ingrédients: Laitue, tomates, concombre...\nInstructions: Mélanger les ingrédients.";
                case "Bruschetta":
                    return "Ingrédients: Pain, tomates, ail...\nInstructions: Griller le pain et ajouter les tomates.";
                case "Soupe":
                    return "Ingrédients: Légumes, bouillon...\nInstructions: Faire cuire les légumes dans le bouillon.";
                case "Spaghetti":
                    return "Ingrédients: Spaghetti, sauce tomate...\nInstructions: Faire cuire les pâtes et ajouter la sauce.";
                case "Couscous":
                    return "Ingrédients: Semoule, légumes...\nInstructions: Cuire la semoule et servir avec les légumes.";
                case "Pizza":
                    return "Ingrédients: Pâte à pizza, sauce tomate, fromage...\nInstructions: Étaler la pâte, ajouter les ingrédients et cuire au four.";
                case "Tiramisu":
                    return "Ingrédients: Mascarpone, café...\nInstructions: Alterner couches de biscuits et de mascarpone.";
                case "Baklava":
                    return "Ingrédients: Pâte filo, noix, miel...\nInstructions: Superposer les couches et cuire au four.";
                case "Crêpes":
                    return "Ingrédients: Farine, œufs, lait...\nInstructions: Mélanger les ingrédients et cuire les crêpes.";
                default:
                    return "Recette non disponible.";
            }
        }
    }

}
