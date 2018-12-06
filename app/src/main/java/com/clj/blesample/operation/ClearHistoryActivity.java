package com.clj.blesample.operation;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.clj.blesample.R;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.utils.HexUtil;

import java.util.Calendar;

public class ClearHistoryActivity extends AppCompatActivity {
    private BleDevice bleDevice;
    Button btn;
    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clearhistory);

        bleDevice = getIntent().getParcelableExtra("KEY_DATA");
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.tv_clear);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BleManager.getInstance().write(
                        bleDevice,
                        //     characteristic.getService().getUuid().toString(),
                        //    characteristic.getUuid().toString(),
                        "0000fff0-0000-1000-8000-00805f9b34fb",
                        "0000fff4-0000-1000-8000-00805f9b34fb",
                        HexUtil.hexStringToBytes(getClearHistory()),
                        new BleWriteCallback() {

                            @Override
                            public void onWriteSuccess(final int current, final int total, final byte[] justWrite) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                               /*         addText(textView, "write success, current: " + current
                                                + " total: " + total
                                                + " justWrite: " + HexUtil.formatHexString(justWrite, true));*/
                                        addText(textView, "清除历史数据成功: " + HexUtil.formatHexString(justWrite, true));

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

    private String getClearHistory() {
        return "F40000000000000000F4";
    }

    private void addText(TextView textView, String content) {
        textView.setText(content);
    }
}
