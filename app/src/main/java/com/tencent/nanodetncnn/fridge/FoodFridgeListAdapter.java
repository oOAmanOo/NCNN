package com.tencent.nanodetncnn.fridge;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.Consts;
import com.tencent.nanodetncnn.MainActivity;
import com.tencent.nanodetncnn.R;
import com.tencent.nanodetncnn.model.FridgeFoodSumModel;
import com.tencent.nanodetncnn.utils.MyUtils;
import com.tencent.nanodetncnn.utils.MyViewRecycle;

import java.util.ArrayList;


public class FoodFridgeListAdapter extends FrameLayout {
    private ArrayList<FridgeFoodSumModel> m_List = null;
    private DataAdapter dataAdapter = null;
    private MyViewRecycle adapter_food_fridge_list_MyViewRecycleSliding = null;
    private int spanCount = 1;//橫向數量

    public FoodFridgeListAdapter(Context context) {
        super(context);
        init();

    }


    public FoodFridgeListAdapter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        inflate(getContext(), R.layout.adapter_food_fridge_list, this);
        adapter_food_fridge_list_MyViewRecycleSliding = findViewById(R.id.adapter_food_fridge_list_MyViewRecycleSliding);
    }



    public void notifyData() {
        dataAdapter.reflashData(this.m_List);
    }




    public void initData(ArrayList<FridgeFoodSumModel> m_list) {
        this.m_List = m_list;
        adapter_food_fridge_list_MyViewRecycleSliding.initRecycleView(spanCount, 0);

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View headView = layoutInflater.inflate(R.layout.head_food_fridge_list, null);


        dataAdapter = new DataAdapter(m_List);
        adapter_food_fridge_list_MyViewRecycleSliding.mySetAdapter(dataAdapter, headView,null);
    }
//
    public void notifyDataChanged() {
        dataAdapter.notifyDataChanged();
    }

    private class DataAdapter extends RecyclerView.Adapter<ViewHolderBlog> {
        private ArrayList<FridgeFoodSumModel> m_List;



        public void notifyDataChanged() {



            this.notifyDataSetChanged();
        }



        public void reflashData(ArrayList<FridgeFoodSumModel> m_List) {

            this.m_List = m_List;
            this.notifyDataSetChanged();
        }

        public DataAdapter(ArrayList<FridgeFoodSumModel> m_List) {
            this.m_List = m_List;
        }

        @Override
        public int getItemCount() {
            int ret = 0;
            if (m_List != null) {
                ret = m_List.size();
            }
            return ret;
        }

        @Override
        public ViewHolderBlog onCreateViewHolder(ViewGroup viewGroup, int i) {
            ViewHolderBlog ret = null;
            View v;
            v = LayoutInflater.from(getContext()).inflate(R.layout.adapter_food_fridge_list_item, viewGroup, false);
            ret = new ViewHolderBlog(v);
            return ret;
        }

        @Override
        public void onBindViewHolder(ViewHolderBlog viewHolder, int i) {

            FridgeFoodSumModel map = m_List.get(i);
            viewHolder.itemView.setTag(map);

            viewHolder.adapter_food_fridge_list_item_position.setText(map.position == 1 ? "冷凍":"冷藏");
            viewHolder.adapter_food_fridge_list_item_amount.setText(map.amount + "");
            viewHolder.adapter_food_fridge_list_item_date.setText(map.expireDate + "");
            viewHolder.adapter_food_fridge_list_item_own.setText(map.userName + "");



            int food_type_color = 0;

            if (MyUtils.getTwoDay(map.alertDate, MyUtils.getNowDateShortString()) > -1) {
                food_type_color = 0;
            } else if (MyUtils.getTwoDay(map.expireDate, MyUtils.getNowDateShortString()) > -1) {
                food_type_color = 1;
            } else {
                food_type_color = 2;
            }


            switch (food_type_color) {
                case Consts.Fridge_Food_all:
                    viewHolder.adapter_food_fridge_list_item_bg.setBackgroundColor(getResources().getColor(R.color.food_ok));
                    break;
                case Consts.Fridge_Food_Ready_to_eat:
                    viewHolder.adapter_food_fridge_list_item_bg.setBackgroundColor(getResources().getColor(R.color.food_re));
                    break;
                case Consts.Fridge_Food_Expired:
                    viewHolder.adapter_food_fridge_list_item_bg.setBackgroundColor(getResources().getColor(R.color.food_ex));
                    break;
            }

            if(map.amount ==0){
                viewHolder.adapter_food_fridge_list_item_bg.setBackgroundColor(getResources().getColor(R.color.food_0));

            }





        }
    }


    private class ViewHolderBlog extends RecyclerView.ViewHolder implements OnClickListener {
        public View adapter_food_fridge_list_item_bg;
        public TextView adapter_food_fridge_list_item_position;
        public TextView adapter_food_fridge_list_item_amount;
        public TextView adapter_food_fridge_list_item_date;
        public TextView adapter_food_fridge_list_item_own;

        public ViewHolderBlog(View itemView) {
            super(itemView);

            adapter_food_fridge_list_item_position = itemView.findViewById(R.id.adapter_food_fridge_list_item_position);
            adapter_food_fridge_list_item_amount = itemView.findViewById(R.id.adapter_food_fridge_list_item_amount);
            adapter_food_fridge_list_item_bg = itemView.findViewById(R.id.adapter_food_fridge_list_item_bg);
            adapter_food_fridge_list_item_date = itemView.findViewById(R.id.adapter_food_fridge_list_item_date);
            adapter_food_fridge_list_item_own = itemView.findViewById(R.id.adapter_food_fridge_list_item_own);

            // TODO layout_blog_grid_Date
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {


            FridgeFoodSumModel map = (FridgeFoodSumModel) v.getTag();
            ((MainActivity) getContext()).addFragment(
                    FridgeHistoryFragment.newInstance(map));
        }
    }

}
