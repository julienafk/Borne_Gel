package com.example.smartgel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.smartgel.databinding.ActivityMain2Binding;

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

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    ArrayList<String> bornesList;
    ArrayAdapter<String> listAdapter;
    Handler mainHandler = new Handler();
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeBornesList();

        binding.actubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new actudata().start();
            }
        });


        binding.deco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deco = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(deco);

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
                    progressDialog = new ProgressDialog(MainActivity2.this);
                    progressDialog.setMessage("Fetching Data");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            });

//https://api.npoint.io/0c8748cad821dd8fd182 https://www.npoint.io/   https://874c8381-7dbc-4718-85c8-1cde681efe65.mock.pstmn.io/borneAssignee?idAgent=7'
            try {
                URL url = new URL("http://51.210.151.13/btssnir/projets2022/bornegel/api/agent.php?idAgent=5");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = bufferedReader.readLine())!= null){
                    data = data + line;
                }

                if (!data.isEmpty()){
                    JSONObject jsonObject  = new JSONObject(data);
                    JSONArray bornes = jsonObject.getJSONArray("Borne attribué");
                    bornesList.clear();

                    for (int i =0;i< bornes.length();i++){

                        JSONObject ids = bornes.getJSONObject(i);
                        String id = "Borne N° " + ids.getString("id");
                        bornesList.add(id );

                        JSONObject nivGels = bornes.getJSONObject(i);
                        String nivGel = "Niveau de gel  : " + nivGels.getString("nivGel") + "\n" ;
                        bornesList.add(nivGel );

                        JSONObject nivBats = bornes.getJSONObject(i);
                        String nivBat = "Niveau de batterie :  " + nivBats.getString("nivBat")+ "\n";
                        bornesList.add(nivBat );

                        JSONObject salles = bornes.getJSONObject(i);
                        String salle = "Salle  : " + salles.getString("salle")+ "\n";
                        bornesList.add(salle );

                        JSONObject etablissements = bornes.getJSONObject(i);
                        String etablissement = "établissement  :  " + etablissements.getString("etablissement") + "\n" + "\n";
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