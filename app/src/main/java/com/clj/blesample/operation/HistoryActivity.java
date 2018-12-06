package com.clj.blesample.operation;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.clj.blesample.R;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.utils.HexUtil;

public class HistoryActivity extends AppCompatActivity {
    private BleDevice bleDevice;
    Button btn, btn_start;
    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        bleDevice = getIntent().getParcelableExtra("KEY_DATA");
        btn = findViewById(R.id.btn);
        btn_start = findViewById(R.id.btn_start);
        textView = findViewById(R.id.tv_electric);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BleManager.getInstance().write(
                        bleDevice,
                        //     characteristic.getService().getUuid().toString(),
                        //    characteristic.getUuid().toString(),
                        "0000fff0-0000-1000-8000-00805f9b34fb",
                        "0000fff4-0000-1000-8000-00805f9b34fb",
                        HexUtil.hexStringToBytes("F80100000000000000F9"),
                        new BleWriteCallback() {

                            @Override
                            public void onWriteSuccess(final int current, final int total, final byte[] justWrite) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                               /*         addText(textView, "write success, current: " + current
                                                + " total: " + total
                                                + " justWrite: " + HexUtil.formatHexString(justWrite, true));*/
                                        addText(textView, "发送请求历史数据的命令: " + HexUtil.formatHexString(justWrite, true));

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
        btn.setText(getResources().getString(R.string.open_notification));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                            addText(textView, "notify success");
                                        }
                                    });
                                }

                                @Override
                                public void onNotifyFailure(final BleException exception) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            addText(textView, exception.toString());
                                        }
                                    });
                                }

                                @Override
                                public void onCharacteristicChanged(final byte[] data) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Toast.makeText(getApplicationContext(), data + "", Toast.LENGTH_SHORT).show();
                                            addText(textView, HexUtil.formatHexString(data, true));
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
            }
        });
    }

    private void addText(TextView textView, String content) {
        //  content = content.replace(" ", "");
        textView.setText(content);
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
