package com.louisgeek.listview2gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.id_tv_up)
    TextView idTvUp;
    @Bind(R.id.id_tv_down)
    TextView idTvDown;
    @Bind(R.id.id_lv)
    ListView idLv;
    @Bind(R.id.id_gv)
    GridView idGv;

    List<Map<String,Object>> mapList=new ArrayList<>();
    MyBaseAdapter myBaseAdapter;
    AnimationSet animationSet;
    LayoutAnimationController layoutAnimationController;


    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
        initAnimationSet();
        initLayoutAnimationController();
        myBaseAdapter=new MyBaseAdapter(mapList,this);
        myBaseAdapter.setIsGridLayout(true);
        dealView();
    }

    private void initLayoutAnimationController() {
        //单个文件
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(800);
        layoutAnimationController= new LayoutAnimationController(alphaAnimation,0.1f);//子项动画时间间隔
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
    }

    private void initAnimationSet() {
        animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(1000);
        //animationSet.setStartOffset(1000); // 1s后开始
        animationSet.addAnimation(alphaAnimation);
    }

    private void initData() {

        for (int i = 0; i < 30; i++) {
            Map<String,Object> map=new HashMap<>();
            map.put("name","测试数据"+i);
            mapList.add(map);
        }
    }

    @OnClick({R.id.id_tv_up, R.id.id_tv_down})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_tv_up:
                Toast.makeText(MainActivity.this, "返回", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_tv_down:
                myBaseAdapter.setIsGridLayout(!myBaseAdapter.isGridLayout());
                dealView();
                break;
        }
    }

    private void dealView() {
        if (myBaseAdapter.isGridLayout()){
            idLv.setVisibility(View.INVISIBLE);
            idGv.setVisibility(View.VISIBLE);

            idGv.setAdapter(myBaseAdapter);
            idGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity.this, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });
            //idGv.smoothScrollToPositionFromTop(firstVisiblePosition, 0);//无效。。。
            idGv.setSelection(idLv.getFirstVisiblePosition());
            //idGv.startAnimation(animationSet);
            idGv.setLayoutAnimation(layoutAnimationController);
            idTvDown.setText("切换到LV");

        }else{

            idLv.setVisibility(View.VISIBLE);
            idGv.setVisibility(View.INVISIBLE);

            idLv.setAdapter(myBaseAdapter);
            idLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity.this, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });
            //idLv.smoothScrollToPositionFromTop(firstVisiblePosition, 0);//无效。。。
            idLv.setSelection(idGv.getFirstVisiblePosition());
            idLv.setLayoutAnimation(layoutAnimationController);
            //idLv.setAnimation(animationSet);
            idTvDown.setText("切换到GV");

        }
    }


}
