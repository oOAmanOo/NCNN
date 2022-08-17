package com.tencent.nanodetncnn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import recycler.ListAdapter;

public class fragment1 extends DialogFragment {

//    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {


//        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment1_layout,container,false);
        View view = inflater.inflate(R.layout.fragment1_layout, container);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        ListAdapter listAdapter = new ListAdapter(this.getActivity(), MainActivity.confirm_class_list);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(listAdapter);

        this.getDialog().setTitle("Confirm Ingredients");

//        this.getDialog()(getActivity())

//        this.getDialog() =new AlertDialog.Builder(getActivity())
//                .setPositiveButton("Next", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        System.out.println("NEXT");
//                    }
//                })
//                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface arg0, int arg1) {
//
//                    }
//                });




        return view;
    }
}
