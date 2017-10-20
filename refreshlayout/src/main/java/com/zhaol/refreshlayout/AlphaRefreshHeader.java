package com.zhaol.refreshlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Description
 * Company Beijing icourt
 * author  lu.zhao  E-mail:zhaolu@icourt.cc
 * date createTime：17/10/9
 * version 1.0.2
 */

public class AlphaRefreshHeader extends RelativeLayout implements RefreshHeader {
    private static final List<Integer> HEADER_VIEW_IDS = Arrays.asList(
//            R.mipmap.pull_to_refresh_0, R.mipmap.pull_to_refresh_1,
//            R.mipmap.pull_to_refresh_2, R.mipmap.pull_to_refresh_3,
//            R.mipmap.pull_to_refresh_4, R.mipmap.pull_to_refresh_5,
//            R.mipmap.pull_to_refresh_6, R.mipmap.pull_to_refresh_7,
//            R.mipmap.pull_to_refresh_8, R.mipmap.pull_to_refresh_9,
//            R.mipmap.pull_to_refresh_10, R.mipmap.pull_to_refresh_11,
//            R.mipmap.pull_to_refresh_12, R.mipmap.pull_to_refresh_13,
            R.mipmap.pull_to_refresh_14, R.mipmap.pull_to_refresh_15,
            R.mipmap.pull_to_refresh_16, R.mipmap.pull_to_refresh_17,
            R.mipmap.pull_to_refresh_18, R.mipmap.pull_to_refresh_19,
            R.mipmap.pull_to_refresh_20, R.mipmap.pull_to_refresh_21,
            R.mipmap.pull_to_refresh_22, R.mipmap.pull_to_refresh_23,
            R.mipmap.pull_to_refresh_24, R.mipmap.pull_to_refresh_25,
            R.mipmap.pull_to_refresh_26, R.mipmap.pull_to_refresh_27,
            R.mipmap.pull_to_refresh_28, R.mipmap.pull_to_refresh_29,
            R.mipmap.pull_to_refresh_30, R.mipmap.pull_to_refresh_31,
            R.mipmap.pull_to_refresh_32, R.mipmap.pull_to_refresh_33,
            R.mipmap.pull_to_refresh_34, R.mipmap.pull_to_refresh_35,
            R.mipmap.pull_to_refresh_36, R.mipmap.pull_to_refresh_37,
            R.mipmap.pull_to_refresh_38, R.mipmap.pull_to_refresh_39,
            R.mipmap.pull_to_refresh_40, R.mipmap.pull_to_refresh_41,
            R.mipmap.pull_to_refresh_42, R.mipmap.pull_to_refresh_43,
            R.mipmap.pull_to_refresh_44, R.mipmap.pull_to_refresh_45,
            R.mipmap.pull_to_refresh_46, R.mipmap.pull_to_refresh_47,
            R.mipmap.pull_to_refresh_48, R.mipmap.pull_to_refresh_49,
            R.mipmap.pull_to_refresh_50, R.mipmap.pull_to_refresh_51,
            R.mipmap.pull_to_refresh_52, R.mipmap.pull_to_refresh_53,
            R.mipmap.pull_to_refresh_54, R.mipmap.pull_to_refresh_55,
            R.mipmap.pull_to_refresh_56, R.mipmap.pull_to_refresh_57,
            R.mipmap.pull_to_refresh_58, R.mipmap.pull_to_refresh_59,
            R.mipmap.pull_to_refresh_60
    );
    protected ImageView mArrowView;
    protected ImageView mProgressView;
    protected RefreshKernel mRefreshKernel;
    protected com.zhaol.refreshlayout.MaterialProgressDrawable mArrowDrawable;
    protected com.zhaol.refreshlayout.MaterialProgressDrawable mProgressDrawable;
    protected SpinnerStyle mSpinnerStyle = SpinnerStyle.Translate;
    protected int mFinishDuration = 500;
    protected int mBackgroundColor;
    protected int mPaddingTop = 20;
    protected int mPaddingBottom = 20;

    public AlphaRefreshHeader(Context context) {
        super(context);
        this.initView(context, null);
    }

