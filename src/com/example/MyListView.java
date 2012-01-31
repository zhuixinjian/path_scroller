package com.example;

import android.content.Context;
import android.widget.ListView;

public class MyListView extends ListView {

    public interface IAwake {
        public void awake(int startDelay);
    }

    public IAwake awakeDelegate;


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

    protected boolean awakenScrollBars (int startDelay, boolean invalidate) {
//        if (!invalidate)
        awakeDelegate.awake(startDelay);
        return super.awakenScrollBars(startDelay, invalidate);
    }
}


