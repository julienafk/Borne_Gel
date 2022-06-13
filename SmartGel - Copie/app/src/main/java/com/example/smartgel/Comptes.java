package com.example.smartgel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.smartgel.databinding.ActivityComptesBinding;
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

public class Comptes extends AppCompatActivity {
    private Button comptesbtn;
    ActivityComptesBinding binding;
    ArrayList<String> comptesList;
    ArrayAdapter<String> listAdapter;
    Handler mainHandler = new Handler();
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComptesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeBornesList();

        binding.actubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new actudata().start();
            }
        });


        binding.bornesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent borne = new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(borne);
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
        comptesList = new ArrayList<>();
        listAdapter  = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,comptesList);
        binding.comptesList.setAdapter(listAdapter);
    }

    class actudata extends Thread{
        String data = "";

        @Override
        public void run() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressDialog = new ProgressDialog(Comptes.this);
                    progressDialog.setMessage("Fetching Data");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            });

//https://api.npoint.io/51ea996647df8356b1ee https://www.npoint.io/   http://51.210.151.13/btssnir/projets2022/bornegel/api/bornes.php
            try {
                URL url = new URL("http://51.210.151.13/btssnir/projets2022/bornegel/api/comptes.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = bufferedReader.readLine())!= null){
                    data = data + line;
                }

                if (!data.isEmpty()){
                    JSONObject jsonObject  = new JSONObject(data);
                    JSONArray comptes = jsonObject.getJSONArray("Comptes");
                    comptesList.clear();



                    for (int i =0;i< comptes.length();i++){

                        JSONObject ids = comptes.getJSONObject(i);
                        String id = "ID  :  " + ids.getString("id");
                        comptesList.add(id );

                        JSONObject noms = comptes.getJSONObject(i);
                        String nom ="Nom  :  " + noms.getString("nom");
                        comptesList.add(nom );

                        JSONObject prenoms = comptes.getJSONObject(i);
                        String prenom = "Prenom  :  " +  prenoms.getString("prenom");
                        comptesList.add(prenom );

                        JSONObject emails = comptes.getJSONObject(i);
                        String email = "adresse mail  :  " +  emails.getString("email");
                        comptesList.add(email );

                        JSONObject passwords = comptes.getJSONObject(i);
                        String password = "Password  :  " +  passwords.getString("password");
                        comptesList.add(password );

                        JSONObject grades = comptes.getJSONObject(i);
                        String grade = "Grade  :  " +  grades.getString("grade")+ "\n" + "\n";
                        comptesList.add(grade );

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


//    JSONArray comptes = jsonObject.getJSONArray("Comptes");
//                    comptesList.clear();
//
//                            for (int i =0;i< comptes.length();i++){
//
//        JSONObject ids = comptes.getJSONObject(i);
//        String id = "ID  :  " + ids.getString("id");
//        comptesList.add(id );
//
//        JSONObject noms = comptes.getJSONObject(i);
//        String nom ="Nom  :  " + noms.getString("nom");
//        comptesList.add(nom );
//
//        JSONObject prenoms = comptes.getJSONObject(i);
//        String prenom = "Prenom  :  " +  prenoms.getString("prenom");
//        comptesList.add(prenom );
//
//        JSONObject emails = comptes.getJSONObject(i);
//        String email = "adresse mail  :  " +  emails.getString("email");
//        comptesList.add(email );
//
//        JSONObject passwords = comptes.getJSONObject(i);
//        String password = "Password  :  " +  passwords.getString("password");
//        comptesList.add(password );
//
//        JSONObject grades = comptes.getJSONObject(i);
//        String grade = "Grade  :  " +  grades.getString("grade")+ "\n" + "\n";
//        comptesList.add(grade );
//        }
//
//        }




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

















































//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.database.DataSetObserver;
//import android.graphics.Color;
//import android.graphics.drawable.Drawable;
//import android.os.Bundle;
////import android.support.v7.app.AppCompatActivity;
//import android.os.Handler;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ExpandableListAdapter;
//import android.widget.ExpandableListView;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.example.smartgel.databinding.ActivityComptesBinding;
//
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//
//public class Comptes extends AppCompatActivity {
//    private Button bornesbtn;
//    ActivityComptesBinding binding;
//    ExpandableListView expendableListView;
//    ArrayList<String> comptesList;
//    ArrayAdapter<String> listAdapter;
//    Handler mainHandler = new Handler();
//    ProgressDialog progressDialog;
////
//
//    // Store group data.
////    private List<String> groupList = null;
//    JSONArray comptes = null;
//    // Store child data. Key is the group value, Value is the child data in a list.
//    private Map<String, List<String>> childListMap = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityComptesBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        binding.actuBbtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                new actuData().start();
//            }
//        });
//
//        binding.bornesbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent bornes = new Intent(getApplicationContext(),MainActivity3.class);
//                startActivity(bornes);
//
//            }
//        });
//
//
////        -----------------------------------------------------------
//
//        setContentView(R.layout.activity_comptes);
//
//        setTitle("Liste des Comptes");
////
////        this.addUserInfo("Richard ", 35, "1", "jerry@dev2qa.com");
////        this.addUserInfo("Richard", 25, "Engineer", "richard@hotmail.com");
////        this.addUserInfo("Tom", 35, "Engineer", "tom@dev2qa.com");
////        this.addUserInfo("Jack", 25, "Tester", "jack@dev2qa.com");
////        this.addUserInfo("Kevin", 35, "Tester", "kevin@dev2qa.com");
//
//        // Create an ExpandableListAdapter object, this object will be used to provide data to ExpandableListView.
//        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter() {
//            @Override
//            public void registerDataSetObserver(DataSetObserver dataSetObserver) {
//            }
//
//            @Override
//            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
//            }
//
//            @Override
//            public int getGroupCount() {
//                return comptes.length();
//            }
//
//            @Override
//            public int getChildrenCount(int groupIndex) {
//                String group = null;
//                try {
//                    group = comptes.get(groupIndex).toString();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                List<String> childInfoList = childListMap.get(group);
//                return childInfoList.size();
//            }
//
//            @Override
//            public Object getGroup(int groupIndex) {
//                try {
//                    return comptes.get(groupIndex);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//            @Override
//            public Object getChild(int groupIndex, int childIndex) {
//                String group = null;
//                try {
//                    group = comptes.get(groupIndex).toString();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                List<String> childInfoList = childListMap.get(group);
//                return childInfoList.get(childIndex);
//            }
//
//            @Override
//            public long getGroupId(int groupIndex) {
//                return groupIndex;
//            }
//
//            @Override
//            public long getChildId(int groupIndex, int childIndex) {
//                return childIndex;
//            }
//
//            @Override
//            public boolean hasStableIds() {
//                return true;
//            }
//
//            // This method will return a View object displayed in group list item.
//            @Override
//            public View getGroupView(int groupIndex, boolean isExpanded, View view, ViewGroup viewGroup) {
//                // Create the group view object.
//                LinearLayout groupLayoutView = new LinearLayout(Comptes.this);
//                groupLayoutView.setOrientation(LinearLayout.HORIZONTAL);
//
//                // Create and add an imageview in returned group view.
//                ImageView groupImageView = new ImageView(Comptes.this);
//                if(isExpanded) {
//                    groupImageView.setImageResource(R.mipmap.ic_launcher_round);
//                }else
//                {
//                    groupImageView.setImageResource(R.mipmap.ic_launcher);
//                }
//                groupLayoutView.addView(groupImageView);
//
//                // Create and add a textview in returned group view.
//                String groupText = null;
//                try {
//                    groupText = comptes.get(groupIndex).toString();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                TextView groupTextView = new TextView(Comptes.this);
//                groupTextView.setText(groupText);
//                groupTextView.setTextSize(30);
//                groupLayoutView.addView(groupTextView);
//
//                return groupLayoutView;
//            }
//
//            // This method will return a View object displayed in child list item.
//            @Override
//            public View getChildView(int groupIndex, int childIndex, boolean isLastChild, View view, ViewGroup viewGroup) {
//                // First get child text/
//                Object childObj = this.getChild(groupIndex, childIndex);
//                String childText = (String)childObj;
//
//                // Create a TextView to display child text.
//                TextView childTextView = new TextView(Comptes.this);
//                childTextView.setText(childText);
//                childTextView.setTextSize(20);
//                childTextView.setBackgroundColor(Color.BLUE);
//
//                // Get group image width.
//                Drawable groupImage = getDrawable(R.mipmap.ic_launcher);
//                int groupImageWidth = groupImage.getIntrinsicWidth();
//
//                // Set child textview offset left. Then it will align to the right of the group image.
//                childTextView.setPadding(groupImageWidth,0,0,0);
//
//                return childTextView;
//            }
//
//            @Override
//            public boolean isChildSelectable(int groupIndex, int childIndex) {
//                return false;
//            }
//
//            @Override
//            public boolean areAllItemsEnabled() {
//                return false;
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public void onGroupExpanded(int groupIndex) {
//
//            }
//
//            @Override
//            public void onGroupCollapsed(int groupIndex) {
//
//
//            }
//
//            @Override
//            public long getCombinedChildId(long groupIndex, long childIndex) {
//                return 0;
//            }
//
//            @Override
//            public long getCombinedGroupId(long groupIndex) {
//                return 0;
//            }
//        };
//
//
//
//        final ExpandableListView expandableListView = (ExpandableListView)findViewById(R.id.comptesList);
//        expandableListView.setAdapter(expandableListAdapter);
//
//        // Add on group expand listener.
//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int groupIndex) {
//                // Get total group size.
//                int comptesSize = comptes.length();
//
//                // Close other expanded group.
//                for(int i=0;i < comptesSize; i++) {
//                    if(i!=groupIndex) {
//                        expandableListView.collapseGroup(i);
//                    }
//                }
//            }
//        });
//    }
//
//
//
//    // Add user info in group and child object.
//    private void addUserInfo(String id, String nom, String prenom, String email, String password, String grade)
//    {
//        if(this.comptes == null)
//        {
//            this.comptes = new JSONArray();
//        }
//
//        if(this.childListMap == null)
//        {
//            this.childListMap = new HashMap<String, List<String>>();
//        }
//
////        if(!this.comptes.contains(prenom)) {
////            this.comptes.add(prenom);
////        }
//
//        // Create child list.
//        List<String> childList = new ArrayList<String>();
//        childList.add("Id : " + id);
//        childList.add("Nom : " + nom);
//        childList.add("Prenom : " + prenom);
//        childList.add("Email : " + email);
//        childList.add("Password : " + password);
//        childList.add("Grade : " + grade);
//        // Add child data list in the map, key is group name.
//        this.childListMap.put(prenom, childList);
//    }
//
//    class actuData extends Thread{
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
//
//                    for (int i =0;i< comptes.length();i++){
//
//                        JSONObject ids = comptes.getJSONObject(i);
//                        String id = ids.getString("id");
//                        comptesList.add(id );
//
//                        JSONObject noms = comptes.getJSONObject(i);
//                        String nom = noms.getString("nom");
//                        comptesList.add(nom );
//
//                        JSONObject prenoms = comptes.getJSONObject(i);
//                        String prenom = prenoms.getString("prenom");
//                        comptesList.add(prenom );
//
//                        JSONObject emails = comptes.getJSONObject(i);
//                        String email = emails.getString("email");
//                        comptesList.add(email );
//
//                        JSONObject passwords = comptes.getJSONObject(i);
//                        String password = passwords.getString("password");
//                        comptesList.add(password );
//
//                        JSONObject grades = comptes.getJSONObject(i);
//                        String grade = grades.getString("grade");
//                        comptesList.add(grade );
//
//                        addUserInfo(id, nom, prenom, email,password,grade);
//                    }
//
////                    addUserInfo("Richard ", 35, "1", "jerry@dev2qa.com");
////                    addUserInfo("Richard", 25, "Engineer", "richard@hotmail.com");
////                    addUserInfo("Tom", 35, "Engineer", "tom@dev2qa.com");
////                    addUserInfo("Jack", 25, "Tester", "jack@dev2qa.com");
////                    addUserInfo("Kevin", 35, "Tester", "kevin@dev2qa.com");
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
//        }
//    }
//}



