package com.emptyshit.hsay.timeMeasureComponent;

import com.emptyshit.hsay.dataTypes.TimeType;
import com.emptyshit.hsay.playerComponent.PlayerComponentInterface;

/**
 * Created by tungu on 09/04/2017.
 */

public class TimeMeasureComponent implements TimeMeasureComponentInterface {

    private PlayerComponentInterface playerComponentInterface;

    public TimeMeasureComponent(){

    }

    public TimeMeasureComponent(PlayerComponentInterface playerComponentInterface){
        this.playerComponentInterface = playerComponentInterface;
    }

    @Override
    public TimeType getCurrentTime() {
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
    public TimeType[] getAllTimeOfGame(int gameID, int playerID) {
        return new TimeType[0];
    }

    @Override
    public TimeData[] getBestOfGame(int gameID) {
        return new TimeData[0];
    }
}
