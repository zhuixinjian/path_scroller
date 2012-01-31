package com.example;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.ViewConfiguration;
import android.widget.*;
import com.nineoldandroids.animation.ObjectAnimator;

import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

public class MyActivity extends Activity implements AbsListView.OnScrollListener, MyListView.IAwake {

    RelativeLayout layout;
    MyListView list;
    Button stub;
    ObjectAnimator animator;

    private final static int ROWS = 20;
    private int y = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        layout = (RelativeLayout) findViewById(R.id.layout);

        layout.setBackgroundColor(Color.argb(255, 228, 228, 228));
        list = new MyListView(this);
        list.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT));
        list.awakeDelegate = this;
        list.setCacheColorHint(Color.argb(0, 0, 0, 0));
        layout.addView(list);

        initData();
        initTextView();

        list.setOnScrollListener(this);
    }

    private void initTextView() {
        stub = new Button(this);
        stub.setLayoutParams(new FrameLayout.LayoutParams(120, 34));
        stub.setTextSize(14);
        stub.setText("0");
        stub.setTextColor(Color.WHITE);
        stub.setBackgroundColor(Color.argb(200, 47, 49, 100));

//        stub.getBackground().setAlpha(100);

        layout.addView(stub);

        animator = ObjectAnimator.ofFloat(stub, "alpha", 1.0f, 0.0f);
        animator.setStartDelay(ViewConfiguration.getScrollDefaultDelay() * 2);
        animator.setDuration(200);
//        animator.start();
    }

    private void initData() {
        String[] data = new String[ROWS];
        for (int i = 0; i < ROWS; i++) data[i] = "Row " + String.valueOf(i + 1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        list.setAdapter(adapter);
    }

    private static final String TAG = "MyActivity";

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {

        Log.v(TAG, "scroll!");
        animate(stub).setStartDelay(0).alpha(255);
//        Log.v(TAG, "list.computeVerticalScrollExtent() = " + list.computeVerticalScrollExtent());
//        Log.v(TAG, "list.computeVerticalScrollOffset() = " + list.computeVerticalScrollOffset());
//        Log.v(TAG, "list.computeVerticalScrollRange() = " + list.computeVerticalScrollRange());
//        Log.v(TAG, "====================================");


        if (totalItemCount - visibleItemCount > 0) {
            y = ((list.getHeight() * list.computeVerticalScrollOffset()) / (list.computeVerticalScrollRange() -
                    (list.computeVerticalScrollOffset() - list.computeVerticalScrollExtent())));

            int d = (totalItemCount * firstVisibleItem) / (totalItemCount - visibleItemCount);
            stub.setText(String.valueOf(d));
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) stub.getLayoutParams();
            params.setMargins(200, y, 10, 0);
            stub.setLayoutParams(params);


        }
    }

    @Override
    public void awake(int delay) {
//        stub.getBackground().setAlpha(255);
        Log.v(TAG, "awake!");
        if (!animator.isRunning()) animator.start();
    }
}
