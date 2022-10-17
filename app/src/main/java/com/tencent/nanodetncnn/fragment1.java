package com.tencent.nanodetncnn;


import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
    public static Dialog dialog1;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.d1_recyclerView);
        ListAdapter listAdapter = new ListAdapter(this.getActivity(), Verify_Activity.confirm_class_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(listAdapter);

        dialog1 = this.getDialog();
        dialog1.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog1.setCanceledOnTouchOutside(false);
        final FragmentManager fm = getParentFragmentManager();

        Button re_button = (Button) view.findViewById(R.id.d1_re_button);
        re_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Verify_Activity.result_java = '1';
                Verify_Activity.verButton.setText("完成辨識");
                File file = new File("/data/data/com.tencent.nanodetncnn/result.txt");
                file.delete();
                NcnnYolov5.varifyCheck(Verify_Activity.result_java);
                Verify_Activity.current_dialog = -1;
                Verify_Activity.dialog_change(Verify_Activity.current_dialog, Verify_Activity.origin_dialog, Verify_Activity.last_dialog, fm);
                dialog1.hide();
            }
        });
        Button next_button = (Button) view.findViewById(R.id.d1_next_button);
        next_button.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int click_check = 0;
                for (int i = 0; i < Verify_Activity.confirm_class_list.length; i++) {
                    if(Verify_Activity.class_list_checked[i].equals("1")) {
                        click_check = 1;
                        break;
                    }
                }
                if(click_check == 1) {
                    try {
                        Verify_Activity.fridge_index = 0;
                        JSONObject obj = null;
                        JSONArray table = null;
                        JSONObject data = null;
                        obj = new JSONObject(Verify_Activity.result);
                        table = obj.getJSONArray("food_dic_ver");
                        for (int i = 0; i < table.length(); i++) {
                            data = table.getJSONObject(i);
                            for (int j = 0; j < Verify_Activity.confirm_class_list.length; j++) {
                                if (data.getString("name").equals(Verify_Activity.confirm_class_list[j]) && Verify_Activity.class_list_checked[j].equals("1")) {
                                    Verify_Activity.fridge_did[Verify_Activity.fridge_index] = data.getString("did");
                                    Verify_Activity.fridge_name[Verify_Activity.fridge_index] = data.getString("name");
                                    Verify_Activity.fridge_position[Verify_Activity.fridge_index] = data.getString("position");
                                    Verify_Activity.fridge_expiredate[Verify_Activity.fridge_index] = LocalDate.now().plusDays(Integer.parseInt(data.getString("expireDay"))).toString();
                                    Verify_Activity.fridge_imgName[Verify_Activity.fridge_index] = data.getString("imgName");
                                    Verify_Activity.fridge_amount[Verify_Activity.fridge_index] = "1";
                                    Verify_Activity.fridge_memo[Verify_Activity.fridge_index] = "#";
                                    ++Verify_Activity.fridge_index;
                                }
                            }
                        }
                        Verify_Activity.old_fridge_index = Verify_Activity.fridge_index;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Verify_Activity.current_dialog = 2;
                }else{
                    Verify_Activity.current_dialog = 3;
                }
                dialog1.hide();
                Verify_Activity.dialog_change(Verify_Activity.current_dialog, Verify_Activity.origin_dialog, Verify_Activity.last_dialog, fm);
            }
        });
        return view;
    }
}
