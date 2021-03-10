package com.sukrit.mckkrs.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sukrit.mckkrs.Helper.URLHelper;
import com.sukrit.mckkrs.MainActivity;
import com.sukrit.mckkrs.R;
import com.sukrit.mckkrs.Utiles.MyPreferences;
import com.sukrit.mckkrs.XuberServicesApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FeedbackActivity extends AppCompatActivity {
    ImageView image;
    EditText edt_feedback;
    TextView txt, txt_submit;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        progressDialog=new ProgressDialog(FeedbackActivity.this);
        progressDialog.setMessage("Please wait...");
        image = findViewById(R.id.image);
        edt_feedback = findViewById(R.id.edt_feedback);
        txt = findViewById(R.id.txt);
        txt_submit = findViewById(R.id.txt_submit);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FeedbackActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
        txt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_feedback.getText().toString().equals("")){
                    edt_feedback.setError("Enter feedback");
                    edt_feedback.requestFocus();
                }
                else{
                    PostFeedback();
                }
            }
        });
    }
    public void PostFeedback(){
        progressDialog.show();
        JSONObject object = new JSONObject();
        try {
            object.put("marn",   MyPreferences.getActiveInstance(FeedbackActivity.this).getMarn());
            object.put("feedback", edt_feedback.getText().toString().trim());
            Log.e("ChangePasswordAPI", "" + object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLHelper.FEEDBACK, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("res2",response.toString());
                try {
                    progressDialog.hide();
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    if (obj.optString("status").equals("200")) {
                        String msg = obj.optString("message");
                        Toast.makeText(FeedbackActivity.this, msg, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(FeedbackActivity.this, HomeActivity.class);
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
      //          Toast.makeText(FeedbackActivity.this, "Something wents wrong", Toast.LENGTH_SHORT).show();
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