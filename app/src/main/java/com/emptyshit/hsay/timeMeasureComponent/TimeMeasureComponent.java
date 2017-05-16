package com.emptyshit.hsay.timeMeasureComponent;

import com.emptyshit.hsay.dataTypes.TimeType;
import com.emptyshit.hsay.playerComponent.PlayerComponentInterface;

/**
 * Created by tungu on 09/04/2017.
 */

public class TimeMeasureComponent implements TimeMeasureComponentInterface {

    private TimeData myTime = null;
    private int statusOfChronograph = 0;
    private TimeType startTime;
    private TimeType stoppedTime;

    private TimeDataRepository timeDataRepository = null;
    private PlayerComponentInterface playerComponentInterface = null;

    public TimeMeasureComponent(PlayerComponentInterface playerComponentInterface, TimeDataRepository timeDataRepository){
        this.playerComponentInterface = playerComponentInterface;
        this.timeDataRepository = timeDataRepository;
    }

    @Override
    public int startChronograph() {
        this.startTime = new TimeType(System.currentTimeMillis());
        this.statusOfChronograph = 1;
        return this.statusOfChronograph;
    }

    @Override
    public int endChronograph() {
        this.stoppedTime = 	new TimeType(System.currentTimeMillis() - this.startTime.getMilliseconds());
        this.statusOfChronograph = 0;
        return this.statusOfChronograph;
    }

    @Override
    public TimeType getCurrentTime() {
        return new TimeType(System.currentTimeMillis() - this.startTime.getMilliseconds());
    }

    @Override
    public TimeType getStoppedTime() {
        return this.stoppedTime;
    }

    @Override
    public TimeType getMyBestTimeOfGame(int gameID) {
        return this.myTime.getBestTimeType();
    }

    @Override
    public TimeType getMyAvgTimeOfGame(int gameID) {
        return this.myTime.getAvgTimeType();
    }

    @Override
    public TimeType[] getAllTimeOfGame(int gameID, int spielerID) {
        return new TimeType[0];
    }

    @Override
    public TimeType[] getBestOfGame(int gameID) {
        return new TimeType[0];
    }
}
