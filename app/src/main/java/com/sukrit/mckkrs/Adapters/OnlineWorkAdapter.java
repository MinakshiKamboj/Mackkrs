package com.sukrit.mckkrs.Adapters;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sukrit.mckkrs.Models.OnlineWorkShop;
import com.sukrit.mckkrs.R;

import java.util.ArrayList;

public class OnlineWorkAdapter extends RecyclerView.Adapter<OnlineWorkAdapter.MyViewHolder> {

    private Context context;
    GotoBookOnlineWorkshopFragment OnGotoTestFragment;
    private ArrayList<OnlineWorkShop> onlineWorkShops;

    public OnlineWorkAdapter(Context context,  GotoBookOnlineWorkshopFragment OnGotoTestFragment, ArrayList<OnlineWorkShop> onlineWorkShops) {
        this.context = context;
        this.OnGotoTestFragment = OnGotoTestFragment;
        this.onlineWorkShops = onlineWorkShops;
    }
    @NonNull
    @Override
    public OnlineWorkAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.online_work_adapter, parent, false);
        return new OnlineWorkAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull OnlineWorkAdapter.MyViewHolder holder, int position) {
        holder.txt_name.setText(onlineWorkShops.get(position).getActivity_name());

        holder.linear_download.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String filename1 = onlineWorkShops.get(position).getUrl()+onlineWorkShops.get(position).getFile_name();
                Uri uri = Uri.parse(filename1);
                DownloadManager downloadManager = (DownloadManager)
                        context.getSystemService(Context.DOWNLOAD_SERVICE);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setTitle("your tittle");
                request.setDescription("your description");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = downloadManager.enqueue(request);
            }
        });

    }
    @Override
    public int getItemCount() {
        return onlineWorkShops.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_onln_wrk, txt_name;
        LinearLayout linear_download;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_onln_wrk = itemView.findViewById(R.id.txt_onln_wrk);
            txt_name = itemView.findViewById(R.id.txt_name);
            linear_download = itemView.findViewById(R.id.linear_download);

        }
    }
    public interface GotoBookOnlineWorkshopFragment{
        public void ClickListener();
    }

}