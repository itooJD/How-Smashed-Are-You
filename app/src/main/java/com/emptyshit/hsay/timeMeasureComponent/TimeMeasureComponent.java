package com.emptyshit.hsay.timeMeasureComponent;

import com.emptyshit.hsay.dataTypes.TimeType;
import com.emptyshit.hsay.playerComponent.PlayerComponentInterface;

/**
 * Implementation of the Time Measure Component
 */
public class TimeMeasureComponent implements TimeMeasureComponentInterface {
    private TimeData myTime = null;
    private TimeType stoppedTime = null;

    private TimeDataRepository timeDataRepository = null;
    private PlayerComponentInterface playerComponentInterface = null;

    public TimeMeasureComponent(PlayerComponentInterface playerComponentInterface, TimeDataRepository timeDataRepository){
        this.playerComponentInterface = playerComponentInterface;
        this.timeDataRepository = timeDataRepository;
    }

    @Override
    public TimeData addTime(long milliseconds, long gameId){
        this.myTime = this.timeDataRepository.getTimeData(this.playerComponentInterface.getMyId(), gameId);
        if(milliseconds > 0) {
            this.stoppedTime = new TimeType(milliseconds);
            this.myTime.addNewTime(this.stoppedTime);
            this.timeDataRepository.updateTimeData(this.myTime);
        }
        return this.myTime;
    }

    @Override
    public TimeType getStoppedTime() {
        if(this.stoppedTime == null){
            return new TimeType(0);
        }
        return this.stoppedTime;
    }

    @Override
    public TimeType getMyBestTimeOfGame(int gameId) {
        this.myTime = this.timeDataRepository.getTimeData(this.playerComponentInterface.getMyId(), gameId);
        return this.myTime.getBestTimeType();
    }

    @Override
    public TimeType getMyAvgTimeOfGame(int gameId) {
        this.myTime = this.timeDataRepository.getTimeData(this.playerComponentInterface.getMyId(), gameId);
        return this.myTime.getAvgTimeType();
    }

    @Override
    public TimeType[] getAllTimeOfGame(int gameId, int playerId) {
        //TODO
        return new TimeType[0];
    }

    @Override
    public TimeType[] getBestOfGame(int gameId) {
        //TODO
        return new TimeType[0];
    }

    private boolean loadLocalTimeOfGame(int gameId){
        return this.timeDataRepository.getTimeData(this.playerComponentInterface.getMyId(), gameId) != null;
    }
}
