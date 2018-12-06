package com.clj.blesample.operation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.clj.blesample.R;
import com.clj.blesample.util.SPUtils;
import com.clj.blesample.util.Utils;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.utils.HexUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/*温度计根据固定的characteristic进行的基础操作*/
public class SimpleOperActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView btnLeft;
    LinearLayout ll_electric, ll_clock, ll_temperature, ll_history, ll_clear_history, ll_look_history;
    private BleDevice bleDevice;
    TextView tv_nodify, tv_show;
    Button btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpleoper);
        btnLeft = findViewById(R.id.btnLeft);
        btnLeft.setOnClickListener(this);
        ll_electric = findViewById(R.id.ll_electric);
        ll_electric.setOnClickListener(this);
        ll_clock = findViewById(R.id.ll_clock);
        ll_clock.setOnClickListener(this);
        ll_temperature = findViewById(R.id.ll_temperature);
        ll_temperature.setOnClickListener(this);
        ll_history = findViewById(R.id.ll_history);
        ll_history.setOnClickListener(this);
        ll_look_history = findViewById(R.id.ll_look_history);
        ll_look_history.setOnClickListener(this);
        ll_clear_history = findViewById(R.id.ll_clear_history);
        ll_clear_history.setOnClickListener(this);
        tv_nodify = findViewById(R.id.tv_nodify);
        tv_show = findViewById(R.id.tv_show);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);


        bleDevice = getIntent().getParcelableExtra("KEY_DATA");

    }

    Intent intent;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_look_history:
                intent = new Intent(this, GetHistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.btn:
                if (btn.getText().toString().equals(getResources().getString(R.string.open_notification))) {
                    btn.setText(getResources().getString(R.string.close_notification));
                    BleManager.getInstance().notify(
                            bleDevice,
                   /*         characteristic.getService().getUuid().toString(),
                            characteristic.getUuid().toString(),*/
                            "0000fff0-0000-1000-8000-00805f9b34fb",
                            "0000fff3-0000-1000-8000-00805f9b34fb",
                            new BleNotifyCallback() {
                                @Override
                                public void onNotifySuccess() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            addText(tv_nodify, "notify success");
                                        }
                                    });
                                }

                                @Override
                                public void onNotifyFailure(final BleException exception) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            addText(tv_nodify, exception.toString());
                                        }
                                    });
                                }

                                @Override
                                public void onCharacteristicChanged(final byte[] data) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Toast.makeText(getApplicationContext(), data + "", Toast.LENGTH_SHORT).show();
                                            addText(tv_nodify, HexUtil.formatHexString(data, true));
                                        }
                                    });
                                }
                            });
                } else {
                    btn.setText(getResources().getString(R.string.open_notification));
                    BleManager.getInstance().stopNotify(
                            bleDevice,
                            "0000fff0-0000-1000-8000-00805f9b34fb",
                            "0000fff3-0000-1000-8000-00805f9b34fb");
                }
                break;
            case R.id.btnLeft:
                finish();
                break;
            case R.id.ll_electric:
                intent = new Intent(this, ElectricActivity.class);
                intent.putExtra("KEY_DATA", bleDevice);
                startActivity(intent);
                break;
            case R.id.ll_clock:
                intent = new Intent(this, ClockActivity.class);
                intent.putExtra("KEY_DATA", bleDevice);
                startActivity(intent);
                break;
            case R.id.ll_temperature:
                Intent intent = new Intent(this, TemperatureActivity.class);
                intent.putExtra("KEY_DATA", bleDevice);
                startActivity(intent);
                break;
            case R.id.ll_history:
                intent = new Intent(this, HistoryActivity.class);
                intent.putExtra("KEY_DATA", bleDevice);
                startActivity(intent);
                break;
            case R.id.ll_clear_history:
                /*if (list == null)
                    list = new ArrayList<>();
                list.add("aa");
                list.add("bb");
                list.add("cc");
                Toast.makeText(getApplicationContext(),list.toString(),Toast.LENGTH_SHORT).show();*/
                if (list == null)
                    list = new ArrayList<>();
                list.add("aa");
                Toast.makeText(getApplicationContext(), list.toString(), Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ClearHistoryActivity.class);
                intent.putExtra("KEY_DATA", bleDevice);
                startActivity(intent);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        Gson gson = new Gson();
        String data = gson.toJson(list);
        SPUtils.getInstance().put("listHistory", data);
        super.onDestroy();

    }

    String data = SPUtils.getInstance().getString("listHistory", "");
    Gson gson = new Gson();
    Type listType = new TypeToken<List<String>>() {
    }.getType();

    List<String> list = gson.fromJson(data, listType);


    private void addText(TextView textView, String content) {
        content = content.replace(" ", "");
        if (content.length() < 20) {
            return;
        }

        if (isHexNumber(content)) {
            String head = content.substring(0, 2);
            if (head.equalsIgnoreCase("F3")) {
                if (list == null)
                    list = new ArrayList<>();
                String year_head, year_low, mMonth, mDay, mHour, mMinute, mSecond;
                String time;
                year_head = content.substring(0, 2);
                year_low = content.substring(2, 4);
                mMonth = content.substring(4, 6);
                mDay = content.substring(6, 8);
                mHour = content.substring(8, 10);
                mMinute = content.substring(10, 12);
                mSecond = content.substring(12, 14);


                int year1 = Integer.valueOf(year_head, 16);
                int year2 = Integer.valueOf(year_low, 16);
                int month = Integer.valueOf(mMonth, 16);
                int day = Integer.valueOf(mDay, 16);
                int hour = Integer.valueOf(mHour, 16);
                int minute = Integer.valueOf(mMinute, 16);
                int second = Integer.valueOf(mSecond, 16);
                time = year1 + "" + year2 + "年" + month + "月" + day + "日" + hour + "时" + minute + "分" + second + "秒";
                list.add(time);
                return;

            }
            if (head.equalsIgnoreCase("F5")) {
                if (list == null)
                    Toast.makeText(getApplicationContext(), "设备请联系技术人员", Toast.LENGTH_SHORT).show();
                String high = content.substring(2, 4);
                String low = content.substring(4, 6);
                int a = Integer.valueOf(high, 16);
                int b = Integer.valueOf(low, 16);
                content = "温度为：" + a + "" + "." + b + "摄氏度";
                list.add(content);
                return;
            }
            if (head.equalsIgnoreCase("F1")) {
                String high = content.substring(14, 16);
                String low = content.substring(16, 18);
                int a = Integer.valueOf(high, 16);
                int b = Integer.valueOf(low, 16);
                content = "当前温度为：" + a + "" + "." + b + "摄氏度";
                textView.setText(content);
            } else {
                tv_show.setText(content);
             /*   textView.append(content);
                textView.append("\n");
                int offset = textView.getLineCount() * textView.getLineHeight();
                if (offset > textView.getHeight()) {
                    textView.scrollTo(0, offset - textView.getHeight());
                }*/
            }

        }
    }

    //十六进制
    private static boolean isHexNumber(String str) {
        boolean flag = false;
        String regex = "^[A-Fa-f0-9]+$";
        if (str.matches(regex)) {
            return true;
        }
        return flag;
    }

}
