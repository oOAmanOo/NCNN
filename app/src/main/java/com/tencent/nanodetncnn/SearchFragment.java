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


                if(text.length() != 0){
                    for(int i = 0; i<2; i ++){
                        AllRecipeList.search(text);
                    }
                    MergeRecipeListFragment.getMode("search");
                    replaceFragment(new MergeRecipeListFragment());
                    text = null;

                }



            }
        });


        normalmode_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MergeRecipeListFragment.getMode("normal");
                replaceFragment(new MergeRecipeListFragment());

            }
        });
        managemode_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MergeRecipeListFragment.getMode("manage");
                replaceFragment(new MergeRecipeListFragment());


            }
        });

        fitnessmode_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MergeRecipeListFragment.getMode("fitness");
                replaceFragment(new MergeRecipeListFragment());


            }
        });
        relaxmode_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MergeRecipeListFragment.getMode("relax");
                replaceFragment(new MergeRecipeListFragment());

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