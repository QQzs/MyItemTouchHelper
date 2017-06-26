package com.zs.helper.myitemtouchhelper.listener;

import android.support.v7.widget.RecyclerView;


public interface ItemDragListener {

    /**
     * 拖动回调
     * @param viewHolder
     */
    void onStartDrags(RecyclerView.ViewHolder viewHolder);

    /**
     * 移动回调
     * @param viewHolder
     * @param flag
     */
    void onMoveData(RecyclerView.ViewHolder viewHolder,int flag);

}
