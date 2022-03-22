package com.example.informationsecuriteimportant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.telecom.Call;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ImageButton logadm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.activity_main_usernameEditText);
        passwordEditText = findViewById(R.id.activity_main_passwordEditText);
        loginButton = findViewById(R.id.activity_main_loginButton);
        logadm = findViewById(R.id.LogAdmin);

        logadm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity_main2 = new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(activity_main2);
                finish();

            }


        });

                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user = usernameEditText.getText().toString();
                        String mdp = passwordEditText.getText().toString();
                        String url = "https://874c8381-7dbc-4718-85c8-1cde681efe65.mock.pstmn.io/ProjetBorne/api/Users?usernameEditText=" + user + "&passwordEditText=" + mdp;
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


                        if (usernameEditText.getText().length() > 0 && passwordEditText.getText().length() > 0) {
                            String toastMessage = "identifiant: " + usernameEditText.getText().toString() + ", Mot De Passe: " + passwordEditText.getText().toString();
                            Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();

                            Intent activity_main2 = new Intent(getApplicationContext(), MainActivity2.class);
                            startActivity(activity_main2);
                            finish();
                        } else {
                            String toastMessage = "Veuillez entrer un identifiant et un mot de passe";
                            Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                        }
                    }


                });



    }
}