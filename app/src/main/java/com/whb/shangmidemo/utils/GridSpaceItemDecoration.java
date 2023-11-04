package com.whb.shangmidemo.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {
	private final int mRowSpacing;//行间距
	private final int mColumnSpacing;// 列间距

	/**
	 * @param rowSpacing    行间距
	 * @param columnSpacing 列间距
	 */
	public GridSpaceItemDecoration(int rowSpacing, int columnSpacing) {
		this.mRowSpacing = rowSpacing;
		this.mColumnSpacing = columnSpacing;
	}

	@Override
	public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
		int position = parent.getChildAdapterPosition(view);
		int spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
//		int column = position % spanCount;
//
//		// 设置左边距
//		if (column == 0) {
//			outRect.left = 0;
//			outRect.right = mColumnSpacing;
//		}
//		// 设置右边距
//		if (column == spanCount - 1) {
//			outRect.right = 0;
//			outRect.left = mColumnSpacing;
//		}
//		// 设置上边距，可根据需要调整
//		outRect.top = mRowSpacing;

		int column = position % spanCount;

// 设置左边距
		if (column == 0) {
			outRect.left = 0;
			outRect.right = mColumnSpacing;
		} else if (column == spanCount - 1) {
			// 设置右边距
			outRect.left = mColumnSpacing;
			outRect.right = 0;
		} else {
			// 其他列的左右边距
			outRect.left = mColumnSpacing / 2;
			outRect.right = mColumnSpacing / 2;
		}

// 设置上边距，可根据需要调整
		outRect.top = mRowSpacing;

	}
}
