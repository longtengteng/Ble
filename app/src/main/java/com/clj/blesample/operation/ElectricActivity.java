package com.clj.blesample.operation;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.clj.blesample.R;
import com.clj.blesample.comm.Observer;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleReadCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.utils.HexUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ElectricActivity extends AppCompatActivity {
    private BleDevice bleDevice;
    Button btn;
    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric);
        bleDevice = getIntent().getParcelableExtra("KEY_DATA");
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.tv_electric);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BleManager.getInstance().read(
                        bleDevice,
                        //     characteristic.getService().getUuid().toString(),
                        //    characteristic.getUuid().toString(),
                        "0000180f-0000-1000-8000-00805f9b34fb",
                        "00002a19-0000-1000-8000-00805f9b34fb",
                        new BleReadCallback() {

                            @Override
                            public void onReadSuccess(final byte[] data) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        addText(textView, HexUtil.formatHexString(data, true));
                                    }
                                });
                            }

                            @Override
                            public void onReadFailure(final BleException exception) {
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

    private void addText(TextView textView, String content) {
        if (isHexNumber(content)) {
            int d = Integer.valueOf(content, 16);
            textView.setText("剩余" + d + "%电量");
        } else {
            textView.setText(content);
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
