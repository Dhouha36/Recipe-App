package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);  // Layout pour l'écran de bienvenue

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        // Récupérer le bouton dans la mise en page
        Button buttonContinue = findViewById(R.id.button_continue);

        // Définir le OnClickListener pour le bouton
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer un Intent pour démarrer MainActivity
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();  // Fermer WelcomeActivity pour que l'utilisateur ne puisse pas revenir à cette activité
            }
        });
    }
}
