package com.example.smartgel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.smartgel.databinding.ActivityMainBinding;
import com.example.smartgel.databinding.ActivityUserBinding;

public class UserActivity extends AppCompatActivity {

    ActivityUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null){

            String name = intent.getStringExtra("name");
            String Email = intent.getStringExtra("Email");
            String UAid = intent.getStringExtra("ID Compte UA");
            int imageid = intent.getIntExtra("imageid",R.drawable.a);

            binding.nameProfile.setText(name);
            binding.Email.setText(Email);
            binding.UAid.setText(UAid);
            binding.profileImage.setImageResource(imageid);


        }

    }
}