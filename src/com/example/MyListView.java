package com.example;

import android.content.Context;
import android.widget.ListView;

public class MyListView extends ListView {


    public MyListView(Context context) {
        super(context);
    }

    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    public int computeVerticalScrollOffset() {
        return super.computeVerticalScrollOffset();
    }

    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }
}

