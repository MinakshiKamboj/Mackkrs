package com.sukrit.mckkrs.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class UpdateProfileActivity extends AppCompatActivity {
    EditText edt_name, edt_email, edt_phone, edt_streetno, edt_streentname, edt_city
            , edt_state, edt_country, edt_zip, edt_address;
    String name, email, phone, streetno, streentname, city
            , state, country, zip, address;
    TextView txt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        edt_name = findViewById(R.id.edt_name);
        edt_email = findViewById(R.id.edt_email);
        edt_phone = findViewById(R.id.edt_phone);
        edt_streetno = findViewById(R.id.edt_streetno);
        edt_streentname = findViewById(R.id.edt_streentname);
        edt_city = findViewById(R.id.edt_city);
        edt_state = findViewById(R.id.edt_state);
        edt_country = findViewById(R.id.edt_country);
        edt_zip = findViewById(R.id.edt_zip);
        edt_address = findViewById(R.id.edt_address);
        txt_login = findViewById(R.id.txt_login);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");
        streetno = getIntent().getStringExtra("StreetNo");
        streentname = getIntent().getStringExtra("StreetName");
        city = getIntent().getStringExtra("city");
        state = getIntent().getStringExtra("state");
        country = getIntent().getStringExtra("country");
        zip = getIntent().getStringExtra("zipcode");
        address = getIntent().getStringExtra("address");
        edt_name.setText(name);
        edt_email.setText(email);
        edt_phone.setText(phone);
        edt_streetno.setText(streetno);
        edt_streentname.setText(streentname);
        edt_city.setText(city);
        edt_state.setText(state);
        edt_country.setText(country);
        edt_zip.setText(zip);
        edt_address.setText(address);
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_name.getText().toString().equals("")) {
                    edt_name.requestFocus();
                }
                else if (edt_email.getText().toString().equals("")) {
                    edt_email.requestFocus();
                }
                else if (edt_phone.getText().toString().equals("")) {
                    edt_phone.requestFocus();
                }
                else if (edt_streetno.getText().toString().equals("")) {
                    edt_streetno.requestFocus();
                }
                else if (edt_streentname.getText().toString().equals("")) {
                    edt_streentname.requestFocus();
                }
                else if (edt_city.getText().toString().equals("")) {
                    edt_city.requestFocus();
                }
                else if (edt_state.getText().toString().equals("")) {
                    edt_state.requestFocus();
                }
                else if (edt_country.getText().toString().equals("")) {
                    edt_country.requestFocus();
                }
                else if (edt_zip.getText().toString().equals("")) {
                    edt_zip.requestFocus();
                }
                else if (edt_address.getText().toString().equals("")) {
                    edt_address.requestFocus();
                }
             else{
                    UpdateProfiles();
                }
            }
        });
    }
    public void UpdateProfiles(){
        JSONObject object = new JSONObject();
        try {
            object.put("marn",   MyPreferences.getActiveInstance(UpdateProfileActivity.this).getMarn());
            object.put("name",  edt_name.getText().toString().trim());
            object.put("email",  edt_email.getText().toString().trim());
            object.put("StreetNo",  edt_streetno.getText().toString().trim());
            object.put("phone",  edt_phone.getText().toString().trim());
            object.put("StreetName",  edt_streentname.getText().toString().trim());
            object.put("city",  edt_city.getText().toString().trim());
            object.put("state",  edt_state.getText().toString().trim());
            object.put("zipcode",  edt_zip.getText().toString().trim());
            object.put("country",  edt_country.getText().toString().trim());
            object.put("address",  edt_address.getText().toString().trim());
            Log.e("ChangePasswordAPI", "" + object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLHelper.PROFILEUPDATE, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("res2",response.toString());
                try {
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    String msg = obj.optString("message");
                    if (obj.optString("status").equals("200")) {
                        Intent i = new Intent(UpdateProfileActivity.this, ProfileDetailsActivity.class);
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