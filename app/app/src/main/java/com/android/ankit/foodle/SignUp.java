package com.android.ankit.foodle;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SignUp extends AppCompatActivity {


    private RequestQueue requestQueue;

    final Calendar calendar = Calendar.getInstance();

    Button signUpButton;
    TextView login;
    EditText mName, mEmail, mHeight, mWeight, mDob, mGender, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);

        mName = findViewById(R.id.sign_up_name);
        mEmail = findViewById(R.id.sign_up_email);
        mHeight = findViewById(R.id.height);
        mWeight = findViewById(R.id.weight);
        mDob = findViewById(R.id.dob);
        mGender = findViewById(R.id.gender);
        mPassword = findViewById(R.id.sign_up_password);

        signUpButton = findViewById(R.id.signUpButton);

        login = findViewById(R.id.loginText);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "YY/MM/dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                mDob.setText(sdf.format(calendar.getTime()));
            }
        };

        mDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SignUp.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name, email, pwd, dob, height, weight, gender;
                name = mName.getText().toString();
                email = mEmail.getText().toString();
                pwd = mPassword.getText().toString();
                dob = mDob.getText().toString();
                height = mHeight.getText().toString();
                weight = mWeight.getText().toString();
                gender = mGender.getText().toString();

                if (!(name.equals("") || email.equals("") || pwd.equals("") || dob.equals("") || height.equals("") || weight.equals("") || gender.equals(""))) {
                    String url = "http://192.168.43.222:8080/signup";
                    requestQueue = VolleySingleton.getInstance(SignUp.this).getRequestQueue();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(SignUp.this, response, Toast.LENGTH_SHORT).show();
                            Log.i("My success", "" + response);

                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                if (jsonObject.getString("status").equals("success")) {
                                    finish();
                                } else {
                                    AlertDialog alert = builder.create();
                                    alert.setTitle(jsonObject.getString("status"));
                                    alert.setMessage("Error: Something is wrong in your data");
                                    alert.setCancelable(true);
                                    alert.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            AlertDialog alert = builder.create();
                            alert.setTitle("Error");
                            alert.setMessage("Something went wrong");
                            alert.setCancelable(true);
                            alert.show();
                            Log.i("My error", "" + error);
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> map = new HashMap<>();
                            map.put("name", name);
                            map.put("email", email);
                            map.put("height", height);
                            map.put("weight", weight);
                            map.put("dob", dob);
                            map.put("gender", gender);
                            map.put("pwd", pwd);
                            return map;
                        }
                    };
                    requestQueue.add(stringRequest);
                    requestQueue.start();
                } else {
                    AlertDialog alert = builder.create();
                    alert.setTitle("Error");
                    alert.setMessage("Fill in all the fields");
                    alert.setCancelable(true);
                    alert.show();
                }
            }
        });

    }
}
