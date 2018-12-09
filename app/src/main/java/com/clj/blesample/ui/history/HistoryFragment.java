package com.clj.blesample.ui.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tu.circlelibrary.CirclePercentBar;
import com.clj.blesample.R;
import com.clj.blesample.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HistoryFragment extends BaseFragment {
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

    @Override
    public View getLayout(LayoutInflater inflater) {
        View rootView = inflater.inflate(R.layout.fragment_history, null);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void processLogic() {
        circlepercent.setPercentData(50, new DecelerateInterpolator());
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
}
