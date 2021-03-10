package com.sukrit.mckkrs.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class ProfileDetailsActivity extends AppCompatActivity {
    TextView txt_name, txt_email, txt_streetNo, txt_phone, txt_streetName,
            txt_city, txt_state, txt_zipcode, txt_country, txt_address, update_profile;
    String name, email, streetno, phone, streetname, city, state, zipcode, country, address;
    ImageView image;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        progressDialog=new ProgressDialog(ProfileDetailsActivity.this);
        progressDialog.setMessage("Please wait...");
        txt_email = findViewById(R.id.txt_email);
        txt_streetNo = findViewById(R.id.txt_streetNo);
        txt_phone = findViewById(R.id.txt_phone);
        txt_streetName = findViewById(R.id.txt_streetName);
        txt_city = findViewById(R.id.txt_city);
        txt_state = findViewById(R.id.txt_state);
        txt_zipcode = findViewById(R.id.txt_zipcode);
        txt_country = findViewById(R.id.txt_country);
        txt_address = findViewById(R.id.txt_address);
        txt_name = findViewById(R.id.txt_name);
        image = findViewById(R.id.image);
        update_profile = findViewById(R.id.update_profile);
        ProfilesDetails();
        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileDetailsActivity.this, UpdateProfileActivity.class);
                i.putExtra("name",name);
                i.putExtra("email",email);
                i.putExtra("StreetNo",streetno);
                i.putExtra("phone",phone);
                i.putExtra("StreetName",streetname);
                i.putExtra("city",city);
                i.putExtra("state",state);
                i.putExtra("zipcode",zipcode);
                i.putExtra("country",country);
                i.putExtra("address",address);
                startActivity(i);
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileDetailsActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
    public void ProfilesDetails(){
        progressDialog.show();
        JSONObject object = new JSONObject();
        try {
            object.put("marn",   MyPreferences.getActiveInstance(ProfileDetailsActivity.this).getMarn());
            Log.e("ChangePasswordAPI", "" + object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLHelper.PROFILDEdETAILS, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("res2",response.toString());
                try {
                    progressDialog.hide();
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    String msg = obj.optString("message");
                    if (obj.optString("status").equals("200")) {
                        JSONObject jsonObject = obj.getJSONObject("userdata");
                        Toast.makeText(ProfileDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
                        name = jsonObject.getString("name");
                        email = jsonObject.getString("email");
                        streetno = jsonObject.getString("StreetNo");
                        phone = jsonObject.getString("phone");
                        streetname = jsonObject.getString("StreetName");
                        city = jsonObject.getString("city");
                        state = jsonObject.getString("state");
                        zipcode = jsonObject.getString("zipcode");
                        country = jsonObject.getString("country");
                        address = jsonObject.getString("address");
                        txt_name.setText(name);
                        txt_email.setText(": "+jsonObject.getString("email"));
                        txt_streetNo.setText(": "+jsonObject.getString("StreetNo"));
                        txt_phone.setText(": "+jsonObject.getString("phone"));
                        txt_streetName.setText(": "+jsonObject.getString("StreetName"));
                        txt_city.setText(": "+jsonObject.getString("city"));
                        txt_state.setText(": "+jsonObject.getString("state"));
                        txt_zipcode.setText(": "+jsonObject.getString("zipcode"));
                        txt_country.setText(": "+jsonObject.getString("country"));
                        txt_address.setText(": "+jsonObject.getString("address"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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