package com.tencent.nanodetncnn;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

import recycler.ListAdapter_4;

public class fragment4 extends DialogFragment {

//    @Nullable
    public Dialog dialog4;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4_layout, container);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.d4_recyclerView);

        int addIndex[] = new int[MainActivity.addNum];
        for(int i = 0; i < MainActivity.addNum; ++i){
            addIndex[i] = i;

        }
        ListAdapter_4 listAdapter_4 = new ListAdapter_4(this.getActivity(), addIndex);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(listAdapter_4);

        dialog4 = this.getDialog();
        dialog4.setTitle(Html.fromHtml("<font color='#00455F'>請填入食物名稱"));
        dialog4.setCanceledOnTouchOutside(false);
        final FragmentManager fm = getParentFragmentManager();

        Button re_button = (Button) view.findViewById(R.id.d4_re_button);
        re_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    MainActivity.current_dialog = 6;
                    dialog4.hide();
                    MainActivity.dialog_change(MainActivity.current_dialog, MainActivity.origin_dialog, MainActivity.last_dialog, fm);
            }
        });

        Button next_button = (Button) view.findViewById(R.id.d4_next_button);
        next_button.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                MainActivity.old_fridge_index = MainActivity.fridge_index;
                MainActivity.fridge_index += MainActivity.addNum;
                try {
                    JSONObject obj = null;
                    JSONArray table = null;
                    JSONObject data = null;
                    obj = new JSONObject(MainActivity.result);
                    table = obj.getJSONArray("food_dic");

                    for (int i = 0; i < table.length(); i++) {
                        data = table.getJSONObject(i);
                        for (int j = MainActivity.old_fridge_index; j < MainActivity.fridge_index; j++) {
                            if (data.getString("name").equals(MainActivity.fridge_name[j])) {
                                MainActivity.fridge_did[j] = data.getString("did");
                                MainActivity.fridge_position[j] = data.getString("position");
                                MainActivity.fridge_expiredate[j] = LocalDate.now().plusDays(Integer.parseInt(data.getString("expireDay"))).toString();
                                MainActivity.fridge_imgName[j] = data.getString("imgName");
                                MainActivity.fridge_amount[j] = "1";
                                MainActivity.fridge_memo[j] = "#";
                                System.out.println(j+" : "+MainActivity.fridge_memo[j]);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MainActivity.current_dialog = 2;
                dialog4.hide();
                MainActivity.dialog_change(MainActivity.current_dialog, MainActivity.origin_dialog, MainActivity.last_dialog, fm);
            }
        });
        return view;
    }
}
