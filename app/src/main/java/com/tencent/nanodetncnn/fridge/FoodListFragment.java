package com.tencent.nanodetncnn.fridge;

import static com.tencent.nanodetncnn.MainActivity.replaceFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.tencent.nanodetncnn.MainActivity;
import com.tencent.nanodetncnn.MergeRecipeListFragment;
import com.tencent.nanodetncnn.R;
import com.tencent.nanodetncnn.model.FridgeFoodSumModel;

import java.util.ArrayList;



public class FoodListFragment extends Fragment {
    private boolean firstIN = true;
    private int pageType;
    private FoodListAdapter fragment_food_list_Adapter;
    private ArrayList<FridgeFoodSumModel> food_sum_list;

    private boolean nowEditType = false;



    public static FoodListFragment newInstance(int pageType,ArrayList<FridgeFoodSumModel> food_sum_list) {
        FoodListFragment fragment = new FoodListFragment(pageType,food_sum_list);
        return fragment;
    }


    @SuppressLint("ValidFragment")
    public FoodListFragment(int pageType,ArrayList<FridgeFoodSumModel> food_sum_list) {
        super();
        this.pageType = pageType;
        this.food_sum_list = food_sum_list;
//        this.food_list = food_list;
    }


    public FoodListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_food_list, container, false);
        fragment_food_list_Adapter = v.findViewById(R.id.fragment_food_fridge_list_Adapter);


        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (firstIN) {
            firstIN = false;



        }


        showData();

    }




    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();
        firstIN = true;

    }


    private void showData() {
        fragment_food_list_Adapter.initData(food_sum_list,false);
        fragment_food_list_Adapter.setEditType(nowEditType);
    }

    public void tagEditButton(boolean isEdit){
        nowEditType = isEdit;

        if(fragment_food_list_Adapter != null)
        fragment_food_list_Adapter.setEditType(nowEditType);
        if(isEdit) {
            for (FridgeFoodSumModel fdg : food_sum_list) {
                fdg.isChose = false;
            }
        }
    }

    public ArrayList<FridgeFoodSumModel> gerChoseItem() {
        if(fragment_food_list_Adapter !=null) {
            return fragment_food_list_Adapter.gerChoseItem();
        }else{
            return  new ArrayList<>();
        }
    }

    public void reflashData(ArrayList<FridgeFoodSumModel> new_list){

        food_sum_list = new_list;

        if(fragment_food_list_Adapter != null){
            fragment_food_list_Adapter.reflashData(food_sum_list);
            fragment_food_list_Adapter.notifyDataChanged();
        }

    }




}
