package com.clj.blesample.base.z.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liuniukeji.singemall.R;
import com.liuniukeji.singemall.base.z.mvp.BasePresenterImpl;
import com.liuniukeji.singemall.base.z.mvp.BaseView;
import com.liuniukeji.singemall.util.utilcode.LogUtils;
import com.liuniukeji.singemall.util.utilcode.ToastUtils;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author
 * @Copyright Copyright (c) 山东六牛网络科技有限公司 保留�?有版�?(https://www.liuniukeji.com)
 * @Date
 * @CreateBy Android Studio
 * @ModifiedBy
 */

public abstract class MVPListBaseFragment<V extends BaseView, T extends BasePresenterImpl<V>, L> extends Fragment implements BaseView, BaseQuickAdapter.OnItemClickListener {
    public T mPresenter;
    private Unbinder binder;
    private int BARCOLOR = R.color.colorPrimary;

    public int getBarColor() {
        return BARCOLOR;
    }

    public void setBarColor(int BARCOLOR) {
        this.BARCOLOR = BARCOLOR;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getInstance(this, 1);
        mPresenter.attachView((V) this);
    }

    /**
     * 打开一个Activity 默认 不关闭当前activity
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle ex) {
        Intent intent = new Intent(getContext(), clz);
        if (ex != null) {
            intent.putExtras(ex);
        }
        startActivity(intent);
        if (isCloseCurrentActivity) {
            ((Activity) getContext()).finish();
        }
    }


    public void showToast(String msg) {
        ToastUtils.showShort(msg);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        if (getLayoutId() != 0) {
            view = inflater.inflate(getLayoutId(), container, false);
        } else {
            view = super.onCreateView(inflater, container, savedInstanceState);
            Log.i("init", "may not set layout by onCreateView()");
        }
        binder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onFirstIn(savedInstanceState);
        init();
    }

    private void init() {
        initView_Bindings();
    }


    /**
     * @return layout res id , default 0 if setLayout by other way
     */
    protected abstract int getLayoutId();

    /**
     * called by onCreate()
     *
     * @param savedInstanceState
     */
    protected abstract void onFirstIn(Bundle savedInstanceState);

    /**
     * init and binding / setting
     */
    protected abstract void initView_Bindings();


    /**
     * called by onResume()
     */
    protected abstract void onReady();

    /**
     * called by onPause
     */
    protected abstract void onPaused();

    /**
     * called by onDestroy
     */
    protected abstract void onDestroyed();


    @Override
    public void onResume() {
        super.onResume();
        onReady();
    }

    @Override
    public void onPause() {
        super.onPause();
        onPaused();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.detachView();
        }
        onDestroyed();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binder != null) {
            binder.unbind();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        onSaveStateData(outState);
    }


    /**
     * 保存数据回调
     *
     * @param outState
     */
    private void onSaveStateData(Bundle outState) {
    }


    private BaseQuickAdapter<L, BaseViewHolder> adapter;

    public BaseQuickAdapter<L, BaseViewHolder> getAdapter() {
        if (adapter == null) {
            LogUtils.d("must call bindlist() first");
        }
        return adapter;
    }


    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onFailed(String msg) {

    }

    @Override
    public void onMessage(String msg) {

    }

    public <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void bindList(final RecyclerView recyclerView, int item_layout_id, List<L> datas, RecyclerView.LayoutManager layoutManager) {
        adapter = new BaseQuickAdapter<L, BaseViewHolder>(item_layout_id, datas) {
            @Override
            protected void convert(BaseViewHolder helper, L item) {
                convertView(recyclerView, helper, item);
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setOnItemClickListener(this);
    }

    /**
     * 视图绑定
     *
     * @param recyclerView
     * @param helper
     * @param t
     */
    protected abstract void convertView(RecyclerView recyclerView, BaseViewHolder helper, L t);


    public void bindList(int recyclerViewResId, int item_layout_id, List<L> datas, RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView = getView().findViewById(recyclerViewResId);
        bindList(recyclerView, item_layout_id, datas, layoutManager);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
