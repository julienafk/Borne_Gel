package com.example.custom_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;


import com.example.custom_listview.databinding.ActivityMainBinding;
import com.example.custom_listview.databinding.ActivityUserBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageId = {R.drawable.compte,R.drawable.compte,R.drawable.compte,R.drawable.d,R.drawable.e,
                R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i};
        String[] name = {"Christopher","Craig","Sergio","Mubariz","Mike","Michael","Toa","Ivana","Alex"};
        String[] Prenom = {"Prenom","Prenom","povoda2847@game4hr.com","povoda2847@game4hr.com","povoda2847@game4hr.com",
                "povoda2847@game4hr.com","povoda2847@game4hr.com","povoda2847@game4hr.com","povoda2847@game4hr.com"};
        String[] ID = {"1","2","3","4","5","6","7","13","9"};
        String[] email = {"povoda2847@game4hr.com","povoda2847@game4hr.com","povoda2847@game4hr.com","povoda2847@game4hr.com","povoda2847@game4hr.com",
                "povoda2847@game4hr.com","povoda2847@game4hr.com","povoda2847@game4hr.com","povoda2847@game4hr.com"};
        String[] idCompt = {"1","2","3","4","5","6","7","13","9"};

        ArrayList<User> userArrayList = new ArrayList<>();

        for(int i = 0;i< imageId.length;i++){

            User user = new User(name[i],Prenom[i],ID[i],email[i],idCompt[i],imageId[i]);
            userArrayList.add(user);

        }


        ListAdapter listAdapter = new ListAdapter(MainActivity.this,userArrayList);

        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);



        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this,UserActivity.class);
                i.putExtra("name",name[position]);
                i.putExtra("Prenom",Prenom[position]);
                i.putExtra("ID",ID[position]);
                i.putExtra("imageid",imageId[position]);
                startActivity(i);

            }
        });

    }
}