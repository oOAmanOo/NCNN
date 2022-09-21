package com.tencent.nanodetncnn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlertHistoryAdapter extends RecyclerView.Adapter<AlertHistoryAdapter.MyViewHolder>{

    Context context;
    String[] alertname;
    String[] alerttime;
    String[] alertcontent;



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_history_adapter,parent,false));

    }

    public AlertHistoryAdapter(Context applicationContext, String[] alertname, String[] alerttime, String[] alertcontent){

        this.context = applicationContext;
        this.alertname = alertname;
        this.alerttime = alerttime;
        this.alertcontent = alertcontent;

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.alerttime.setText(alerttime[position]);
        holder.alertcontent.setText(alertcontent[position]);
    }

    @Override
    public int getItemCount() {

        return Alert_History_Activity.count;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView alerttime;
        TextView alertcontent;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            alerttime = itemView.findViewById(R.id.alerttime);
            alertcontent = itemView.findViewById(R.id.alertcontent);


        }

    }


}
