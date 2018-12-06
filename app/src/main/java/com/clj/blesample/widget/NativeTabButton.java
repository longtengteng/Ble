package com.clj.blesample.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clj.blesample.BMainActivity;
import com.clj.blesample.R;


public class NativeTabButton extends FrameLayout {
    private int mIndex;

    private ImageView mImage;
    private TextView mTitle;

    private Drawable mSelectedImg;
    private Drawable mUnselectedImg;

    private Context mContext;
    private int mSelectedColorRes = R.color.color_text;
    private int mUnselectedColorRes = R.color.color_c8;

    public NativeTabButton(Context context) {
        this(context, null);
    }

    public NativeTabButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NativeTabButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        this.mContext = context;

        OnClickListener clickListner = new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BMainActivity) mContext)
                        .setFragmentShow(mIndex);
            }
        };

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_native_tab_button, this, true);
        LinearLayout container = (LinearLayout) findViewById(R.id.tab_btn_container);

        mImage = (ImageView) findViewById(R.id.tab_btn_default);
        mTitle = (TextView) findViewById(R.id.tab_btn_title);
        container.setOnClickListener(clickListner);
    }

    public void setIndex(int index) {
        this.mIndex = index;
    }

    public void setUnselectedImage(Drawable img) {
        this.mUnselectedImg = img;
    }

    public void setSelectedImage(Drawable img) {
        this.mSelectedImg = img;
    }

    public void setmSelectedColorRes(@ColorRes int mSelectedColorRes) {
        this.mSelectedColorRes = mSelectedColorRes;
    }

    public void setmUnselectedColorRes(@ColorRes int mUnselectedColorRes) {
        this.mUnselectedColorRes = mUnselectedColorRes;
    }

    /**
     * 设置字体颜色
     *
     * @param selected
     */
    private void setSelectedColor(Boolean selected) {
        if (selected) {
            mTitle.setTextColor(ContextCompat.getColor(getContext(), mSelectedColorRes));
        } else {
            mTitle.setTextColor(ContextCompat.getColor(getContext(), mUnselectedColorRes));
        }
    }

    public void setSelectedButton(Boolean selected) {
        setSelectedColor(selected);
        if (selected) {
            mImage.setImageDrawable(mSelectedImg);
        } else {
            mImage.setImageDrawable(mUnselectedImg);
        }
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

}
