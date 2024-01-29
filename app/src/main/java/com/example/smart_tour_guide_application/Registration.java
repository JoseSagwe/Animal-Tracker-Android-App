package com.example.smart_tour_guide_application;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smart_tour_guide_application.helpers.StringHelper;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    EditText first_name, last_name, email, dob, location, mobile, password, confirm;

    Button sign_up_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        dob = findViewById(R.id.dob);
        location = findViewById(R.id.location);
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);


        sign_up_btn = findViewById(R.id.sign_up_btn);

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processFormFields();
            }
        });

    }

    public void goToHome(View view){
        Intent intent = new Intent (Registration.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToSignInAct(View view){
        Intent intent = new Intent (Registration.this, Login.class);
        startActivity(intent);
        finish();
    }


    public void processFormFields(){
        //Check for Errors
        if (!validateFirstName() || !validateLastName() || !validateEmail() || !validatePasswordAndConfirm()){
            return;
        }
        //End of Check for Errors

        //Instantiate The request queue
        RequestQueue queue = Volley.newRequestQueue(Registration.this);
        //The URL Posting TO:
        String url = "http://192.168.91.226:8080/smart/user/register";

        //String Request Object
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Registration", "Response: " + response);
                if (response.equalsIgnoreCase("success")){
                    first_name.setText(null);
                    last_name.setText(null);
                    email.setText(null);
                    dob.setText(null);
                    location.setText(null);
                    mobile.setText(null);
                    password.setText(null);
                    confirm.setText(null);
                    Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("Registration", "Error during network request", error);
                System.out.println(error.getMessage());
                Toast.makeText(Registration.this, "Registration Un-successful", Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("first_name", first_name.getText().toString());
                params.put("last_name", last_name.getText().toString());
                params.put("email", email.getText().toString());
                params.put("dob", dob.getText().toString());
                params.put("location", location.getText().toString());
                params.put("mobile", mobile.getText().toString());
                params.put("password", password.getText().toString());
                return params;
            }
        };

        queue.add(stringRequest);
    }


    public boolean validateFirstName(){
        String firstName = first_name.getText().toString();

        //check if First name is Empty
        if (firstName.isEmpty()){
            first_name.setError("First name cannot be Empty");
            return false;
        } else {
            first_name.setError(null);
            return true;
        }
    }
    //End of validate First name field

    public boolean validateLastName(){
        String lastName = last_name.getText().toString();

        //check if Last name is Empty
        if (lastName.isEmpty()){
            last_name.setError("Last name cannot be Empty");
            return false;
        } else {
            last_name.setError(null);
            return true;
        }
    }


        //Date Of Birth
    public boolean validateDateOfBirth(){
        String dateOfBirth = dob.getText().toString();

        if (dateOfBirth.isEmpty()){
            dob.setError("Date Of Birth cannot be Empty");
            return false;
        } else {
            dob.setError(null);
            return true;
        }
    }

    //Location
    public boolean validateLocation(){
        String location_l = location.getText().toString();

        if (location_l.isEmpty()){
            location.setError("Location cannot be Empty");
            return false;
        } else {
            location.setError(null);
            return true;
        }
    }


        //Mobile
    public boolean validateMobile(){
        String p_mobile = mobile.getText().toString();

        if (p_mobile.isEmpty()){
            mobile.setError("Mobile Number cannot be Empty");
            return false;
        } else {
            mobile.setError(null);
            return true;
        }
    }



   //Email
    public boolean validateEmail(){
        String email_e = email.getText().toString();

        //check if email is Empty
        if (email_e.isEmpty()){
            email.setError("Email cannot be Empty");
            return false;
        } else if (StringHelper.regexEmailValidationPattern(email_e)){
            email.setError("Please Enter Valid Email");
            return false;
        } else {
            email.setError(null);
            return true; //Check if Email Is empty
        }
    }
    //End of validate Email field

    public boolean validatePasswordAndConfirm(){
        String password_p = password.getText().toString();
        String confirm_p = confirm.getText().toString();

        //check if Password and Confirm fields are empty
        if (password_p.isEmpty()){
            password.setError("Password field cannot be Empty!");
            return false;
        } else if (!password_p.equals(confirm_p)){
            password.setError("Password do not match!");
            return false;
        }else if (confirm_p.isEmpty()) {
            confirm.setError("Confirm field cannot be Empty!");
            return false;
        }else {
            password.setError(null);
            confirm.setError(null);
            return true;
        }
    }

}