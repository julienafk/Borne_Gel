package com.example.informationsecuriteimportant;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import java.io.Serializable;
import android.content.Intent;
import android.os.Bundle;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button loginButton;
    private ImageButton logadm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.activity_main_idEditText);
        password = findViewById(R.id.activity_main_mdpEditText);
        loginButton = findViewById(R.id.activity_main_loginButton);
        logadm = findViewById(R.id.LogAdmin);

        logadm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_main2 = new Intent(getApplicationContext(), MainActivity3.class);
//                getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(activity_main2);
                finish();

            }


        });

                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Debut GET
                        String jsmail = email.getText().toString();
                        String jsmdp = password.getText().toString();
                        String url = "https://874c8381-7dbc-4718-85c8-1cde681efe65.mock.pstmn.io/connexion?email=" + jsmail + "&password=" + jsmdp;
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(MainActivity.this, response.trim(), Toast.LENGTH_SHORT).show();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
//                {
//                    @Override
//                    protected Map<String, String> getParams() {
//                        Map<String, String> params = new HashMap<String, String>();
//                        params.put("usernameEditText",user );
//                        params.put("passwordEditText",mdp );
//
//                        return params;
//
//                    }
//                };
                        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                        requestQueue.add(stringRequest);
                        //Fin Get



                        if (email.getText().length() > 0 && password.getText().length() > 0) {
                           // String toastMessage = "identifiant: " + email.getText().toString() + ", Mot De Passe: " + password.getText().toString();
                           // Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                            Intent activity_main2 = new Intent(getApplicationContext(), MainActivity2.class);
                            //getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            activity_main2.putExtra("nom", jsmail);
                            startActivity(activity_main2);
                            finish();


                        }


                        else {
                            String toastMessage = "Veuillez entrer un identifiant et un mot de passe";
                            Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                        }
                    }




                });



    }
}