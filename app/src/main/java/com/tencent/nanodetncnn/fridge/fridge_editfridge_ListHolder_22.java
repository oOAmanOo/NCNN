package com.tencent.nanodetncnn.fridge;


import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.R;

public class fridge_editfridge_ListHolder_22 extends RecyclerView.ViewHolder {
    TextView ef22_date_textview;
    TextView ef22_amount_textview;
    EditText ef22_editTextNumber;
    ImageButton ef22_imageButton;
    public fridge_editfridge_ListHolder_22(@NonNull View itemView) {
        super(itemView);
        TextView ef22_date_textview;
        TextView ef22_amount_textview;
        EditText ef22_editTextNumber;
        ImageButton ef22_imageButton;
        ef22_date_textview= (TextView) itemView.findViewById(R.id.ef22_date_textview);
        ef22_amount_textview= (TextView) itemView.findViewById(R.id.ef22_amount_textview);
        ef22_editTextNumber= (EditText) itemView.findViewById(R.id.ef22_editTextNumber);
        ef22_imageButton= (ImageButton) itemView.findViewById(R.id.ef22_imageButton);
    }

}
