package com.example.informationsecuriteimportant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity3 extends AppCompatActivity {
    String JSON_STRING="{\"info\":{\"succes\":\"true\",\"id\":4\",\"email\":test.agent1@test.fr\",\"mdp\":Agents\",\"prenom\":marcheA\",\"nom\":ok}}";
    String succes, id;
    TextView employeeName, employeeSalary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
// get the reference of TextView's
        employeeName=(TextView) findViewById(R.id.succes);
        employeeSalary=(TextView) findViewById(R.id.id);
        try {
            // get JSONObject from JSON file
            JSONObject obj=new JSONObject(JSON_STRING);
            // fetch JSONObject named employee
            JSONObject employee=obj.getJSONObject("info");
            // get employee name and salary
            succes=employee.getString("succes");
            id=employee.getString("id");
            // set employee name and salary in TextView's
            employeeName.setText("Succes: "+succes);
            employeeSalary.setText("id: "+id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
//        final Button returntButton=(Button)findViewById(R.id.activity_main_returnButton);
//        returntButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View w) {
//                Intent activity_main1 = new Intent(MainActivity3.this, MainActivity.class);
//                startActivity(activity_main1);
//
//            }
//        });
//
//
//    }
//}