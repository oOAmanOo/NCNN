package com.tencent.nanodetncnn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tencent.nanodetncnn.databinding.FragmentSearchBinding;


public class SearchFragment extends Fragment {

    FragmentSearchBinding binding;
    public SearchFragment() {
        // Required empty public constructor
    }
    EditText searchtext;
    ImageView searchbtn;
    public static String text = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_search, container, false);

        ImageView normalmode_image = (ImageView) v.findViewById(R.id.normal);
        ImageView managemode_image = (ImageView) v.findViewById(R.id.manage);
        ImageView fitnessmode_image = (ImageView) v.findViewById(R.id.fitness);
        ImageView relaxmode_image = (ImageView) v.findViewById(R.id.relax);
        searchtext = (EditText)v.findViewById(R.id.search_input);
        searchbtn = (ImageView) v.findViewById(R.id.searchbtn);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = null;
                text = searchtext.getText().toString().trim();
//                text = "咖哩";

                if(text.length() != 0){
                    MainActivity.allmode_loop = 1;
                    MainActivity.allmode_start = 1;
                    MainActivity.thread1_run();
                    MergeRecipeListFragment.getMode("search");
//                    replaceFragment(new MergeRecipeListFragment());
                    while(MainActivity.allmode_loop == 1){System.out.println(".");}
                    replaceFragment(new MergeRecipeListFragment());
                    MainActivity.allmode_start = 0;

                }
            }
        });


        normalmode_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.normal_loop = 1;
                MainActivity.normal_start = 1;
                MainActivity.thread1_run();
                MergeRecipeListFragment.getMode("normal");
//                replaceFragment(new MergeRecipeListFragment());
                while(MainActivity.normal_loop == 1){System.out.println(".");}
                replaceFragment(new MergeRecipeListFragment());
                MainActivity.normal_start = 0;

            }
        });
        managemode_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.manage_loop = 1;
                MainActivity.manage_start = 1;
                MainActivity.thread1_run();
                MergeRecipeListFragment.getMode("manage");
//                replaceFragment(new MergeRecipeListFragment());
                while(MainActivity.manage_loop == 1){System.out.println(".");}
                replaceFragment(new MergeRecipeListFragment());
                MainActivity.manage_start = 0;

            }
        });

        fitnessmode_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fitness_loop = 1;
                MainActivity.fitness_start = 1;
                MainActivity.thread1_run();
                MergeRecipeListFragment.getMode("fitness");
//                replaceFragment(new MergeRecipeListFragment());
                while(MainActivity.fitness_loop == 1){System.out.println(".");}
                replaceFragment(new MergeRecipeListFragment());
                MainActivity.fitness_start = 0;

            }
        });
        relaxmode_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.relax_loop = 1;
                MainActivity.relax_start = 1;
                MainActivity.thread1_run();
                MergeRecipeListFragment.getMode("relax");
//                replaceFragment(new MergeRecipeListFragment());
                while(MainActivity.relax_loop == 1){System.out.println(".");}
                replaceFragment(new MergeRecipeListFragment());
                MainActivity.relax_start = 0;

            }
        });


        return v;
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getFragmentManager();
        Bundle bundle=new Bundle();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


}