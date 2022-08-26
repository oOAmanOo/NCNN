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
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import recycler.editfridge_ListAdapter_21;


public class editfridge_dfragment2 extends DialogFragment {
    public Dialog editdialog2;
    public static FragmentActivity nested_f ;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        FragmentActivity nested_f = this.getActivity();
        View view = inflater.inflate(R.layout.editfridge2_layout, container);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ef2_recyclerView);
        String editfridge_did[] = new String[MainActivity.editfridge_index];
        String editfridge_name[] = new String[MainActivity.editfridge_index];
        String editfridge_imgName[] = new String[MainActivity.editfridge_index];
        for (int i = 0; i < MainActivity.editfridge_index; ++i){
            editfridge_did[i] = MainActivity.editfridge_did[i];
            editfridge_name[i] = MainActivity.editfridge_name[i];
            editfridge_imgName[i] = MainActivity.editfridge_imgName[i];
        }
        editfridge_ListAdapter_21 editfridge_listAdapter_2 = new editfridge_ListAdapter_21(this.getActivity(), editfridge_did, editfridge_name, editfridge_imgName);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(editfridge_listAdapter_2);

        editdialog2 = this.getDialog();
        editdialog2.setTitle(Html.fromHtml("<font color='#00455F'>食物編輯確認"));
        editdialog2.setCanceledOnTouchOutside(false);
        final FragmentManager fm = getParentFragmentManager();

        Button re_button = (Button) view.findViewById(R.id.ef2_re_button);
        re_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity.current_editdialog = 1;
                editdialog2.dismiss();
                MainActivity.editdialog_change(MainActivity.current_editdialog, MainActivity.origin_editdialog, fm);
            }
        });

        Button next_button = (Button) view.findViewById(R.id.ef2_next_button);
        next_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int next = 1;
                System.out.println(1);
                for (int i = 0; i < MainActivity.editfridge_fault.length; i++) {
                    System.out.println(111);
                    if(MainActivity.editfridge_fault[i] == -2){
                        System.out.println(2);
                        AlertDialog.Builder dumb = new AlertDialog.Builder(v.getContext());
                        dumb.setTitle(Html.fromHtml("<font color='#00455F'>錯誤"));
                        dumb.setMessage(Html.fromHtml("<font color='#00455F'>編輯數量超過存儲數量"));
                        dumb.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = dumb.create();
                        dialog.show();
                        next = 0;
                        break;
                    }else if(MainActivity.editfridge_fault[i] == -1){
                        System.out.println(3);
                        AlertDialog.Builder dumb = new AlertDialog.Builder(v.getContext());
                        dumb.setTitle(Html.fromHtml("<font color='#00455F'>錯誤"));
                        dumb.setMessage(Html.fromHtml("<font color='#00455F'>編輯數值不得為負數"));
                        dumb.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = dumb.create();
                        dialog.show();
                        next = 0;
                        break;
                    }
                }
                if(next == 1){
                    System.out.println(4);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("[");
                    int first = 0;
                    for (int i = 0; i < MainActivity.editfridgedb_index; ++i){
                        if(MainActivity.editfridgedb_editnum[i] == 0){
                            continue;
                        }else if(first == 0){
                            ++first;
                            stringBuilder.append("{");
                        }else{
                            stringBuilder.append(",{");
                        }
                        stringBuilder.append("\"did\":\"" + MainActivity.editfridgedb_did[i] + "\",");
                        stringBuilder.append("\"fid\":\"" + MainActivity.editfridgedb_fid[i] + "\",");
                        stringBuilder.append("\"name\":\"" + MainActivity.editfridgedb_name[i] + "\",");
                        stringBuilder.append("\"editnum\":\"" + MainActivity.editfridgedb_editnum[i] + "\"");
                        stringBuilder.append("}");
                    }
                    stringBuilder.append("]");
                    MainActivity.editjsonupload = stringBuilder.toString();
                    MainActivity.current_editdialog = 0;
                    editdialog2.hide();
                    MainActivity.editdialog_change(MainActivity.current_editdialog, MainActivity.origin_editdialog, fm);
                }else{
                    System.out.println(5);
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
