package com.tencent.nanodetncnn;

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

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import recycler.editfridge_ListAdapter_21;


public class editfridge_dfragment2 extends DialogFragment {
    public Dialog editdialog2;
    public static FragmentActivity nested_f ;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        FragmentActivity nested_f = this.getActivity();
        View view = inflater.inflate(R.layout.editfridge2_layout, container,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ef2_recyclerView);
        int counter = 0;
        for (int i = 0; i < dialog_fragment.listDid.length; ++i){
            if(dialog_fragment.checkeditems[i] == true){
                ++counter;
            }
        }
        String editfridge_did[] = new String[counter];
        String editfridge_name[] = new String[counter];
        String editfridge_imgName[] = new String[counter];

        counter = 0;
        for (int i = 0; i < dialog_fragment.listDid.length; ++i){
            if(dialog_fragment.checkeditems[i] == true){
                editfridge_did[counter] = dialog_fragment.listDid[i];
                editfridge_name[counter] = dialog_fragment.listItem[i];
                editfridge_imgName[counter] = dialog_fragment.listImg[i];
                ++counter;
            }
        }
        editfridge_ListAdapter_21 editfridge_listAdapter_2 = new editfridge_ListAdapter_21(this.getActivity(), editfridge_did, editfridge_name, editfridge_imgName);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(editfridge_listAdapter_2);

        editdialog2 = this.getDialog();
        editdialog2.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        editdialog2.setCanceledOnTouchOutside(false);

        Button re_button = (Button) view.findViewById(R.id.ef2_re_button);
        re_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity.current_editdialog = 1;
                editdialog2.dismiss();
                MainActivity.editdialog_change(MainActivity.current_editdialog, MainActivity.origin_editdialog, MainActivity.fm_p);
            }
        });

        Button next_button = (Button) view.findViewById(R.id.ef2_next_button);
        next_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
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
                MainActivity.editdialog_change(MainActivity.current_editdialog, MainActivity.origin_editdialog, MainActivity.fm_p);
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
