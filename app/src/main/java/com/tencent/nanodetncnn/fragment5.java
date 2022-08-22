package com.tencent.nanodetncnn;

import android.app.AlertDialog;
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

import java.io.File;

public class fragment5 extends DialogFragment {

//    @Nullable
    public Dialog dialog5;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment5_layout, container);

        dialog5 = this.getDialog();
        dialog5.setTitle(Html.fromHtml("<font color='#00455F'>請輸入欲手動新增食物筆數"));
        dialog5.setCanceledOnTouchOutside(false);
        final FragmentManager fm = getParentFragmentManager();

        Button re_button = (Button) view.findViewById(R.id.d5_re_button);
        re_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity.result_java = '1';
                File file = new File("/data/data/com.tencent.nanodetncnn/result.txt");
                file.delete();
                NcnnYolov5.varifyCheck(MainActivity.result_java);
                MainActivity.current_dialog = -1;
                MainActivity.dialog_change(MainActivity.current_dialog, MainActivity.origin_dialog, MainActivity.last_dialog, fm);
                dialog5.hide();
            }
        });
        Button next_button = (Button) view.findViewById(R.id.d5_next_button);
        next_button.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                EditText d5_editTextNumber = (EditText) view.findViewById(R.id.d5_editTextNumber);
                MainActivity.addNum = Integer.parseInt(d5_editTextNumber.getText().toString());
                if(MainActivity.addNum > 0){
                    MainActivity.current_dialog = 4;
                    dialog5.hide();
                    MainActivity.dialog_change(MainActivity.current_dialog, MainActivity.origin_dialog, MainActivity.last_dialog, fm);
                }else{
                    AlertDialog.Builder dumb = new AlertDialog.Builder(MainActivity.mContext2);

                }

            }
        });
        return view;
    }
}
