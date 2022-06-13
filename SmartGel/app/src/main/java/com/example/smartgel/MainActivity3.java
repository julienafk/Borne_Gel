package com.example.smartgel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.smartgel.databinding.ActivityMain3Binding;
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

public class MainActivity3 extends AppCompatActivity {
    private Button comptesbtn;
    ActivityMain3Binding binding;
    ArrayList<String> bornesList;
    ArrayAdapter<String> listAdapter;
    Handler mainHandler = new Handler();
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeBornesList();

        binding.actubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new actudata().start();
            }
        });

        binding.comptesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comptes = new Intent(getApplicationContext(),Comptes.class);
                startActivity(comptes);

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
                    progressDialog = new ProgressDialog(MainActivity3.this);
                    progressDialog.setMessage("Fetching Data");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            });

//https://api.npoint.io/51ea996647df8356b1ee https://www.npoint.io/   http://51.210.151.13/btssnir/projets2022/bornegel/api/bornes.php
            try {
                URL url = new URL("http://51.210.151.13/btssnir/projets2022/bornegel/api/bornes.php");
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

                    for (int i =0;i< bornes.length();i++){

                        JSONObject born = bornes.getJSONObject(i);

                        String id = "Borne N° " + born.getString("id");
                        String nivGel = "Niveau de gel  : " + born.getString("nivGel") + "\n" ;
                        String nivBat = "Niveau de batterie :  " + born.getString("nivBat")+ "\n";
                        String salle = "Salle  : " + born.getString("salle")+ "\n";
                        String etablissement = "établissement  :  " + born.getString("etablissement") + "\n" + "\n";
                        bornesList.add(id );
                        bornesList.add(nivGel );
                        bornesList.add(nivBat );
                        bornesList.add(salle );
                        bornesList.add(etablissement );

//                        JSONObject etablissements = bornes.getJSONObject(i);
//                        String etablissement = "établissement  :  " + etablissements.getString("etablissement") + "\n" + "\n";
//                        bornesList.add(etablissement );
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


//package com.example.smartgel;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ExpandableListView;
//import android.widget.ListView;
//
//import com.example.smartgel.databinding.ActivityComptesBinding;
//
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//
//public class Comptes extends AppCompatActivity {
//    private Button bornesbtn;
//    ActivityComptesBinding binding;
//    ExpandableListView expendableListView;
//    ArrayList<String> comptesList;
//    ArrayAdapter<String> listAdapter;
//    Handler mainHandler = new Handler();
//    ProgressDialog progressDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityComptesBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        initializecCompteList();
//        binding.actubtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                new actudata().start();
//            }
//        });
//
//        binding.bornesbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent bornes = new Intent(getApplicationContext(),MainActivity3.class);
//                startActivity(bornes);
//            }
//        });
//    }
//    private void initializecCompteList() {
//
//        comptesList = new ArrayList<>();
//        listAdapter  = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,comptesList);
//        binding.comptesList.setAdapter(listAdapter);
//
//    }
//    ArrayAdapter adapter = new ArrayAdapter<String>(
//            this,
//            android.R.layout.simple_list_item_1,comptesList);
//
//
//
//    class actudata extends Thread{
//        String data = "";
//
//        @Override
//        public void run() {
//            mainHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    progressDialog = new ProgressDialog(Comptes.this);
//                    progressDialog.setMessage("Fetching Data");
//                    progressDialog.setCancelable(false);
//                    progressDialog.show();
//                }
//            });
//
////https://api.npoint.io/abb268c49134d4ea46b0 https://www.npoint.io/   http://51.210.151.13/btssnir/projets2022/bornegel/api/api/bornes.php
//            try {
//                URL url = new URL("http://51.210.151.13/btssnir/projets2022/bornegel/api/comptes.php");
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                String line;
//
//                while ((line = bufferedReader.readLine())!= null){
//                    data = data + line;
//                }
//
//
//                if (!data.isEmpty()){
//                    JSONObject jsonObject  = new JSONObject(data);
//                    JSONArray comptes = jsonObject.getJSONArray("Comptes");
//                    comptesList.clear();
//
//                    for (int i =0;i< comptes.length();i++){
//
//                        JSONObject ids = comptes.getJSONObject(i);
//                        String id = "ID  :  " + ids.getString("id");
//                        comptesList.add(id );
//
//                        JSONObject noms = comptes.getJSONObject(i);
//                        String nom ="Nom  :  " + noms.getString("nom");
//                        comptesList.add(nom );
//
//                        JSONObject prenoms = comptes.getJSONObject(i);
//                        String prenom = "Prenom  :  " +  prenoms.getString("prenom");
//                        comptesList.add(prenom );
//
//                        JSONObject emails = comptes.getJSONObject(i);
//                        String email = "adresse mail  :  " +  emails.getString("email");
//                        comptesList.add(email );
//
//                        JSONObject passwords = comptes.getJSONObject(i);
//                        String password = "Password  :  " +  passwords.getString("password");
//                        comptesList.add(password );
//
//                        JSONObject grades = comptes.getJSONObject(i);
//                        String grade = "Grade  :  " +  grades.getString("grade")+ "\n" + "\n";
//                        comptesList.add(grade );
//                    }
//
//                }
//
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            mainHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    if(progressDialog.isShowing())
//                        progressDialog.dismiss();
//                    listAdapter.notifyDataSetChanged();
//                }
//            });
//
//
//
//        }
//    }
//
//
//}