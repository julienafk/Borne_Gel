package com.example.smartgel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity2 extends AppCompatActivity {
    //private Button returntButton;
    private TextView affichagePN;
    private Button returntButton;
    final String[] NomCadenas = new String[1];
    final int[] Actif = new int[1];
    String NomCad, Etat;
    TextView nomcad, etat, borne1, borne2, borne3;
    List<String> BorneListe = new ArrayList<String>();
    JSONArray ArrayBorne = null;
    int nbrBorne = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        //Debut GET https://874c8381-7dbc-4718-85c8-1cde681efe65.mock.pstmn.io/connexion http://51.210.151.13/btssnir/projets2022/fablab/api/cadenas/recuperer.php http://51.210.151.13/btssnir/projets2022/bornegel/api/api/bornes.php
        String url = "http://51.210.151.13/btssnir/projets2022/bornegel/api/api/bornes.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity2.this, response.trim(), Toast.LENGTH_SHORT).show();
                        if (response != null) {
                            //JSONObject jObject = null;
                            for (int i = 0; i < response.length(); i++){
                            try {
                                JSONArray responseObject = new JSONArray(response);
                                JSONObject obj = responseObject.getJSONObject(i);
                                Review review = new Review();
                                review.setCategory(obj.getString("id"));
                                review.setName(obj.getString("nivGel"));
                                review.setPhone(obj.getString("nivBat"));
                                review.setComment(obj.getString("salle"));
                                reviewList.add(review);
                                // get JSONObject from JSON file
                                //JSONObject objBorne =new JSONObject(response);
                                // fetch JSONObject named employee
                                //JSONObject jBorne = objBorne.getJSONObject("Borne");  jBorne.toJSONArray(jBorne.names());

//   j'utilise c'est 2 ligne                             ArrayBorne = new JSONArray(response);
//                                nbrBorne = ArrayBorne.length();

                                for (int i = 0; i < nbrBorne; i++) {
                                    Log.d("array", ArrayBorne.getString(i));
                                    //parcourBorneListe.add(ArrayBorne.getString(i));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
             // notifying list adapter about data changes
           // so that it renders the list view with updated data
             adapter.notifyDataSetChanged();
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity2.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_PHONENUMBER_USER, phoneNoofUser);
                return params;
            }
            };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);
        requestQueue.add(stringRequest);
// Fin GET
        ListView listView = findViewById(R.id.list_borne);

        List<String> list = new ArrayList<>();
//        Log.d("borne",nbrBorne);
        for (int i = 1; i <= 3; i++) {

            String nameBorne = "Borne " + String.valueOf(i);

            list.add(nameBorne);
            list.add(String.valueOf(nbrBorne));

        }


        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //click borne 1
                    startActivity(new  Intent(MainActivity2.this,Borne2Activity.class));
                } else if (position == 2) {
                    //click borne 2
                    startActivity(new  Intent(MainActivity2.this,Borne2Activity.class));
                } else {

                }
            }
        });
    }
}


//********************************************************************************************************
//        returntButton=(Button)findViewById(R.id.activity_main_returnButton);
//        returntButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View w) {
//                Intent activity_main2 = new Intent(MainActivity2.this, MainActivity.class);
//                startActivity(activity_main2);
//                finish();
//
//
//
//
