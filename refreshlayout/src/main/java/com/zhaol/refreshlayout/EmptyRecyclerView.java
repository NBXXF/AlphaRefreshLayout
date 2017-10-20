package com.zhaol.refreshlayout;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


/**
 * Description
 * Company Beijing icourt
 * author  lu.zhao  E-mail:zhaolu@icourt.cc
 * date createTime：17/9/21
 * version 1.0.2
 */

public class EmptyRecyclerView extends FrameLayout {
    private View emptyView;
    private FrameLayout emptyParentFrameLayout;
    /**
     * 空布局icon
     */
    private ImageView emptyIconIv;
    /**
     * 空布局内容
     */
    private TextView emptyContentTv;
    private LinearLayout emptyLayout;
    private RecyclerView recyclerView;
    private View rootView;
    private OnEmptyViewClickListener onEmptyViewClickListener;
    final private RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    public EmptyRecyclerView(Context context) {
        super(context);
        initEmptyView(context);
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initEmptyView(context);
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initEmptyView(context);
    }

    public void setOnEmptyViewClickListener(OnEmptyViewClickListener onEmptyViewClickListener) {
        this.onEmptyViewClickListener = onEmptyViewClickListener;
    }

    public View getEmptyView() {
        return emptyView;
    }

    public void setEmptyView(Context context, @LayoutRes int id) {
        emptyParentFrameLayout.removeAllViews();
        emptyView = View.inflate(context, id, emptyParentFrameLayout);
    }

    public void setEmptyView(View view) {
        emptyParentFrameLayout.removeAllViews();
        emptyView = view;
        emptyParentFrameLayout.addView(emptyView);
    }

    /**
     * 初始化view
     *
     * @param context
     */
    private void initEmptyView(Context context) {
        rootView = LayoutInflater.from(context).inflate(R.layout.custom_recyclerview_layout, this);
        recyclerView = rootView.findViewById(R.id.parent_recyclerview);
        emptyParentFrameLayout = rootView.findViewById(R.id.empty_parent_view);

        setEmptyView(context, R.layout.refresh_empty_view);
        emptyIconIv = emptyView.findViewById(R.id.contentEmptyImage);
        emptyContentTv = emptyView.findViewById(R.id.contentEmptyText);
        emptyLayout = emptyView.findViewById(R.id.empty_layout);
        emptyView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEmptyViewClickListener != null) {
                    onEmptyViewClickListener.onEmptyViewClick();
                }
            }
        });
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * 添加分割线
     *
     * @param decor
     */
    public void addItemDecoration(RecyclerView.ItemDecoration decor) {
        if (recyclerView != null) {
            recyclerView.addItemDecoration(decor);
        }
    }

    /**
     * 添加分割线
     *
     * @param decor
     * @param index
     */
    public void addItemDecoration(RecyclerView.ItemDecoration decor, int index) {
        if (recyclerView != null) {
            recyclerView.addItemDecoration(decor, index);
        }
    }

    /**
     * 添加item动画
     *
     * @param animator
     */
    public void setItemAnimator(RecyclerView.ItemAnimator animator) {
        if (recyclerView != null) {
            recyclerView.setItemAnimator(animator);
        }
    }

    /**
     * 设置manager
     *
     * @param layoutManager
     */
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if (recyclerView != null) {
            recyclerView.setLayoutManager(layoutManager);
        }
    }

    /**
     * 设置adapter
     *
     * @param adapter
     */
    public void setAdapter(RecyclerView.Adapter adapter) {
        if (recyclerView != null) {
            recyclerView.setAdapter(adapter);
            adapter.registerAdapterDataObserver(observer);
            checkIfEmpty();
        }
    }

    /**
     * 是否显示emptyview
     */
    public void checkIfEmpty() {
        if (emptyParentFrameLayout != null && recyclerView.getAdapter() != null) {
            boolean emptyViewVisible = recyclerView.getAdapter() == null || recyclerView.getAdapter().getItemCount() <= 0;
            emptyParentFrameLayout.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            recyclerView.setVisibility(emptyViewVisible ? GONE : VISIBLE);
        }
    }

    /**
     * 根据数据是否为空，判断是否显示空页面。
     *
     * @param result 用来判断是否要显示空页面的列表
     */
    public void enableEmptyView(List result) {
        if (emptyParentFrameLayout != null) {
            boolean emptyViewVisible = result == null || result.isEmpty();
            emptyParentFrameLayout.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            recyclerView.setVisibility(emptyViewVisible ? GONE : VISIBLE);
        }
    }

    public void setNoticeEmpty(@DrawableRes int id, @NonNull CharSequence text) {
        if (emptyIconIv == null) {
            return;
        }
        if (emptyContentTv == null) {
            return;
        }
        emptyIconIv.setImageResource(id);
        emptyContentTv.setText(text);
    }

    public void setNoticeEmpty(@DrawableRes int resId, @StringRes int strId) {
        if (emptyIconIv == null) {
            return;
        }
        if (emptyContentTv == null) {
            return;
        }
        emptyIconIv.setImageResource(resId);
        emptyContentTv.setText(getResources().getString(strId));
    }

    /**
     * 设置空布局icon
     *
     * @param id
     */
    public void setEmptyIcon(@DrawableRes int id) {
        if (emptyIconIv == null) {
            return;
        }
        emptyIconIv.setImageResource(id);
    }

    /**
     * 设置空布局内容
     *
     * @param text
     */
    public void setEmptyContent(@NonNull CharSequence text) {
        if (emptyContentTv == null) {
            return;
        }
        emptyContentTv.setText(text);
    }

    /**
     * 设置空布局内容
     *
     * @param id
     */
    public void setEmptyContent(@StringRes int id) {
        if (emptyContentTv == null) {
            return;
        }
        emptyContentTv.setText(getResources().getString(id));
    }

    /**
     * emptyview点击监听
     */
    public interface OnEmptyViewClickListener {
        /**
         * 空图标点击事件
         */
        void onEmptyViewClick();
    }
}
