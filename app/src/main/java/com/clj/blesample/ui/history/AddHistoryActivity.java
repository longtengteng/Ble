package com.clj.blesample.ui.history;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clj.blesample.R;
import com.clj.blesample.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*添加用药记录*/
public class AddHistoryActivity extends BaseActivity {
    @BindView(R.id.btnLeft)
    ImageView btnLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_time)
    EditText etTime;
    @BindView(R.id.tv_add)
    TextView tvAdd;

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_addhistory);
        ButterKnife.bind(this);
    }

    @Override
    protected void processLogic() {
        tvTitle.setText("添加用药记录");
    }

    @OnClick({R.id.btnLeft, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLeft:
                finish();
                break;
            case R.id.tv_add:

                break;
        }
    }
}
