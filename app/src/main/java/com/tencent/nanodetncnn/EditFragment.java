package com.tencent.nanodetncnn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public EditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
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
        View view=inflater.inflate(R.layout.fragment_edit, container, false);
        RadioGroup radioGroup=(RadioGroup) view.findViewById(R.id.radioGroup);
        Button btnpost=(Button) view.findViewById(R.id.btnpost);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton r=(RadioButton) view.findViewById(i);
                String rb=r.getText().toString();
                if(r.getText().equals("客製化")){
                    //讓客製化選單跑出來
                    //cb1.setEnabled(true);
                    //cb2.setEnabled(true);
                    //cb3.setEnabled(true);
                    //cb4.setEnabled(true);
                    btnpost.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder alert = new AlertDialog.Builder(getContext(),R.style.AlertDialog_background);//只需要context
                            alert.setMessage("確定更改?\n"+rb);
                            alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    replaceFragment(new cust1Fragment());
                                }
                            });
                            alert.setNegativeButton("cancel", null);
                            alert.show();
                        }
                    });

                    //replaceFragment(new cust1Fragment());
                }else {
                    //cb1.setEnabled(false);
                    //cb2.setEnabled(false);
                    //cb3.setEnabled(false);
                    //cb4.setEnabled(false);
                    //btnpost.setVisibility(view.VISIBLE);
                    //Toast.makeText(getActivity(), rb, Toast.LENGTH_SHORT).show();//new
                    btnpost.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder alert = new AlertDialog.Builder(getContext(),R.style.AlertDialog_background);//只需要context
                            alert.setMessage("確定更改?\n"+rb);
                            alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    replaceFragment(new MyFridgeFragment());
                                }
                            });
                            alert.setNegativeButton("cancel", null);
                            alert.show();
                        }
                    });

                }


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