package com.gdin.analyse.tools;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public abstract class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener{
    private GestureDetectorCompat compat;  //处理手势的类：手势探测器,比GestureDetector能更好兼容低版本的api
    private RecyclerView recyclerView;


    public OnRecyclerItemClickListener(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        compat = new GestureDetectorCompat(recyclerView.getContext(),new ItemTouchHelperGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        compat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        compat.onTouchEvent(e);
    }
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
    //在WordSpellingView中调用此方法处理点击事件
    public abstract void onItemClick(RecyclerView.ViewHolder holder);

    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent motionEvent) { //按下时触发
            return false;
        }

        @Override
        public void onShowPress(MotionEvent event) { //按下时间超过瞬间，而且按下的时候没有松开或者拖动
        }

        @Override
        public boolean onSingleTapUp(MotionEvent event) { //单击事件
            View child = recyclerView.findChildViewUnder(event.getX(), event.getY());
            if(child==null)
                return false;
            RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(child);
            onItemClick(holder);
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) { //拖动事件
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) { //长按

        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {  //滑屏
            return false;
        }
    }
}

