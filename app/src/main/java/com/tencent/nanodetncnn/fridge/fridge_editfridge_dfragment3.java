package com.tencent.nanodetncnn.fridge;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.tencent.nanodetncnn.MainActivity;
import com.tencent.nanodetncnn.R;


public class fridge_editfridge_dfragment3 extends DialogFragment {
    public Dialog efdialog3;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.editfridge3_layout, container);
        efdialog3 = this.getDialog();
        efdialog3.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        efdialog3.setCanceledOnTouchOutside(false);
        ImageView ef3_imageView = (ImageView) view.findViewById(R.id.ef3_imageView);
        TextView ef3_textView = (TextView) view.findViewById(R.id.ef3_textView);
        TextView ef3_owner_textview = (TextView) view.findViewById(R.id.ef3_owner_textview);
        TextView ef3_position_textview = (TextView) view.findViewById(R.id.ef3_position_textview);
        TextView ef3_insertDate_textview = (TextView) view.findViewById(R.id.ef3_insertDate_textview);
        TextView ef3_expireDate_textview = (TextView) view.findViewById(R.id.ef3_expireDate_textview);
        TextView ef3_amount_textview4 = (TextView) view.findViewById(R.id.ef3_amount_textview4);
        TextView ef3_remark_edittext = (TextView) view.findViewById(R.id.ef3_remark_edittext);

        ef3_imageView.setImageResource(getContext().getApplicationContext().getResources().getIdentifier(String.valueOf(MainActivity.info_editfridge_imgName),"drawable", getContext().getPackageName()));
        ef3_textView.setText(MainActivity.info_editfridge_name);
        ef3_owner_textview.setText(MainActivity.info_editfridgedb_uid);

        if(MainActivity.info_editfridgedb_position.equals("0")){
            ef3_position_textview.setText("冷凍");
        }else{
            ef3_position_textview.setText("冷藏");
        }
        ef3_insertDate_textview.setText(MainActivity.info_editfridgedb_insertDate);
        ef3_expireDate_textview.setText(MainActivity.info_editfridgedb_expireDate);
        ef3_amount_textview4.setText(MainActivity.info_editfridgedb_amount);
        ef3_remark_edittext.setText(MainActivity.info_editfridgedb_memo);
        Button next_button = (Button) view.findViewById(R.id.ef3_next_button);
        next_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                efdialog3.dismiss();
            }
        });

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
        }
    }
}
