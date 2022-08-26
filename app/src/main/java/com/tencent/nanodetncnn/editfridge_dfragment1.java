package com.tencent.nanodetncnn;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import recycler.editfridge_ListAdapter_1;


public class editfridge_dfragment1 extends DialogFragment {
    public Dialog efdialog1;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.editfridge1_layout, container);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ef1_recyclerView);
        String editfridge_did[] = new String[MainActivity.editfridge_index];
        String editfridge_name[] = new String[MainActivity.editfridge_index];
        String editfridge_imgName[] = new String[MainActivity.editfridge_index];
        int editfridge_num[] = new int[MainActivity.editfridge_index];
        for (int i = 0; i < MainActivity.editfridge_index; ++i){
            editfridge_did[i] = MainActivity.editfridge_did[i];
            editfridge_name[i] = MainActivity.editfridge_name[i];
            editfridge_imgName[i] = MainActivity.editfridge_imgName[i];
            editfridge_num[i] = MainActivity.editfridge_num[i];
        }

        editfridge_ListAdapter_1 editfridge_listAdapter_1 = new editfridge_ListAdapter_1(this.getActivity(), editfridge_did, editfridge_name, editfridge_imgName, editfridge_num);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(editfridge_listAdapter_1);

        efdialog1 = this.getDialog();
        efdialog1.setTitle(Html.fromHtml("<font color='#00455F'>請輸入欲消耗食材數量"));
        efdialog1.setCanceledOnTouchOutside(false);
        final FragmentManager fm = getParentFragmentManager() ;

        Button re_button = (Button) view.findViewById(R.id.ef1_re_button);
        re_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                efdialog1.dismiss();
                MainActivity.dialog_change(MainActivity.current_dialog, MainActivity.origin_dialog, MainActivity.last_dialog, fm);
            }
        });

        Button next_button = (Button) view.findViewById(R.id.ef1_next_button);
        next_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int next = 1;
                for (int i = 0; i < editfridge_ListAdapter_1.editfridgeAmountText.length; i++) {
                    if(editfridge_ListAdapter_1.editfridgeAmountText[i] == 0){
                        next = 0;
                        break;
                    }
                }
                if(next == 1){
                    int first = 0;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("[");
                    for (int i = 0; i < MainActivity.editfridge_index; ++i){
                        if(MainActivity.editfridge_num[i] > 0) {
                            if(first != 0){
                                stringBuilder.append(",");
                            }else{
                                ++first;
                            }
                            stringBuilder.append("\"" + MainActivity.editfridge_did[i] + "\"");
                        }
                    }
                    stringBuilder.append("]");
                    MainActivity.editjson = stringBuilder.toString();
                    MainActivity.current_editdialog = 2;
                    efdialog1.hide();
                    MainActivity.editdialog_change(MainActivity.current_editdialog, MainActivity.origin_editdialog, fm);
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

        return view;
    }
}
