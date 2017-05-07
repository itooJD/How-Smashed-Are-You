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

    // fehlt eine Relation zu Player
    @Id
    private int playerID;

    private int gameID;

    @Convert(converter = TimeTypeConverter.class, columnType = Float.class)
    private TimeType bestTimeType = null;

    @Convert(converter = TimeTypeConverter.class, columnType = Float.class)
    private TimeType avgTimeType = null;

    // TimeType eine eigene Entität?
    @Transient
    private ArrayList<TimeType> timeTypeLine = new ArrayList<TimeType>();

    private int timesPlayed = 0;


    @Generated(hash = 61090496)
    public TimeData() {
    }

    @Generated(hash = 1642838466)
    public TimeData(int playerID, int gameID, TimeType bestTimeType, TimeType avgTimeType, int timesPlayed) {
        this.playerID = playerID;
        this.gameID = gameID;
        this.bestTimeType = bestTimeType;
        this.avgTimeType = avgTimeType;
        this.timesPlayed = timesPlayed;
    }

    private int setBestTime(TimeType bestTimeType){
        if(bestTimeType.isSmallerThan(bestTimeType)) {
            this.bestTimeType = bestTimeType;
            return 1;
        }
        return 0;
    }

    private TimeType calculateAvgTime(TimeType newTimeType){
        avgTimeType = avgTimeType.multiply(timesPlayed/(timesPlayed+1)).add(newTimeType.divide(timesPlayed+1));
        return avgTimeType;
    }

    public TimeType addNewTime(TimeType newTimeType){
        this.timeTypeLine.add(0, newTimeType);
        if(timeTypeLine.size() > 30 ){
            this.timeTypeLine.remove(30);
        }
        this.timesPlayed++;
        setBestTime(newTimeType);
        calculateAvgTime(newTimeType);
        return newTimeType;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getGameID() {
        return gameID;
    }

    public TimeType getBestTimeType() {
        return bestTimeType;
    }

    public TimeType getAvgTimeType() {
        return avgTimeType;
    }

    public ArrayList<TimeType> getTimeTypeLine() {
        return timeTypeLine;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    public void setBestTimeType(TimeType bestTimeType) {
        this.bestTimeType = bestTimeType;
    }

    public void setAvgTimeType(TimeType avgTimeType) {
        this.avgTimeType = avgTimeType;
    }
}