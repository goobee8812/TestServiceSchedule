package com.example.goobee_yuer.testserviceschedule;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MusicDanceService.MyBinder mMyBinder = null;
    int[] actions = new int[]{1,4,3,2,3,2,1,2,3,4,3,2};

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("Test", "onServiceConnected: ");
            mMyBinder = (MusicDanceService.MyBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("Test", "onServiceDisconnected: ");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //连接服务
        Intent intent = new Intent(this,MusicDanceService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);
        findViewById(R.id.send_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMyBinder != null){
                    mMyBinder.startMusicDanceActions(actions);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}
