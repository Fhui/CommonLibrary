package com.example.huifeng.library.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *  Recycle Decoration
 * Created by HIMan on 16/8/17.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

    private int width;
    private int height;

    public SpaceItemDecoration(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = width;
        outRect.right = width;
        outRect.bottom = height;
        outRect.top = height;
    }
}
