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

import java.io.File;
import java.time.LocalDate;

import recycler.ListAdapter;

public class fragment1 extends DialogFragment {

//    @Nullable
    public Dialog dialog1;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.d1_recyclerView);
        ListAdapter listAdapter = new ListAdapter(this.getActivity(), MainActivity.confirm_class_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(listAdapter);

        dialog1 = this.getDialog();
        dialog1.setTitle(Html.fromHtml("<font color='#00455F'>請選擇欲添加至冰箱的食物"));
        dialog1.setCanceledOnTouchOutside(false);
        final FragmentManager fm = getParentFragmentManager();

        Button re_button = (Button) view.findViewById(R.id.d1_re_button);
        re_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity.result_java = '1';
                File file = new File("/data/data/com.tencent.nanodetncnn/result.txt");
                file.delete();
                NcnnYolov5.varifyCheck(MainActivity.result_java);
                MainActivity.current_dialog = -1;
                MainActivity.dialog_change(MainActivity.current_dialog, MainActivity.origin_dialog, MainActivity.last_dialog, fm);
            }
        });
        Button next_button = (Button) view.findViewById(R.id.d1_next_button);
        next_button.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int none_click = 1;
                for (int i = 0; i < MainActivity.confirm_class_list.length; i++) {
                    if(!MainActivity.class_list_checked[i].equals("1")) {
                        ++none_click;
                    }
                }
                if(none_click != MainActivity.confirm_class_list.length) {
                    try {
                        MainActivity.fridge_index = 0;
                        JSONObject obj = null;
                        JSONArray table = null;
                        JSONObject data = null;
                        obj = new JSONObject(MainActivity.result);
                        table = obj.getJSONArray("food_dic");
                        for (int i = 0; i < table.length(); i++) {
                            data = table.getJSONObject(i);
                            for (int j = 0; j < MainActivity.confirm_class_list.length; j++) {
                                if (data.getString("name").equals(MainActivity.confirm_class_list[j]) && MainActivity.class_list_checked[j].equals("1")) {
                                    MainActivity.fridge_did[MainActivity.fridge_index] = data.getString("did");
                                    MainActivity.fridge_name[MainActivity.fridge_index] = data.getString("name");
                                    MainActivity.fridge_position[MainActivity.fridge_index] = data.getString("position");
                                    MainActivity.fridge_expiredate[MainActivity.fridge_index] = LocalDate.now().plusDays(Integer.parseInt(data.getString("expireDay"))).toString();
                                    MainActivity.fridge_imgName[MainActivity.fridge_index] = data.getString("imgName");
                                    MainActivity.fridge_amount[MainActivity.fridge_index] = "1";
                                    MainActivity.fridge_memo[MainActivity.fridge_index] = "#";
                                    ++MainActivity.fridge_index;
                                }
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    MainActivity.current_dialog = 2;
                    dialog1.hide();
                    MainActivity.dialog_change(MainActivity.current_dialog, MainActivity.origin_dialog, MainActivity.last_dialog, fm);
                }else{
                    MainActivity.current_dialog = 5;
                    dialog1.hide();
                    MainActivity.dialog_change(MainActivity.current_dialog, MainActivity.origin_dialog, MainActivity.last_dialog, fm);
                }
            }
        });
        return view;
    }
}
