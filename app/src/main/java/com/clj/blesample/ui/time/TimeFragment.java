package com.clj.blesample.ui.time;

import android.view.LayoutInflater;
import android.view.View;

import com.clj.blesample.R;
import com.clj.blesample.base.BaseFragment;

public class TimeFragment extends BaseFragment{

    @Override
    public View getLayout(LayoutInflater inflater) {
        View rootView = inflater.inflate(R.layout.fragment_mine, null);
        return rootView;
    }

    @Override
    protected void processLogic() {

    }
}
