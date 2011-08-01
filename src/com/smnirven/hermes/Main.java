package com.smnirven.hermes;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {
    
	private static final int REQUEST_ENABLE_BT=0;
	private Button btnScan;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnScan = (Button) findViewById(R.id.btnScan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BTConnectionManager.setupBluetooth();
                if (BTConnectionManager.adapter == null) {
                    // Device does not support Bluetooth
                    Toast.makeText(getApplicationContext(), R.string.no_bluetooth, Toast.LENGTH_LONG).show();
                }
                if (!BTConnectionManager.adapter.isEnabled()) {
                    // Setup Bluetooth
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                } else {
                    Intent deviceConnectIntent = new Intent(getApplicationContext(), DeviceConnect.class);
                    startActivity(deviceConnectIntent);  
                }
            }
        });
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
        	handleBluetoothEnableRequestResult(resultCode, data);
        }
    }
    
    private void handleBluetoothEnableRequestResult(int code, Intent data) {
        if (code == RESULT_OK) {
            Intent deviceConnectIntent = new Intent(getApplicationContext(), DeviceConnect.class);
            startActivity(deviceConnectIntent);
        } else if (code == RESULT_CANCELED) {
            Toast.makeText(getApplicationContext(), R.string.bluetooth_canceled, Toast.LENGTH_LONG).show();
        }
    }
}