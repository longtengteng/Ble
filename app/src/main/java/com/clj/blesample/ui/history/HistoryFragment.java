package com.clj.blesample.ui.history;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tu.circlelibrary.CirclePercentBar;
import com.clj.blesample.MainActivity;
import com.clj.blesample.R;
import com.clj.blesample.base.BaseFragment;
import com.clj.blesample.util.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HistoryFragment extends BaseFragment implements HistoryContract.View {
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.iv_history)
    ImageView ivHistory;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.circlepercent)
    CirclePercentBar circlepercent;
    Unbinder unbinder;
    HistoryContract.Present present;

    @Override
    public View getLayout(LayoutInflater inflater) {
        View rootView = inflater.inflate(R.layout.fragment_history, null);
        unbinder = ButterKnife.bind(this, rootView);
        present = new HistoryPresenter(context);
        present.attachView(this);
        return rootView;
    }

    @Override
    protected void processLogic() {
        /*是否开始搜索设备*/
        AlertDialog dialog1 = new AlertDialog.Builder(context).setTitle("").setMessage("是否搜索设备？").setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);

            }
        }).show();
        circlepercent.setPercentData(50, new DecelerateInterpolator());
        present.index_noconnected();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_head, R.id.iv_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:

                break;
            case R.id.iv_history:

                break;
        }
    }

    @Override
    public void index_noconnected(HistoryBean historyBean) {
        ImageLoader.loadHead(context, ivHead, historyBean.getHeader_pic());
        tvPhone.setText(historyBean.getName());
        tvTime.setText(historyBean.getTime_str());
        tvTemperature.setText(historyBean.getPower_num() + "");

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onError() {

    }
}
