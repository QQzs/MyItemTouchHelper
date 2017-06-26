package com.zs.helper.myitemtouchhelper.callback;

import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.zs.helper.myitemtouchhelper.listener.ItemMoveListener;


public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    public static final float ALPHA_FULL = 1.0f;
    ItemMoveListener mItemMoveListener;

    /**
     * 是否关闭 长按 或者 侧滑
     */
    private boolean closeLongPressDrag;
    private boolean closeItemViewSwipe;

    public ItemTouchHelperCallback(ItemMoveListener itemMoveListener) {
        mItemMoveListener = itemMoveListener;
    }

    public ItemTouchHelperCallback(ItemMoveListener mItemMoveListener, boolean closeLongPressDrag, boolean closeItemViewSwipe) {
        this.mItemMoveListener = mItemMoveListener;
        this.closeLongPressDrag = closeLongPressDrag;
        this.closeItemViewSwipe = closeItemViewSwipe;
    }

    /**
     * 获取动作标识
     * 动作标识分：dragFlags和swipeFlags
     * dragFlags：列表滚动方向的动作标识（如竖直列表就是上和下，水平列表就是左和右）
     * wipeFlags：与列表滚动方向垂直的动作标识（如竖直列表就是左和右，水平列表就是上和下）
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        // 如果你不想上下拖动，可以将 dragFlags = 0
        int dragFlags;
        // 如果你不想左右滑动，可以将 swipeFlags = 0
        int swipeFlags;
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            swipeFlags = 0;
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        }
        if (closeLongPressDrag){
            dragFlags = 0;
        }
        if (closeItemViewSwipe){
            swipeFlags = 0;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }


    /**
     * 是否开启item长按拖拽功能
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
//        return false;
    }

    /**
     * 是否开启item左右滑动删除
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
//        return false;
    }

    /**
     * 当item拖拽移动时触发
     *
     * @param recyclerView
     * @param viewHolder       当前被拖拽的item的viewHolder
     * @param targetViewHolder 当前被拖拽的item下方的另一个item的viewHolder
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder targetViewHolder) {
        return mItemMoveListener.onItemMove(viewHolder.getAdapterPosition(), targetViewHolder.getAdapterPosition());
    }

    /**
     * 当item侧滑出去时触发（竖直列表是侧滑，水平列表是竖滑）
     *
     * @param viewHolder
     * @param direction  滑动的方向
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mItemMoveListener.onItemRemove(viewHolder.getAdapterPosition());
    }

    /**
     * 当item被拖拽或侧滑时触发
     *
     * @param viewHolder
     * @param actionState 当前item的状态
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {

        //不管是拖拽或是侧滑，背景色都要变化
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE)
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(android.R.color.darker_gray));
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 当item的交互动画结束时触发
     *
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setScaleY(1);
        viewHolder.itemView.setAlpha(ALPHA_FULL);
        viewHolder.itemView.setBackgroundColor(0);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            float value = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(value);
            viewHolder.itemView.setScaleY(value);
        }
    }
}
