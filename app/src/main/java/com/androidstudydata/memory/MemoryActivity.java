package com.androidstudydata.memory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.androidstudydata.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MemoryActivity extends AppCompatActivity implements View.OnClickListener {

    private List<ImageView> imgs;
    private List<String> strs;

    static MemoryLeak memoryLeak;

    @Subscribe
    public void onEvent(EmptyEvent event) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        imgs = new ArrayList<>();
        strs = new ArrayList<>();
        findViewById(R.id.memory_btn).setOnClickListener(this);
        EventBus.getDefault().register(this);

        if (memoryLeak == null) {
            memoryLeak = new MemoryLeak();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.memory_btn:
                //add();
                shake();
                break;
        }
    }

    private void add() {
        for (int i = 0; i < 10000; i++) {
            ImageView imageView = new ImageView(this);
            imgs.add(imageView);
        }
    }

    private void shake() {
        for (int i = 0; i < 1000000; i++) {
            String s = new String(i + "");
            strs.add(s);
        }
    }

    class MemoryLeak {
        void doSomeThing() {
            try {
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Wheee!!!");
        }
    }

    class EmptyEvent {

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
