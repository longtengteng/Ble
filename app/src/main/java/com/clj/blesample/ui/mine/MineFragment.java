package com.clj.blesample.ui.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.clj.blesample.R;
import com.clj.blesample.base.BaseFragment;

import butterknife.ButterKnife;

public class MineFragment extends BaseFragment {
    @Override
    public View getLayout(LayoutInflater inflater) {
        View rootView = inflater.inflate(R.layout.fragment_shop, null);
        return rootView;
    }

    @Override
    protected void processLogic() {

    }
}
