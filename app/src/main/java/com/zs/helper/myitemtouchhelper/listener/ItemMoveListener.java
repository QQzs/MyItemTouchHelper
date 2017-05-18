package com.zs.helper.myitemtouchhelper.listener;

public interface ItemMoveListener {
    boolean onItemMove(int fromPosition, int toPosition);

    boolean onItemRemove(int position);
}
