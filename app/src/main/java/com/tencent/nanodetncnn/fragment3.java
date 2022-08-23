package com.tencent.nanodetncnn;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class fragment3 extends DialogFragment {

//    @Nullable
    public Dialog dialog3;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3_layout, container);

        dialog3 = this.getDialog();
        dialog3.setTitle(Html.fromHtml("<font color='#00455F'>請輸入欲手動新增食物筆數"));
        dialog3.setCanceledOnTouchOutside(false);
        final FragmentManager fm = getParentFragmentManager();

        Button re_button = (Button) view.findViewById(R.id.d3_re_button);
        re_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity.current_dialog = 2;
                dialog3.hide();
                MainActivity.dialog_change(MainActivity.current_dialog, MainActivity.origin_dialog, MainActivity.last_dialog, fm);
            }
        });
        Button next_button = (Button) view.findViewById(R.id.d3_next_button);
        next_button.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                EditText d3_editTextNumber = (EditText) view.findViewById(R.id.d3_editTextNumber);
                MainActivity.addNum = Integer.parseInt(d3_editTextNumber.getText().toString());
                MainActivity.current_dialog = 4;
                dialog3.hide();
                MainActivity.dialog_change(MainActivity.current_dialog, MainActivity.origin_dialog, MainActivity.last_dialog, fm);
            }
        });
        return view;
    }
}
