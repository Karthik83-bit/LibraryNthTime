package com.example.bluetooth_app

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BluetoothReciever:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("kjj","hello")
        val action=intent?.action
        if(action==BluetoothAdapter.ACTION_STATE_CHANGED){
            when(intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,BluetoothAdapter.ERROR)){
                BluetoothAdapter.STATE_ON->{
                    Log.d("msg","StateOn")
                }
                BluetoothAdapter.STATE_OFF->{
                    Log.d("msg","StateOFF")
                }
                BluetoothAdapter.STATE_TURNING_OFF->{
                    Log.d("msg","StateTurningOff")
                }
                BluetoothAdapter.STATE_TURNING_ON->{
                    Log.d("msg","StateTurningOn")
                }
            }
        }

    }

}
