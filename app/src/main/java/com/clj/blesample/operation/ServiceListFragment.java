package com.clj.blesample.operation;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.clj.blesample.R;
import com.clj.fastble.BleManager;
import com.clj.fastble.data.BleDevice;

import java.util.ArrayList;
import java.util.List;

/*服务列表*/
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class ServiceListFragment extends Fragment {

    private TextView txt_name, txt_mac;
    private ResultAdapter mResultAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service_list, null);
        initView(v);
        showData();
        return v;
    }

    private void initView(View v) {
        txt_name = (TextView) v.findViewById(R.id.txt_name);
        txt_mac = (TextView) v.findViewById(R.id.txt_mac);

        mResultAdapter = new ResultAdapter(getActivity());
        ListView listView_device = (ListView) v.findViewById(R.id.list_service);
        listView_device.setAdapter(mResultAdapter);
        listView_device.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BluetoothGattService service = mResultAdapter.getItem(position);
                if (service.getUuid().toString().contains("0000180f")) {
                    //查看电量
                    Intent intent = new Intent(getActivity(), ElectricActivity.class);
                    intent.putExtra("service", service);
                    intent.putExtra("KEY_DATA", ((OperationActivity) getActivity()).getBleDevice());
                    startActivity(intent);
                } else if (service.getUuid().toString().contains("0000fff0")) {
                    //温度计的操作
                    ((OperationActivity) getActivity()).setBluetoothGattService(service);
                    ((OperationActivity) getActivity()).changePage(1);
                } else {
                    Toast.makeText(getActivity(), "新增服务，请与开发人员沟通", Toast.LENGTH_LONG).show();
         /*           ((OperationActivity) getActivity()).setBluetoothGattService(service);
                    ((OperationActivity) getActivity()).changePage(1);*/
                }


            }
        });
    }

    private void showData() {
        BleDevice bleDevice = ((OperationActivity) getActivity()).getBleDevice();
        String name = bleDevice.getName();
        String mac = bleDevice.getMac();
        BluetoothGatt gatt = BleManager.getInstance().getBluetoothGatt(bleDevice);

        txt_name.setText(String.valueOf(getActivity().getString(R.string.name) + name));
        txt_mac.setText(String.valueOf(getActivity().getString(R.string.mac) + mac));

        mResultAdapter.clear();
        for (BluetoothGattService service : gatt.getServices()) {
            if (service.getUuid().toString().contains("0000180f") || service.getUuid().toString().contains("0000fff0")) {
                mResultAdapter.addResult(service);
            }

        }
        mResultAdapter.notifyDataSetChanged();
    }

    private class ResultAdapter extends BaseAdapter {

        private Context context;
        private List<BluetoothGattService> bluetoothGattServices;

        ResultAdapter(Context context) {
            this.context = context;
            bluetoothGattServices = new ArrayList<>();
        }

        void addResult(BluetoothGattService service) {
            bluetoothGattServices.add(service);
        }

        void clear() {
            bluetoothGattServices.clear();
        }

        @Override
        public int getCount() {
            return bluetoothGattServices.size();
        }

        @Override
        public BluetoothGattService getItem(int position) {
            if (position > bluetoothGattServices.size())
                return null;
            return bluetoothGattServices.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                convertView = View.inflate(context, R.layout.adapter_service, null);
                holder = new ViewHolder();
                convertView.setTag(holder);
                holder.txt_title = (TextView) convertView.findViewById(R.id.txt_title);
                holder.txt_uuid = (TextView) convertView.findViewById(R.id.txt_uuid);
                holder.txt_type = (TextView) convertView.findViewById(R.id.txt_type);
                holder.ll_service = convertView.findViewById(R.id.ll_service);
            }

            BluetoothGattService service = bluetoothGattServices.get(position);
            String uuid = service.getUuid().toString();
            if (uuid.contains("0000180f")) {
                holder.txt_title.setText("温度计电量显示");
                holder.txt_uuid.setText(uuid);
                holder.txt_type.setText("电量");
            } else if (uuid.contains("0000fff0")) {
                holder.txt_title.setText("温度计的操作");
                holder.txt_uuid.setText(uuid);
                holder.txt_type.setText("具体的操作");
            } else {
                holder.txt_title.setText("温度计的操作");
                holder.txt_uuid.setText(uuid);
                holder.txt_type.setText("具体的操作");
            }
            return convertView;
        }

        class ViewHolder {
            TextView txt_title;
            TextView txt_uuid;
            TextView txt_type;
            LinearLayout ll_service;
        }
    }

}
