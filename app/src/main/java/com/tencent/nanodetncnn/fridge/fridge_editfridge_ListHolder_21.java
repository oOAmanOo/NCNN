package com.tencent.nanodetncnn.fridge;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.R;

public class fridge_editfridge_ListHolder_21 extends RecyclerView.ViewHolder {
    ImageView ef21_imageView;
    TextView ef21_textView;
    RecyclerView ef21_recyclerview;
    public fridge_editfridge_ListHolder_21(@NonNull View itemView) {
        super(itemView);
        ImageView ef21_imageView;
        TextView ef21_textView;
        RecyclerView ef21_recyclerview;
        ef21_imageView = (ImageView) itemView.findViewById(R.id.ef21_imageView);
        ef21_textView= (TextView) itemView.findViewById(R.id.ef21_textView);
        ef21_recyclerview = (RecyclerView) itemView.findViewById(R.id.ef21_recyclerview);
    }

}
