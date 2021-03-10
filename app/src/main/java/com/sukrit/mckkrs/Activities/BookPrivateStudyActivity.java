package com.sukrit.mckkrs.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.snackbar.Snackbar;
import com.sukrit.mckkrs.Adapters.BookPrivateStudyAdapter;
import com.sukrit.mckkrs.Helper.URLHelper;
import com.sukrit.mckkrs.MainActivity;
import com.sukrit.mckkrs.Models.BookPrivateStudy;
import com.sukrit.mckkrs.R;
import com.sukrit.mckkrs.Utiles.MyPreferences;
import com.sukrit.mckkrs.XuberServicesApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class BookPrivateStudyActivity extends AppCompatActivity implements BookPrivateStudyAdapter.OnClickStudy {
    RecyclerView recyclerView;
    LinearLayoutManager HorizontalLayout;
    ArrayList<BookPrivateStudy> bookPrivateStudies;
    BookPrivateStudyAdapter adapter;
    TextView txt_check;
    TextView totalPrice, txt_login;
    LinearLayout lineae_no_, linear_no_data_found;
    ProgressDialog progressDialog;
    double totalPriceAmt =0;
    int itemCount =0;
    int cpd_type = 3;
    ImageView image_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_private_study);
        image_back = findViewById(R.id.image_back);
        recyclerView = findViewById(R.id.recyclerView);
        txt_check = findViewById(R.id.txt_check);
        totalPrice = findViewById(R.id.totalPrice);
        lineae_no_ = findViewById(R.id.lineae_no_);
        txt_login = findViewById(R.id.txt_login);
        linear_no_data_found = findViewById(R.id.linear_no_data_found);
        linear_no_data_found.setVisibility(View.GONE);
        progressDialog=new ProgressDialog(BookPrivateStudyActivity.this);
        progressDialog.setMessage("Please wait...");
        getBookPrivateStudy();
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrivateOrderSubmit();
            }
        });
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(BookPrivateStudyActivity.this, OrderActivitiesActivity.class );
               startActivity(i);
               finish();
            }
        });
    }

    public void PrivateOrderSubmit(){
        JSONObject object = new JSONObject();
        try{
            object.put("marn",   MyPreferences.getActiveInstance(BookPrivateStudyActivity.this).getMarn());
            object.put("email",   MyPreferences.getActiveInstance(BookPrivateStudyActivity.this).getEmail());
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
        for(int i=0;i<=adapter.getList().size()-1;i++) {
         BookPrivateStudy aaa  = adapter.getList().get(i);
         if(aaa.isSelected()) {
             JSONObject job = new JSONObject();
             job.put("activity_name", aaa.getActivity_name());
             job.put("price", aaa.getGlobalPrice());
             job.put("cpd_point", aaa.getGlobalPoint());
             job.put("activity_id", aaa.getId_activity());
             list.add(aaa.getId_activity());
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
           //             Toast.makeText(BookPrivateStudyActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                    else {
           //             Toast.makeText(BookPrivateStudyActivity.this, msg, Toast.LENGTH_SHORT).show();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error: ", error.toString());
    //            Toast.makeText(BookPrivateStudyActivity.this, "something wents wrong", Toast.LENGTH_SHORT).show();
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

    public void getBookPrivateStudy() {
        progressDialog.show();
        JSONObject object = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLHelper.BOOKPRIVATESTUDY, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("res2", response.toString());
                try {
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    progressDialog.hide();
                    if (obj.optString("status").equals("200")) {
                        bookPrivateStudies = new ArrayList<>();
                        JSONArray dataArray = obj.getJSONArray("activities");
                        for (int i = 0; i < dataArray.length(); i++) {
                            BookPrivateStudy bookPrivateStudy = new BookPrivateStudy();
                            JSONObject dataobj = dataArray.getJSONObject(i);
                            if (dataArray.length() >0){
                                linear_no_data_found.setVisibility(View.GONE);
                                lineae_no_.setVisibility(View.VISIBLE);
                                bookPrivateStudy.setId_activity(dataobj.getString("id_activity"));
                                bookPrivateStudy.setActivity_name(dataobj.getString("activity_name"));
                                bookPrivateStudy.setActivity_status(dataobj.getString("activity_status"));
                                bookPrivateStudy.setOmara_code(dataobj.getString("omara_code"));
                                bookPrivateStudy.setId_activity_type(dataobj.getString("id_activity_type"));
                                bookPrivateStudy.setActivity_mandatory(dataobj.getString("activity_mandatory"));
                                bookPrivateStudy.setActivity_status(dataobj.getString("display_status"));
                                bookPrivateStudy.setGlobalDuration(dataobj.getString("globalDuration"));
                                bookPrivateStudy.setGlobalPoint(dataobj.getString("globalPoint"));
                                bookPrivateStudy.setGlobalPrice(dataobj.getString("globalPrice"));
                                bookPrivateStudies.add(bookPrivateStudy);
                            }else {
                                lineae_no_.setVisibility(View.GONE);
                                linear_no_data_found.setVisibility(View.VISIBLE);
                            }
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
    private void setupRecyclerBookPrivateStudy() {
         adapter = new BookPrivateStudyAdapter(this, bookPrivateStudies, BookPrivateStudyActivity.this);
        recyclerView.setAdapter(adapter);
        HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
    }

    @Override
    public void itemClick(BookPrivateStudy bookPrivateStudy) {
        itemCount=0;
        totalPriceAmt=0;
        ArrayList<BookPrivateStudy> list=   adapter.getList();


       for (BookPrivateStudy data : list){
           if(data.getSelected()) {
              ++itemCount;

           String price=   data.getGlobalPrice().replace("$", "");
               totalPriceAmt = totalPriceAmt+Double.valueOf(price);

           }
       }
        txt_check.setText("Item : "+String.valueOf(itemCount));
        totalPrice.setText(""+totalPriceAmt);
    }

}
