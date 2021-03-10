package com.sukrit.mckkrs.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sukrit.mckkrs.Adapters.BookPrivateStudyAdapter;
import com.sukrit.mckkrs.Adapters.CPDWorkShopRequestAdapter;
import com.sukrit.mckkrs.Adapters.OnlineWorkAdapter;
import com.sukrit.mckkrs.Helper.URLHelper;
import com.sukrit.mckkrs.Models.BookPrivateStudy;
import com.sukrit.mckkrs.Models.OnlineWorkShop;
import com.sukrit.mckkrs.Models.workshopdetails;
import com.sukrit.mckkrs.R;
import com.sukrit.mckkrs.Utiles.MyPreferences;
import com.sukrit.mckkrs.XuberServicesApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CPDWorkShopRequestActivity extends AppCompatActivity implements CPDWorkShopRequestAdapter.OnClickStudy{
    TextView tv_cpd_date, tv_address, totalPrice, txt_check, txt_login;
    ProgressDialog progressDialog;
    private ArrayList<workshopdetails> onlineWorkShops;
    LinearLayout linear_no_data_found;
    LinearLayoutManager HorizontalLayout;
    RecyclerView recyclerView;
    String id_schedule = "";
    double totalPriceAmt =0;
    int itemCount =0;
    int cpd_type = 3;
    CPDWorkShopRequestAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_p_d_work_shop_request);
        id_schedule = getIntent().getStringExtra("id_schedule");
        linear_no_data_found = findViewById(R.id.linear_no_data_found);
        txt_check = findViewById(R.id.txt_check);
        txt_login = findViewById(R.id.txt_login);
        totalPrice = findViewById(R.id.totalPrice);
        tv_cpd_date = findViewById(R.id.tv_cpd_date);
        tv_address = findViewById(R.id.tv_address);
        recyclerView = findViewById(R.id.recyclerView);
        progressDialog=new ProgressDialog(CPDWorkShopRequestActivity.this);
        progressDialog.setMessage("Please wait...");
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f2fSubmit();
            }
        });
        getOnlineWorkShop();
    }
    public void f2fSubmit(){
        JSONObject object = new JSONObject();
        try{
            object.put("marn",   MyPreferences.getActiveInstance(CPDWorkShopRequestActivity.this).getMarn());
            object.put("email",   MyPreferences.getActiveInstance(CPDWorkShopRequestActivity.this).getEmail());
            //  object.put("activities","[\"1004\",\"1050\",\"1051\",\"1052\"]");
            object.put("delivery","Private Study");
            object.put("total_price",totalPriceAmt);  // total price
            object.put("cpd_points",itemCount); // number of items
            object.put("id_schedule",""); // only f to f value otherwise empty
            object.put("address",""); // alway empty
            object.put("live",""); // emty
            object.put("cpd_type",cpd_type);
            JSONArray jsonArray = new JSONArray();
            ArrayList <String> list =new ArrayList<>();
            for(int i=0;i<=adapter.getList().size()-1;i++) { // activities me array me nhi ja rahe h value ["111", "111"] aise jana
                workshopdetails aaa  = adapter.getList().get(i);
                if(aaa.isSelected()) {
                    JSONObject job = new JSONObject();
                    job.put("activity_name", aaa.getActivity_name());
                    job.put("price", aaa.getPrice());
                    job.put("cpd_point", aaa.getPoints());
                    job.put("activity_id", aaa.getId_activity());
                    list.add(aaa.getId_activity()); // sahi h ye id but agr ye id h to
                    jsonArray.put(job);
                }
            }
            JSONArray mActivites = new JSONArray(list);

            object.put("activities",mActivites);
            object.put("activity_list",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("requestparam: ",object.toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLHelper.FACETOFACESUBMIT, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("res22",response.toString());
                try {
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    String msg = obj.optString("message");
                    if (obj.optString("status").equals("200")) {
       //                 Toast.makeText(CPDWorkShopRequestActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                    else {
     //                   Toast.makeText(CPDWorkShopRequestActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error: ", error.toString());
    //            Toast.makeText(CPDWorkShopRequestActivity.this, "something wents wrong", Toast.LENGTH_SHORT).show();
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



    public void getOnlineWorkShop(){
        progressDialog.show();
        JSONObject object = new JSONObject();
        try {
            object.put("id_schedule", id_schedule);
            Log.e("ChangePasswordAPI", "" + object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLHelper.FACETOFACESCHEDULES, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("res2",response.toString());
                try {
                    progressDialog.hide();
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    if(obj.optString("status").equals("200")){
                        onlineWorkShops = new ArrayList<>();
                        JSONArray dataArray  = obj.getJSONArray("activity_list");
                        for (int i = 0; i < dataArray.length(); i++) {
                            workshopdetails onlineWorkShop = new workshopdetails();
                            JSONObject dataobj = dataArray.getJSONObject(i);
                            if (dataArray.length() >= 0) {
                                linear_no_data_found.setVisibility(View.GONE);
                                onlineWorkShop.setId_activity(dataobj.getString("id_activity"));
                                onlineWorkShop.setActivity_name(dataobj.getString("activity_name"));
                                onlineWorkShop.setPoints(dataobj.getString("points"));
                                onlineWorkShop.setOmara_code(dataobj.getString("omara_code"));
                                onlineWorkShop.setPrice(dataobj.getString("price"));
                                onlineWorkShop.setId(dataobj.getString("id"));
                                onlineWorkShops.add(onlineWorkShop);
                            }else
                            {
              //                  linear_no_data_found.setVisibility(View.VISIBLE);
                            }}

                        JSONArray workshop_details  = obj.getJSONArray("workshop_details");
                        for (int j = 0; j < workshop_details.length(); j++) {
                            workshopdetails onlineWorkShop1 = new workshopdetails();
                            JSONObject dataobj1 = workshop_details.getJSONObject(j);
                            if (workshop_details.length() >= 0) {
                                linear_no_data_found.setVisibility(View.GONE);
                                tv_cpd_date.setText("CPD Date : "+dataobj1.getString("date_schedule"));
                                tv_address.setText("Address : "+dataobj1.getString("address") + ", "+ dataobj1.getString("city_schedule"));
                                onlineWorkShops.add(onlineWorkShop1);
                            }else
                            {
                                linear_no_data_found.setVisibility(View.VISIBLE);
                            }}

                        setupRecyclerBookPrivateStudy();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
  //              Toast.makeText(CPDWorkShopRequestActivity.this, "something wents wrong", Toast.LENGTH_SHORT).show();
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
        adapter = new CPDWorkShopRequestAdapter(CPDWorkShopRequestActivity.this,  onlineWorkShops, CPDWorkShopRequestActivity.this);
        recyclerView.setAdapter(adapter);
        HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
    }

    @Override
    public void itemClick(workshopdetails id) {
        itemCount=0;
        totalPriceAmt=0;
        ArrayList<workshopdetails> list=   adapter.getList();


        for (workshopdetails data : list){
            if(data.getSelected()) {
                ++itemCount;
                String price=   data.getPrice().replace("$", "");
                totalPriceAmt = totalPriceAmt+Double.valueOf(price);
            }
        }
        txt_check.setText("Item : "+String.valueOf(itemCount));
        totalPrice.setText(""+totalPriceAmt);
    }
}