package com.tencent.nanodetncnn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cust1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cust1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public cust1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cust1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static cust1Fragment newInstance(String param1, String param2) {
        cust1Fragment fragment = new cust1Fragment();
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
        View view = inflater.inflate(R.layout.fragment_cust1, container, false);

        Button btnback=(Button) view.findViewById(R.id.btnback);
        CheckBox cb1=(CheckBox) view.findViewById(R.id.cb1);
        CheckBox cb2=(CheckBox) view.findViewById(R.id.cb2);
        CheckBox cb3=(CheckBox) view.findViewById(R.id.cb3);
        CheckBox cb4=(CheckBox) view.findViewById(R.id.cb4);



        //fragment傳data
        Button btntest=(Button) view.findViewById(R.id.btntest);
        btntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                String msg ="";
                if (cb1.isChecked()){
                    msg+="\n"+cb1.getText().toString();
                }
                if (cb2.isChecked()){
                    msg+="\n"+cb2.getText().toString();
                }
                if (cb3.isChecked()){
                    msg+="\n"+cb3.getText().toString();
                }
                if (cb4.isChecked()){
                    msg+="\n"+cb4.getText().toString();
                }
                bundle.putString("key",msg);
                //別的變數型態傳送
                //bundle.putBoolean("key1",true);
                //bundle.putFloat("float",3.44f);
                cust2Fragment fragment=new cust2Fragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
            }
        });//fragment傳data,end

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new EditFragment());
            }
        });

        return view;
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


}