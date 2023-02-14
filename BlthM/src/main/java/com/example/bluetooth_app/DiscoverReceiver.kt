package com.example.bluetooth_app

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

class DiscoverReceiver(val devices:MutableSet<BluetoothDevice>): BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("kjj","hello")
        val action=intent?.action
        if(action==BluetoothDevice.ACTION_FOUND){
            val bluetoothDevice:BluetoothDevice?=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
            Log.d("kjj","hello")
            if (bluetoothDevice != null) {
                devices.add(bluetoothDevice)
            }
            val listViewArray:ArrayList<BluetoothDevice>
            listViewArray=ArrayList()
            val setIterator=devices.iterator()
            setIterator.forEach {
                listViewArray.add(it)

            }
            val listAdapter:DeviceListAdapter= DeviceListAdapter(context,R.layout.device_adapetr_view,listViewArray)
        }
//        fun getDevices():ArrayList<BluetoothDevice>{
//            return devices
//        }
    }

}
