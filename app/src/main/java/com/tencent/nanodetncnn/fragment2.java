package com.tencent.nanodetncnn;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

import recycler.ListAdapter_2;


public class fragment2 extends DialogFragment {
    public Dialog dialog2;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout, container);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.d2_recyclerView);
        String fridge_did[] = new String[Verify_Activity.fridge_index];
        String fridge_name[] = new String[Verify_Activity.fridge_index];
        String fridge_position[] = new String[Verify_Activity.fridge_index];
        String fridge_expiredate[] = new String[Verify_Activity.fridge_index];
        String fridge_imgName[] = new String[Verify_Activity.fridge_index];
        String fridge_amount[] = new String[Verify_Activity.fridge_index];
        String fridge_memo[] = new String[Verify_Activity.fridge_index];
        for (int i = 0; i < Verify_Activity.fridge_index; ++i){
            fridge_did[i] = Verify_Activity.fridge_did[i];
            fridge_name[i] = Verify_Activity.fridge_name[i];
            fridge_position[i] = Verify_Activity.fridge_position[i];
            fridge_expiredate[i] = Verify_Activity.fridge_expiredate[i];
            fridge_imgName[i] = Verify_Activity.fridge_imgName[i];
            fridge_amount[i] = Verify_Activity.fridge_amount[i];
            fridge_memo[i] = Verify_Activity.fridge_memo[i];
        }
        ListAdapter_2 listAdapter_2 = new ListAdapter_2(this.getActivity(), fridge_did, fridge_name, fridge_position, fridge_expiredate, fridge_imgName, fridge_amount, fridge_memo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(listAdapter_2);

        dialog2 = this.getDialog();
        dialog2.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog2.setCanceledOnTouchOutside(false);
        final FragmentManager fm = getParentFragmentManager() ;

        Button re_button = (Button) view.findViewById(R.id.d2_re_button);
        if(Verify_Activity.enter_dialog == 1){
            re_button.setText("上一步");
        }else{
            re_button.setText("重新辨識");
        }
        re_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(Verify_Activity.enter_dialog == 1){
                    Verify_Activity.current_dialog = 1;
                }else{
                    Verify_Activity.result_java = '1';
                    Verify_Activity.verButton.setText("完成辨識");
                    File file = new File("/data/data/com.tencent.nanodetncnn/result.txt");
                    file.delete();
                    NcnnYolov5.varifyCheck(Verify_Activity.result_java);
                    Verify_Activity.current_dialog = -1;
                }
                dialog2.dismiss();
                Verify_Activity.dialog_change(Verify_Activity.current_dialog, Verify_Activity.origin_dialog, Verify_Activity.last_dialog, fm);
            }
        });

        Button next_button = (Button) view.findViewById(R.id.d2_next_button);
        next_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int next = 1;
                for (int i = 0; i < ListAdapter_2.fridgeAmountText.length; i++) {
                    if(ListAdapter_2.fridgeAmountText[i] == 0){
                        next = 0;
                        break;
                    }
                }
                if(next == 1){
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("[");
                    for (int i = 0; i < Verify_Activity.fridge_index; ++i){
                        if(i == 0){
                            stringBuilder.append("{");
                        }else{
                            stringBuilder.append(",{");
                        }
                        stringBuilder.append("\"did\":\"" + Verify_Activity.fridge_did[i] + "\",");
                        stringBuilder.append("\"name\":\"" + Verify_Activity.fridge_name[i] + "\",");
                        stringBuilder.append("\"position\":\"" + Verify_Activity.fridge_position[i] + "\",");
                        stringBuilder.append("\"expireDate\":\"" + Verify_Activity.fridge_expiredate[i] + "\",");
                        stringBuilder.append("\"amount\":\"" + Verify_Activity.fridge_amount[i] + "\",");
                        stringBuilder.append("\"memo\":\"" + Verify_Activity.fridge_memo[i] + "\"");
                        stringBuilder.append("}");
                    }
                    stringBuilder.append("]");
                    Verify_Activity.json = stringBuilder.toString();
                    Verify_Activity.result_java = '1';
                    File file = new File("/data/data/com.tencent.nanodetncnn/result.txt");
                    file.delete();
                    NcnnYolov5.varifyCheck(Verify_Activity.result_java);
                    Verify_Activity.current_dialog = 0;
                    dialog2.hide();
                    Verify_Activity.dialog_change(Verify_Activity.current_dialog, Verify_Activity.origin_dialog, Verify_Activity.last_dialog, fm);
                }else{
                    AlertDialog.Builder dumb = new AlertDialog.Builder(v.getContext());
                    dumb.setTitle(Html.fromHtml("<font color='#00455F'>錯誤"));
                    dumb.setMessage(Html.fromHtml("<font color='#00455F'>食物數量不可小於 1"));
                    dumb.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = dumb.create();
                    dialog.show();
                }
            }
        });
        ImageButton add_button = (ImageButton) view.findViewById(R.id.d2_AddFood);
        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Verify_Activity.current_dialog = 3;
                dialog2.hide();
                Verify_Activity.dialog_change(Verify_Activity.current_dialog, Verify_Activity.origin_dialog, Verify_Activity.last_dialog, fm);
            }
        });

        return view;
    }
}
