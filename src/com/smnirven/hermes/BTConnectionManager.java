package com.smnirven.hermes;

import android.bluetooth.BluetoothAdapter;

public class BTConnectionManager {
    public static BluetoothAdapter adapter;
    
    
    public static void setupBluetooth() {
        adapter = BluetoothAdapter.getDefaultAdapter();
    }
}
