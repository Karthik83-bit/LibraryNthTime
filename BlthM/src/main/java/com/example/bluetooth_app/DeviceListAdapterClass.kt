package com.example.bluetooth_app

import android.R
import android.app.admin.DeviceAdminService
import android.bluetooth.BluetoothClass
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.*
import java.util.zip.Inflater


class DeviceListAdapter(
    context: Context,
    tvResourceId: Int,
    Devices: ArrayList<BluetoothDevice>
) :
    ArrayAdapter<BluetoothDevice?>(context, tvResourceId, Devices as List<BluetoothDevice?>) {

    private val mLayoutInflater: LayoutInflater
    private val mViewResourceId: Int
    private val mDevices: List<BluetoothDevice>

    init {
        mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mViewResourceId = tvResourceId
        mDevices=Devices
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val convertView = LayoutInflater.from(this.context).inflate(mViewResourceId,null)
        val device = mDevices[position]
        if (device != null) {

            val deviceName = convertView.findViewById<View>(R.id.text1) as TextView
            val deviceAdress = convertView.findViewById<View>(R.id.text2) as TextView
            if (deviceName != null) {
                deviceName.text = device.name
            }
            if (deviceAdress != null) {
                deviceAdress.text = device.address
            }
        }
        return convertView
    }


}