    public AlphaRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context, attrs);
    }

    public AlphaRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context, attrs);
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public AlphaRefreshHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        DensityUtil density = new DensityUtil();
        LinearLayout layout = new LinearLayout(context);
        layout.setId(android.R.id.widget_frame);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setOrientation(LinearLayout.VERTICAL);

        LayoutParams lpHeaderLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lpHeaderLayout.addRule(CENTER_IN_PARENT);
        addView(layout, lpHeaderLayout);

        LayoutParams lpArrow = new LayoutParams(density.dip2px(50), density.dip2px(50));
        lpArrow.addRule(CENTER_IN_PARENT);
        mArrowView = new ImageView(context);
        mArrowView.setMaxWidth(density.dip2px(50));
        mArrowView.setMaxHeight(density.dip2px(50));
        addView(mArrowView, lpArrow);

        LayoutParams lpProgress = new LayoutParams(density.dip2px(51), density.dip2px(51));
        lpProgress.addRule(CENTER_IN_PARENT);
        mProgressView = new ImageView(context);
        mProgressView.animate().setInterpolator(new LinearInterpolator());
        addView(mProgressView, lpProgress);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ClassicsHeader);

        lpArrow.width = ta.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableArrowSize, lpArrow.width);
        lpArrow.height = ta.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableArrowSize, lpArrow.height);
        lpProgress.width = ta.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableProgressSize, lpProgress.width);
        lpProgress.height = ta.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableProgressSize, lpProgress.height);

        lpArrow.width = ta.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableSize, lpArrow.width);
        lpArrow.height = ta.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableSize, lpArrow.height);
        lpProgress.width = ta.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableSize, lpProgress.width);
        lpProgress.height = ta.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableSize, lpProgress.height);

        mFinishDuration = ta.getInt(R.styleable.ClassicsHeader_srlFinishDuration, mFinishDuration);
        mSpinnerStyle = SpinnerStyle.values()[ta.getInt(R.styleable.ClassicsHeader_srlClassicsSpinnerStyle, mSpinnerStyle.ordinal())];

        if (ta.hasValue(R.styleable.ClassicsHeader_srlDrawableArrow)) {
            mArrowView.setImageDrawable(ta.getDrawable(R.styleable.ClassicsHeader_srlDrawableArrow));
        } else {
            mArrowView.setImageResource(R.mipmap.pull_to_refresh_60);
        }

        if (ta.hasValue(R.styleable.ClassicsHeader_srlDrawableProgress)) {
            mProgressView.setImageDrawable(ta.getDrawable(R.styleable.ClassicsHeader_srlDrawableProgress));
        } else {
            mProgressDrawable = new com.zhaol.refreshlayout.MaterialProgressDrawable(getContext(), mProgressView);
            mProgressDrawable.setColorSchemeColors(0xffed6c00);
            mProgressDrawable.setAlpha(255);
            mProgressView.setImageDrawable(mProgressDrawable);
        }

        int primaryColor = ta.getColor(R.styleable.ClassicsHeader_srlPrimaryColor, 0);
        int accentColor = ta.getColor(R.styleable.ClassicsHeader_srlAccentColor, 0);
        if (primaryColor != 0) {
            if (accentColor != 0) {
                setPrimaryColors(primaryColor, accentColor);
            } else {
                setPrimaryColors(primaryColor);
            }
        } else if (accentColor != 0) {
            setPrimaryColors(0, accentColor);
        }

        ta.recycle();

        if (getPaddingTop() == 0) {
            if (getPaddingBottom() == 0) {
                setPadding(getPaddingLeft(), mPaddingTop = density.dip2px(20), getPaddingRight(), mPaddingBottom = density.dip2px(20));
            } else {
                setPadding(getPaddingLeft(), mPaddingTop = density.dip2px(20), getPaddingRight(), mPaddingBottom = getPaddingBottom());
            }
        } else {
            if (getPaddingBottom() == 0) {
                setPadding(getPaddingLeft(), mPaddingTop = getPaddingTop(), getPaddingRight(), mPaddingBottom = density.dip2px(20));
            } else {
                mPaddingTop = getPaddingTop();
                mPaddingBottom = getPaddingBottom();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            setPadding(getPaddingLeft(), 0, getPaddingRight(), 0);
        } else {
            setPadding(getPaddingLeft(), mPaddingTop, getPaddingRight(), mPaddingBottom);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {
        mRefreshKernel = kernel;
        mRefreshKernel.requestDrawBackgoundForHeader(mBackgroundColor);
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
    }

    @Override
    public void onPullingDown(float percent, int offset, int headHeight, int extendHeight) {
        mArrowView.setImageResource(getArrowViewResourceId((int) (percent * 20)));
        LayoutParams arrowParams = (LayoutParams) mArrowView.getLayoutParams();
        arrowParams.height = (int) (percent * 100);
        arrowParams.width = (int) (percent * 100);
        mArrowView.setLayoutParams(arrowParams);

        LayoutParams progressParams = (LayoutParams) mProgressView.getLayoutParams();
        progressParams.height = offset;
        progressParams.width = offset;
        mProgressView.setLayoutParams(progressParams);
        mProgressDrawable.setSizeParameters(arrowParams.width, arrowParams.height, percent * 22, 2.0f, 10, 5);
        mProgressView.setImageDrawable(mProgressDrawable);
    }

    private int getArrowViewResourceId(int offset) {
        return HEADER_VIEW_IDS.get(offset % 40);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReleasing(float percent, int offset, int headHeight, int extendHeight) {
        if (percent >= 1) {
            LayoutParams arrowParams = (LayoutParams) mArrowView.getLayoutParams();
            arrowParams.height = mArrowView.getMaxHeight();
            arrowParams.width = mArrowView.getMaxHeight();
            mArrowView.setLayoutParams(arrowParams);
            mArrowView.setImageResource(R.mipmap.pull_to_refresh_60);
        }
    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int headHeight, int extendHeight) {
        if (mProgressDrawable != null) {
            mProgressDrawable.start();
        } else {
            Drawable drawable = mProgressView.getDrawable();
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).start();
            } else {
                mProgressView.animate().rotation(36000).setDuration(100000);
            }
        }
    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        if (mProgressDrawable != null) {
            mProgressDrawable.stop();
        } else {
            Drawable drawable = mProgressView.getDrawable();
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).stop();
            } else {
                mProgressView.animate().rotation(0).setDuration(300);
            }
        }
        mProgressView.setVisibility(GONE);
        //延迟500毫秒之后再弹回
        return mFinishDuration;
    }

    @Override
    @Deprecated
    public void setPrimaryColors(@ColorInt int... colors) {
        if (colors.length > 0) {
            if (!(getBackground() instanceof BitmapDrawable)) {
                setPrimaryColor(colors[0]);
            }
        }
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return mSpinnerStyle;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case PullDownToRefresh:
                mArrowView.setVisibility(VISIBLE);
                mProgressView.setVisibility(GONE);
                break;
            case Refreshing:
                mProgressView.setVisibility(VISIBLE);
                mArrowView.setVisibility(VISIBLE);
                break;
            case ReleaseToRefresh:
                mProgressView.setVisibility(GONE);
                mArrowView.setVisibility(VISIBLE);
                break;
            case Loading:
                mArrowView.setVisibility(GONE);
                mProgressView.setVisibility(GONE);
                break;
            default:
                break;
        }
    }

    public AlphaRefreshHeader setProgressBitmap(Bitmap bitmap) {
        mProgressDrawable = null;
        mProgressView.setImageBitmap(bitmap);
        return this;
    }

    public AlphaRefreshHeader setProgressDrawable(Drawable drawable) {
        mProgressDrawable = null;
        mProgressView.setImageDrawable(drawable);
        return this;
    }

    public AlphaRefreshHeader setProgressResource(@DrawableRes int resId) {
        mProgressDrawable = null;
        mProgressView.setImageResource(resId);
        return this;
    }

    public AlphaRefreshHeader setArrowBitmap(Bitmap bitmap) {
        mArrowDrawable = null;
        mArrowView.setImageBitmap(bitmap);
        return this;
    }

    public AlphaRefreshHeader setArrowDrawable(Drawable drawable) {
        mArrowDrawable = null;
        mArrowView.setImageDrawable(drawable);
        return this;
    }

    public AlphaRefreshHeader setArrowResource(@DrawableRes int resId) {
        mArrowDrawable = null;
        mArrowView.setImageResource(resId);
        return this;
    }

    public AlphaRefreshHeader setPrimaryColor(@ColorInt int primaryColor) {
        setBackgroundColor(mBackgroundColor = primaryColor);
        if (mRefreshKernel != null) {
            mRefreshKernel.requestDrawBackgoundForHeader(mBackgroundColor);
        }
        return this;
    }

    public AlphaRefreshHeader setSpinnerStyle(SpinnerStyle style) {
        this.mSpinnerStyle = style;
        return this;
    }

    public ImageView getArrowView() {
        return mArrowView;
    }

    public ImageView getProgressView() {
        return mProgressView;
    }
}
