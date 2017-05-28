package com.emptyshit.hsay.timeMeasureComponent;

import com.emptyshit.hsay.dataTypes.TimeType;
import com.emptyshit.hsay.dataTypes.TimeTypeConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;


import java.util.ArrayList;


/**
 * Representation of the Time
 */
@Entity
public class TimeData {

    @Id
    @Generated
    private Long timeDataID;

    @Property
    private long playerID;

    private long gameID;

    @Convert(converter = TimeTypeConverter.class, columnType = Double.class)
    private TimeType bestTimeType = null;

    @Convert(converter = TimeTypeConverter.class, columnType = Double.class)
    private TimeType avgTimeType = null;

    // TimeType eine eigene Entität?
    @Transient
    private ArrayList<TimeType> timeTypeLine = new ArrayList<TimeType>();

    private int timesPlayed = 0;

    @Generated(hash = 61090496)
    public TimeData() {
    }

    @Generated(hash = 1203745979)
    public TimeData(Long timeDataID, long playerID, long gameID, TimeType bestTimeType, TimeType avgTimeType,
            int timesPlayed) {
        this.timeDataID = timeDataID;
        this.playerID = playerID;
        this.gameID = gameID;
        this.bestTimeType = bestTimeType;
        this.avgTimeType = avgTimeType;
        this.timesPlayed = timesPlayed;
    }

    /**
     *
     * @param bestTimeType
     * @return
     */
    private int setBestTime(TimeType bestTimeType){
        if(!this.bestTimeType.isSmallerThan(bestTimeType)) {
            this.bestTimeType = bestTimeType;
            return 1;
        }
        return 0;
    }

    /**
     *
     * @param newTimeType
     * @return
     */
    private TimeType calculateAvgTime(TimeType newTimeType){
        avgTimeType = avgTimeType.multiply(timesPlayed/(timesPlayed+1)).add(newTimeType.divide(timesPlayed+1));
        return avgTimeType;
    }

    /**
     *
     * @param newTimeType
     * @return
     */
    TimeType addNewTime(TimeType newTimeType){
        this.timeTypeLine.add(0, newTimeType);
        if(timeTypeLine.size() > 30 ){
            this.timeTypeLine.remove(30);
        }
        this.timesPlayed++;
        setBestTime(newTimeType);
        calculateAvgTime(newTimeType);
        return newTimeType;
    }

    long getPlayerID() {
        return playerID;
    }

    long getGameID() {
        return gameID;
    }

    TimeType getBestTimeType() {
        return bestTimeType;
    }

    TimeType getAvgTimeType() {
        return avgTimeType;
    }

    ArrayList<TimeType> getTimeTypeLine() {
        return timeTypeLine;
    }

    int getTimesPlayed() {
        return timesPlayed;
    }

    void setPlayerID(long playerID) {
        this.playerID = playerID;
    }

    void setGameID(long gameID) {
        this.gameID = gameID;
    }

    void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    void setBestTimeType(TimeType bestTimeType) {
        this.bestTimeType = bestTimeType;
    }

    void setAvgTimeType(TimeType avgTimeType) {
        this.avgTimeType = avgTimeType;
    }

    public Long getTimeDataID() {
        return this.timeDataID;
    }

    public void setTimeDataID(Long timeDataID) {
        this.timeDataID = timeDataID;
    }
}