package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;

public class MyActivity extends Activity implements AbsListView.OnScrollListener {

    RelativeLayout layout;
    MyListView list;
    Button stub;

    private final static int ROWS = 20;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        layout = (RelativeLayout) findViewById(R.id.layout);

        list = new MyListView(this);
        list.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT));
        layout.addView(list);

        initData();
        initTextView();

        list.setOnScrollListener(this);
    }

    private void initTextView() {
        stub = new Button(this);
        stub.setLayoutParams(new FrameLayout.LayoutParams(160, 40));
        stub.setTextSize(14);
        stub.setText("0");

        stub.getBackground().setAlpha(100);

        layout.addView(stub);
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
        Log.v(TAG, "list.computeVerticalScrollExtent() = " + list.computeVerticalScrollExtent());
        Log.v(TAG, "list.computeVerticalScrollOffset() = " + list.computeVerticalScrollOffset());
        Log.v(TAG, "list.computeVerticalScrollRange() = " + list.computeVerticalScrollRange());
        Log.v(TAG, "====================================");


        int y = 1;
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

}
