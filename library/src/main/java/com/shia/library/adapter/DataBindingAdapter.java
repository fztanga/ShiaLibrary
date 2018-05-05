package com.shia.library.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * databing 通用adapter Created by hehz on 2018/4/10.
 */
public abstract class DataBindingAdapter<T, B extends ViewDataBinding>
        extends BaseQuickAdapter<T, DataBindingAdapter.ViewHolder> {

    public DataBindingAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public DataBindingAdapter(@LayoutRes int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(DataBindingAdapter.ViewHolder helper, T item) {
        convert(helper, (B) helper.itemView.getTag(), item);
    }

    protected abstract void convert(ViewHolder helper, B binding, T item);

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        B binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(binding);
        return view;
    }

    @Override
    protected ViewHolder createBaseViewHolder(View view) {
        return new ViewHolder(view);
    }

    public class ViewHolder extends BaseViewHolder {

        public ViewHolder(View view) {
            super(view);
        }

    }
}
