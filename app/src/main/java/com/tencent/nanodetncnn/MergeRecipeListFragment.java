package com.tencent.nanodetncnn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.fridge.MyFridgeFragment;

import java.util.ArrayList;


public class MergeRecipeListFragment extends Fragment implements View.OnTouchListener,RecyclerViewInterface{

    public static String currentMode;
    public static String currentName;
    public static void getMode(String mode){
        currentMode = mode;
    }
    public static void getSearch(String name){ currentName = name; }
    public static TextView noSearchContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        RecyclerView recyclerView;
        ArrayList Recipeimages;
        ArrayList Recipenames;
        ArrayList Recipefood;
        ArrayList Recipesugar;
        ArrayList Recipesalt;
        ArrayList Recipeoil;
        TextView modeText;
//        ArrayList Recipesteps;
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_merge_recipe_list,container,false);
        view.setOnTouchListener(this);
        recyclerView = view.findViewById(R.id.merge_recyclerview);
        modeText = view.findViewById(R.id.mergeTitle);
        noSearchContent = view.findViewById(R.id.noSearchContent);


        Recipeimages = new ArrayList();
        Recipenames = new ArrayList();
        Recipefood = new ArrayList();
        Recipesugar = new ArrayList();
        Recipesalt = new ArrayList();
        Recipeoil = new ArrayList();

        ImageView backbtn = (ImageView) view.findViewById(R.id.merge_backstack_button);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentMode == "autosearch"){
                    replaceFragment(new MyFridgeFragment());
                }else{
                    replaceFragment(new SearchFragment());
                }
                backbtn.setVisibility(View.INVISIBLE);
            }
        });

        if(currentMode == "normal"){
            modeText.setText("一般模式");

            for( int i = 0; i < NormalRecipeList.recipeindex; i++) {

                Recipeimages.add(NormalRecipeList.imgName);
                Recipenames.add(NormalRecipeList.allRecipeNames);
                Recipefood.add(NormalRecipeList.allRecipeFood);
                Recipesugar.add(NormalRecipeList.allRecipeSugar);
                Recipesalt.add(NormalRecipeList.allRecipeSalt);
                Recipeoil.add(NormalRecipeList.allRecipeOil);
            }
            MergeListAdapter listAdapter = new MergeListAdapter(getContext(), Recipeimages, Recipenames,Recipefood,Recipesugar,Recipesalt,Recipeoil,this,currentMode);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(listAdapter);
            MergeDetailFragment.getContext(getContext());
        }
        else if(currentMode == "fitness"){
            modeText.setText("健身模式");

            for( int i = 0; i < FitnessRecipeList.recipeindex; i++) {
                Recipeimages.add(FitnessRecipeList.imgName);
                Recipenames.add(FitnessRecipeList.allRecipeNames);
                Recipefood.add(FitnessRecipeList.allRecipeFood);
                Recipesugar.add(FitnessRecipeList.allRecipeSugar);
                Recipesalt.add(FitnessRecipeList.allRecipeSalt);
                Recipeoil.add(FitnessRecipeList.allRecipeOil);
            }
            MergeListAdapter listAdapter = new MergeListAdapter(getContext(), Recipeimages, Recipenames,Recipefood,Recipesugar,Recipesalt,Recipeoil,this,currentMode);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(listAdapter);
            MergeDetailFragment.getContext(getContext());
        }
        else if(currentMode == "manage"){
            modeText.setText("管理模式");

            for( int i = 0; i < ManageRecipeList.recipeindex; i++) {
                Recipeimages.add(ManageRecipeList.imgName);
                Recipenames.add(ManageRecipeList.allRecipeNames);
                Recipefood.add(ManageRecipeList.allRecipeFood);
                Recipesugar.add(ManageRecipeList.allRecipeSugar);
                Recipesalt.add(ManageRecipeList.allRecipeSalt);
                Recipeoil.add(ManageRecipeList.allRecipeOil);
            }
            MergeListAdapter listAdapter = new MergeListAdapter(getContext(), Recipeimages, Recipenames,Recipefood,Recipesugar,Recipesalt,Recipeoil,this,currentMode);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(listAdapter);
            MergeDetailFragment.getContext(getContext());
        }
        else if(currentMode == "relax"){
            modeText.setText("頹廢模式");

            for( int i = 0; i < RelaxRecipeList.recipeindex; i++) {
                Recipeimages.add(RelaxRecipeList.imgName);
                Recipenames.add(RelaxRecipeList.allRecipeNames);
                Recipefood.add(RelaxRecipeList.allRecipeFood);
                Recipesugar.add(RelaxRecipeList.allRecipeSugar);
                Recipesalt.add(RelaxRecipeList.allRecipeSalt);
                Recipeoil.add(RelaxRecipeList.allRecipeOil);
            }
            MergeListAdapter listAdapter = new MergeListAdapter(getContext(), Recipeimages, Recipenames,Recipefood,Recipesugar,Recipesalt,Recipeoil,this,currentMode);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(listAdapter);
            MergeDetailFragment.getContext(getContext());
        }
        else if(currentMode == "search"){
            modeText.setText(AllRecipeList.searchtext2);

            if(AllRecipeList.recipeindex == 0){
                noSearchContent.setVisibility(View.VISIBLE);
            }
            else{
                for( int i = 0; i < AllRecipeList.recipeindex; i++) {
                    Recipeimages.add(AllRecipeList.imgName);
                    Recipenames.add(AllRecipeList.allRecipeNames);
                    Recipefood.add(AllRecipeList.allRecipeFood);
                    Recipesugar.add(AllRecipeList.allRecipeSugar);
                    Recipesalt.add(AllRecipeList.allRecipeSalt);
                    Recipeoil.add(AllRecipeList.allRecipeOil);
                }
                MergeListAdapter listAdapter = new MergeListAdapter(getContext(), Recipeimages, Recipenames,Recipefood,Recipesugar,Recipesalt,Recipeoil,this,currentMode);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(listAdapter);
                MergeDetailFragment.getContext(getContext());
            }
        }
        else if(currentMode == "autosearch") {
            modeText.setText(currentName);

            if (AutoRecipeList.count == 0) {
                noSearchContent.setVisibility(View.VISIBLE);
            } else {
                for (int i = 0; i < AutoRecipeList.count; i++) {
                    Recipeimages.add(AutoRecipeList.imgName);
                    Recipenames.add(AutoRecipeList.allRecipeNames);
                    Recipefood.add(AutoRecipeList.allRecipeFood);
                    Recipesugar.add(AutoRecipeList.allRecipeSugar);
                    Recipesalt.add(AutoRecipeList.allRecipeSalt);
                    Recipeoil.add(AutoRecipeList.allRecipeOil);
                }
                MergeListAdapter listAdapter = new MergeListAdapter(getContext(), Recipeimages, Recipenames, Recipefood, Recipesugar, Recipesalt, Recipeoil, this, currentMode);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(listAdapter);
                MergeDetailFragment.getContext(getContext());
            }
        }

        return view;
    }
    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getFragmentManager();
        Bundle bundle=new Bundle();
//        bundle.putString("data",scount);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onItemClick(int position) {

        replaceFragment(new MergeDetailFragment());

    }
}