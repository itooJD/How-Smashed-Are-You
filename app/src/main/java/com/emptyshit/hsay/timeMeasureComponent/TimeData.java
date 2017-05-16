package com.emptyshit.hsay.timeMeasureComponent;

import com.emptyshit.hsay.dataTypes.TimeType;
import com.emptyshit.hsay.dataTypes.TimeTypeConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;


import java.util.ArrayList;


/**
 * Representation of the Time
 */
@Entity
public class TimeData {

    @Id
    private long id;

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

    @Generated(hash = 1348853680)
    public TimeData(long id, long playerID, long gameID, TimeType bestTimeType, TimeType avgTimeType,
            int timesPlayed) {
        this.id = id;
        this.playerID = playerID;
        this.gameID = gameID;
        this.bestTimeType = bestTimeType;
        this.avgTimeType = avgTimeType;
        this.timesPlayed = timesPlayed;
    }

    int setBestTime(TimeType bestTimeType){
        if(bestTimeType.isSmallerThan(bestTimeType)) {
            this.bestTimeType = bestTimeType;
            return 1;
        }
        return 0;
    }

    TimeType calculateAvgTime(TimeType newTimeType){
        avgTimeType = avgTimeType.multiply(timesPlayed/(timesPlayed+1)).add(newTimeType.divide(timesPlayed+1));
        return avgTimeType;
    }

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

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}