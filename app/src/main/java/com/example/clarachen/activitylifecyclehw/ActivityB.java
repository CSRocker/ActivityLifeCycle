package com.example.clarachen.activitylifecyclehw;

/**
 * Created by ClaraChen on 2/20/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.clarachen.activitylifecyclehw.util.Utils;
import com.example.clarachen.activitylifecyclehw.util.StatusTracker;


public class ActivityB extends Activity{

    private String mActivityName;
    private TextView mStatusView;
    private TextView mStatusAllView;
    private StatusTracker mStatusTracker = StatusTracker.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        mActivityName = getString(R.string.activity_b_label);

        mStatusTracker.setStatus(mActivityName, getString(R.string.on_create));
    }


    @Override
    protected void onStart(){
        super.onStart();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_start));
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_restart));
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
       // Utils.printStatus(mStatusView, mStatusAllView);  Print the status on the screen
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStatusTracker.setStatus(mActivityName, getString(R.string.on_destroy));
    }


    public void startActivityA(View v) {
        Intent intent = new Intent(ActivityB.this, ActivityA.class);
        startActivity(intent);
    }


    public void finsihActivityB(View v){

        ActivityB.this.finish();

        //Restart ActivityA
        Intent intent = new Intent(ActivityB.this, ActivityA.class);
        startActivity(intent);
    }
}
