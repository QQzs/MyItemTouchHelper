package com.zs.helper.myitemtouchhelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zs
 * Date：2017年 06月 26日
 * Time：16:48
 * —————————————————————————————————————
 * About:
 * —————————————————————————————————————
 */

public class GridTouchHelperActivity extends Activity {

    private RecyclerView mRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRv = (RecyclerView) findViewById(R.id.rv);
    }
}
