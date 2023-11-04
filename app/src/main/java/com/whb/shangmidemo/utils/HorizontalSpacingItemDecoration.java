package com.whb.shangmidemo.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int spacing; // 间距

    private final int mColumnSpacing;// 列间距

    public HorizontalSpacingItemDecoration(int spacing) {
        this.spacing = spacing;
        this.mColumnSpacing = 0;
    }

    public HorizontalSpacingItemDecoration(int spacing, int columnSpacing) {
        this.spacing = spacing;
        this.mColumnSpacing = columnSpacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = spacing; // 设置左间距
        outRect.right = spacing; // 设置右间距

        if (mColumnSpacing != 0) {
//            outRect.top = mColumnSpacing;
            outRect.bottom = mColumnSpacing;
        }
    }
}
