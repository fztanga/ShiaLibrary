package com.shia.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shia.library.listener.RecyclerSlideHelper;

/**
 * Created by hehz on 2017/4/13.
 */
public abstract class SliderQuickAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {

    protected Context context;

    public SliderQuickAdapter(Context context, int layoutResId) {
        super(layoutResId);
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        recyclerView.addOnItemTouchListener(new RecyclerSlideHelper(context, new RecyclerSlideHelper.Callback() {
            @Override
            public int getHorizontalRange(RecyclerView.ViewHolder holder) {

                if (holder.itemView instanceof LinearLayout) {
                    ViewGroup viewGroup = (ViewGroup) holder.itemView;
                    if (viewGroup.getChildCount() == 2) {
                        View sliderView = viewGroup.getChildAt(1);
                        if (sliderView.getVisibility() == View.GONE) {
                            return 0;
                        }
                        return sliderView.getWidth();
                    }
                }
                return 0;
            }

            @Override
            public RecyclerView.ViewHolder getChildViewHolder(View childView) {
                return recyclerView.getChildViewHolder(childView);
            }

            @Override
            public View findTargetView(float x, float y) {
                return recyclerView.findChildViewUnder(x, y);
            }

        }));
    }
}
