//package com.tencent.nanodetncnn;
//
////import static com.tencent.nanodetncnn.R.id.layout;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//
//
//public class recyclerViewActivity extends AppCompatActivity {
//
//    @SuppressLint("ResourceType")
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment1_layout);
//
//
//        FrameLayout layout = new FrameLayout(this);
//        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
//        layout.setId(R.id.recyclerView);
//        setContentView(layout);
//
//        getSupportFragmentManager().beginTransaction().add(R.id.recyclerView, new Fragment()).commit();
//    }
//
//
//}
