package com.tencent.nanodetncnn;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
        if(Verify_Activity.enter_dialog == 1){
            re_button.setText("上一步");
        }else{
            re_button.setText("重新辨識");
        }
        re_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(Verify_Activity.enter_dialog == 1){
                    Verify_Activity.current_dialog = 2;
                }else{
                    Verify_Activity.result_java = '1';
                    Verify_Activity.verButton.setText("完成辨識");
                    File file = new File("/data/data/com.tencent.nanodetncnn/result.txt");
                    file.delete();
                    NcnnYolov5.varifyCheck(Verify_Activity.result_java);
                    Verify_Activity.current_dialog = -1;
                }
                dialog3.hide();
                Verify_Activity.dialog_change(Verify_Activity.current_dialog, Verify_Activity.origin_dialog, Verify_Activity.last_dialog, fm);
            }
        });
        Button next_button = (Button) view.findViewById(R.id.d3_next_button);
        next_button.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                EditText d3_editTextNumber = (EditText) view.findViewById(R.id.d3_editTextNumber);
                if(d3_editTextNumber.getText().toString().matches("") || Integer.parseInt(d3_editTextNumber.getText().toString()) <= 0){
                    AlertDialog.Builder dumb = new AlertDialog.Builder(v.getContext());
                    dumb.setTitle(Html.fromHtml("<font color='#00455F'>錯誤"));
                    dumb.setMessage(Html.fromHtml("<font color='#00455F'>請填入新增食物筆數，數值需大於 0"));
                    dumb.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = dumb.create();
                    dialog.show();
                }else {
                    Verify_Activity.addNum = Integer.parseInt(d3_editTextNumber.getText().toString());
                    Verify_Activity.current_dialog = 4;
                    dialog3.hide();
                    Verify_Activity.dialog_change(Verify_Activity.current_dialog, Verify_Activity.origin_dialog, Verify_Activity.last_dialog, fm);
                }
            }
        });
        return view;
    }
}
