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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.textfield.TextInputLayout;
import com.sukrit.mckkrs.Adapters.OrderHistoryAdapter;
import com.sukrit.mckkrs.Adapters.PrivateStudyAdapter;
import com.sukrit.mckkrs.Helper.URLHelper;
import com.sukrit.mckkrs.Models.OrderHistory;
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

public class PrivateStudyActivity extends AppCompatActivity implements PrivateStudyAdapter.GotoTestFragment  {
    RecyclerView recyclerView;
    LinearLayoutManager HorizontalLayout;
    ArrayList<PrivateStudy> privateStudies;
    TextView txt;
    ImageView image;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_study);
        progressDialog=new ProgressDialog(PrivateStudyActivity.this);
        progressDialog.setMessage("Please wait...");
        recyclerView = findViewById(R.id.recyclerView);
     //   txt = findViewById(R.id.txt);
    //    txt.setText(MyPreferences.getActiveInstance(PrivateStudyActivity.this).getMarn());
        image = findViewById(R.id.image);
        getPrivateStudy();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PrivateStudyActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    public void ClickListener() {
       /* Intent i = new Intent(PrivateStudyActivity.this, BookPrivateStudyActivity.class);
        startActivity(i);*/
    }

    public void getPrivateStudy(){
        progressDialog.show();
        JSONObject object = new JSONObject();
        try {
            object.put("marn",   MyPreferences.getActiveInstance(PrivateStudyActivity.this).getMarn());
            Log.e("ChangePasswordAPI", "" + object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLHelper.PRIVATESTUDY, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("res2",response.toString());
                try {
                    progressDialog.hide();
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    if(obj.optString("status").equals("200")){
                        privateStudies = new ArrayList<>();
                        JSONArray dataArray  = obj.getJSONArray("userdata");
                        for (int i = 0; i < dataArray.length(); i++) {
                            PrivateStudy privateStudy = new PrivateStudy();
                            JSONObject dataobj = dataArray.getJSONObject(i);
                            privateStudy.setId(dataobj.getString("id"));
                            privateStudy.setActivity_name(dataobj.getString("activity_name"));
                            privateStudy.setOmara_code(dataobj.getString("omara_code"));
                            privateStudy.setFile_name(dataobj.getString("file_name"));
                            privateStudy.setFile_name_2(dataobj.getString("file_name_2"));
                            privateStudy.setId_invoice(dataobj.getString("id_invoice"));
                            privateStudy.setId_client_activity(dataobj.getString("id_client_activity"));
                            privateStudy.setUrl(dataobj.getString("url"));
                            privateStudies.add(privateStudy);
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
     //       Toast.makeText(PrivateStudyActivity.this, "something wents wrong", Toast.LENGTH_SHORT).show();
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
        PrivateStudyAdapter adapter = new PrivateStudyAdapter(this, this, privateStudies);
        recyclerView.setAdapter(adapter);
        HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
    }
}