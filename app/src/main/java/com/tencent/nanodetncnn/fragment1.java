package com.tencent.nanodetncnn;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import recycler.ListAdapter;

public class fragment1 extends DialogFragment {

//    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        ListAdapter listAdapter = new ListAdapter(this.getActivity(), MainActivity.confirm_class_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(listAdapter);

        this.getDialog().setTitle(Html.fromHtml("<font color='#00455F'>請選擇欲添加至冰箱的食物"));

        Button re_button = (Button) view.findViewById(R.id.re_button);
        re_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity.result_java = '1';
                NcnnYolov5.varifyCheck(MainActivity.result_java);
                dismiss();
            }
        });
        Button next_button = (Button) view.findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
