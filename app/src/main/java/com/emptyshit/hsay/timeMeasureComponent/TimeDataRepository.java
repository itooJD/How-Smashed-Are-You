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
     * @return TimeData
     */
    TimeData findByPlayerId(long playerId){
        this.queryBuilder = this.timeDataDao.queryBuilder().where(TimeDataDao.Properties.PlayerID.eq(playerId));
        try {
            List<TimeData> timeDataList = this.queryBuilder.list();
            if (timeDataList.size() == 1) {
                return timeDataList.get(0);
            }
        } catch(Exception e){
        }
        return null;
    }

    /**
     *
     * @param timeData
     * @return
     */
    TimeData save(TimeData timeData){
        try {
            this.timeDataDao.insert(timeData);
            return timeData;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    TimeData update(TimeData timeData){
        try {
            this.timeDataDao.update(timeData);
            return timeData;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param playerId
     * @return
     */
    int deleteTimeData(long playerId) {
        this.queryBuilder = this.timeDataDao.queryBuilder().where(TimeDataDao.Properties.PlayerID.eq(playerId));
        List<TimeData> timeDataList = this.queryBuilder.list();
        if (timeDataList.size() == 1) {
            timeDataDao.delete(timeDataList.get(0));
            return 1;
        }
        return 0;
    }
}