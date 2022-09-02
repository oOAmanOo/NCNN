package com.tencent.nanodetncnn.fridge;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tencent.nanodetncnn.Consts;
import com.tencent.nanodetncnn.MainActivity;
import com.tencent.nanodetncnn.MyApplication;
import com.tencent.nanodetncnn.R;
import com.tencent.nanodetncnn.model.FridgeFoodSumModel;

import java.util.ArrayList;

import Adapter.SectionPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFridgeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFridgeFragment extends Fragment {

    private Button fragment_my_fridge_editButton;
    private boolean editMode = false;


    private View myFragment;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private FoodListFragment food_All_fragment;
    private FoodListFragment food_Ready_fragment;
    private FoodListFragment food_Expired_fragment;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyFridgeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyFridgeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyFridgeFragment newInstance(String param1, String param2) {
        MyFridgeFragment fragment = new MyFridgeFragment();
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


        food_All_fragment = new FoodListFragment(Consts.Fridge_Food_all,
                MyApplication.foodModelPraise.getAllFridgeFoodSum(Consts.Fridge_Food_all));
        food_Ready_fragment = new FoodListFragment(Consts.Fridge_Food_Ready_to_eat,
                MyApplication.foodModelPraise.getAllFridgeFoodSum(Consts.Fridge_Food_Ready_to_eat));
        food_Expired_fragment = new FoodListFragment(Consts.Fridge_Food_Expired,
                MyApplication.foodModelPraise.getAllFridgeFoodSum(Consts.Fridge_Food_Expired));


//        Log.d("debug001","TEST DAY====>"+ MyUtils.getTwoDay("2022-08-27",MyUtils.getNowDateShortString())  );
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_my_fridge, container, false);

        viewPager = myFragment.findViewById(R.id.viewPager);
        tabLayout = myFragment.findViewById(R.id.tabLayout);
        fragment_my_fridge_editButton = myFragment.findViewById(R.id.fragment_my_fridge_editButton);
        fragment_my_fridge_editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editMode) {
                    //用戶確認要 編輯show Dialog

                    //取出用戶選出的資料項
                    ArrayList<FridgeFoodSumModel> allList = food_All_fragment.gerChoseItem();
                    ArrayList<FridgeFoodSumModel> alertList = food_Ready_fragment.gerChoseItem();
                    ArrayList<FridgeFoodSumModel> expiredList = food_Expired_fragment.gerChoseItem();


                    Log.d("debug001", "allList---->" + allList.size());
                    Log.d("debug001", "alertList---->" + alertList.size());
                    Log.d("debug001", "expiredList---->" + expiredList.size());

                    //剔除重複項
                    for (FridgeFoodSumModel ff : alertList) {
                        Log.d("debug001", "alertList---->" + ff.did);
                        if (!checkExistInAll(ff, allList)) {
                            FridgeFoodSumModel ttF = new FridgeFoodSumModel();
                            ttF.name = ff.name;
                            ttF.imgName = ff.imgName;
                            ttF.amount = ff.amount;
                            ttF.did = ff.did;
                            ttF.fid = ff.fid;
                            allList.add(ttF);
                        }
                    }

                    for (FridgeFoodSumModel ff : expiredList) {
                        Log.d("debug001", "expiredList---->" + ff.did);
                        if (!checkExistInAll(ff, allList)) {

                            FridgeFoodSumModel ttF = new FridgeFoodSumModel();
                            ttF.name = ff.name;
                            ttF.imgName = ff.imgName;
                            ttF.amount = ff.amount;
                            ttF.did = ff.did;
                            ttF.fid = ff.fid;

                            allList.add(ttF);
                        }
                    }

                    MyApplication.choseItem_List.clear();
                    MyApplication.choseItem_List.addAll(allList); //用戶要拿出的食材
                    if(MyApplication.choseItem_List.size() != 0){
                        int first = 0;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("[");
                        for (int i = 0; i < MyApplication.choseItem_List.size(); ++i){
                            if(first != 0){
                                stringBuilder.append(",");
                            }else{
                                ++first;
                            }
                            stringBuilder.append("\"" + MyApplication.choseItem_List.get(i).did + "\"");
                        }
                        stringBuilder.append("]");
                        MainActivity.editjson = stringBuilder.toString();
                        MainActivity.editfridgedialog_change(2, 1, MainActivity.fm_p);
                    }
                    editMode = false;
                    fragment_my_fridge_editButton.setText("編輯");
                    food_All_fragment.tagEditButton(editMode);
                    food_Ready_fragment.tagEditButton(editMode);
                    food_Expired_fragment.tagEditButton(editMode);

                } else {
                    //chose mode
                    MyApplication.choseItem_List.clear();
                    editMode = true;
                    fragment_my_fridge_editButton.setText("確定");
                    food_All_fragment.tagEditButton(editMode);
                    food_Ready_fragment.tagEditButton(editMode);
                    food_Expired_fragment.tagEditButton(editMode);
                }


            }
        });

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return myFragment;
    }

    private boolean checkExistInAll(FridgeFoodSumModel ch, ArrayList<FridgeFoodSumModel> allList) {
        for (FridgeFoodSumModel ff : allList) {
            if (ff.did == ch.did) {
                return true;
            }
        }
        return false;

    }

    private void resetChoseMode() {
        editMode = false;
        fragment_my_fridge_editButton.setText("編輯");
        food_All_fragment.tagEditButton(editMode);
        food_Ready_fragment.tagEditButton(editMode);
        food_Expired_fragment.tagEditButton(editMode);
    }




    @Override
    public void onResume() {
        super.onResume();
    }


    private void refalshFragment() {
        //重新取資料並reflash
        food_All_fragment.reflashData(  MyApplication.foodModelPraise.getAllFridgeFoodSum(Consts.Fridge_Food_all));
        food_Ready_fragment.reflashData(  MyApplication.foodModelPraise.getAllFridgeFoodSum(Consts.Fridge_Food_Ready_to_eat));
        food_Expired_fragment.reflashData(  MyApplication.foodModelPraise.getAllFridgeFoodSum(Consts.Fridge_Food_Expired));
    }


    private void setUpViewPager(ViewPager viewPager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());

        adapter.addFragment(food_All_fragment, "全部");
        adapter.addFragment(food_Ready_fragment, "即期");
        adapter.addFragment(food_Expired_fragment, "過期");

        viewPager.setAdapter(adapter);
    }


    public void showDialog(Object d) {
        WindowManager wm = getActivity().getWindowManager();
        Display display = wm.getDefaultDisplay();
        WindowManager.LayoutParams lp = ((Dialog) d).getWindow().getAttributes();
        lp.width = display.getWidth() * 4 / 5;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        ((Dialog) d).getWindow().setAttributes(lp);
        ((Dialog) d).getWindow().setBackgroundDrawable(new ColorDrawable());
        ((Dialog) d).show();

    }


    public void showBigDialog(Object d) {


        Window window = ((Dialog) d).getWindow();

        //   window.setGravity(Gravity.BOTTOM);
        //  window.setWindowAnimations(R.style.dialog_animation);
        window.getDecorView().setPadding(0, 0, 0, 0);

        //背景色透明
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //设置dialog周围activity背景的透明度，[0f,1f]，0全透明，1不透明黑
        window.setDimAmount(0.0f);

        WindowManager.LayoutParams lp = ((Dialog) d).getWindow().getAttributes();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        ((Dialog) d).getWindow().setAttributes(lp);
        ((Dialog) d).getWindow().setBackgroundDrawable(new ColorDrawable());
        ((Dialog) d).show();

    }


}