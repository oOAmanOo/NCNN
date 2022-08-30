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
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import recycler.dialog_fragment_ListAdapter_1;

public class dialog_fragment extends DialogFragment {
    public static String[] listItem;
    public static String[] listDid;
    public static String[] listImg;
    public static String[] allfoodName;
    public static boolean[] allfoodDid;
    public static boolean[] checkeditems;
    public static Button update_percentage;


    public static void getlistItem(String[] listItems, String[] listDIds, String[] listImgs, String[] allfoodNames, boolean[] allfoodDids){
        listItem = listItems;
        listDid = listDIds;
        listImg = listImgs;
        allfoodName = allfoodNames;
        allfoodDid = allfoodDids;


        checkeditems = new boolean[listItem.length];

    }




    public Dialog efdialog1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        efdialog1 = this.getDialog();
        efdialog1.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_food_layout, container,false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ef1_recyclerView);
        TextView percentage = (TextView) view.findViewById(R.id.percentage);
        update_percentage = (Button) view.findViewById(R.id.update_percentage);


        update_percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                for(int i = 0; i < checkeditems.length; ++i){
                    if(checkeditems[i]){
                        count+=1;

                    }
                }
                percentage.setText("可行性： " + count + "/" + checkeditems.length);
            }
        });



//        String editfridge_did[] = new String[MainActivity.editfridge_index];
//        String editfridge_name[] = new String[MainActivity.editfridge_index];
//
//
//
//        int editfridge_num[] = new int[MainActivity.editfridge_index];
//        for (int i = 0; i < MainActivity.editfridge_index; ++i){
//            editfridge_did[i] = MainActivity.editfridge_did[i];
//            editfridge_name[i] = MainActivity.editfridge_name[i];
//            editfridge_num[i] = MainActivity.editfridge_num[i];
//        }


        dialog_fragment_ListAdapter_1 dialog_fragment_ListAdapter_1 = new dialog_fragment_ListAdapter_1(this.getActivity(),listDid , listItem, listImg, allfoodDid);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(dialog_fragment_ListAdapter_1);


//        efdialog1.setTitle(Html.fromHtml("<font color='#00455F'>請勾選您有的食材"));

        efdialog1.setCanceledOnTouchOutside(false);
        final FragmentManager fm = getParentFragmentManager() ;

        Button re_button = (Button) view.findViewById(R.id.ef1_re_button);

        re_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                efdialog1.dismiss();

                MainActivity.current_editdialog = -1;
                MainActivity.editdialog_change(MainActivity.current_editdialog, MainActivity.origin_editdialog, fm);
            }
        });

        Button next_button = (Button) view.findViewById(R.id.ef1_next_button);
        next_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                int next = 1;
//                for (int i = 0; i < editfridge_ListAdapter_1.editfridgeAmountText.length; i++) {
//                    if(editfridge_ListAdapter_1.editfridgeAmountText[i] == 0){
//                        next = 0;
//                        break;
//                    }
//                }

                int first = 0;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("[");
                for (int i = 0; i < listDid.length; ++i){
                    if(allfoodDid[i]) {

                        if(first != 0){
                            stringBuilder.append(",");
                        }else{
                            ++first;
                        }
                        stringBuilder.append("\"" + listDid[i] + "\"");
                    }
                }
                stringBuilder.append("]");
                MainActivity.editjson = stringBuilder.toString();
                MainActivity.current_editdialog = 2;
                efdialog1.hide();
                MainActivity.editdialog_change(MainActivity.current_editdialog, MainActivity.origin_editdialog, fm);


//                if(next == 1){
//
//                }else{
//                    AlertDialog.Builder dumb = new AlertDialog.Builder(v.getContext());
//                    dumb.setTitle(Html.fromHtml("<font color='#00455F'>錯誤"));
//                    dumb.setMessage(Html.fromHtml("<font color='#00455F'>食物數量不可小於 1"));
//                    dumb.setPositiveButton("確定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//                    AlertDialog dialog = dumb.create();
//                    dialog.show();
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
        }
    }
}
