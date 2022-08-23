package com.tencent.nanodetncnn;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cust2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cust2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public cust2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cust2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static cust2Fragment newInstance(String param1, String param2) {
        cust2Fragment fragment = new cust2Fragment();
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
        View view= inflater.inflate(R.layout.fragment_cust2, container, false);

        Button btnnext=(Button) view.findViewById(R.id.btnnext);
        Button btndel=(Button) view.findViewById(R.id.btndel);
        TextView display=(TextView) view.findViewById(R.id.display);


        //動態spinner
        Spinner sptest=(Spinner) view.findViewById(R.id.sptest);
        EditText add=(EditText) view.findViewById(R.id.add);
        Button btnaddsp=(Button) view.findViewById(R.id.btnaddsp);


        List list = new ArrayList();

        list.add("洋蔥");

        list.add("香蕉");

        list.add("橘子");

        list.add("香蕉");
        ArrayAdapter adapter=new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,list);

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);

        sptest.setAdapter(adapter);

        btnaddsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=add.getText().toString();
                list.add(str);
                Toast.makeText(getActivity(), "新增成功", Toast.LENGTH_SHORT).show();//new
                add.setText("");

            }
        });

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=add.getText().toString();
                list.remove(str);
                Toast.makeText(getActivity(), "刪除成功", Toast.LENGTH_SHORT).show();//new
                add.setText("");
            }
        });
        Bundle bundle=this.getArguments();
        sptest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String string=(String) sptest.getSelectedItem();

                String data=bundle.getString("key");
                //String mix=(String) "食材："+string+"\n模式:"+data;
                //String mix=(String) "不喜歡食材："+string+"\n飲食習慣:"+data;
                String mix=(String) string+"\n"+data;

                bundle.putString("key",mix);

                btnnext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //fragment傳data
                        //EditText display=(EditText) view.findViewById(R.id.display);

                        //別的變數型態傳送
                        //bundle.putBoolean("key1",true);
                        //bundle.putFloat("float",3.44f);
                        cust3Fragment fragment=new cust3Fragment();
                        fragment.setArguments(bundle);
                        getFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();

                        //display.setText(data);
                        //fragment傳data,end

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






        //end

        //spinner複選
        TextView food;
        boolean[] selectedfood;
        ArrayList<Integer> foodlist=new ArrayList<>();
        String[] foodarray={"洋蔥","蛋","糖","茶","茄子","t"};

        food=(TextView) view.findViewById(R.id.food);
        selectedfood =new boolean[foodarray.length];

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(),R.style.AlertDialog_background);
                builder.setTitle("select food");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(foodarray, selectedfood, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            foodlist.add(i);
                            Collections.sort(foodlist);
                        }else {
                            foodlist.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringbuilder=new StringBuilder();
                        for (int j=0;j<foodlist.size();j++){
                            stringbuilder.append(foodarray[foodlist.get(j)]);
                            if(j!=foodlist.size()-1){
                                stringbuilder.append(", ");
                            }

                        }
                        food.setText(stringbuilder.toString());
                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setNeutralButton("clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j=0;j<selectedfood.length;j++){
                            selectedfood[j]=false;
                            foodlist.clear();
                            food.setText("");
                        }
                    }
                });

                builder.show();
            }
        });
        //


        return view;
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}