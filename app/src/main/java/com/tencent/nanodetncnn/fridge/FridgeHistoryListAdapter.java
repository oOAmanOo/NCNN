package com.tencent.nanodetncnn.fridge;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.R;
import com.tencent.nanodetncnn.model.FridgeHistoryModel;
import com.tencent.nanodetncnn.utils.MyViewRecycle;

import java.util.ArrayList;


public class FridgeHistoryListAdapter extends FrameLayout {
    private ArrayList<FridgeHistoryModel> m_List = null;
    private DataAdapter dataAdapter = null;
    private MyViewRecycle adapter_fridge_history_list_MyViewRecycleSliding = null;
    private int spanCount = 1;//橫向數量

    public FridgeHistoryListAdapter(Context context) {
        super(context);
        init();

    }


    public FridgeHistoryListAdapter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        inflate(getContext(), R.layout.adapter_fridge_history_list, this);
        adapter_fridge_history_list_MyViewRecycleSliding = findViewById(R.id.adapter_fridge_history_list_MyViewRecycleSliding);
    }



    public void notifyData() {
        dataAdapter.reflashData(this.m_List);
    }




    public void initData(ArrayList<FridgeHistoryModel> m_list,int showColor) {
        this.m_List = m_list;
        adapter_fridge_history_list_MyViewRecycleSliding.initRecycleView(spanCount, 0);

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View headView = layoutInflater.inflate(R.layout.head_fridge_history_list, null);


        dataAdapter = new DataAdapter(m_List,showColor);
        adapter_fridge_history_list_MyViewRecycleSliding.mySetAdapter(dataAdapter, headView,null);
    }
//
    public void notifyDataChanged() {
        dataAdapter.notifyDataChanged();
    }

    private class DataAdapter extends RecyclerView.Adapter<ViewHolderBlog> {
        private ArrayList<FridgeHistoryModel> m_List;
        private int showColor;



        public void notifyDataChanged() {



            this.notifyDataSetChanged();
        }



        public void reflashData(ArrayList<FridgeHistoryModel> m_List) {

            this.m_List = m_List;
            this.notifyDataSetChanged();
        }

        public DataAdapter(ArrayList<FridgeHistoryModel> m_List,int showColor) {
            this.m_List = m_List;
            this.showColor = showColor;
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
            v = LayoutInflater.from(getContext()).inflate(R.layout.adapter_fridge_history_list_item, viewGroup, false);
            ret = new ViewHolderBlog(v);
            return ret;
        }

        @Override
        public void onBindViewHolder(ViewHolderBlog viewHolder, int i) {

            FridgeHistoryModel map = m_List.get(i);
            viewHolder.itemView.setTag(map);

            viewHolder.adapter_fridge_history_list_item_updateNum.setText(map.updateNum+"");
            viewHolder.adapter_fridge_history_list_item_date.setText(map.updateDate + "");
            viewHolder.adapter_fridge_history_list_item_updateAmount.setText(map.updateAmount + "");
            viewHolder.adapter_fridge_history_list_item_use.setText(map.uid + "");


//            switch (showColor) {
//                case Consts.Fridge_Food_all:
//                    viewHolder.adapter_fridge_history_list_item_bg.setBackgroundColor(getResources().getColor(R.color.food_ok));
//                    break;
//                case Consts.Fridge_Food_Ready_to_eat:
//                    viewHolder.adapter_fridge_history_list_item_bg.setBackgroundColor(getResources().getColor(R.color.food_re));
//                    break;
//                case Consts.Fridge_Food_Expired:
//                    viewHolder.adapter_fridge_history_list_item_bg.setBackgroundColor(getResources().getColor(R.color.food_ex));
//                    break;
//            }
//




        }
    }


    private class ViewHolderBlog extends RecyclerView.ViewHolder implements OnClickListener {
        public View adapter_fridge_history_list_item_bg;
        public TextView adapter_fridge_history_list_item_updateAmount;
        public TextView adapter_fridge_history_list_item_updateNum;
        public TextView adapter_fridge_history_list_item_date;
        public TextView adapter_fridge_history_list_item_use;

        public ViewHolderBlog(View itemView) {
            super(itemView);

            adapter_fridge_history_list_item_updateAmount = itemView.findViewById(R.id.adapter_fridge_history_list_item_updateAmount);
            adapter_fridge_history_list_item_updateNum = itemView.findViewById(R.id.adapter_fridge_history_list_item_updatenum);
            adapter_fridge_history_list_item_bg = itemView.findViewById(R.id.adapter_fridge_history_list_item_bg);
            adapter_fridge_history_list_item_date = itemView.findViewById(R.id.adapter_fridge_history_list_item_date);
            adapter_fridge_history_list_item_use = itemView.findViewById(R.id.adapter_fridge_history_list_item_use);

            // TODO layout_blog_grid_Date
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }

}
