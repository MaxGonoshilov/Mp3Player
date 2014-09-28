package com.example.mp3player;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    static int sw = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final Button btn = (Button) findViewById(R.id.buttonPlay);
        final TextView tv = (TextView) findViewById(R.id.textViewStatusValue);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent mintent = new Intent(MainActivity.this, PlayService.class);
            	if (sw == 0) {
            		sw = 1;
            		startService(mintent);            		
            		btn.setText(getResources().getString(R.string.button_pause));
            		tv.setText(getResources().getString(R.string.status_playing));
            	} else if (sw == 1) {
            		sw = 2;
            		stopService(mintent);            		
            		btn.setText(getResources().getString(R.string.button_play));
            		tv.setText(getResources().getString(R.string.status_paused));
            	} else if (sw == 2) {
            		sw = 1;
            		startService(mintent);            		
            		btn.setText(getResources().getString(R.string.button_pause));
            		tv.setText(getResources().getString(R.string.status_playing));
            	}
            }
        });
        registerReceiver(broadcastReceiver, new IntentFilter(PlayService.BROADCAST_ACTION));
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);       
        }
    };
    
    private void updateUI(Intent intent) {
        final Button btn = (Button) findViewById(R.id.buttonPlay);
        final TextView tv = (TextView) findViewById(R.id.textViewStatusValue);
		sw = 0;
		btn.setText("Play");
		tv.setText("Idle");
    }    
}
