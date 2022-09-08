package com.tencent.nanodetncnn.fridge;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tencent.nanodetncnn.Consts;
import com.tencent.nanodetncnn.MainActivity;
import com.tencent.nanodetncnn.R;
import com.tencent.nanodetncnn.model.FridgeFoodSumModel;
import com.tencent.nanodetncnn.utils.MyViewRecycle;

import java.util.ArrayList;


public class FoodListAdapter extends FrameLayout {
    private ArrayList<FridgeFoodSumModel> m_List = null;
    private DataAdapter dataAdapter = null;
    private MyViewRecycle adapter_food_list_MyViewRecycleSliding = null;
    private int spanCount = 1;//橫向數量

    public FoodListAdapter(Context context) {
        super(context);
        init();

    }


    public FoodListAdapter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        inflate(getContext(), R.layout.adapter_food_list, this);
        adapter_food_list_MyViewRecycleSliding = findViewById(R.id.adapter_food_fridge_list_MyViewRecycleSliding);
    }

    public ArrayList<FridgeFoodSumModel> gerChoseItem() {
        return dataAdapter.gerChoseItem();
    }

    public void notifyData() {
        dataAdapter.reflashData(this.m_List);
    }

    public void addData(ArrayList<FridgeFoodSumModel> moreData) {
        this.m_List.addAll(moreData);
        dataAdapter.reflashData(this.m_List);
    }

    public void reflashData(ArrayList<FridgeFoodSumModel> newList) {
        this.m_List = newList;
        dataAdapter.reflashData(this.m_List);
    }

    //
    public void setEditType(boolean isEdit) {
        dataAdapter.setEditType(isEdit);
        adapter_food_list_MyViewRecycleSliding.setEditType(isEdit);
    }


    public void initData(ArrayList<FridgeFoodSumModel> m_list, boolean editType) {
        this.m_List = m_list;
        adapter_food_list_MyViewRecycleSliding.initRecycleView(spanCount, 0);

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View headView = layoutInflater.inflate(R.layout.head_food_list, null);
        View headView_edit = layoutInflater.inflate(R.layout.head_food_list_edit, null);


        dataAdapter = new DataAdapter(m_List);
        dataAdapter.setEditType(editType);
        adapter_food_list_MyViewRecycleSliding.mySetAdapter(dataAdapter, headView, headView_edit);
    }
