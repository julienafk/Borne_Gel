package com.example.informationsecuriteimportant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    //private Button returntButton;
    private TextView affichagePN;
    private Button returntButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Recup de la variable de la page 1
        affichagePN = (TextView)findViewById(R.id.PrenomNom);
        String nom = getIntent().getStringExtra("nom");

        affichagePN.setText(nom);
        // Fin recup de la variable

        returntButton=(Button)findViewById(R.id.activity_main_returnButton);





        returntButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                Intent activity_main2 = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(activity_main2);

            }
        });


    }
}