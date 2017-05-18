package com.zs.helper.myitemtouchhelper.listener;

import android.support.v7.widget.RecyclerView;


public interface ItemDragListener {

    void onStartDrags(RecyclerView.ViewHolder viewHolder);

    void onMoveData(RecyclerView.ViewHolder viewHolder,int flag);

}
