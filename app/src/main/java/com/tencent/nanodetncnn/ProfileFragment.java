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
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tencent.nanodetncnn.login.LoginActivity;

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

    //結束

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
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
        EditText personname=(EditText) view.findViewById(R.id.editTextTextPersonName2);

        EditText password=(EditText) view.findViewById(R.id.editTextTextPassword2);
        //Toast.makeText(getActivity(), "請先登入帳號", Toast.LENGTH_SHORT).show();//new
        AlertDialog.Builder alert2 = new AlertDialog.Builder(getContext());//只需要context
        alert2.setMessage("編輯飲食條件請先登入");
        alert2.setPositiveButton("ok", null);
        alert2.show();





        //    btn2.setOnClickListener(new View.OnClickListener() {
        //        @Override
        //        public void onClick(View v) {
        //            Log.v("test","getActivity() "+getActivity());
        //            //Toast.makeText(getActivity(), "已登出", Toast.LENGTH_SHORT).show();//new
        //            Toast.makeText(getContext(), "已登出", Toast.LENGTH_SHORT).show();//new
        //            replaceFragment(new MyFridgeFragment());
        //Intent intent=new Intent(getActivity(),MainActivity.class);
        //startActivity(intent);
        //getActivity().finish();
        //       }
        //   });


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
                //Toast.makeText(getActivity(), "已登出", Toast.LENGTH_SHORT).show();//new
            }
        });

        //編輯
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (personname.getText().toString().equals("ad")&&password.getText().toString().equals("adm")){
                    Toast.makeText(getActivity(), "登入成功", Toast.LENGTH_SHORT).show();//new
                    replaceFragment(new EditFragment());
                }else {
                    Toast.makeText(getActivity(), "帳號密碼輸入錯誤", Toast.LENGTH_SHORT).show();//new
                    replaceFragment(new EditFragment());
                }

            }
        });

        return view;
    }//public View onCreateView整串改過


    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }






}

//public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile, container, false);
//
//    }


//    @Override
//    public void onActivityCreated(Bundle savedInstanceState){
//        super.onActivityCreated(savedInstanceState);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "aaaaaaa", Toast.LENGTH_SHORT).show();

//            }
//        });

//    }



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