package com.example.clarachen.activitylifecyclehw;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

import com.example.clarachen.activitylifecyclehw.util.StatusTracker;
import com.example.clarachen.activitylifecyclehw.util.Utils;

public class ActivityA extends Activity {


    private String mActivityName;
    private TextView mStatusView;
    private TextView mStatusAllView;
    private StatusTracker mStatusTracker = StatusTracker.getInstance();
    private TextView cThread;
    private TextView counter;
    Timer timer;
    int count=1;

    public static Thread mThread;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        mActivityName = getString(R.string.activity_a);
        //mStatusView = (TextView)findViewById(R.id.status_view_a);
        //mStatusAllView = (TextView)findViewById(R.id.status_view_all_a);
        Utils.printStatus(mStatusView, mStatusAllView);
        counter = (TextView) findViewById(R.id.t);

    }



    @Override
    protected void onStart() {

        super.onStart();

        cThread = (TextView) findViewById(R.id.t2);
        counter.setEnabled(false);
        cThread.setEnabled(false);
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        counter.setText(" " + count);
                        count++;
                    }

                });
            }
        }, 1000, 1000);
    }



    @Override
    protected void onRestart(){
        super.onRestart();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_restart));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_resume));

    }

    @Override
    protected void onPause(){
        super.onPause();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_pause));
    }

    @Override
    protected void onStop(){
        super.onStop();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_stop));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_destroy));
        mStatusTracker.clear();
    }

    public void startDialog(View v) {
        Intent intent = new Intent(ActivityA.this, DialogActivity.class);
        startActivity(intent);
    }


    public void startActivityB(View v){
        Intent intent = new Intent(ActivityA.this, ActivityB.class);
        startActivity(intent);
    }

    public void finishActivityA(View V){
        ActivityA.this.finish();
    }


}
