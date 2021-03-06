package com.slavetny.quwitest.presentation;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.IntRange;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewMargin extends RecyclerView.ItemDecoration {

    private final int columns;
    private final int margin;

    public RecyclerViewMargin(@IntRange(from = 0) int margin, @IntRange(from = 0) int columns) {
        this.margin = margin;
        this.columns = columns;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        outRect.right = margin;
        outRect.bottom = margin;

        if (position < columns) {
            outRect.top = margin;
        }
        if (position % columns == 0) {
            outRect.left = margin;
        }
    }
}