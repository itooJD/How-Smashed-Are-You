package com.emptyshit.hsay.timeMeasureComponent;

import com.emptyshit.hsay.dataTypes.TimeType;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;

import java.util.ArrayList;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by tungu on 09/04/2017.
 */

@Entity
public class TimeData {

    // fehlt eine Relation zu Player
    @Id
    private int playerID;

    private int gameID;

    @Transient
    private TimeType bestTimeType = null;

    @Transient
    private TimeType avgTimeType = null;

    @Transient
    private ArrayList<TimeType> timeTypeLine = new ArrayList<TimeType>();

    private int timesPlayed = 0;

    public TimeData(int playerID, int gameID){
        this.playerID = playerID;
        this.gameID = gameID;
    }

    @Generated(hash = 2106540647)
    public TimeData(int playerID, int gameID, int timesPlayed) {
        this.playerID = playerID;
        this.gameID = gameID;
        this.timesPlayed = timesPlayed;
    }

    @Generated(hash = 61090496)
    public TimeData() {
    }

    private int setBestTime(TimeType bestTimeType){
        if(bestTimeType.isSmaller(bestTimeType)) {
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
}
