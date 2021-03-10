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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sukrit.mckkrs.Adapters.OnlineWorkAdapter;
import com.sukrit.mckkrs.Adapters.PrivateStudyAdapter;
import com.sukrit.mckkrs.Helper.URLHelper;
import com.sukrit.mckkrs.Models.OnlineWorkShop;
import com.sukrit.mckkrs.Models.PrivateStudy;
import com.sukrit.mckkrs.R;
import com.sukrit.mckkrs.Utiles.MyPreferences;
import com.sukrit.mckkrs.XuberServicesApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OnlineWorkActivity extends AppCompatActivity implements OnlineWorkAdapter.GotoBookOnlineWorkshopFragment {
    RecyclerView recyclerView;
    LinearLayoutManager HorizontalLayout;
    private ArrayList<OnlineWorkShop> onlineWorkShops;
    LinearLayout linear_no_data_found;
    ImageView image;
    ProgressDialog progressDialog;
    RelativeLayout relative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_work);
        progressDialog=new ProgressDialog(OnlineWorkActivity.this);
        progressDialog.setMessage("Please wait...");
        image = findViewById(R.id.image);
        linear_no_data_found = findViewById(R.id.linear_no_data_found);
        linear_no_data_found.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.recyclerView);
        relative = findViewById(R.id.relative);
        getOnlineWorkShop();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OnlineWorkActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    public void ClickListener() {
      /*  Intent i = new Intent(OnlineWorkActivity.this, BookOnlineWorkshopActivity.class);
        startActivity(i);*/
    }
    public void getOnlineWorkShop(){
        progressDialog.show();
        JSONObject object = new JSONObject();
        try {
            object.put("marn",   MyPreferences.getActiveInstance(OnlineWorkActivity.this).getMarn());
            Log.e("ChangePasswordAPI", "" + object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLHelper.ONLINEWORKSHOP, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("res2",response.toString());
                try {
                    progressDialog.hide();
                    JSONObject obj = new JSONObject(String.valueOf(response));

                    if(obj.optString("status").equals("200")){
                        onlineWorkShops = new ArrayList<>();
                        JSONArray dataArray  = obj.getJSONArray("userdata");
                        for (int i = 0; i < dataArray.length(); i++) {
                            OnlineWorkShop onlineWorkShop = new OnlineWorkShop();
                            JSONObject dataobj = dataArray.getJSONObject(i);
                            if (dataArray.length() >= 0) {
                            relative.setVisibility(View.VISIBLE);
                            linear_no_data_found.setVisibility(View.GONE);
                            onlineWorkShop.setId(dataobj.getString("id"));
                            onlineWorkShop.setActivity_name(dataobj.getString("activity_name"));
                            onlineWorkShop.setOmara_code(dataobj.getString("omara_code"));
                            onlineWorkShop.setFile_name(dataobj.getString("file_name"));
                            onlineWorkShop.setFile_name_2(dataobj.getString("file_name_2"));
                            onlineWorkShop.setId_invoice(dataobj.getString("id_invoice"));
                            onlineWorkShop.setDate_schedule(dataobj.getString("date_schedule"));
                            onlineWorkShop.setTime(dataobj.getString("time"));
                            onlineWorkShop.setId_client_activity(dataobj.getString("id_client_activity"));
                            onlineWorkShop.setUrl(dataobj.getString("url"));
                            onlineWorkShops.add(onlineWorkShop);
                        }else
                            {
                                relative.setVisibility(View.VISIBLE);
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
  //          Toast.makeText(OnlineWorkActivity.this, "something wents wrong", Toast.LENGTH_SHORT).show();
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
        OnlineWorkAdapter adapter = new OnlineWorkAdapter(this, this, onlineWorkShops);
        recyclerView.setAdapter(adapter);
        HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
    }
}