package com.tencent.nanodetncnn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.tencent.nanodetncnn.login.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //試做
    private View view;
    private Context context;
    public static JSONObject obj = null;
    public static JSONArray table = null;
    public static JSONObject data;

    //結束

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String pmode,pname,alerttime,recipetime,modename,scount;
    TextView mode,name,alertTime,recipeTime;


    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=getActivity();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View view;

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        Button btn=(Button) view.findViewById(R.id.button);
        Button btn2=(Button) view.findViewById(R.id.button2);
        mode=view.findViewById(R.id.mode);
        name=view.findViewById(R.id.name);
        alertTime=view.findViewById(R.id.alertTime);
        recipeTime=view.findViewById(R.id.recipeTime);


        if(getArguments()!=null){
            scount=getArguments().getString("data_uid");//uid
            updateUserData(data.toString());
        }

        //登出
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());//只需要context
                alert.setMessage("確定要登出？");
                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "已登出", Toast.LENGTH_SHORT).show();//new
                        Intent intent=new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("cancel", null);
                alert.show();
                Log.v("test","getActivity() "+getActivity());
            }
        });

        //編輯
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mix=scount+";"+pname+";"+recipeTime.getText().toString() + ";" + alertTime.getText().toString();
                Bundle bundle=new Bundle();
                bundle.putString("key",mix);
                EditFragment fragment=new EditFragment();
                //starteditFragment fragment=new starteditFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
            }
        });

        return view;
    }

    private void updateUserData(String result) {
        try {
            pmode = data.getString("mid");
            pname= data.getString("name");
            alerttime = data.getString("alertTime");
            recipetime = data.getString("recipeTime");
            modename=data.getString("mname");
            name.setText(pname);
            if(pmode.equals("1")||pmode.equals("2")||pmode.equals("3")||pmode.equals("4")){
                mode.setText(modename);
            }else {
                mode.setText("客製化");
            }


            String[] split = alerttime.split(":");
            String atime=split[0]+":"+split[1];
            alertTime.setText(atime);
            String[] split1 = recipetime.split(":");
            String rtime=split1[0]+":"+split1[1];
            recipeTime.setText(rtime);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void user(String result,String uid){
        String id;

        try {
            obj = new JSONObject(result);
            table = obj.getJSONArray("user");
            for (int i =0; i< table.length();++i){
                data = table.getJSONObject(i);
                id = data.getString("uid");
                if(id.equals(uid)){
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}




//原本在編輯裡的code
    //要先判斷帳號密碼是否正確
//    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());//只需要context
//                alert.setMessage("確定更改？");
//                        alert.setMessage("名稱："+personname+"\n飲食條件：");
//                        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
////之後確定編輯的程式碼寫這邊
//        //要先判斷帳號密碼是否正確
//        if(personname.getText().toString().equals("ad")&&password.getText().toString().equals("adm")){
//        //登入成功的code
//        replaceFragment(new EditFragment());
//        }else{
//        //登入失敗，回到profile重新輸入
//        Toast.makeText(getActivity(), "帳號密碼輸入錯誤", Toast.LENGTH_SHORT).show();//new
//        //AlertDialog.Builder alert1 = new AlertDialog.Builder(getContext());//只需要context
//        //alert1.setMessage("帳號密碼輸入錯誤！");
//        //alert1.setNeutralButton("ok",null);
//        //alert1.show();
//        //replaceFragment(new ProfileFragment());
//        }


//        }
//        });
////alert.setNegativeButton("cancel", null);
////alert.show();