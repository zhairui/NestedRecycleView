package com.nestedrecycleview;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * Created by 10769 on 2016/10/19.
 */

public class StickyNavLayout extends LinearLayout implements NestedScrollingParent {

    private int topHeight=50;
    OverScroller mScroller;
    private View topview;
    public StickyNavLayout(Context context) {
        super(context);
        mScroller=new OverScroller(context);
    }

    public StickyNavLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller=new OverScroller(context);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(target, dx, dy, consumed);
        boolean hiddenTop=dy>0 && getScrollY()<topHeight; //上拉的时候 topvview显示的时候，父布局移动（consumed[1]=dy），隐藏的时候里面的子view移动
        boolean showTop=dy<0;
        if(hiddenTop || showTop){

            if(getScrollY()<topHeight){  //下拉的时候，如果topview显示的时候，父布局移动，隐藏的时候子View移动

            }else{
                consumed[1]=dy;
            }
            scrollBy(0,dy);
        }
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if(getScrollY()>=topHeight) return false;
        fling((int)velocityY);
        return true;
    }
    public void fling(int velocityY){
        mScroller.fling(0,getScrollY(),0,velocityY,0,0,0,topHeight);
        invalidate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        topview=findViewById(R.id.view);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        topHeight=topview.getMeasuredHeight();
    }

    @Override
    public void scrollTo(int x, int y) {
        if(y<0){
            y=0;
        }
        if(y>topHeight){
            y=topHeight;
        }
        if(y!=getScrollY()){
            super.scrollTo(x,y);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
            invalidate();
        }
    }
}
