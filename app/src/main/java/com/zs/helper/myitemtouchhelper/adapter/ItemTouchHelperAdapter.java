package com.zs.helper.myitemtouchhelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zs.helper.myitemtouchhelper.R;
import com.zs.helper.myitemtouchhelper.listener.ItemDragListener;
import com.zs.helper.myitemtouchhelper.listener.ItemMoveListener;

import java.util.Collections;
import java.util.List;

public class ItemTouchHelperAdapter extends RecyclerView.Adapter<ItemTouchHelperAdapter.ItemTouchHelperViewHolder> implements ItemMoveListener {

    private List<String> mData;
    private ItemDragListener mItemDragListener;

    public ItemTouchHelperAdapter(List<String> data, ItemDragListener itemDragListener) {
        mData = data;
        mItemDragListener = itemDragListener;
    }

    @Override
    public ItemTouchHelperViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_touch_helper, viewGroup, false);
        return new ItemTouchHelperViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ItemTouchHelperViewHolder viewHolder, final int position) {
        viewHolder.mTvStr.setText(mData.get(position));
        viewHolder.mIvDrag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mItemDragListener.onStartDrags(viewHolder);
                return false;
            }
        });

        viewHolder.mIvUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemDragListener.onMoveData(viewHolder,0);
            }
        });

        viewHolder.mIvDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemDragListener.onMoveData(viewHolder,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //1、交换数据
        Collections.swap(mData, fromPosition, toPosition);
        //2、刷新
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public boolean onItemRemove(int position) {
        //1、删除数据
        mData.remove(position);
        //2、刷新
        notifyItemRemoved(position);
        return true;
    }

    class ItemTouchHelperViewHolder extends RecyclerView.ViewHolder {

        TextView mTvStr;
        ImageView mIvDrag;
        ImageView mIvUp;
        ImageView mIvDown;

        public ItemTouchHelperViewHolder(View itemView) {
            super(itemView);
            mTvStr = (TextView) itemView.findViewById(R.id.tvStr);
            mIvDrag = (ImageView) itemView.findViewById(R.id.ivDrag);
            mIvUp = (ImageView) itemView.findViewById(R.id.iv_up);
            mIvDown = (ImageView) itemView.findViewById(R.id.iv_down);
        }
    }

}
