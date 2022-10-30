package com.tencent.nanodetncnn;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class MergeDetailFragment extends Fragment implements View.OnTouchListener{


    public static Context context;
    public static String[] listItem;
    public static String[] listDId;
    public static String[] listImg;
    public static String[] HistoryfoodName;
    public static String[] HistoryfoodDid;
    public static boolean[] AllfoodDid;
    public static boolean[] checkedItem;
    ArrayList<Integer> mUserItem = new ArrayList<>();

    public MergeDetailFragment() {
        // Required empty public constructor
    }

    public static int position_now;
    public static String currentMode;

    public static void postion(int position){
        position_now = position;
    }

    public static void getmode(String mode){
        currentMode = mode;
    }

    public static void getContext(Context thiscontext){

        context = thiscontext;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_merge_detail,container,false);
        view.setOnTouchListener(this);



        ImageView backbtn = (ImageView) view.findViewById(R.id.backstack_button_merge_detail);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new MergeRecipeListFragment());
                backbtn.setVisibility(View.INVISIBLE);
            }
        });

        TextView merge_recipe_name_detail = view.findViewById(R.id.merge_recipe_name_detail);
        TextView merge_recipe_tag_detail = view.findViewById(R.id.merge_recipe_tag_detail);
        TextView merge_recipe_food_detail = view.findViewById(R.id.merge_recipe_food_detail);
        TextView merge_recipe_step_detail = view.findViewById(R.id.merge_recipe_step_detail);
        ImageView merge_recipe_img_detail = view.findViewById(R.id.merge_recipe_img_detail);
        Button evaluate_btn = view.findViewById(R.id.evaluatebtn);

        listItem = null;
        listDId = null;
        listImg = null;
        HistoryfoodName = null;
        HistoryfoodDid = null;

        if(currentMode == "normal"){
            merge_recipe_name_detail.setText(NormalRecipeList.allRecipeNames[position_now]);
            merge_recipe_food_detail.setText(NormalRecipeList.allRecipeFood[position_now]);
            merge_recipe_tag_detail.setText(NormalRecipeList.allRecipeSugar[position_now]+"/"+NormalRecipeList.allRecipeSalt[position_now]+"/"+NormalRecipeList.allRecipeOil[position_now]);
            merge_recipe_step_detail.setText(NormalRecipeList.allRecipeSteps[position_now]);
            merge_recipe_img_detail.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(NormalRecipeList.imgName[position_now]),"drawable", context.getPackageName()));


            listItem = NormalRecipeList.allRecipeFood[position_now].split(",");
            listDId = NormalRecipeList.allRecipeDid[position_now].split(",");
            listImg = NormalRecipeList.allRecipeFoodImg[position_now].split(",");
            HistoryfoodName = NormalRecipeList.allfoodhistoryName;
            HistoryfoodDid = NormalRecipeList.allfoodhistoryDid;


            checkedItem = new boolean[listItem.length];
            AllfoodDid = new boolean[listItem.length];

            for(int i = 0; i < NormalRecipeList.allfoodhistoryName.length; ++i ){
                for (int j = 0; j < listItem.length; j++) {
                    if(Objects.equals(listItem[j], NormalRecipeList.allfoodhistoryName[i])){
                        AllfoodDid[j] = true;
                        break;
                    }
                }
            }
            for (int i = 0; i < AllfoodDid.length; i++) {
                if(AllfoodDid[i] != true){
                    AllfoodDid[i] = false;
                }
            }
        }

        else if(currentMode == "manage"){
            merge_recipe_name_detail.setText(ManageRecipeList.allRecipeNames[position_now]);
            merge_recipe_food_detail.setText(ManageRecipeList.allRecipeFood[position_now]);
            merge_recipe_tag_detail.setText(ManageRecipeList.allRecipeSugar[position_now]+"/"+ManageRecipeList.allRecipeSalt[position_now]+"/"+ManageRecipeList.allRecipeOil[position_now]);
            merge_recipe_step_detail.setText(ManageRecipeList.allRecipeSteps[position_now]);
            merge_recipe_img_detail.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(ManageRecipeList.imgName[position_now]),"drawable", context.getPackageName()));


            listItem = ManageRecipeList.allRecipeFood[position_now].split(",");
            listDId = ManageRecipeList.allRecipeDid[position_now].split(",");
            listImg = ManageRecipeList.allRecipeFoodImg[position_now].split(",");
            HistoryfoodName = ManageRecipeList.allfoodhistoryName;
            HistoryfoodDid = ManageRecipeList.allfoodhistoryDid;


            checkedItem = new boolean[listItem.length];
            AllfoodDid = new boolean[listItem.length];
            int num = 0;

            for(int i = 0; i < ManageRecipeList.allfoodhistoryName.length; ++i ){
                for (int j = 0; j < listItem.length; j++) {
                    if(Objects.equals(listItem[j], ManageRecipeList.allfoodhistoryName[i])){
                        AllfoodDid[j] = true;

                        break;
                    }
                }
            }
            for (int i = 0; i < AllfoodDid.length; i++) {
                if(AllfoodDid[i] != true){
                    AllfoodDid[i] = false;
                }
            }
        }

        else if(currentMode == "fitness"){
            merge_recipe_name_detail.setText(FitnessRecipeList.allRecipeNames[position_now]);
            merge_recipe_food_detail.setText(FitnessRecipeList.allRecipeFood[position_now]);
            merge_recipe_tag_detail.setText(FitnessRecipeList.allRecipeSugar[position_now]+"/"+FitnessRecipeList.allRecipeSalt[position_now]+"/"+FitnessRecipeList.allRecipeOil[position_now]);
            merge_recipe_step_detail.setText(FitnessRecipeList.allRecipeSteps[position_now]);
            merge_recipe_img_detail.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(FitnessRecipeList.imgName[position_now]),"drawable", context.getPackageName()));


            listItem = FitnessRecipeList.allRecipeFood[position_now].split(",");
            listDId = FitnessRecipeList.allRecipeDid[position_now].split(",");
            listImg = FitnessRecipeList.allRecipeFoodImg[position_now].split(",");
            HistoryfoodName = FitnessRecipeList.allfoodhistoryName;
            HistoryfoodDid = FitnessRecipeList.allfoodhistoryDid;


            checkedItem = new boolean[listItem.length];
            AllfoodDid = new boolean[listItem.length];
            int num = 0;

            for(int i = 0; i < FitnessRecipeList.allfoodhistoryName.length; ++i ){
                for (int j = 0; j < listItem.length; j++) {
                    if(Objects.equals(listItem[j], FitnessRecipeList.allfoodhistoryName[i])){
                        AllfoodDid[j] = true;

                        break;
                    }
                }
            }
            for (int i = 0; i < AllfoodDid.length; i++) {
                if(AllfoodDid[i] != true){
                    AllfoodDid[i] = false;
                }
            }
        }

        else if(currentMode == "relax"){
            merge_recipe_name_detail.setText(RelaxRecipeList.allRecipeNames[position_now]);
            merge_recipe_food_detail.setText(RelaxRecipeList.allRecipeFood[position_now]);
            merge_recipe_tag_detail.setText(RelaxRecipeList.allRecipeSugar[position_now]+"/"+RelaxRecipeList.allRecipeSalt[position_now]+"/"+RelaxRecipeList.allRecipeOil[position_now]);
            merge_recipe_step_detail.setText(RelaxRecipeList.allRecipeSteps[position_now]);
            merge_recipe_img_detail.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(RelaxRecipeList.imgName[position_now]),"drawable", context.getPackageName()));


            listItem = RelaxRecipeList.allRecipeFood[position_now].split(",");
            listDId = RelaxRecipeList.allRecipeDid[position_now].split(",");
            listImg = RelaxRecipeList.allRecipeFoodImg[position_now].split(",");
            HistoryfoodName = RelaxRecipeList.allfoodhistoryName;
            HistoryfoodDid = RelaxRecipeList.allfoodhistoryDid;


            checkedItem = new boolean[listItem.length];
            AllfoodDid = new boolean[listItem.length];
            int num = 0;

            for(int i = 0; i < RelaxRecipeList.allfoodhistoryName.length; ++i ){
                for (int j = 0; j < listItem.length; j++) {
                    if(Objects.equals(listItem[j], RelaxRecipeList.allfoodhistoryName[i])){
                        AllfoodDid[j] = true;

                        break;
                    }
                }
            }
            for (int i = 0; i < AllfoodDid.length; i++) {
                if(AllfoodDid[i] != true){
                    AllfoodDid[i] = false;
                }
            }
        }

        else if(currentMode == "search"){
            merge_recipe_name_detail.setText(AllRecipeList.allRecipeNames[position_now]);
            merge_recipe_food_detail.setText(AllRecipeList.allRecipeFood[position_now]);
            merge_recipe_tag_detail.setText(AllRecipeList.allRecipeSugar[position_now]+AllRecipeList.allRecipeSalt[position_now]+AllRecipeList.allRecipeOil[position_now]);
            merge_recipe_step_detail.setText(AllRecipeList.allRecipeSteps[position_now]);
            merge_recipe_img_detail.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(AllRecipeList.imgName[position_now]),"drawable", context.getPackageName()));


            listItem = AllRecipeList.allRecipeFood[position_now].split(",");
            listDId = AllRecipeList.allRecipeDid[position_now].split(",");
            listImg = AllRecipeList.allRecipeFoodImg[position_now].split(",");
            HistoryfoodName = AllRecipeList.allfoodhistoryName;
            HistoryfoodDid = AllRecipeList.allfoodhistoryDid;


            checkedItem = new boolean[listItem.length];
            AllfoodDid = new boolean[listItem.length];
            int num = 0;

            for(int i = 0; i < AllRecipeList.allfoodhistoryName.length; ++i ){
                for (int j = 0; j < listItem.length; j++) {
                    if(Objects.equals(listItem[j], AllRecipeList.allfoodhistoryName[i])){
                        AllfoodDid[j] = true;

                        break;
                    }
                }
            }
            for (int i = 0; i < AllfoodDid.length; i++) {
                if(AllfoodDid[i] != true){
                    AllfoodDid[i] = false;
                }
            }
        }

        else if(currentMode == "autosearch"){
            System.out.println("AutoRecipeList.allfoodhistoryName: "+ Arrays.toString(AutoRecipeList.allfoodhistoryName));
            merge_recipe_name_detail.setText(AutoRecipeList.allRecipeNames[position_now]);
            merge_recipe_food_detail.setText(AutoRecipeList.allRecipeFood[position_now]);
            merge_recipe_tag_detail.setText(AutoRecipeList.allRecipeSugar[position_now]+AutoRecipeList.allRecipeSalt[position_now]+AutoRecipeList.allRecipeOil[position_now]);
            merge_recipe_step_detail.setText(AutoRecipeList.allRecipeSteps[position_now]);
            merge_recipe_img_detail.setImageResource(context.getApplicationContext().getResources().getIdentifier(String.valueOf(AutoRecipeList.imgName[position_now]),"drawable", context.getPackageName()));

            listItem = AutoRecipeList.allRecipeFood[position_now].split(",");
            listDId = AutoRecipeList.allRecipeDid[position_now].split(",");
            listImg = AutoRecipeList.allRecipeFoodImg[position_now].split(",");
            HistoryfoodName = AutoRecipeList.allfoodhistoryName;
            HistoryfoodDid = AutoRecipeList.allfoodhistoryDid;


            checkedItem = new boolean[listItem.length];
            AllfoodDid = new boolean[listItem.length];
            int num = 0;
            for(int i = 0; i < AutoRecipeList.allfoodhistoryName.length; ++i ){
                for (int j = 0; j < listItem.length; j++) {
                    if(Objects.equals(listItem[j], AutoRecipeList.allfoodhistoryName[i])){
                        AllfoodDid[j] = true;
                        break;
                    }
                }
            }
            for (int i = 0; i < AllfoodDid.length; i++) {
                if(AllfoodDid[i] != true){
                    AllfoodDid[i] = false;
                }
            }
        }


        evaluate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_fragment.getlistItem(listItem,listDId,listImg, HistoryfoodName,AllfoodDid);
                MainActivity.current_editdialog = 1;
                MainActivity.editdialog_change(MainActivity.current_editdialog, MainActivity.origin_editdialog, MainActivity.fm_p);
            }
        });

