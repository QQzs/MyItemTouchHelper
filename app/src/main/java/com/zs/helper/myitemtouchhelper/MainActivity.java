package com.zs.helper.myitemtouchhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.zs.helper.myitemtouchhelper.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainView = (LinearLayout) findViewById(R.id.main_view);

        ListView listView = new ListView(this);
        mainView.addView(listView);

        List<Class> activitys = new ArrayList<>();
        activitys.add(ItemTouchHelperActivity.class);
        activitys.add(GridTouchHelperActivity.class);
        activitys.add(LinearTouchHelperActivity.class);

        listView.setAdapter(new MyAdapter(activitys));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Adapter adapter = parent.getAdapter();
                Class activity = (Class) adapter.getItem(position);
                startActivity(new Intent(MainActivity.this,activity));
            }
        });
    }
}
