package com.sukrit.mckkrs.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sukrit.mckkrs.Adapters.BookPrivateStudyAdapter;
import com.sukrit.mckkrs.Adapters.FtoFCpdWorkShopAdapter;
import com.sukrit.mckkrs.Helper.URLHelper;
import com.sukrit.mckkrs.Models.BookPrivateStudy;
import com.sukrit.mckkrs.Models.FtoFCpdWorkShop;
import com.sukrit.mckkrs.R;
import com.sukrit.mckkrs.XuberServicesApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FtoFCpdWorkShopActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager HorizontalLayout;
    ArrayList<FtoFCpdWorkShop> ftoFCpdWorkShops;
    FtoFCpdWorkShopAdapter adapter;
    ProgressDialog progressDialog;
    LinearLayout linear_no_data_found;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fto_f_cpd_work_shop);
        recyclerView = findViewById(R.id.recyclerView);
        linear_no_data_found = findViewById(R.id.linear_no_data_found);
        linear_no_data_found.setVisibility(View.GONE);
        progressDialog=new ProgressDialog(FtoFCpdWorkShopActivity.this);
        progressDialog.setMessage("Please wait...");
        getFtoFWorkShop();
    }

    public void getFtoFWorkShop() {
        progressDialog.show();
        JSONObject object = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLHelper.FACETOFACEWORKSHOP,
                object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("res2", response.toString());
                try {
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    progressDialog.hide();
                    if (obj.optString("status").equals("200")) {
                        ftoFCpdWorkShops = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("activities");
                        for (int i = 0; i < dataArray.length(); i++) {
                            FtoFCpdWorkShop ftoFCpdWorkShop = new FtoFCpdWorkShop();
                            JSONObject dataobj = dataArray.getJSONObject(i);
                            if (dataArray.length() >0){
                                linear_no_data_found.setVisibility(View.GONE);
                                ftoFCpdWorkShop.setId_schedule(dataobj.getString("id_schedule"));
                                ftoFCpdWorkShop.setDate_schedule(dataobj.getString("date_schedule"));
                                ftoFCpdWorkShop.setCity_schedule(dataobj.getString("city_schedule"));
                                ftoFCpdWorkShop.setVenue(dataobj.getString("venue"));
                                ftoFCpdWorkShop.setAddress(dataobj.getString("address"));
                                ftoFCpdWorkShop.setTotal_price(dataobj.getString("total_price"));
                                ftoFCpdWorkShops.add(ftoFCpdWorkShop);
                            }else {
                                linear_no_data_found.setVisibility(View.VISIBLE);
                            }
                        }
                        setupRecyclerFtoFWorkShop();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //         Toast.makeText(MainActivity.this, "something wents wrong", Toast.LENGTH_SHORT).show();
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
    private void setupRecyclerFtoFWorkShop() {
        adapter = new FtoFCpdWorkShopAdapter(FtoFCpdWorkShopActivity.this, ftoFCpdWorkShops);
        recyclerView.setAdapter(adapter);
        HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
    }
}