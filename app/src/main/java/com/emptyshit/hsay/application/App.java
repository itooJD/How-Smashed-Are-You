package com.emptyshit.hsay.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import com.emptyshit.hsay.playerComponent.DaoMaster;
import com.emptyshit.hsay.playerComponent.DaoSession;
import com.emptyshit.hsay.playerComponent.PlayerComponent;
import com.emptyshit.hsay.playerComponent.PlayerComponentInterface;
import com.emptyshit.hsay.playerComponent.PlayerRepository;
import com.emptyshit.hsay.timeMeasureComponent.TimeDataRepository;
import com.emptyshit.hsay.timeMeasureComponent.TimeMeasureComponent;
import com.emptyshit.hsay.timeMeasureComponent.TimeMeasureComponentInterface;

/**
 * App initializes the Application with all necessary dependencies
 * and provides the component-interfaces for the other classes
 */
public class App extends Application{

    private static DaoSession daoSession = null;
    private static PlayerComponentInterface playerComponentInterface = null;
    private static PlayerRepository playerRepository= null;
    private static TimeMeasureComponentInterface timeMeasureComponentInterface = null;
    private static TimeDataRepository timeDataRepository = null;

    @Override
    public void onCreate(){
        super.onCreate();

        getApplicationContext().deleteDatabase("db");
        this.getApplicationContext().deleteFile("player");
        this.getApplicationContext().deleteFile("timeData");
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getApplicationContext(), "db");
        SQLiteDatabase db = helper.getWritableDatabase();
        daoSession = new DaoMaster(db).newSession();

        playerRepository = new PlayerRepository(daoSession);
        playerComponentInterface = new PlayerComponent(playerRepository, getApplicationContext());
        timeDataRepository = new TimeDataRepository(daoSession);
        timeMeasureComponentInterface = new TimeMeasureComponent(playerComponentInterface, timeDataRepository, getApplicationContext());
    }

    public static PlayerComponentInterface getPlayerComponentInterface(){
        return playerComponentInterface;
    }

    public static TimeMeasureComponentInterface getTimeMeasureComponentInterface(){
        return timeMeasureComponentInterface;
    }
}