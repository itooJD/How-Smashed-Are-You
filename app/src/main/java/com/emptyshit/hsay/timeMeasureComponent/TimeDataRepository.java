package com.emptyshit.hsay.timeMeasureComponent;

import com.emptyshit.hsay.dataTypes.TimeType;
import com.emptyshit.hsay.playerComponent.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class TimeDataRepository {

    private TimeDataDao timeDataDao;
    private QueryBuilder<TimeData> queryBuilder;

    public TimeDataRepository(DaoSession daoSession){
        this.timeDataDao = daoSession.getTimeDataDao();
    }

    TimeData getTimeData(long playerId, long gameId){
        this.queryBuilder = this.timeDataDao.queryBuilder().where(TimeDataDao.Properties.PlayerID.eq(playerId), TimeDataDao.Properties.GameID.eq(gameId));
        List<TimeData> timeDataList = this.queryBuilder.list();
        if(timeDataList.size() == 1){
            return timeDataList.get(0);
        }
        return createTimeData(playerId, gameId);
    }

    TimeData createTimeData(long playerId, long gameId){
        TimeData timeData = new TimeData();
        timeData.setPlayerID(playerId);
        timeData.setGameID(gameId);
        timeData.setBestTimeType(new TimeType(Double.MAX_VALUE));
        timeData.setAvgTimeType(new TimeType(1));
        timeData.setTimesPlayed(0);
        saveTimeData(timeData);
        return timeData;
    }


    TimeData saveTimeData(TimeData timeData){
        timeDataDao.save(timeData);
        return timeData;
    }
}