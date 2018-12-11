package com.clj.blesample.ui.history;

import com.clj.blesample.base.BasePresenter;
import com.clj.blesample.base.BaseView;

import java.util.List;

public class HistoryContract {

    interface View extends BaseView {
        void index_noconnected(HistoryBean historyBean);

    }

    interface Present extends BasePresenter<View> {

        void index_noconnected();

    }
}
