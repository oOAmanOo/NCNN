package com.tencent.nanodetncnn.fridge;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tencent.nanodetncnn.AutoRecipeList;
import com.tencent.nanodetncnn.MainActivity;
import com.tencent.nanodetncnn.MergeRecipeListFragment;
import com.tencent.nanodetncnn.MyApplication;
import com.tencent.nanodetncnn.R;
import com.tencent.nanodetncnn.model.FridgeFoodSumModel;
import com.tencent.nanodetncnn.utils.MyUtils;

import java.util.ArrayList;


public class FoodFridgeFragment extends Fragment {
    private boolean firstIN = true;
    private FridgeFoodSumModel foodModel;
    private FoodFridgeListAdapter fragment_food_fridge_list_Adapter;
    private ImageView fragment_food_fridge_list_pic;
    private Button autosearch;
    private TextView fragment_food_fridge_list_name;
    public static String autosearchinput = null;

    private boolean nowEditType = false;



    public static FoodFridgeFragment newInstance(FridgeFoodSumModel foodModel) {
        FoodFridgeFragment fragment = new FoodFridgeFragment(foodModel);
        return fragment;
    }


    @SuppressLint("ValidFragment")
    public FoodFridgeFragment(FridgeFoodSumModel foodModel) {
        super();
        this.foodModel = foodModel;
    }


    public FoodFridgeFragment() {
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
        View v = inflater.inflate(R.layout.fragment_food_fridge_list, container, false);
        fragment_food_fridge_list_Adapter = v.findViewById(R.id.fragment_food_fridge_list_Adapter);
        fragment_food_fridge_list_pic = v.findViewById(R.id.fragment_food_fridge_list_pic);
        fragment_food_fridge_list_name = v.findViewById(R.id.fragment_food_fridge_list_name);
        autosearch = (Button) v.findViewById(R.id.fragment_food_fridge_list_check);
        autosearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(foodModel.name.length() != 0){
                    AutoRecipeList.search(foodModel.name);
                    MergeRecipeListFragment.getMode("autosearch");
                    MergeRecipeListFragment.getSearch(foodModel.name);
                    MainActivity.scanBtn_public.setVisibility(View.INVISIBLE);
                    replaceFragment(new MergeRecipeListFragment());
                    foodModel.name = null;
                }
            }
        });

        ImageView fragment_food_fridge_list_back = v.findViewById(R.id.fragment_food_fridge_list_back);
        fragment_food_fridge_list_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.onBack();
            }
        });

        Resources resources = getContext().getResources();
        final int resourceId = resources.getIdentifier(foodModel.imgName, "drawable",
                getContext().getPackageName());
        fragment_food_fridge_list_pic.setImageResource(resourceId);

        fragment_food_fridge_list_name.setText(foodModel.name+"\n\n總數："+foodModel.amount);

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
        ArrayList<FridgeFoodSumModel> list = MyApplication.foodModelPraise.getAllFridgeFoodByDid(foodModel.did);
        fragment_food_fridge_list_Adapter.initData(list);
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
