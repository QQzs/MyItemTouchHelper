package com.zs.helper.myitemtouchhelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.zs.helper.myitemtouchhelper.adapter.RecyclerListAdapter;
import com.zs.helper.myitemtouchhelper.callback.ItemTouchHelperCallback;
import com.zs.helper.myitemtouchhelper.listener.ItemDragListener;

/**
 * Created by zs
 * Date：2017年 06月 26日
 * Time：16:48
 * —————————————————————————————————————
 * About: Grid布局拖动
 * —————————————————————————————————————
 */
public class GridTouchHelperActivity extends Activity implements ItemDragListener{

    private RecyclerView recyclerView;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.rv);

        final RecyclerListAdapter adapter = new RecyclerListAdapter(this, this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        final int spanCount = 2;
        final GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public void onStartDrags(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void onMoveData(RecyclerView.ViewHolder viewHolder, int flag) {

    }
}
