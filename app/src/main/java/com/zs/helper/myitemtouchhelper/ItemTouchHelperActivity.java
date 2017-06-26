package com.zs.helper.myitemtouchhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.zs.helper.myitemtouchhelper.adapter.ItemTouchHelperAdapter;
import com.zs.helper.myitemtouchhelper.callback.ItemTouchHelperCallback;
import com.zs.helper.myitemtouchhelper.data.Cheeses;
import com.zs.helper.myitemtouchhelper.listener.ItemDragListener;
import com.zs.helper.myitemtouchhelper.util.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Linear布局item拖动和移动
 */
public class ItemTouchHelperActivity extends AppCompatActivity implements ItemDragListener {

    private List<String> mData = new ArrayList<>();
    private RecyclerView mRv;
    private ItemTouchHelperAdapter mAdapter;
    private ItemTouchHelperCallback mCallback;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRv = (RecyclerView) findViewById(R.id.rv);
        initData();
        setRecyclerView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i < Cheeses.MEMBWE.length; i++) {
            mData.add(Cheeses.MEMBWE[i]);
        }
        Collections.sort(mData, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    /**
     * 初始化RecyclerView
     */
    private void setRecyclerView() {
        mAdapter = new ItemTouchHelperAdapter(mData, this);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);
        // 设置 item间隔
        mRv.addItemDecoration(new SpaceItemDecoration(40));

        // 不可长按拖动 和 侧滑删除
        mCallback = new ItemTouchHelperCallback(mAdapter,true,true);
//        mCallback = new ItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(mCallback);
        mItemTouchHelper.attachToRecyclerView(mRv);

    }

    @Override
    public void onStartDrags(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void onMoveData(RecyclerView.ViewHolder viewHolder, int flag) {
        int position = viewHolder.getAdapterPosition();
//        height = mRv.getLayoutManager().findViewByPosition(1).getHeight();
        if (flag == 0){ // 上升
            if (position == 0){
                return;
            }else{
//                Collections.swap(mData, position, position - 1);
                if (position == 1){
                    moveData();
                }else{
                    mAdapter.onItemMove(position, position - 1);
                }
            }
        }else if (flag == 1){ // 下降
            if (position == mData.size() - 1){
                return;
            }else{
//                Collections.swap(mData, position, position + 1);
                if (position == 0){
                    moveData();
                }else{
                    mAdapter.onItemMove(position, position + 1);
                }
            }
        }
    }

    /**
     * 单独处理前两个item交换
     */
    private void moveData(){
        if (mData.size() < 2){
            return;
        }
        LinearLayoutManager manager = (LinearLayoutManager) mRv.getLayoutManager();
        // figure out the position of the first visible item
        int firstPos = manager.findFirstCompletelyVisibleItemPosition();
        int offsetTop = 0;
        if(firstPos >= 0) {
            View firstView = manager.findViewByPosition(firstPos);
            offsetTop = manager.getDecoratedTop(firstView) - manager.getTopDecorationHeight(firstView);
        }
        // apply changes
        mAdapter.onItemMove(1, 0);
        // reapply the saved position
        if(firstPos >= 0) {
            manager.scrollToPositionWithOffset(firstPos, offsetTop);
            Log.d("My_Log",mData.toString());
        }
    }

}
