package com.emptyshit.hsay.timeMeasureComponent;

import com.emptyshit.hsay.dataTypes.*;

/**
 * Interface of the Time Measure Component, which provides the methods for operating the chronograph
 * and accessing the Time Data
 */
public interface TimeMeasureComponentInterface {

    /**
     * starting the local chronograph
     * @param gameId
     * @return code(0 = chronograph stopped, 1 = chronograph running)
     */
    TimeData addTime(long milliseconds, long gameId);

    /**
     *
     * @return
     */
    TimeType getStoppedTime();

    /**
     *
     * @param gameId
     * @return
     */
    TimeType getMyBestTimeOfGame(int gameId);

    /**
     *
     */
    TimeType getMyAvgTimeOfGame(int gameId);

    /**
     *
     * @param gameId
     * @param playerId
     * @return
     */
    TimeType[] getAllTimeOfGame(int gameId, int playerId);

    /**
     *
     * @param gameId
     * @return
     */
    TimeType[] getBestOfGame(int gameId);
}
