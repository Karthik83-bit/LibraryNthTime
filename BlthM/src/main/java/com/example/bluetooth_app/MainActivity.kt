package com.example.bluetooth_app

import android.Manifest
import android.app.Activity
import android.app.VoiceInteractor
import android.bluetooth.*
import android.companion.AssociationInfo
import android.companion.AssociationRequest
import android.companion.BluetoothDeviceFilter
import android.companion.CompanionDeviceManager
import android.content.*
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelUuid
import android.service.voice.VoiceInteractionSession
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContentProviderCompat.requireContext
import java.io.IOException
import java.util.*
import java.util.concurrent.Executor
import java.util.regex.Pattern
import java.util.zip.Inflater
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    lateinit var bluetoothAdapter: BluetoothAdapter
    lateinit var bluetoothManager: BluetoothManager
    lateinit var reciever:BluetoothReciever
    lateinit var bluetoothDiscoverable:BluetoothDiscoverable
    lateinit var discoverReciever:DiscoverReceiver
    lateinit var devices:MutableSet<BluetoothDevice>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bluetoothManager=getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter=bluetoothManager.adapter
        bluetoothDiscoverable= BluetoothDiscoverable()


//         class ConnectThread(device: BluetoothDevice) : Thread() {
//
//
//
//            private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
//                device.createRfcommSocketToServiceRecord(device.uuids[0].uuid)
//            }
//
//            public override fun run() {
//                // Cancel discovery because it otherwise slows down the connection.
//                bluetoothAdapter?.cancelDiscovery()
//
//                mmSocket?.let { socket ->
//                    // Connect to the remote device through the socket. This call blocks
//                    // until it succeeds or throws an exception.
//                    socket.connect()
//                        Log.d("msg","connected")
//                    // The connection attempt succeeded. Perform work associated with
//                    // the connection in a separate thread.
//
//                }
//            }
//
//            // Closes the client socket and causes the thread to finish.
//            fun cancel() {
//                try {
//                    mmSocket?.close()
//
//                } catch (e: IOException) {
//                    Log.e("msg", "Could not close the client socket", e)
//                }
//            }
//        }


//        val receiver = object : BroadcastReceiver() {
//
//            override fun onReceive(context: Context, intent: Intent) {
//                val action: String =    intent.action
//                when(action) {
//                    BluetoothDevice.ACTION_FOUND -> {
//                        // Discovery has found a device. Get the BluetoothDevice
//                        // object and its info from the Intent.
//                        val device: BluetoothDevice =
//                            intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
//                        val deviceName = device.name
//                        val deviceHardwareAddress = device.address // MAC address
//                        Log.d("msg","${deviceName}")
//                    }
//                }
//            }
//        }

