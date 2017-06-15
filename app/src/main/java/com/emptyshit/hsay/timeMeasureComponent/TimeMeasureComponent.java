package com.emptyshit.hsay.timeMeasureComponent;

import android.content.Context;

import com.emptyshit.hsay.dataTypes.TimeType;
import com.emptyshit.hsay.playerComponent.PlayerComponentInterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Implementation of the Time Measure Component
 */
public class TimeMeasureComponent implements TimeMeasureComponentInterface {
    private TimeData myTime = null;
    private TimeType stoppedTime = null;

    private TimeDataRepository timeDataRepository = null;
    private PlayerComponentInterface playerComponentInterface = null;

    private String fileName = "timeData";
    private Context context = null;

    public TimeMeasureComponent(PlayerComponentInterface playerComponentInterface, TimeDataRepository timeDataRepository, Context context){
        this.playerComponentInterface = playerComponentInterface;
        this.timeDataRepository = timeDataRepository;
        this.context = context;

        //load myTime from repository
    }

    /**
     * Cr
     * @param playerId
     * @return
     */
    private TimeData createTimeData(long playerId){
        TimeData timeData = new TimeData();
        timeData.setPlayerID(playerId);
        timeData.setTimesPlayed(0);
        timeData.setBestTimeType(new TimeType(Long.MAX_VALUE));
        timeData.setAvgTimeType(new TimeType(1));
        this.timeDataRepository.save(timeData);
        this.myTime = timeData;
        return timeData;
    }

    @Override
    public boolean addTime(long milliseconds){
        if(milliseconds > 0) {
            this.stoppedTime = new TimeType(milliseconds);
            try {
                this.myTime.addNewTime(this.stoppedTime);
            } catch (Exception e){
                this.createTimeData(this.playerComponentInterface.getMyId());
                this.myTime.addNewTime(this.stoppedTime);
            }
            this.saveLocalTimeOfGame(this.myTime);
            this.timeDataRepository.update(this.myTime);
            return true;
        }
        return false;
    }

    @Override
    public TimeType getStoppedTime() {
        if(this.stoppedTime == null){
            return new TimeType(0);
        }
        return this.stoppedTime;
    }

    @Override
    public TimeType getMyBestTimeOfGame() {
        return this.myTime.getBestTimeType();
    }

    @Override
    public TimeType getMyAvgTimeOfGame() {
        return this.myTime.getAvgTimeType();
    }

    @Override
    public TimeType[] getAllTimeOfGame() {
        //TODO
        return new TimeType[0];
    }

    @Override
    public int getTimesPlayed() {
        return myTime.getTimesPlayed();
    }

    @Override
    public boolean alreadyPlayed() {
        return this.myTime != null && this.myTime.getTimesPlayed() != 0;
    }

    private boolean saveLocalTimeOfGame(TimeData myTime){
        this.myTime = myTime;
        try{
            FileOutputStream fileOutputStream = this.context.openFileOutput(this.fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.myTime);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.myTime != null;
    }

    private TimeData loadLocalTimeOfGame(){
        try {
            FileInputStream fileInputStream = this.context.openFileInput(this.fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            TimeData myTime = (TimeData) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
            this.myTime = myTime;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return this.myTime;
    }

    private boolean deleteLocalTimeOfGame(){
        this.context.deleteFile(this.fileName);
        this.myTime = null;
        return this.myTime == null;
    }
}
