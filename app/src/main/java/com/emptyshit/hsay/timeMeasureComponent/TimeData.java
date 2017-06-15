package com.emptyshit.hsay.timeMeasureComponent;

import com.emptyshit.hsay.dataTypes.TimeType;
import com.emptyshit.hsay.dataTypes.TimeTypeConverter;
import java.io.Serializable;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;


import java.util.ArrayList;


/**
 * Representation of the Time
 */
@Entity
public class TimeData implements Serializable{

    @Id
    @Generated
    private Long timeDataId;

    @Transient
    private static final long serialVersionUID = 2L;

    @Property
    private long playerID;

    @Convert(converter = TimeTypeConverter.class, columnType = Double.class)
    private TimeType bestTimeType = null;

    @Convert(converter = TimeTypeConverter.class, columnType = Double.class)
    private TimeType avgTimeType = null;

    // TimeType eine eigene Entität?
    @Transient
    private ArrayList<TimeType> timeTypeLine = new ArrayList<TimeType>();

    @Property
    private int timesPlayed = 0;


    @Generated(hash = 1411090033)
    public TimeData(Long timeDataId, long playerID, TimeType bestTimeType, TimeType avgTimeType, int timesPlayed) {
        this.timeDataId = timeDataId;
        this.playerID = playerID;
        this.bestTimeType = bestTimeType;
        this.avgTimeType = avgTimeType;
        this.timesPlayed = timesPlayed;
    }

    @Generated(hash = 61090496)
    public TimeData() {
    }

    /**
     *
     * @param bestTimeType
     * @return
     */
    private int setBestTime(TimeType bestTimeType){
        if(!this.bestTimeType.isSmallerThan(bestTimeType)) {
            this.bestTimeType = bestTimeType;
            return 1;
        }
        return 0;
    }

    /**
     *
     * @param newTimeType
     * @return
     */
    private TimeType calculateAvgTime(TimeType newTimeType){
        if(timesPlayed > 0) {
            avgTimeType = avgTimeType.multiply(timesPlayed / (timesPlayed + 1.0)).add(newTimeType.divide(timesPlayed + 1));
        } else {
            avgTimeType = newTimeType;
        }
        return avgTimeType;
    }

    /**
     *
     * @param newTimeType
     * @return
     */
    TimeType addNewTime(TimeType newTimeType){
        this.timeTypeLine.add(0, newTimeType);
        if(timeTypeLine.size() > 30 ){
            this.timeTypeLine.remove(30);
        }
        setBestTime(newTimeType);
        calculateAvgTime(newTimeType);
        this.timesPlayed++;
        return newTimeType;
    }

    long getPlayerID() {
        return playerID;
    }

    TimeType getBestTimeType() {
        return bestTimeType;
    }

    TimeType getAvgTimeType() {
        return avgTimeType;
    }

    ArrayList<TimeType> getTimeTypeLine() {
        return timeTypeLine;
    }

    int getTimesPlayed() {
        return timesPlayed;
    }

    void setPlayerID(long playerID) {
        this.playerID = playerID;
    }

    void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    void setBestTimeType(TimeType bestTimeType) {
        this.bestTimeType = bestTimeType;
    }

    void setAvgTimeType(TimeType avgTimeType) {
        this.avgTimeType = avgTimeType;
    }

    public Long getTimeDataID() {
        return this.timeDataId;
    }

    public void setTimeDataID(Long timeDataId) {
        this.timeDataId = timeDataId;
    }

    public Long getTimeDataId() {
        return this.timeDataId;
    }

    public void setTimeDataId(Long timeDataId) {
        this.timeDataId = timeDataId;
    }
}