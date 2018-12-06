package com.clj.blesample.operation;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.clj.blesample.R;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleReadCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.utils.HexUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemperatureActivity extends AppCompatActivity {
    private BleDevice bleDevice;
    private BluetoothGattCharacteristic characteristic;
    private int charaProp;
    Button btn;
    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temper);
        bleDevice = getIntent().getParcelableExtra("KEY_DATA");
        characteristic = getIntent().getParcelableExtra("characteristic");
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.tv_electric);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
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
        content = content.replace(" ", "");
        if (content.length() < 20) {
            textView.append(content);
            textView.append("\n");
            int offset = textView.getLineCount() * textView.getLineHeight();
            if (offset > textView.getHeight()) {
                textView.scrollTo(0, offset - textView.getHeight());
            }
            return;
        }
        if (isHexNumber(content)) {
            String high = content.substring(14, 16);
            String low = content.substring(16, 18);
            int a = Integer.valueOf(high, 16);
            int b = Integer.valueOf(low, 16);
            content = "当前温度为：" + a + "" + "." + b + "摄氏度";
            textView.append(content);
            textView.append("\n");
            int offset = textView.getLineCount() * textView.getLineHeight();
            if (offset > textView.getHeight()) {
                textView.scrollTo(0, offset - textView.getHeight());
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


    public BleDevice getBleDevice() {
        return bleDevice;
    }


    public BluetoothGattCharacteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(BluetoothGattCharacteristic characteristic) {
        this.characteristic = characteristic;
    }

    public int getCharaProp() {
        return charaProp;
    }

    public void setCharaProp(int charaProp) {
        this.charaProp = charaProp;
    }

}
