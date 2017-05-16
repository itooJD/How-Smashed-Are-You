package com.emptyshit.hsay.dataTypes;

/**
 * Representation of the Time
 */
public class TimeType {

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
    public TimeType multiply(float number){
        return new TimeType(this.milliseconds * number);
    }

    /**
     * devide two times x.devide(y) => x / y
     * @param number
     * @return
     */
    public TimeType divide(int number){
       return  new TimeType(this.milliseconds/ number);
    }

    public double getMilliseconds() {
        return this.milliseconds;
    }

    @Override
    public String toString(){
        return this.milliseconds + " ms";
    }
}
