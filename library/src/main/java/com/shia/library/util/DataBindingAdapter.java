package com.shia.library.util;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shia.library.R;

/**
 * Created by hehz on 2017/4/6.
 */
public class DataBindingAdapter {

    @BindingAdapter("android:layout_width")
    public static void setLayoutWidth(View view, float width) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = (int) width;
        view.setLayoutParams(params);
    }

    @BindingAdapter("android:layout_height")
    public static void setLayoutHeight(View view, float height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = (int) height;
        view.setLayoutParams(params);
    }

    @BindingAdapter("android:layout_marginLeft")
    public static void setLayoutMarginLeft(View view, float marginLeft) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins((int) marginLeft, p.topMargin, p.rightMargin, p.bottomMargin);
            view.requestLayout();
        }
    }

    @BindingAdapter({ "image" })
    public static void imageLoader(ImageView imageView, String url) {
        if (url != null && !"".equals(url)) {
            Glide.with(imageView.getContext()).load(url).error(R.drawable.default_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        }
    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }
}
