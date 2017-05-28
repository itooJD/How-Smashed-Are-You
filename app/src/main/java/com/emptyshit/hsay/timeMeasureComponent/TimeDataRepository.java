package com.emptyshit.hsay.timeMeasureComponent;

import com.emptyshit.hsay.dataTypes.TimeType;
import com.emptyshit.hsay.playerComponent.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 *  Time Data Repository
 *
 *  CRUD operations for the Time data
 */
public class TimeDataRepository {

    private TimeDataDao timeDataDao;
    private QueryBuilder<TimeData> queryBuilder;

    public TimeDataRepository(DaoSession daoSession){
        this.timeDataDao = daoSession.getTimeDataDao();
    }

    /**
     * find the Time Data
     * @param playerId
     * @param gameId
     * @return TimeData
     */
    TimeData getTimeData(long playerId, long gameId){
        this.queryBuilder = this.timeDataDao.queryBuilder().where(TimeDataDao.Properties.PlayerID.eq(playerId), TimeDataDao.Properties.GameID.eq(gameId));
        List<TimeData> timeDataList = this.queryBuilder.list();
        if(timeDataList.size() == 1){
            return timeDataList.get(0);
        } else {
            return createTimeData(playerId, gameId);
        }
    }

    /**
     * Cr
     * @param playerId
     * @param gameId
     * @return
     */
    private TimeData createTimeData(long playerId, long gameId){
        TimeData timeData = new TimeData();
        timeData.setPlayerID(playerId);
        timeData.setGameID(gameId);
        timeData.setTimesPlayed(0);
        timeData.setBestTimeType(new TimeType(Long.MAX_VALUE));
        timeData.setAvgTimeType(new TimeType(1));
        saveTimeData(timeData);
        return timeData;
    }

    /**
     *
     * @param timeData
     * @return
     */
    TimeData saveTimeData(TimeData timeData){
        this.timeDataDao.save(timeData);
        return timeData;
    }

    TimeData updateTimeData(TimeData timeData){
        this.timeDataDao.update(timeData);
        return timeData;
    }

    /**
     *
     * @param playerId
     * @param gameId
     * @return
     */
    int deleteTimeData(long playerId, long gameId) {
        this.queryBuilder = this.timeDataDao.queryBuilder().where(TimeDataDao.Properties.PlayerID.eq(playerId), TimeDataDao.Properties.GameID.eq(gameId));
        List<TimeData> timeDataList = this.queryBuilder.list();
        if (timeDataList.size() == 1) {
            timeDataDao.delete(timeDataList.get(0));
            return 1;
        }
        return 0;
    }
}