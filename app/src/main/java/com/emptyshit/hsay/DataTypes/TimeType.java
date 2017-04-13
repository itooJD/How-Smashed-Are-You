package com.emptyshit.hsay.DataTypes;

/**
 * Created by huynh_phuong_nguyen on 13.04.17.
 */

public class TimeType {

    private float milliseconds;

    public TimeType(float milliseconds){
        this.milliseconds = milliseconds;
    }

    public boolean isSmaller(TimeType otherTimeType){

        return this.getMilliseconds() < otherTimeType.getMilliseconds();
    }

    public TimeType add(TimeType otherTimeType){
        return new TimeType(this.milliseconds + otherTimeType.getMilliseconds());
    }
    public TimeType multiply(float number){
        return new TimeType(this.milliseconds * number);
    }

    public TimeType divide(int number){
       return  new TimeType(this.milliseconds/ number);
    }
    private float getMilliseconds() {
        return this.milliseconds;
    }
}
