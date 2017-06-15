package com.emptyshit.timeMeasureComponent;

import android.content.Context;

import com.emptyshit.hsay.dataTypes.TimeType;
import com.emptyshit.hsay.playerComponent.DaoMaster;
import com.emptyshit.hsay.playerComponent.DaoSession;
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
        playerComponentInterface = new PlayerComponent(playerRepository, this.context);
        timeDataRepository = new TimeDataRepository(daoSession);
        timeMeasureComponentInterface = new TimeMeasureComponent(playerComponentInterface, timeDataRepository, this.context);
    }

    @After
    public void destruct(){
        playerComponentInterface.delete();
    }

    @Test
    public void addTimeTest(){
        assertEquals("trying to add time = 0", false, timeMeasureComponentInterface.addTime(0));
        assertEquals("trying to add time > 0", true, timeMeasureComponentInterface.addTime(1));
    }

    @Test
    public void getMyBestTimeOfGameTest(){
        Random rand = new Random();
        TimeType bestTime = new TimeType(Long.MAX_VALUE);
        for(int i = 0; i < 10; i++) {
            timeMeasureComponentInterface.addTime(rand.nextInt(1000));
            if (!bestTime.isSmallerThan(timeMeasureComponentInterface.getStoppedTime())) {
                bestTime = timeMeasureComponentInterface.getStoppedTime();
            }
        }
        assertEquals("beste zeit ist", bestTime, timeMeasureComponentInterface.getMyBestTimeOfGame());
    }

    @Test
    public void getMyAvgTimeOfGameTest(){
        double collector = 0.0;
        for(int i = 1; i < 11; i++) {
            timeMeasureComponentInterface.addTime(i);
            collector += timeMeasureComponentInterface.getStoppedTime().getMilliseconds();
        }
        assertEquals("avg zeit ist", new TimeType(collector / 10), timeMeasureComponentInterface.getMyAvgTimeOfGame());
    }
}
