package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals("com.example.broadcasttest.MY_BROADCAST")){
            Toast.makeText(context, "received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
        }

    }
}
