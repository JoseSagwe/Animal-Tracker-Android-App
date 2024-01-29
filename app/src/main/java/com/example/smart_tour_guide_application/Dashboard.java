package com.example.smart_tour_guide_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    TextView tv_first_name, tv_last_name, tv_dob, tv_location, tv_mobile, tv_email;
    Button sign_out_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Hook Sign Out Button
        sign_out_btn = findViewById(R.id.sign_out_btn);

        //Set on click Listener
        sign_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUserOut();
            }
        });
    }


    public void signUserOut(){

        //Return User back to home page
        Intent goToHome = new Intent(Dashboard.this, MainActivity.class);
        startActivity(goToHome);
        finish();
    }
}