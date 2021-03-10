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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sukrit.mckkrs.Adapters.TestAdapter;
import com.sukrit.mckkrs.Helper.URLHelper;
import com.sukrit.mckkrs.Models.AnswerModel;
import com.sukrit.mckkrs.Models.QuestionModel;
import com.sukrit.mckkrs.R;
import com.sukrit.mckkrs.XuberServicesApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager HorizontalLayout;
    ArrayList<QuestionModel> mListQuestions;
    ImageView image;
    String id, id_client_activity, id_activity;
    TextView txt;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        progressDialog=new ProgressDialog(TestActivity.this);
        progressDialog.setMessage("Please wait...");
        image = findViewById(R.id.image);
        txt = findViewById(R.id.txt);
        id = getIntent().getStringExtra("id");
        id_client_activity = getIntent().getStringExtra("id_client_activity");
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TestActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        getPrivateStudy();
    }

    public void getPrivateStudy(){
        progressDialog.show();
        JSONObject object = new JSONObject();
        try {
            object.put("id_activity", id);
            object.put("id_client_activity", id_client_activity);

            Log.e("ChangePasswordAPI", "" + object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLHelper.TEST, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("res2",response.toString());
                try {
                    progressDialog.hide();
                    JSONObject obj = new JSONObject(String.valueOf(response));
                    if(obj.optString("status").equals("200")){
                        mListQuestions = new ArrayList<>();
                        JSONObject jsonObject  = obj.getJSONObject("userdata");
                        txt.setText(jsonObject.getString("activity_name"));
                        String id_activity = jsonObject.getString("id_client_activity");

                        JSONArray dataArray  = jsonObject.getJSONArray("question");

                        for (int i = 0; i < dataArray.length(); i++) {
                            QuestionModel questionModel = new QuestionModel();
                            JSONObject dataobj = dataArray.getJSONObject(i);
                            questionModel.setId_activity(dataobj.getString("id_activity"));
                            questionModel.setId_question(dataobj.getString("id_question"));
                            questionModel.setQuestion_txt(dataobj.getString("question_txt"));

                            JSONArray dataArray1 = dataobj.getJSONArray("answers");

                            ArrayList<AnswerModel> answerList=new ArrayList<>();

                            for (int j = 0; j < dataArray1.length(); j++) {
                                AnswerModel answerModel = new AnswerModel();
                                JSONObject dataobj1 = dataArray1.getJSONObject(j);
                                answerModel.setAnswer_txt(dataobj1.getString("answer_txt"));
                                answerModel.setId_answer(dataobj1.getString("id_answer"));
                                answerModel.setAnswer_correct(dataobj1.getString("answer_correct"));
                                answerList.add(answerModel);
                            }
                            questionModel.setAnswerModels(answerList);
                            mListQuestions.add(questionModel);
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
        TestAdapter adapter = new TestAdapter(this, mListQuestions);
        recyclerView.setAdapter(adapter);
        HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
    }
}