package com.emptyshit.hsay.front.games;

import android.os.Bundle;
import android.app.Fragment;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import com.emptyshit.hsay.R;
import com.emptyshit.hsay.application.App;
import com.emptyshit.hsay.timeMeasureComponent.TimeMeasureComponentInterface;

public class FragmentClock extends Fragment {

    private TimeMeasureComponentInterface timeMeasureComponentInterface;
    private Chronometer clockChronometer;
    private long time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentClock = inflater.inflate(R.layout.fragment_clock, container, false);

        this.timeMeasureComponentInterface = App.getTimeMeasureComponentInterface();
        this.clockChronometer = (Chronometer) fragmentClock.findViewById(R.id.clockChronometer);
        this.clockChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
            @Override
            public void onChronometerTick(Chronometer cArg) {
                long chronometerTime = SystemClock.elapsedRealtime() - cArg.getBase();
                //int mil = (int) (chronometerTime % 1000);
                int sec = (int) (chronometerTime / 1000 % 60);
                String ss = sec < 10 ? "0"+sec: sec+"";
                //mil = mil / 10;
                //String mi = mil < 10 ? "0"+mil: mil+"";
                //cArg.setText(ss+":"+mi);
                cArg.setText(ss + "s");
                time = chronometerTime % (1000 * 60 * 60);
            }
        });
        this.clockChronometer.setBase(SystemClock.elapsedRealtime());
        this.clockChronometer.start();
        return fragmentClock;
    }

    void stopChronometer(){
        this.clockChronometer.stop();
        this.timeMeasureComponentInterface.addTime(this.time);
    }
}
