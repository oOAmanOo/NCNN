package com.tencent.nanodetncnn;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link editmodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class editmodeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView mode, name, alertTime, recipeTime;
    String result,id,pname,salerttime,srecipetime,modename;

    public editmodeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment editmodeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static editmodeFragment newInstance(String param1, String param2) {
        editmodeFragment fragment = new editmodeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_editmode, container, false);

        Button btn2 = (Button) view.findViewById(R.id.button2);
        mode = view.findViewById(R.id.mode);
        name = view.findViewById(R.id.name);
        alertTime = view.findViewById(R.id.alertTime);
        recipeTime = view.findViewById(R.id.recipeTime);

        Bundle bundle=this.getArguments();
        result=bundle.getString("key");//userID;user name;recipetime;alerttime;mode name
        String[] split = result.split(";");
        id = split[0];//uid
        pname=split[1];//user name
        srecipetime = split[2];
        salerttime = split[3];
        modename=split[4];

        name.setText(pname);
        mode.setText(modename);
        alertTime.setText(salerttime);
        recipeTime.setText(srecipetime);


        //繼續編輯模式
        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mix = id +";"+pname+";"+recipeTime.getText().toString() + ";" + alertTime.getText().toString();//userID;user name;recipetime;alerttime
                Bundle bundle = new Bundle();
                bundle.putString("key", mix);
                EditFragment fragment = new EditFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
            }
        });

        //設定食譜時間
        recipeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        if(hour<10){
                            if (minute == 0) {
                                recipeTime.setText("0"+hour + ":" + minute + "0");
                            } else if (minute < 10) {
                                recipeTime.setText("0"+hour + ":" + "0" + minute);
                            } else {
                                recipeTime.setText("0"+hour + ":" + minute);
                            }
                        }else {
                            if (minute == 0) {
                                recipeTime.setText(hour + ":" + minute + "0");
                            } else if (minute < 10) {
                                recipeTime.setText(hour + ":" + "0" + minute);
                            } else {
                                recipeTime.setText(hour + ":" + minute);
                            }
                        }
                    }
                }, hour, minute, true).show();

            }
        });

        //設定提醒時間
        alertTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        if(hour<10){
                            if (minute == 0) {
                                alertTime.setText("0"+hour + ":" + minute + "0");
                            } else if (minute < 10) {
                                alertTime.setText("0"+hour + ":" + "0" + minute);
                            } else {
                                alertTime.setText("0"+hour + ":" + minute);
                            }
                        }else {
                            if (minute == 0) {
                                alertTime.setText(hour + ":" + minute + "0");
                            } else if (minute < 10) {
                                alertTime.setText(hour + ":" + "0" + minute);
                            } else {
                                alertTime.setText(hour + ":" + minute);
                            }
                        }
                    }
                }, hour, minute, true).show();

            }
        });

        //傳資料庫
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pass to settimedb
                String mix = id + ";" +pname+";"+ recipeTime.getText().toString() + ";" + alertTime.getText().toString()+";"+modename;
                Intent intent = new Intent(getActivity(), passvalueActivity.class);
                Bundle bundlle1 = new Bundle();
                bundlle1.putString("result", mix); //放入所需要傳遞的值
                intent.putExtras(bundlle1);
                getActivity().startActivity(intent); //這里一定要獲取到所在Activity再startActivity()；
            }
        });


        return view;
    }
}