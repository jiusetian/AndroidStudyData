package com.androidstudydata.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidstudydata.R;

import java.util.List;

public class CoordinatorLayoutActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OneAdapter adapter;
    InitData initData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        recyclerView=findViewById(R.id.recyclerView);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        initData=new InitData();
        final List<String> list=initData.integerList(); //数据

        adapter=new OneAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setNewData(list);
        adapter.setPreLoadNumber(1);
    }
}
