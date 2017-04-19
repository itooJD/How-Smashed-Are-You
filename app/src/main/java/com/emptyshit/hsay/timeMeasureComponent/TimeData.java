package com.emptyshit.hsay.timeMeasureComponent;

import com.emptyshit.hsay.dataTypes.TimeType;

import java.util.ArrayList;

/**
 * Created by tungu on 09/04/2017.
 */

public class TimeData {

    private int playerID;
    private int gameID;
    private TimeType bestTimeType = null;
    private TimeType avgTimeType = null;
    private ArrayList<TimeType> timeTypeLine = new ArrayList<TimeType>();
    private int timesPlayed = 0;

    public TimeData(int playerID, int gameID){
        this.playerID = playerID;
        this.gameID = gameID;
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
}
