package com.clj.blesample.base.z.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuniukeji.singemall.util.utilcode.ToastUtils;

import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public abstract class MVPBaseFragment<V extends BaseView, T extends BasePresenterImpl<V>> extends Fragment implements BaseView {
    public T mPresenter;
    //   private SuperTextView actionBar;
    private Unbinder binder;


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
        //ImmersionBar.with(this).destroy();
        onDestroyed();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
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


    @Override
    public Context getContext() {
        return super.getContext();
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


}
