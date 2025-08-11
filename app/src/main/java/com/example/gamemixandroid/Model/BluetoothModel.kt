package com.example.gamemixandroid.Model

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ui.home.REQUEST_CODE_BLUETOOTH_PERMISSION

class BluetoothModel {
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    fun isBluetoothSupported(): Boolean {
        Log.d("BluetoothModel", "Checking if Bluetooth is supported")
        return bluetoothAdapter != null
    }

    fun isBluetoothEnabled(): Boolean {
        Log.d("BluetoothModel", "Checking if Bluetooth is enabled")
        return bluetoothAdapter?.isEnabled ?: false
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun enableBluetooth(): Boolean {
        return bluetoothAdapter?.enable() ?: false
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun disableBluetooth(): Boolean {
        return bluetoothAdapter?.disable() ?: false
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    fun getPairedDevices(): Set<BluetoothDevice>? {
        return bluetoothAdapter?.bondedDevices
    }

    fun connectToDevice(device: BluetoothDevice): Boolean {
        // Implémentez la logique de connexion à l'appareil Bluetooth ici
        return true
    }

    fun hasBluetoothPermission(context : Context): Boolean {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_CONNECT
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH
            ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.BLUETOOTH_ADMIN
                    ) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun requestBluetoothPermission(activity : Activity) {
        if(!isBluetoothEnabled()){
            Log.e("BluetoothPermission", "Bluetooth is not enabled. Please enable Bluetooth first.")
            return
        }
        if (!isBluetoothSupported()){
            Log.e("BluetoothPermission", "Bluetooth is not supported on this device.")
        }
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            arrayOf(Manifest.permission.BLUETOOTH_CONNECT)
        } else {
            arrayOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN
            )
        }
        Log.d("BluetoothPermission", "Requesting permissions: ${permissions.joinToString(", ")}")
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE_BLUETOOTH_PERMISSION)
    }
}
