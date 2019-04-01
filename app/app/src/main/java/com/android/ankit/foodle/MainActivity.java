package com.android.ankit.foodle;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;

    Button loginButton;
    EditText mUsername, mPassword;
    TextView forgotPassword, signUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if (SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }

        loginButton = findViewById(R.id.main_loginButton);
        mUsername = findViewById(R.id.main_loginUsername);
        mPassword = findViewById(R.id.main_loginPassword);
        forgotPassword = findViewById(R.id.main_forgotPassword);
        signUpText = findViewById(R.id.main_signUpLink);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email, pwd;
                email = mUsername.getText().toString();
                pwd = mPassword.getText().toString();

                if (!(email.equals("") || pwd.equals(""))) {
                    String url = "http://192.168.43.222:8080/login";
                    requestQueue = VolleySingleton.getInstance(MainActivity.this).getRequestQueue();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                if (jsonObject.getString("status").equals("success")) {
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                                    SaveSharedPreference.setLoggedIn(MainActivity.this, true);
                                } else {
                                    AlertDialog alert = builder.create();
                                    alert.setTitle(jsonObject.getString("status"));
                                    alert.setMessage("Error: Check your credentials");
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
                            alert.setMessage("Error: Something went wrong");
                            alert.setCancelable(true);
                            alert.show();
                            Log.i("My error", "" + error);
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> map = new HashMap<>();
                            map.put("email", email);
                            map.put("pwd", pwd);
                            return map;
                        }
                    };

                    requestQueue.add(stringRequest);
                } else {
                    AlertDialog alert = builder.create();
                    alert.setTitle("Error");
                    alert.setMessage("Fill in all the fields");
                    alert.setCancelable(true);
                    alert.show();
                }

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Logic baaki hai
                Toast.makeText(MainActivity.this, "ForgotButton Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpForm = new Intent(MainActivity.this, SignUp.class);
                startActivity(signUpForm);
            }
        });

    }
}
