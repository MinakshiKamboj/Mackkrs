package com.sukrit.mckkrs.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sukrit.mckkrs.Helper.URLHelper;
import com.sukrit.mckkrs.R;
import com.sukrit.mckkrs.Utiles.MyPreferences;
import com.sukrit.mckkrs.XuberServicesApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgotPasswordActivity extends AppCompatActivity {
    TextView txt_login, txt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        txt_login = findViewById(R.id.txt_login);
        txt_back = findViewById(R.id.txt_back);
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgotPassword();
            }
        });
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForgotPasswordActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void ForgotPassword(){
        JSONObject object = new JSONObject();
        try {
            object.put("marn",   MyPreferences.getActiveInstance(ForgotPasswordActivity.this).getMarn());
            Log.e("ChangePasswordAPI", "" + object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLHelper.FORGOTPASS, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("res2",response.toString());
                try {
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    if (obj.optString("status").equals("200")) {
                        String msg = obj.optString("message");
                        Toast.makeText(ForgotPasswordActivity.this, msg, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ForgotPasswordActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
      //          Toast.makeText(ForgotPasswordActivity.this, "Something wents wrong", Toast.LENGTH_SHORT).show();
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