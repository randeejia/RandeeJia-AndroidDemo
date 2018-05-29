package com.randeejia.bluetooth

import android.bluetooth.BluetoothAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent


val REQUEST_ENABLE_BT = 1

var bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /**
         * 1.判断设备是否支持蓝牙
         * 2.判断蓝牙是否开启
         * 3.
         */
        if (bluetoothAdapter == null){
            // Device does not support Bluetooth

            return
        }

        if (!bluetoothAdapter.isEnabled){
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent,REQUEST_ENABLE_BT )
        }

    }
}