//        evaluate_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder mbuilder = new AlertDialog.Builder(NormalDetailFragment.context);
//                mbuilder.setTitle("Multiple choice list");
//                if(NormalRecipeList.allfoodhistoryName != null){
//                    for(int i = 0; i < NormalRecipeList.allfoodhistoryName.length; ++i){
//                        for(int y = 0; y < listItem.length; ++y){
//                            if(Objects.equals(listItem[y], NormalRecipeList.allfoodhistoryName[i])){
//                                checkedItem[y] = true;
//                                mUserItem.add(y);
//                            }
//
//                        }
//                    }
//                }
//
//
//
//                mbuilder.setMultiChoiceItems(listItem, checkedItem, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                        if(! isChecked){
//                            if(mUserItem.contains(which)){
//                                mUserItem.remove(which);
//                            }
//
//                            System.out.println("uncheckedItem[i]"+checkedItem[which] + which);
//                            System.out.println(" mUserItem.contains(which)"+ mUserItem.contains(which));
//                        }
//                        else if(isChecked){
//                            if(! mUserItem.contains(which)){
//                                mUserItem.add(which);
//                            }
//                            System.out.println("checkedItem[i]"+checkedItem[which] + which);
//                            System.out.println(" mUserItem.contains(which)"+ mUserItem.contains(which));
//                        }
//
//                    }
//                });
//                mbuilder.setCancelable(false);
//                mbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String item = "";
//                        for(int i = 0; i < mUserItem.size(); ++i){
//                            item += listItem[mUserItem.get(i)];
//                            if(i != mUserItem.size()-1){
//                                item += ",";
//                            }
//                        }
//                        System.out.println("item: "+item);
//                        for(int i = 0; i < checkedItem.length; ++i){
//                            checkedItem[i] = false;
//                            mUserItem.clear();
//                        }
//                    }
//                });
//
//                mbuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        for(int i = 0; i < checkedItem.length; ++i){
//                            checkedItem[i] = false;
//                            mUserItem.clear();
//                        }
//                    }
//                });
//                AlertDialog mdialog = mbuilder.create();
//                mdialog.show();
//
//            }
//        });






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


}