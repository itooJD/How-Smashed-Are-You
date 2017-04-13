package com.emptyshit.hsay.timeMeasureComponent;

import com.emptyshit.hsay.DataTypes.*;

/**
 * Created by tungu on 09/04/2017.
 */

public interface TimeMeasureComponentInterface {

    /**
     *
     * @return
     */
    public TimeType getCurrentTime();

    /**
     *
     * @param gameID
     * @param playerID
     * @return
     */
    public TimeType getMyBestTimeOfGame(int gameID, int playerID);

    /**
     *
     * @param gameID
     * @param playerID
     * @return
     */
    public TimeType getMyAvgTimeOfGame(int gameID, int playerID);

    /**
     *
     * @param gameID
     * @param playerID
     * @return
     */
    public TimeType[] getAllTimeOfGame(int gameID, int playerID);

    /**
     *
     * @param gameID
     * @return
     */
    public TimeData[] getBestOfGame(int gameID);
}
