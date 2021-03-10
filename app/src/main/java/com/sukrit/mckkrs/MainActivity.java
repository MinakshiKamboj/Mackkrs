package com.sukrit.mckkrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sukrit.mckkrs.Activities.CreateUserDetailsActivity;
import com.sukrit.mckkrs.Activities.ForgotPasswordActivity;
import com.sukrit.mckkrs.Activities.HomeActivity;
import com.sukrit.mckkrs.Helper.URLHelper;
import com.sukrit.mckkrs.Utiles.MyPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView txt_login, txt_forgot, tv_signUp;
    EditText edt_user, edt_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_login = findViewById(R.id.txt_login);
        txt_forgot = findViewById(R.id.txt_forgot);
        edt_user = findViewById(R.id.edt_user);
        edt_pass = findViewById(R.id.edt_pass);
        tv_signUp = findViewById(R.id.tv_signUp);

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_user.getText().toString().equals("")) {
                    edt_user.setError("Enter username");
                    edt_user.requestFocus();
                }
                else if(edt_pass.getText().toString().equals("")){
                    edt_pass.setError("Enter password");
                    edt_pass.requestFocus();
                }
                else{
                    Login();
                 /*   MyPreferences.getActiveInstance(MainActivity.this).setIsLoggedIn(true);
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();*/
                }
            }
        });

        txt_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                startActivity(i);
            }
        });

        tv_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateUserDetailsActivity.class);
                startActivity(i);
            }
        });
    }
    public void Login(){
        JSONObject object = new JSONObject();
        try {
            object.put("marn",  edt_user.getText().toString().trim());
            object.put("password", edt_pass.getText().toString().trim());
            Log.e("ChangePasswordAPI", "" + object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLHelper.LOGIN, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("res2",response.toString());
                try {
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    String msg = obj.optString("message");
                    if (obj.optString("status").equals("200")) {
                        JSONObject jsonObject = obj.getJSONObject("userdata");
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        MyPreferences.getActiveInstance(MainActivity.this).setMarn(jsonObject.getString("marn"));
                        MyPreferences.getActiveInstance(MainActivity.this).setEmail(jsonObject.getString("email"));
                        Intent i = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "something wents wrong", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Basic Y3BkOmNwZDEyMw==");
                return headers;
            }
        };
        XuberServicesApplication.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}