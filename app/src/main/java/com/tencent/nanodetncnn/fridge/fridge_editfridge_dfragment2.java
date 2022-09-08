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

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.MainActivity;
import com.tencent.nanodetncnn.MyApplication;
import com.tencent.nanodetncnn.R;


public class fridge_editfridge_dfragment2 extends DialogFragment {
    public static Dialog editdialog2;
    public static FragmentActivity nested_f ;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        FragmentActivity nested_f = this.getActivity();
        View view = inflater.inflate(R.layout.editfridge2_layout, container,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ef2_recyclerView);

        String editfridge_did[] = new String[MyApplication.choseItem_List.size()];
        String editfridge_name[] = new String[MyApplication.choseItem_List.size()];
        String editfridge_imgName[] = new String[MyApplication.choseItem_List.size()];

        for (int i = 0; i < MyApplication.choseItem_List.size(); ++i){
                editfridge_did[i] = String.valueOf(MyApplication.choseItem_List.get(i).did);
                editfridge_name[i] = MyApplication.choseItem_List.get(i).name;
                editfridge_imgName[i] = MyApplication.choseItem_List.get(i).imgName;
        }
        fridge_editfridge_ListAdapter_21 editfridge_listAdapter_2 = new fridge_editfridge_ListAdapter_21(this.getActivity(), editfridge_did, editfridge_name, editfridge_imgName);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(editfridge_listAdapter_2);

        editdialog2 = this.getDialog();
        editdialog2.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        editdialog2.setCanceledOnTouchOutside(false);

        Button re_button = (Button) view.findViewById(R.id.ef2_re_button);
        re_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                editdialog2.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);

                editdialog2.dismiss();
                MainActivity.editfridgedialog_change(-1, 0, MainActivity.fm_p);
            }
        });

        Button next_button = (Button) view.findViewById(R.id.ef2_next_button);
        next_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                int next = 1;
//
//                for (int i = 0; i < MainActivity.editfridgedb_index; i++) {
//
//                    if(MainActivity.editfridge_fault[i] == -2){
//                        AlertDialog.Builder dumb = new AlertDialog.Builder(v.getContext());
//                        dumb.setTitle(Html.fromHtml("<font color='#00455F'>錯誤"));
//                        dumb.setMessage(Html.fromHtml("<font color='#00455F'>編輯數量超過存儲數量"));
//                        dumb.setPositiveButton("確定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                        AlertDialog dialog = dumb.create();
//                        dialog.show();
//                        next = 0;
//                        break;
//                    }
//                    else if(MainActivity.editfridge_fault[i] == -1){
//                        AlertDialog.Builder dumb = new AlertDialog.Builder(v.getContext());
//                        dumb.setTitle(Html.fromHtml("<font color='#00455F'>錯誤"));
//                        dumb.setMessage(Html.fromHtml("<font color='#00455F'>編輯數值不得為負數"));
//                        dumb.setPositiveButton("確定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                        AlertDialog dialog = dumb.create();
//                        dialog.show();
//                        next = 0;
//                        break;
//                    }
//                }
//                if(next == 1){
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
                    MainActivity.editfridgedialog_change(0, 2, MainActivity.fm_p);
//                }
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
//            fridge_editfridge_dfragment2.editdialog2.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        }
    }
}
