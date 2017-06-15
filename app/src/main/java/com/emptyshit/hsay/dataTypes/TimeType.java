package com.emptyshit.hsay.dataTypes;

import java.io.Serializable;

/**
 * Representation of the Time
 */
public class TimeType implements Serializable{

    private static final long serialVersionUID = 3L;

    private double milliseconds;

    public TimeType(double milliseconds){
        this.milliseconds = milliseconds;
    }

    /**
     * checks if the Time is smaller than the other Time
     * @param otherTimeType
     * @return
     */
    public boolean isSmallerThan(TimeType otherTimeType){
        return this.getMilliseconds() < otherTimeType.getMilliseconds();
    }

    /**
     * add two times together
     * @param otherTimeType
     * @return
     */
    public TimeType add(TimeType otherTimeType){
        return new TimeType(this.milliseconds + otherTimeType.getMilliseconds());
    }

    /**
     * multiply two times together
     * @param number
     * @return
     */
    public TimeType multiply(double number){
        return new TimeType(this.milliseconds * number);
    }

    /**
     * devide two times x.devide(y) => x / y
     * @param number
     * @return
     */
    public TimeType divide(double number){
       return  new TimeType(this.milliseconds/ number);
    }

    public double getMilliseconds() {
        return this.milliseconds;
    }

    @Override
    public String toString(){
        return this.milliseconds + " ms";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeType timeType = (TimeType) o;

        return Double.compare(timeType.milliseconds, milliseconds) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(milliseconds);
        return (int) (temp ^ (temp >>> 32));
    }
}
