package com.emptyshit.timeMeasureComponent;

import android.content.Context;

import com.emptyshit.hsay.application.App;
import com.emptyshit.hsay.dataTypes.EmailType;
import com.emptyshit.hsay.dataTypes.TimeType;
import com.emptyshit.hsay.playerComponent.DaoMaster;
import com.emptyshit.hsay.playerComponent.DaoSession;
import com.emptyshit.hsay.playerComponent.Player;
import com.emptyshit.hsay.playerComponent.PlayerComponent;
import com.emptyshit.hsay.playerComponent.PlayerComponentInterface;
import com.emptyshit.hsay.playerComponent.PlayerRepository;
import com.emptyshit.hsay.timeMeasureComponent.TimeDataRepository;
import com.emptyshit.hsay.timeMeasureComponent.TimeMeasureComponent;
import com.emptyshit.hsay.timeMeasureComponent.TimeMeasureComponentInterface;

import org.greenrobot.greendao.database.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.Random;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class TimeMeasureComponentTest {
    private Context context;
    private DaoSession daoSession;
    private PlayerRepository playerRepository;
    private PlayerComponentInterface playerComponentInterface;
    private TimeDataRepository timeDataRepository;
    private TimeMeasureComponentInterface timeMeasureComponentInterface;

    @Before
    public void construct(){
        this.context = RuntimeEnvironment.application;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this.context, "db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        playerRepository = new PlayerRepository(daoSession);
        playerComponentInterface = new PlayerComponent(playerRepository);
        timeDataRepository = new TimeDataRepository(daoSession);
        timeMeasureComponentInterface = new TimeMeasureComponent(playerComponentInterface, timeDataRepository);
    }

    @After
    public void destruct(){
        playerComponentInterface.delete();
    }

    @Test
    public void chronographTest(){
        assertEquals("start of the Chronograph", 1, timeMeasureComponentInterface.startChronograph());
        TimeType currentTime = timeMeasureComponentInterface.getCurrentTime();
        assertNotNull("current time is not empty",currentTime);
        assertEquals("end of the Chronograph", 0, timeMeasureComponentInterface.endChronograph());
        TimeType stoppedTime = timeMeasureComponentInterface.getStoppedTime();
        assertNotNull("stopped time is not empty", stoppedTime);
        assertNotEquals("saved mid time is not the same as the stopped time", currentTime, stoppedTime);
    }

    @Test
    public void getMyBestTimeOfGameTest(){
        Random rand = new Random();
        TimeType bestTime = new TimeType(Long.MAX_VALUE);
        for(int i = 0; i < 10; i++) {
            timeMeasureComponentInterface.startChronograph();
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
            }
            timeMeasureComponentInterface.endChronograph();
            if (!bestTime.isSmallerThan(timeMeasureComponentInterface.getStoppedTime())) {
                bestTime = timeMeasureComponentInterface.getStoppedTime();
            }
        }
        assertEquals("beste zeit ist", bestTime, timeMeasureComponentInterface.getMyBestTimeOfGame(0));
    }

    @Test
    public void getMyAvgTimeOfGameTest(){
        for(int i = 0; i < 10; i++) {
            timeMeasureComponentInterface.startChronograph();
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
            }
            timeMeasureComponentInterface.endChronograph();
        }
        assertEquals("avg zeit ist", new TimeType(4.5), timeMeasureComponentInterface.getMyAvgTimeOfGame(0));
    }

    @Test
    public void getAllTimeOfGameTest(){

    }

    @Test
    public void getBestOfGameTest(){

    }
}
