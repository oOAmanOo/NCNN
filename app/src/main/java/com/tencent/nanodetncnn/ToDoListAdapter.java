package com.tencent.nanodetncnn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.MyViewHolder>{

    Context context;
    String[] listname;
    String[] listfood;
    String[] listtime;
    private  OnItemClickedListener mlistener;

    public interface OnItemClickedListener{
        void onItemDelete(int position);
    }

    public void setOnItemClickedListener(OnItemClickedListener listener){
        mlistener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list_adapter,parent,false),mlistener);

    }

    public ToDoListAdapter(Context applicationContext, String[] listname, String[] listfood, String[] listtime){

        this.context = applicationContext;
        this.listname = listname;
        this.listfood = listfood;
        this.listtime = listtime;

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.listname.setText(listname[position]+": ");
        holder.listfood.setText("待辦內容: "+listfood[position]);
        holder.listtime.setText("每日 "+listtime[position]);

    }

    @Override
    public int getItemCount() {

        return ToDoListActivity.count;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView listname;
        TextView listtime;
        TextView listfood;
        Button listdone;

        public MyViewHolder(@NonNull View itemView, OnItemClickedListener listener) {

            super(itemView);

            listname = itemView.findViewById(R.id.listname);
            listtime = itemView.findViewById(R.id.listtime);
            listfood = itemView.findViewById(R.id.listfood);
            listdone = itemView.findViewById(R.id.listdone);

            listdone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemDelete(position);
                        }
                    }
                }
            });


        }

    }


}
