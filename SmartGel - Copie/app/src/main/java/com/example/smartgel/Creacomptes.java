package com.example.smartgel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Creacomptes extends AppCompatActivity {
    private EditText Nom;
    private EditText Prenom;
    private EditText Grade;
    private EditText Email;
    private EditText Password;
    final boolean[]succes = new boolean[1]

    private Button CréerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacomptes);
        http://51.210.151.13/btssnir/projets2022/bornegel/api/creationCompte.php
        Nom = findViewById(R.id.activity_main_NomEditText);
        Prenom = findViewById(R.id.activity_main_PrenomEditText);
        Grade = findViewById(R.id.activity_main_GradeEditText);
        Email = findViewById(R.id.activity_main_EmailEditText);
        Password = findViewById(R.id.activity_main_mdpEditText);
        CréerButton = findViewById(R.id.activity_main_loginButton);

        CréerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Debut GET
                String jsnom = Nom.getText().toString();
                String jsprenom = Prenom.getText().toString();
                String jsgrade = Grade.getText().toString();
                String jsmail = Email.getText().toString();
                String jsmdp = Password.getText().toString();
                String url = "http://51.210.151.13/btssnir/projets2022/bornegel/api/creationCompte.php?Nom=" + jsnom + "&Prenom=" + jsprenom + "&Grade=" + jsgrade+ "&Email=" + jsmail+ "&Password=" + jsmdp;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response)
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(stringRequest);
                // Fin GET

            }

        });








    }
}