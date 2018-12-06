package com.clj.blesample.operation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.clj.blesample.R;
import com.clj.blesample.util.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetHistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
  //  List<String> history = new ArrayList<>();
    GetHistoryAdapter adapter;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gethistory);
        recyclerview = findViewById(R.id.rv_getHistory);
        String data = SPUtils.getInstance().getString("listHistory", "");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>() {
        }.getType();

        List<String> list = gson.fromJson(data, listType);
        if (list == null)
            list = new ArrayList<>();
        adapter = new GetHistoryAdapter(this, list);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
