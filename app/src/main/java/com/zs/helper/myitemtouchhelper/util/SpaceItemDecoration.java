package com.zs.helper.myitemtouchhelper.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zs
 * Date：2017年 05月 23日
 * Time：13:10
 * —————————————————————————————————————
 * About: 设置 RecyclerView 条目间隔
 * —————————————————————————————————————
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        //设置左右的间隔如果想设置的话自行设置，我这用不到就注释掉了
/*          outRect.left = space;
            outRect.right = space;*/

        //改成使用上面的间隔来设置
        if(parent.getChildPosition(view) != 0)
            outRect.top = space;
    }
}
