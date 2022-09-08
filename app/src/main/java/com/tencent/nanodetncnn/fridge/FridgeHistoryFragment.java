package com.tencent.nanodetncnn.fridge;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.tencent.nanodetncnn.R;
import com.tencent.nanodetncnn.MyApplication;
import com.tencent.nanodetncnn.model.FridgeFoodSumModel;
import com.tencent.nanodetncnn.model.FridgeHistoryModel;
import com.tencent.nanodetncnn.model.MyCompartor_history_datetime;
import com.tencent.nanodetncnn.Consts;
import com.tencent.nanodetncnn.utils.MyUtils;

import java.util.ArrayList;
import java.util.Collections;


public class FridgeHistoryFragment extends Fragment {
    private boolean firstIN = true;
    private FridgeFoodSumModel foodModel;
    private FridgeHistoryListAdapter fragment_fridge_history_list_Adapter;
    private ImageView fragment_fridge_history_list_pic;
    private TextView fragment_fridge_history_list_name;
    private TextView fragment_fridge_history_list_data;
    private TextView fragment_fridge_history_list_expire;
    private TextView fragment_fridge_history_list_amount;


    private boolean nowEditType = false;



    public static FridgeHistoryFragment newInstance(FridgeFoodSumModel foodModel) {
        FridgeHistoryFragment fragment = new FridgeHistoryFragment(foodModel);
        return fragment;
    }


    @SuppressLint("ValidFragment")
    public FridgeHistoryFragment(FridgeFoodSumModel foodModel) {
        super();
        this.foodModel = foodModel;
    }


    public FridgeHistoryFragment() {
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
        View v = inflater.inflate(R.layout.fragment_fridge_history_list, container, false);
        fragment_fridge_history_list_Adapter = v.findViewById(R.id.fragment_fridge_history_list_Adapter);
        fragment_fridge_history_list_pic = v.findViewById(R.id.fragment_fridge_history_list_pic);
        fragment_fridge_history_list_name = v.findViewById(R.id.fragment_fridge_history_list_name);
        fragment_fridge_history_list_data = v.findViewById(R.id.fragment_fridge_history_list_data);
        fragment_fridge_history_list_expire = v.findViewById(R.id.fragment_fridge_history_list_expire);
        fragment_fridge_history_list_amount = v.findViewById(R.id.fragment_fridge_history_list_amount);



        ImageView fragment_fridge_history_list_back = v.findViewById(R.id.fragment_fridge_history_list_back);
        fragment_fridge_history_list_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.onBack();
            }
        });



        Resources resources = getContext().getResources();
        final int resourceId = resources.getIdentifier(foodModel.imgName, "drawable",
                getContext().getPackageName());
        fragment_fridge_history_list_pic.setImageResource(resourceId);

        fragment_fridge_history_list_name.setText(foodModel.name);
        fragment_fridge_history_list_data.setText(
                "擁有者   " + foodModel.userName+"\n"+
                "存放位置   " + (foodModel.position == 1 ? "冷凍": "冷藏")+"\n"+
                        "存入日期   " + foodModel.insertDate
                   );

        fragment_fridge_history_list_expire.setText(
                        "過期日   " + foodModel.expireDate
                     );

        fragment_fridge_history_list_amount.setText(

                        "當前存量   " + foodModel.amount);


        switch (MyUtils.checkColorByType(foodModel.alertDate,foodModel.expireDate)) {
            case Consts.Fridge_Food_all:
//                fragment_fridge_history_list_expire.setTextColor(getResources().getColor(R.color.food_ok));
                break;
            case Consts.Fridge_Food_Ready_to_eat:
                fragment_fridge_history_list_expire.setTextColor(getResources().getColor(R.color.food_re));
                break;
            case Consts.Fridge_Food_Expired:
                fragment_fridge_history_list_expire.setTextColor(getResources().getColor(R.color.food_ex));
                break;
        }


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




        ArrayList<FridgeHistoryModel> list = MyApplication.foodModelPraise.getFridgeHistoryByDid(foodModel.fid);


        //依日期排序
        MyCompartor_history_datetime myComparator = new MyCompartor_history_datetime();
        Collections.sort(list, myComparator);


        fragment_fridge_history_list_Adapter.initData(list,MyUtils.checkColorByType(foodModel.alertDate,foodModel.expireDate));
    }








}
