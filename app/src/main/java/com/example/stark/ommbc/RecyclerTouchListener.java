package com.example.stark.ommbc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{
    private GestureDetector gDetector;
    private ClickListener cListener;

    public RecylcerTouchListener(Context context, final RecyclerView recyclerview, final ClickListener clickListener){
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e){
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e){
                View child = recyclerview.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null){
                    clickListener.onLongClick(child, recyclerview.getChildPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e){
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if(child != null && clickListener != null && gestureDetector,onTouchEvent(e)){
            clickListener.onClick(child, rv.getChildPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e){

    }

    @Override
    public void onRequestDisallowIntercptTouchEvent(boolean disallowIntercept){

    }

    public interface ClickListener{
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }
}
