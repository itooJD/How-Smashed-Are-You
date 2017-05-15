package com.emptyshit.hsay.timeMeasureComponent;

import android.database.sqlite.*;

import com.emptyshit.hsay.playerComponent.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by huynh_phuong_nguyen on 13.04.17.
 */

public class TimeDataRepository {

    private TimeDataDao timeDataDao;
    private QueryBuilder<TimeData> queryBuilder;

    public TimeDataRepository (DaoSession daoSession){
        this.timeDataDao = daoSession.getTimeDataDao();
    }

    public TimeData getTimeDataByPlayerID(int playerID){
        return null;
        // if get = fail
        // then createTimeData()
    }

    public TimeData getTimeDataByGameID(int gameID){
        return null;
        // if get = fail
        // then createTimeData()
    }

    private TimeData createTimeData(int playerID){
        return null;

    }
}
