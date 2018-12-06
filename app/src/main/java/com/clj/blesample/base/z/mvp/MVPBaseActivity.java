package com.clj.blesample.base.z.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.liuniukeji.singemall.util.utilcode.ToastUtils;

import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public abstract class MVPBaseActivity<V extends BaseView, T extends BasePresenterImpl<V>> extends AppCompatActivity implements BaseView {
    public T mPresenter;
    private Unbinder binder;
    private InputMethodManager inputMethodManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getInstance(this, 1);
        mPresenter.attachView((V) this);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
       // LnSecureUtils.getDefault().regist(getApplicationContext(), getPackageName(), AppUtils.getAppName(this), URLs.APIHTTP);
        init();
        onFirstIn(savedInstanceState);
        initView_Bindings();
    }


    private void init() {
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        } else {
            Log.i("init", "may not set layout by setContentView()");
        }
        try {//打印异常
            binder = ButterKnife.bind(this);
        } catch (Exception e) {
            Log.e("init", "注解出错");
            e.printStackTrace();
        }

    }

    public void showToast(String msg) {
        ToastUtils.showShort(msg);
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
        Intent intent = new Intent(this, clz);
        if (ex != null) {
            intent.putExtras(ex);
        }
        startActivity(intent);
        if (isCloseCurrentActivity) {
            ((Activity) getContext()).finish();
        }
    }

    protected void hideSoftKeyboard(View view) {
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void showSoftKeyboard(View view) {
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * want a layout res id
     *
     * @return @return layout res id default 0 if setLayout by other way
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
     * called by onDestroy
     */
    protected abstract void onDestroyed();

    /**
     * called by onPause
     */
    protected abstract void onPaused();

    @Override
    protected void onResume() {
        super.onResume();
        onReady();
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPaused();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (binder != null) {
            binder.unbind();
        }
        onDestroyed();
    }


    @Override
    public Context getContext() {
        return this;
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
        }
        return null;
    }
}
