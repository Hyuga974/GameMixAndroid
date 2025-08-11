package com.example.gamemixandroid.ViewModel

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothDevice
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.lifecycle.ViewModel
import com.example.gamemixandroid.Model.BluetoothModel

class BluetoothViewModel() : ViewModel() {
    private val bluetoothModel: BluetoothModel = BluetoothModel()
    fun isBluetoothSupported(): Boolean {
        return bluetoothModel.isBluetoothSupported()
    }

    fun isBluetoothEnabled(): Boolean {
        return bluetoothModel.isBluetoothEnabled()
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun enableBluetooth(): Boolean {
        return bluetoothModel.enableBluetooth()
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun disableBluetooth(): Boolean {
        return bluetoothModel.disableBluetooth()
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun getPairedDevices(): Set<BluetoothDevice>? {
        return bluetoothModel.getPairedDevices()
    }

    fun connectToDevice(device: BluetoothDevice): Boolean {
        return bluetoothModel.connectToDevice(device)
    }

    fun hasBluetoothPermission( context: Context): Boolean {
        return bluetoothModel.hasBluetoothPermission(context)
    }

    fun requestBluetoothPermission(activity : Activity) {
        bluetoothModel.requestBluetoothPermission(activity)
    }
}
