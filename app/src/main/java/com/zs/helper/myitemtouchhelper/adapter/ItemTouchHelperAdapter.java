package com.zs.helper.myitemtouchhelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

        viewHolder.mEdit.setTag(position);
        viewHolder.mEdit.setText("");
        viewHolder.mEdit.setHint(mData.get(position));
        viewHolder.mEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                关键点：1.给edittext设置tag，此tag用来与position做对比校验，验证当前选中的edittext是否为需要的控件;
//                2.焦点判断：只有当前有焦点的edittext才有更改数据的权限，否则会造成数据紊乱
//                3.edittext内数据变动直接直接更改datalist的数据值，以便滑动view时显示正确
                if ((Integer)viewHolder.mEdit.getTag() == position && viewHolder.mEdit.hasFocus()) {
//                    dataList.get(position).setLnum(s.toString());
                    mData.set(position,s.toString());
                }
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
        EditText mEdit;

        public ItemTouchHelperViewHolder(View itemView) {
            super(itemView);
            mTvStr = (TextView) itemView.findViewById(R.id.tvStr);
            mIvDrag = (ImageView) itemView.findViewById(R.id.ivDrag);
            mIvUp = (ImageView) itemView.findViewById(R.id.iv_up);
            mIvDown = (ImageView) itemView.findViewById(R.id.iv_down);
            mEdit = (EditText) itemView.findViewById(R.id.edit_text);
        }
    }

}