//
    public void notifyDataChanged() {
        dataAdapter.notifyDataChanged();
    }




    ///==================================
    private class DataAdapter extends RecyclerView.Adapter<ViewHolderBlog> {
        private ArrayList<FridgeFoodSumModel> m_List;
        private boolean isEditMode = false;

        public void goNotifyData() {
            this.notifyDataSetChanged();
        }

        public ArrayList<FridgeFoodSumModel> gerChoseItem() {
            ArrayList<FridgeFoodSumModel> ccLise = new ArrayList<FridgeFoodSumModel>();
            for (FridgeFoodSumModel ff : m_List) {
                if (ff.isChose) {
                    FridgeFoodSumModel ttF = new FridgeFoodSumModel();
                    ttF.name = ff.name;
                    ttF.did = ff.did;
                    ttF.imgName = ff.imgName;
                    ttF.amount = ff.amount;
                    ccLise.add(ttF);
                }
            }
            return ccLise;
        }

        public void notifyDataChanged() {
            this.notifyDataSetChanged();
        }


        public void setEditType(boolean isEdit) {
            isEditMode = isEdit;
            this.notifyDataSetChanged();
        }

        public void reflashData(ArrayList<FridgeFoodSumModel> m_List) {
            this.m_List = m_List;
            this.notifyDataSetChanged();
        }

        public DataAdapter(ArrayList<FridgeFoodSumModel> m_List) {
            this.m_List = m_List;
            Log.d("debug001", "data size:" + m_List.size());
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
            v = LayoutInflater.from(getContext()).inflate(R.layout.adapter_food_list_item, viewGroup, false);
            ret = new ViewHolderBlog(v);
            return ret;
        }

        @Override
        public void onBindViewHolder(ViewHolderBlog viewHolder, int i) {

            FridgeFoodSumModel map = m_List.get(i);
            viewHolder.itemView.setTag(map);
            viewHolder.adapter_food_list_item_bg.setTag(map);

            viewHolder.adapter_food_list_item_chose.setTag(map);
            viewHolder.adapter_food_list_item_title.setText(map.name);
            viewHolder.adapter_food_list_item_amount.setText(map.amount + ""); //使用後台的值
//            viewHolder.adapter_food_list_item_amount.setText(map.amount_self + ""); //自己算的值
            viewHolder.adapter_food_list_item_date.setText(map.expireDate + "");
            viewHolder.adapter_food_list_item_own.setText(map.userName + "");


            Resources resources = getContext().getResources();
            final int resourceId = resources.getIdentifier(map.imgName, "drawable",
                    getContext().getPackageName());

            viewHolder.adapter_food_list_item_pic.setImageResource(resourceId);


            switch (map.food_color) {
                case Consts.Fridge_Food_color_ok:
                    viewHolder.adapter_food_list_item_bg.setBackgroundColor(getResources().getColor(R.color.food_ok));
                    break;
                case Consts.Fridge_Food_color_Ready_to_eat:
                    viewHolder.adapter_food_list_item_bg.setBackgroundColor(getResources().getColor(R.color.food_re));
                    break;
                case Consts.Fridge_Food_color_Expired:
                    viewHolder.adapter_food_list_item_bg.setBackgroundColor(getResources().getColor(R.color.food_ex));
                    break;
            }

            viewHolder.adapter_food_list_item_chose.setVisibility(isEditMode ? VISIBLE : INVISIBLE);
//            viewHolder.adapter_food_list_item_date.setVisibility(isEditMode ? VISIBLE : INVISIBLE);
//            viewHolder.adapter_food_list_item_own.setVisibility(isEditMode ? VISIBLE : INVISIBLE);

            viewHolder.adapter_food_list_item_date.setVisibility( INVISIBLE);
            viewHolder.adapter_food_list_item_own.setVisibility( INVISIBLE);
            if (map.isChose) {
                viewHolder.adapter_food_list_item_chose.setBackgroundResource(R.drawable.a10_bg_shape_2);
            } else {
                viewHolder.adapter_food_list_item_chose.setBackgroundResource(R.drawable.a10_bg_shape_1);
            }


        }
    }


    private class ViewHolderBlog extends RecyclerView.ViewHolder implements OnClickListener {
        public View adapter_food_list_item_bg;
        public View adapter_food_list_item_chose;
        public ImageView adapter_food_list_item_pic;
        public TextView adapter_food_list_item_title;
        public TextView adapter_food_list_item_amount;
        public TextView adapter_food_list_item_date;
        public TextView adapter_food_list_item_own;

        public ViewHolderBlog(View itemView) {
            super(itemView);
            adapter_food_list_item_chose = itemView.findViewById(R.id.adapter_food_list_item_chose);
            adapter_food_list_item_pic = itemView.findViewById(R.id.adapter_food_list_item_pic);
            adapter_food_list_item_title = itemView.findViewById(R.id.adapter_food_list_item_title);
            adapter_food_list_item_amount = itemView.findViewById(R.id.adapter_food_list_item_amount);
            adapter_food_list_item_bg = itemView.findViewById(R.id.adapter_food_list_item_bg);
            adapter_food_list_item_date = itemView.findViewById(R.id.adapter_food_list_item_date);
            adapter_food_list_item_own = itemView.findViewById(R.id.adapter_food_list_item_own);

            adapter_food_list_item_chose.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    FridgeFoodSumModel map = (FridgeFoodSumModel) view.getTag();
                    map.isChose = !map.isChose;
                    dataAdapter.goNotifyData();
                }
            });
            adapter_food_list_item_bg.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    FridgeFoodSumModel map = (FridgeFoodSumModel) view.getTag();
                    ((MainActivity) getContext()).addFragment(FoodFridgeFragment.newInstance(map));


                }
            });

            // TODO layout_blog_grid_Date
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {



        }
    }

}
