package com.emptyshit.hsay.application;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.emptyshit.hsay.playerComponent.DaoMaster;
import com.emptyshit.hsay.playerComponent.DaoSession;
import com.emptyshit.hsay.playerComponent.PlayerComponent;
import com.emptyshit.hsay.playerComponent.PlayerComponentInterface;
import com.emptyshit.hsay.playerComponent.PlayerRepository;
import com.emptyshit.hsay.playerComponent.PlayerRepositoryInterface;
import com.emptyshit.hsay.timeMeasureComponent.TimeDataRepository;
import com.emptyshit.hsay.timeMeasureComponent.TimeMeasureComponent;
import com.emptyshit.hsay.timeMeasureComponent.TimeMeasureComponentInterface;

import org.greenrobot.greendao.database.Database;

/**
 * Created by tungu on 04/05/2017.
 */
public class App extends Application{

    private static DaoSession daoSession = null;

    private static PlayerComponentInterface playerComponentInterface = null;
    private static PlayerRepositoryInterface playerRepositoryInterface = null;
    private static TimeMeasureComponentInterface timeMeasureComponentInterface = null;
    private static TimeDataRepository timeDataRepository = null;

    @Override
    public void onCreate(){
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "db");
        SQLiteDatabase db = helper.getWritableDatabase();
        daoSession = new DaoMaster(db).newSession();

        playerRepositoryInterface = new PlayerRepository(daoSession);
        playerComponentInterface = new PlayerComponent(playerRepositoryInterface);
        timeDataRepository = new TimeDataRepository(daoSession);
        timeMeasureComponentInterface = new TimeMeasureComponent(playerComponentInterface, timeDataRepository);
    }

    public static PlayerComponentInterface getPlayerComponentInterface(){
        return playerComponentInterface;
    }

    public static TimeMeasureComponentInterface getTimeMeasureComponentInterface(){
        return timeMeasureComponentInterface;
    }
}