package com.clj.blesample.base.z.helper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author zhugefubin tel:18615680590;
 * @Copyright Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date 2018-04-14 08:38
 * @CreateBy Android Studio
 * @ModifiedBy
 */

public abstract class ListActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener {

    private <L> void bindList(final RecyclerView recyclerView, int item_layout_id, List<L> datas, RecyclerView.LayoutManager layoutManager) {
        BaseQuickAdapter<L,BaseViewHolder> adapter = new BaseQuickAdapter<L,BaseViewHolder>(item_layout_id, datas) {

            @Override
            protected void convert(BaseViewHolder helper, L item) {
                ListActivity.this.convert(recyclerView, helper, item);
            }
        };
//        recyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Context getContext() {
        return this;
    }

    /**
     * 视图和数据绑定
     *
     * @param recyclerView
     * @param helper
     * @param t
     */
    protected abstract <L> void convert(RecyclerView recyclerView, BaseViewHolder helper, L t);

    private <L> void bindList(int recyclerViewResId, int item_layout_id, List<L> datas, RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView = findViewById(recyclerViewResId);
        bindList(recyclerView, item_layout_id, datas, layoutManager);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
