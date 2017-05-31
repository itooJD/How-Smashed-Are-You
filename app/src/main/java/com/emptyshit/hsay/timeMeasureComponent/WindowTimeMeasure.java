package com.emptyshit.hsay.timeMeasureComponent;

import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.TextView;

import com.emptyshit.hsay.R;
import com.emptyshit.hsay.application.App;
import com.emptyshit.hsay.dataTypes.TimeType;


public class WindowTimeMeasure extends AppCompatActivity {

    private Chronometer windowTimeMeasureChronometer;
    private Button windowTimeMeasureStopButton;
    private TextView windowTimeMeasureTimeTextView;
    private TimeMeasureComponentInterface timeMeasureComponentInterface;
    public long time = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_time_measure);

        timeMeasureComponentInterface = App.getTimeMeasureComponentInterface();
        windowTimeMeasureChronometer = (Chronometer) findViewById(R.id.windowTimeMeasureChronometer);
        windowTimeMeasureStopButton = (Button) findViewById(R.id.windowTimeMeasureStopButton);
        windowTimeMeasureTimeTextView = (TextView) findViewById(R.id.windowTimeMeasureTimeTextView);
        windowTimeMeasureTimeTextView.setText(timeMeasureComponentInterface.getStoppedTime().toString());

        setupClickListener();
        dothis();
    }

        public void dothis(){
        windowTimeMeasureChronometer.setOnChronometerTickListener(new OnChronometerTickListener(){
                @Override
                public void onChronometerTick(Chronometer cArg) {
                    long chronometerTime = SystemClock.elapsedRealtime() - cArg.getBase();
                    int milli = (int) (chronometerTime % 1000);
                    int s = (int) (chronometerTime / 1000 % 60);
                    int m = (int) (chronometerTime / (1000 * 60) % 60);
                    String mm = m < 10 ? "0"+m: m+"";
                    String ss = s < 10 ? "0"+s: s+"";
                    String mil = "";
                    if(milli < 10){
                        mil = "00" +  milli;
                    } else if(milli < 100){
                        mil = "0" + milli;
                    } else {
                        mil = "" + milli;
                    }
                    cArg.setText(mm+":"+ss+":"+mil);
                    time = chronometerTime % (1000 * 60 * 60);

                }
            });
        windowTimeMeasureChronometer.setBase(SystemClock.elapsedRealtime());
        windowTimeMeasureChronometer.start();
        }

    private void setupClickListener(){
        windowTimeMeasureStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                windowTimeMeasureChronometer.stop();
                timeMeasureComponentInterface.addTime(time, 1);
                windowTimeMeasureTimeTextView.setText("" + timeMeasureComponentInterface.getStoppedTime());
            }
        });
    }

}
