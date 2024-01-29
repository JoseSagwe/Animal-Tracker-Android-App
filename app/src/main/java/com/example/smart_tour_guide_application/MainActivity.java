package com.example.smart_tour_guide_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button get_started_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //End of On Create Method


    public void goRegistration(View view){
        Intent intent = new Intent(MainActivity.this, Registration.class);
        startActivity(intent);
        finish();

    }
    //End of Registration Activity

}