//button.setOnClickListener {
//
//    val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
//    registerReceiver(receiver, filter)
//
//}

         class ConnectThread(device: BluetoothDevice) : Thread() {

            private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
                device.createRfcommSocketToServiceRecord(device.uuids[0].uuid)
            }

            public override fun run() {
                // Cancel discovery because it otherwise slows down the connection.
                bluetoothAdapter?.cancelDiscovery()

                mmSocket?.let { socket ->
                    // Connect to the remote device through the socket. This call blocks
                    // until it succeeds or throws an exception.
                    socket.connect()













                    // The connection attempt succeeded. Perform work associated with
                    // the connection in a separate thread.
                    Log.d("msg","suceeded")

                }
            }

            // Closes the client socket and causes the thread to finish.
            fun cancel() {
                try {
                    mmSocket?.close()
                } catch (e: IOException) {
                    Log.e("msg", "Could not close the client socket", e)
                }
            }
        }



        @RequiresApi(Build.VERSION_CODES.O)
        fun showDevices(){
            devices=bluetoothAdapter.bondedDevices
            val listViewArray:ArrayList<String>

            val setIterator=devices.iterator()
            listViewArray=ArrayList()
            setIterator.forEach {
                listViewArray.add(it.name)
                Log.d("msg","${it.uuids}")
            }
            val list:ListView=findViewById(R.id.list_view)
            val listAdapter: ArrayAdapter<BluetoothDevice>

            val arrayAdapter:ArrayAdapter<String>
            arrayAdapter=ArrayAdapter(this,R.layout.device_adapetr_view,listViewArray)
            val listItem=findViewById<TextView>(R.id.list_item)
            val listView: ListView=findViewById(R.id.list_view)
            listView.adapter=arrayAdapter
            listView.setOnItemClickListener { parent, view, position, id ->

                val selectedItem = parent.getItemAtPosition(position) as String
                devices.iterator().forEach {
                    if(it.name==selectedItem){
                        Log.d("msg","${it.uuids}")
                        Toast.makeText(this,"${it.uuids}",Toast.LENGTH_SHORT).show()

                        val conn=ConnectThread(it)
                        conn.run()
                    }
                }
                Log.d("msg","${selectedItem}")
            }


//            val intent= Intent(BluetoothDevice.ACTION_FOUND)

//              val discoverDevices:IntentFilter= IntentFilter(BluetoothDevice.ACTION_FOUND)
//
//                bluetoothAdapter.startDiscovery()
//            discoverReciever= DiscoverReceiver(devices)
//            Log.d("msg","discover${registerReceiver(discoverReciever,discoverDevices)}")
//            registerReceiver(discoverReciever,discoverDevices)




//
//
//            Log.d("msg"," discovering devices")
//            if(bluetoothAdapter.isDiscovering()){
//                bluetoothAdapter.cancelDiscovery()
//                Log.d("msg","cancelling discovery")
//                bluetoothAdapter.startDiscovery()
//
//                val discoverDevices:IntentFilter= IntentFilter(BluetoothDevice.ACTION_FOUND)
//                registerReceiver(discoverReciever,discoverDevices)
//            }
//            else{
////                bluetoothAdapter.scanMode=BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE
//
//                Log.d("msg"," discovering else devices${BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE}")
////                val intent= Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
////
//                val discoverDevices:IntentFilter= IntentFilter(BluetoothDevice.ACTION_PAIRING_REQUEST)
//                bluetoothAdapter.startDiscovery()
//
//                Log.d("msg"," discovering else devices${bluetoothAdapter.startDiscovery()}")
//
//              devices=discoverReciever.devices
//
//
//              Log.d("msg",devices.toString())
//              listAdapter= ArrayAdapter(this@MainActivity,R.layout.device_adapetr_view,devices)
//                list.adapter=listAdapter
//
//            }










        }



        show_devices.setOnClickListener{
            showDevices()
        }
        val btn_disc=findViewById<Button>(R.id.btn_discoverable)
        btn_disc.setOnClickListener{

//            Toast.makeText(this,"Conection" +
//                    "",Toast.LENGTH_SHORT).show()
            val discoverableIntent=Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE )
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300000)
            startActivity(discoverableIntent)
            val intentFilter= IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
            registerReceiver(bluetoothDiscoverable,intentFilter)



        }

val new_btn=findViewById<Button>(R.id.btn_bluetooth_connect)
//        val adpapterView= Inflater().inflate(R.layout.device_adapetr_view)
//        val layout=LayoutInflater.from(this@MainActivity).inflate(R.layout.device_adapetr_view)
//        val try_new=findViewById<ImageView>(R.id.)
        new_btn.setOnClickListener{
            reciever= BluetoothReciever()

            Toast.makeText(this,"Conection${bluetoothAdapter.isEnabled}" +
                    "",Toast.LENGTH_SHORT).show()
            if(!bluetoothAdapter.isEnabled){
                btn_bluetooth_connect.text="Disonnect"
                bluetoothAdapter.enable()
                val intent= Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivity(intent)

                    val intentFilter= IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)





            }
            bluetoothManager.adapter
            if(bluetoothAdapter.isEnabled){
                btn_bluetooth_connect.text="Connect"
                bluetoothAdapter.disable()
//                val bluetoothDiscoverableIntent=Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                val intentFilter=IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
                registerReceiver(reciever,intentFilter)
            }
//            Toast.makeText(this,"discoverable",Toast.LENGTH_SHORT).show()
        }
    }



    private fun enableDisableBluetooth() {
        when{
            ContextCompat.checkSelfPermission(this,android.Manifest.permission.BLUETOOTH_CONNECT)==PackageManager.PERMISSION_GRANTED->{

            }
            shouldShowRequestPermissionRationale(android.Manifest.permission.BLUETOOTH_CONNECT)->{


             ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.BLUETOOTH_CONNECT),101)
            }
        }
    }





//    override fun onDestroy() {
//        super.onDestroy()
//
//        unregisterReceiver(reciever)
//    }
}


