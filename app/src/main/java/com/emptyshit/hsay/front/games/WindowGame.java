package com.emptyshit.hsay.front.games;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.emptyshit.hsay.R;
import com.emptyshit.hsay.application.App;
import com.emptyshit.hsay.front.fake.WindowFakeWhatsappAccess;
import com.emptyshit.hsay.lockComponent.LockComponentInterface;

public class WindowGame extends Activity implements FragmentCommunication{
    private FragmentClock fragmentClock;
    private FragmentEnterWordGame fragmentEnterWordGame;
    private LockComponentInterface lockComponentInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_game);

        this.fragmentClock = new FragmentClock();
        this.fragmentEnterWordGame = new FragmentEnterWordGame();
        this.lockComponentInterface = App.getLockComponentInterface();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.gameClockFragment, fragmentClock);
        fragmentTransaction.add(R.id.gameGameFragment, fragmentEnterWordGame);
        fragmentTransaction.commit();
    }

    @Override
    public void stopChronometer() {
        this.fragmentClock.stopChronometer();
    }

    @Override
    public void unLock(){
        if(this.lockComponentInterface.isLocked()){
            this.lockComponentInterface.unLock();
            Intent intent = new Intent(getApplicationContext(),WindowFakeWhatsappAccess.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), WindowShowTime.class);
            startActivity(intent);
        }
    }
}
