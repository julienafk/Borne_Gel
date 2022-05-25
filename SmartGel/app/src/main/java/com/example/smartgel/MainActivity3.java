package com.example.smartgel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class MainActivity3 extends AppCompatActivity {
//    String JSON_STRING="{\"info\":{\"succes\":\"true\",\"id\":4\",\"email\":test.agent1@test.fr\",\"mdp\":Agents\",\"prenom\":marcheA\",\"nom\":ok}}";
//    String succes, id,email;
//     TextView  employeeSucces, employeeID, employeeMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
//
//        ListView listView = findViewById(R.id.list_borne);
//
//        List<String> list = new ArrayList<>();
//        for (int i=1;i<=nbrBorne;i++)
//        {
//            list.add("Borne".i);
//        }
//
//
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(arrayAdapter);

    }
}










//// get the reference of TextView's
//        employeeSucces=(TextView) findViewById(R.id.succes);
//        employeeID=(TextView) findViewById(R.id.id);
//        employeeMail=(TextView) findViewById(R.id.mail);
//        try {
//            // get JSONObject from JSON file
//            JSONObject obj=new JSONObject(JSON_STRING);
//            // fetch JSONObject named employee
//            JSONObject employee=obj.getJSONObject("info");
//            // get employee name and salary
//            succes=employee.getString("succes");
//            id=employee.getString("id");
//            email=employee.getString("email");
//            // set employee name and salary in TextView's
//            employeeSucces.setText("Succes: "+succes);
//            employeeID.setText("id: "+id);
//            employeeMail.setText("mail: "+email);
//
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//}
