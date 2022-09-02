package com.tencent.nanodetncnn.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.donkingliang.headerviewadapter.view.HeaderRecyclerView;
import com.tencent.nanodetncnn.R;

import java.lang.ref.WeakReference;


public class MyViewRecycle extends FrameLayout {
    public Context context = null;
    public boolean firstIN = true;
    public SwipeRefreshLayout myViewRecycleSwipeRefreshLayout;
    public HeaderRecyclerView aRecycleView = null;
    public View headView,headViewEdit;


    //加載更多參數
    private int currentScrollState = 0;
    private int[] lastPositions;
    private int lastVItemPosition = 0;
    public int lastVisibleItemPosition = 0;
    public int lastVisibleItemPositionOffset = 0;


    //----------WeakReference Handler
    private static class MyHandler extends Handler {
        private final WeakReference<MyViewRecycle> finalWeakObjct;

        public MyHandler(MyViewRecycle fromObject) {
            finalWeakObjct = new WeakReference<>(fromObject);
        }

        @Override
        public void handleMessage(Message msg) {
            MyViewRecycle thisObject = finalWeakObjct.get();
            if (thisObject != null) {
                //    —>   thisObject.
                switch (msg.what) {
                    case 0:
                        thisObject.myViewRecycleSwipeRefreshLayout.setRefreshing(false);
                        break;
                    case 1:
                        thisObject.myViewRecycleSwipeRefreshLayout.setRefreshing(true);
                        break;

                    case 2:
                        // scrollLastPos(msg.arg1,msg.arg2);
                        break;

                }
            }
        }
    }

    private final MyHandler myHandler = new MyHandler(this);
//----------WeakReference Handler--- end


    public MyViewRecycle(Context context) {
        super(context);
        this.context = context;
        initView();
    }


    public MyViewRecycle(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    /*


     */


    private void initView_sliding() {

        LayoutInflater.from(context).inflate(R.layout.view_recycle_sliding, this);
        aRecycleView = findViewById(R.id.view_recycle_sliding_recycle_view);
        myViewRecycleSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.view_recycle_sliding_swipeRefreshLayout);
        myViewRecycleSwipeRefreshLayout.setColorSchemeResources(R.color.holo_blue_bright, R.color.holo_green_light,
                R.color.holo_orange_light, R.color.holo_red_light);

        //加載更新
        myViewRecycleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                myHandler.sendEmptyMessageDelayed(0, 500);

                new Thread() {

                    @Override
                    public void run() {
                        try {

                        } catch (Exception e) {
                            myHandler.sendEmptyMessage(1);
                        }
                    }
                }.start();
            }
        });


        //加載更多 * grid 和 line 不同
        aRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                try {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    StaggeredGridLayoutManager staggeredGridLayoutManager
                            = (StaggeredGridLayoutManager) layoutManager;
                    if (lastPositions == null) {
                        lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                    }
                    staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                    lastVItemPosition = findMax(lastPositions);
                    lastVisibleItemPosition = findTop(lastPositions);
                    lastVisibleItemPositionOffset = aRecycleView.computeVerticalScrollOffset();
                    View topView = layoutManager.getChildAt(0);          //获取可视的第一个view
                    lastVisibleItemPositionOffset = topView.getTop();                                   //获取与该view的顶部的偏移量
                    lastVisibleItemPosition = layoutManager.getPosition(topView);  //得到该View的数组位置
                    //    MyLog.d("Change ===>  Position:   " + lastVisibleItemPosition + ", offset:" + lastVisibleItemPositionOffset);
                } catch (Exception e) {

                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                try {
                    currentScrollState = newState;
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    if ((visibleItemCount > 0 && currentScrollState == RecyclerView.SCROLL_STATE_IDLE &&
                            (lastVItemPosition) >= totalItemCount - 1)) {
                    }

                } catch (Exception e) {

                }
            }
        });
    }

    private void initView() {

        LayoutInflater.from(context).inflate(R.layout.view_recycle, this);
        aRecycleView =findViewById(R.id.view_recycle_sliding_recycle_view);


    }


    public void scrollLastPos(int v1, int v2) {
        ((StaggeredGridLayoutManager) aRecycleView.getLayoutManager())
                .scrollToPositionWithOffset(v1, v2);
    }

    private void scrollLastPos() {
        int v1 = lastVisibleItemPosition;
        int v2 = lastVisibleItemPositionOffset;
        //   MyLog.d("*scrollLastPos===>  Position:   " + v1 + ", offset:" + v2);
        // RecyclerView.LayoutManager layoutManager = aRecycleView.getLayoutManager();
        // StaggeredGridLayoutManager staggeredGridLayoutManager
        //        = (StaggeredGridLayoutManager) layoutManager;
        //   RecyclerView.LayoutManager. mLayoutManager = aRecycleView.getLayoutManager();
        ((StaggeredGridLayoutManager) aRecycleView.getLayoutManager())
                .scrollToPositionWithOffset(v1, v2);
    }


    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }


    private int findTop(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value < max) {
                max = value;
            }
        }
        return max;
    }


    //pop 2,15
    public void initRecycleView( int spanCount, int spacing) {
        // 设置布局管理器
        // 1.线性布局
        // LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false);
        // 2.Grid布局
        // RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        // 3.瀑布流
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        boolean includeEdge = true;
        aRecycleView.setLayoutManager(layoutManager);

        if (firstIN) {
            firstIN = false;
            aRecycleView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        }


    }



    public void setEditType(boolean isEdit) {

        if (this.headView != null) {
            aRecycleView.removeHeaderView(this.headView);
        }
        if (this.headViewEdit != null) {
            aRecycleView.removeHeaderView(this.headViewEdit);
        }
        if(isEdit){
            aRecycleView.addHeaderView( this.headViewEdit );
        }else{
            aRecycleView.addHeaderView( this.headView );
        }

    }

    public void mySetAdapter(RecyclerView.Adapter reAdapter, View headView, View headViewEdit) {

        try {
            aRecycleView.setAdapter(reAdapter);
        } catch (Exception e) {
        }


        if(this.headView != null){
            aRecycleView.removeHeaderView(headView);
        }
        if(this.headViewEdit != null){
            aRecycleView.removeHeaderView(headViewEdit);
        }


        this.headView = headView;
        this.headViewEdit = headViewEdit;

        if(headView!=null)
        aRecycleView.addHeaderView(headView );

    }



}
