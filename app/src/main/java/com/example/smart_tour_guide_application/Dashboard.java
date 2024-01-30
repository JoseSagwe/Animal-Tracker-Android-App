package com.example.smart_tour_guide_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    CardView logoutCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Hook Sign logoutCardView
        logoutCardView = findViewById(R.id.logoutCardView);


        //Set on click Listener
        logoutCardView.setOnClickListener(new View.OnClickListener() {
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