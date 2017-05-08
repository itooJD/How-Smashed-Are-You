package com.emptyshit.hsay.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.emptyshit.hsay.playerComponent.DaoMaster;
import com.emptyshit.hsay.playerComponent.DaoSession;
import com.emptyshit.hsay.playerComponent.PlayerComponent;
import com.emptyshit.hsay.playerComponent.PlayerComponentInterface;
import com.emptyshit.hsay.playerComponent.PlayerRepository;
import com.emptyshit.hsay.playerComponent.PlayerRepositoryInterface;

import org.greenrobot.greendao.database.Database;

/**
 * Created by tungu on 04/05/2017.
 */
 public class App extends Application{

    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;

    private static PlayerComponentInterface playerComponentInterface;
    private PlayerRepositoryInterface playerRepositoryInterface;


    @Override
    public void onCreate(){
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "db-encrypted" : "db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        playerRepositoryInterface = new PlayerRepository(daoSession);
        playerComponentInterface = new PlayerComponent(playerRepositoryInterface);
    }

    public static PlayerComponentInterface getPlayerComponentInterface(){
        return playerComponentInterface;
    }
}