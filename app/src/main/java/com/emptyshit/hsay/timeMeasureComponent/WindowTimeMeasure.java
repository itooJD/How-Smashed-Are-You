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
import com.emptyshit.hsay.frontsPlayer.WindowRegister;

public class WindowTimeMeasure extends AppCompatActivity {

    private Chronometer windowTimeMeasureChronometer;
    private Button windowTimeMeasureStopButton;
    private TextView windowTimeMeasureTimeTextView;
    private TimeMeasureComponentInterface timeMeasureComponentInterface;
    private TimeType time;

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

    }
        windowTimeMeasureChronometer.setOnChronometerTickListener(new OnChronometerTickListener(){
        @Override
        public void onChronometerTick(Chronometer cArg) {


            // change to TimeType
            double currentTime;
            currentTime = time.getMilliseconds() - cArg.getBase();
            int sec  = (int) (time.getMilliseconds() * 1000);
            int milli = (int) time.getMilliseconds();

            String seconds = sec < 10 ? "0"+sec: sec+"";
            String milliseconds = milli < 10 ? "0"+milli: milli+"";
            cArg.setText(seconds+":"+milliseconds+":");

            windowTimeMeasureChronometer.setBase();
            windowTimeMeasureChronometer.start();

        }
    });
            // Hatte funktioniert mit Android SystemClock.uptimeMillis().
        /*
        windowTimeMeasureChronometer.setOnChronometerTickListener(new OnChronometerTickListener(){
                @Override
                public void onChronometerTick(Chronometer cArg) {

                    // change to TimeType
                    long time = SystemClock.elapsedRealtime() - cArg.getBase();
                    int h   = (int)(time /3600000);
                    int m = (int)(time - h*3600000)/60000;
                    int s= (int)(time - h*3600000- m*60000)/1000 ;
                    String hh = h < 10 ? "0"+h: h+"";
                    String mm = m < 10 ? "0"+m: m+"";
                    String ss = s < 10 ? "0"+s: s+"";
                    cArg.setText(hh+":"+mm+":"+ss);
                }
            });

        windowTimeMeasureChronometer.setBase(SystemClock.uptimeMillis());
        windowTimeMeasureChronometer.start();
        */

    private void setupClickListener(){
        windowTimeMeasureStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                windowTimeMeasureChronometer.stop();
                timeMeasureComponentInterface.getStoppedTime();
            }
        });
    }

}
