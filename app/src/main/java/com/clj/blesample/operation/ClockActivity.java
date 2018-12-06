package com.clj.blesample.operation;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.clj.blesample.R;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.utils.HexUtil;

import java.util.Calendar;

public class ClockActivity extends AppCompatActivity {
    private BleDevice bleDevice;
    Button btn;
    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        bleDevice = getIntent().getParcelableExtra("KEY_DATA");
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.tv_clock);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BleManager.getInstance().write(
                        bleDevice,
                        //     characteristic.getService().getUuid().toString(),
                        //    characteristic.getUuid().toString(),
                        "0000fff0-0000-1000-8000-00805f9b34fb",
                        "0000fff4-0000-1000-8000-00805f9b34fb",
                        HexUtil.hexStringToBytes(getTime()),
                        new BleWriteCallback() {

                            @Override
                            public void onWriteSuccess(final int current, final int total, final byte[] justWrite) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                               /*         addText(textView, "write success, current: " + current
                                                + " total: " + total
                                                + " justWrite: " + HexUtil.formatHexString(justWrite, true));*/
                                        addText(textView, "时间设置成功, 设备返回时间为: " + HexUtil.formatHexString(justWrite, true));

                                    }
                                });
                            }

                            @Override
                            public void onWriteFailure(final BleException exception) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        addText(textView, exception.toString());
                                    }
                                });
                            }
                        });
            }
        });
    }

    String mYear, mMonth, mDay, mHour, mMinute, mSecond;
    String time;
    long check;
    int year, month, day, hour, minute, second;

    private String getTime() {
        Calendar c = Calendar.getInstance();
        mYear = Integer.toHexString(c.get(Calendar.YEAR)); // 获取当前年份
        mMonth = Integer.toHexString(c.get(Calendar.MONTH) + 1); // 获取当前月份
        mDay = Integer.toHexString(c.get(Calendar.DAY_OF_MONTH));// 获取当日期
        mHour = Integer.toHexString(c.get(Calendar.HOUR_OF_DAY));//时
        mMinute = Integer.toHexString(c.get(Calendar.MINUTE));//分
        mSecond = Integer.toHexString(c.get(Calendar.SECOND));//秒
        if (mMonth.length() == 1) {
            mMonth = "0" + mMonth;
        }
        if (mDay.length() == 1) {
            mDay = "0" + mDay;
        }
        if (mHour.length() == 1) {
            mHour = "0" + mHour;
        }
        if (mMinute.length() == 1) {
            mMinute = "0" + mMinute;
        }
        if (mSecond.length() == 1) {
            mSecond = "0" + mSecond;
        }
        String year_next;
        year_next = mYear.substring(1, 3);
        check = Long.parseLong("F0", 16) + Long.parseLong("07", 16) + Long.parseLong(year_next, 16) + Long.parseLong(mMonth, 16) + Long.parseLong(mDay, 16) + Long.parseLong(mMinute, 16) + Long.parseLong(mHour, 16) + Long.parseLong(mSecond, 16);
        // Toast.makeText(getApplicationContext(), Long.toHexString(check), Toast.LENGTH_LONG).show();

        String check_get = String.valueOf(Long.toHexString(check));
        if (check_get.length() == 3) {
            check_get = check_get.substring(1, 3);
        }
        time = "f00" + mYear + mMonth + mDay + mHour + mMinute + mSecond + "00" + check_get;
//"F007E1070B10101D0027"
        return time.toUpperCase();
    }

    private void addText(TextView textView, String content) {
        textView.setText(content);
    }
}
