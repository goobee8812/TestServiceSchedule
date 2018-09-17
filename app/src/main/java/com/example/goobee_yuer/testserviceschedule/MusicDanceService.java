package com.example.goobee_yuer.testserviceschedule;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MusicDanceService extends Service {
    private MyBinder binder = null;
    private final static String TAG = "TestService";
    public MusicDanceService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new MyBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binder = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    public class MyBinder extends Binder {
        private int[] actions;
        public TimerTask mTimerTask = null;
        public Timer mTimer = null;
        private int position;
        /**
         * 根据动作数组发送指定指令
         */
        public void startMusicDanceActions(final int[] actions ){
            if (!(actions.length > 0)) return;
            this.actions = actions;
            if (mTimerTask != null){
                mTimerTask.cancel();
                mTimerTask = null;
            }
            if (mTimer != null){
                mTimer.cancel();
                mTimer = null;
            }
            position = 0;
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    handleSendCmd(actions[position]);
                }

            };
            mTimer = new Timer();
            mTimer.schedule(mTimerTask,0,1500);
        }

        private void handleSendCmd(int type){
            switch (type){
                case 1:
                    Log.d(TAG, "startMusicDanceActions: 前进");
                    break;
                case 2:
                    Log.d(TAG, "startMusicDanceActions: 后退");
                    break;
                case 3:
                    Log.d(TAG, "startMusicDanceActions: 左转");
                    break;
                case 4:
                    Log.d(TAG, "startMusicDanceActions: 右转");
                    break;
                default:
                    break;
            }
            position ++;
            if (position >= actions.length) {
                position = 0;
            }
            Log.d(TAG, "handleSendCmd: " + position);
        }
    }

}
