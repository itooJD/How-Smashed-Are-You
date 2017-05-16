package com.emptyshit.hsay.timeMeasureComponent;

import com.emptyshit.hsay.dataTypes.*;

/**
 * Created by tungu on 09/04/2017.
 */

public interface TimeMeasureComponentInterface {

    int startChronograph();

    int endChronograph();

    TimeType getCurrentTime();

    TimeType getStoppedTime();

    TimeType getMyBestTimeOfGame(int gameID);

    TimeType getMyAvgTimeOfGame(int gameID);

    TimeType[] getAllTimeOfGame(int gameID, int spielerID);

    TimeType[] getBestOfGame(int gameID);
}
