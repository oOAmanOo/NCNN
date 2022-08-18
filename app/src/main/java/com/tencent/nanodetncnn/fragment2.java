package com.tencent.nanodetncnn;

import android.app.Dialog;
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

import recycler.ListAdapter_2;

public class fragment2 extends DialogFragment {

//    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout, container);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.d2_recyclerView);

        String fridge_did[] = new String[MainActivity.fridge_index];
        String fridge_name[] = new String[MainActivity.fridge_index];
        String fridge_position[] = new String[MainActivity.fridge_index];
        String fridge_expiredate[] = new String[MainActivity.fridge_index];
        String fridge_imgName[] = new String[MainActivity.fridge_index];
        String fridge_amount[] = new String[MainActivity.fridge_index];
        String fridge_memo[] = new String[MainActivity.fridge_index];

        for (int i = 0; i < MainActivity.fridge_index; ++i){
            fridge_did[i] = MainActivity.fridge_did[i];
            fridge_name[i] = MainActivity.fridge_name[i];
            fridge_position[i] = MainActivity.fridge_position[i];
            fridge_expiredate[i] = MainActivity.fridge_expiredate[i].toString();
            fridge_imgName[i] = MainActivity.fridge_imgName[i];
            fridge_amount[i] = MainActivity.fridge_amount[i];
            fridge_memo[i] = MainActivity.fridge_memo[i];
        }
        ListAdapter_2 listAdapter_2 = new ListAdapter_2(this.getActivity(), fridge_did, fridge_name, fridge_position, fridge_expiredate, fridge_imgName, fridge_amount, fridge_memo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(listAdapter_2);

        Dialog dialog2 = this.getDialog();
        dialog2.setTitle(Html.fromHtml("<font color='#00455F'>請aaa選擇欲添加至冰箱的食物"));
        dialog2.setCanceledOnTouchOutside(false);
        final FragmentManager fm = getParentFragmentManager() ;

        Button re_button = (Button) view.findViewById(R.id.d2_re_button);
        re_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    MainActivity.current_dialog = 1;
                    dialog2.dismiss();
                    MainActivity.dialog_change(MainActivity.current_dialog, MainActivity.origin_dialog, MainActivity.last_dialog, fm);
            }
        });

        Button next_button = (Button) view.findViewById(R.id.d2_next_button);
        next_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("[");
                for (int i = 0; i <= MainActivity.fridge_index; ++i){
                    if(i == 0){
                        stringBuilder.append("{");
                    }else{
                        stringBuilder.append(",{");
                    }
                    stringBuilder.append("\"did\":\"" + MainActivity.fridge_did[i] + "\", ");
                    stringBuilder.append("\"position\":\"" + MainActivity.fridge_position[i] + "\", ");
                    stringBuilder.append("\"expireDate\":\"" + MainActivity.fridge_expiredate[i] + "\", ");
                    stringBuilder.append("\"amount\":\"" + MainActivity.fridge_amount[i] + "\", ");
                    stringBuilder.append("\"memo\":\"" + MainActivity.fridge_memo[i] + "\",");
                    stringBuilder.append("}");
                }
                stringBuilder.append("]");
                MainActivity.json = stringBuilder.toString();
                MainActivity.current_dialog = 0;
                dialog2.dismiss();
                MainActivity.dialog_change(MainActivity.current_dialog, MainActivity.origin_dialog, MainActivity.last_dialog, fm);
            }
        });
//        Button add_button = (Button) view.findViewById(R.id.d2_next_button);
//        add_button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                MainActivity.current_dialog = 3;
//                dialog2.dismiss();
//                MainActivity.dialog_change(MainActivity.current_dialog, MainActivity.past_dialog, fm);
//            }
//        });

        return view;
    }
}
