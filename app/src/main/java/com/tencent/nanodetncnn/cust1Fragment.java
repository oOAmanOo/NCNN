package com.tencent.nanodetncnn;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class cust1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String result,sugar,salt,oil;
    public static String userid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cust1, container, false);

        Spinner sugarsp=(Spinner) view.findViewById(R.id.sugarsp);
        Spinner saltsp=(Spinner) view.findViewById(R.id.saltsp);
        Spinner oilsp=(Spinner) view.findViewById(R.id.oilsp);

        sugarsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sugar = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        saltsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                salt = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        oilsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                oil = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //fragment傳data
        Button btntest=(Button) view.findViewById(R.id.btntest);
        //跳到下一頁cust2
        btntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result=userid+";"+sugar+"糖/"+salt+"鹽/"+oil+"油";
                Bundle bundl=new Bundle();
                bundl.putString("key",result);
                cust22222Fragment fragment=new cust22222Fragment();
                fragment.setArguments(bundl);
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
            }
        });
        return view;
    }
    public static void user(String uid){
        userid=uid;
    }
}