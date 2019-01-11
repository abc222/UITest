package com.example.xiaoxiong.uitest.piechartdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xiaoxiong.uitest.R;

import java.util.ArrayList;

public class PieViewTestActivity extends AppCompatActivity {

    private ArrayList<PieData> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_view_test);
        PieView pieView = (PieView)findViewById(R.id.pie_view);
        initData();
        pieView.setData(mData);
    }

    private void initData() {
        PieData p1 = new PieData("衣",25);
        PieData p2 = new PieData("食",35);
        PieData p3 = new PieData("住",45);
        PieData p4 = new PieData("行",55);
        mData.add(p1);
        mData.add(p2);
        mData.add(p3);
        mData.add(p4);
    }
}
