package com.tencent.nanodetncnn;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cust22222Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cust22222Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static JSONObject obj = null;
    public static JSONArray table = null;
    public static JSONObject data=null;

    public static String result,uid;
    public static String foodhateid[]=new String[200];
    public static String foodhatename[]=new String[200];
    public static int hatefood_index=0;

    TextView food;
    String bb;
    StringBuilder stringbuilder=new StringBuilder();
    //new end

    public cust22222Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cust22222Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static cust22222Fragment newInstance(String param1, String param2) {
        cust22222Fragment fragment = new cust22222Fragment();
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
        View view= inflater.inflate(R.layout.fragment_cust22222, container, false);

        Button btnnext=(Button) view.findViewById(R.id.btnnext);
        Button btnback=(Button) view.findViewById(R.id.btnback);
        EditText add=(EditText) view.findViewById(R.id.add);
        Button btnadd=(Button) view.findViewById(R.id.btnaddsp);

        food=(TextView) view.findViewById(R.id.food);
//        a=(TextView) view.findViewById(R.id.a);

        Bundle bundle=this.getArguments();
        bb=bundle.getString("key");//userid;sugar糖/salt鹽/oil油
        stringbuilder.append("無");


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cust1Fragment fragment=new cust1Fragment();
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ccc=stringbuilder.toString();
                bb=bb+";"+ccc;
                Bundle bundl=new Bundle();
                bundl.putString("key",bb);
                cust3Fragment fragment=new cust3Fragment();
                fragment.setArguments(bundl);
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //沒輸入或是輸入重複
                int k=0;
                if(add.getText().toString().length() != 0){
                    for (int i = 0; i < hatefood_index; i++) {
                        if(add.getText().toString().equals(foodhatename[i])){
                            dialoge();//判斷新增的食材是否已存在列表中
                            add.setText("");
                            break;
                        }
                        k++;
                    }
                }else {
                    dialogw();//判斷新增的食材是否為空值
                }
                //成功的話跑addlist()
                if (k==hatefood_index){
                    foodhatename[hatefood_index]=add.getText().toString();
                    hatefood_index++;
                    addlist(foodhatename,hatefood_index);
                    add.setText("");
                }

            }
        });

        //updateUserData(dat.toString());


        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                System.out.println(222);
                addlist(foodhatename,hatefood_index);
            }
        });



        return view;
    }

    private void addlist(String foodhatename[], int hatefood_index) {
        String[] foodarray= new String[hatefood_index];
        //kkkkk
        String[] aa=new String[hatefood_index];
        for (int i = 0; i < hatefood_index; i++) {
            aa[i]="0";
        }
        //

        //System.out.println(111);
        for (int i = 0; i < hatefood_index; i++) {
            foodarray[i]=foodhatename[i];
        }
        boolean[] selectedfood;
        LinkedList<Integer> foodlist=new LinkedList<>();
        selectedfood =new boolean[foodarray.length];

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("選擇不喜歡食物");
        builder.setCancelable(false);
        builder.setMultiChoiceItems(foodarray, selectedfood, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                if(b){
//                    foodlist.add(i);
//                    Collections.sort(foodlist);
                    aa[i]="1";

                }else {
                    aa[i]="0";
                    //foodlist.remove(i);
                }
            }
        });

        builder.setPositiveButton("完成", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StringBuilder stringbuilder1=new StringBuilder();
                stringbuilder=stringbuilder1;
                //kkkk
                int k=0;
                for (int j = 0; j < hatefood_index; j++) {
                    if(aa[j].equals("1")){
                        stringbuilder.append(foodarray[j]);
                        stringbuilder.append(",");
                        k=1;
                    }
                }
                if(k==0){
                    stringbuilder.append("無");
                }else{
                    stringbuilder.deleteCharAt(stringbuilder.length()-1);
                }
//                for (int j=0;j<foodlist.size();j++){
//                    stringbuilder.append(foodarray[foodlist.get(j)]);
//                    if(j!=foodlist.size()-1){
//                        stringbuilder.append(",");
//                    }
//
//                }
//                if(foodlist.size()==0){
//                    stringbuilder.append("無");
//                }
                food.setText(stringbuilder.toString());
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

//        builder.setNeutralButton("清空", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                for (int j = 0; j < selectedfood.length; j++) {
//                    selectedfood[j] = false;
//                    foodlist.clear();
//                    food.setText("");
//                }
//            }
//        });
        builder.show();

    }

    public static void hate(String result,String uid){
//        String id;
//        JSONObject data_json=null;

        try {
            obj = new JSONObject(result);
            table=obj.getJSONArray("food_hate");
            hatefood_index=0;

            for (int i =0; i< table.length();i++){
                data=table.getJSONObject(i);
                foodhateid[i]=data.getString("did");
                foodhatename[i]=data.getString("name");
                ++hatefood_index;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void dialogw() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());//只需要context
        builder.setMessage("請輸入不喜歡食材"); //提示訊息
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    private void dialoge() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());//只需要context
        builder.setMessage("食材列表已有該食材"); //提示訊息
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}