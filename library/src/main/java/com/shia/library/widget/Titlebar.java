package com.shia.library.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.shia.library.R;


/**
 * 标题控件
 */
public class Titlebar extends RelativeLayout {

    private String titleText;
    private boolean canBack;
    private ImageView imgBack;
    private String backText;
    private String moreText;
    private int moreImg;
    private TextView tvMore;
    private LinearLayout moreLayout;

    public Titlebar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Titlebar, 0, 0);
        try {
            titleText = ta.getString(R.styleable.Titlebar_titleText);
            canBack = ta.getBoolean(R.styleable.Titlebar_canBack, false);
            backText = ta.getString(R.styleable.Titlebar_backText);
            moreImg = ta.getResourceId(R.styleable.Titlebar_moreImg, 0);
            moreText = ta.getString(R.styleable.Titlebar_moreText);
            setUpView();
        } finally {
            ta.recycle();
        }
    }

    private void setUpView() {
        TextView tvTitle = (TextView) findViewById(R.id.title);
        tvTitle.setText(titleText);
        LinearLayout backBtn = (LinearLayout) findViewById(R.id.title_back);
        imgBack = (ImageView) findViewById(R.id.img_back);
        if (!canBack) {
            imgBack.setVisibility(GONE);
        }
        if (backText != null && !"".equals(backText)) {
            TextView tvBack = (TextView) findViewById(R.id.txt_back);
            tvBack.setText(backText);

            canBack = true;
        }
        backBtn.setVisibility(canBack ? VISIBLE : INVISIBLE);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });

        if (moreImg != 0) {
            ImageView moreImgView = (ImageView) findViewById(R.id.img_more);
            moreImgView.setImageDrawable(getContext().getResources().getDrawable(moreImg));
        }
        tvMore = (TextView) findViewById(R.id.txt_more);
        tvMore.setText(moreText);
        moreLayout = (LinearLayout) findViewById(R.id.txt_more_layout);
        if (moreText == null || "".equals(moreText)) {
            moreLayout.setVisibility(GONE);
        }
    }

    /**
     * 标题控件
     *
     * @param titleText
     *            设置标题文案
     */
    public void setTitleText(String titleText) {
        this.titleText = titleText;
        TextView tvTitle = (TextView) findViewById(R.id.title);
        tvTitle.setText(titleText);
    }

    public void setBackImg(int img) {
        findViewById(R.id.title_back).setVisibility(VISIBLE);
        imgBack.setVisibility(VISIBLE);
        imgBack.setImageDrawable(getContext().getResources().getDrawable(img));
    }

    public void setBackAction(View.OnClickListener listener) {
        LinearLayout backBtn = (LinearLayout) findViewById(R.id.title_back);
        backBtn.setOnClickListener(listener);
    }

    /**
     * 标题更多按钮
     *
     * @param img
     *            设置更多按钮
     */
    public void setMoreImg(int img) {
        moreImg = img;
        ImageView moreImgView = (ImageView) findViewById(R.id.img_more);
        moreImgView.setImageDrawable(getContext().getResources().getDrawable(moreImg));
    }

    /**
     * 设置更多按钮事件
     *
     * @param listener
     *            事件监听
     */
    public void setMoreImgAction(View.OnClickListener listener) {
        ImageView moreImgView = (ImageView) findViewById(R.id.img_more);
        moreImgView.setOnClickListener(listener);
    }

    public void setMoreTextImg(int img) {
        tvMore.setBackground(getContext().getResources().getDrawable(img));
    }

    /**
     * 设置更多按钮事件
     *
     * @param listener
     *            事件监听
     */
    public void setMoreTextAction(View.OnClickListener listener) {
        moreLayout.setVisibility(VISIBLE);
        moreLayout.setOnClickListener(listener);
    }

    /**
     * 设置更多文字内容
     *
     * @param text
     *            更多文本
     */
    public void setMoreTextContext(String text) {
        tvMore.setText(text);
        moreLayout.setVisibility(VISIBLE);
    }

    /**
     * 设置返回按钮事件
     *
     * @param listener
     *            事件监听
     */
    public void setBackListener(View.OnClickListener listener) {
        if (canBack) {
            LinearLayout backBtn = (LinearLayout) findViewById(R.id.title_back);
            backBtn.setOnClickListener(listener);
        }
    }

}