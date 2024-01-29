package com.example.smart_tour_guide_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.smart_tour_guide_application.helpers.StringHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Login extends AppCompatActivity {


    Button sign_in_btn;
    EditText et_email, et_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = findViewById(R.id.email);
        et_password = findViewById(R.id.password);

        sign_in_btn = findViewById(R.id.sign_in_btn);

        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });

    }

    public void authenticateUser(){

        if ( !validateEmail() || !validatePassword()){
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(Login.this);

        String url = "http://192.168.91.226:8080/smart/user/login";

        //Set Parameters;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", et_email.getText().toString());
        params.put("password", et_password.getText().toString());

        //Set Request Object
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //Get Values From Response Object
                    String first_name = (String) response.get("first_name");
                    String last_name = (String) response.get("last_name");
                    String email = (String) response.get("email");

                    //Set Intent Actions:
                    Intent goToProfile = new Intent(Login.this, Dashboard.class);
                    //Start Activity
                    startActivity(goToProfile);
                    finish();
                }catch (JSONException e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println(error.getMessage());
                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG).show();
            }
        });

        queue.add(jsonObjectRequest);

    }
    public void goToHome(View view){
        Intent intent = new Intent (Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }



    public void goToSignUpAct(View view){
        Intent intent = new Intent (Login.this, Registration.class);
        startActivity(intent);
        finish();
    }

    //End of Go to Sign Up Intent Method

    public boolean validateEmail(){
        String email = et_email.getText().toString();

        //check if email is Empty
        if (email.isEmpty()){
            et_email.setError("Email cannot be Empty");
            return false;
        } else if (StringHelper.regexEmailValidationPattern(email)){
            et_email.setError("Please Enter Valid Email");
            return false;
        } else {
            et_email.setError(null);
            return true; //Check If Email Is Empty
        }
    }
    //End of validate Email field

    public boolean validatePassword(){
        String password_p = et_password.getText().toString();

        //check if Password  field is empty
        if (password_p.isEmpty()){
            et_password.setError("Password field cannot be Empty!");
            return false;
        }else {
            et_password.setError(null);
            return true;
        }
    }
}