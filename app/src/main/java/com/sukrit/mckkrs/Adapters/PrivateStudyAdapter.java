package com.sukrit.mckkrs.Adapters;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sukrit.mckkrs.Activities.TestActivity;
import com.sukrit.mckkrs.Models.PrivateStudy;
import com.sukrit.mckkrs.R;

import java.util.ArrayList;

public class PrivateStudyAdapter extends RecyclerView.Adapter<PrivateStudyAdapter.MyViewHolder> {
    private Context context;
    GotoTestFragment OnGotoTestFragment;
    private ArrayList<PrivateStudy> privateStudies;

    public PrivateStudyAdapter(Context context, GotoTestFragment OnGotoTestFragment, ArrayList<PrivateStudy> privateStudies) {
        this.context = context;
        this.OnGotoTestFragment = OnGotoTestFragment;
        this.privateStudies = privateStudies;
    }
    @NonNull
    @Override
    public PrivateStudyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pri_study_adapter, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull PrivateStudyAdapter.MyViewHolder holder, int position) {
        holder.txt_name.setText(privateStudies.get(position).getActivity_name());

        holder.linear_down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String filename1 = privateStudies.get(position).getUrl()+privateStudies.get(position).getFile_name();
                Uri uri = Uri.parse(filename1);
                DownloadManager downloadManager = (DownloadManager)
                        context.getSystemService(Context.DOWNLOAD_SERVICE);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setTitle(privateStudies.get(position).getFile_name());
                request.setDescription(privateStudies.get(position).getFile_name());
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = downloadManager.enqueue(request);
            }
        });
        holder.img_open_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = privateStudies.get(position).getUrl()+privateStudies.get(position).getFile_name();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(filename));
                context.startActivity(browserIntent);
            }
        });

        holder.linear_cpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, TestActivity.class);
                i.putExtra("id",privateStudies.get(position).getId());
                i.putExtra("id_client_activity",privateStudies.get(position).getId_client_activity());
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return privateStudies.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_private, txt_name;
        LinearLayout linear_cpd, linear_down;
        WebView webview;
        ImageView img_open_pdf;
        ProgressBar progressbar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_private = itemView.findViewById(R.id.txt_private);
            txt_name = itemView.findViewById(R.id.txt_name);
            linear_cpd = itemView.findViewById(R.id.linear_cpd);
            webview = itemView.findViewById(R.id.webview);
            linear_down = itemView.findViewById(R.id.linear_down);
            img_open_pdf = itemView.findViewById(R.id.img_open_pdf);
            webview.getSettings().setJavaScriptEnabled(true);
        }
    }
    public interface GotoTestFragment{
        public void ClickListener();
    }
}