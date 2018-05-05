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
import com.shia.library.util.StringUtil;

/**
 * 标题控件
 */
public class Titlebar extends RelativeLayout {

    private LinearLayout tvBackLayout, tvMore1Layout, tvMore2Layout, tvMore3Layout;
    private TextView tvTitle, tvBack, tvMore1, tvMore2, tvMore3;
    private ImageView imgBack, imgMore1, imgMore2, imgMore3;

    public Titlebar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.shia_layout_title, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Titlebar, 0, 0);
        try {
            String titleText = ta.getString(R.styleable.Titlebar_titleText);
            boolean canBack = ta.getBoolean(R.styleable.Titlebar_canBack, true);
            String backText = ta.getString(R.styleable.Titlebar_backText);
            int moreImg = ta.getResourceId(R.styleable.Titlebar_moreImg, 0);
            String moreText = ta.getString(R.styleable.Titlebar_moreText);
            setUpView(titleText, canBack, backText, moreText, moreImg);
        } finally {
            ta.recycle();
        }
    }

    private void setUpView(String titleText, boolean canBack, String backText, String moreText, int moreImg) {
        tvTitle = (TextView) findViewById(R.id.title);
        tvBack = (TextView) findViewById(R.id.txt_back);
        tvMore1 = (TextView) findViewById(R.id.txt_more1);
        tvMore2 = (TextView) findViewById(R.id.txt_more2);
        tvMore3 = (TextView) findViewById(R.id.txt_more3);

        imgBack = (ImageView) findViewById(R.id.img_back);
        imgMore1 = (ImageView) findViewById(R.id.img_more1);
        imgMore2 = (ImageView) findViewById(R.id.img_more2);
        imgMore3 = (ImageView) findViewById(R.id.img_more3);

        tvBackLayout = (LinearLayout) findViewById(R.id.txt_back_layout);
        tvMore1Layout = (LinearLayout) findViewById(R.id.txt_more1_layout);
        tvMore2Layout = (LinearLayout) findViewById(R.id.txt_more2_layout);
        tvMore3Layout = (LinearLayout) findViewById(R.id.txt_more3_layout);
        setBackVisible(false);
        setMoreVisible(false);
        setMore2Visible(false);
        setMore3Visible(false);

        tvTitle.setText(titleText);
        if (StringUtil.isNotEmpty(backText)) {
            setBackText(backText);
            setBackAction(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) getContext()).finish();
                }
            });
        }
        setBackVisible(canBack);

        if (moreImg > 0) {
            setMoreImg(moreImg);
        } else if (StringUtil.isNotEmpty(moreText)) {
            setMoreText(moreText);
        }
    }

    /**
     * 标题控件
     *
     * @param titleText 设置标题文案
     */
    public Titlebar setTitleText(String titleText) {
        tvTitle.setText(titleText);
        return this;
    }

    /**
     * 设置返回文字内容
     *
     * @param text 返回文本
     */
    public Titlebar setBackText(String text) {
        setBackVisible(true);
        tvBack.setText(text);
        imgBack.setVisibility(GONE);
        return this;
    }

    /**
     * 标题返回按钮图标
     *
     * @param img 设置返回按钮
     */
    public Titlebar setBackImg(int img) {
        setBackVisible(true);
        imgBack.setImageResource(img);
        return this;
    }

    /**
     * 返回事件
     *
     * @param listener
     * @return
     */
    public Titlebar setBackAction(OnClickListener listener) {
        setBackVisible(true);
        tvBackLayout.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置返回按钮是否显示
     *
     * @param show 是否显示
     * @return
     */
    public Titlebar setBackVisible(boolean show) {
        tvBackLayout.setVisibility(show ? VISIBLE : GONE);
        return this;
    }

    /**
     * 设置更多文字内容
     *
     * @param text 更多文本
     * @return
     */
    public Titlebar setMoreText(String text) {
        setMoreVisible(true);
        tvMore1.setText(text);
        return this;
    }

    /**
     * 标题更多按钮图标
     *
     * @param img 设置更多按钮
     * @return
     */
    public Titlebar setMoreImg(int img) {
        setMoreVisible(true);
        imgMore1.setImageResource(img);
        return this;
    }

    /**
     * 设置更多按钮事件
     *
     * @param listener 事件监听
     * @return
     */
    public Titlebar setMoreAction(OnClickListener listener) {
        setMoreVisible(true);
        tvMore1Layout.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置更多按钮是否显示
     *
     * @param show 是否显示
     * @return
     */
    public Titlebar setMoreVisible(boolean show) {
        tvMore1Layout.setVisibility(show ? VISIBLE : GONE);
        return this;
    }

    /**
     * 设置更多2文字内容
     *
     * @param text 更多文本
     * @return
     */
    public Titlebar setMore2Text(String text) {
        setMore2Visible(true);
        tvMore2.setText(text);
        return this;
    }

    /**
     * 标题更多2按钮图标
     *
     * @param img 设置更多按钮
     * @return
     */
    public Titlebar setMore2Img(int img) {
        setMore2Visible(true);
        imgMore2.setImageResource(img);
        return this;
    }

    /**
     * 设置更多2按钮事件
     *
     * @param listener 事件监听
     * @return
     */
    public Titlebar setMore2Action(OnClickListener listener) {
        setMore2Visible(true);
        tvMore2Layout.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置更多2按钮是否显示
     *
     * @param show 是否显示
     * @return
     */
    public Titlebar setMore2Visible(boolean show) {
        tvMore2Layout.setVisibility(show ? VISIBLE : GONE);
        return this;
    }

    /**
     * 设置更多3文字内容
     *
     * @param text 更多文本
     * @return
     */
    public Titlebar setMore3Text(String text) {
        setMore3Visible(true);
        tvMore3.setText(text);
        return this;
    }

    /**
     * 标题更多3按钮图标
     *
     * @param img 设置更多按钮
     * @return
     */
    public Titlebar setMore3Img(int img) {
        setMore3Visible(true);
        imgMore3.setImageResource(img);
        return this;
    }

    /**
     * 设置更多3按钮事件
     *
     * @param listener 事件监听
     * @return
     */
    public Titlebar setMore3Action(OnClickListener listener) {
        setMore3Visible(true);
        tvMore3Layout.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置更多3按钮是否显示
     *
     * @param show 是否显示
     * @return
     */
    public Titlebar setMore3Visible(boolean show) {
        tvMore3Layout.setVisibility(show ? VISIBLE : GONE);
        return this;
    }
}