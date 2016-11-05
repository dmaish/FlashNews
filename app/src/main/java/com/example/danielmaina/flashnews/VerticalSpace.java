package com.example.danielmaina.flashnews;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by danielmaina on 11/5/16.
 */
public class VerticalSpace extends RecyclerView.ItemDecoration{
    int Space;

    //constructor for class
    public VerticalSpace(int Space){
        this.Space=Space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //implementing spacings on each recyclerViews item
        outRect.left=Space;
        outRect.right=Space;
        outRect.bottom=Space;

        //only implementing top spacing for the first RecyclerView Item
        if(parent.getChildLayoutPosition(view)==0){
            outRect.top=Space;
        }

    }


}
