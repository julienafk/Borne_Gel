package com.example.informationsecuriteimportant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final Button returntButton=(Button)findViewById(R.id.activity_main_returnButton);
        returntButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                Intent activity_main1 = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(activity_main1);

            }
        });


    }
}