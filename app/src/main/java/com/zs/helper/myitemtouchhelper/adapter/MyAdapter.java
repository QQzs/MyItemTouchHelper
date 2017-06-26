package com.zs.helper.myitemtouchhelper.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zs
 * Date：2017年 06月 15日
 * Time：15:06
 * —————————————————————————————————————
 * About:
 * —————————————————————————————————————
 */

public class MyAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Class> acts;

    public MyAdapter(List<Class> acts) {
        this.acts = acts;
    }

    @Override
    public int getCount() {
        return acts == null ? 0:acts.size();
    }

    @Override
    public Object getItem(int position) {
        return acts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            if (inflater == null) {
                inflater = LayoutInflater.from(parent.getContext());
            }
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(acts.get(position).getSimpleName());
        return textView;
    }
}
