package com.hackathon.picfix.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.hackathon.picfix.PicFixImageView;
import com.hackathon.picfix.R;
import com.hackathon.picfix.adapter.ContentAdapter;

import butterknife.InjectView;


public class MainActivity extends BaseActivity {

    @InjectView(R.id.my_recycler_view)
    RecyclerView recyclerView;
    @InjectView(R.id.tool_bar)Toolbar toolbar;

    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setSupport Action bar
        setSupportActionBar(toolbar);
        //set recyclerview
        setRecyclerView();
    }

    /**
     * set {@link RecyclerView} layout manager, adapter.
     * */
    private void setRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(new ContentAdapter(this));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }
}
