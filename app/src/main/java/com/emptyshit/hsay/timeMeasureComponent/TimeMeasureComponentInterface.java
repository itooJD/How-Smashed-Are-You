package com.emptyshit.hsay.timeMeasureComponent;

import com.emptyshit.hsay.dataTypes.*;

/**
 * Interface of the Time Measure Component, which provides the methods for operating the chronograph
 * and accessing the Time Data
 */
public interface TimeMeasureComponentInterface {

    /**
     * starting the local chronograph
     * @return code(0 = chronograph stopped, 1 = chronograph running)
     */
    boolean addTime(long milliseconds);

    /**
     *
     * @return
     */
    TimeType getStoppedTime();

    /**
     *

     * @return
     */
    TimeType getMyBestTimeOfGame();

    /**
     *
     */
    TimeType getMyAvgTimeOfGame();

    /**
     *
     */
    TimeType[] getAllTimeOfGame();

    /**
     *
     * @return
     */
    int getTimesPlayed();

    boolean alreadyPlayed();
}
