package com.example.bluetooth_app

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BluetoothDiscoverable:BroadcastReceiver() {
    override fun onReceive(p0: Context?, intent: Intent?) {
        val action=intent?.action
        if(action==(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)){
            when(intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE,BluetoothAdapter.ERROR)){
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE->{
                    Log.d("msg","Discoverability Enabled")
                }
                BluetoothAdapter.SCAN_MODE_CONNECTABLE->{
                    Log.d("msg","Discoverability Enabled.Able to ReceiveConnections")
                }
                BluetoothAdapter.SCAN_MODE_NONE->{
                    Log.d("msg","Discoverability Enabled.Unable to Receive Connections")
                }
                BluetoothAdapter.STATE_CONNECTED->{
                    Log.d("msg","connected")
                }
                BluetoothAdapter.STATE_DISCONNECTED->{
                    Log.d("msg","disconnected")
                }
            }
        }

    }
}