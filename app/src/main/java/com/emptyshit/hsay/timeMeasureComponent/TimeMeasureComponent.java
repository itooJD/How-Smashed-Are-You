package com.emptyshit.hsay.timeMeasureComponent;

import com.emptyshit.hsay.dataTypes.TimeType;
import com.emptyshit.hsay.playerComponent.PlayerComponentInterface;

/**
 * Created by tungu on 09/04/2017.
 */

public class TimeMeasureComponent implements TimeMeasureComponentInterface {

    private TimeDataRepository timeDataRepository;
    private PlayerComponentInterface playerComponentInterface;

    public TimeMeasureComponent(PlayerComponentInterface playerComponentInterface, TimeDataRepository timeDataRepository){
        this.playerComponentInterface = playerComponentInterface;
        this.timeDataRepository = timeDataRepository;
    }

    @Override
    public int startChronograph() {
        return 0;
    }

    @Override
    public int endChronograph() {
        return 0;
    }

    @Override
    public TimeType getCurrentTime() {
        return null;
    }

    @Override
    public TimeType getStoppedTime() {
        return null;
    }

    @Override
    public TimeType getMyBestTimeOfGame(int gameID, int playerID) {
        return null;
    }

    @Override
    public TimeType getMyAvgTimeOfGame(int gameID, int playerID) {
        return null;
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
