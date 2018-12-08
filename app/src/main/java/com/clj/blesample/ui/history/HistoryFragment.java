package com.clj.blesample.ui.history;

import android.view.LayoutInflater;
import android.view.View;

import com.clj.blesample.R;
import com.clj.blesample.base.BaseFragment;

public class HistoryFragment extends BaseFragment{
    @Override
    public View getLayout(LayoutInflater inflater) {
        View rootView = inflater.inflate(R.layout.fragment_history, null);
        return rootView;
    }

    @Override
    protected void processLogic() {

    }

}
