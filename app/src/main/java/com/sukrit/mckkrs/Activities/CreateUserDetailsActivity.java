package com.sukrit.mckkrs.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sukrit.mckkrs.Helper.URLHelper;
import com.sukrit.mckkrs.Models.CountryList;
import com.sukrit.mckkrs.R;
import com.sukrit.mckkrs.Utiles.MyPreferences;
import com.sukrit.mckkrs.XuberServicesApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateUserDetailsActivity extends AppCompatActivity {
    EditText edt_marn, edt_name, edt_fmlyname, edt_email, edt_phone, edt_streetno, edt_streentname, edt_city
            , edt_state, edt_country, edt_zip, edt_address, edt_pass;
    ImageView image;
    TextView txt_login;
    private ArrayList<CountryList> goodModelArrayList;
    private ArrayList<String> names = new ArrayList<String>();
    Spinner cntry_spinners;
    String country_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        edt_marn = findViewById(R.id.edt_marn);
        edt_name = findViewById(R.id.edt_name);
        edt_fmlyname = findViewById(R.id.edt_fmlyname);
        edt_email = findViewById(R.id.edt_email);
        edt_phone = findViewById(R.id.edt_phone);
        edt_streetno = findViewById(R.id.edt_streetno);
        edt_streentname = findViewById(R.id.edt_streentname);
        edt_city = findViewById(R.id.edt_city);
        edt_state = findViewById(R.id.edt_state);
        edt_country = findViewById(R.id.edt_country);
        edt_zip = findViewById(R.id.edt_zip);
        edt_address = findViewById(R.id.edt_address);
        edt_pass = findViewById(R.id.edt_pass);
        txt_login = findViewById(R.id.txt_login);
        cntry_spinners = findViewById(R.id.cntry_spinners);
        gettingCountry();
        image = findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateUserDetailsActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_name.getText().toString().equals("")) {
                    edt_name.setError("Enter username");
                    edt_name.requestFocus();
                }
                else if (edt_email.getText().toString().equals("")) {
                    edt_email.setError("Enter email");
                    edt_email.requestFocus();
                }
                else if (edt_phone.getText().toString().equals("")) {
                    edt_phone.setError("Enter phone");
                    edt_phone.requestFocus();
                }
                else if (edt_streetno.getText().toString().equals("")) {
                    edt_streetno.setError("Enter streetno");
                    edt_streetno.requestFocus();
                }
                else if (edt_streentname.getText().toString().equals("")) {
                    edt_streentname.setError("Enter street name");
                    edt_streentname.requestFocus();
                }
                else if (edt_city.getText().toString().equals("")) {
                    edt_city.setError("Enter city");
                    edt_city.requestFocus();
                }
                else if (edt_state.getText().toString().equals("")) {
                    edt_state.setError("Enter state");
                    edt_state.requestFocus();
                }
                else if (edt_country.getText().toString().equals("")) {
                    edt_country.setError("Enter country");
                    edt_country.requestFocus();
                }
                else if (edt_zip.getText().toString().equals("")) {
                    edt_zip.setError("Enter zip code");
                    edt_zip.requestFocus();
                }
                else if (edt_address.getText().toString().equals("")) {
                    edt_address.setError("Enter address");
                    edt_address.requestFocus();
                }
                else if (edt_marn.getText().toString().equals("")) {
                    edt_marn.setError("Enter marn");
                    edt_marn.requestFocus();
                }
                else if (edt_fmlyname.getText().toString().equals("")) {
                    edt_fmlyname.setError("Enter family name");
                    edt_fmlyname.requestFocus();
                }
                   else if (edt_pass.getText().toString().equals("")) {
                    edt_pass.setError("Enter password");
                    edt_pass.requestFocus();
                }
                else{
                    SIGNUPProfile();
                }
            }
        });
        cntry_spinners.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    country_name = goodModelArrayList.get(position).getCountryName();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void gettingCountry(){
        JSONObject object = new JSONObject();
        try {
            object.put("marn",  edt_marn.getText().toString().trim());
   //         Log.e("ChangePasswordAPI", "" + object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLHelper.COUNTRYLIST, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("res2",response.toString());
                try {
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    String msg = obj.optString("message");
                    if (obj.optString("status").equals("200")) {
                        goodModelArrayList = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("activities");
                        names.add("Please select Country Name");
                        for (int i = 0; i < dataArray.length(); i++) {
                            final CountryList playerModel = new CountryList();
                            final JSONObject dataobj = dataArray.getJSONObject(i);
                            playerModel.setId(dataobj.getString("id"));
                            playerModel.setCountryName(dataobj.getString("countryName"));
                            goodModelArrayList.add(playerModel);
                        }
                        for (int i = 0; i < goodModelArrayList.size(); i++) {
                            names.add(goodModelArrayList.get(i).getCountryName().toString());
                        }
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(CreateUserDetailsActivity.this, android.R.layout.simple_spinner_item, names);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        cntry_spinners.setAdapter(spinnerArrayAdapter);
                    }else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
   //             Toast.makeText(CreateUserDetailsActivity.this, "something wents wrong", Toast.LENGTH_SHORT).show();
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

    public void SIGNUPProfile(){
        JSONObject object = new JSONObject();
        try {
            object.put("marn",  edt_marn.getText().toString().trim());
            object.put("name",  edt_name.getText().toString().trim());
            object.put("email",  edt_email.getText().toString().trim());
            object.put("StreetNo",  edt_streetno.getText().toString().trim());
            object.put("phone",  edt_phone.getText().toString().trim());
            object.put("StreetName",  edt_streentname.getText().toString().trim());
            object.put("city",  edt_city.getText().toString().trim());
            object.put("state",  edt_state.getText().toString().trim());
            object.put("zipcode",  edt_zip.getText().toString().trim());
            object.put("country",  country_name);
            object.put("address",  edt_address.getText().toString().trim());
            object.put("familyname",  edt_fmlyname.getText().toString().trim());
            object.put("password",  edt_pass.getText().toString().trim());
            Log.e("ChangePasswordAPI", "" + object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLHelper.SIGNUP, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("res2",response.toString());
                try {
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    String msg = obj.optString("message");
                    if (obj.optString("status").equals("200")) {
                        Intent i = new Intent(CreateUserDetailsActivity.this, ProfileDetailsActivity.class);
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