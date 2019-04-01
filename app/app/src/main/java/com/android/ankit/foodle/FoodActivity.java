package com.android.ankit.foodle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class FoodActivity extends AppCompatActivity {

    ImageView imageView;
    private RequestQueue requestQueue;
    ProgressBar pBar;

    TextView foodType, foodSpecific, foodCalory;
    public AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        builder = new AlertDialog.Builder(FoodActivity.this);

        foodType = findViewById(R.id.foodTitle);
        foodSpecific = findViewById(R.id.foodSpecific);
        foodCalory = findViewById(R.id.foodCalory);
        pBar = findViewById(R.id.pBar);

        imageView = findViewById(R.id.imageFromUser);

        if (getIntent().hasExtra("imageByteArray")) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("imageByteArray"), 0, getIntent().getByteArrayExtra("imageByteArray").length);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageBitmap(bitmap);
            pBar.getIndeterminateDrawable().setColorFilter(0xffff0000, PorterDuff.Mode.MULTIPLY);
            SendImage(getStringImage(bitmap));
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }

    private void SendImage(final String image) {
        String url = "http://192.168.43.222:8080/sendImage";
        requestQueue = VolleySingleton.getInstance(FoodActivity.this).getRequestQueue();
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
                        pBar.setVisibility(View.INVISIBLE);
                        foodType.setText(String.format("%s: %s", jsonObject.getString("type"), jsonObject.getString("category")));
                        foodSpecific.setText(jsonObject.getString("specific"));
                        foodCalory.setText(String.format("Calory range: %s KCal - %s KCal", jsonObject.getString("calorieMin"), jsonObject.getString("calorieMax")));
                    } else {
                        AlertDialog alert = builder.create();
                        alert.setTitle(jsonObject.getString("status"));
                        alert.setMessage("Couldn't upload image on server");
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
                pBar.setVisibility(View.INVISIBLE);
                Log.i("My error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();
                map.put("image", image);
                return map;
            }
        };

        requestQueue.add(stringRequest);

    }
}
