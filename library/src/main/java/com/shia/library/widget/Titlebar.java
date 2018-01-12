package com.shia.library.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.shia.library.R;


/**
 * 标题控件
 */
public class Titlebar extends LinearLayout {

    private Toolbar toolbar;
    private String titleText;
    private boolean canBack;
    private String backText;
    private String moreText;
    private int moreImg;

    private TextView tvMore;
    private ImageView moreImgView;


    public Titlebar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_title, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Titlebar, 0, 0);
        try {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            titleText = ta.getString(R.styleable.Titlebar_titleText);
            canBack = ta.getBoolean(R.styleable.Titlebar_canBack, true);
            backText = ta.getString(R.styleable.Titlebar_backText);
            moreImg = ta.getResourceId(R.styleable.Titlebar_moreImg, 0);
            moreText = ta.getString(R.styleable.Titlebar_moreText);
            setUpView();
        } finally {
            ta.recycle();
        }
    }

    private void setUpView() {
        final Context context = getContext();

        OnClickListener finshLinster = new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        };
        if (backText != null && !"".equals(backText)) {// 左边按钮
            TextView tvBack = (TextView) findViewById(R.id.txt_back);
            tvBack.setText(backText);
            tvBack.setOnClickListener(finshLinster);
            canBack = false;
        }

        if (context instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) context;
            activity.setSupportActionBar(toolbar);

            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            // 返回键点击事件
            toolbar.setNavigationIcon(R.drawable.ic_avigation);
            toolbar.setNavigationOnClickListener(finshLinster);

            if (!canBack) {// 不允许返回
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }

        TextView tvTitle = (TextView) findViewById(R.id.title);
        tvTitle.setText(titleText);
        ((Activity) getContext()).setTitle("");


        moreImgView = (ImageView) findViewById(R.id.img_more);
        if (moreImg != 0) {
            moreImgView.setImageDrawable(getContext().getResources().getDrawable(moreImg));
        } else {
            moreImgView.setVisibility(View.GONE);
        }
        tvMore = (TextView) findViewById(R.id.txt_more);
        tvMore.setText(moreText);
        if (moreText == null || "".equals(moreText)) {
            tvMore.setVisibility(View.GONE);
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * 标题控件
     *
     * @param titleText 设置标题文案
     */
    public void setTitleText(String titleText) {
        this.titleText = titleText;
        TextView tvTitle = (TextView) findViewById(R.id.title);
        tvTitle.setText(titleText);
    }

    /**
     * 标题更多按钮
     *
     * @param img 设置更多按钮
     */
    public void setMoreImg(int img) {
        moreImg = img;
        moreImgView.setVisibility(View.VISIBLE);
        moreImgView.setImageDrawable(getContext().getResources().getDrawable(moreImg));
    }


    /**
     * 设置更多按钮事件
     *
     * @param listener 事件监听
     */
    public void setMoreImgAction(OnClickListener listener) {
        moreImgView.setOnClickListener(listener);
    }


    /**
     * 设置更多按钮事件
     *
     * @param listener 事件监听
     */
    public void setMoreTextAction(OnClickListener listener) {
        tvMore.setOnClickListener(listener);
    }


    /**
     * 设置更多文字内容
     *
     * @param text 更多文本
     */
    public void setMoreTextContext(String text) {
        tvMore.setVisibility(View.VISIBLE);
        tvMore.setText(text);
    }


    /**
     * 设置返回按钮事件
     *
     * @param listener 事件监听
     */
    public void setBackListener(OnClickListener listener) {
        if (canBack) {
            toolbar.setNavigationOnClickListener(listener);
        }
    }

    /**
     * 设置取消按钮事件
     *
     * @param listener 事件监听
     */
    public void setBackTextAction(OnClickListener listener) {
        TextView tvBack = (TextView) findViewById(R.id.txt_back);
        tvBack.setOnClickListener(listener);
    }

}
