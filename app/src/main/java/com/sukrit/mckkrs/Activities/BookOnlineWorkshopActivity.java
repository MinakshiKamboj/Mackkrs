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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sukrit.mckkrs.Adapters.BookOnlineWorkshopAdapter;
import com.sukrit.mckkrs.Adapters.BookPrivateStudyAdapter;
import com.sukrit.mckkrs.Helper.URLHelper;
import com.sukrit.mckkrs.Models.BookPrivateStudy;
import com.sukrit.mckkrs.Models.OnlineWorkShopList;
import com.sukrit.mckkrs.R;
import com.sukrit.mckkrs.Utiles.MyPreferences;
import com.sukrit.mckkrs.XuberServicesApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookOnlineWorkshopActivity extends AppCompatActivity implements BookOnlineWorkshopAdapter.OnClickStudy1{
    RecyclerView recyclerView;
    LinearLayoutManager HorizontalLayout;
    RelativeLayout relative;
    ImageView image;
    ArrayList<OnlineWorkShopList> onlineWorkShopLists;
    BookOnlineWorkshopAdapter adapter;
    TextView txt_check, totalPrice, txt_login;
    LinearLayout linear_no_data_found, linear_checkbox, linear2,line;
    ProgressDialog progressDialog;
    int itemCount =0;
    double totalPriceAmt =0;
    int cpd_type = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_online_workshop);
        progressDialog=new ProgressDialog(BookOnlineWorkshopActivity.this);
        progressDialog.setMessage("Please wait...");
        image = findViewById(R.id.image);
        recyclerView = findViewById(R.id.recyclerView);
        txt_check = findViewById(R.id.txt_check);
        totalPrice = findViewById(R.id.totalPrice);
        linear_no_data_found = findViewById(R.id.linear_no_data_found);
        relative = findViewById(R.id.relative);
        linear_checkbox = findViewById(R.id.linear_checkbox);
        linear2 = findViewById(R.id.linear2);
        line = findViewById(R.id.line);
        txt_login = findViewById(R.id.txt_login);
        linear_no_data_found.setVisibility(View.GONE);
        getOnlineWorkshopList();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnlineWorkshopList();

            }
        });
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrivateOrderSubmit();
            }
        });
    }
    public void PrivateOrderSubmit(){
        JSONObject object = new JSONObject();
        try{
            object.put("marn",   MyPreferences.getActiveInstance(BookOnlineWorkshopActivity.this).getMarn());
            object.put("email",   MyPreferences.getActiveInstance(BookOnlineWorkshopActivity.this).getEmail());
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
                OnlineWorkShopList aaa  = adapter.getList().get(i);
                if(aaa.isSelected()) {
                    JSONObject job = new JSONObject();
                    job.put("activity_name", aaa.getActivity_name());
                    job.put("date", aaa.getDate_schedule());
                    job.put("time", aaa.getTime());
                    job.put("price", aaa.getPrice());
                    job.put("cpd_point", "1");
                    job.put("activity_id", aaa.getActivity_id());
                    list.add(aaa.getActivity_id()); // sahi h ye id but agr ye id h to
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

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLHelper.PRIVATEORDERSUBMIT, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("res22",response.toString());
                try {
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    String msg = obj.optString("message");
                    if (obj.optString("status").equals("200")) {
                        Toast.makeText(BookOnlineWorkshopActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(BookOnlineWorkshopActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error: ", error.toString());
                Toast.makeText(BookOnlineWorkshopActivity.this, "something wents wrong", Toast.LENGTH_SHORT).show();
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
    public void getOnlineWorkshopList(){
        progressDialog.show();
       JSONObject object = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLHelper.ONLINEWORKSHOPLIST, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("resjhkjhj2",response.toString());
                try {
                    progressDialog.hide();
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    if(obj.optString("status").equals("200")){
                        onlineWorkShopLists = new ArrayList<>();
                        JSONArray dataArray  = obj.getJSONArray("activities");
                        for (int i = 0; i < dataArray.length(); i++) {
                            OnlineWorkShopList onlineWorkShopList = new OnlineWorkShopList();
                            JSONObject dataobj = dataArray.getJSONObject(i);
                            if (dataArray.length() >0) {
                                linear_no_data_found.setVisibility(View.GONE);
                                relative.setVisibility(View.VISIBLE);
                                onlineWorkShopList.setId(dataobj.getString("id"));
                                onlineWorkShopList.setDate_schedule(dataobj.getString("date_schedule"));
                                onlineWorkShopList.setActivity_id(dataobj.getString("activity_id"));
                                onlineWorkShopList.setActivity_name(dataobj.getString("activity_name"));
                                onlineWorkShopList.setTime(dataobj.getString("time"));
                                onlineWorkShopList.setCapacity(dataobj.getString("capacity"));
                                onlineWorkShopList.setPrice(dataobj.getString("price"));
                                onlineWorkShopLists.add(onlineWorkShopList);
                            }else {
                                line.setVisibility(View.GONE);
                                relative.setVisibility(View.GONE);
                                linear_checkbox.setVisibility(View.GONE);
                                linear2.setVisibility(View.GONE);
                                linear_no_data_found.setVisibility(View.VISIBLE);
                            }
                        }
                        setupRecyclerWorkshopList();
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
    private void setupRecyclerWorkshopList(){
        adapter = new BookOnlineWorkshopAdapter(this, onlineWorkShopLists, BookOnlineWorkshopActivity.this);
        recyclerView.setAdapter(adapter);
        HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
    }

    @Override
    public void itemClick(OnlineWorkShopList onlineWorkShopList) {
        ArrayList<OnlineWorkShopList> list=   adapter.getList();
        int itemCount =0;
        double totalPriceAmt =0;
        for (OnlineWorkShopList data : list){
            if(data.getSelected()) {
                ++itemCount;
                String price=   data.getPrice().replace("$", "");
                totalPriceAmt = totalPriceAmt+Double.valueOf(price);
            }
        }
        txt_check.setText(String.valueOf("Selected Item : "+itemCount));
        totalPrice.setText("Total Price : "+totalPriceAmt);
    }
}