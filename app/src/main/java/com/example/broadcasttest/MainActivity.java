package com.example.broadcasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    private IntentFilter intentFilterTest1;
    private Receiver receiverTest1;

    final private String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
//                //发送广播到本项目的广播接收器
//                intent.setPackage("com.example.broadcasttest");
//                sendOrderedBroadcast(intent, null);
//                //发送广播到第二个项目的广播接收器
//                intent.setPackage("com.example.broadcasttest2");
//                sendOrderedBroadcast(intent, null);
//                Log.d(TAG, "onClick: Send the Broadcast for MyBroadcastReceiver");
                Intent intent = new Intent("com.example.MyBroadcast");
                //发送广播到本项目的动态广播接收器
                //intent.setPackage("com.example.broadcasttest");
                //sendOrderedBroadcast(intent, null);
                //intent.setPackage("com.example.broadcasttest2");
                sendOrderedBroadcast(intent, null);

            }
        });

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);

        intentFilterTest1 = new IntentFilter();
        intentFilterTest1.addAction("com.example.MyBroadcast");
        intentFilterTest1.setPriority(10);
        receiverTest1 = new Receiver();
        registerReceiver(receiverTest1, intentFilterTest1);



    }
    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectionManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available",
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "network is unavailable",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "receiverTest1：" + context.toString(), Toast.LENGTH_SHORT).show();
            //abortBroadcast();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
        unregisterReceiver(receiverTest1);
    }
}