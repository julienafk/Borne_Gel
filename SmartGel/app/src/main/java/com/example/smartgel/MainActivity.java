package com.example.smartgel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import java.io.Serializable;
import android.content.Intent;
import android.os.Bundle;
import android.os.Build;
import android.os.Parcelable;
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
import org.json.JSONException;
import org.json.JSONObject;
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
        final boolean[]succes = new boolean[1] ;
        final int[] grade = new int[1];


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Debut GET https://874c8381-7dbc-4718-85c8-1cde681efe65.mock.pstmn.io/connexion http://51.210.151.13/btssnir/projets2022/bornegel/api/api/connexion.php  http://51.210.151.13/btssnir/projets2022/fablab/api/connexion.php
                String jsmail = email.getText().toString();
                String jsmdp = password.getText().toString();
                String url = "http://51.210.151.13/btssnir/projets2022/bornegel/api/api/connexion.php?email=" + jsmail + "&password=" + jsmdp;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                               // Toast.makeText(MainActivity.this, response.trim(), Toast.LENGTH_SHORT).show();
                                if(response!=null){
                                    JSONObject jObject = null;
                                    try {
                                        jObject = new JSONObject(response);
                                        succes[0] = jObject.getBoolean("success");
                                        grade[0] = jObject.getInt("grade");
                                        System.out.println("test : "+ succes[0]);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                                if(succes[0]==true) {
                                    if (grade[0] == 1) {
                                        Intent activity_main2 = new Intent(MainActivity.this, MainActivity2.class);
                                        activity_main2.putExtra("email", jsmail);
                                        startActivity(activity_main2);
                                        finish();
                                    } else if (grade[0] == 2) {
                                        Intent activity_main2 = new Intent(MainActivity.this, MainActivity2.class);
                                        activity_main2.putExtra("email", jsmail);
                                        startActivity(activity_main2);
                                        finish();
                                    } else if (grade[0] == 3) {
                                        Intent activity_main2 = new Intent(MainActivity.this, MainActivity2.class);
                                        activity_main2.putExtra("email", jsmail);
                                        startActivity(activity_main2);
                                        finish();
                                    } else if (grade[0] == 4) {
                                        Intent activity_main2 = new Intent(MainActivity.this, MainActivity2.class);
                                        activity_main2.putExtra("email", jsmail);
                                        startActivity(activity_main2);
                                        finish();
                                    } else {
                                        Toast.makeText(MainActivity.this, "erreur", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                else
                                {
                                    Toast.makeText(MainActivity.this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                                }
                                //Toast.makeText(MainActivity.this, url.toString(), Toast.LENGTH_SHORT).show();

                            }
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

                if (email.getText().length() > 0 && password.getText().length() > 0) {
                    String toastMessage ="identifiant: " + email.getText().toString() + ", Mot De Passe: " + password.getText().toString();
                    // Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                    //getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                }
            }

        });

        logadm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_main2 = new Intent(getApplicationContext(), MainActivity3.class);
//                getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(activity_main2);
                finish();
            }
        });

    }
}

