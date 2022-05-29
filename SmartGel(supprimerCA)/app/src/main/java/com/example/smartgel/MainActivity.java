package com.example.smartgel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.smartgel.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<String> bornesList;
    ArrayAdapter<String> listAdapter;
    Handler mainHandler = new Handler();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeBornesList();
        binding.actubtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new actudata().start();
            }
        });

    }

    private void initializeBornesList() {
        bornesList = new ArrayList<>();
        listAdapter  = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,bornesList);
        binding.bornesList.setAdapter(listAdapter);
    }

    class actudata extends Thread{
        String data = "";

        @Override
        public void run() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Fetching Data");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            });

//https://api.npoint.io/13c6b831870ce04ea29a https://www.npoint.io/   http://51.210.151.13/btssnir/projets2022/bornegel/api/api/bornes.php
            try {
                URL url = new URL("https://api.npoint.io/13c6b831870ce04ea29a");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = bufferedReader.readLine())!= null){
                    data = data + line;
                }

                if (!data.isEmpty()){
                    JSONObject jsonObject  = new JSONObject(data);
                    JSONArray bornes = jsonObject.getJSONArray("Bornes");
                    bornesList.clear();

//     le code d'origine fonctionnel               for (int i =0;i< bornes.length();i++){
//                        JSONObject etablissements = bornes.getJSONObject(i);
//                        String etablissement = etablissements.getString("etablissement");
//                        bornesList.add(etablissement );
//                    }

                    for (int i =0;i< bornes.length();i++){

                        JSONObject ids = bornes.getJSONObject(i);
                        String id = ids.getString("id");
                        bornesList.add(id );

                        JSONObject nivGels = bornes.getJSONObject(i);
                        String nivGel = nivGels.getString("nivGel");
                        bornesList.add(nivGel );

                        JSONObject nivBats = bornes.getJSONObject(i);
                        String nivBat = nivBats.getString("nivBat");
                        bornesList.add(nivBat );

                        JSONObject salles = bornes.getJSONObject(i);
                        String salle = salles.getString("salle");
                        bornesList.add(salle );

                        JSONObject etablissements = bornes.getJSONObject(i);
                        String etablissement = etablissements.getString("etablissement");
                        bornesList.add(etablissement );
                    }

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    listAdapter.notifyDataSetChanged();
                }
            });


        }
    }


}