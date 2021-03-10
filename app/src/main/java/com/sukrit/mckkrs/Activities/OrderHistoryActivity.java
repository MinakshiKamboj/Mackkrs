package com.sukrit.mckkrs.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sukrit.mckkrs.Adapters.BookPrivateStudyAdapter;
import com.sukrit.mckkrs.Adapters.OrderHistoryAdapter;
import com.sukrit.mckkrs.Helper.URLHelper;
import com.sukrit.mckkrs.Models.BookPrivateStudy;
import com.sukrit.mckkrs.Models.OrderHistory;
import com.sukrit.mckkrs.R;
import com.sukrit.mckkrs.Utiles.MyPreferences;
import com.sukrit.mckkrs.XuberServicesApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderHistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager HorizontalLayout;
    ArrayList<OrderHistory> orderHistories;
    ImageView image;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        progressDialog=new ProgressDialog(OrderHistoryActivity.this);
        progressDialog.setMessage("Please wait...");
        image = findViewById(R.id.image);
        recyclerView = findViewById(R.id.recyclerView);
        getBookPrivateStudy();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderHistoryActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

     /*   OrderHistoryAdapter adapter = new OrderHistoryAdapter(OrderHistoryActivity.this);
        recyclerView.setAdapter(adapter);
        HorizontalLayout = new LinearLayoutManager(OrderHistoryActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);*/
    }

    public void getBookPrivateStudy(){
        progressDialog.show();
        JSONObject object = new JSONObject();
        try {
            object.put("marn",   MyPreferences.getActiveInstance(OrderHistoryActivity.this).getMarn());
            Log.e("ChangePasswordAPI", "" + object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLHelper.ORDERHISTORY, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("res2",response.toString());
                try {
                    progressDialog.hide();
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    if(obj.optString("status").equals("200")){
                        orderHistories = new ArrayList<>();
                        JSONArray dataArray  = obj.getJSONArray("data");
                        for (int i = 0; i < dataArray.length(); i++) {
                            OrderHistory orderHistory = new OrderHistory();
                            JSONObject dataobj = dataArray.getJSONObject(i);
                            orderHistory.setId_invoice(dataobj.getString("id_invoice"));
                            orderHistory.setMarn(dataobj.getString("marn"));
                            orderHistory.setOrder_date(dataobj.getString("order_date"));
                            orderHistory.setPayment_date(dataobj.getString("payment_date"));
                            orderHistory.setPrice(dataobj.getString("price"));
                            orderHistories.add(orderHistory);
                        }
                        setupRecyclerBookPrivateStudy();
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
    private void setupRecyclerBookPrivateStudy(){
        OrderHistoryAdapter adapter = new OrderHistoryAdapter(this, orderHistories);
        recyclerView.setAdapter(adapter);
        HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
    }
}