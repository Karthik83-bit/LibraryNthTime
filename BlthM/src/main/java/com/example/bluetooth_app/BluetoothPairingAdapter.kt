package com.example.bluetooth_app

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BluetoothPairingAdapter:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action=intent.action
        if(action==BluetoothDevice.ACTION_BOND_STATE_CHANGED){
            val bluetoothDevice:BluetoothDevice?=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
            if(bluetoothDevice?.bondState==BluetoothDevice.BOND_BONDED){
                Log.d("msg","bluetooth bonded")
            }
            if(bluetoothDevice?.bondState==BluetoothDevice.BOND_BONDING){
                Log.d("msg","device is getting bonded")


            }
            if(bluetoothDevice?.bondState==BluetoothDevice.BOND_NONE){
                Log.d("msg","bluetoothdevice is getting unbonded")
            }
        }
    }
}